package com.bracu.hrm.service;

import com.bracu.hrm.model.settings.EmployeeType;

public interface EmployeeTypeService {

    void save(EmployeeType employeeType);

    String getEmployeeTypeList();

    String getEmployeeTypeById(int i);

    String update(EmployeeType employeeType);

    String delete(int i);

    boolean uniqueNameCheck(Integer id, String name);
}
