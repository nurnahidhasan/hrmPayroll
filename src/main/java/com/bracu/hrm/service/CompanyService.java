package com.bracu.hrm.service;

import java.util.List;

import com.bracu.hrm.model.Employee;
import com.bracu.hrm.model.org.Company;



public interface CompanyService {
	
	Company findById(int id);
	
	Company findByName(String name);
	
	void save(Company company);
	
	void update(Company company);
	
	void delete(int id);

	List<Company> findAll(); 
	
	boolean isUnique(Integer id, String name);

}