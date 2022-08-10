package com.teambaccrat.model.exception;

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
