package com.jobtracker.dto;

import com.jobtracker.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserDtoTransformer {

    public UserResponseDto toDto(User user) {
        UserResponseDto userDto = new UserResponseDto();
        userDto.setEmail(user.getUserEmail());
        userDto.setUserName(user.getUsername());
        return userDto;
    }

    public User toEntity(RegisterRequestDto registerRequestDto) {
        User user = new User();
        user.setUsername(registerRequestDto.getUsername());
        user.setUserEmail(registerRequestDto.getEmail());
        user.setPassword(registerRequestDto.getPassword());
        return user;
    }

    public User toEntity(UserRequestDto dto) {
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setUserEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        return user;
    }

    public void updateUser(User user, UserRequestDto dto) {
        user.setUsername(dto.getUsername());
        user.setUserEmail(dto.getEmail());

        if (dto.getPassword() != null && !dto.getPassword().isBlank()) {
            user.setPassword(dto.getPassword());
        }
    }
}
