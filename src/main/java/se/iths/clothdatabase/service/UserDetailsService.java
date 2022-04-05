package se.iths.clothdatabase.service;

import org.springframework.stereotype.Service;
import se.iths.clothdatabase.entity.AddressEntity;
import se.iths.clothdatabase.entity.UserDetailsEntity;
import se.iths.clothdatabase.repository.AddressRepository;
import se.iths.clothdatabase.repository.UserDetailsRepository;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class UserDetailsService {

    private final UserDetailsRepository userDetailsRepository;
    private final AddressRepository addressRepository;

    public UserDetailsService(UserDetailsRepository userDetailsRepository, AddressRepository addressRepository) {
        this.userDetailsRepository = userDetailsRepository;
        this.addressRepository = addressRepository;
    }

    public UserDetailsEntity createUserDetail(UserDetailsEntity userDetailsEntity, Long addressId) {
        AddressEntity addressEntity = addressRepository.findById(addressId).orElseThrow(EntityNotFoundException::new);
        userDetailsEntity.addAddress(addressEntity);
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
        UserDetailsEntity foundUserDetails = userDetailsRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        foundUserDetails.setFirstName(userDetailsEntity.getFirstName());
        foundUserDetails.setLastName(userDetailsEntity.getLastName());
        foundUserDetails.setAddress(userDetailsEntity.getAddress());
        foundUserDetails.setEmail(userDetailsEntity.getEmail());

        return userDetailsRepository.save(userDetailsEntity);
    }

}
