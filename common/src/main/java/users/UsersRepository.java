package users;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

import java.util.Optional;

@Repository
public interface UsersRepository extends CrudRepository<User, String> {

  Optional<User> findByUserId(String userId);
}
