package com.hse.skillsevaluation.service;

import com.hse.skillsevaluation.entity.Skill;
import com.hse.skillsevaluation.entity.skill_dependency.Tag;
import com.hse.skillsevaluation.repositry.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SkillServiceImpl implements SkillService{
    private SkillRepository skillRepository;

    @Autowired
    public SkillServiceImpl(SkillRepository skillRepository) {
        this.skillRepository = skillRepository;
    }

    public List<Skill> getAllSkills() {
        return skillRepository.findAll();
    }

    @Override
    public Skill getSkillById(Long id) {
        Skill skill = null;
        Optional<Skill> optionalEmployee = skillRepository.findById(id);
        if (optionalEmployee.isPresent()) {
            skill = optionalEmployee.get();
        }
        return skill;
    }

    @Override
    public List<Skill> getSkillByTags(List<Tag> tags) {
        return skillRepository.findDistinctSkillsByTagsIn(tags);
    }

    @Override
    public void saveSkill(Skill skill) {
        skillRepository.save(skill);
    }

    @Override
    public void deleteSkillById(Long id) {
        skillRepository.deleteById(id);
    }
}
