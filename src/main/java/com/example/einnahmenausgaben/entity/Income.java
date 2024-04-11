package com.example.einnahmenausgaben.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "incomes")
public class Income {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal amount;
    private String description;
    private Date date;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
