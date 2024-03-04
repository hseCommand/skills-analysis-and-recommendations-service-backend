package com.hse.profile.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.time.Instant;
import java.util.List;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Getter
@Setter
@Table(name = "profile")
public class Profile {
  @Id @GeneratedValue private UUID id;

  @Column(name = "user_id", nullable = false)
  private Long userId;

  @Column(name = "created_at", updatable = false, nullable = false)
  @Temporal(TemporalType.TIMESTAMP)
  @CreationTimestamp
  private Instant createdAt;

  @Column(name = "status", nullable = false, columnDefinition = "VARCHAR(20) DEFAULT 'NEW'")
  @Enumerated(EnumType.STRING)
  private ProfileStatus status;

  @Column(name = "skill_grade")
  private int skillGrade;

  @Column(
      name = "skill_type",
      nullable = false,
      columnDefinition = "VARCHAR(20) DEFAULT 'EMPLOYEE'")
  @Enumerated(EnumType.STRING)
  private SkillType skillType;

  @Column(
      name = "unit_type",
      nullable = false,
      columnDefinition = "VARCHAR(20) DEFAULT 'DEVELOPER'")
  @Enumerated(EnumType.STRING)
  private UnitType unitType;

  @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
  @JoinTable(
      name = "profile_skills",
      joinColumns = @JoinColumn(name = "profile_id"),
      inverseJoinColumns = @JoinColumn(name = "skill_info_id"))
  private List<SkillInfo> skills;
}
