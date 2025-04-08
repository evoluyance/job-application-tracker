package com.jobtracker.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "job_request")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JobRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @NotBlank
    @Column(name= "name_job", nullable = false,length = 100)
    private String nameJob;

    @Column(name = "company_name", length = 100)
    private String companyName;

    @Column(name = "comment_of_job", columnDefinition = "TEXT")
    private String commentOfJob;

    @Column(name = "url_job", length = 500)
    private String urlJob;

    @Column(name = "url_resume", length = 500)
    private String urlResume;

    @Column(name = "data_job_request")
    private LocalDateTime dataJobRequest;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_review", nullable = false)
    private StatusReview statusReview;

}
