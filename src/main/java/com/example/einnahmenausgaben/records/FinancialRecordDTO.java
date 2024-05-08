package com.example.einnahmenausgaben.records;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
@Data
@AllArgsConstructor
public class FinancialRecordDTO {
    private BigDecimal amount;
    private String description;
    private LocalDate date;
    private String type; // "Income" oder "Expense"


}
