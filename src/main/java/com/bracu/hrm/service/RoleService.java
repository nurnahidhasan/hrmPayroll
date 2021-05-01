package com.bracu.hrm.service;

import java.util.List;

import com.bracu.hrm.model.Role;
import com.bracu.hrm.model.User;

public interface RoleService {
    void save(Role role);
    
    List<Role>findAll();
}
