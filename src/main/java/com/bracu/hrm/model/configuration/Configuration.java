package com.bracu.hrm.model.configuration;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name ="configuration")
public class Configuration {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	private Integer version;
	
	@NotEmpty
	@Column(name = "name", nullable = false, unique = true)
	private String name;
	
	private String note;
	
	
}
