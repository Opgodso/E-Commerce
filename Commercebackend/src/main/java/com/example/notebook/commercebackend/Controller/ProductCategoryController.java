package com.example.notebook.commercebackend.Controller;


import com.example.notebook.commercebackend.Dao.ProductCategoryRepository;
import com.example.notebook.commercebackend.entity.ProductCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

//Public interface

@RestController
@RequestMapping("/products-category")
@CrossOrigin(origins = "http://localhost:4200")
public class ProductCategoryController {

    private final ProductCategoryRepository productCategoryRepository;


    @Autowired
    public ProductCategoryController(ProductCategoryRepository productCategoryRepository) {
        this.productCategoryRepository = productCategoryRepository;
    }

//    ------------------productCategory-----------------
    @GetMapping
    public ResponseEntity<Map<String,Object>> getProductCategoryById() {
        List<ProductCategory> categories = productCategoryRepository.findAll();
        List<Map<String,Object>> CategoryList = categories.stream().map(category ->{
            Map<String,Object> map = new HashMap<>();
            map.put("categoryName", category.getCategoryName());
            map.put("_links",buildLinks(category.getId()));
            map.put("id", category.getId());
            return map;
        }).toList();

        Map<String,Object> response = new HashMap<>();
        response.put("productCategory",CategoryList);
        return ResponseEntity.ok(response);
    }

    private Map<String,Object> buildLinks(Long id) {
        Map<String,Object> _links = new HashMap<>();

        Map<String,String> selfLink = new HashMap<>();
        selfLink.put("href","http://localhost:8081/products-category/"+id);

        Map<String,String> productCategoryLink = new HashMap<>();
        productCategoryLink.put("href","http://localhost:8081/product-category");

        Map<String,String> productsLink = new HashMap<>();
        productsLink.put("href","http://localhost:8081/product-category/"+id+"/products");

        _links.put("self", selfLink);
        _links.put("productCategory", productCategoryLink);
        _links.put("products", productsLink);
        return _links;
    }

}
