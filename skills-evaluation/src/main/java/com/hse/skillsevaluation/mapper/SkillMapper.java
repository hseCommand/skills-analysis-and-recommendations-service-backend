package com.hse.skillsevaluation.mapper;

import com.hse.skillsevaluation.dto.FilterDto;
import com.hse.skillsevaluation.dto.SkillCreateDto;
import com.hse.skillsevaluation.dto.SkillDto;
import com.hse.skillsevaluation.dto.SkillGradeDto;
import com.hse.skillsevaluation.entity.Skill;
import com.hse.skillsevaluation.entity.SkillGrade;
import com.hse.skillsevaluation.entity.Tag;
import com.hse.skillsevaluation.service.Filter;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface SkillMapper {

  SkillDto skillToSkillDto(Skill skill);

  Skill skillDtoToSkill(SkillDto skillDto);

  Skill skillCreateDtoToSkill(SkillCreateDto skillCreateDto);

  Filter filterDtoToFilter(FilterDto filterDto);

  SkillGrade convertToSkillGrade(SkillGradeDto skillGradeDto);

  default List<String> tagsToStringList(List<Tag> tags) {
    if (tags == null) {
      return Collections.emptyList();
    }
    return tags.stream().map(Tag::getTag).collect(Collectors.toList());
  }

  default List<Tag> stringListToTags(List<String> stringList) {
    if (stringList == null) {
      return Collections.emptyList();
    }
    return stringList.stream()
        .map(
            tag -> {
              Tag t = new Tag();
              t.setTag(tag);
              return t;
            })
        .collect(Collectors.toList());
  }

  @AfterMapping
  default void setSkillGrades(SkillCreateDto skillCreateDto, @MappingTarget Skill skill) {
    if (skillCreateDto.getSkillGrades() != null) {
      for (SkillGrade skillGrade : skill.getSkillGrades()) {
        skillGrade.setSkill(skill);
      }
    }
  }
}
