package com.hse.skillsevaluation.mapper;

import com.hse.skillsevaluation.dto.SkillCreateDto;
import com.hse.skillsevaluation.dto.SkillDto;
import com.hse.skillsevaluation.entity.Skill;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SkillMapper {

  SkillDto skillToSkillDto(Skill skill);

  Skill skillDtoToSkill(SkillDto skillDto);

  Skill skillCreateDtoToSkill(SkillCreateDto skillCreateDto);
}
