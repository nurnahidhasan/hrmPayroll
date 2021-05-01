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

import com.bracu.hrm.model.settings.SetupEntity;

@Entity
@Table(name = "employee_experience")
public class EmployeeExperience {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	private Integer version;
	
	@ManyToOne(targetEntity=Employee.class,fetch=FetchType.LAZY,cascade= CascadeType.DETACH)
	@JoinColumn(name ="empoyee_id")
	private Employee employee;
	
	@Column(name="job_title")
	private String jobTitle;
	
	@Column(name="job_role")
	private String jobRole; // job responsibility
	
	@Column(name="company_name")
	private String companyName;
	
	@Column(name="company_address")
	private String companyAddress;
	
	@Column(name="company_phone")
	private String companyPhone;
	
	
	@Column(name="company_email")
	private String companyEmail;
	
	@Column(name="company_fax")
	private String companyFax;
	
	
	@Column(name="position_hold")
	private String positionHold;
	
	@Column(name="date_from")
	private Date dateFrom;
	
	@Column(name="date_to")
	private Date dateTo;
	
	
	@Column(name="reasion_leave")
	private String reasonForLeave;
	
	
	private Byte certificate;
	
}
