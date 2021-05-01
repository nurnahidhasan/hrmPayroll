package com.bracu.hrm.dao;

import com.bracu.hrm.model.leave.LeaveType;
import com.bracu.hrm.model.settings.EntityType;
import com.bracu.hrm.model.settings.SetupEntity;

import java.util.List;

/**
 * Created by HP on 1/14/2018.
 */
public interface LeaveTypeDao {
    LeaveType findById(int id);
    void save(LeaveType leaveType);
    void delete(int id);
    List<LeaveType> findAll();
    boolean uniqueNameTest(Integer id, String name);


}
