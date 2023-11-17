package com.hse.skillsevaluation.repositry;

import com.hse.skillsevaluation.entity.skill.Skill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SkillRepository extends JpaRepository<Skill, Long> {
}
