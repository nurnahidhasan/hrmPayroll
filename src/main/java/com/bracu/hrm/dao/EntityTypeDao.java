package com.bracu.hrm.dao;

import java.util.List;

import com.bracu.hrm.model.org.Company;
import com.bracu.hrm.model.org.Designation;
import com.bracu.hrm.model.settings.EntityType;

public interface EntityTypeDao {

	EntityType findById(int id);
	
	EntityType findByName(String name);
	
	void save(EntityType entityType);
	
	void delete(int id);
	
	List<EntityType> findAll();

}

