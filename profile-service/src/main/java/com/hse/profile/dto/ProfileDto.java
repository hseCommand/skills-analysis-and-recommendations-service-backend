package com.hse.profile.dto;

import com.hse.profile.entity.ProfileStatus;
import com.hse.profile.entity.SkillType;
import com.hse.profile.entity.UnitType;
import java.util.List;
import java.util.UUID;
import lombok.Data;

@Data
public class ProfileDto {
  private UUID id;

  private String userLogin;

  private String createdAt;

  private String status;

  private String skillType;

  private String unitType;

  private Integer targetGradeByDefault;

  private String profileComment;

  private List<SkillInfoDto> skills;
}
