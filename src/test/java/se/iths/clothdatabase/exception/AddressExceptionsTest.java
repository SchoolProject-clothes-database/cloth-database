package se.iths.clothdatabase.exception;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import se.iths.clothdatabase.entity.AddressEntity;
import se.iths.clothdatabase.exception.address.IncorrectZIPCodeException;
import se.iths.clothdatabase.service.AddressService;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
public class AddressExceptionsTest {

    @Autowired
    AddressService addressService;


    @Test
    void exceptionShouldThrowIfZipCodeLengthIsShorterThan5Digits(){

        assertThatThrownBy(() -> addressService.createAddress(new AddressEntity("street",222,22,"Sweden","province","city","2020")) )
                .isInstanceOf(IncorrectZIPCodeException.class);
    }

    @Test
    void exceptionShouldThrowIfZipCodeLengthIsLongerThan5Digits(){

        assertThatThrownBy(() -> addressService.createAddress(new AddressEntity("street",123456,22,"Sweden","province","city","2020")) )
                .isInstanceOf(IncorrectZIPCodeException.class);
    }
}
