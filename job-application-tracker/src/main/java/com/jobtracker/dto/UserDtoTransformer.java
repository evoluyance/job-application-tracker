package com.jobtracker.dto;

import com.jobtracker.model.User;

public class UserDtoTransformer {

    public static UserResponseDto toDto(User user) {
        UserResponseDto userDto = new UserResponseDto();
        userDto.setEmail(user.getUserEmail());
        userDto.setUserName(user.getUsername());
        return userDto;
    }

    public static User fromRegisterDto(RegisterRequestDto registerRequestDto) {
        User user = new User();
        user.setUsername(registerRequestDto.getUsername());
        user.setUserEmail(registerRequestDto.getEmail());
        user.setPassword(registerRequestDto.getPassword());
        return user;
    }

    public static void updateUser(User user, UserRequestDto userRequestDto) {
        user.setUsername(userRequestDto.getFirstName());
        user.setUserEmail(userRequestDto.getEmail());
        user.setPassword(userRequestDto.getPassword());
        if (userRequestDto.getPassword() != null && !userRequestDto.getPassword().isBlank()) {
            user.setPassword(userRequestDto.getPassword());
        }
    }
}
