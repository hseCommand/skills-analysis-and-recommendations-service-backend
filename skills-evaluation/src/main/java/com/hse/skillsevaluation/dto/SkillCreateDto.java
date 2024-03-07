package com.hse.skillsevaluation.dto;

import java.util.List;
import lombok.Data;

@Data
public class SkillCreateDto {
  private String skillType;
  private String unitType;
  private List<String> tags;
  private String name;
  private List<SkillGradeDto> skillGrades;
}
