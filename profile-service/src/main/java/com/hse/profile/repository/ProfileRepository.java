package com.hse.profile.repository;

import com.hse.profile.entity.Profile;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProfileRepository extends JpaRepository<Profile, UUID> {}
