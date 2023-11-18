package com.hse.skillsevaluation.service;

import com.hse.skillsevaluation.entity.skill.Skill;
import java.util.List;

public interface SkillService {

  List<Skill> saveAllSkills(List<Skill> skills);

  Skill getSkillById(Long id);
  
  List<Skill> getAllSkills();

  Skill saveSkill(Skill skill);

  Skill addSkill(Skill skill);

  void deleteSkillById(Long id);
}
