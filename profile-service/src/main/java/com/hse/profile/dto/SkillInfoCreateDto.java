package com.hse.profile.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SkillInfoCreateDto {
  @NotNull private Long skillId;

  private String artifact;

  private Integer targetGrade;
}
