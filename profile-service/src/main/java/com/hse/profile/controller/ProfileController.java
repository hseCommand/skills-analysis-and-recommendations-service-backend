package com.hse.profile.controller;

import com.hse.profile.dto.ProfileCreateDto;
import com.hse.profile.dto.ProfileDto;
import com.hse.profile.entity.Profile;
import com.hse.profile.mapper.ProfileMapper;
import com.hse.profile.service.ProfileService;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("/profiles")
public class ProfileController {

  private final ProfileMapper profileMapper;

  private final ProfileService profileService;

  @PostMapping
  public ProfileDto createProfile(@RequestBody ProfileCreateDto profileCreateDto) {
    Profile profile = profileMapper.profileCreateDtotoProfile(profileCreateDto);
    return profileMapper.profiletoProfileDto(profileService.addProfile(profile));
  }

  @PutMapping
  public ProfileDto updateProfile(@RequestBody ProfileDto profileDto) {
    Profile profile = profileMapper.profileDtoToProfile(profileDto);
    return profileMapper.profiletoProfileDto(profileService.updateProfile(profile));
  }

  @GetMapping("/all")
  public List<ProfileDto> getAllProfiles() {
    var profiles = profileService.getAllProfiles();
    return profiles.stream().map(profileMapper::profiletoProfileDto).collect(Collectors.toList());
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public void deleteProfileById(@PathVariable UUID id) {
    profileService.deleteProfileById(id);
  }

  @GetMapping("/{id}")
  public ProfileDto getProfileById(@PathVariable UUID id) {
    return profileMapper.profiletoProfileDto(profileService.getProfileById(id));
  }
}
