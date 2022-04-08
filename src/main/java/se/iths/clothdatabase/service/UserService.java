package se.iths.clothdatabase.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import se.iths.clothdatabase.entity.*;
import se.iths.clothdatabase.jms.MessageObject;
import se.iths.clothdatabase.jms.Sender;
import se.iths.clothdatabase.repository.*;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class UserService {

    private final Sender sender;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final ProductRepository productRepository;
    private final PaymentRepository paymentRepository;
    private final UserDetailsRepository userDetailsRepository;


    public UserService(Sender sender, UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, ProductRepository productRepository, PaymentRepository paymentRepository, UserDetailsRepository userDetailsRepository) {
        this.sender = sender;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.productRepository = productRepository;
        this.paymentRepository = paymentRepository;
        this.userDetailsRepository = userDetailsRepository;
    }

    public UserEntity createUser(UserEntity userEntity) {
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        RoleEntity roleToAdd = roleRepository.findByRole("ROLE_USER");
        userEntity.addRoles(roleToAdd);
        return userRepository.save(userEntity);
    }

    public void addToCart(Long userId, Long productId){
        ProductEntity productToAdd = productRepository.findById(productId).orElseThrow(EntityNotFoundException::new);
        UserEntity user = userRepository.findById(userId).orElseThrow(EntityNotFoundException::new);
        user.addToCart(productToAdd);
        userRepository.save(user);
    }

    public void addPaymentOption(Long userId, Long paymentId){
        PaymentEntity paymentEntity = paymentRepository.findById(paymentId).orElseThrow(EntityNotFoundException::new);
        UserEntity user = userRepository.findById(userId).orElseThrow(EntityNotFoundException::new);
        user.addPaymentOption(paymentEntity);
        userRepository.save(user);
    }

    public void addDetails(Long userId, Long userDetailsId){
        UserDetailsEntity userDetailsEntity = userDetailsRepository.findById(userDetailsId).orElseThrow(EntityNotFoundException::new);
        UserEntity user = userRepository.findById(userId).orElseThrow(EntityNotFoundException::new);
        user.addDetails(userDetailsEntity);
        userRepository.save(user);
    }

    @Transactional
    public void checkOut(Long userId){
        UserEntity user = userRepository.findById(userId).orElseThrow();

        double balance = user.getPaymentEntity().getAmount();
        balance -= productRepository.totalSum(user.getId()).stream().mapToDouble(value -> value).sum();
        user.getPaymentEntity().setAmount(balance);
        productRepository.purchasedProduct(user.getId());
        userRepository.save(user);
        sender.sendMessage("Order Confirmation");
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
        UserEntity foundUser = userRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        foundUser.setUsername(userEntity.getUsername());
        return userRepository.save(userEntity);
    }
}
