package com.hse.skillsevaluation.service;

import com.hse.skillsevaluation.entity.Skill;
import com.hse.skillsevaluation.entity.skill_dependency.Tag;

import java.util.List;

public interface SkillService {
    public List<Skill> getAllSkills();

    public Skill getSkillById(Long id);

    public List<Skill> getSkillByTags(List<Tag> tags);

    public void saveSkill(Skill skill);

    public void deleteSkillById(Long id);
}
