package com.hse.profile.exception;

import java.io.Serial;

public class NoSuchSkillInfoException extends RuntimeException {
  @Serial
  private static final long serialVersionUID = 1578312731813573742L;

  public NoSuchSkillInfoException() {
    super("Requested skillInfo not found");
  }
}
