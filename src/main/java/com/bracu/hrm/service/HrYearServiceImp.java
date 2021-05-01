package com.bracu.hrm.service;

import com.bracu.hrm.dao.HrYearDao;
import com.bracu.hrm.dao.LeaveTypeDao;
import com.bracu.hrm.dbconfig.ReadOnlyConnection;
import com.bracu.hrm.model.leave.LeaveType;
import com.bracu.hrm.model.util.HrYear;
import com.bracu.hrm.util.DateUtil;
import com.bracu.hrm.util.JSONUtil;
import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by HP on 1/17/2018.
 */
@Service
@Transactional
public class HrYearServiceImp implements HrYearService {
    @Autowired
    HrYearDao hrYearDao;
    @Autowired
    MessageSource messageSource;
    @Override
    public void save(HrYear hrYear) {
        hrYear.setIsOpen(true);
        hrYear.setVersion(0);
        hrYear.setDateCreate(new Date());
        hrYearDao.save(hrYear);

    }

    @Override
    @ReadOnlyConnection
    @Transactional(readOnly = true)
    public String getlist() {
        List<HrYear> list = hrYearDao.findAll();
        return JSONUtil.getJsonObject(list);
    }

    @Override
    @ReadOnlyConnection
    @Transactional(readOnly = true)
    public String getEntityById(int i) {
        HrYear hrYear = hrYearDao.findById(i);
        return JSONUtil.getJsonObject(hrYear);
    }

}
