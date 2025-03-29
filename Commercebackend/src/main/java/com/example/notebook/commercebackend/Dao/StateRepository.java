package com.example.notebook.commercebackend.Dao;

import com.example.notebook.commercebackend.entity.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StateRepository extends JpaRepository<State, Long> {
    List<State> findByCountryCode(String code);
}
