package com.bracu.hrm.service;

import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

import com.bracu.hrm.model.Employee;



public interface EmployeeService {
	
	Employee findById(int id);
	
	Employee findByPin(String pin);
	
	void saveEmployee(Employee employee);
	
	void updateEmployee(Employee employee);
	
	void deleteEmployeeByPin(String pin);

	List<Employee> findAllEmployees(); 
	
	boolean isEmployeePinUnique(Integer id, String pin);

	Map getAllSetupEntity();

	ResultSet getSqlServerEmployee(String pin);
	Employee prepareNewEmployeeFromPaySlip(Object object);

}