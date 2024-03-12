package com.hse.profile.mapper;

import com.hse.profile.dto.ProfileCreateDto;
import com.hse.profile.dto.ProfileDto;
import com.hse.profile.dto.SkillInfoCreateDto;
import com.hse.profile.dto.SkillInfoDto;
import com.hse.profile.entity.Profile;
import com.hse.profile.entity.SkillInfo;
import java.time.Instant;
import java.time.ZoneId;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProfileMapper {

  @Mapping(
      target = "createdAt",
      expression = "java(mapInstantToDateString(profile.getCreatedAt()))")
  ProfileDto profileToProfileDto(Profile profile);

  @Mapping(target = "createdAt", ignore = true)
  Profile profileDtoToProfile(ProfileDto profileDto);

  Profile profileCreateDtoToProfile(ProfileCreateDto profileCreateDto);

  SkillInfo skillInfoCreateDtoToSkillInfo(SkillInfoCreateDto skillInfoDto);

  SkillInfoDto skillInfoToSkillInfoDto(SkillInfo skillInfo);

  default String mapInstantToDateString(Instant instant) {
    if (instant != null) {
      return instant.atZone(ZoneId.systemDefault()).toLocalDate().toString();
    }
    return null;
  }
}
