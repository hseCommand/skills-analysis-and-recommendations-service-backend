package com.hse.skillsevaluation.controller;

import com.hse.skillsevaluation.dto.FilterDto;
import com.hse.skillsevaluation.dto.SkillCreateDto;
import com.hse.skillsevaluation.dto.SkillDto;
import com.hse.skillsevaluation.entity.Skill;
import com.hse.skillsevaluation.mapper.SkillMapper;
import com.hse.skillsevaluation.service.Filter;
import com.hse.skillsevaluation.service.SkillService;
import com.hse.skillsevaluation.util.JwtUtil;
import jakarta.validation.Valid;
import jakarta.ws.rs.NotAuthorizedException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/skills")
public class SkillController {

  private final SkillService skillService;

  private final SkillMapper skillMapper;

  private final JwtUtil jwtUtil;

  @GetMapping
  public List<SkillDto> getAllSkills() {
    List<Skill> skills = skillService.getAllSkills();
    return skills.stream().map(skillMapper::skillToSkillDto).collect(Collectors.toList());
  }

  @PostMapping("/import")
  public List<SkillDto> importSkills(
      @RequestBody @Valid List<SkillCreateDto> skillCreateDtos,
      @RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
    validateTokenAndCheckAccessRights(token, "ADMIN");

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

  @PostMapping("/filter")
  public List<SkillDto> getAllSkillsByFilter(
      @RequestBody @Valid FilterDto filterDto,
      @RequestHeader(HttpHeaders.AUTHORIZATION) String token
  ) {
    Filter filter = skillMapper.FilterDtoToFilter(filterDto);
    List<Skill> skills = skillService.getAllSkillsByFilter(filter);

    return skills.stream().map(skillMapper::skillToSkillDto).collect(Collectors.toList());
  }

  @PostMapping
  public SkillDto addSkill(
      @RequestBody @Valid SkillCreateDto skillCreateDto,
      @RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
    validateTokenAndCheckAccessRights(token, "ADMIN");

    Skill skill = skillMapper.skillCreateDtoToSkill(skillCreateDto);
    Skill savedSkill = skillService.addSkill(skill);
    return skillMapper.skillToSkillDto(savedSkill);
  }

  @PutMapping
  public SkillDto updateSkill(
      @RequestBody @Valid SkillDto skillDto,
      @RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
    validateTokenAndCheckAccessRights(token, "ADMIN");

    Skill skill = skillMapper.skillDtoToSkill(skillDto);
    Skill updatedSkill = skillService.updateSkill(skill);
    return skillMapper.skillToSkillDto(updatedSkill);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public void deleteSkillById(
      @PathVariable Long id, @RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
    validateTokenAndCheckAccessRights(token, "ADMIN");

    skillService.deleteSkillById(id);
  }

  public void validateTokenAndCheckAccessRights(String token, String role) {
    Map<String, Object> userInfo = jwtUtil.validateTokenAndExtractData(token);
    if (!userInfo.get("roles").toString().contains(role)) {
      throw new NotAuthorizedException("No access rights");
    }
  }
}
