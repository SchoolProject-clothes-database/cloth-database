package se.iths.clothdatabase.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.iths.clothdatabase.entity.UserDetailsEntity;
import se.iths.clothdatabase.service.UserDetailsService;

import java.util.Optional;

@RestController
@RequestMapping("userDetails")
public class UserDetailsController {

    UserDetailsService userDetailsService;

    public UserDetailsController(UserDetailsService customerService) {
        this.userDetailsService = customerService;
    }

    @PostMapping("/addUserDetails")
    public ResponseEntity<UserDetailsEntity> createUserDetails(@RequestBody UserDetailsEntity userDetails) {
        UserDetailsEntity createdUserDetails = userDetailsService.createUserDetail(userDetails);
        return new ResponseEntity<>(createdUserDetails, HttpStatus.CREATED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteUserDetails(@PathVariable Long id) {
        userDetailsService.deleteUserDetail(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Optional<UserDetailsEntity>> findUserDetailsById(@PathVariable Long id) {
        Optional<UserDetailsEntity> foundUserDetails = userDetailsService.findUserDetailById(id);
        return new ResponseEntity<>(foundUserDetails, HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<Iterable<UserDetailsEntity>> findAllUserDetails() {
        Iterable<UserDetailsEntity> allUserDetails = userDetailsService.findAllUserDetails();
        return new ResponseEntity<>(allUserDetails, HttpStatus.OK);
    }

    @PatchMapping("update/{id}")
    public ResponseEntity<UserDetailsEntity> updateUserDetails(@PathVariable Long id, @RequestBody UserDetailsEntity userDetailsEntity) {
        return new ResponseEntity<>(userDetailsService.updateUserDetails(id, userDetailsEntity), HttpStatus.OK);
    }

}
