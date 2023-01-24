package audit.exception;

public class EntityNotFoundException extends CommonException {

  public EntityNotFoundException(String code) {
    super(code);
  }

  public EntityNotFoundException(String code, String message) {
    super(code, message);
  }
}
