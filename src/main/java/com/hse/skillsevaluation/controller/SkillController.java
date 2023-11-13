package com.hse.skillsevaluation.controller;

import com.hse.skillsevaluation.entity.Skill;
import com.hse.skillsevaluation.entity.skill_dependency.Tag;
import com.hse.skillsevaluation.service.SkillServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SkillController {
    public SkillServiceImpl skillService;

    @Autowired
    public SkillController(SkillServiceImpl skillService) {
        this.skillService = skillService;
    }

    @GetMapping("/skills")
    public List<Skill> getAllSkills() {
        return skillService.getAllSkills();
    }

    @GetMapping("/skills/{id}")
    public Skill getSkillById(@PathVariable Long id) {
        return skillService.getSkillById(id);
    }

    @PostMapping("/skills/tag")
    public List<Skill> getSkillById(@RequestBody List<Tag> tags) {
        return skillService.getSkillByTags(tags);
    }

    @PostMapping("/skills")
    public Skill addNewEmployee(@RequestBody Skill skill) {
        skillService.saveSkill(skill);
        return skill;
    }

    @PutMapping("/skills")
    public Skill updateEmployee(@RequestBody Skill skill) {
        skillService.saveSkill(skill);
        return skill;
    }

    @DeleteMapping("/skills/{id}")
    public String deleteEmployee(@PathVariable Long id) {
        Skill skill = skillService.getSkillById(id);
        if (skill == null) {
            return "There is no employee with id = " + id;
        }
        skillService.deleteSkillById(id);
        return "Employee with id = " + id + " was deleted";
    }
}
