package com.teambaccrat.model.exception;

public class IllegalBetException extends  IllegalArgumentException{
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
