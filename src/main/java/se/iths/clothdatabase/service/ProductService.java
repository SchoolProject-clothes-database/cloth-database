package se.iths.clothdatabase.service;

import org.springframework.stereotype.Service;
import se.iths.clothdatabase.entity.CategoryEntity;
import se.iths.clothdatabase.entity.ProductEntity;
import se.iths.clothdatabase.repository.CategoryRepository;
import se.iths.clothdatabase.repository.ProductRepository;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class ProductService {

    ProductRepository productRepository;
    CategoryRepository categoryRepository;

    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    public ProductEntity createProduct(ProductEntity productEntity, Long categoryId) {
        CategoryEntity categoryToAdd = categoryRepository.findById(categoryId).orElseThrow();
        productEntity.addCategory(categoryToAdd);
        return productRepository.save(productEntity);
    }

    public void deleteProduct(Long id) {
        ProductEntity foundProduct = productRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        productRepository.deleteById(foundProduct.getId());
    }

    public Optional<ProductEntity> findProductById(Long id) {
        return productRepository.findById(id);
    }

    public Iterable<ProductEntity> findAllProducts() {
        return productRepository.findAll();
    }

    public ProductEntity updateProduct(Long id, ProductEntity productEntity) {
        ProductEntity foundProduct = productRepository.findById(id).orElseThrow();
        foundProduct.setProductName(productEntity.getProductName());
        foundProduct.setPrice(productEntity.getPrice());
        foundProduct.setQuantity(productEntity.getQuantity());

        return productRepository.save(productEntity);
    }

}
