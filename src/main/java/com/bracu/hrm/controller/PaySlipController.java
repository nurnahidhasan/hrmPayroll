package com.bracu.hrm.controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.activation.DataSource;
import javax.mail.internet.MimeMessage;
import javax.mail.util.ByteArrayDataSource;

import com.bracu.hrm.dao.SetupEntityDao;
import com.bracu.hrm.model.Employee;
import com.bracu.hrm.service.DepartmentService;
import com.bracu.hrm.service.EmployeeService;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.sun.jmx.snmp.Timestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.jasperreports.JasperReportsPdfView;

import com.bracu.hrm.service.payslip.PaySlipService;
import com.lowagie.text.pdf.PdfWriter;

import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRPdfExporterParameter;
import net.sf.jasperreports.export.PdfExporterConfiguration;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;

/**
 * Created by Nahid on 07-Jan-18.
 */
@Controller
@RequestMapping("/payment")
public class PaySlipController {
	@Autowired
	PaySlipService paySlipService;
	@Autowired
	DepartmentService departmentService;


	@RequestMapping(value = { "/show" }, method = RequestMethod.GET)
	public String create(ModelMap model) {

		Map<Integer, String> monthsList = new HashMap<Integer, String>();
		String[] months = new DateFormatSymbols().getMonths();
		for (int i = 0; i < months.length - 1; i++) {
			String month = months[i];
			monthsList.put((i + 1), months[i]);
		}
		ArrayList<String> yearList = new ArrayList<String>();
		for (int years = 2001; years <= Calendar.getInstance().get(Calendar.YEAR); years++) {
			yearList.add(years + "");
		}
		Integer currentMonth = Calendar.getInstance().get(Calendar.MONTH);
		Integer currentYear = Calendar.getInstance().get(Calendar.YEAR);
		// List<Employee> employeeList = employeeService.findAllEmployees();
		model.addAttribute("monthsList", monthsList);
		model.addAttribute("yearList", yearList);
		model.addAttribute("currentMonth", currentMonth + 1);
		model.addAttribute("currentYear", currentYear);
		model.addAttribute("departmentList",departmentService.findAll());
		// model.addAttribute("loggedinuser", getPrincipal());
		return "paySlip/paySlip";
	}

	@RequestMapping(value = { "/printAll/{salaryType}/{pinNo}/{salaryMonth}/{salaryYear}/{printOrEmail}/{departmentId}" }, method = RequestMethod.GET)
	public ModelAndView printAll(@PathVariable("salaryType") String salaryType, @PathVariable("pinNo") String pinNo,
			@PathVariable("salaryMonth") String salaryMonth, @PathVariable("salaryYear") String salaryYear,
			@PathVariable("printOrEmail") String printOrEmail, @PathVariable("departmentId") String departmentId) {

		Map criteria = new HashMap<String,String>();
		criteria.put("salaryType",salaryType.toString());
		criteria.put("pinNo",pinNo.toString());
		criteria.put("salaryMonth",salaryMonth.toString());
		criteria.put("salaryYear",salaryYear.toString());
		criteria.put("printOrEmail",printOrEmail.toString());
		criteria.put("departmentId",departmentId.toString());

		return paySlipService.generatePaySlip(criteria);

	}
	@ResponseBody
	@RequestMapping(value = { "/mailList" }, method = RequestMethod.POST)
	public String list(ModelMap model) {
		String subGroupJson = paySlipService.getMailList();
		return subGroupJson ;
	}

	@RequestMapping(value = { "/list" }, method = RequestMethod.GET)
	public String listPage(ModelMap model) {
		return "paySlip/paySlipList";
	}


	@RequestMapping(value = { "/studentEmail" }, method = RequestMethod.GET)
	public String studentEmail(ModelMap model) {
		return "studentEmail/studentEmail";
	}

	@RequestMapping(value = { "/emailAll/{offset}" }, method = RequestMethod.GET)
	public ModelAndView emailAll(@PathVariable("offset") String offset) {

		Map criteria = new HashMap<String,String>();
		criteria.put("offSet", offset);
		return paySlipService.sendGsuitData(criteria);

	}

