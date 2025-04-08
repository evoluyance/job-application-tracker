package com.jobtracker.service.impl;

import com.jobtracker.model.User;
import com.jobtracker.repository.UserRepository;
import com.jobtracker.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User create(User user) {
    log.info("Creating user with email: " + user.getUserEmail());
    return userRepository.save(user);
    }

    @Override
    public User findByEmail(String email) {
        log.info("Finding user by email: " + email);
        return userRepository.findByUserEmail(email).orElseThrow(() -> new UsernameNotFoundException("User " +
                "with email:"+ email + " not found"));
    }

    @Override
    public boolean existsByEmail(String email) {
        log.debug("Checking existence of user with email: {}", email);
    return userRepository.existsByUserEmail(email);
    }

    @Override
    public void delete(long id){
        log.warn("Deleting user with id: " + id);
        userRepository.deleteById(id);

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.debug("Loading user by username (email): {}", username);
        return (UserDetails) findByEmail(username);
    }
}
