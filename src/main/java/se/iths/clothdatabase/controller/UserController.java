package se.iths.clothdatabase.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import se.iths.clothdatabase.service.UserService;

@RestController
@RequestMapping("user")
public class UserController {

    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
}
