package se.iths.clothdatabase.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.iths.clothdatabase.entity.ProductEntity;
import se.iths.clothdatabase.service.ProductService;

import java.util.Optional;

@RestController
@RequestMapping("product")
public class ProductController {

    ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/addProduct")
    public ResponseEntity<ProductEntity> createProduct(@RequestBody ProductEntity product) {
        ProductEntity createdProduct = productService.createProduct(product);
        return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("{id}")
    public ResponseEntity<Optional<ProductEntity>> findProductById(@PathVariable Long id) {
        Optional<ProductEntity> foundProduct = productService.findProductById(id);
        return new ResponseEntity<>(foundProduct, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<Iterable<ProductEntity>> findAllProduct() {
        Iterable<ProductEntity> allProduct = productService.findAllProducts();
        return new ResponseEntity<>(allProduct, HttpStatus.OK);
    }

    @PatchMapping("{id}")
    public ResponseEntity<ProductEntity> updateProduct(@PathVariable Long id, @RequestBody ProductEntity productEntity) {
        return new ResponseEntity<>(productService.updateProduct(id, productEntity), HttpStatus.OK);
    }

}
