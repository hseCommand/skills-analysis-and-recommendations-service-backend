package com.hse.skillsevaluation.dto.skill;

import lombok.Data;

@Data
public class SkillGradeDto {
  private int gradeNumber;
  private String requirements;
  private String artifact;
  private String recommendation;
}
