package com.jobtracker.service;

import com.jobtracker.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User create(User user);
    User findByEmail(String email);
    boolean existsByEmail(String email);
    void delete(long id);
}
