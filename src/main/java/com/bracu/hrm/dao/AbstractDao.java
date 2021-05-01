package com.bracu.hrm.dao;

import java.io.Serializable;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;

public abstract class AbstractDao<PK extends Serializable, T> {
	
	private final Class<T> persistentClass;
	
	@SuppressWarnings("unchecked")
	public AbstractDao(){
		this.persistentClass =(Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[1];
	}
	
	@Autowired
	private SessionFactory sessionFactory;

	protected Session getSession(){
		return sessionFactory.getCurrentSession();
	}

	@SuppressWarnings("unchecked")
	public T getByKey(PK key) {
		return getSession().get(persistentClass, key);
	}

	public void persist(T entity) {
		getSession().persist(entity);
	}

	public void update(T entity) {
		getSession().update(entity);
	}

	public void delete(T entity) {
		getSession().delete(entity);
	}
	
	protected Criteria createEntityCriteria(){
		return getSession().createCriteria(persistentClass);
	}

	@SuppressWarnings("unchecked")
	@Query(nativeQuery = true)
	public List executeSQL(String selectQuery){
		Session session = sessionFactory.getCurrentSession();
		List<Map<String,String>> result= session.createSQLQuery(selectQuery).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
		//List<Object[]> list = query.list();
		return  result;
	}
	public boolean uniqueNameTest(Integer id, String name) {
		Criteria crit = createEntityCriteria();
		if (id == null) {
			crit.add(Restrictions.eq("name",name).ignoreCase());
			if (crit.list().size() == 0) {
				return true;
			} else {
				return false;
			}
		} else {
			crit.add(Restrictions.eq("id", id));
			crit.add(Restrictions.like("name", name).ignoreCase());
			if (crit.list().size() == 0) {
				crit.add(Restrictions.like("name", name).ignoreCase());
				if (crit.list().size() == 0) {
					return true;
				} else {
					return false;
				}
			} else {
				return true;
			}
		}
	}

}
