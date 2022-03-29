package se.iths.clothdatabase.cart;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import se.iths.clothdatabase.entity.ProductEntity;
import se.iths.clothdatabase.entity.UserEntity;
import se.iths.clothdatabase.repository.ProductRepository;
import se.iths.clothdatabase.repository.UserRepository;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class CartJpaTests {

    @Autowired
    private UserRepository userRepository;

    @Test
    void productsAddsToCartCorrectly(){
        UserEntity userEntity = new UserEntity("username","pass");
        ProductEntity productEntity = new ProductEntity("socks",200,2);
        userEntity.addToCart(productEntity);

        userRepository.save(userEntity);

        assertThat(userEntity.getProductEntities().get(0).getProductName()).isEqualTo("socks");
        assertThat(productEntity.getUserEntities().get(0).getUsername()).isEqualTo("username");
    }


}
