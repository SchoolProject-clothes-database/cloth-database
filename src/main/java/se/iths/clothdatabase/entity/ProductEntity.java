package se.iths.clothdatabase.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String productName;
    private double price;
    @ManyToOne
    private CategoryEntity category;

    @ManyToMany(mappedBy = "productEntities")
    private List<UserEntity> userEntities = new ArrayList<>();

    public void addCategory(CategoryEntity categoryEntity){
        setCategory(categoryEntity);
        category.getProducts().add(this);
    }

    public ProductEntity(String productName, double price, int quantity) {
        this.productName = productName;
        this.price = price;
    }

    public ProductEntity() {
    }

    @JsonIgnore
    public List<UserEntity> getUserEntities() {
        return userEntities;
    }

    public void setUserEntities(List<UserEntity> userEntities) {
        this.userEntities = userEntities;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public CategoryEntity getCategory() {
        return category;
    }

    public void setCategory(CategoryEntity category) {
        this.category = category;
    }

}
