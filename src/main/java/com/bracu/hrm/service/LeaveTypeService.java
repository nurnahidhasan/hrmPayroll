package com.bracu.hrm.service;

import com.bracu.hrm.model.leave.LeaveType;

import java.security.Principal;

/**
 * Created by HP on 1/14/2018.
 */
public interface LeaveTypeService {
    void save(LeaveType leaveType, Principal principal);
    String delete(Integer id);
    String getLeaveTypeList();
    String getLeaveTypeById(int i);
    String update(LeaveType leaveType);
    boolean uniqueNameCheck(Integer id, String name);
}
