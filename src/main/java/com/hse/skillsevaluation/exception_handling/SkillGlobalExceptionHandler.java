package com.hse.skillsevaluation.exception_handling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class SkillGlobalExceptionHandler {
  @ExceptionHandler
  public ResponseEntity<SkillIncorrectData> handleException(NoSuchSkillException exception) {
    SkillIncorrectData incorrectData = new SkillIncorrectData();
    incorrectData.setInfo(exception.getMessage());

    return new ResponseEntity<>(incorrectData, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler
  public ResponseEntity<SkillIncorrectData> handleException(Exception exception) {
    SkillIncorrectData incorrectData = new SkillIncorrectData();
    incorrectData.setInfo(exception.getMessage());

    return new ResponseEntity<>(incorrectData, HttpStatus.BAD_REQUEST);
  }
}
