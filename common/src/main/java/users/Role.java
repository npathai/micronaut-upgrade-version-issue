package users;

import audit.Auditable;
import io.micronaut.core.annotation.Introspected;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Introspected
@Entity
@Table(name = "roles")
public class Role extends Auditable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
  @GenericGenerator(name = "native", strategy = "native")
  private Integer id;

  private String name;

  public static Role with(Integer roleId) {
    var role = new Role();
    role.setId(roleId);
    return role;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
