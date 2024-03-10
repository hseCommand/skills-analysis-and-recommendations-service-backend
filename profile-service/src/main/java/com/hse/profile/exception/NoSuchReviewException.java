package com.hse.profile.exception;

import java.io.Serial;

public class NoSuchReviewException extends RuntimeException {

  @Serial
  private static final long serialVersionUID = 1578312731813573742L;

  public NoSuchReviewException() {
    super("Requested review not found");
  }
}
