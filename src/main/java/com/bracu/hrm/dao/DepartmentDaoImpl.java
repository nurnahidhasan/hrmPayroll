package com.bracu.hrm.dao;

import com.bracu.hrm.model.org.Department;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository("departmentDao")
public class DepartmentDaoImpl extends AbstractDao<Integer, Department> implements DepartmentDao {

    static final Logger logger = LoggerFactory.getLogger(DepartmentDaoImpl.class);



    @SuppressWarnings("unchecked")
    public List<Department> listAll() {
        Criteria criteria = createEntityCriteria();//.addOrder(Order.asc("column1"));
        //criteria.add(Restrictions.eq("entityType", entityType));
        //criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
        List<Department> setupEntityList = (List<Department>) criteria.list();

        return setupEntityList;
    }

    public Department findById(Integer id){
        Criteria criteria = createEntityCriteria();//.addOrder(Order.asc("column1"));
        criteria.add(Restrictions.eq("id", id));
        //criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
        Department setupEntityList = (Department) criteria.uniqueResult();
        return setupEntityList;
    }
}
