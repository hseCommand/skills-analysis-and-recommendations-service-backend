package com.hse.skillsevaluation.entity.skill;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class SkillGrade {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private int gradeNumber;

  private String requirements;

  private String artifact;

  private String recommendation;
}
