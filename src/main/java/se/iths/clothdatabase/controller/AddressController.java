package se.iths.clothdatabase.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.iths.clothdatabase.entity.AddressEntity;
import se.iths.clothdatabase.service.AddressService;

import java.util.Optional;

@RestController
@RequestMapping("address")
public class AddressController {

    AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @PostMapping()
    public ResponseEntity<AddressEntity> createAddress(@RequestBody AddressEntity address) {
        AddressEntity createdAddress = addressService.createAddress(address);
        return new ResponseEntity<>(createdAddress, HttpStatus.CREATED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteAddress(@PathVariable Long id) {
        addressService.deleteAddress(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("{id}")
    public ResponseEntity<Optional<AddressEntity>> findAddressById(@PathVariable Long id) {
        Optional<AddressEntity> foundAddress = addressService.findAddressById(id);
        return new ResponseEntity<>(foundAddress, HttpStatus.OK);
    }

    @GetMapping("/findAll")
    public ResponseEntity<Iterable<AddressEntity>> findAllAddress() {
        Iterable<AddressEntity> allAddress = addressService.findAllAddress();
        return new ResponseEntity<>(allAddress, HttpStatus.OK);
    }

    @PatchMapping("update/{id}")
    public ResponseEntity<AddressEntity> updateAddress(@PathVariable Long id, @RequestBody AddressEntity addressEntity) {
        return new ResponseEntity<>(addressService.updateAddress(id, addressEntity), HttpStatus.OK);
    }
}
