package com.bracu.hrm.dao;

import com.bracu.hrm.model.org.Company;

import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

public interface PaySlipDao {


	ResultSet findAll(Map params);
	ResultSet findAllPf(Map params);
	Void sendAdmitCard();
	ResultSet findAllId(Map params);
	List findAllStudent(Map params);
	List findByStudentId(Map params);
	List getRequisitionList();

}

