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



@Repository("entityTypeDao")
public class EntityTypeDaoImpl extends AbstractDao<Integer, EntityType> implements EntityTypeDao {

	static final Logger logger = LoggerFactory.getLogger(EntityTypeDaoImpl.class);
	
	public EntityType findById(int id) {
		EntityType designation = getByKey(id);
		return designation;
	}

	public EntityType findByName(String name) {
		logger.info("NAME : {}", name);
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("name", name));
		EntityType entityType = (EntityType)crit.uniqueResult();
		/*if(user!=null){
			Hibernate.initialize(user.getUserProfiles());
		}*/
		return entityType;
	}

	@SuppressWarnings("unchecked")
	public List<EntityType> findAll() {
		Criteria criteria = createEntityCriteria().addOrder(Order.asc("name"));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
		List<EntityType> entityTypeList = (List<EntityType>) criteria.list();
		
		return entityTypeList;
	}

	public void save(EntityType entityType) {
		persist(entityType);
	}

	public void delete(int id) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("id", id));
		EntityType entityType = (EntityType)crit.uniqueResult();
		delete(entityType);
	}

}
