package com.hse.skillsevaluation.exception_handling;

import java.io.Serial;

public class NoSuchSkillException extends RuntimeException {
  @Serial private static final long serialVersionUID = -5083318909820839641L;

  public NoSuchSkillException(String message) {
    super(message);
  }
}
