package se.iths.clothdatabase.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import se.iths.clothdatabase.entity.CartEntity;

@Repository
public interface CartRepository extends CrudRepository<CartEntity, Long> {
}
