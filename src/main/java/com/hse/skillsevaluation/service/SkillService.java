package com.hse.skillsevaluation.service;

import com.hse.skillsevaluation.entity.Skill;
import com.hse.skillsevaluation.entity.skill_dependency.Tag;
import java.util.List;

public interface SkillService {
  Skill getSkillById(Long id);

  List<Skill> getSkillsByTags(List<Tag> tags);

  List<Skill> getAllSkills();

  void saveSkill(Skill skill);

  void deleteSkillById(Long id);
}
