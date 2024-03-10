package com.hse.profile.dto;

import com.hse.profile.entity.ReviewGrade;
import java.time.Instant;
import java.util.List;
import java.util.UUID;
import lombok.Data;

@Data
public class ReviewDto {
  private UUID id;

  private UUID profileId;

  private String createdAt;

  private List<ReviewGradeDto> reviewGrades;
}
