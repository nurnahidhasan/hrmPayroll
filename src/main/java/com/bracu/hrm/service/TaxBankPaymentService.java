package com.bracu.hrm.service;

import com.bracu.hrm.model.payroll.TaxBankPayment;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

/**
 * Created by rana on 3/11/18.
 */
public interface TaxBankPaymentService {
    String save(TaxBankPayment taxBankPayment);
    String list();
    List findAllThirdParty();
    String getTexBankPaymentById(int id);
    ModelAndView printTaxCertificate(Map params);
    String update(TaxBankPayment taxBankPayment);
    String delete(TaxBankPayment taxBankPayment);
}
