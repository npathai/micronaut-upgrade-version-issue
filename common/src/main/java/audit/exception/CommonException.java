package audit.exception;

public class CommonException extends RuntimeException {

  private final String message;
  private final String code;

  public CommonException(Throwable cause, String code, String message) {
    super(cause);
    this.code = code;
    this.message = message;
  }

  public CommonException() {
    this.code = null;
    this.message = null;
  }

  public CommonException(String code) {
    this.code = code;
    this.message = null;
  }

  public CommonException(String code, String message) {
    this.code = code;
    this.message = message;
  }

  public String getCode() {
    return code;
  }

  @Override
  public String getMessage() {
    return message;
  }
}
