package com.bracu.hrm.model.settings;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "entity_type")
public class EntityType {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
	
	private Integer version;
	
	private String name;
	
	private String column1; // this will be clolumn caption
	private String column2;
	private String column3;
	@Column(name = "is_column3_large_text")
	private Boolean isColumn3LargeText;
	private String column4;
	@Column(name = "is_column4_large_text")
	private Boolean isColumn4LargeText;
	
	@OneToMany(mappedBy ="entityType", cascade= CascadeType.DETACH)
	private Set<SetupEntity> setupEntities;

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

	public String getColumn1() {
		return column1;
	}

	public void setColumn1(String column1) {
		this.column1 = column1;
	}

	public String getColumn2() {
		return column2;
	}

	public void setColumn2(String column2) {
		this.column2 = column2;
	}

	public String getColumn3() {
		return column3;
	}

	public void setColumn3(String column3) {
		this.column3 = column3;
	}

	public Boolean getIsColumn3LargeText() {
		return isColumn3LargeText;
	}

	public void setIsColumn3LargeText(Boolean isColumn3LargeText) {
		this.isColumn3LargeText = isColumn3LargeText;
	}

	public String getColumn4() {
		return column4;
	}

	public void setColumn4(String column4) {
		this.column4 = column4;
	}

	public Boolean getIsColumn4LargeText() {
		return isColumn4LargeText;
	}

	public void setIsColumn4LargeText(Boolean isColumn4LargeText) {
		this.isColumn4LargeText = isColumn4LargeText;
	}

	public Set<SetupEntity> getSetupEntities() {
		return setupEntities;
	}

	public void setSetupEntities(Set<SetupEntity> setupEntities) {
		this.setupEntities = setupEntities;
	}
	
	
}
