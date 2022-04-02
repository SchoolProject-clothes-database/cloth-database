package se.iths.clothdatabase.exception;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import se.iths.clothdatabase.entity.PaymentEntity;
import se.iths.clothdatabase.entity.ProductEntity;
import se.iths.clothdatabase.entity.UserEntity;
import se.iths.clothdatabase.repository.ProductRepository;
import se.iths.clothdatabase.repository.UserRepository;
import se.iths.clothdatabase.service.UserService;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
public class CartExceptionsTest {


    @Autowired
    UserRepository userRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    UserService userService;

    @Test
    void notEnoughMoneyExceptionTest(){
        UserEntity userEntity = new UserEntity("username","pass");
        PaymentEntity paymentEntity = new PaymentEntity(20);
        ProductEntity productEntity = new ProductEntity("socks",40,2);
        userEntity.addToCart(productEntity);
        userEntity.addPaymentOption(paymentEntity);
        userRepository.save(userEntity);

        assertThatThrownBy(() -> userService.checkOut(userEntity.getId())).isInstanceOf(NotEnoughMoneyException.class);

    }




}
