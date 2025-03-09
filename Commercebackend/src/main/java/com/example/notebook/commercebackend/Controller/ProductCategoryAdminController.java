package com.example.notebook.commercebackend.Controller;


import com.example.notebook.commercebackend.Dao.ProductCategoryRepository;
import com.example.notebook.commercebackend.Service.ProductCategoryService;
import com.example.notebook.commercebackend.entity.ProductCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/admin/products-category")
@CrossOrigin(origins = "http://localhost:4200")
public class ProductCategoryAdminController {
    private final ProductCategoryRepository productCategoryRepository;
    private final ProductCategoryService productCategoryService;


    @Autowired
    public ProductCategoryAdminController(ProductCategoryRepository productCategoryRepository, ProductCategoryService productCategoryService) {
        this.productCategoryRepository = productCategoryRepository;
        this.productCategoryService = productCategoryService;
    }

//    ------------------productCategory-----------------
    @PostMapping
    public ResponseEntity<ProductCategory> saveProductCategory(@RequestBody ProductCategory productCategory){
        try{
            ProductCategory saveCategory = productCategoryRepository.save(productCategory);
            return ResponseEntity.status(HttpStatus.CREATED).body(saveCategory);
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductCategory> updateProductCategory(@PathVariable("id")Long id,@RequestBody ProductCategory productCategoryDetails){
        ProductCategory updateProductCategoryName = productCategoryService.updateProductCategoryNameService(id,productCategoryDetails.getCategoryName());
        return ResponseEntity.ok(updateProductCategoryName);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProductCategory(@PathVariable("id")Long id){
        ProductCategory category = productCategoryService.findCategoryById(id);
        if(category == null){
            return ResponseEntity.notFound().build();
        }
        productCategoryService.deleteCategoryById(id);
        return ResponseEntity.noContent().build();
    }
}
