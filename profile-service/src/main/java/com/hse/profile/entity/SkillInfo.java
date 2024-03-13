package com.hse.profile.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "skill_info")
public class SkillInfo {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "skill_id", nullable = false)
  private Long skillId;

  @Column(name = "artifact")
  private String artifact;

  @Column(name = "target_grade")
  private Integer targetGrade;

  @Column(name = "self_review_grade")
  private Integer selfReviewGrade;

  @Column(name = "is_approve")
  private Boolean isApprove;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "profile_id", nullable = false)
  private Profile profile;
}
