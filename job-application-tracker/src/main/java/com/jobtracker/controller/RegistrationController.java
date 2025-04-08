package com.jobtracker.controller;

import com.jobtracker.dto.RegisterRequestDto;
import com.jobtracker.dto.UserResponseDto;
import com.jobtracker.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class RegistrationController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<UserResponseDto> register(@RequestBody @Valid RegisterRequestDto registerDto) {
        if (userService.existsByEmail(registerDto.getEmail())) {
            log.warn("User with email {} already exists", registerDto.getEmail());
            return ResponseEntity.badRequest().build();
        }

        registerDto.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        UserResponseDto createdUser = userService.register(registerDto);

        log.info("User registered: {}", createdUser.getEmail());

        return ResponseEntity.ok(createdUser);
    }
}
