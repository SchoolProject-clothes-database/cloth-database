package se.iths.clothdatabase.security;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import se.iths.clothdatabase.entity.UserEntity;
import se.iths.clothdatabase.repository.UserRepository;

@Service
public class WebShopUserService implements UserDetailsService {

    private final UserRepository userRepository;

    public WebShopUserService(UserRepository userRepository) {
        super();
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByUsername(username);
        if(userEntity == null){
            throw new UsernameNotFoundException("Can't find: " + "username");
        }

        return new WebShopUserPrincipal(userEntity);
    }
}
