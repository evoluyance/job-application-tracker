package com.jobtracker.service;

import com.jobtracker.model.JobRequest;

import java.util.List;

public interface JobRequestService {
    JobRequest create(JobRequest jobRequest);
    JobRequest update(JobRequest jobRequest);
    void delete(long id);
    JobRequest findById(long id);
    List<JobRequest> findAll();
}
