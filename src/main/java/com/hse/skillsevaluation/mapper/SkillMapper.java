package com.hse.skillsevaluation.mapper;

import com.hse.skillsevaluation.dto.skill.SkillDto;
import com.hse.skillsevaluation.dto.skill.SkillCreateDto;
import com.hse.skillsevaluation.entity.skill.Skill;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SkillMapper {

  SkillDto skillToSkillDto(Skill skill);

  Skill skillDtoToSkill(SkillDto skillDto);

  SkillCreateDto skillToSkillCreateDto(Skill skill);

  Skill skillCreateDtoToSkill(SkillCreateDto skillCreateDto);
}
