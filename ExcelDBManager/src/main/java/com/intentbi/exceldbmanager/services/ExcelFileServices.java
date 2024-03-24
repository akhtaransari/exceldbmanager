package com.intentbi.exceldbmanager.services;

import com.intentbi.exceldbmanager.entity.ExcelFileProcessor;
import com.intentbi.exceldbmanager.exception.IntentBiException;
import com.intentbi.exceldbmanager.repository.ExcelFileProcessorRepository;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

@Service
public class ExcelFileServices implements ExcelServiceInterface {

    @Autowired
    ExcelFileProcessorRepository excelFileProcessorRepository;

    /**
     * Uploads an Excel file and saves its data to the database.
     * @param file The Excel file to upload.
     * @return A message indicating the result of the upload operation.
     */
    public String uploadExcelFile(MultipartFile file) {
        try {
            // Load Excel file
            Workbook workbook = new XSSFWorkbook(file.getInputStream());

            // Get the first sheet
            Sheet sheet = workbook.getSheetAt(0);

            // Iterate through each row of the sheet
            Iterator<Row> iterator = sheet.iterator();
            List<ExcelFileProcessor> processorsToInsert = new ArrayList<>();
            while (iterator.hasNext()) {
                Row currentRow = iterator.next();

                // Skip header row
                if (currentRow.getRowNum() == 0) {
                    continue;
                }
                // Create entity from each row and add it to the appropriate list
                ExcelFileProcessor processor = createProcessorFromRow(currentRow);
                if (isRowComplete(processor)) {
                    processorsToInsert.add(processor);
                } else {
                    throw new IntentBiException("Row " + currentRow.getRowNum() + " data is incomplete. Please fix the data and re-upload your file.");
                }
            }

            excelFileProcessorRepository.saveAll(processorsToInsert);

            workbook.close();
        } catch (IOException e) {
            throw new IntentBiException("Failed to upload Excel file: " + e.getMessage());
        }
        return "File uploaded successfully.";
    }

    /**
     * Creates an ExcelFileProcessor object from a row in the Excel sheet.
     * @param row The row containing data.
     * @return An ExcelFileProcessor object created from the row.
     */
    private ExcelFileProcessor createProcessorFromRow(Row row) {
        ExcelFileProcessor processor = new ExcelFileProcessor();
        try {
            processor.setMarket(row.getCell(0).getStringCellValue());
            processor.setCountry(row.getCell(1).getStringCellValue());
            processor.setProduct(row.getCell(2).getStringCellValue());
            processor.setDiscountBand(row.getCell(3).getStringCellValue());

            processor.setUnitsSold(BigDecimal.valueOf(row.getCell(4).getNumericCellValue()));
            processor.setManufacturingPrice(parseMonetaryValue(row.getCell(5).toString()));
            processor.setSalePrice(parseMonetaryValue(row.getCell(6).toString()));
            processor.setGrossSales(parseMonetaryValue(row.getCell(7).toString()));
            processor.setSales(parseMonetaryValue(row.getCell(8).toString()));
            processor.setCOGS(parseMonetaryValue(row.getCell(9).toString()));
            processor.setProfit(parseMonetaryValue(row.getCell(10).toString()));

            processor.setDate(row.getCell(11).getLocalDateTimeCellValue().toLocalDate());
            processor.setMonthNumber((int) row.getCell(12).getNumericCellValue());
            processor.setMonthName(row.getCell(13).getStringCellValue());
            processor.setYear((int) row.getCell(14).getNumericCellValue());
        } catch (Exception e) {
            throw new IntentBiException("Error: Row " + (row.getRowNum() + 1) + " contains incomplete data. Please review and fix the data, then re-upload your file.");
        }
        return processor;
    }

    /**
     * Parses a monetary value string to BigDecimal.
     * @param monetaryValue The monetary value string to parse.
     * @return The BigDecimal value parsed from the string.
     */
    public static BigDecimal parseMonetaryValue(String monetaryValue) {
        String numericValue = monetaryValue.replaceAll("[^\\d.]", "");
        return new BigDecimal(numericValue);
    }

    /**
     * Checks if an ExcelFileProcessor object contains complete data.
     * @param processor The ExcelFileProcessor object to check.
     * @return true if all fields are non-null, false otherwise.
     */
    private boolean isRowComplete(ExcelFileProcessor processor) {
        return processor.getMarket() != null &&
                processor.getCountry() != null &&
                processor.getProduct() != null &&
                processor.getDiscountBand() != null &&
                processor.getUnitsSold() != null &&
                processor.getManufacturingPrice() != null &&
                processor.getSalePrice() != null &&
                processor.getGrossSales() != null &&
                processor.getSales() != null &&
                processor.getCOGS() != null &&
                processor.getDate() != null &&
                processor.getMonthNumber() != null &&
                processor.getMonthName() != null &&
                processor.getYear() != null;
    }

    /**
     * Updates an existing Excel file data by ID.
     * @param id The ID of the data to be updated.
     * @param newData The new data to update.
     * @return A message indicating the result of the update operation.
     */
    @Override
    public String updateData(Long id, ExcelFileProcessor newData) {
        try {
            // Call the repository method to update data by ID
            excelFileProcessorRepository.updateDataById(id, newData.getMarket(), newData.getCountry(),
                    newData.getProduct(), newData.getDiscountBand(), newData.getUnitsSold(),
                    newData.getManufacturingPrice(), newData.getSalePrice(), newData.getGrossSales(),
                    newData.getSales(), newData.getCOGS(), newData.getProfit(), newData.getDate(),
                    newData.getMonthNumber(), newData.getMonthName(), newData.getYear());

            return "Data with ID " + id + " updated successfully.";
        } catch (Exception e) {
            return "Failed to update data with ID " + id + ": " + e.getMessage();
        }
    }

    /**
     * Deletes data from the Excel file by ID.
     * @param id The ID of the data to be deleted.
     * @return A message indicating the result of the delete operation.
     */
    @Override
    public String deleteData(Long id) {
        if (excelFileProcessorRepository.existsById(id)) {
            excelFileProcessorRepository.deleteById(id);
            return "Data with ID " + id + " deleted successfully.";
        } else {
            throw new IntentBiException("Data with ID " + id + " not found.");
        }
    }

    /**
     * Retrieves data from the Excel file by page number.
     * @param page The page number.
     * @return A Page object containing data for the specified page number.
     */
    @Override
    public Page<ExcelFileProcessor> getDataByPageNumber(int page) {
        Pageable pageable = PageRequest.of(page, 10);
        Page<ExcelFileProcessor> dataPage = excelFileProcessorRepository.findAll(pageable);

        if (dataPage.isEmpty()) {
            throw new IntentBiException("No data available.");
        }
        return dataPage;
    }
}
