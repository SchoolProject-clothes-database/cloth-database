package se.iths.clothdatabase.exception;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import se.iths.clothdatabase.entity.UserDetailsEntity;
import se.iths.clothdatabase.service.UserDetailsService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
public class UserDetailsExceptionsTest {

    @Autowired
    UserDetailsService userDetailsService;

    @Test
    void shouldThrowIfUserIsYoungerThan15(){
        assertThatThrownBy(() -> userDetailsService.createUserDetail(new UserDetailsEntity("Luke","fine",14,"email@email")))
                .isInstanceOf(YoungerThan15Exception.class);
    }

    @Test
    void emailCheckShouldReturnTrue(){
        UserDetailsEntity userDetailsEntity = new UserDetailsEntity();

        String email = "user@domain.com";
        assertThat(userDetailsEntity.emailCheck(email)).isTrue();
    }

    @Test
    void shouldThrowCauseInvalidEmailFormat(){
        assertThatThrownBy(() -> userDetailsService.createUserDetail(new UserDetailsEntity("Luke","fine",15,"@email.com")))
                .isInstanceOf(InvalidEmailException.class);
    }



}