	@RequestMapping(value = { "/showPfSlip" }, method = RequestMethod.GET)
	public String pfSlip(ModelMap model) {

		Map<Integer, String> monthsList = new HashMap<Integer, String>();
		String[] months = new DateFormatSymbols().getMonths();
		for (int i = 0; i < months.length - 1; i++) {
			String month = months[i];
			monthsList.put((i + 1), months[i]);
		}
		ArrayList<String> yearList = new ArrayList<String>();
		for (int years = 2010; years <= Calendar.getInstance().get(Calendar.YEAR)-0; years++) {
			yearList.add(years + "-"+ (years+1));
		}
		Integer currentMonth = Calendar.getInstance().get(Calendar.MONTH);
		Integer currentYear = Calendar.getInstance().get(Calendar.YEAR);
		// List<Employee> employeeList = employeeService.findAllEmployees();
		model.addAttribute("monthsList", monthsList);
		model.addAttribute("yearList", yearList);
		model.addAttribute("currentMonth", currentMonth + 1);
		model.addAttribute("currentYear", currentYear);
		model.addAttribute("departmentList",departmentService.findAll());
		// model.addAttribute("loggedinuser", getPrincipal());
		return "paySlip/pfSlip";
	}

	@RequestMapping(value = { "/printAllPf/{salaryType}/{pinNo}/{salaryYear}/{printOrEmail}/{departmentId}" }, method = RequestMethod.GET)
	public ModelAndView printAllPf(@PathVariable("salaryType") String salaryType, @PathVariable("pinNo") String pinNo,
								   @PathVariable("salaryYear") String salaryYear, @PathVariable("printOrEmail") String printOrEmail,
								   @PathVariable("departmentId") String departmentId) {

		Map criteria = new HashMap<String,String>();
		criteria.put("salaryType",salaryType.toString());
		criteria.put("pinNo",pinNo.toString());
		criteria.put("salaryYear",salaryYear.toString());
		criteria.put("printOrEmail",printOrEmail.toString());
		criteria.put("departmentId",departmentId.toString());

		return paySlipService.generatePfSlip(criteria);

	}

	@RequestMapping(value = { "/printJasper" }, method = RequestMethod.GET)
	public String printJasper(ModelMap model) {


		return "paySlip/printJasper";
	}
	@RequestMapping(value = { "/printEmpIdJasper/{employeeId}" }, method = RequestMethod.GET)
	public ModelAndView printEmpIdJasper(@PathVariable("employeeId") String employeeId) {

		Map criteria = new HashMap<String,String>();
		criteria.put("employeeId",employeeId.toString());
		return paySlipService.generateEmployeeId(criteria);

	}

	@RequestMapping(value = { "/showHtml" }, method = RequestMethod.GET)
	public String createHTML(ModelMap model) {

		Map<Integer, String> monthsList = new HashMap<Integer, String>();
		String[] months = new DateFormatSymbols().getMonths();
		for (int i = 0; i < months.length - 1; i++) {
			String month = months[i];
			monthsList.put((i + 1), months[i]);
		}
		ArrayList<String> yearList = new ArrayList<String>();
		for (int years = 2001; years <= Calendar.getInstance().get(Calendar.YEAR); years++) {
			yearList.add(years + "");
		}
		Integer currentMonth = Calendar.getInstance().get(Calendar.MONTH);
		Integer currentYear = Calendar.getInstance().get(Calendar.YEAR);
		// List<Employee> employeeList = employeeService.findAllEmployees();

		try {
			HttpResponse<String> response = Unirest.get("https://www.citypopulation.de/en/germany/covid/")
					.header("cache-control", "no-cache")
					.header("postman-token", "3247dd2d-3b96-4326-3e68-f228c3c37c50")
					.asString();
			String startTable = "";
			String fianlTable = "";
			if(response.getBody()!=null || response.getBody()!= ""){
				startTable = response.getBody().substring(response.getBody().indexOf("<table") + 0 , response.getBody().length());
			}
			if(startTable !=""){
				fianlTable = startTable.substring(0 , startTable.indexOf("</table>")+8);
			}


			model.addAttribute("response", fianlTable);

		}catch (Exception e){

		}

		model.addAttribute("monthsList", monthsList);
		model.addAttribute("yearList", yearList);
		model.addAttribute("currentMonth", currentMonth + 1);
		model.addAttribute("currentYear", currentYear);
		model.addAttribute("departmentList",departmentService.findAll());
		// model.addAttribute("loggedinuser", getPrincipal());
		return "paySlip/paySlip";
	}

}
