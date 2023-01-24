package users;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

import java.util.List;

@Repository
public interface UserRoleRepository extends CrudRepository<UserRole, Long> {

  List<UserRole> findAllByRoleId(Integer roleId);
}
