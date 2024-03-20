package com.hse.skillsevaluation.service;

import com.hse.skillsevaluation.entity.Skill;
import java.util.List;

public interface SkillService {

  List<Skill> saveAllSkills(List<Skill> skills);

  Skill getSkillById(Long id);
  
  List<Skill> getAllSkills();

  List<Skill> getAllSkillsByFilter(Filter filter);

  Skill updateSkill(Skill skill);

  Skill addSkill(Skill skill);

  void deleteSkillById(Long id);

  List<String> getAllDistinctTags();
}
