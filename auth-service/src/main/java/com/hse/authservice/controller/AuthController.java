package com.hse.authservice.controller;

import com.hse.authservice.dto.UserCredentialAuthRequest;
import com.hse.authservice.dto.UserCredentialRegisterRequest;
import com.hse.authservice.mapper.UserCredentialMapper;
import com.hse.authservice.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

  private final AuthService authService;

  private final AuthenticationManager authenticationManager;

  private final UserCredentialMapper userCredentialMapper;

  @PostMapping("/register")
  @ResponseStatus(HttpStatus.CREATED)
  public void createUser(@RequestBody UserCredentialRegisterRequest credential) {
    authService.saveUser(
        userCredentialMapper.userCredentialRegisterRequestToUserCredential(credential));
  }

  @PostMapping("/token")
  public String getToken(@RequestBody UserCredentialAuthRequest userCredentialAuthRequest) {
    Authentication auth =
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                userCredentialAuthRequest.getName(), userCredentialAuthRequest.getPassword()));
    if (auth.isAuthenticated()) {
      return authService.generateToken(userCredentialAuthRequest.getName());
    } else {
      throw new RuntimeException("Invalid access");
    }
  }

  @GetMapping("/validate")
  public boolean validateToken(@RequestParam("token") String token) {
    try {
      authService.validateToken(token);
    } catch (Exception e) {
      return false;
    }
    return true;
  }
}