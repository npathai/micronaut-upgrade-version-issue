# micronaut-upgrade-version-issue
Execute the tests to reproduce the issue
`.\gradlew test`

## Commenting this line from `Auditable` class resolves the issue
```
  @PrePersist <--- Comment this line out
  protected void beforeInsert() {
    updatedAt = Instant.now();
  }
```
`PrePersist` is causing issue in the parent class. It is also present in `User` class, but seems to work fine.
