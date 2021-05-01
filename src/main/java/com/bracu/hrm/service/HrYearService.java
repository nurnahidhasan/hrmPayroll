package com.bracu.hrm.service;

import com.bracu.hrm.model.util.HrYear;

/**
 * Created by HP on 1/17/2018.
 */
public interface HrYearService {
    void save(HrYear hrYear);

    String getlist();

    String getEntityById(int i);

}
