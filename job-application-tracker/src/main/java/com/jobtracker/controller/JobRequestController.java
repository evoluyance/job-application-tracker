package com.jobtracker.controller;

import com.jobtracker.dto.JobRequestRequestDto;
import com.jobtracker.dto.JobRequestResponseDto;
import com.jobtracker.model.User;
import com.jobtracker.service.JobRequestService;
import com.jobtracker.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/job-requests")
@RequiredArgsConstructor
public class JobRequestController {

    private final JobRequestService jobRequestService;
    private final UserService userService;

    @PostMapping
    public ResponseEntity<JobRequestResponseDto> create(@RequestBody @Valid JobRequestRequestDto requestDto,
                                                        @AuthenticationPrincipal UserDetails userDetails) {
        User user = userService.getEntityByEmail(userDetails.getUsername());
        JobRequestResponseDto saved = jobRequestService.create(requestDto, user);
        return ResponseEntity.ok(saved);
    }

    @GetMapping
    public ResponseEntity<List<JobRequestResponseDto>> getAll(@AuthenticationPrincipal UserDetails userDetails) {
        User user = userService.getEntityByEmail(userDetails.getUsername());
        List<JobRequestResponseDto> result = jobRequestService.findAllByUser(user);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobRequestResponseDto> getJobRequestById(@PathVariable("id") Long id,
                                                                   @AuthenticationPrincipal UserDetails userDetails) {
        User user = userService.getEntityByEmail(userDetails.getUsername());
        return ResponseEntity.ok(jobRequestService.findById(id, user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id,
                                       @AuthenticationPrincipal UserDetails userDetails) {
        User user = userService.getEntityByEmail(userDetails.getUsername());
        jobRequestService.delete(id, user);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<JobRequestResponseDto> updateJobRequest(@PathVariable("id") Long id,
                                                                  @RequestBody @Valid JobRequestRequestDto requestDto,
                                                                  @AuthenticationPrincipal UserDetails userDetails) {
        User user = userService.getEntityByEmail(userDetails.getUsername());
        JobRequestResponseDto updated = jobRequestService.update(id, requestDto, user);
        return ResponseEntity.ok(updated);
    }
}
