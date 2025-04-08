package com.jobtracker.dto;

import com.jobtracker.model.StatusReview;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class JobRequestResponseDto {

    private long jobId;
    private String jobName;
    private String companyName;
    private String commentOfJob;
    private String urlJob;
    private String urlResume;
    private LocalDateTime dataJobRequest;
    private StatusReview statusReview;
    private String username;
}
