package se.iths.clothdatabase;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import se.iths.clothdatabase.entity.AddressEntity;
import se.iths.clothdatabase.entity.RoleEntity;
import se.iths.clothdatabase.entity.UserDetailsEntity;
import se.iths.clothdatabase.entity.UserEntity;
import se.iths.clothdatabase.repository.AddressRepository;
import se.iths.clothdatabase.repository.RoleRepository;
import se.iths.clothdatabase.repository.UserDetailsRepository;
import se.iths.clothdatabase.repository.UserRepository;

@SpringBootApplication
public class ClothDatabaseApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClothDatabaseApplication.class, args);
    }

    @Bean
    public CommandLineRunner setUpRole(RoleRepository roleRepository){
        return (args) -> {
            roleRepository.save(new RoleEntity("ROLE_ADMIN"));
            roleRepository.save(new RoleEntity("ROLE_USER"));



        };
    }

    @Bean
    public CommandLineRunner setUpAddress(AddressRepository addressRepository, UserDetailsRepository userDetailsRepository, UserRepository userRepository, RoleRepository roleRepository){
        return (args) -> {
            AddressEntity address1 = new AddressEntity("city",2223,32,"country","province","street","phonenumber");
            UserDetailsEntity userDetailsEntity1 = new UserDetailsEntity("firstname", "lastname", "email" );
            UserEntity userEntity = new UserEntity("username","password");
            address1.addAddress(userDetailsEntity1);
            userEntity.addDetails(userDetailsEntity1);
            addressRepository.save(address1);
        };
    }


}
