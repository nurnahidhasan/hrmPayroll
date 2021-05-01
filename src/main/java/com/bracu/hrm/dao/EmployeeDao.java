package com.bracu.hrm.dao;

import java.sql.ResultSet;
import java.util.List;

import com.bracu.hrm.model.Employee;



public interface EmployeeDao {

	Employee findById(int id);
	
	Employee findByPin(String pin);
	
	void save(Employee employee);
	
	void deleteByPin(String pin);
	
	List<Employee> findAllEmployees();

	ResultSet getSqlServerEmployee(String pin);

}

