package com.teambaccrat.model.exception;
/**
 * Exception that extends {@link IllegalArgumentException} to handle invalid wagers.
 */
public class IllegalWagerAmountException extends IllegalArgumentException {

  public IllegalWagerAmountException(String message) {
    super(message);
  }

  public IllegalWagerAmountException(Throwable cause) {
    super(cause);
  }


  public IllegalWagerAmountException(String message, Throwable cause) {
    super(message, cause);
  }
}
