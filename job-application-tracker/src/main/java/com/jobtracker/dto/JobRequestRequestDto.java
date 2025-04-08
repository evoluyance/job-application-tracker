package com.jobtracker.dto;

import com.jobtracker.model.StatusReview;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class JobRequestRequestDto {

    private long id;
    @NotBlank(message = "Job name is required")
    private String jobName;
    private String companyName;
    private String commentOfJob;
    private String urlJob;
    private String urlResume;

    @NotNull(message = "Data of job request is required")
    private LocalDateTime dataJobRequest;

    @NotNull(message = "Status review is required")
    private StatusReview statusReview;
}
