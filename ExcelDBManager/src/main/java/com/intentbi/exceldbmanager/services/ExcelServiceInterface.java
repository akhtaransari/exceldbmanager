package com.intentbi.exceldbmanager.services;

import com.intentbi.exceldbmanager.entity.ExcelFileProcessor;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

public interface ExcelServiceInterface {
    String uploadExcelFile(MultipartFile file);
    String updateData(Long id, ExcelFileProcessor newData);
    String deleteData(Long id);
    Page<ExcelFileProcessor> getDataByPageNumber(int page , String type , String direction);
}
