package com.example.notebook.commercebackend.Service;
import com.example.notebook.commercebackend.Dao.ProductRepository;
import com.example.notebook.commercebackend.entity.Product;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import java.util.Optional;

@Service
public class ProductService {
    final private ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product updateProductNameService(Long id, String name){
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product not found"));

        return productRepository.save(product);
    }

    public void deleteProductByIdService(Long id){
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product not found"));
        productRepository.delete(product);
    }

    public Optional<Product> findProductByIdService(Long id){
        return productRepository.findById(id);
    }

    public Page<Product> findByCategoryIdService (Long categoryId, Pageable pageable) {
        return productRepository.findByCategoryId(categoryId, pageable);
    }


    public Page<Product> findByNameService (String productName, Pageable pageable) {
        return productRepository.findByName(productName, pageable);
    }

    public Page<Product> findByFuzzyNameService (String productName, Pageable pageable) {
        return productRepository.findByFuzzyName(productName, pageable);
    }
}
