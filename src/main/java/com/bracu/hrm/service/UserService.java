package com.bracu.hrm.service;

import java.util.List;

import com.bracu.hrm.model.User;

public interface UserService {
    void save(User user);

    User findByUsername(String username);
    
    List<User>findAll();
}
