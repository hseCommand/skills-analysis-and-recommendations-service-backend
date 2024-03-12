package com.hse.profile.dto;

import com.hse.profile.entity.ProfileStatus;
import com.hse.profile.entity.SkillType;
import com.hse.profile.entity.UnitType;
import java.util.List;
import lombok.Data;

@Data
public class ProfileCreateDto {
  private String status;

  private String skillType;

  private String unitType;

  private int targetGradeByDefault;

  private List<SkillInfoCreateDto> skills;
}
