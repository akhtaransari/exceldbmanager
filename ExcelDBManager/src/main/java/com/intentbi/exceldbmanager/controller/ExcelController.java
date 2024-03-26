package com.intentbi.exceldbmanager.controller;

import com.intentbi.exceldbmanager.entity.ExcelFileProcessor;
import com.intentbi.exceldbmanager.services.ExcelServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * Controller class for handling Excel-related operations.
 */
@RestController
@CrossOrigin("*")
public class ExcelController {
    @Autowired
    ExcelServiceInterface excelServiceInterface;

    /**
     * Handles the uploading of an Excel file.
     * @param file The Excel file to upload.
     * @return ResponseEntity indicating the result of the upload operation.
     */
    @PostMapping("/upload")
    public ResponseEntity<String> uploadExcelFile(@RequestParam("file") MultipartFile file) {
        // Check if the file exists
        if (file.isEmpty()) {
            // Return a bad request response if no file is provided
            return new ResponseEntity<>("Please select a file to upload", HttpStatus.BAD_REQUEST);
        }

        // Check if the file is an Excel file by examining its content type
        String contentType = file.getContentType();
        if (contentType == null || (!contentType.equals("application/vnd.ms-excel") && !contentType.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))) {
            // Return a bad request response if the file is not an Excel file
            return new ResponseEntity<>("Only Excel files are allowed", HttpStatus.BAD_REQUEST);
        }

        // Proceed with uploading the Excel file
        return new ResponseEntity<>(excelServiceInterface.uploadExcelFile(file), HttpStatus.ACCEPTED);
    }


    /**
     * Handles updating data based on the provided ID.
     * @param id The ID of the data to update.
     * @param newData The updated data.
     * @return ResponseEntity indicating the result of the update operation.
     */
    @PutMapping("/edit/{id}")
    public ResponseEntity<String> updateData(@PathVariable Long id, @RequestBody ExcelFileProcessor newData) {
        return new ResponseEntity<>(excelServiceInterface.updateData(id,newData), HttpStatus.OK);
    }

    /**
     * Handles deleting data based on the provided ID.
     * @param id The ID of the data to delete.
     * @return ResponseEntity indicating the result of the delete operation.
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteData(@PathVariable Long id) {
        return new ResponseEntity<>(excelServiceInterface.deleteData(id), HttpStatus.ACCEPTED);
    }

    /**
     * Retrieves data by page number, with optional sorting parameters.
     *
     * @param page      The page number to retrieve.
     * @param type      The field type to sort by. Defaults to "name" if not provided.
     * @param direction The sort direction. Defaults to "asc" (ascending) if not provided.
     * @return ResponseEntity containing the data on the specified page.
     */
    @GetMapping("/data/page?page={page}&type={type}&direction={direction}")
    public ResponseEntity<Page<ExcelFileProcessor>> getDataByPage(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "name") String type,
            @RequestParam(defaultValue = "asc") String direction
    ) {
        return new ResponseEntity<>(excelServiceInterface.getDataByPageNumber(page, type, direction), HttpStatus.ACCEPTED);
    }

}
