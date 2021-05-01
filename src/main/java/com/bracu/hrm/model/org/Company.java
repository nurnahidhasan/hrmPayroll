package com.bracu.hrm.model.org;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

import lombok.Data;

@Entity
@Table(name = "company")
@Data
public class Company {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	private Integer version;
	
	@NotEmpty
	@Column(name = "name", nullable = false,unique = true)
	private String name;
	@NotEmpty
	@Column(name = "short_name", nullable = false, unique = true)
	private String shortName;
	@NotEmpty
	@Column(name = "web_url", nullable = false)
	private String webUrl;

	@NotEmpty
	@Column(name = "email", nullable = false)
	private String email;
	
	@NotEmpty
	@Column(name = "phone1", nullable = false)
	private String phone1;

	private String phone2;
	private String fax;
	private String address;
	@Column(name = "company_logo")
	byte[] companyLogo;
	
}
