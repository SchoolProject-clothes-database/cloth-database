package se.iths.clothdatabase.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import se.iths.clothdatabase.entity.ProductEntity;
import se.iths.clothdatabase.entity.RoleEntity;
import se.iths.clothdatabase.entity.UserEntity;
import se.iths.clothdatabase.repository.ProductRepository;
import se.iths.clothdatabase.repository.RoleRepository;
import se.iths.clothdatabase.repository.UserRepository;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final ProductRepository productRepository;

    public UserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, ProductRepository productRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.productRepository = productRepository;
    }

    public UserEntity createUser(UserEntity userEntity) {
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        RoleEntity roleToAdd = roleRepository.findByRole("ROLE_USER");
        userEntity.addRoles(roleToAdd);
        return userRepository.save(userEntity);
    }

    public void addToCart(Long userId, Long productId){
        ProductEntity productToAdd = productRepository.findById(productId).orElseThrow();
        UserEntity user = userRepository.findById(userId).orElseThrow();
        user.addToCart(productToAdd);
        userRepository.save(user);
    }

    public void deleteUser(Long id) {
        UserEntity foundUser = userRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        userRepository.deleteById(foundUser.getId());
    }

    public Optional<UserEntity> findUserById(Long id) {
        return userRepository.findById(id);
    }

    public Iterable<UserEntity> findAllUsers() {
        return userRepository.findAll();
    }

    public UserEntity updateUser(Long id, UserEntity userEntity) {
        UserEntity foundUser = userRepository.findById(id).orElseThrow();
        foundUser.setUsername(userEntity.getUsername());
        return userRepository.save(userEntity);
    }
}
