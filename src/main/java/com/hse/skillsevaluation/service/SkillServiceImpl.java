package com.hse.skillsevaluation.service;

import com.hse.skillsevaluation.entity.skill.Skill;
import com.hse.skillsevaluation.exception_handling.NoSuchSkillException;
import com.hse.skillsevaluation.repositry.SkillRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SkillServiceImpl implements SkillService {
  private final SkillRepository skillRepository;

  @Override
  public List<Skill> getAllSkills() {
    return skillRepository.findAll();
  }

  @Transactional
  @Override
  public List<Skill> saveAllSkills(List<Skill> skills) {
    return skillRepository.saveAll(skills);
  }

  @Override
  public Skill getSkillById(Long id) {
    return skillRepository.findById(id).orElseThrow(NoSuchSkillException::new);
  }

  @Override
  public Skill saveSkill(Skill skill) {
    if (skillRepository.existsById(skill.getId())) {
      return skillRepository.save(skill);
    } else {
      throw new NoSuchSkillException();
    }
  }

  @Override
  public Skill addSkill(Skill skill) {
    return skillRepository.save(skill);
  }

  @Override
  public void deleteSkillById(Long id) {
    if (skillRepository.existsById(id)) {
      skillRepository.deleteById(id);
    } else {
      throw new NoSuchSkillException();
    }
  }
}
