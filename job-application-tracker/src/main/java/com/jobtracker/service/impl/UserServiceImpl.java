package com.jobtracker.service.impl;

import com.jobtracker.dto.RegisterRequestDto;
import com.jobtracker.dto.UserRequestDto;
import com.jobtracker.dto.UserResponseDto;
import com.jobtracker.dto.UserDtoTransformer;
import com.jobtracker.model.User;
import com.jobtracker.repository.UserRepository;
import com.jobtracker.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserDtoTransformer transformer;

    @Override
    public UserResponseDto register(RegisterRequestDto dto) {
        log.info("Registering user with email: {}", dto.getEmail());
        User user = transformer.toEntity(dto);
        User savedUser = userRepository.save(user);
        return transformer.toDto(savedUser);
    }


    @Override
    public UserResponseDto findByEmail(String email) {
        log.info("Finding user by email: {}", email);
        User user = getEntityByEmail(email);
        return transformer.toDto(user);
    }

    @Override
    public boolean existsByEmail(String email) {
        log.debug("Checking if user exists with email: {}", email);
        return userRepository.existsByUserEmail(email);
    }

    @Override
    public void delete(long id) {
        log.warn("Deleting user with id: {}", id);
        userRepository.deleteById(id);
    }

    @Override
    @Transactional
    public UserResponseDto update(UserRequestDto dto, String email) {
        User user =getEntityByEmail(email);
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());
        return transformer.toDto(userRepository.save(user));
    }

    @Override
    public User getEntityByEmail(String email) {
        return userRepository.findByUserEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User with email " + email + " not found"));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.debug("Loading user by username (email): {}", username);
        return (UserDetails) getEntityByEmail(username);
    }

}
