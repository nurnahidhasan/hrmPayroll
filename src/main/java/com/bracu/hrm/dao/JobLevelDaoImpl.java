package com.bracu.hrm.dao;

import com.bracu.hrm.model.settings.JobLevel;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository("jobLevelDao")
@Transactional
public class JobLevelDaoImpl extends AbstractDao<Integer, JobLevel> implements JobLevelDao {
    @Override
    public void save(JobLevel jobLevel) {
        try {
            super.persist(jobLevel);
        }catch (Exception e){
            System.out.println(e.getStackTrace());
            throw e;
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<JobLevel> getJobLevelList() {
        List<JobLevel> jobLevelList=null;
            Criteria criteria = createEntityCriteria();
             jobLevelList = (List<JobLevel>) criteria.list();
        return jobLevelList;
    }

    @Override
    public JobLevel getJobLevelById(int id) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("id",id));
        JobLevel jobLevel = (JobLevel) criteria.uniqueResult();
        return  jobLevel;
    }

    @Override
    public void delete(int id) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("id",id));
        JobLevel jobLevel = (JobLevel) criteria.uniqueResult();
        delete(jobLevel);
    }

    @Override
    public boolean uniqueNameCheck(Integer id, String name) {
        Criteria criteria = createEntityCriteria();
        if (id == null) {
            if (criteria.list().size() == 0) {
                return true;
            } else {
                return false;
            }
        } else {
            criteria.add(Restrictions.eq("id",id));
            criteria.add(Restrictions.like("Name",name).ignoreCase());
            if (criteria.list().size() == 0) {
                criteria.add(Restrictions.like("name", name).ignoreCase());
                if (criteria.list().size() == 0) {
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
