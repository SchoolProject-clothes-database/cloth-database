package se.iths.clothdatabase.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class AddressEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String street;
    private int zipCode;
    private int houseNumber;
    private String country;
    private String province;
    private String city;
    private String phoneNumber;
    @OneToMany(mappedBy = "address", cascade = CascadeType.ALL)
    private List<UserDetailsEntity> userDetails = new ArrayList<>();

    public AddressEntity(String street, int zipCode, int houseNumber, String country, String province, String city, String phoneNumber) {
        this.street = street;
        this.zipCode = zipCode;
        this.houseNumber = houseNumber;
        this.country = country;
        this.province = province;
        this.city = city;
        this.phoneNumber = phoneNumber;
    }

    public void addAddress(UserDetailsEntity userDetailsEntity){
        userDetails.add(userDetailsEntity);
        userDetailsEntity.setAddress(this);
    }

    public AddressEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    public int getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(int houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


    @JsonIgnore
    public List<UserDetailsEntity> getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(List<UserDetailsEntity> userDetails) {
        this.userDetails = userDetails;
    }
}
