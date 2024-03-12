package com.hse.profile.service;

import com.hse.profile.entity.Profile;

import java.util.List;
import java.util.UUID;

public interface ProfileService {

  Profile updateProfile(Profile profile);

  Profile addProfile(Profile profile);


  Profile getProfileById(UUID id);

  List<Profile> getAllProfiles();

  void deleteProfileById(UUID id);

  Profile approveSkillByProfileIdAndSkillInfoId(UUID profileId, Long skillInfoId,
      Long approverId, boolean isApprove);

  List<Profile> getProfilesByUserId(Long userId);
}
