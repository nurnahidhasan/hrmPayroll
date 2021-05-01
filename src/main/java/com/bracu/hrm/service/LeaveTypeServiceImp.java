package com.bracu.hrm.service;

import com.bracu.hrm.dao.LeaveTypeDao;
import com.bracu.hrm.dbconfig.ReadOnlyConnection;
import com.bracu.hrm.model.leave.LeaveType;
import com.bracu.hrm.util.JSONUtil;
import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by HP on 1/14/2018.
 */
@Service
@Transactional
public class LeaveTypeServiceImp implements LeaveTypeService {
    @Autowired
    LeaveTypeDao leaveTypeDao;
    @Autowired
    MessageSource messageSource;
    @Override
    public void save(LeaveType leaveType, Principal principal) {
        leaveType.setDateCreate(new Date());
        leaveType.setVersion(0);
        leaveTypeDao.save(leaveType);

    }

    @Override
    public String delete(Integer id) {
        LeaveType leaveType= leaveTypeDao.findById(id);
        String resultJson = "";
        try {
            leaveTypeDao.delete(leaveType.getId());
            Gson gson = new Gson();
            String message = messageSource.getMessage("delete.success.message", new String[]{"Leave Type", leaveType.getName()}, Locale.getDefault());
            resultJson = gson.toJson(message);


        }catch (JsonIOException e){
            System.out.println(e.getMessage());
        }catch (RuntimeException e){
            System.out.println(e.getMessage());
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return resultJson;
    }
    @Override
    @ReadOnlyConnection
    @Transactional(readOnly = true)
    public String getLeaveTypeList() {
        List<LeaveType> list = leaveTypeDao.findAll();
        return JSONUtil.getJsonObject(list);
    }

    @Override
    @ReadOnlyConnection
    @Transactional(readOnly = true)
    public String getLeaveTypeById(int i) {
        LeaveType leaveType= leaveTypeDao.findById(i);
        return JSONUtil.getJsonObject(leaveType);
    }

    @Override
    @Transactional
    public String update(LeaveType leaveType) {
        LeaveType leaveType1=leaveTypeDao.findById(leaveType.getId());
        if(leaveType.getVersion()<leaveType1.getVersion()){
            String message=messageSource.getMessage("update.version.change", new String[]{"Leave Type", String.valueOf(leaveType.getVersion())}, Locale.getDefault());
            return message;
        }else{
            leaveType1.setName(leaveType.getName());
            leaveType.setVersion(leaveType.getVersion()+1);
            leaveTypeDao.save(leaveType1);
            leaveType.getId();
            String message = messageSource.getMessage("save.updated.message", new String[]{"Leave Type", leaveType.getName()}, Locale.getDefault());
            return message;
        }
    }

    @Override
    public boolean uniqueNameCheck(Integer id, String name) {
        return leaveTypeDao.uniqueNameTest(id,name);
    }

}