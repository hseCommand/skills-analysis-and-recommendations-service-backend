package com.hse.profile.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SkillInfoDto {
  @NotNull
  private Long id;
  @NotNull
  private Long skillId;

  private String artifact;

  private Integer targetGrade;

  private Integer selfReviewGrade;

  private Boolean isApprove;
}
