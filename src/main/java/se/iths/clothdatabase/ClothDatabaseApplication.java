package se.iths.clothdatabase;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import se.iths.clothdatabase.entity.RoleEntity;
import se.iths.clothdatabase.repository.RoleRepository;

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


}
