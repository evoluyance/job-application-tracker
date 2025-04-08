package com.jobtracker.repository;

import com.jobtracker.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUserEmail(String email);

    boolean existsByUserEmail(String email);
}
