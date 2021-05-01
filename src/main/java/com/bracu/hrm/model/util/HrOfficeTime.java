package com.bracu.hrm.model.util;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.bracu.hrm.model.BaseEntity;
import com.bracu.hrm.model.org.Company;

public class HrOfficeTime extends BaseEntity {
	
	@ManyToOne(targetEntity = Company.class, fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
	@JoinColumn(name = "company_id")
	private Company company;
	
	private String startTime24Format;
	private String endTime24Format;
	private String startTime12Format;
	private String endTIme12Format;
	private Boolean isRegularShift; 
	private Boolean isActive;
	
}
