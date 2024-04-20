package com.example.einnahmenausgaben.repository;

import com.example.einnahmenausgaben.entity.Expense;
import com.example.einnahmenausgaben.entity.Income;
import com.example.einnahmenausgaben.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ExpenseRepository   extends JpaRepository<Expense, Long> {
    List<Expense> findByUserEntity(UserEntity user);

}
