package com.example.notebook.commercebackend.Controller;


import com.example.notebook.commercebackend.Dao.ProductRepository;
import com.example.notebook.commercebackend.Service.ProductService;
import com.example.notebook.commercebackend.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/admin/products")
@CrossOrigin(origins = "http://localhost:4200")
public class ProductAdminController {
    private final ProductRepository productRepository;
    private final ProductService productService;


    @Autowired
    public ProductAdminController(ProductRepository productRepository, ProductService productService) {
        this.productRepository = productRepository;
        this.productService = productService;
    }

    //    ------------------Product-----------------
    @PostMapping
    public ResponseEntity<Product> saveProductCategory(@RequestBody Product product){
        try{
            Product saveCategory = productRepository.save(product);
            return ResponseEntity.status(HttpStatus.CREATED).body(saveCategory);
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProductCategory(@PathVariable("id")Long id,@RequestBody Product productDetails){
        Product updateProductCategoryName = productService.updateProductNameService(id,productDetails.getName());
        return ResponseEntity.ok(updateProductCategoryName);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable("id")Long id){
        Product product = productService.findProductByIdService(id);
        if(product == null){
            return ResponseEntity.notFound().build();
        }
        productService.deleteProductByIdService(id);
        return ResponseEntity.noContent().build();
    }
}

