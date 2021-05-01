package com.bracu.hrm.model.payroll;

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

import org.hibernate.validator.constraints.NotEmpty;

import com.bracu.hrm.model.org.Designation;
import com.bracu.hrm.model.settings.EmployeeType;


@Entity
@Table(name = "pay_structure")
public class PayStructure {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	private Integer version;
	
	@ManyToOne(targetEntity=Designation.class,fetch=FetchType.LAZY,cascade= CascadeType.DETACH)
	@JoinColumn(name="designation_id")
	private Designation designation;
	
	
	@ManyToOne(targetEntity=Designation.class,fetch=FetchType.LAZY,cascade= CascadeType.DETACH)
	@JoinColumn(name="employee_type_id")
	private EmployeeType employeeType;
	
	
	@Column(name = "min_amount")
	private double minAmount;
	
	@Column(name = "max_amount")
	private double maxAmount;
	
}
