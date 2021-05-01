package com.bracu.hrm.model.payroll;
import javax.persistence.*;
import com.bracu.hrm.model.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;

@Entity
@Table(name = "tax_bank_payment")
public class TaxBankPayment extends BaseEntity {

    @Getter
    @Setter
    private String challanNumber;

    @DateTimeFormat(pattern = "MM-dd-yyyy")
    @Temporal(TemporalType.DATE)
    @Column(name="challan_date")
    @Getter
    @Setter
    private Date challanDate;

    @Getter
    @Setter
    private String bankName;

    @Getter
    @Setter
    private String totalAmount;

}
