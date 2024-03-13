package com.hse.profile.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ReviewSkillInfoDto {
  @NotNull
  private Long skillId;

  private Boolean isApprove;

  private String skillComment;
}
