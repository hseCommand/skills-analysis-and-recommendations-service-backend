package com.hse.authservice.util;

import com.hse.authservice.entity.Role;
import com.hse.authservice.entity.UserCredential;
import java.util.Collection;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

public class CustomUserDetails implements UserDetails {

  private String name;
  private String password;

  private List<Role> roles;

  public CustomUserDetails(UserCredential userCredential) {
    this.name = userCredential.getName();
    this.password = userCredential.getPassword();
    this.roles = userCredential.getRoles();
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    String[] userRoles = roles.stream().map(Enum::name).toArray(String[]::new);
    return AuthorityUtils.createAuthorityList(userRoles);
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return name;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
