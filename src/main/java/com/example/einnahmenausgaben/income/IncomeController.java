package com.example.einnahmenausgaben.income;

import com.example.einnahmenausgaben.auth.UserEntity;
import com.example.einnahmenausgaben.auth.UserRepository;
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
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteIncome(@PathVariable Long id) {
        if (!incomeService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        incomeService.deleteById(id);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/test")
    public ResponseEntity<String> test() {
//        return "test";
        return new ResponseEntity<>("test", HttpStatus.OK);

    }

}