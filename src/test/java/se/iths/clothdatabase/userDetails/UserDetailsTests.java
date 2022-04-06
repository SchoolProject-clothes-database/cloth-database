package se.iths.clothdatabase.userDetails;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import se.iths.clothdatabase.entity.AddressEntity;
import se.iths.clothdatabase.entity.UserDetailsEntity;
import se.iths.clothdatabase.repository.UserDetailsRepository;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
public class UserDetailsTests {

    @Autowired
    UserDetailsRepository userDetailsRepository;


    @Test
    void userDetailEntityShouldBeConnectedToAnAddress(){
        AddressEntity address = new AddressEntity("street",12345,20,"country","province","city","123123123");
        UserDetailsEntity userDetailsEntity = new UserDetailsEntity("firstName","lastName",12,"email@email.se");

        userDetailsEntity.addAddress(address);

        assertThat(address.getUserDetails().size()).isEqualTo(1);
    }

}
