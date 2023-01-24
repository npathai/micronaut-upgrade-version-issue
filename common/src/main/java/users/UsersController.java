package users;

import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;

@Controller("/users")
public class UsersController {

  private final UsersService usersService;

  public UsersController(UsersService usersService) {
    this.usersService = usersService;
  }

  @Post("/syncUser")
  public User syncUser(@Body UserSaveDto userSaveDto) {
    final var userObject = createUserObject(userSaveDto);
    return usersService.syncUser(userObject);
  }

  private User createUserObject(UserSaveDto userSaveDto) {
    var user = new User();
    user.setFirstName(userSaveDto.getFirstName());
    user.setLastName(userSaveDto.getLastName());
    user.setEmail(userSaveDto.getEmail());
    user.setUserId(userSaveDto.getUserId());
    return user;
  }
}
