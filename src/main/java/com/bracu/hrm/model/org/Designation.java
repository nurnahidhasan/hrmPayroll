package com.bracu.hrm.model.org;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "designation")
public class Designation {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	private Integer version;
	
	@Column(unique = true, nullable = false)
	private String name;
	
	@Column(name = "short_name", unique = true)
	private String shortName;
	private Integer level;
	
	@Column(name = "functional_name", unique = true)
	private String functionalName;
	
	@Column(name = "functional_short_name", unique = true)
	private String functionalShortName;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public String getFunctionalName() {
		return functionalName;
	}

	public void setFunctionalName(String functionalName) {
		this.functionalName = functionalName;
	}

	public String getFunctionalShortName() {
		return functionalShortName;
	}

	public void setFunctionalShortName(String functionalShortName) {
		this.functionalShortName = functionalShortName;
	}
	
	
}
