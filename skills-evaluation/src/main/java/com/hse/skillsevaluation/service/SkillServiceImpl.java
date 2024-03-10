package com.hse.skillsevaluation.service;

import com.hse.skillsevaluation.dto.FilterDto;
import com.hse.skillsevaluation.entity.Skill;
import com.hse.skillsevaluation.entity.Tag;
import com.hse.skillsevaluation.exception_handling.NoSuchSkillException;
import com.hse.skillsevaluation.repository.SkillRepository;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.data.jpa.domain.Specification.*;
import static com.hse.skillsevaluation.service.CustomSpecs.*;

@Service
@RequiredArgsConstructor
public class SkillServiceImpl implements SkillService {

  private final SkillRepository skillRepository;

  @Override
  public List<Skill> getAllSkills() {
    return skillRepository.findAll();
  }

  @Override
  public List<Skill> getAllSkillsByFilter(Filter filter) {
    return skillRepository.findAll(
        where(byUnitType(filter.getUnitTypes()))
            .and(bySkillType(filter.getSkillTypes())
                .and(byTags(filter.getTags())))
    );
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
  public Skill updateSkill(Skill skill) {
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

  @Override
  public List<String> getAllDistinctTags() {
    return skillRepository.getAllDistinctTags();
  }
}
