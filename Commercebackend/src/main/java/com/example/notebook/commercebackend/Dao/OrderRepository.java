package com.example.notebook.commercebackend.Dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.notebook.commercebackend.entity.Order;
import org.springframework.data.repository.query.Param;


public interface OrderRepository extends JpaRepository<Order, Long> {
    Page<Order> findByCustomerEmail(@Param("email") String email, Pageable pageable);
}
