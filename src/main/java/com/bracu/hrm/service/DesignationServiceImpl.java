package com.bracu.hrm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bracu.hrm.dao.CompanyDao;
import com.bracu.hrm.dao.EmployeeDao;
import com.bracu.hrm.model.Employee;
import com.bracu.hrm.model.org.Company;


@Service("designationService")
@Transactional
public class DesignationServiceImpl implements CompanyService {

	@Autowired
	private CompanyDao companyDao;
	
	public Company findById(int id) {
		return companyDao.findById(id);
	}

	public Company findByName(String name) {
		Company company = companyDao.findByName(name);
		return company;
	}

	public void save(Company company) {
		companyDao.save(company);
	}

	public void update(Company company) {
		Company entity = companyDao.findById(company.getId());
		if(entity!=null){
			entity.setVersion(company.getVersion()+1);
			entity.setName(company.getName());
			entity.setShortName(company.getShortName());
			entity.setEmail(company.getEmail());
			entity.setWebUrl(company.getWebUrl());
			entity.setEmail(company.getEmail());
			entity.setPhone1(company.getPhone1());
			entity.setPhone2(company.getPhone2());
			entity.setFax(company.getFax());
			entity.setAddress(company.getAddress());
			entity.setCompanyLogo(company.getCompanyLogo());
		}
	}

	
	public void delete(int id) {
		companyDao.delete(id);
	}

	public List<Company> findAll() {
		return companyDao.findAll();
	}

	public boolean isUnique(Integer id, String name) {
		Company user = findByName(name);
		return ( user == null || ((id != null) && (user.getId() == id)));
	}


	
}
