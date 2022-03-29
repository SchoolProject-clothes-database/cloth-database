package se.iths.clothdatabase.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import se.iths.clothdatabase.entity.ProductEntity;

@Repository
public interface ProductRepository extends CrudRepository<ProductEntity, Long> {
    ProductEntity findByProductName(String productName);

    @Query("SELECT sum(p.price) FROM ProductEntity p")
    double totalSum();
}
