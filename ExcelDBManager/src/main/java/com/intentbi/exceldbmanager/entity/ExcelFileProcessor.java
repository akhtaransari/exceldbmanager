package com.intentbi.exceldbmanager.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Represents an entity for processing Excel data.
 */
@Entity
@Data
@Table(name = "excel_data")
public class ExcelFileProcessor {

    /**
     * The unique identifier for the data.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * The market associated with the data.
     */
    @NotBlank(message = "Market property is empty")
    @Column(name = "market", nullable = false, length = 255)
    private String market;

    /**
     * The country associated with the data.
     */
    @NotBlank(message = "Country property is empty")
    @Column(name = "country", nullable = false, length = 255)
    private String country;

    /**
     * The product associated with the data.
     */
    @NotBlank(message = "Product property is empty")
    @Column(name = "product", nullable = false, length = 255)
    private String product;

    /**
     * The discount band associated with the data.
     */
    @NotNull(message = "Discount Band property is empty")
    @Column(name = "discount_band", nullable = false, length = 255)
    private String discountBand;

    /**
     * The number of units sold.
     */
    @NotNull(message = "Units Sold property is empty")
    @Column(name = "units_sold", nullable = false, precision = 10, scale = 2)
    private BigDecimal unitsSold;

    /**
     * The manufacturing price.
     */
    @NotNull(message = "Manufacturing Price property is empty")
    @Column(name = "manufacturing_price", nullable = false, precision = 10, scale = 2)
    private BigDecimal manufacturingPrice;

    /**
     * The sale price.
     */
    @NotNull(message = "Sale Price property is empty")
    @Column(name = "sale_price", nullable = false, precision = 10, scale = 2)
    private BigDecimal salePrice;

    /**
     * The gross sales.
     */
    @NotNull(message = "Gross Sales property is empty")
    @Column(name = "gross_sales", nullable = false, precision = 10, scale = 2)
    private BigDecimal grossSales;

    /**
     * The sales.
     */
    @NotNull(message = "Sales property is empty")
    @Column(name = "sales", nullable = false, precision = 10, scale = 2)
    private BigDecimal sales;

    /**
     * The cost of goods sold (COGS).
     */
    @NotNull(message = "COGS property is empty")
    @Column(name = "cogs", nullable = false, precision = 10, scale = 2)
    private BigDecimal COGS;

    /**
     * The profit.
     */
    @NotNull(message = "Profit property is empty")
    @Column(name = "profit", nullable = false, precision = 10, scale = 2)
    private BigDecimal profit;

    /**
     * The date associated with the data.
     */
    @NotNull(message = "Date property is empty")
    @Column(name = "date", nullable = false)
    private LocalDate date;

    /**
     * The month number.
     */
    @NotNull(message = "Month Number property is empty")
    @Column(name = "month_number", nullable = false)
    private Integer monthNumber;

    /**
     * The month name.
     */
    @NotNull(message = "Month Name property is empty")
    @Column(name = "month_name", nullable = false, length = 255)
    private String monthName;

    /**
     * The year.
     */
    @NotNull(message = "Year property is empty")
    @Column(name = "year", nullable = false)
    private Integer year;
}
