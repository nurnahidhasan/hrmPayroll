package com.bracu.hrm.service;

import com.bracu.hrm.dao.TaxBankPaymentDao;
import com.bracu.hrm.dbconfig.ReadOnlyConnection;
import com.bracu.hrm.exception.ServiceConditionException;
import com.bracu.hrm.exception.SystemException;
import com.bracu.hrm.model.payroll.TaxBankPayment;
import com.bracu.hrm.util.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.jasperreports.JasperReportsPdfView;

import java.sql.ResultSet;
import java.util.*;

/**
 * Created by rana on 3/11/18.
 */
@Service
@Transactional
@Slf4j
public class TaxBankPaymentServiceImpl implements TaxBankPaymentService {
    @Autowired
    private TaxBankPaymentDao taxBankPaymentDao;
    @Autowired
    MessageSource messageSource;
    @Autowired
    ApplicationContext appContext;


    @Override
    @Transactional
    public String save(TaxBankPayment taxBankPayment) throws SystemException, ServiceConditionException {
        try {
            taxBankPayment.setVersion(0);
            taxBankPayment.setDateCreate(new Date());
            taxBankPaymentDao.save(taxBankPayment);
            String message = messageSource.getMessage("save.successful.message", new String[]{"Tax Bank Payment", taxBankPayment.getChallanNumber()}, Locale.getDefault());
            return message;
        } catch (SystemException e) {
            log.error(e.getMessage());
            throw new SystemException(e.getMessage());
        } catch (ServiceConditionException e) {
            log.error(e.getMessage());
            throw new ServiceConditionException(e.getMessage());
        }
    }

    @Override
    @ReadOnlyConnection
    @Transactional(readOnly = true)
    public String list() {
        List<TaxBankPayment> taxBankPaymentList = taxBankPaymentDao.getList();
        return JSONUtil.getJsonObject(taxBankPaymentList);

    }

    @Override
    @ReadOnlyConnection
    @Transactional(readOnly = true)
    public List findAllThirdParty() {
        return  taxBankPaymentDao.findAllThirdParty();
       // return JSONUtil.getJsonObject(partyList);

    }

    @Override
    @ReadOnlyConnection
    @Transactional(readOnly = true)
    public String getTexBankPaymentById(int id) {
        List<TaxBankPayment> taxBankPaymentList = taxBankPaymentDao.getTaxBankpaymentById(id);
        return JSONUtil.getJsonObject(taxBankPaymentList.get(0));

    }

    @Override
    @Transactional
    public String update(TaxBankPayment taxBankPayment) throws SystemException, ServiceConditionException {
        try {
            TaxBankPayment currentTaxBankPayment= taxBankPaymentDao.getHTaxBankPaymentById(taxBankPayment.getId());
            if (currentTaxBankPayment.getVersion() > taxBankPayment.getVersion()) {
                String message = messageSource.getMessage("update.version.change", new String[]{"Tax Bank Payment", String.valueOf(taxBankPayment.getVersion())}, Locale.getDefault());
                return message;
            }
            currentTaxBankPayment.setDateLastUpdate(new Date());
            currentTaxBankPayment.setChallanNumber(taxBankPayment.getChallanNumber());
            currentTaxBankPayment.setChallanDate(taxBankPayment.getChallanDate());
            currentTaxBankPayment.setVersion(taxBankPayment.getVersion() + 1);
            currentTaxBankPayment.setBankName(taxBankPayment.getBankName());
            currentTaxBankPayment.setTotalAmount(taxBankPayment.getTotalAmount());

            taxBankPaymentDao.save(currentTaxBankPayment);
            String message = messageSource.getMessage("save.updated.message", new String[]{"Tax Bank Payment", taxBankPayment.getChallanNumber()}, Locale.getDefault());
            return message;

        } catch (NullPointerException e) {
            log.error(e.getClass().getSimpleName());
            log.error(e.getLocalizedMessage());
            throw new SystemException("Version does not find in database field");
        } catch (SystemException e) {
            log.error(e.getMessage());
            throw new SystemException(e.getMessage());
        } catch (ServiceConditionException e) {
            log.error(e.getMessage());
            throw new ServiceConditionException(e.getMessage());
        }
    }

    @Override
    @Transactional
    public String delete(TaxBankPayment taxBankPayment) {
        try {
            TaxBankPayment currentTaxBankPayment= taxBankPaymentDao.getHTaxBankPaymentById(taxBankPayment.getId());
            if (!currentTaxBankPayment.getVersion().equals(currentTaxBankPayment.getVersion()) || currentTaxBankPayment== null) {
                String message = messageSource.getMessage("delete.version.message", new String[]{"Tax Bank Payment", String.valueOf(currentTaxBankPayment.getVersion())}, Locale.getDefault());
                return message;
            } else {
                taxBankPaymentDao.delete(currentTaxBankPayment);
                String message = messageSource.getMessage("delete.success.message", new String[]{"Tax Bank Payment", String.valueOf(currentTaxBankPayment.getVersion())}, Locale.getDefault());
                return message;

            }
        } catch (NoSuchMessageException e) {
            log.error(e.getMessage());
            throw new SystemException(e.getMessage());
        } catch (NullPointerException e) {
            log.error(e.getLocalizedMessage());
            throw new SystemException("Version does not find in database field");
        }
    }

    public ModelAndView printTaxCertificate(Map criteria) {
        JasperReportsPdfView view = new JasperReportsPdfView();
        Map<String, Object> params = new HashMap<>();

        Map<String, String> pathVariable = new HashMap<String, String>();
        pathVariable.put("partyId", criteria.get("partyId").toString());
        pathVariable.put("fromDate", criteria.get("fromDate").toString());
        pathVariable.put("toDate", criteria.get("toDate").toString());
        pathVariable.put("sectionName", criteria.get("sectionName").toString());
        pathVariable.put("sectionCode", criteria.get("sectionCode").toString());
        try{
                view.setUrl("classpath:reports/taxCertificate/taxCertificate.jasper");
                view.setApplicationContext(appContext);
                params.put("currency_note", "BDT");
                params.put("currency_coin", "Paisa");
                params.put("sumAmount", 55484);
                params.put("masterReportTitle", "Tax Certificate");
                params.put("masterCurrentUser", "Nahid Hasan");
                params.put("SUBREPORT_DIR", "/home/rana/dev/hrm-payroll/src/main/resources/reports/subreports/");//getClass().getClassLoader().getResource("reports/subreports/").getPath());
                params.put("transactionDateFromDMY", "01-01-2017");
                params.put("transactionDateToDMY", "10-01-2017");
                params.put("title", "test");
                params.put("companyLogo", getClass().getClassLoader().getResource("reports/subreports/bracu_logo.png").getFile());
                params.put("datasource", new JRResultSetDataSource(taxBankPaymentDao.printTaxCertificate(pathVariable)));
            } catch (Exception e) {
                e.printStackTrace();
            }
        return 	new ModelAndView(view, params);
    }
}


