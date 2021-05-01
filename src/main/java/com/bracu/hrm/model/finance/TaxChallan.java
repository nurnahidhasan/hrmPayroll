package com.bracu.hrm.model.finance;

import com.bracu.hrm.model.BaseEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by rana on 2/14/18.
 */
@Entity
@Data
@Table(name = "tax_challan")
public class TaxChallan extends BaseEntity {
    @Column(name = "party_id")
    private String partyId;
    @Column(name = "month_no")
    private Integer monthNo;
    @Column(name = "challan_no")
    private String challanNo;
    @Column(name = "challan_date")
    private String challanDate;
    @Column(name = "bank_name")
    private String bankName;
    @Column(name = "total_amount")
    private Double totalAmountInChallan;
    private String remarks;
}
