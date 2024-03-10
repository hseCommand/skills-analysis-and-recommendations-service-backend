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

  void deleteProfilesByFilter(ProfilesFilter profilesFilter);
}
