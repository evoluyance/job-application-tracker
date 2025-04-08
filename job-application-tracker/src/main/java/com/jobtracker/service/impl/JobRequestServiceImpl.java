package com.jobtracker.service.impl;

import com.jobtracker.dto.JobRequestRequestDto;
import com.jobtracker.dto.JobRequestResponseDto;
import com.jobtracker.dto.JobRequestDtoTransformer;
import com.jobtracker.model.JobRequest;
import com.jobtracker.model.User;
import com.jobtracker.repository.JobRequestRepository;
import com.jobtracker.service.JobRequestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class JobRequestServiceImpl implements JobRequestService {

    private final JobRequestRepository jobRequestRepository;
    private final JobRequestDtoTransformer transformer;

    @Override
    public JobRequestResponseDto create(JobRequestRequestDto requestDto, User user) {
        JobRequest jobRequest = transformer.toEntity(requestDto, user);
        JobRequest saved = jobRequestRepository.save(jobRequest);
        return transformer.toDto(saved);
    }

    @Override
    @Transactional
    public JobRequestResponseDto update(long id, JobRequestRequestDto dto, User user) {
        JobRequest jobRequest = findEntityByIdAndUser(id, user);

        jobRequest.setNameJob(dto.getJobName());
        jobRequest.setCompanyName(dto.getCompanyName());
        jobRequest.setCommentOfJob(dto.getCommentOfJob());
        jobRequest.setUrlJob(dto.getUrlJob());
        jobRequest.setUrlResume(dto.getUrlResume());
        jobRequest.setDataJobRequest(dto.getDataJobRequest());
        jobRequest.setStatusReview(dto.getStatusReview());

        return transformer.toDto(jobRequestRepository.save(jobRequest));
    }

    @Override
    public void delete(long id, User user) {
        JobRequest jobRequest = findEntityByIdAndUser(id, user);
        jobRequestRepository.delete(jobRequest);
    }

    @Override
    public JobRequestResponseDto findById(long id, User user) {
        return transformer.toDto(findEntityByIdAndUser(id, user));
    }

    @Override
    public List<JobRequestResponseDto> findAllByUser(User user) {
        return jobRequestRepository.findByUserId(user.getId())
                .stream()
                .map(transformer::toDto)
                .collect(Collectors.toList());
    }

    private JobRequest findEntityByIdAndUser(long id, User user) {
        return jobRequestRepository.findById(id)
                .filter(req -> req.getUser().getId().equals(user.getId()))
                .orElseThrow(() -> new RuntimeException("Job request not found or does not belong to the user"));
    }
}
