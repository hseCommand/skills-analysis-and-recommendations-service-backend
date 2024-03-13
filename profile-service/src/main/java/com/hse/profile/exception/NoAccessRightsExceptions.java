package com.hse.profile.exception;

import java.io.Serial;

public class NoAccessRightsExceptions extends RuntimeException{
  @Serial
  private static final long serialVersionUID = 1578312731813573742L;

  public NoAccessRightsExceptions() {
    super("No access rights");
  }
}
