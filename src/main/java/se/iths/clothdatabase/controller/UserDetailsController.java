package se.iths.clothdatabase.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import se.iths.clothdatabase.service.UserDetailsService;

@RestController
@RequestMapping("customer")
public class UserDetailsController {

    UserDetailsService customerService;

    public UserDetailsController(UserDetailsService customerService) {
        this.customerService = customerService;
    }
}
