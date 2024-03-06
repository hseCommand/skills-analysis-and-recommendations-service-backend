package com.hse.skillsevaluation.mapper;

import com.hse.skillsevaluation.dto.FilterDto;
import com.hse.skillsevaluation.dto.SkillCreateDto;
import com.hse.skillsevaluation.dto.SkillDto;
import com.hse.skillsevaluation.dto.SkillGradeDto;
import com.hse.skillsevaluation.entity.Skill;
import com.hse.skillsevaluation.entity.SkillGrade;
import com.hse.skillsevaluation.service.Filter;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface SkillMapper {

  SkillDto skillToSkillDto(Skill skill);

  Skill skillDtoToSkill(SkillDto skillDto);

  Skill skillCreateDtoToSkill(SkillCreateDto skillCreateDto);

  Filter FilterDtoToFilter(FilterDto filterDto);

  SkillGrade convertToSkillGrade(SkillGradeDto skillGradeDto);

  @AfterMapping
  default void setSkillGrades(SkillCreateDto skillCreateDto, @MappingTarget Skill skill) {
    if (skillCreateDto.getSkillGrades() != null) {
      for (SkillGrade skillGrade : skill.getSkillGrades()) {
        skillGrade.setSkill(skill);
      }
    }
  }
}
