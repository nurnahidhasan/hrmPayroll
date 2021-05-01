package com.bracu.hrm.model;

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
@Table(name = "employee_nominee")
public class EmployeeNominee {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	private Integer version;
	
	@ManyToOne(targetEntity=Employee.class,fetch=FetchType.LAZY,cascade= CascadeType.DETACH)
	@JoinColumn(name="empoyee_id")
	private Employee employee;
	
	private String name;
	
	@Column(name="date_of_birth")
	private String dateOfBirth;
	
	@ManyToOne(targetEntity=SetupEntity.class,fetch=FetchType.LAZY,cascade= CascadeType.DETACH)
	@JoinColumn(name="gender_id")
	private SetupEntity gender;
	
	
	@ManyToOne(targetEntity=SetupEntity.class,fetch=FetchType.LAZY,cascade= CascadeType.DETACH)
	@JoinColumn(name="relation_id")
	private SetupEntity relation;

	private String nid;
	
	private String passport;
	
	@Column(name="present_address")
	private String presentAdress;
	
	@Column(name="permanent_address")
	private String permanentAdress;
	
	private Double percentage;
	
	private Byte photo;
	
}
