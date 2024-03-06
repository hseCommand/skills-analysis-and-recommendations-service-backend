package com.hse.skillsevaluation.dto;

import java.util.List;
import lombok.Data;

@Data
public class FilterDto {
  private List<String> skillTypes;
  private List<String> unitTypes;
  private List<TagDto> tags;
}
