package se.iths.clothdatabase.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Entity
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = true)
    private Long id;
    private String username;
    private String password;

    @OneToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "userDetail_id")
    private UserDetailsEntity userDetail;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "payment_entity_id")
    private PaymentEntity paymentEntity;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "cart",
            joinColumns = @JoinColumn(name = "user_entity_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "product_entities_id", referencedColumnName = "id"))
    private List<ProductEntity> productEntities = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "userRoles",
            joinColumns = @JoinColumn(name = "role"),
            inverseJoinColumns = @JoinColumn(name = "user"))
    private Set<RoleEntity> roles = new LinkedHashSet<>();


    public void addToCart(ProductEntity productEntity){
        productEntities.add(productEntity);
        productEntity.getUserEntities().add(this);
    }

    public void addPaymentOption(PaymentEntity paymentEntity){
        setPaymentEntity(paymentEntity);
        paymentEntity.setUser(this);
    }


    public void addRoles(RoleEntity role){
        roles.add(role);
        role.getUser().add(this);
    }

    public void addDetails(UserDetailsEntity userDetailsEntity){
        setUserDetail(userDetailsEntity);
        userDetailsEntity.setUser(this);
    }

    public UserEntity(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public UserEntity() {
    }

    @JsonIgnore
    public Set<RoleEntity> getRoles() {
        return roles;
    }

    public void setRoles(Set<RoleEntity> roles) {
        this.roles = roles;
    }

    @JsonIgnore
    public List<ProductEntity> getProductEntities() {
        return productEntities;
    }

    public void setProductEntities(List<ProductEntity> productEntities) {
        this.productEntities = productEntities;
    }


    @JsonIgnore
    public PaymentEntity getPaymentEntity() {
        return paymentEntity;
    }

    public void setPaymentEntity(PaymentEntity paymentEntity) {
        this.paymentEntity = paymentEntity;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String userName) {
        this.username = userName;
    }

    @JsonIgnore
    public UserDetailsEntity getUserDetail() {
        return userDetail;
    }

    public void setUserDetail(UserDetailsEntity userDetail) {
        this.userDetail = userDetail;
    }

}
