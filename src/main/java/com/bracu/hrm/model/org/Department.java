package com.bracu.hrm.model.org;

import com.bracu.hrm.model.BaseEntity;
import lombok.Data;

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
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "department",uniqueConstraints = { @UniqueConstraint( columnNames = {"company_id", "code"}),
		@UniqueConstraint( columnNames = {"company_id", "name"}),@UniqueConstraint( columnNames = {"company_id", "short_name"}) })
@Data
public class Department extends BaseEntity {

	@ManyToOne(targetEntity = Company.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "company_id" )
	private Company company;
	
	@Column(nullable =  false)
	private String code;
	
	@Column(nullable =  false)
	private String name;
	
	@Column(name = "short_name")
	private String shortName;
	
	
	@ManyToOne(targetEntity = Department.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name =  "parent_department_id",nullable = true)
	private Department department;
	
	
}
