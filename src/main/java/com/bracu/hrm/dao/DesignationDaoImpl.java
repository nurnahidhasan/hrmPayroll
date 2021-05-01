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
import com.bracu.hrm.model.org.Designation;



@Repository("designationDao")
public class DesignationDaoImpl extends AbstractDao<Integer, Designation> implements DesignationDao {

	static final Logger logger = LoggerFactory.getLogger(DesignationDaoImpl.class);
	
	public Designation findById(int id) {
		Designation designation = getByKey(id);
		return designation;
	}

	public Designation findByName(String name) {
		logger.info("NAME : {}", name);
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("name", name));
		Designation designation = (Designation)crit.uniqueResult();
		/*if(user!=null){
			Hibernate.initialize(user.getUserProfiles());
		}*/
		return designation;
	}

	@SuppressWarnings("unchecked")
	public List<Designation> findAll() {
		Criteria criteria = createEntityCriteria().addOrder(Order.asc("name"));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
		List<Designation> designationList = (List<Designation>) criteria.list();
		
		return designationList;
	}

	public void save(Designation designation) {
		persist(designation);
	}

	public void delete(int id) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("id", id));
		Designation designation = (Designation)crit.uniqueResult();
		delete(designation);
	}

}
