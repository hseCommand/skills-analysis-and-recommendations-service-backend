package com.hse.profile.dto;

import jakarta.validation.constraints.NotNull;
import java.util.UUID;
import lombok.Data;

@Data
public class ApproveDto {
  @NotNull
  private UUID profileId;
  @NotNull
  private Long skillInfoId;
  @NotNull
  private Boolean isApprove;
}
