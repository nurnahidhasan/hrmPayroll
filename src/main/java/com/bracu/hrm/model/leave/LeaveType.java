package com.bracu.hrm.model.leave;

import com.bracu.hrm.model.BaseEntity;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "leave_type")
@Data
public class LeaveType extends BaseEntity {
	@Column(unique=true)
	private String name;
	/*public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}*/

}
