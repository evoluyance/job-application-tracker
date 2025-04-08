package com.jobtracker.controller;

import com.jobtracker.dto.UserRequestDto;
import com.jobtracker.dto.UserResponseDto;
import com.jobtracker.model.User;
import com.jobtracker.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    /**
     * Get the currently logged user
     */
    @GetMapping("/me")
    public ResponseEntity<UserResponseDto> getCurrentUser(@AuthenticationPrincipal UserDetails userDetails) {
        UserResponseDto responseDto = userService.findByEmail(userDetails.getUsername());
        return ResponseEntity.ok(responseDto);
    }

    /**
     * update the currently logger user
     */
    @PutMapping("/update")
    public ResponseEntity<UserResponseDto> updateUser(@RequestBody @Valid UserRequestDto userRequestDto,
                                                      @AuthenticationPrincipal UserDetails userDetails) {
        UserResponseDto updated = userService.update(userRequestDto, userDetails.getUsername());
        return ResponseEntity.ok(updated);
    }

    /**
     * delete the currently logger user
     */
    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteAccount(@AuthenticationPrincipal UserDetails userDetails) {
        User user = userService.getEntityByEmail(userDetails.getUsername());
        userService.delete(user.getId());
        return ResponseEntity.noContent().build();
    }
}
