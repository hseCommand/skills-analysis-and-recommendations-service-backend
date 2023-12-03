package com.hse.skillsevaluation.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorDto {
  private String info;

  public ErrorDto(String info) {
    this.info = info;
  }



}
