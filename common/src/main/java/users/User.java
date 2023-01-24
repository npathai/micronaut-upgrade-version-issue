package users;

import audit.Auditable;
import io.micronaut.core.annotation.Introspected;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Introspected
@Entity
@Table(name = "users")
public class User extends Auditable {

  @Id private String id;

  @Column(updatable = false)
  private String userId;

  private String firstName;
  private String lastName;
  private String email;

  @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
  private Set<UserRole> userRoles = new HashSet<>();

  @PrePersist
  protected void beforeInsert() {
    super.beforeInsert();
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Set<UserRole> getUserRoles() {
    return userRoles;
  }

  public void setUserRoles(Set<UserRole> userRoles) {
    this.userRoles = userRoles;
  }
}
