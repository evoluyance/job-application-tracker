package com.jobtracker.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Entity
@Table(name="users")
@Getter
@Setter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Pattern(regexp = "[A-Z][a-z]+([-'][A-Z][a-z]+)*", message ="username must be starting of the capital letter and" +
            "contain only letter " )
    @Column(nullable = false, length = 50)
    private String username;

    @NotBlank
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*()\\-_=+\\[\\]{}|;:'\",.<>?/`~\\\\]).{8,}$",
            message = "Password must be at least 8 characters long and " +
                    "contain at least one letter, one number, and " +
                    "can include special characters"
    )
    @JsonIgnore
    @Column(nullable = false)
    private String password;

    @NotBlank(message = "Must be a valid e-mail address")
    @Email(message = "Email має бути валідним")
    @Column(unique = true, nullable = false)
    private String userEmail;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<JobRequest> userJobRequestList;

}