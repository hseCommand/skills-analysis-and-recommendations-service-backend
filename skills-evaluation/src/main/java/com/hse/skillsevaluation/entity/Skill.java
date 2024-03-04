package com.hse.skillsevaluation.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "skill")
public class Skill {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "name", nullable = false, unique = true)
  private String name;

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

  @ManyToMany(
      cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
  @JoinTable(
      name = "skill_tag",
      joinColumns = @JoinColumn(name = "skill_id"),
      inverseJoinColumns = @JoinColumn(name = "tag_id"))
  private List<Tag> tags;

  @OneToMany(mappedBy = "skill", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<SkillGrade> skillGrades;

  public void addSkillGrade(SkillGrade skillGrade) {
    skillGrades.add(skillGrade);
    skillGrade.setSkill(this);
  }

}
