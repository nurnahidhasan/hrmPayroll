package com.bracu.hrm.service;


import com.bracu.hrm.model.Role;
import com.bracu.hrm.repository.RoleRepository;
import com.bracu.hrm.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void save(Role role) {
    		roleRepository.save(role);
    }


	@Override
	public List<Role> findAll() {
		return roleRepository.findAll();
	}
    
    
}
