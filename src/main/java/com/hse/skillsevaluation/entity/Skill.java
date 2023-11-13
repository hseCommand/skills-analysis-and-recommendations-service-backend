package com.hse.skillsevaluation.entity;


import com.hse.skillsevaluation.entity.skill_dependency.SkillType;
import com.hse.skillsevaluation.entity.skill_dependency.UnitType;
import com.hse.skillsevaluation.entity.skill_dependency.SkillData;
import com.hse.skillsevaluation.entity.skill_dependency.Tag;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

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

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE,
    CascadeType.PERSIST, CascadeType.REFRESH})
    private List<Tag> tags;

    @OneToOne(cascade = CascadeType.ALL)
    private SkillData skillData;
}
