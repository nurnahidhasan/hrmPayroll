package com.bracu.hrm.dao;

import java.util.List;

import com.bracu.hrm.model.org.Company;
import com.bracu.hrm.model.org.Designation;
import com.bracu.hrm.model.settings.EntityType;
import com.bracu.hrm.model.settings.SetupEntity;

public interface SetupEntityDao {

	SetupEntity findById(int id);
	
	SetupEntity findByName(String name);
	
	void save(SetupEntity setupEntity);
	
	void delete(int id);
	
	List<SetupEntity> findAll();

	List findAllByEntityType(EntityType entityType);

}

