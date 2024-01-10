package com.hse.authservice.repository;

import com.hse.authservice.entity.UserCredential;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserCredentialsRepository extends JpaRepository<UserCredential, Long> {
  Optional<UserCredential> findByName(String name);
}
