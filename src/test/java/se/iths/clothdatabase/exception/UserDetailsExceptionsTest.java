package se.iths.clothdatabase.exception;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import se.iths.clothdatabase.entity.AddressEntity;
import se.iths.clothdatabase.entity.UserDetailsEntity;
import se.iths.clothdatabase.exception.userDetails.InvalidEmailException;
import se.iths.clothdatabase.exception.userDetails.YoungerThan15Exception;
import se.iths.clothdatabase.repository.AddressRepository;
import se.iths.clothdatabase.repository.UserDetailsRepository;
import se.iths.clothdatabase.service.UserDetailsService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
public class UserDetailsExceptionsTest {

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    UserDetailsRepository userDetailsRepository;

    @Autowired
    AddressRepository addressRepository;

    @Test
    void shouldThrowIfUserIsYoungerThan15(){
        AddressEntity address = new AddressEntity("street",12345,20,"country","province","city","123123123");
        UserDetailsEntity userDetailsEntity = new UserDetailsEntity("firstName","lastName",12,"email@email.se");
        userDetailsRepository.save(userDetailsEntity);
        addressRepository.save(address);

        assertThatThrownBy(() -> userDetailsService.createUserDetail(userDetailsEntity, address.getId()))
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
        AddressEntity address = new AddressEntity("street",12345,20,"country","province","city","123123123");
        UserDetailsEntity userDetailsEntity = new UserDetailsEntity("firstName","lastName",16,"@email.se");
        userDetailsRepository.save(userDetailsEntity);
        addressRepository.save(address);

        assertThatThrownBy(() -> userDetailsService.createUserDetail(userDetailsEntity, address.getId()))
                .isInstanceOf(InvalidEmailException.class);
    }



}
