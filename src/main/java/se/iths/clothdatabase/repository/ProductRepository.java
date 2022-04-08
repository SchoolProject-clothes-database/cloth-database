package se.iths.clothdatabase.repository;

import org.hibernate.annotations.NamedNativeQuery;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import se.iths.clothdatabase.entity.ProductEntity;

import java.util.Collection;
import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<ProductEntity, Long> {
    ProductEntity findByProductName(String productName);

    @Query(value = "SELECT sum(pe.price) FROM cart c join user_entity ue on ue.id = c.user_entity_id join product_entity pe on c.product_entities_id = pe.id where c.user_entity_id = ?1", nativeQuery = true)
    Collection<Double> totalSum(Long userId);

    @Modifying
    @Query(value = "DELETE pe, c FROM cart c inner join user_entity ue on ue.id = c.user_entity_id inner join product_entity pe on c.product_entities_id = pe.id where c.user_entity_id = ?1", nativeQuery = true)
    void purchasedProduct(Long userId);

}
