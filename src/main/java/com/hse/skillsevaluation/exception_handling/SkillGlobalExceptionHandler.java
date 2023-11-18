package com.hse.skillsevaluation.exception_handling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class SkillGlobalExceptionHandler {
  @ExceptionHandler
  public ResponseEntity<ErrorDto> handleException(NoSuchSkillException exception) {
    ErrorDto incorrectData = new ErrorDto(exception.getMessage());

    return new ResponseEntity<>(incorrectData, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler
  public ResponseEntity<ErrorDto> handleException(Exception exception) {
    ErrorDto incorrectData = new ErrorDto(exception.getMessage());

    return new ResponseEntity<>(incorrectData, HttpStatus.BAD_REQUEST);
  }
}
