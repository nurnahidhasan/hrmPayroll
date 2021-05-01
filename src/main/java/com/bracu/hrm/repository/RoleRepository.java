package com.bracu.hrm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bracu.hrm.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{
}
