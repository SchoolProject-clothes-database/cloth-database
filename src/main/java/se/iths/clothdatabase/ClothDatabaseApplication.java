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

import javax.transaction.Transactional;


@SpringBootApplication
public class ClothDatabaseApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClothDatabaseApplication.class, args);
    }

   @Bean
    public CommandLineRunner setUpRole(RoleRepository roleRepository){
        return (args) -> {
            if( roleRepository.findByRole("ROLE_ADMIN") == null )
                roleRepository.save(new RoleEntity("ROLE_ADMIN"));
            if( roleRepository.findByRole("ROLE_USER") == null )
                roleRepository.save(new RoleEntity("ROLE_USER"));
        };
    }



}
