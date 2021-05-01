package com.bracu.hrm.model.configuration;

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

@Entity
@Table(name = "configuration_parameter")
public class ConfigurationParameter {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	private Integer version;
	
	
	@ManyToOne(targetEntity=Configuration.class,fetch=FetchType.LAZY,cascade= CascadeType.DETACH)
	@JoinColumn(name="configuration_id")
	private Configuration configuration;
	
	@Column(name ="parameter_name")
	private String parameterName;
	
	@Column(name ="parameter_value")
	private String parameterValue;
}
