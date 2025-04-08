package com.jobtracker.service.impl;

import com.jobtracker.model.JobRequest;
import com.jobtracker.repository.JobRequestRepository;
import com.jobtracker.service.JobRequestService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class JobRequestServiceImpl implements JobRequestService {

    private final JobRequestRepository jobRequestRepository;

    @Override
    public JobRequest create(JobRequest jobRequest) {
        log.info("Creating new JobRequest for user: {}", jobRequest.getUser().getUserEmail());
        return jobRequestRepository.save(jobRequest);
    }

    @Override
    public JobRequest update(JobRequest jobRequest) {
        log.info("Updating JobRequest with ID: {}", jobRequest.getId());
        if (!jobRequestRepository.existsById(jobRequest.getId())) {
            log.warn("JobRequest with ID {} not found for update", jobRequest.getId());
            throw new EntityNotFoundException("JobRequest not found");
        }
        return jobRequestRepository.save(jobRequest);
    }

    @Override
    public void delete(long id) {
        log.info("Deleting JobRequest with ID: {}", id);
        if (!jobRequestRepository.existsById(id)) {
            log.warn("JobRequest with ID {} not found for deletion", id);
            throw new EntityNotFoundException("JobRequest not found");
        }
        jobRequestRepository.deleteById(id);
    }

    @Override
    public JobRequest findById(long id) {
        log.debug("Searching JobRequest by ID: {}", id);
        return jobRequestRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("JobRequest with ID {} not found", id);
                    return new EntityNotFoundException("JobRequest not found");
                });
    }

    @Override
    public List<JobRequest> findAll() {
        log.debug("Retrieving all JobRequests");
        return jobRequestRepository.findAll();
    }
}
