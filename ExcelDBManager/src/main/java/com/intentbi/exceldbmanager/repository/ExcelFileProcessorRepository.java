package com.intentbi.exceldbmanager.repository;

import com.intentbi.exceldbmanager.entity.ExcelFileProcessor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Repository interface for managing ExcelFileProcessor entities.
 */
public interface ExcelFileProcessorRepository extends JpaRepository<ExcelFileProcessor, Long> {

    /**
     * Method to update data by ID.
     *
     * @param id               The ID of the entity to be updated.
     * @param market           The updated market value.
     * @param country          The updated country value.
     * @param product          The updated product value.
     * @param discountBand     The updated discount band value.
     * @param unitsSold        The updated units sold value.
     * @param manufacturingPrice The updated manufacturing price value.
     * @param salePrice        The updated sale price value.
     * @param grossSales       The updated gross sales value.
     * @param sales            The updated sales value.
     * @param COGS             The updated COGS value.
     * @param profit           The updated profit value.
     * @param date             The updated date value.
     * @param monthNumber      The updated month number value.
     * @param monthName        The updated month name value.
     * @param year             The updated year value.
     */
    @Modifying
    @Transactional
    @Query("UPDATE ExcelFileProcessor e SET " +
            "e.market = :market, " +
            "e.country = :country, " +
            "e.product = :product, " +
            "e.discountBand = :discountBand, " +
            "e.unitsSold = :unitsSold, " +
            "e.manufacturingPrice = :manufacturingPrice, " +
            "e.salePrice = :salePrice, " +
            "e.grossSales = :grossSales, " +
            "e.sales = :sales, " +
            "e.COGS = :COGS, " +
            "e.profit = :profit, " +
            "e.date = :date, " +
            "e.monthNumber = :monthNumber, " +
            "e.monthName = :monthName, " +
            "e.year = :year " +
            "WHERE e.id = :id")
    void updateDataById(Long id, String market, String country, String product, String discountBand,
                        BigDecimal unitsSold, BigDecimal manufacturingPrice, BigDecimal salePrice,
                        BigDecimal grossSales, BigDecimal sales, BigDecimal COGS, BigDecimal profit,
                        LocalDate date, Integer monthNumber, String monthName, Integer year);
}
