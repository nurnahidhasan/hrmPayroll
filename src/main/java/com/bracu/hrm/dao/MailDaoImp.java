package com.bracu.hrm.dao;

import com.bracu.hrm.model.email.Mail;
import com.bracu.hrm.model.leave.LeaveType;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by HP on 1/14/2018.
 */
@Repository("mailDao")
public class MailDaoImp extends AbstractDao<Integer, Mail> implements MailDao {

    static final Logger logger = LoggerFactory.getLogger(MailDaoImp.class);



    @Override
    public void save(Mail mail) {
        persist(mail);

    }


}
