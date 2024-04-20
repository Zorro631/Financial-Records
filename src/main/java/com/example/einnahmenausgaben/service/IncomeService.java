package com.example.einnahmenausgaben.service;

import com.example.einnahmenausgaben.entity.Income;
import com.example.einnahmenausgaben.entity.UserEntity;
import com.example.einnahmenausgaben.repository.IncomeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
public class IncomeService {

    IncomeRepository incomeRepository;

    @Autowired
    public IncomeService(IncomeRepository incomeRepository) {
        this.incomeRepository = incomeRepository;
    }

    @Transactional
    public ResponseEntity<String> addIncome(Income income) {
        incomeRepository.save(income);
        return new ResponseEntity<>("Income successfully added", HttpStatus.CREATED);
    }
    @Transactional(readOnly = true)
    public List<Income> getAllIncome(UserEntity user) {

        return incomeRepository.findByUserEntity(user);
//        return toDoRepository.findAll();
    }

}
