package com.bracu.hrm.dao;

import com.bracu.hrm.model.settings.EmployeeType;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository("employeeTypeDao")
@Transactional
public class EmployeeTypeDaoImpl extends AbstractDao<Integer, EmployeeType> implements EmployeeTypeDao {
    @Override
    public void save(EmployeeType employeeType) {
        super.persist(employeeType);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<EmployeeType> getEmployeeTypeList() {
        List<EmployeeType> employeeTypeList=null;
            Criteria criteria = createEntityCriteria();
             employeeTypeList = (List<EmployeeType>) criteria.list();

        return employeeTypeList;
    }

    @Override
    public EmployeeType getEmployeeTypeById(int id) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("id",id));
        EmployeeType employeeType = (EmployeeType) criteria.uniqueResult();
        return  employeeType;
    }

    @Override
    public void delete(int id) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("id",id));
        EmployeeType employeeType = (EmployeeType) criteria.uniqueResult();
        delete(employeeType);
    }

    @Override
    public boolean uniqueNameCheck(Integer id, String name) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("name",name).ignoreCase());
        if(criteria.list().size() == 0){
            return true;
            } else {
                return false;
        }
    }


}
