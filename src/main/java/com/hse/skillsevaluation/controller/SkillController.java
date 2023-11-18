package com.hse.skillsevaluation.controller;

import com.hse.skillsevaluation.dto.skill.SkillCreateDto;
import com.hse.skillsevaluation.dto.skill.SkillDto;
import com.hse.skillsevaluation.entity.skill.Skill;
import com.hse.skillsevaluation.mapper.SkillMapper;
import com.hse.skillsevaluation.service.SkillService;
import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/skills")
public class SkillController {
  private final SkillService skillService;

  private final SkillMapper skillMapper;

  @GetMapping
  public List<SkillDto> getAllSkills() {
    List<Skill> skills = skillService.getAllSkills();
    return skills.stream().map(skillMapper::skillToSkillDto).collect(Collectors.toList());
  }

  @PostMapping("/import")
  public List<SkillDto> importSkills(@RequestBody @Valid List<SkillCreateDto> skillCreateDtos) {
    List<Skill> skillsToSave =
        skillCreateDtos.stream()
            .map(skillMapper::skillCreateDtoToSkill)
            .collect(Collectors.toList());

    List<Skill> savedSkills = skillService.saveAllSkills(skillsToSave);

    return savedSkills.stream().map(skillMapper::skillToSkillDto).collect(Collectors.toList());
  }

  @GetMapping("/{id}")
  public SkillDto getSkillById(@PathVariable Long id) {
    Skill skill = skillService.getSkillById(id);
    return skillMapper.skillToSkillDto(skill);
  }

  @PostMapping
  public SkillDto addSkill(@RequestBody @Valid SkillCreateDto skillCreateDto) {
    Skill skill = skillMapper.skillCreateDtoToSkill(skillCreateDto);
    Skill savedSkill = skillService.saveSkill(skill);
    return skillMapper.skillToSkillDto(savedSkill);
  }

  @PutMapping
  public SkillDto updateSkill(@RequestBody @Valid SkillDto skillDto) {
    Skill skill = skillMapper.skillDtoToSkill(skillDto);
    Skill updatedSkill = skillService.saveSkill(skill);
    return skillMapper.skillToSkillDto(updatedSkill);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> deleteSkillById(@PathVariable Long id) {
    skillService.deleteSkillById(id);
    return new ResponseEntity<>(HttpStatus.OK);
  }
}
