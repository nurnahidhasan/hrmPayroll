package com.bracu.hrm.dao;

import com.bracu.hrm.model.leave.LeaveType;
import com.bracu.hrm.model.settings.ApplicantAdmitCardInformation;

import java.util.List;

/**
 * Created by HP on 1/14/2018.
 */
public interface ApplicantAdmitCardDao {
    ApplicantAdmitCardInformation findById(int id);
    List<ApplicantAdmitCardInformation> findAllByStatus(boolean isSend);
    void save(ApplicantAdmitCardInformation applicantAdmitCardInformation);


}
