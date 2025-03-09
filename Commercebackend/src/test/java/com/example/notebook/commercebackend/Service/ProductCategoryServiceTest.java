package com.example.notebook.commercebackend.Service;

import com.example.notebook.commercebackend.Dao.ProductCategoryRepository;
import com.example.notebook.commercebackend.Service.ProductCategoryService;
import com.example.notebook.commercebackend.entity.ProductCategory;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@Slf4j
@ExtendWith(SpringExtension.class)
public class ProductCategoryServiceTest {

    @Mock
    private ProductCategoryRepository productCategoryRepository;

    @InjectMocks
    private ProductCategoryService productCategoryService;

    private List<ProductCategory> mockCategories;
    private ProductCategory category1;
    private ProductCategory category2;

    @BeforeEach
    void setUp() {
        ProductCategory category1 = new ProductCategory();

        category1.setId(1L);
        category1.setCategoryName("Test1");

        ProductCategory category2 = new ProductCategory();
        category2.setId(2L);
        category2.setCategoryName("Test2");

        mockCategories = Arrays.asList(category1, category2);
    }

    @Test
    void testUpdateProductCategoryName() {
        Long id = 1L;
        String newName = "Update Name";

        ProductCategory category1 = new ProductCategory();
        category1.setId(id);
        category1.setCategoryName("Old Name");

        when(productCategoryRepository.existsById(id)).thenReturn(true);
        when(productCategoryRepository.updateProductCategoryName(id,newName)).thenReturn(1);
        when(productCategoryRepository.findById(id)).thenReturn(Optional.of(category1));

        ProductCategory updateCategory = productCategoryService.updateProductCategoryNameService(id,newName);

        assertNotNull(updateCategory);
        assertEquals(id, updateCategory.getId());
        assertEquals(newName, updateCategory.getCategoryName());

        verify(productCategoryRepository,times(1)).updateProductCategoryName(id,newName);
        verify(productCategoryRepository, times(1)).findById(id);
    }

    @Test
    void testFindAllProductCategory() {
        when(productCategoryRepository.findAll()).thenReturn(mockCategories);
        ProductCategory findAllCategory = productCategoryRepository.findAll().iterator().next();

        assertNotNull(findAllCategory);
        assertEquals(mockCategories.size(), productCategoryRepository.findAll().size());
    }

}
