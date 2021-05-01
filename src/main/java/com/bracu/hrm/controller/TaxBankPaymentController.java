package com.bracu.hrm.controller;

/**
 * Created by rana on 3/11/18.
 */

import com.bracu.hrm.dao.TaxBankPaymentDao;
import com.bracu.hrm.model.leave.LeaveType;
import com.bracu.hrm.model.payroll.TaxBankPayment;
import com.bracu.hrm.service.TaxBankPaymentService;
import com.bracu.hrm.util.SQLDataSourceTax;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.jasperreports.JasperReportsPdfView;

import javax.sql.DataSource;
import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Rana on 3/4/2018.
 */
@Controller
public class TaxBankPaymentController {
    @Autowired
    private TaxBankPaymentService taxBankPaymentService;
    @Autowired
    private TaxBankPaymentDao taxBankPaymentDao;
    @Autowired
    MessageSource messageSource;
    @Autowired
    ApplicationContext appContext;

    @Autowired
    DataSource dataSource;

    @RequestMapping(value = "/taxbankpayment/create", method = RequestMethod.GET)
    public String create(){
        return "taxBankPayment/create";
    }

    @ResponseBody
    @RequestMapping(value = { "/taxbankpayment/save" }, method = RequestMethod.POST,consumes = { MediaType.APPLICATION_JSON_VALUE })
    public String save(@RequestBody TaxBankPayment taxBankPayment, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "taxBankPayment/create";
        } else{
            return taxBankPaymentService.save(taxBankPayment);
        }


    }

    @RequestMapping(value = { "/taxbankpayment/show" }, method = RequestMethod.GET)
    public String create(ModelMap model) {
        model.addAttribute("thirdPartyList",taxBankPaymentService.findAllThirdParty());
        return "taxBankPayment/show";
    }


    @ResponseBody
    @RequestMapping(value = "/taxbankpayment/list/", method = RequestMethod.POST)
    public String list(){
        String list = taxBankPaymentService.list();
        return list;
    }
    @ResponseBody
    @RequestMapping(value = "/taxbankpayment/edit/{id}", method = RequestMethod.POST)
    public String edit(@PathVariable("id") String id, ModelMap model){
        String taxBankPayment = taxBankPaymentService.getTexBankPaymentById(Integer.parseInt(id));
        return taxBankPayment;
    }
    @ResponseBody
    @RequestMapping(value = { "/taxbankpayment/update" }, method = RequestMethod.POST,consumes = { MediaType.APPLICATION_JSON_VALUE })
    public String update(@RequestBody TaxBankPayment taxBankPayment, BindingResult resultItem, Model model) {
        if (resultItem.hasErrors()) {
            return "taxBankPayment/create";
        } else{
            return taxBankPaymentService.update(taxBankPayment);
        }

    }
    @ResponseBody
    @RequestMapping(value = { "/taxbankpayment/delete/" }, method = RequestMethod.POST)
    public String delete(@RequestBody TaxBankPayment taxBankPayment, BindingResult resultItem) {
        if (resultItem.hasErrors()) {
            return "taxBankPayment/create";
        } else{
            return taxBankPaymentService.delete(taxBankPayment);
        }

    }

    @RequestMapping(value = { "/taxbankpayment/printTaxCertificate/{partyId}/{fromDate}/{toDate}/{sectionName}/{sectionCode}" }, method = RequestMethod.GET)
    public ModelAndView printAll(@PathVariable("partyId") String partyId, @PathVariable("fromDate") String fromDate,
                                 @PathVariable("toDate") String toDate, @PathVariable("sectionName") String sectionName,
                                 @PathVariable("sectionCode") String sectionCode) {
        JasperReportsPdfView view = new JasperReportsPdfView();
        Map<String, Object> params = new HashMap<>();

        try{
            SimpleDateFormat fromUser = new SimpleDateFormat("MM-dd-yyyy");
            SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
            String fromDateStr = myFormat.format(fromUser.parse(fromDate.toString()));
            String toDateStr = myFormat.format(fromUser.parse(toDate.toString()));

            SimpleDateFormat fromUserJS = new SimpleDateFormat("MM-dd-yyyy");
            SimpleDateFormat myJasperFormate = new SimpleDateFormat("MM-dd-yyyy");
            String fromDateStrParms = myJasperFormate.format(fromUserJS.parse(fromDate.toString()));
            String toDateStrParms = myJasperFormate.format(fromUserJS.parse(toDate.toString()));


            Map criteria = new HashMap<String,String>();
            criteria.put("partyId",partyId.toString());
            criteria.put("fromDate",fromDateStr);
            criteria.put("toDate",toDateStr);
            criteria.put("sectionName",sectionName.toString());
            criteria.put("sectionCode",sectionCode.toString());

            Map<String, String> pathVariable = new HashMap<String, String>();
            pathVariable.put("partyId", criteria.get("partyId").toString());
            pathVariable.put("fromDate", criteria.get("fromDate").toString());
            pathVariable.put("toDate", criteria.get("toDate").toString());
            pathVariable.put("sectionName", criteria.get("sectionName").toString());
            pathVariable.put("sectionCode", criteria.get("sectionCode").toString());


            view.setUrl("classpath:reports/taxCertificate/taxCertificate.jasper");
            view.setApplicationContext(appContext);
            params.put("currency_note", "BDT");
            params.put("currency_coin", "Paisa");
            params.put("sumAmount", 55484);
            params.put("masterReportTitle", "Tax Certificate");
            params.put("masterCurrentUser", "Nahid Hasan");
            params.put("SUBREPORT_DIR", getClass().getClassLoader().getResource("reports/subreports/").getPath());//getClass().getClassLoader().getResource("reports/subreports/").getPath());
            params.put("fromDate", fromDateStrParms);
            params.put("toDate", toDateStrParms);
            params.put("title", "test");
            params.put("REPORT_CONNECTION", new SQLDataSourceTax().getPgConnection());
//            params.put("SUBREPORTDATASOURCE", new JRResultSetDataSource(taxBankPaymentDao.printTaxCertificate(pathVariable)));
            params.put("companyLogo", getClass().getClassLoader().getResource("reports/subreports/bracu_logo.png").getFile());
            params.put("datasource", new JRResultSetDataSource(taxBankPaymentDao.printTaxCertificate(pathVariable)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 	new ModelAndView(view, params);

    }
}
