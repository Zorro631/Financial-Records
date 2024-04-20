package com.example.einnahmenausgaben.controller;

import com.example.einnahmenausgaben.dto.IncomeDto;
import com.example.einnahmenausgaben.entity.Income;
import com.example.einnahmenausgaben.entity.UserEntity;
import com.example.einnahmenausgaben.repository.UserRepository;
import com.example.einnahmenausgaben.service.IncomeService;
//import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/finances/income")
public class IncomeController {
    IncomeService incomeService;
    UserRepository userRepository;
//    IncomeRepository incomeRepository;
    @Autowired
    public IncomeController(IncomeService incomeService, UserRepository userRepository//,IncomeRepository incomeRepository
    ) {
        this.incomeService = incomeService;
        this.userRepository = userRepository;
//        this.incomeRepository = incomeRepository;
    }

    @PostMapping("/add")
    public ResponseEntity<String> addIncome(@RequestBody Income income, Authentication authentication) {
        String username = authentication.getName();
        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        income.setUserEntity(user);
        return incomeService.addIncome(income);
    }
    @GetMapping("/view")
    public ResponseEntity<List<IncomeDto>> viewIncome(Authentication authentication){
        String username = authentication.getName();
        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<Income> incomes = incomeService.getAllIncome(user);
        List<IncomeDto> incomeDTOs = incomes.stream()
                .map(income -> new IncomeDto(income.getAmount(), income.getDescription(), income.getDate()))
                .collect(Collectors.toList());

        return ResponseEntity.ok(incomeDTOs);

//        List<Income> userIncomes = incomeService.getAllIncome(user);
//        return new ResponseEntity<>(userIncomes, HttpStatus.OK);
    }

    @GetMapping("/test")
    public ResponseEntity<String> test() {
//        return "test";
        return new ResponseEntity<>("test", HttpStatus.OK);

    }

}