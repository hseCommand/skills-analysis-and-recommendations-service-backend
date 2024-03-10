package com.hse.profile.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SkillInfoDto {
  @NotNull private Long skillId;

  private String artifact;

  private int targetGrade;
}
