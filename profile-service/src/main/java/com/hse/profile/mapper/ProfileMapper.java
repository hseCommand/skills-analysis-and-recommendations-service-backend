package com.hse.profile.mapper;

import com.hse.profile.dto.ProfileCreateDto;
import com.hse.profile.dto.ProfileDto;
import com.hse.profile.entity.Profile;
import java.time.Instant;
import java.time.ZoneId;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProfileMapper {

  @Mapping(
      target = "createdAt",
      expression = "java(mapInstantToDateString(profile.getCreatedAt()))")
  ProfileDto profiletoProfileDto(Profile profile);

  @Mapping(target = "createdAt", ignore = true)
  Profile profileDtoToProfile(ProfileDto profileDto);

  Profile profileCreateDtotoProfile(ProfileCreateDto profileCreateDto);

  default String mapInstantToDateString(Instant instant) {
    if (instant != null) {
      return instant.atZone(ZoneId.systemDefault()).toLocalDate().toString();
    }
    return null;
  }
}
