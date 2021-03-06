package se.iths.clothdatabase.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import se.iths.clothdatabase.entity.*;
import se.iths.clothdatabase.rabbitmq.MessageSender;
import se.iths.clothdatabase.repository.*;
import se.iths.clothdatabase.entity.PaymentEntity;
import se.iths.clothdatabase.entity.ProductEntity;
import se.iths.clothdatabase.entity.RoleEntity;
import se.iths.clothdatabase.entity.UserEntity;
import se.iths.clothdatabase.exception.user.LessThanThreeCharacterException;
import se.iths.clothdatabase.exception.user.NotEnoughMoneyException;
import se.iths.clothdatabase.exception.product.ProductIsNotInStockException;
import se.iths.clothdatabase.repository.PaymentRepository;
import se.iths.clothdatabase.repository.ProductRepository;
import se.iths.clothdatabase.repository.RoleRepository;
import se.iths.clothdatabase.repository.UserRepository;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class UserService {

    private final MessageSender messageSender;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final ProductRepository productRepository;
    private final PaymentRepository paymentRepository;
    private final UserDetailsRepository userDetailsRepository;


    public UserService(MessageSender messageSender, UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, ProductRepository productRepository, PaymentRepository paymentRepository, UserDetailsRepository userDetailsRepository) {
        this.messageSender = messageSender;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.productRepository = productRepository;
        this.paymentRepository = paymentRepository;
        this.userDetailsRepository = userDetailsRepository;
    }

    public UserEntity createUser(UserEntity userEntity) throws LessThanThreeCharacterException {
        if(userEntity.getUsername().length() < 3 || userEntity.getPassword().length() < 3)
            throw new LessThanThreeCharacterException("Needs to be longer than 3 characters");

        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        RoleEntity roleToAdd = roleRepository.findByRole("ROLE_USER");
        userEntity.addRoles(roleToAdd);
        return userRepository.save(userEntity);
    }

    public void addToCart(Long userId, Long productId) throws ProductIsNotInStockException{
        ProductEntity productToAdd = productRepository.findById(productId).orElseThrow(() -> new ProductIsNotInStockException("Out of stock"));
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
    public void checkOut(Long userId) throws NotEnoughMoneyException {
        UserEntity user = userRepository.findById(userId).orElseThrow();

        double balance = user.getPaymentEntity().getAmount();
        balance -= productRepository.totalSum(user.getId()).stream().mapToDouble(value -> value).sum();
        user.getPaymentEntity().setAmount(balance);
        productRepository.purchasedProduct(user.getId());
        userRepository.save(user);
        messageSender.sendMessage("Order Confirmation");
        if(user.getPaymentEntity().getAmount()< 0)
           throw new NotEnoughMoneyException("Not enough money");
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
