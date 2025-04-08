package com.jobtracker.service;

import com.jobtracker.dto.RegisterRequestDto;
import com.jobtracker.dto.UserRequestDto;
import com.jobtracker.dto.UserResponseDto;
import com.jobtracker.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    UserResponseDto register(RegisterRequestDto dto);

    UserResponseDto findByEmail(String email);

    boolean existsByEmail(String email);

    void delete(long id);

    UserResponseDto update(UserRequestDto dto, String email);

    User getEntityByEmail(String email);
}