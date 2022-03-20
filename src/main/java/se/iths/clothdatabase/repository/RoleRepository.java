package se.iths.clothdatabase.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import se.iths.clothdatabase.entity.RoleEntity;

@Repository
public interface RoleRepository extends CrudRepository<RoleEntity,Long> {
    RoleEntity findByRole(String role);
}
