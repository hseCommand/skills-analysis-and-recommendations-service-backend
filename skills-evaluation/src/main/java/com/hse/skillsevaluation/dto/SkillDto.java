package com.hse.skillsevaluation.dto;

import jakarta.validation.constraints.NotNull;
import java.util.List;
import lombok.Data;

@Data
public class SkillDto {
  @NotNull private Long id;
  private String skillType;
  private String unitType;
  private List<TagDto> tags;
  private SkillDataDto skillData;
}
