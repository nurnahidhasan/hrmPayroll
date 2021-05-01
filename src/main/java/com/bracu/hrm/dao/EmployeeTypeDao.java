package com.bracu.hrm.dao;

import com.bracu.hrm.model.settings.EmployeeType;

import java.util.List;

public interface EmployeeTypeDao {

    void save(EmployeeType employeeType);

    List<EmployeeType> getEmployeeTypeList();

    EmployeeType getEmployeeTypeById(int i);

    void delete(int id);

    boolean uniqueNameCheck(Integer id, String name);
}
