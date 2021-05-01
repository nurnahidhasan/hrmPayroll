package com.bracu.hrm.dao;

import java.util.List;

import com.bracu.hrm.model.org.Company;

public interface CompanyDao {

	Company findById(int id);
	
	Company findByName(String name);
	
	void save(Company company);
	
	void delete(int id);
	
	List<Company> findAll();

}

