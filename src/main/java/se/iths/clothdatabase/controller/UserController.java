package se.iths.clothdatabase.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.iths.clothdatabase.entity.UserEntity;
import se.iths.clothdatabase.service.UserService;

import java.util.Optional;

@RestController
@RequestMapping("user")
public class UserController {

    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping()
    public ResponseEntity<UserEntity> createUser(@RequestBody UserEntity user) {
        UserEntity createdUser = userService.createUser(user);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("{id}")
    public ResponseEntity<Optional<UserEntity>> findUserById(@PathVariable Long id) {
        Optional<UserEntity> foundUser = userService.findUserById(id);
        return new ResponseEntity<>(foundUser, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<Iterable<UserEntity>> findAllUser() {
        Iterable<UserEntity> allUser = userService.findAllUsers();
        return new ResponseEntity<>(allUser, HttpStatus.OK);
    }

    @PatchMapping("{id}")
    public ResponseEntity<UserEntity> updateUser(@PathVariable Long id, @RequestBody UserEntity userEntity) {
        return new ResponseEntity<>(userService.updateUser(id, userEntity), HttpStatus.OK);
    }

}
