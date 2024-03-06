package com.hse.skillsevaluation.repository;

import com.hse.skillsevaluation.entity.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SkillRepository extends JpaRepository<Skill, Long>,
    JpaSpecificationExecutor<Skill> {
}
