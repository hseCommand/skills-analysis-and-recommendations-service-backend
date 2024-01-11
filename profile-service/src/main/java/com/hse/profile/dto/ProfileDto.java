package com.hse.profile.dto;

import com.hse.profile.entity.ProfileStatus;
import java.util.List;
import java.util.UUID;
import lombok.Data;

@Data
public class ProfileDto {
  private UUID id;

  private String createdAt;

  private ProfileStatus status;

  private int skillGrade;
  private List<Long> skills;
}
