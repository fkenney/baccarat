package com.teambaccrat.model.exception;

public class NoBalanceException extends IllegalArgumentException {

  public NoBalanceException(String message) {
    super(message);
  }

  public NoBalanceException(Throwable cause) {
    super(cause);
  }

  public NoBalanceException(String message, Throwable cause) {
    super(message, cause);
  }

}
