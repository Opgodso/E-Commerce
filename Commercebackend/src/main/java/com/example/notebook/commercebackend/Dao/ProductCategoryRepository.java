package com.example.notebook.commercebackend.Dao;

import com.example.notebook.commercebackend.entity.ProductCategory;
import jakarta.transaction.Transactional;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;



@Repository
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {


    @Modifying
    @Transactional
    @Query("UPDATE ProductCategory pc SET pc.categoryName =:name WHERE pc.id =:id ")
    int updateProductCategoryName(@Param("id")Long id,@Param("name")String name);



}
