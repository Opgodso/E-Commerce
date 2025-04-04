package com.example.notebook.commercebackend.Controller;


import com.example.notebook.commercebackend.Dao.ProductRepository;
import com.example.notebook.commercebackend.Service.ProductService;
import com.example.notebook.commercebackend.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

//Public interface

@RestController
@RequestMapping("/products")
@CrossOrigin(origins = "http://localhost:4200")
public class ProductController {

    private final ProductRepository productRepository;
    private final ProductService productService;


    @Autowired
    public ProductController(ProductRepository productRepository, ProductService productService) {
        this.productRepository = productRepository;
        this.productService = productService;
    }

//    ------------------product-----------------

    @GetMapping
    public ResponseEntity<List<Product>> getAllProduct(){
        return ResponseEntity.ok(productRepository.findAll());
    }

//    @GetMapping("/products/all")
//    public ResponseEntity<Page<Product>> getProductPage(Pageable pageable){
//        return ResponseEntity.ok(productService.findAllProductService(pageable));
//    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProductCategoryById(@PathVariable("id") Long id) {
        Optional<Product> product = productService.findProductByIdService(id);
        return product.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/search/findByCategoryId")
    public ResponseEntity<Page<Product>> findByCategoryId(@RequestParam Long categoryId, Pageable pageable) {
        Page<Product> products = productService.findByCategoryIdService(categoryId, pageable);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/search/findByFuzzyName")
    public ResponseEntity<Page<Product>> findByFuzzyName(@RequestParam String Name,@RequestParam(required = false, defaultValue = "false") boolean exactMatch,Pageable pageable) {
        Page<Product> products;
        if(exactMatch){
            products = productService.findByNameService(Name,pageable);
        } else {
            products = productService.findByFuzzyNameService(Name,pageable);
        }
        return ResponseEntity.ok(products);
    }

}
