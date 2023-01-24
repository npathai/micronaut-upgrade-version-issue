package users;

import audit.exception.EntityNotFoundException;
import jakarta.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.Set;

@Singleton
public class UsersService {

  private static final Logger log = LoggerFactory.getLogger(UsersService.class);
  private final UsersRepository usersRepository;
  private final UserRoleRepository userRoleRepository;

  public UsersService(
      UsersRepository usersRepository,
      UserRoleRepository userRoleRepository) {
    this.usersRepository = usersRepository;
    this.userRoleRepository = userRoleRepository;
  }

  @Transactional
  public User syncUser(User user) {
    Optional<User> savedUser = usersRepository.findByUserId(user.getUserId());
    return savedUser.map(value -> updatePersistedUser(value, user)).orElseGet(() -> createNewUser(user));
  }

  private User updatePersistedUser(User persistedUser, User newUser) {
    setNewFields(persistedUser, newUser);
    usersRepository.update(persistedUser);
    return persistedUser;
  }

  private void saveAllUserRoles(User persistedUser, Set<UserRole> userRolesToAdd) {
    userRolesToAdd.forEach(
        userRole -> {
          setUser(persistedUser, userRole);
          userRoleRepository.save(userRole);
        });
  }

  private void setNewFields(User persistedUser, User newUser) {
    persistedUser.setEmail(newUser.getEmail());
    persistedUser.setFirstName(newUser.getFirstName());
    persistedUser.setLastName(newUser.getLastName());
  }

  private User createNewUser(User user) {
    usersRepository.save(user);
    if (user.getUserRoles() != null) {
      saveAllUserRoles(user, user.getUserRoles());
    }
    return user;
  }

  private void setUser(User user, UserRole userRole) {
    userRole.setUser(user);
  }
}
