package com.bracu.hrm.service;

import com.bracu.hrm.model.org.Department;
import org.springframework.web.servlet.ModelAndView;

import java.sql.ResultSet;
import java.util.List;
import java.util.Map;


public interface DepartmentService {


    List<Department> findAll();

}