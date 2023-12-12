package com.hse.authservice.service;

import com.hse.authservice.entity.UserCredential;

public interface AuthService {
  UserCredential saveUser(UserCredential credential);

  String generateToken(String userName);

  void validateToken(String token);
}
