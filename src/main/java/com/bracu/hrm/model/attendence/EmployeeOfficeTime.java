package com.bracu.hrm.model.attendence;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.bracu.hrm.model.BaseEntity;
import com.bracu.hrm.model.Employee;
import com.bracu.hrm.model.util.HrYear;

/****
 * 
 * @author rana
 *
 *
 *Those employees who are not in regular shift, this table only contain this employees data
 *
 */
public class EmployeeOfficeTime extends BaseEntity {
	
	private HrYear hrYear;
	
	@ManyToOne(targetEntity=Employee.class,fetch=FetchType.LAZY,cascade= CascadeType.DETACH)
	@JoinColumn(name="empoyee_id")
	private Employee employee;
	
	@Temporal(TemporalType.DATE)
	private Date   officeDate;
	
	private Boolean isFlexibleTime;
	private Integer officeHourPerDay;	// this field is valied if flexible office timing
	private String  startTime24Format;
	private String endTime24Format;
}
