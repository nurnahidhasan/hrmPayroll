package com.bracu.hrm.model.attendence;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.bracu.hrm.model.BaseEntity;
import com.bracu.hrm.model.Employee;

@Entity
@Table(name = "employee_attendance")
public class EmployeeAttandence extends BaseEntity {
	
	@ManyToOne(targetEntity=Employee.class,fetch=FetchType.LAZY,cascade= CascadeType.DETACH)
	@JoinColumn(name="empoyee_id")
	private Employee employee;
	
	 @Temporal(TemporalType.DATE)
	private Date date;
	
	private String startTime;
	private String endTIme;
	
	@ManyToOne(targetEntity=AttendanceStatus.class,fetch=FetchType.LAZY,cascade= CascadeType.DETACH)
	@JoinColumn(name="attendance_status_id")
	private AttendanceStatus attendanceStatus;
	
}
