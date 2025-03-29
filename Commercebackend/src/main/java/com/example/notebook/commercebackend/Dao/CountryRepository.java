package com.example.notebook.commercebackend.Dao;

import com.example.notebook.commercebackend.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;


public interface CountryRepository extends JpaRepository<Country, Integer> {
}
