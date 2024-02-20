package com.hse.profile.service;

import com.hse.profile.entity.Profile;
import com.hse.profile.exception.NoSuchProfileException;
import com.hse.profile.repository.ProfileRepository;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {

  private final ProfileRepository profileRepository;

  @Override
  public Profile updateProfile(Profile profile) {
    var profileOptional = profileRepository.findById(profile.getId());
    if (profileOptional.isPresent()) {
      profile.setCreatedAt(profileOptional.get().getCreatedAt());
      return profileRepository.save(profile);
    } else {
      throw new NoSuchProfileException();
    }
  }

  @Override
  public Profile addProfile(Profile profile) {
    return profileRepository.save(profile);
  }

  @Override
  public Profile getProfileById(UUID id) {
    return profileRepository.findById(id).orElseThrow(NoSuchProfileException::new);
  }

  @Override
  public List<Profile> getAllProfiles() {
    return profileRepository.findAll();
  }

  @Override
  public void deleteProfileById(UUID id) {
    profileRepository.deleteById(id);
  }

  public void getProfileByUserId(Long id) {
    profileRepository.
  }
}
