package com.jobtracker.controller;

import com.jobtracker.dto.LoginRequestDto;
import com.jobtracker.dto.LoginResponseDto;
import com.jobtracker.security.JwtUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class LoginController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    /**
     * Login processing. If email/password is correct, JWT token is returned
     */
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody @Valid LoginRequestDto requestDto) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            requestDto.getEmail(),
                            requestDto.getPassword()
                    )
            );

            String token = jwtUtil.generateToken(requestDto.getEmail());

            log.info("User logged in: {}", requestDto.getEmail());

            return ResponseEntity.ok(new LoginResponseDto(token));

        } catch (AuthenticationException e) {
            log.warn("Login failed for user: {}", requestDto.getEmail());
            return ResponseEntity.status(401).build();
        }
    }
}
