package com.bracu.hrm.model.payroll;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class PayDeductionHead {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	private Integer version;
	
	String name;
	String note;
	@Column(name = "rule_ref")
	String ruleRef;
	
}
