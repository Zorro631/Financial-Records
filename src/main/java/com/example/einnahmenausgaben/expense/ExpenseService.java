package com.example.einnahmenausgaben.expense;

import com.example.einnahmenausgaben.auth.UserEntity;
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
