package se.iths.clothdatabase.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.iths.clothdatabase.entity.UserEntity;
import se.iths.clothdatabase.exception.NotEnoughMoneyException;
import se.iths.clothdatabase.service.UserService;

import java.util.Optional;

@RestController
@RequestMapping("users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("signup")
    public ResponseEntity<UserEntity> createUser(@RequestBody UserEntity user) {
        UserEntity createdUser = userService.createUser(user);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @PatchMapping("addToCart/{userId}/{productId}")
    public ResponseEntity<Void> addToCart(@PathVariable Long userId, @PathVariable Long productId){
        userService.addToCart(userId, productId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("addPayment/{userId}/{paymentId}")
    public ResponseEntity<Void> addPayment(@PathVariable Long userId, @PathVariable Long paymentId){
        userService.addToPaymentOption(userId, paymentId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("checkout/{userId}")
    public ResponseEntity<Void> checkOut(@PathVariable Long userId) throws NotEnoughMoneyException {
        userService.checkOut(userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("find/{id}")
    public ResponseEntity<Optional<UserEntity>> findUserById(@PathVariable Long id) {
        Optional<UserEntity> foundUser = userService.findUserById(id);
        return new ResponseEntity<>(foundUser, HttpStatus.OK);
    }

    @GetMapping("findAll")
    public ResponseEntity<Iterable<UserEntity>> findAllUser() {
        Iterable<UserEntity> allUser = userService.findAllUsers();
        return new ResponseEntity<>(allUser, HttpStatus.OK);
    }

    @PatchMapping("update/{id}")
    public ResponseEntity<UserEntity> updateUser(@PathVariable Long id, @RequestBody UserEntity userEntity) {
        return new ResponseEntity<>(userService.updateUser(id, userEntity), HttpStatus.OK);
    }

}
