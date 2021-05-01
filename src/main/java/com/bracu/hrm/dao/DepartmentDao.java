package com.bracu.hrm.dao;

import com.bracu.hrm.model.org.Department;

import java.util.List;

public interface DepartmentDao {

    List<Department> listAll();
    Department findById(Integer id);
}


