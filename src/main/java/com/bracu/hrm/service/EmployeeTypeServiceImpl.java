package com.bracu.hrm.service;

import com.bracu.hrm.cache.CacheService;
import com.bracu.hrm.dao.EmployeeTypeDao;
import com.bracu.hrm.model.settings.EmployeeType;
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
public class EmployeeTypeServiceImpl implements EmployeeTypeService {

    @Autowired
    private EmployeeTypeDao employeeTypeDao;
    @Autowired
    private CacheService cacheService;
    @Autowired
    private MessageSource messageSource;

    @Override
    public void save(EmployeeType employeeType) {
        employeeType.setDateCreate(new Date());
        employeeType.setVersion(0);
        employeeType.setUserCreated(cacheService.getUser());
        employeeType.setCompany(cacheService.getCompnay());
        employeeTypeDao.save(employeeType);
    }

    @Override
    public String getEmployeeTypeList() {
        List<EmployeeType> list = employeeTypeDao.getEmployeeTypeList();
        return JSONUtil.getJsonObject(list);
    }

    @Override
    public String getEmployeeTypeById(int i) {
        EmployeeType employeeType = employeeTypeDao.getEmployeeTypeById(i);
        return JSONUtil.getJsonObject(employeeType);
    }

    @Override
    @Transactional
    public String update(EmployeeType employeeType) {
        EmployeeType currentEmptype = employeeTypeDao.getEmployeeTypeById(employeeType.getId());
        if (employeeType.getVersion() < currentEmptype.getVersion()) {
            String message = messageSource.getMessage("update.version.change", new String[]{"EmployeeType Information", String.valueOf(employeeType.getVersion())}, Locale.getDefault());
            return message;
        } else {
            currentEmptype.setName(employeeType.getName());
            currentEmptype.setNote(employeeType.getNote());
            currentEmptype.setDateLastUpdate(new Date());
            currentEmptype.setVersion(employeeType.getVersion()+1);
            currentEmptype.setUserLastUpdated(cacheService.getUser());
            employeeTypeDao.save(currentEmptype);
            String message = messageSource.getMessage("save.updated.message", new String[]{"Employee Type Information", employeeType.getName()}, Locale.getDefault());
            return message;
        }
    }

    @Override
    public String delete(int id) {
        EmployeeType employeeType = employeeTypeDao.getEmployeeTypeById(id);
        String resultJson = "";
//        try {
            employeeTypeDao.delete(employeeType.getId());
            Gson gson = new Gson();
            String message = messageSource.getMessage("delete.success.message", new String[]{"Employee Type Information", employeeType.getName()}, Locale.getDefault());
            resultJson = gson.toJson(message);


//        }catch (JsonIOException e){
//            System.out.println(e.getMessage());
//        }catch (RuntimeException e){
//            System.out.println(e.getMessage());
//        }catch (Exception e){
//            System.out.println(e.getMessage());
//        }
        return resultJson;
    }

    @Override
    public boolean uniqueNameCheck(Integer id, String name) {
        return  employeeTypeDao.uniqueNameCheck(id,name);
    }
}
