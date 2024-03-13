package com.hse.profile.dto;

import jakarta.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;
import lombok.Data;

@Data
public class ReviewProfileDto {
  @NotNull
  private UUID profileId;

  private String profileComment;

  private List<ReviewSkillInfoDto> reviewSkills;
}
