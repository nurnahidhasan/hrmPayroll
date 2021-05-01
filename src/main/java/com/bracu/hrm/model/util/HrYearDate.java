package com.bracu.hrm.model.util;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.bracu.hrm.model.BaseEntity;
import com.bracu.hrm.model.org.Company;


/****
 * 
 * @author rana
 *This domain data entry will be done with date entry of hrYear;
 *before create this data must have value in HrOfficeTime regular shift data
 *
 */
public class HrYearDate extends BaseEntity {
	
	@ManyToOne(targetEntity = HrYear.class, fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
	@JoinColumn(name = "hr_year_id")
	private HrYear hrYear;
	
	@Temporal(TemporalType.DATE)
	private Date   officeDate;
	private String startTime24Format;	//this value will come from HrOfficeTime entity
	private String endTime24Format;		//this value will come from HrOfficeTime entity
	private String status;
	private String comments;
	

}
