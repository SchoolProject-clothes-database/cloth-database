package se.iths.clothdatabase.service;

import org.springframework.stereotype.Service;
import se.iths.clothdatabase.entity.AddressEntity;
import se.iths.clothdatabase.repository.AddressRepository;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class AddressService {

    AddressRepository addressRepository;

    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public AddressEntity createAddress(AddressEntity addressEntity) {
        return addressRepository.save(addressEntity);
    }

    public void deleteAddress(Long id) {
        AddressEntity foundAddress = addressRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        addressRepository.deleteById(foundAddress.getId());
    }

    public Optional<AddressEntity> findAddressById(Long id) {
        return addressRepository.findById(id);
    }

    public Iterable<AddressEntity> findAllAddress() {
        return addressRepository.findAll();
    }

    public AddressEntity updateAddress(Long id, AddressEntity addressEntity) {
        AddressEntity foundAddress = addressRepository.findById(id).orElseThrow();
        foundAddress.setStreet(addressEntity.getStreet());
        foundAddress.setZipCode(addressEntity.getZipCode());
        foundAddress.setHouseNumber(addressEntity.getHouseNumber());
        foundAddress.setCountry(addressEntity.getCountry());
        foundAddress.setProvince(addressEntity.getProvince());
        foundAddress.setCity(addressEntity.getCity());
        foundAddress.setPhoneNumber(addressEntity.getPhoneNumber());
        return addressRepository.save(addressEntity);
    }
}
