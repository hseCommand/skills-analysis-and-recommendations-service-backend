package com.hse.profile.exception;

import java.io.Serial;

public class NoSuchReviewGradeException extends RuntimeException {
  @Serial
  private static final long serialVersionUID = 1578312731813573742L;

  public NoSuchReviewGradeException() {
    super("Requested review not found");
  }
}
