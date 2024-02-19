package com.hse.authservice.controller;

import com.hse.authservice.dto.UserCredentialAuthRequest;
import com.hse.authservice.dto.UserCredentialRegisterRequest;
import com.hse.authservice.entity.Role;
import com.hse.authservice.entity.UserCredential;
import com.hse.authservice.mapper.UserCredentialMapper;
import com.hse.authservice.service.AuthService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AuthController {

  private final AuthService authService;

  private final AuthenticationManager authenticationManager;

  private final UserCredentialMapper userCredentialMapper;

  @PostMapping("/register")
  @ResponseStatus(HttpStatus.CREATED)
  public void createUser(@RequestBody UserCredentialRegisterRequest credential) {
    UserCredential userCredential = userCredentialMapper.userCredentialRegisterRequestToUserCredential(credential);
    userCredential.setRoles(List.of(Role.USER));
    authService.saveUser(userCredential);
  }

  @PostMapping("/token")
  public String getToken(@RequestBody UserCredentialAuthRequest userCredentialAuthRequest) {
    int[] a = new int[5];
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
    if (AuthorityUtils.authorityListToSet(SecurityContextHolder.getContext().getAuthentication().getAuthorities()).contains("ROLE_MANAGER")) {
      /* ... */
    }
    try {
      authService.validateToken(token);
    } catch (Exception e) {
      return false;
    }
    return true;
  }
}
