package com.bracu.hrm.model.util;

import com.bracu.hrm.model.BaseEntity;
import com.bracu.hrm.model.org.Company;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

import javax.persistence.*;


@Entity
@Table(name = "hr_year")
@Setter
@Getter
public class HrYear extends BaseEntity {
	
	@ManyToOne(targetEntity = Company.class, fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
	@JoinColumn(name = "company_id")
	private Company company;
	private String name;
	@DateTimeFormat(pattern = "MM-dd-yyyy")
	@Temporal(TemporalType.DATE)
	@Column(name = "date_from", nullable = false)
	private Date dateFrom;
	@DateTimeFormat(pattern = "MM-dd-yyyy")
	@Temporal(TemporalType.DATE)
	@Column(name = "date_to", nullable = false)
	private Date dateTo;
	private Boolean isOpen;

	public HrYear() {
	}

	public HrYear(Date dateFrom, Date dateTo, Boolean isOpen) {
		this.dateFrom = dateFrom;
		this.dateTo = dateTo;
		this.isOpen = isOpen;
	}
}
