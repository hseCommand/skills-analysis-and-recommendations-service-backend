package com.hse.profile.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import java.time.Instant;
import java.util.List;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Getter
@Setter
public class Profile {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @NotNull
  private Long userId;

  @CreationTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  @Column(updatable = false)
  private Instant createdAt;

  @Enumerated(EnumType.STRING)
  @Column(columnDefinition = "VARCHAR(20) DEFAULT 'NEW'")
  private ProfileStatus status;

  private int skillGrade;

  @Enumerated(EnumType.STRING)
  @Column(columnDefinition = "VARCHAR(20) DEFAULT 'EMPLOYEE'")
  private SkillType skillType;

  @Enumerated(EnumType.STRING)
  @Column(columnDefinition = "VARCHAR(20) DEFAULT 'DEVELOPER'")
  private UnitType unitType;

  @ManyToMany(
      cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
  private List<SkillInfo> skills;
}
