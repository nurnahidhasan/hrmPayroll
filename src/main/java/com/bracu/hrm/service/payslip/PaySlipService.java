package com.bracu.hrm.service.payslip;

import com.bracu.hrm.model.org.Company;
import org.springframework.web.servlet.ModelAndView;

import java.sql.ResultSet;
import java.util.List;
import java.util.Map;


public interface PaySlipService {
	

	ResultSet findAll(Map params);
	ResultSet findAllPf(Map params);
	ResultSet findAllId(Map params);
	List findAllStudent(Map params);
	List findByStudentId(Map params);
	ModelAndView generatePaySlip(Map params);
	ModelAndView sendGsuitData(Map params);
	public String getMailList();
	public String sendAdmitCard();
	ModelAndView generatePfSlip(Map params);
	ModelAndView generateEmployeeId(Map params);

}