package com.hse.skillsevaluation.service;

import com.hse.skillsevaluation.entity.skill.Skill;
import java.util.List;

public interface SkillService {
  Skill getSkillById(Long id);
  
  List<Skill> getAllSkills();

  void saveSkill(Skill skill);

  void deleteSkillById(Long id);
}
