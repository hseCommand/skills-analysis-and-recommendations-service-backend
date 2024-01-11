package com.hse.profile.dto;

import com.hse.profile.entity.ProfileStatus;
import lombok.Data;

import java.util.List;

@Data
public class ProfileCreateDto {

  private ProfileStatus status;

  private int skillGrade;
  private List<Long> skills;
}
