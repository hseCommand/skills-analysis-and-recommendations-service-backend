package com.hse.profile.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Table(name = "review_grade")
public class ReviewGrade {
  @Id
  @GeneratedValue
  private UUID id;

  @Column(name = "skill_info_id")
  private Long skillInfoId;

  @Column(name = "self_review_grade")
  private int selfReviewGrade;

  @Column(name = "is_approve")
  private boolean isApprove;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "review_id", nullable = false)
  private Review review;
}
