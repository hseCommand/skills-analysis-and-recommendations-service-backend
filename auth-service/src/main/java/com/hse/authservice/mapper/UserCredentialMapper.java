package com.hse.authservice.mapper;

import com.hse.authservice.dto.UserCredentialRegisterRequest;
import com.hse.authservice.entity.UserCredential;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserCredentialMapper {

  UserCredential userCredentialRegisterRequestToUserCredential(
      UserCredentialRegisterRequest userCredentialRegisterRequest);
}
