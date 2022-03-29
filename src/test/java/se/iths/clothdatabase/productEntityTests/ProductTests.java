package se.iths.clothdatabase.productEntityTests;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import se.iths.clothdatabase.entity.CategoryEntity;
import se.iths.clothdatabase.entity.ProductEntity;
import se.iths.clothdatabase.repository.ProductRepository;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
public class ProductTests {

    @Autowired
    ProductRepository productRepository;

    @Test
    void productsAddsCorrectlyToCategories(){
        ProductEntity productEntity = new ProductEntity("socks",20,2);
        CategoryEntity categoryEntity = new CategoryEntity("sock","cloth");
        productEntity.addCategory(categoryEntity);

        assertThat(productEntity.getCategory().getCategoryName()).isEqualTo("sock");
        assertThat(categoryEntity.getProducts().get(0).getProductName()).isEqualTo("socks");
    }

}
