package com.bracu.hrm.service;

import com.bracu.hrm.model.settings.JobLevel;

public interface JobLevelService {

    void save(JobLevel jobLevel);

    String getJobLevelList();

    String getJobLevelById(int i);

    String update(JobLevel jobLevel);

    String delete(int i);

    boolean uniqueNameCheck(Integer id, String name);
}
