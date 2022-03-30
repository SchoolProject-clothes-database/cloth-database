package se.iths.clothdatabase.user;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import se.iths.clothdatabase.entity.PaymentEntity;
import se.iths.clothdatabase.entity.UserEntity;
import se.iths.clothdatabase.repository.UserRepository;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
public class UserTests {

    @Autowired
    UserRepository userRepository;

    @Test
    void paymentOptionAddsCorrectly(){
        UserEntity userEntity = new UserEntity("sempron","pass");
        PaymentEntity paymentEntity = new PaymentEntity(200);

        userEntity.addPaymentOption(paymentEntity);

        assertThat(userEntity.getPaymentEntity().getAmount()).isEqualTo(200);
        assertThat(paymentEntity.getUser().getUsername()).isEqualTo("sempron");


    }

}
