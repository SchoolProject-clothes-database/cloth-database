package se.iths.clothdatabase.productEntityTests;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import se.iths.clothdatabase.entity.CategoryEntity;
import se.iths.clothdatabase.entity.ProductEntity;
import se.iths.clothdatabase.entity.UserEntity;
import se.iths.clothdatabase.repository.ProductRepository;
import se.iths.clothdatabase.repository.UserRepository;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
public class ProductTests {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    UserRepository userRepository;

    @Test
    void productsAddsCorrectlyToCategories(){
        ProductEntity productEntity = new ProductEntity("socks",20,2);
        CategoryEntity categoryEntity = new CategoryEntity("sock","cloth");
        productEntity.addCategory(categoryEntity);

        assertThat(productEntity.getCategory().getCategoryName()).isEqualTo("sock");
        assertThat(categoryEntity.getProducts().get(0).getProductName()).isEqualTo("socks");
    }

    @Test
    void showsCorrectTotalSumForEveryUser(){
        UserEntity user = new UserEntity("semponr","pass");
        UserEntity user2 = new UserEntity("semponr","pass");
        ProductEntity productEntity = new ProductEntity("socks",20,2);
        ProductEntity productEntity2 = new ProductEntity("socks",20,2);
        ProductEntity productEntity3 = new ProductEntity("socks",20,2);
        user.addToCart(productEntity);
        user.addToCart(productEntity2);
        user2.addToCart(productEntity3);

        userRepository.save(user);
        userRepository.save(user2);
        assertThat(productRepository.totalSum(user.getId()).stream().mapToDouble(Double::doubleValue).sum()).isEqualTo(40);
        assertThat(productRepository.totalSum(user2.getId()).stream().mapToDouble(Double::doubleValue).sum()).isEqualTo(20);
    }

}
