package com.bracu.hrm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bracu.hrm.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
