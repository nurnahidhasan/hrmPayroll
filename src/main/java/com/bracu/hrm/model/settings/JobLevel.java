package com.bracu.hrm.model.settings;

import com.bracu.hrm.model.BaseEntity;
import com.bracu.hrm.model.org.Company;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "job_level")
@Data
public class JobLevel extends BaseEntity{

    @ManyToOne(targetEntity = Company.class, fetch = FetchType.LAZY,cascade = CascadeType.DETACH)
    @JoinColumn(name = "company_id",nullable = false)
    private Company company;

    private String level;

    @Column(name="int_level")
    private Integer intLevel;

    private String note;

}

