package com.bracu.hrm.model.settings;

import com.bracu.hrm.model.BaseEntity;
import com.bracu.hrm.model.org.Company;
import lombok.Data;

import javax.persistence.*;

//Employee type like Permanent, contractual etc

@Entity
@Table(name ="employee_type")
@Data
public class EmployeeType extends BaseEntity{

	@ManyToOne(targetEntity = Company.class, fetch = FetchType.LAZY,cascade = CascadeType.DETACH)
	@JoinColumn(name = "company_id",nullable = false)
	private Company company;
	@Column(unique=true)
	private String name;

	private String note;

}
