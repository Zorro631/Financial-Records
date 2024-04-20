package com.example.einnahmenausgaben.service;

import com.example.einnahmenausgaben.entity.Expense;
import com.example.einnahmenausgaben.entity.Income;
import com.example.einnahmenausgaben.entity.UserEntity;
import com.example.einnahmenausgaben.repository.ExpenseRepository;
import com.example.einnahmenausgaben.repository.IncomeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseService {
    ExpenseRepository expenseRepository;

    @Autowired
    public ExpenseService(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    public ResponseEntity<String> addExpense(Expense expense) {
        expenseRepository.save(expense);
        return new ResponseEntity<>("Expense successfully added", HttpStatus.CREATED);
    }
    public List<Expense> getAllExpense(UserEntity user) {
        return expenseRepository.findByUserEntity(user);
    }
}
