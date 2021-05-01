package com.bracu.hrm.model.payroll;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.bracu.hrm.model.settings.SetupEntity;

public class GradeSlabDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	private Integer version;
	
	  @ManyToOne(targetEntity=GradeSlab.class,fetch=FetchType.LAZY,cascade= CascadeType.DETACH)
		@JoinColumn(name="grade_slab_id")
	 	GradeSlab gradeSlab;
	  
	  @ManyToOne(targetEntity=PayCode.class,fetch=FetchType.LAZY,cascade= CascadeType.DETACH)
		@JoinColumn(name="pay_code_id")
	    PayCode payCode;
	  
	    Double amount;
	    Double percentage;
}
