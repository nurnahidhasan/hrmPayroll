package com.bracu.hrm.model.leave;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.bracu.hrm.model.Employee;
import com.bracu.hrm.model.util.HrYear;

@Entity
@Table(name = "employee_aquire_leave")
public class EmployeeAquireLeave {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	private Integer version;
	
	@ManyToOne(targetEntity=Employee.class,fetch=FetchType.LAZY,cascade= CascadeType.DETACH)
	@JoinColumn(name="empoyee_id")
	private Employee employee;
	
	@ManyToOne(targetEntity=HrYear.class,fetch=FetchType.LAZY,cascade= CascadeType.DETACH)
	@JoinColumn(name="hr_year_id")
	private HrYear hrYear; 
	
	@ManyToOne(targetEntity=Leave.class,fetch=FetchType.LAZY,cascade= CascadeType.DETACH)
	@JoinColumn(name="leave_id")
	private Leave leave; 
	
	private Integer days;
	
	
}
