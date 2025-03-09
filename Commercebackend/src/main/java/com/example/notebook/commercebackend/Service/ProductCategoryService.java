package com.example.notebook.commercebackend.Service;
import com.example.notebook.commercebackend.entity.ProductCategory;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import com.example.notebook.commercebackend.Dao.ProductCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class ProductCategoryService {


    final private ProductCategoryRepository productCategoryRepository;

    @Autowired
    public  ProductCategoryService(ProductCategoryRepository productCategoryRepository) {
        this.productCategoryRepository = productCategoryRepository;
    }


    //非靜態 因為會調用
    public ProductCategory updateProductCategoryNameService(Long id, String name) {
        ProductCategory productCategory = productCategoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product category not found"));

        int updateName = productCategoryRepository.updateProductCategoryName(id, name);
        if (updateName == 0) {
            throw new RuntimeException("Product category can not be updated");
        }

        productCategory.setCategoryName(name);
        return productCategory;
    }

    public void deleteCategoryById(Long id) {
        if (!productCategoryRepository.existsById(id)) {
            throw new RuntimeException("Product category not exists");
        }
        productCategoryRepository.deleteById(id);
    }


    public ProductCategory findCategoryById(Long id) {
        return productCategoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product category not found"));
    }
}
