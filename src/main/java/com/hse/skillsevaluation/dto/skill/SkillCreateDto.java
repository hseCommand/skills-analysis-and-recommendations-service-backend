package com.hse.skillsevaluation.dto.skill;

import java.util.List;
import lombok.Data;

@Data
public class SkillCreateDto {
  private String skillType;
  private String unitType;
  private List<TagDto> tags;
  private SkillDataDto skillData;
}
