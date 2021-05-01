package com.bracu.hrm.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.bracu.hrm.model.org.Designation;
import com.bracu.hrm.model.settings.SetupEntity;

@Entity
@Table(name = "employee_designation")
public class EmployeeDesignation {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	private Integer version;
	
	@ManyToOne(targetEntity=Employee.class,fetch=FetchType.LAZY,cascade= CascadeType.DETACH)
	@JoinColumn(name="empoyee_id")
	private Employee employee;
	
	@ManyToOne(targetEntity = Designation.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "designation_id")
	private Designation designation;
	
	@Column(name = "start_date")
	private Date startDate;
	
	@Column(name = "end_date")
	private Date endDate;
	
	@Column(name = "is_active")
	private Boolean isActive;
	
	
	
}
