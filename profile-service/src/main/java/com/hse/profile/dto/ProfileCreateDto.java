package com.hse.profile.dto;

import com.hse.profile.entity.ProfileStatus;
import com.hse.profile.entity.SkillType;
import com.hse.profile.entity.UnitType;
import java.util.List;
import lombok.Data;

@Data
public class ProfileCreateDto {

  private Long userId;

  private ProfileStatus status;

  private SkillType skillType;

  private UnitType unitType;

  private int skillGrade;

  private List<SkillInfoDto> skills;
}
