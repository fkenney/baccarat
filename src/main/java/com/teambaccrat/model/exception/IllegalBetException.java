package com.teambaccrat.model.exception;

/**
 * Exception that extends {@link IllegalArgumentException} to handle invalid bets.
 */
public class IllegalBetException extends IllegalArgumentException {

  public IllegalBetException(String message) {
    super(message);
  }

  public IllegalBetException(Throwable cause) {
    super(cause);
  }


  public IllegalBetException(String message, Throwable cause) {
    super(message, cause);
  }
}
