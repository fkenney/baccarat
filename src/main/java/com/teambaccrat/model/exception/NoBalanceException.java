package com.teambaccrat.model.exception;
/**
 * Exception that extends {@link IllegalArgumentException} to handle balance.
 */
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