package com.jobtracker.dto;

import com.jobtracker.model.JobRequest;
import com.jobtracker.model.User;
import org.springframework.stereotype.Component;

@Component
public class JobRequestDtoTransformer {

    public JobRequestResponseDto toDto(JobRequest jobRequest) {
        JobRequestResponseDto dto = new JobRequestResponseDto();
        dto.setJobId(jobRequest.getId());
        dto.setJobName(jobRequest.getNameJob());
        dto.setCompanyName(jobRequest.getCompanyName());
        dto.setCommentOfJob(jobRequest.getCommentOfJob());
        dto.setUrlJob(jobRequest.getUrlJob());
        dto.setUrlResume(jobRequest.getUrlResume());
        dto.setDataJobRequest(jobRequest.getDataJobRequest());
        dto.setStatusReview(jobRequest.getStatusReview());
        dto.setUsername(jobRequest.getUser().getUsername());
        return dto;
    }

    public static JobRequest toEntity(JobRequestRequestDto dto, User user) {
        JobRequest jobRequest = new JobRequest();
        jobRequest.setNameJob(dto.getJobName());
        jobRequest.setCompanyName(dto.getCompanyName());
        jobRequest.setCommentOfJob(dto.getCommentOfJob());
        jobRequest.setUrlJob(dto.getUrlJob());
        jobRequest.setUrlResume(dto.getUrlResume());
        jobRequest.setDataJobRequest(dto.getDataJobRequest());
        jobRequest.setStatusReview(dto.getStatusReview());
        jobRequest.setUser(user);
        return jobRequest;
    }
}
