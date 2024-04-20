package com.example.einnahmenausgaben.repository;

import com.example.einnahmenausgaben.entity.Income;
import com.example.einnahmenausgaben.entity.Role;
import com.example.einnahmenausgaben.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IncomeRepository  extends JpaRepository<Income, Long> {

    List<Income> findByUserEntity(UserEntity user);

}
