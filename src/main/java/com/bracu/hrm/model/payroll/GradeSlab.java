package com.bracu.hrm.model.payroll;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.bracu.hrm.model.settings.SetupEntity;

public class GradeSlab {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	private Integer version;
	
    String payPackage;
    //EmployeeLevel employeeLevel;
    
    @ManyToOne(targetEntity=PayGroup.class,fetch=FetchType.LAZY,cascade= CascadeType.DETACH)
   	@JoinColumn(name="pay_group_id")
    PayGroup payGroup;
    
    @ManyToOne(targetEntity=Slab.class,fetch=FetchType.LAZY,cascade= CascadeType.DETACH)
	@JoinColumn(name="slab_id")
    Slab slab;
    
    @Column(name ="date_from")
    Date dateFrom;
    
    @Column(name ="date_to")
    Date dateTo;
    @ManyToOne(targetEntity=SetupEntity.class,fetch=FetchType.LAZY,cascade= CascadeType.DETACH)
	@JoinColumn(name="currency_id")
	private SetupEntity currency;	//Key =CURRENCY
    
    @Column(name = "total_amount")
    Double totalAmount;
    
    @Column(name = "is_active")
    Boolean isActive;
    
}
