package se.iths.clothdatabase.exception;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import se.iths.clothdatabase.entity.UserEntity;
import se.iths.clothdatabase.service.UserService;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
public class UserExceptionsTest {
    @Autowired
    UserService userService;


    @Test
    void usernameLessThanThreeCharactersTest(){
        assertThatThrownBy(() -> userService.createUser(new UserEntity("se","sess"))).isInstanceOf(LessThanThreeCharacterException.class);
    }

    @Test
    void passwordLessThanThreeCharactersTest(){
        assertThatThrownBy(() -> userService.createUser(new UserEntity("sess","se"))).isInstanceOf(LessThanThreeCharacterException.class);
    }
}
