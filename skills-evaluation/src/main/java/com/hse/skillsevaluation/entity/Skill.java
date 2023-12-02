package com.hse.skillsevaluation.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Skill {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private SkillType skillType;

  private UnitType unitType;

  @ManyToMany(
      cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
  private List<Tag> tags;

  @OneToOne(cascade = CascadeType.ALL)
  private SkillData skillData;
}
