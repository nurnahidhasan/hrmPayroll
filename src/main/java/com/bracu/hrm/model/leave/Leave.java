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
import com.bracu.hrm.model.util.HrYear;

@Entity
@Table(name = "hr_leave")
public class Leave extends BaseEntity {
	
	@ManyToOne(targetEntity=HrYear.class,fetch=FetchType.LAZY,cascade= CascadeType.DETACH)
	@JoinColumn(name="hr_year_id")
	private HrYear hrYear; 
	
	private Integer days;
	
	private Boolean isForwardable;
	
	private Integer maxFoward;
	
	
}
