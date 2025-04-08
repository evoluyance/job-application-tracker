package com.jobtracker.service;

import com.jobtracker.dto.JobRequestRequestDto;
import com.jobtracker.dto.JobRequestResponseDto;
import com.jobtracker.model.User;

import java.util.List;

public interface JobRequestService {

    JobRequestResponseDto create(JobRequestRequestDto requestDto, User user);

    JobRequestResponseDto update(long id, JobRequestRequestDto dto, User user);

    void delete(long id, User user);

    JobRequestResponseDto findById(long id, User user);

    List<JobRequestResponseDto> findAllByUser(User user);
}
