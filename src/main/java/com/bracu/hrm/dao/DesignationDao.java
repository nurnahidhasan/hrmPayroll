package com.bracu.hrm.dao;

import java.util.List;

import com.bracu.hrm.model.org.Company;
import com.bracu.hrm.model.org.Designation;

public interface DesignationDao {

	Designation findById(int id);
	
	Designation findByName(String name);
	
	void save(Designation designation);
	
	void delete(int id);
	
	List<Designation> findAll();

}

