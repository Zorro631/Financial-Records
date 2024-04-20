package com.example.einnahmenausgaben.controller;

import com.example.einnahmenausgaben.dto.ExpenseDto;
import com.example.einnahmenausgaben.dto.IncomeDto;
import com.example.einnahmenausgaben.entity.Expense;
import com.example.einnahmenausgaben.entity.Income;
import com.example.einnahmenausgaben.entity.UserEntity;
import com.example.einnahmenausgaben.repository.UserRepository;
import com.example.einnahmenausgaben.service.ExpenseService;
import com.example.einnahmenausgaben.service.IncomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
@RestController
@RequestMapping("/api/finances/expense")
public class ExpenseController {
    ExpenseService expenseService;
    UserRepository userRepository;

    @Autowired
    public ExpenseController(ExpenseService expenseService, UserRepository userRepository) {
        this.expenseService = expenseService;
        this.userRepository = userRepository;
    }

    @PostMapping("/add")
    public ResponseEntity<String> addExpense(@RequestBody Expense expense, Authentication authentication) {
        String username = authentication.getName();
        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        expense.setUserEntity(user);
        return expenseService.addExpense(expense);
    }
    @GetMapping("/view")
    public ResponseEntity<List<ExpenseDto>> viewExpense(Authentication authentication){
        String username = authentication.getName();
        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<Expense> expenses = expenseService.getAllExpense(user);
        List<ExpenseDto> expenseDtos = expenses.stream()
                .map(expense -> new ExpenseDto(expense.getAmount(), expense.getDescription(), expense.getDate()))
                .collect(Collectors.toList());

        return ResponseEntity.ok(expenseDtos);

    }

}
