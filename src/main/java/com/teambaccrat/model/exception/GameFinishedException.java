package com.teambaccrat.model.exception;

public class GameFinishedException extends IllegalStateException {

  public GameFinishedException(String message) {
    super(message);
  }

  public GameFinishedException(Throwable cause) {
    super(cause);
  }

  public GameFinishedException(String message, Throwable cause) {
    super(message, cause);
  }


}
