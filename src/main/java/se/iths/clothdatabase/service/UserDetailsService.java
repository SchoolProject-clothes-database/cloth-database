package se.iths.clothdatabase.service;

import org.springframework.stereotype.Service;
import se.iths.clothdatabase.entity.UserDetailsEntity;
import se.iths.clothdatabase.exception.userDetails.InvalidEmailException;
import se.iths.clothdatabase.exception.userDetails.YoungerThan15Exception;
import se.iths.clothdatabase.repository.UserDetailsRepository;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class UserDetailsService {

    UserDetailsRepository userDetailsRepository;

    public UserDetailsService(UserDetailsRepository userDetailsRepository) {
        this.userDetailsRepository = userDetailsRepository;
    }

    public UserDetailsEntity createUserDetail(UserDetailsEntity userDetailsEntity) throws YoungerThan15Exception, InvalidEmailException {
        if(userDetailsEntity.getAge() < 15)
            throw new YoungerThan15Exception("You need to be older than 15");
        if(!userDetailsEntity.emailCheck(userDetailsEntity.getEmail()))
            throw new InvalidEmailException("Incorrect Format");

        return userDetailsRepository.save(userDetailsEntity);
    }

    public void deleteUserDetail(Long id) {
        UserDetailsEntity foundUserDetail = userDetailsRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        userDetailsRepository.deleteById(foundUserDetail.getId());
    }

    public Optional<UserDetailsEntity> findUserDetailById(Long id) {
        return userDetailsRepository.findById(id);
    }

    public Iterable<UserDetailsEntity> findAllUserDetails() {
        return userDetailsRepository.findAll();
    }

    public UserDetailsEntity updateUserDetails(Long id, UserDetailsEntity userDetailsEntity) {
        UserDetailsEntity foundUserDetails = userDetailsRepository.findById(id).orElseThrow();
        foundUserDetails.setFirstName(userDetailsEntity.getFirstName());
        foundUserDetails.setLastName(userDetailsEntity.getLastName());
        foundUserDetails.setAddress(userDetailsEntity.getAddress());
        foundUserDetails.setEmail(userDetailsEntity.getEmail());

        return userDetailsRepository.save(userDetailsEntity);
    }

}
