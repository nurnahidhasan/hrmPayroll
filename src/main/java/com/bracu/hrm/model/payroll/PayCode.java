package com.bracu.hrm.model.payroll;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "pay_code")
public class PayCode {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	private Integer version;
	
	
	    String code;
	    String name;
	    String description;
	    @Column(name ="is_active")
	    Boolean isActive;
}
