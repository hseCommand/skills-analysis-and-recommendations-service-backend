package com.hse.skillsevaluation.entity.skill_dependency.skill_data_dependency;

import com.hse.skillsevaluation.entity.skill_dependency.skill_data_dependency.skill_grade_dependency.Artifact;
import com.hse.skillsevaluation.entity.skill_dependency.skill_data_dependency.skill_grade_dependency.Recommendation;
import com.hse.skillsevaluation.entity.skill_dependency.skill_data_dependency.skill_grade_dependency.Requirement;
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
public class SkillGrade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int gradeNumber;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Requirement> requirements;

    @OneToOne(cascade = CascadeType.ALL)
    private Artifact artifact;

    @OneToOne(cascade = CascadeType.ALL)
    private Recommendation recommendation;
}
