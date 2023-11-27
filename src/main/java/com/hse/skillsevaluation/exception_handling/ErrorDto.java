package com.hse.skillsevaluation.exception_handling;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorDto {
  public ErrorDto(String info) {
    this.info = info;
  }

  private String info;



}
