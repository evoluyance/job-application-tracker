package com.jobtracker.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequestDto {

    @NotBlank(message = "Username is required")
    @Pattern(regexp = "^[A-Z][a-z]{1,29}$", message = "Username must start with a capital letter and contain only letters")
    private String username;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Password is required")
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[A-Za-z\\d_-]{8,}$",
            message = "Password must be at least 8 characters long, contain an uppercase letter, lowercase letter, and a digit"
    )
    private String password;
}
