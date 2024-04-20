package com.example.einnahmenausgaben.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
@Data
@AllArgsConstructor
public class IncomeDto {
    private BigDecimal amount;
    private String description;
    private LocalDate date;
}
