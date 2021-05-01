package com.bracu.hrm.dao;

import com.bracu.hrm.model.leave.LeaveType;
import com.bracu.hrm.model.settings.EntityType;
import com.bracu.hrm.model.settings.SetupEntity;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.repository.query.parser.Part;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by HP on 1/14/2018.
 */
@Repository("leaveTypeDao")
public class LeaveTypeDaoImp extends AbstractDao<Integer, LeaveType> implements LeaveTypeDao {

    static final Logger logger = LoggerFactory.getLogger(LeaveTypeDaoImp.class);


    @Override
    public LeaveType findById(int id) {
        Criteria criteria = createEntityCriteria();//.addOrder(Order.asc("column1"));
        criteria.add(Restrictions.eq("id", id));
        LeaveType leaveType = (LeaveType) criteria.uniqueResult();
        return leaveType;
    }

    @Override
    public void save(LeaveType leaveType) {
        persist(leaveType);

    }

    @Override
    public void delete(int id) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("id", id));
        LeaveType leaveType= (LeaveType) crit.uniqueResult();
        delete(leaveType);

    }

    @Override
    @SuppressWarnings("unchecked")
    public List<LeaveType> findAll() {
        Criteria criteria = createEntityCriteria();//.addOrder(Order.asc("column1"));
        List<LeaveType> leaveTypeList = (List<LeaveType>) criteria.list();
        return leaveTypeList;
    }



}
