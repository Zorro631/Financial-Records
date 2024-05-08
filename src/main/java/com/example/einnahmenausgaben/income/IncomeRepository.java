package com.example.einnahmenausgaben.income;

import com.example.einnahmenausgaben.auth.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IncomeRepository  extends JpaRepository<Income, Long> {

    List<Income> findByUserEntity(UserEntity user);

}
