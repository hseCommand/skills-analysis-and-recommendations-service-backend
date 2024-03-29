package com.hse.profile.controller;

import com.hse.profile.dto.ApproveDto;
import com.hse.profile.dto.ProfileCreateDto;
import com.hse.profile.dto.ProfileDto;
import com.hse.profile.dto.ReviewProfileDto;
import com.hse.profile.dto.ReviewSkillInfoDto;
import com.hse.profile.dto.SkillInfoCreateDto;
import com.hse.profile.dto.SkillInfoDto;
import com.hse.profile.entity.Profile;
import com.hse.profile.entity.SkillInfo;
import com.hse.profile.exception.NoAccessRightsExceptions;
import com.hse.profile.mapper.ProfileMapper;
import com.hse.profile.service.ProfileService;
import com.hse.profile.util.JwtUtil;
import io.jsonwebtoken.JwtException;
import jakarta.ws.rs.NotAuthorizedException;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/profiles")
public class ProfileController {

  private final ProfileMapper profileMapper;

  private final ProfileService profileService;

  private final JwtUtil jwtUtil;

  @PostMapping
  public ProfileDto createProfile(
      @RequestBody ProfileCreateDto profileCreateDto,
      @RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
    Map<String, Object> userInfo = jwtUtil.validateTokenAndExtractData(token);
    Long userId = Long.parseLong(userInfo.get("id").toString());

    Profile profile = profileMapper.profileCreateDtoToProfile(profileCreateDto);
    profile.setUserId(userId);
    profile.getSkills().forEach(skillInfo -> skillInfo.setProfile(profile));

    return profileMapper.profileToProfileDto(profileService.addProfile(profile));
  }

  @PutMapping
  public ProfileDto updateProfile(
      @RequestBody ProfileDto profileDto, @RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
    Map<String, Object> userInfo = jwtUtil.validateTokenAndExtractData(token);
    Profile profile = profileMapper.profileDtoToProfile(profileDto);
    Profile profileFromBase = profileService.getProfileById(profile.getId());
    profile.setUserId(profileFromBase.getUserId());
    profile.getSkills().forEach(skillInfo -> {
      skillInfo.setId(profileFromBase.getSkills().stream()
          .filter(skillInfoFromBase -> skillInfo.getSkillId() == skillInfoFromBase.getSkillId())
          .findFirst().orElseThrow().getId());
      skillInfo.setProfile(profile);
    });

    if (profile.getUserId() != Long.parseLong(userInfo.get("id").toString())) {
      throw new NotAuthorizedException("No access rights");
    }

    return profileMapper.profileToProfileDto(profileService.updateProfile(profile));
  }

  @GetMapping("/all")
  public List<ProfileDto> getAllProfiles(
      @RequestHeader(value = HttpHeaders.AUTHORIZATION) String token) {
    List<Profile> profiles;
    try {
      jwtUtil.validateTokenAndCheckAccessRights(token, "ADMIN", "SUPERVISOR", "APPROVER");
      profiles = profileService.getAllProfiles();
    } catch (NoAccessRightsExceptions e) {
      Map<String, Object> userInfo = jwtUtil.validateTokenAndExtractData(token);
      Long userId = Long.parseLong(userInfo.get("id").toString());
      profiles = profileService.getProfilesByUserId(userId);
    }
    return profiles.stream().map(profileMapper::profileToProfileDto).collect(Collectors.toList());
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public void deleteProfileById(
      @PathVariable UUID id, @RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
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
    return profileMapper.profileToProfileDto(profileService.getProfileById(id));
  }

  @PostMapping("/addSkill/{profile_id}")
  public SkillInfoDto addSkillToProfileById(
      @PathVariable UUID profile_id,
      @RequestBody SkillInfoCreateDto skillInfoCreateDto,
      @RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
    Map<String, Object> userInfo = jwtUtil.validateTokenAndExtractData(token);
    Profile profile = profileService.getProfileById(profile_id);

    if (profile.getUserId() == Long.parseLong(userInfo.get("id").toString())) {
      SkillInfo skillInfo = profileMapper.skillInfoCreateDtoToSkillInfo(skillInfoCreateDto);
      skillInfo.setProfile(profile);
      profile.getSkills().add(skillInfo);
      profileService.updateProfile(profile);
      return profileMapper.skillInfoToSkillInfoDto(skillInfo);
    } else {
      throw new NotAuthorizedException("No access rights");
    }
  }

  @PostMapping("/approve")
  public ProfileDto approveSkillByReviewIdAndReviewGradeId(
      @RequestBody ApproveDto approveDto,
      @RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
    jwtUtil.validateTokenAndCheckAccessRights(token, "ADMIN", "APPROVER");
    Map<String, Object> userInfo = jwtUtil.validateTokenAndExtractData(token);
    Long approverId = Long.parseLong(userInfo.get("id").toString());

    return profileMapper.profileToProfileDto(
        profileService.approveSkillByProfileIdAndSkillInfoId(approveDto.getProfileId(),
            approveDto.getSkillId(), approverId, approveDto.getIsApprove()));
  }

  @PostMapping("/review")
  public ProfileDto setReviewFields(
      @RequestBody ReviewProfileDto reviewProfileDto,
      @RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
    jwtUtil.validateTokenAndCheckAccessRights(token, "ADMIN", "APPROVER");
    Map<String, Object> userInfo = jwtUtil.validateTokenAndExtractData(token);
    Long approverId = Long.parseLong(userInfo.get("id").toString());
    Profile profileFromBase = profileService.getProfileById(reviewProfileDto.getProfileId());

    profileFromBase.setApproverId(approverId);
    profileFromBase.setProfileComment(reviewProfileDto.getProfileComment());

    profileFromBase.getSkills().forEach(skillInfo -> {
      ReviewSkillInfoDto foundSkillInfo = reviewProfileDto.getReviewSkills().stream()
          .filter(reviewSkill -> reviewSkill.getSkillId() == skillInfo.getSkillId())
          .findFirst().orElseThrow();
      skillInfo.setSkillComment(foundSkillInfo.getSkillComment());
      skillInfo.setIsApprove(foundSkillInfo.getIsApprove());
    });

    return profileMapper.profileToProfileDto(profileService.updateProfile(profileFromBase));
  }
}
