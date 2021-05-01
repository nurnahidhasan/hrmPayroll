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
import com.bracu.hrm.model.settings.EntityType;
import com.bracu.hrm.model.settings.SetupEntity;



@Repository("setupEntityDao")
public class SetupEntityDaoImpl extends AbstractDao<Integer, SetupEntity> implements SetupEntityDao {

	static final Logger logger = LoggerFactory.getLogger(SetupEntityDaoImpl.class);
	
	public SetupEntity findById(int id) {
		SetupEntity designation = getByKey(id);
		return designation;
	}

	public SetupEntity findByName(String name) {
		logger.info("NAME : {}", name);
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("column1", name));
		SetupEntity setupEntity = (SetupEntity)crit.uniqueResult();
		/*if(user!=null){
			Hibernate.initialize(user.getUserProfiles());
		}*/
		return setupEntity;
	}

	@SuppressWarnings("unchecked")
	public List<SetupEntity> findAll() {
		Criteria criteria = createEntityCriteria().addOrder(Order.asc("column1"));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
		List<SetupEntity> setupEntityList = (List<SetupEntity>) criteria.list();
		
		return setupEntityList;
	}

	@SuppressWarnings("unchecked")
	public List<SetupEntity> findAllByEntityType(EntityType entityType) {
		Criteria criteria = createEntityCriteria();//.addOrder(Order.asc("column1"));
		criteria.add(Restrictions.eq("entityType", entityType));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
		List<SetupEntity> setupEntityList = (List<SetupEntity>) criteria.list();
		
		return setupEntityList;
	}
	
	public void save(SetupEntity designation) {
		persist(designation);
	}

	public void delete(int id) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("id", id));
		SetupEntity setupEntity = (SetupEntity)crit.uniqueResult();
		delete(setupEntity);
	}

}
