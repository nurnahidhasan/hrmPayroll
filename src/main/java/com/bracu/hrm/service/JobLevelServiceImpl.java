package com.bracu.hrm.service;

import com.bracu.hrm.cache.CacheService;
import com.bracu.hrm.dao.JobLevelDao;
import com.bracu.hrm.model.settings.JobLevel;
import com.bracu.hrm.util.JSONUtil;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Locale;

@Service
public class JobLevelServiceImpl implements JobLevelService {

    @Autowired
    private JobLevelDao jobLevelDao;

    @Autowired
    private CacheService cacheService;

    @Autowired
    private MessageSource messageSource;

    @Override
    public void save(JobLevel jobLevel) {
        jobLevel.setDateCreate(new Date());
        jobLevel.setVersion(0);
        jobLevel.setCompany(cacheService.getCompnay());
        jobLevel.setUserCreated(cacheService.getUser());
        jobLevelDao.save(jobLevel);
    }

    @Override
    public String getJobLevelList() {
        List<JobLevel> list = jobLevelDao.getJobLevelList();
        return JSONUtil.getJsonObject(list);
    }

    @Override
    public String getJobLevelById(int i) {
        JobLevel jobLevel = jobLevelDao.getJobLevelById(i);
        return JSONUtil.getJsonObject(jobLevel);
    }

    @Override
    @Transactional
    public String update(JobLevel jobLevel) {
        JobLevel currentJobLevel = jobLevelDao.getJobLevelById(jobLevel.getId());
        if (jobLevel.getVersion() < currentJobLevel.getVersion()) {
            String message = messageSource.getMessage("update.version.change", new String[]{"Job Level Information", String.valueOf(jobLevel.getVersion())}, Locale.getDefault());
            return message;
        } else {
            currentJobLevel.setVersion(jobLevel.getVersion()+1);
            currentJobLevel.setNote(jobLevel.getNote());
            currentJobLevel.setUserLastUpdated(cacheService.getUser());
            currentJobLevel.setLevel(jobLevel.getLevel());
            currentJobLevel.setIntLevel(jobLevel.getIntLevel());
            currentJobLevel.setDateLastUpdate(new Date());

            jobLevelDao.save(currentJobLevel);

            String message = messageSource.getMessage("save.updated.message", new String[]{"Job Level Information", jobLevel.getLevel()}, Locale.getDefault());
            return message;
        }
    }

    @Override
    public String delete(int id) {
        JobLevel jobLevel = jobLevelDao.getJobLevelById(id);
        String resultJson = "";
        jobLevelDao.delete(jobLevel.getId());
        Gson gson = new Gson();
        String message = messageSource.getMessage("delete.success.message", new String[]{"Job Level Information", jobLevel.getLevel()}, Locale.getDefault());
        resultJson = gson.toJson(message);

        return resultJson;
    }

    @Override
    public boolean uniqueNameCheck(Integer id, String name) {
        return jobLevelDao.uniqueNameCheck(id, name);
    }
}
