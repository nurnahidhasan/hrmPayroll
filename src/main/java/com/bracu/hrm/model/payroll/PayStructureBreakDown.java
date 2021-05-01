package com.bracu.hrm.model.payroll;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "pay_structure_breakdown")
public class PayStructureBreakDown {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	private Integer version;
	
	private String payHead;
	private Double percentage;
	
	
	
}
