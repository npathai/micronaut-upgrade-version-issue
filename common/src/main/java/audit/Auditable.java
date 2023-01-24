package audit;

import io.micronaut.data.annotation.event.PrePersist;

import java.time.Instant;

public abstract class Auditable {

  private Instant updatedAt;
  private String updatedBy;

  @PrePersist
  protected void beforeInsert() {
    updatedAt = Instant.now();
  }

  protected void beforeUpdate() {
    updatedAt = Instant.now();
  }

  public Instant getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(Instant updatedAt) {
    this.updatedAt = updatedAt;
  }

  public void setUpdatedBy(String updatedBy) {
    this.updatedBy = updatedBy;
  }

  public String getUpdatedBy() {
    return updatedBy;
  }
}
