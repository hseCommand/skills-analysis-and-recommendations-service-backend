package com.hse.skillsevaluation.repository;

import com.hse.skillsevaluation.entity.Skill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SkillRepository extends JpaRepository<Skill, Long> {
}
