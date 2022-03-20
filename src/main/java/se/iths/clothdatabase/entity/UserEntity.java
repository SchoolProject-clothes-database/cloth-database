package se.iths.clothdatabase.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String userName;
    private String role;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<CartEntity> carts = new ArrayList<>();
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "userDetail_id", nullable = false)
    private UserDetailsEntity userDetail;
    @OneToOne(fetch = FetchType.LAZY,
            cascade =  CascadeType.ALL,
            mappedBy = "user")
    private PaymentEntity payment;

    public void addCart(CartEntity cart) {
        carts.add(cart);
        cart.setUser(this);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<CartEntity> getCarts() {
        return carts;
    }

    public void setCarts(List<CartEntity> carts) {
        this.carts = carts;
    }

    public UserDetailsEntity getUserDetail() {
        return userDetail;
    }

    public void setUserDetail(UserDetailsEntity userDetail) {
        this.userDetail = userDetail;
    }

    public PaymentEntity getPayment() {
        return payment;
    }

    public void setPayment(PaymentEntity payment) {
        this.payment = payment;
    }
}
