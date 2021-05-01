package com.bracu.hrm.dao;

import com.bracu.hrm.model.util.HrYear;

import java.util.List;

/**
 * Created by HP on 1/17/2018.
 */
public interface HrYearDao {
    void save(HrYear hrYear);
    List<HrYear> findAll();
    HrYear findById(int i);
    void delete(Integer id);
}
