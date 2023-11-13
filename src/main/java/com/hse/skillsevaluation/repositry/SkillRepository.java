package com.hse.skillsevaluation.repositry;

import com.hse.skillsevaluation.entity.Skill;
import com.hse.skillsevaluation.entity.skill_dependency.Tag;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SkillRepository extends JpaRepository<Skill, Long> {
  List<Skill> findDistinctSkillsByTagsIn(List<Tag> tags);
}
