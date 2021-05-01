package com.bracu.hrm.dao;

import com.bracu.hrm.model.settings.JobLevel;

import java.util.List;

public interface JobLevelDao {

    void save(JobLevel jobLevel);

    List<JobLevel> getJobLevelList();

    JobLevel getJobLevelById(int i);

    void delete(int id);

    boolean uniqueNameCheck(Integer id, String name);
}
