package com.hse.skillsevaluation.dto.skill;

import java.util.List;
import lombok.Data;

@Data
public class SkillDataDto {
  private String name;
  private List<SkillGradeDto> skillGrades;
}
