package se.iths.clothdatabase.service;

import org.springframework.stereotype.Service;
import se.iths.clothdatabase.repository.UserDetailsRepository;

@Service
public class UserDetailsService {

    UserDetailsRepository customerRepository;

    public UserDetailsService(UserDetailsRepository customerRepository) {
        this.customerRepository = customerRepository;
    }
}
