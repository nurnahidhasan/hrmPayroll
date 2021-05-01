package com.bracu.hrm.dao;

import com.bracu.hrm.model.leave.LeaveType;
import com.bracu.hrm.model.util.HrYear;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by HP on 1/17/2018.
 */
@Repository("hrYearDao")
public class HrYearDaoImp extends AbstractDao<Integer, HrYear> implements HrYearDao {
    static final Logger logger = LoggerFactory.getLogger(LeaveTypeDaoImp.class);
    @Override
    public void save(HrYear hrYear) {
    persist(hrYear);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<HrYear> findAll() {
        Criteria criteria = createEntityCriteria();//.addOrder(Order.asc("column1"));
        List<HrYear> hrYearList= (List<HrYear>) criteria.list();
        return hrYearList;
    }

    @Override
    public HrYear findById(int id) {
        Criteria criteria = createEntityCriteria();//.addOrder(Order.asc("column1"));
        criteria.add(Restrictions.eq("id", id));
        HrYear hrYear1= (HrYear) criteria.uniqueResult();
        return hrYear1;

    }

    @Override
    public void delete(Integer id) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("id", id));
        HrYear hrYear= (HrYear) crit.uniqueResult();
        delete(hrYear);

    }
}
