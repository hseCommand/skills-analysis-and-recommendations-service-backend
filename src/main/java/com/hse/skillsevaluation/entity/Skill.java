package com.hse.skillsevaluation.entity;

import com.hse.skillsevaluation.entity.skill_dependency.SkillData;
import com.hse.skillsevaluation.entity.skill_dependency.SkillType;
import com.hse.skillsevaluation.entity.skill_dependency.Tag;
import com.hse.skillsevaluation.entity.skill_dependency.UnitType;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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
