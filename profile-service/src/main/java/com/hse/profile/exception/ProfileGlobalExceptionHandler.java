package com.hse.profile.exception;

import com.hse.profile.dto.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ProfileGlobalExceptionHandler {
  @ExceptionHandler
  public ResponseEntity<ErrorDto> handleException(NoSuchProfileException exception) {
    ErrorDto incorrectData = new ErrorDto(exception.getMessage());

    return new ResponseEntity<>(incorrectData, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler
  public ResponseEntity<ErrorDto> handleException(NoSuchSkillInfoException exception) {
    ErrorDto incorrectData = new ErrorDto(exception.getMessage());

    return new ResponseEntity<>(incorrectData, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler
  public ResponseEntity<ErrorDto> handleException(Exception exception) {
    ErrorDto incorrectData = new ErrorDto(exception.getMessage());

    return new ResponseEntity<>(incorrectData, HttpStatus.BAD_REQUEST);
  }
}
