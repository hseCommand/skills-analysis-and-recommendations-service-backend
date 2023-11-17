package com.hse.skillsevaluation.controller;

import com.hse.skillsevaluation.entity.skill.Skill;
import com.hse.skillsevaluation.entity.skill.Tag;
import com.hse.skillsevaluation.service.SkillService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SkillController {
  private final SkillService skillService;

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
    return skillService.getSkillsByTags(tags);
  }

  @PostMapping("/skills")
  public Skill addSkill(@RequestBody Skill skill) {
    skillService.saveSkill(skill);
    return skill;
  }

  @PutMapping("/skills")
  public Skill updateSkill(@RequestBody Skill skill) {
    skillService.saveSkill(skill);
    return skill;
  }

  @DeleteMapping("/skills/{id}")
  public String deleteSkillById(@PathVariable Long id) {
    Skill skill = skillService.getSkillById(id);
    if (skill == null) {
      return "There is no employee with id = " + id;
    }
    skillService.deleteSkillById(id);
    return "Employee with id = " + id + " was deleted";
  }
}
