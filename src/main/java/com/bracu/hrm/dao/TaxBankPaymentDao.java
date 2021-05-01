package com.bracu.hrm.dao;

import com.bracu.hrm.model.payroll.TaxBankPayment;

import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

/**
 * Created by rana on 3/11/18.
 */
public interface TaxBankPaymentDao {
   void save(TaxBankPayment taxBankPayment);
   List<TaxBankPayment> getList();
   List findAllThirdParty();
   ResultSet printTaxCertificate(Map params);
   List<TaxBankPayment> getTaxBankpaymentById(int id);
   TaxBankPayment getHTaxBankPaymentById(int id);
   void delete(TaxBankPayment currentTaxBankPayment);
}
