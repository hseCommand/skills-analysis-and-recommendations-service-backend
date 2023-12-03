package com.hse.skillsevaluation.dto;

import java.util.List;
import lombok.Data;

@Data
public class SkillDataDto {
  private String name;
  private List<SkillGradeDto> skillGrades;
}
