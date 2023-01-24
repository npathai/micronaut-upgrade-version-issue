package users;

import audit.Auditable;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.micronaut.core.annotation.Introspected;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Introspected
@Entity
@Table(name = "user_role")
public class UserRole extends Auditable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
  @GenericGenerator(name = "native", strategy = "native")
  private Long id;

  @JsonIgnore
  @ManyToOne(targetEntity = User.class)
  @JoinColumn(name = "userId")
  private User user;

  @ManyToOne(targetEntity = Role.class)
  @JoinColumn(name = "roleId")
  private Role role;

  public static UserRole with(Role role) {
    var userRole = new UserRole();
    userRole.setRole(role);
    return userRole;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public Role getRole() {
    return role;
  }

  public void setRole(Role role) {
    this.role = role;
  }

}
