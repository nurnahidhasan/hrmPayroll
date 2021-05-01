package com.bracu.hrm.model.util;

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.bracu.hrm.model.BaseEntity;
import com.bracu.hrm.model.org.Company;

@Entity
@Table(name = "holiday")
public class Holiday extends BaseEntity {
	
	@ManyToOne(targetEntity = HrYear.class, fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
	@JoinColumn(name = "hr_year_id")
	private HrYear hrYear;
	
	@Column(unique=true,nullable=false)
	@Temporal(TemporalType.DATE)
	private Date date;
	
	private String name;
	
	private String description;
	
	private Boolean isRepeated;		// if true then insert repeated values in multiple rows
	
	
}
