package com.bracu.hrm.service;

import java.util.List;

import com.bracu.hrm.model.Employee;
import com.bracu.hrm.model.org.Company;
import com.bracu.hrm.model.org.Designation;



public interface DesignationService {
	
	Company findById(int id);
	
	Company findByName(String name);
	
	void save(Designation designation);
	
	void update(Designation designation);
	
	void delete(int id);

	List<Company> findAll(); 
	
	boolean isUnique(Integer id, String name);

}