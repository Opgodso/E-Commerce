package com.example.notebook.commercebackend.Dao;

import com.example.notebook.commercebackend.entity.Product;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Page<Product> findByCategoryId(@Param("id")Long id, Pageable pageable);

    Page<Product> findByName(@Param("name")String name, Pageable pageable);

    @Query("SELECT p FROM Product p WHERE (:name IS NULL OR p.name LIKE CONCAT('%', :name, '%'))")
    Page<Product> findByFuzzyName(@Param("name") String name , Pageable pageable);
}
