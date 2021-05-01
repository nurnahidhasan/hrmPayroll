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

import com.bracu.hrm.model.BaseEntity;
import com.bracu.hrm.model.Employee;
import com.bracu.hrm.model.util.HrYear;

@Entity
@Table(name = "employee_leave_balance")
public class EmployeeLeaveBalance extends BaseEntity {
		
	@ManyToOne(targetEntity=Employee.class,fetch=FetchType.LAZY,cascade= CascadeType.DETACH)
	@JoinColumn(name="empoyee_id")
	private Employee employee;
	
	@ManyToOne(targetEntity=Leave.class,fetch=FetchType.LAZY,cascade= CascadeType.DETACH)
	@JoinColumn(name="leave_id")
	private Leave leave; 
	
	private Double days;
	
	private String leaveName;	// default carry forward leave
	
}
