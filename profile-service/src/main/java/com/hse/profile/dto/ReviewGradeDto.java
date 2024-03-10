package com.hse.profile.dto;

import java.util.UUID;
import lombok.Data;

@Data
public class ReviewGradeDto {
  private UUID id;

  private Long skillInfoId;

  private int selfReviewGrade;
}
