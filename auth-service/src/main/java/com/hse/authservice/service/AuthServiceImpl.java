package com.hse.authservice.service;

import com.hse.authservice.entity.UserCredential;
import com.hse.authservice.repository.UserCredentialsRepository;
import com.hse.authservice.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
  private final UserCredentialsRepository userCredentialsRepository;

  private final PasswordEncoder passwordEncoder;

  private final JwtUtil jwtUtil;
  @Override
  public UserCredential saveUser(UserCredential credential) {
    credential.setPassword(passwordEncoder.encode(credential.getPassword()));
    userCredentialsRepository.save(credential);
    return credential;
  }

  @Override
  public String generateToken(String userName) {
    return jwtUtil.generateToken(userName);
  }

  @Override
  public void validateToken(String token) {
    jwtUtil.validateToken(token);
  }
}
