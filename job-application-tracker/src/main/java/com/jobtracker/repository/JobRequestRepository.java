package com.jobtracker.repository;

import com.jobtracker.model.JobRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobRequestRepository extends JpaRepository<JobRequest, Long> {
    List<JobRequest> findByUserId(Long userId);

    List<JobRequest> findByUserIdAndCompanyName(Long userId, String companyName);
}
