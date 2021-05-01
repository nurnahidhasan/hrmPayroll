package com.bracu.hrm.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.bracu.hrm.model.Employee;
import com.bracu.hrm.model.User;
import com.bracu.hrm.model.org.Company;



@Repository("companyDao")
public class CompanyDaoImpl extends AbstractDao<Integer, Company> implements CompanyDao {

	static final Logger logger = LoggerFactory.getLogger(CompanyDaoImpl.class);
	
	public Company findById(int id) {
		Company company = getByKey(id);
		return company;
	}

	public Company findByName(String name) {
		logger.info("NAME : {}", name);
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("name", name));
		Company company = (Company)crit.uniqueResult();
		/*if(user!=null){
			Hibernate.initialize(user.getUserProfiles());
		}*/
		return company;
	}

	@SuppressWarnings("unchecked")
	public List<Company> findAll() {
		Criteria criteria = createEntityCriteria().addOrder(Order.asc("name"));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
		List<Company> companyList = (List<Company>) criteria.list();
		
		return companyList;
	}

	public void save(Company company) {
		persist(company);
	}

	public void delete(int id) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("id", id));
		Company company = (Company)crit.uniqueResult();
		delete(company);
	}

}
