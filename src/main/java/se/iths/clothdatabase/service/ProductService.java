package se.iths.clothdatabase.service;

import org.springframework.stereotype.Service;
import se.iths.clothdatabase.entity.ProductEntity;
import se.iths.clothdatabase.repository.ProductRepository;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class ProductService {

    ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductEntity createProduct(ProductEntity productEntity) {
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
