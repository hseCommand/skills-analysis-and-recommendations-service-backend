package com.hse.profile.controller;

import com.hse.profile.dto.ProfileCreateDto;
import com.hse.profile.dto.ProfileDto;
import com.hse.profile.entity.Profile;
import com.hse.profile.mapper.ProfileMapper;
import com.hse.profile.service.ProfileService;
import com.hse.profile.util.JwtUtil;
import jakarta.ws.rs.NotAuthorizedException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequiredArgsConstructor
@RequestMapping("/profiles")
public class ProfileController {

  private final ProfileMapper profileMapper;

  private final ProfileService profileService;

  private final JwtUtil jwtUtil;

  @PostMapping
  public ProfileDto createProfile(@RequestBody ProfileCreateDto profileCreateDto) {
    Profile profile = profileMapper.profileCreateDtotoProfile(profileCreateDto);
    return profileMapper.profiletoProfileDto(profileService.addProfile(profile));
  }

  @PutMapping
  public ProfileDto updateProfile(@RequestBody ProfileDto profileDto,
      @RequestHeader(value = HttpHeaders.AUTHORIZATION) String token) {
    Map<String, Object> userInfo = jwtUtil.validateTokenAndExtractData(token);
    Profile profile = profileMapper.profileDtoToProfile(profileDto);

    if (profile.getUserId() != Long.parseLong(userInfo.get("id").toString())) {
      throw new NotAuthorizedException("No access rights");
    }

    return profileMapper.profiletoProfileDto(profileService.updateProfile(profile));
  }

  @GetMapping("/all")
  public List<ProfileDto> getAllProfiles(
      @RequestHeader(value = HttpHeaders.AUTHORIZATION) String token) {
    validateTokenAndCheckAccessRights(token, "ADMIN", "SUPERVISOR");

    var profiles = profileService.getAllProfiles();
    return profiles.stream().map(profileMapper::profiletoProfileDto).collect(Collectors.toList());
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public void deleteProfileById(@PathVariable UUID id,
      @RequestHeader(value = HttpHeaders.AUTHORIZATION) String token) {
    Map<String, Object> userInfo = jwtUtil.validateTokenAndExtractData(token);
    Profile profile = profileService.getProfileById(id);

    if (profile.getUserId() == Long.parseLong(userInfo.get("id").toString())) {
      profileService.deleteProfileById(id);
    } else {
      throw new NotAuthorizedException("No access rights");
    }
  }

  @GetMapping("/{id}")
  public ProfileDto getProfileById(@PathVariable UUID id) {
    return profileMapper.profiletoProfileDto(profileService.getProfileById(id));
  }

  public void validateTokenAndCheckAccessRights(String token, String... roles) {
    Map<String, Object> userInfo = jwtUtil.validateTokenAndExtractData(token);
    String rolesStr = userInfo.get("roles").toString();
    if (Arrays.stream(roles).noneMatch(rolesStr::contains)) {
      throw new NotAuthorizedException("No access rights");
    }
  }
}
