package com.bracu.hrm.dao;

import com.bracu.hrm.model.leave.LeaveType;
import com.bracu.hrm.model.settings.ApplicantAdmitCardInformation;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by HP on 1/14/2018.
 */
@Repository("applicantAdmitCardDao")
public class ApplicantAdmitCardDaoImpl extends AbstractDao<Integer, ApplicantAdmitCardInformation> implements ApplicantAdmitCardDao {

    static final Logger logger = LoggerFactory.getLogger(ApplicantAdmitCardDaoImpl.class);


    @Override
    public ApplicantAdmitCardInformation findById(int id) {
        Criteria criteria = createEntityCriteria();//.addOrder(Order.asc("column1"));
        criteria.add(Restrictions.eq("id", id));
        ApplicantAdmitCardInformation applicantAdmitCardInformation = (ApplicantAdmitCardInformation) criteria.uniqueResult();
        return applicantAdmitCardInformation;
    }

    @Override
    public void save(ApplicantAdmitCardInformation applicantAdmitCardInformation) {
        persist(applicantAdmitCardInformation);

    }

    @Override
    public List<ApplicantAdmitCardInformation> findAllByStatus(boolean issend) {
        Criteria criteria = createEntityCriteria();//.addOrder(Order.asc("column1"));
        criteria.add(Restrictions.eq("isSend", issend));
        List<ApplicantAdmitCardInformation> applicantAdmitCardInformationList = (List<ApplicantAdmitCardInformation>) criteria.list();
        return applicantAdmitCardInformationList;
    }





}
