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
     * Retrieves data by page number.
     * @param page The page number.
     * @return ResponseEntity containing the data on the specified page.
     */
    @GetMapping("/data/{page}")
    public ResponseEntity<Page<ExcelFileProcessor>> getDataByPage(@RequestParam(defaultValue = "0") int page) {
        return new ResponseEntity<>(excelServiceInterface.getDataByPageNumber(page), HttpStatus.ACCEPTED);
    }
}
