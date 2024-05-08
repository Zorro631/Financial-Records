package com.example.einnahmenausgaben.expense;

import com.example.einnahmenausgaben.auth.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ExpenseRepository   extends JpaRepository<Expense, Long> {
    List<Expense> findByUserEntity(UserEntity user);

}
