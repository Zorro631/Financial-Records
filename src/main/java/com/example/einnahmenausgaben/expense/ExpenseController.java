package com.example.einnahmenausgaben.expense;

import com.example.einnahmenausgaben.auth.UserEntity;
import com.example.einnahmenausgaben.auth.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
