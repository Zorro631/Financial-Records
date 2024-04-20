package com.example.einnahmenausgaben.controller;

import com.example.einnahmenausgaben.dto.FinancialRecordDTO;
import com.example.einnahmenausgaben.entity.Expense;
import com.example.einnahmenausgaben.entity.Income;
import com.example.einnahmenausgaben.entity.UserEntity;
import com.example.einnahmenausgaben.repository.ExpenseRepository;
import com.example.einnahmenausgaben.repository.IncomeRepository;
import com.example.einnahmenausgaben.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/finances")
public class FinancialRecordController {

    private IncomeRepository incomeRepository;
    private ExpenseRepository expenseRepository;
    private UserRepository userRepository;

    @Autowired
    public FinancialRecordController(IncomeRepository incomeRepository, ExpenseRepository expenseRepository, UserRepository userRepository) {
        this.incomeRepository = incomeRepository;
        this.expenseRepository = expenseRepository;
        this.userRepository = userRepository;
    }
    @GetMapping("/financial-records")
    public ResponseEntity<List<FinancialRecordDTO>> getFinancialRecords(Authentication authentication) {
        String username = authentication.getName();
        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<Income> incomes = incomeRepository.findByUserEntity(user);
        List<Expense> expenses = expenseRepository.findByUserEntity(user);

        List<FinancialRecordDTO> financialRecords = new ArrayList<>();

        financialRecords.addAll(incomes.stream()
                .map(income -> new FinancialRecordDTO(income.getAmount(), income.getDescription(), income.getDate(), "Income"))
                .toList());
        financialRecords.addAll(expenses.stream()
                .map(expense -> new FinancialRecordDTO(expense.getAmount(), expense.getDescription(), expense.getDate(), "Expense"))
                .toList());

        financialRecords.sort(Comparator.comparing(FinancialRecordDTO::getDate));

        return ResponseEntity.ok(financialRecords);
    }
}
