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

/***
 * 
 * @author rana
 *
 *This table contain daily punch in and punch out histry of an employee;
 */
public class EmployeeDailyAttendenceHistry extends BaseEntity {

	@ManyToOne(targetEntity=Employee.class,fetch=FetchType.LAZY,cascade= CascadeType.DETACH)
	@JoinColumn(name="empoyee_id")
	private Employee employee;
	
	@Temporal(TemporalType.DATE)
	private Date dateAttendance;
	private String inTime;
	private String outTime;
	
}
