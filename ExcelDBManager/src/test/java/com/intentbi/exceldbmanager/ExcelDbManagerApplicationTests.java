package com.intentbi.exceldbmanager;

import com.intentbi.exceldbmanager.services.ExcelFileServices;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.intentbi.exceldbmanager.entity.ExcelFileProcessor;
import com.intentbi.exceldbmanager.exception.IntentBiException;
import com.intentbi.exceldbmanager.repository.ExcelFileProcessorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
		import static org.mockito.Mockito.*;

@SpringBootTest
public class ExcelDbManagerApplicationTests {

	@Mock
	private ExcelFileProcessorRepository excelFileProcessorRepository;

	@InjectMocks
	private ExcelFileServices excelFileServices;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testUploadExcelFile_Success() throws IOException {
		// Arrange
		byte[] fileBytes = "Test Excel Content".getBytes();
		MultipartFile multipartFile = new MockMultipartFile("test.xlsx", fileBytes);

		// Stub the repository's saveAll method to return a testProcessorList
		when(excelFileProcessorRepository.saveAll(any())).thenReturn(getTestProcessorList());

		// Act
		String result = excelFileServices.uploadExcelFile(multipartFile);

		// Assert
		// Check that the method returns the expected success message
		assertEquals("File uploaded successfully.", result);
		// Verify that the saveAll method of the repository was called once with any argument
		verify(excelFileProcessorRepository, times(1)).saveAll(any());
	}

	@Test
	public void testUploadExcelFile_ExceptionThrown() throws IOException {
		// Arrange
		byte[] fileBytes = "Test Excel Content".getBytes();
		MultipartFile multipartFile = new MockMultipartFile("test.xlsx", fileBytes);

		// Stub the repository's saveAll method to throw a RuntimeException
		when(excelFileProcessorRepository.saveAll(any())).thenThrow(new RuntimeException("Test Exception"));

		// Act & Assert
		// Check that the method throws an IntentBiException when a RuntimeException is thrown
		assertThrows(IntentBiException.class, () -> excelFileServices.uploadExcelFile(multipartFile));
		// Verify that the saveAll method of the repository was called once with any argument
		verify(excelFileProcessorRepository, times(1)).saveAll(any());
	}

	private List<ExcelFileProcessor> getTestProcessorList() {
		List<ExcelFileProcessor> processors = new ArrayList<>();

		// Create a new instance of ExcelFileProcessor with default constructor
		ExcelFileProcessor processor = new ExcelFileProcessor();

		// Set values for each field individually
		processor.setId(1L);
		processor.setMarket("Market1");
		processor.setCountry("Country1");
		processor.setProduct("Product1");
		processor.setDiscountBand("Band1");
		processor.setUnitsSold(BigDecimal.ONE);
		processor.setManufacturingPrice(BigDecimal.ONE);
		processor.setSalePrice(BigDecimal.ONE);
		processor.setGrossSales(BigDecimal.ONE);
		processor.setSales(BigDecimal.ONE);
		processor.setCOGS(BigDecimal.ONE);
		processor.setProfit(BigDecimal.ONE);
		processor.setDate(LocalDate.now());
		processor.setMonthNumber(1);
		processor.setMonthName("January");
		processor.setYear(2024);

		// Add the populated processor to the list
		processors.add(processor);

		// Add more test data as needed

		return processors;
	}

}

