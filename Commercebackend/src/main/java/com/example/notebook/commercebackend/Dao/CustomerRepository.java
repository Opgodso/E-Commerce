package com.example.notebook.commercebackend.Dao;

import com.example.notebook.commercebackend.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
