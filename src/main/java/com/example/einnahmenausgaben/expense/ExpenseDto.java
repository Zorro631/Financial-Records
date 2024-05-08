package com.example.einnahmenausgaben.expense;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;


@Data
@AllArgsConstructor
public class ExpenseDto {
    private BigDecimal amount;
    private String description;
    private LocalDate date;
}
