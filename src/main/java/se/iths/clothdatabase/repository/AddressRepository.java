package se.iths.clothdatabase.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import se.iths.clothdatabase.entity.AddressEntity;

@Repository
public interface AddressRepository extends CrudRepository<AddressEntity, Long> {
}
