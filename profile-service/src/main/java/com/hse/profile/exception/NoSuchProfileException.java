package com.hse.profile.exception;

import java.io.Serial;

public class NoSuchProfileException extends RuntimeException {
  @Serial private static final long serialVersionUID = 1578312731813573742L;

  public NoSuchProfileException() {
    super("Requested profile not found");
  }
}
