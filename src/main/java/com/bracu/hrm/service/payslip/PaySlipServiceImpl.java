package com.bracu.hrm.service.payslip;

import com.bracu.hrm.dao.CompanyDao;
import com.bracu.hrm.dao.MailDao;
import com.bracu.hrm.dao.PaySlipDao;
import com.bracu.hrm.dao.SetupEntityDao;
import com.bracu.hrm.model.Employee;
import com.bracu.hrm.model.email.Mail;
import com.bracu.hrm.model.org.Company;
import com.bracu.hrm.service.CompanyService;
import com.bracu.hrm.service.EmployeeService;
import com.bracu.hrm.service.email.EmailService;
import com.bracu.hrm.util.SQLDataSource;
import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.lowagie.text.pdf.PdfWriter;
import com.sun.mail.iap.ByteArray;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.ContentBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.hibernate.mapping.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.jasperreports.JasperReportsPdfView;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.util.ByteArrayDataSource;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.text.DateFormatSymbols;
import java.util.*;
import java.util.List;
import java.util.Map;
import java.util.Set;


@Service("paySlipService")
@Transactional
public class PaySlipServiceImpl implements PaySlipService {

	@Autowired
	private PaySlipDao paySlipDao;
	@Autowired
	ApplicationContext appContext;
	@Autowired
	EmployeeService employeeService;
	@Autowired
	private JavaMailSender emailSender;
	@Autowired
	SetupEntityDao setupEntityDao;
	@Autowired
	PaySlipService paySlipService;
	@Autowired
	MailDao mailDao;
//	public Company findById(int id) {
//		return companyDao.findById(id);
//	}
//
//	public Company findByName(String name) {
//		Company company = companyDao.findByName(name);
//		return company;
//	}
//
//	public void save(Company company) {
//		companyDao.save(company);
//	}
//
//	public void update(Company company) {
//		Company entity = companyDao.findById(company.getId());
//		if(entity!=null){
//			entity.setVersion(company.getVersion()+1);
//			entity.setName(company.getName());
//			entity.setShortName(company.getShortName());
//			entity.setEmail(company.getEmail());
//			entity.setWebUrl(company.getWebUrl());
//			entity.setEmail(company.getEmail());
//			entity.setPhone1(company.getPhone1());
//			entity.setPhone2(company.getPhone2());
//			entity.setFax(company.getFax());
//			entity.setAddress(company.getAddress());
//			entity.setCompanyLogo(company.getCompanyLogo());
//		}
//	}
//
//
//	public void delete(int id) {
//		companyDao.delete(id);
//	}

	public ResultSet findAll(Map params) {
		return paySlipDao.findAll(params);
	}
	public ResultSet findAllPf(Map params) {
		return paySlipDao.findAllPf(params);
	}
	public ResultSet findAllId(Map params) {
		return paySlipDao.findAllId(params);
	}

	public List findAllStudent(Map params) {
		return paySlipDao.findAllStudent(params);
	}
	public List findByStudentId(Map params) {
		return paySlipDao.findByStudentId(params);
	}

//	public boolean isUnique(Integer id, String name) {
//		Company user = findByName(name);
//		return ( user == null || ((id != null) && (user.getId() == id)));
//	}

	public ModelAndView generatePaySlip(Map criteria){
		JasperReportsPdfView view = new JasperReportsPdfView();
		Map<String, Object> params = new HashMap<>();
		try {
			Map<String, String> pathVariable = new HashMap<String, String>();
			pathVariable.put("salaryType", criteria.get("salaryType").toString());
			pathVariable.put("pinNo", criteria.get("pinNo").toString());
			pathVariable.put("salaryMonth", criteria.get("salaryMonth").toString());
			pathVariable.put("salaryYear", criteria.get("salaryYear").toString());
			pathVariable.put("departmentId", criteria.get("departmentId").toString());
			if(criteria.get("printOrEmail").toString().equals("print")) {
				view.setUrl("classpath:reports/paySlip/paySlipMaster.jasper");
				view.setApplicationContext(appContext);
				params.put("masterReportTitle", "Requisition List");
				params.put("masterCurrentUser", "Nahid Hasan");
				params.put("SUBREPORT_DIR", "classpath:reports/subreports/");
				params.put("transactionDateFromDMY", "01-01-2017");
				params.put("transactionDateToDMY", "10-01-2017");
				params.put("title", "test");
				params.put("companyLogo", getClass().getClassLoader().getResource("reports/subreports/bracu_logo.png").getFile());
				params.put("datasource", new JRResultSetDataSource(paySlipService.findAll(pathVariable)));
			}else {
				view.setUrl("classpath:reports/paySlip/paySlipMaster.jasper");
				view.setApplicationContext(appContext);


				params.put("masterReportTitle", "Requisition List");
				params.put("masterCurrentUser", "Nahid Hasan");
				params.put("SUBREPORT_DIR", "classpath:reports/subreports/");
				params.put("transactionDateFromDMY", "01-01-2017");
				params.put("transactionDateToDMY", "10-01-2017");
				params.put("title", "test");
				params.put("companyLogo", getClass().getClassLoader().getResource("reports/subreports/bracu_logo.png").getFile());
						//"classpath:reports/subreports/bracu_logo.png");
				params.put("datasource", new JRResultSetDataSource(paySlipService.findAll(pathVariable)));

				ResultSet rs = paySlipService.findAll(pathVariable);

				ResultSetMetaData md = rs.getMetaData();
				Set<String> uniquePinList = new HashSet<String>();
				Map map=new HashMap();
				while (rs.next()){
					uniquePinList.add(rs.getString("pin"));
					map.put(rs.getString("pin"), rs.getString("email"));
				}
				boolean isPin = true;
				for (String pin : uniquePinList) {
//					if (pin.equals("00015010")==true) {
//						isPin = true;
//					}
					if(isPin==true){


					Employee employee = employeeService.findByPin(pin);
					if (employee != null) {
						if (employee.getEmail().contains("@bracu.ac.bd") || map.get(pin).toString().contains("@bracu.ac.bd")) {
							if (map.get(pin).toString().equals(employee.getEmail().toString())) {
								sendValidEmail(employee, params, pathVariable, criteria, pin);
							} else {
								if (map.get(pin).toString().contains("@bracu.ac.bd")) {
									employee.setEmail(map.get(pin).toString());
									employeeService.saveEmployee(employee);
									sendValidEmail(employee, params, pathVariable, criteria, pin);
								}
								if ((map.get(pin).toString().equals("") || !map.get(pin).toString().contains("@bracu.ac.bd")) && employee.getEmail().contains("@bracu.ac.bd")) {
									sendValidEmail(employee, params, pathVariable, criteria, pin);
								}

							}
						} else {
							Mail mail = new Mail();
							mail.setFrom("BRAC University <erp@bracu.ac.bd>");
							mail.setTo(employee.getPin().toString());
							mail.setSubject("No Email Address Found. in Bracu Domain");
							mail.setContent("Dear " + employee.getFullName() + ", \r\nPlease Find the Payslip for the month of " + new DateFormatSymbols().getMonths()[Integer.parseInt(criteria.get("salaryMonth").toString()) - 1] +
									", " + criteria.get("salaryYear").toString() + " as attached file. \r\n \r\n \r\n N.B. This Payslip is machine generated and" +
									" password protected pdf file. Please use your 8 digit PIN to open it. (e.g : 00000001).");
							mail.setNote("No Email Address Found For " + employee.getPin().toString() + " - " + employee.getFullName().toString());
							mail.setStatus(false);
							mail.setSalaryMonth(criteria.get("salaryMonth").toString());
							mail.setSalaryYear(criteria.get("salaryYear").toString());
							mailDao.save(mail);
						}

					} else {
						ResultSet newEmployee = employeeService.getSqlServerEmployee(pin);

						ResultSetMetaData md2 = newEmployee.getMetaData();
						int columns = md2.getColumnCount();
						ArrayList list = new ArrayList(newEmployee.getRow());
						while (newEmployee.next()) {
							HashMap row = new HashMap(columns);
							for (int i = 1; i <= columns; ++i) {
								row.put(md2.getColumnName(i), newEmployee.getObject(i));
							}
							list.add(row);
						}
						Object empObj = list.get(0);
						Employee newEmployeeForSalary = employeeService.prepareNewEmployeeFromPaySlip(empObj);
						if (newEmployeeForSalary != null) {
							sendValidEmail(newEmployeeForSalary, params, pathVariable, criteria, newEmployeeForSalary.getPin().toString());
						}

					}
				}
				}

			}
			Connection con  = new SQLDataSource().getSqlConnection();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return 	new ModelAndView(view, params);
	}

	public void sendValidEmail(Employee employee,Map params,Map pathVariable,Map criteria,String pin ){

		try {
			Map<String, String> emailVariable = new HashMap<String, String>();
			pathVariable.put("salaryType", "1");
			pathVariable.put("pinNo", pin);
			pathVariable.put("salaryMonth", criteria.get("salaryMonth").toString());
			pathVariable.put("salaryYear", criteria.get("salaryYear").toString());
			pathVariable.put("departmentId", criteria.get("departmentId").toString());
			//InputStream input = new FileInputStream(new File("/Users/rana/Works/eclipse-workspace/hrm-payroll/src/main/resources/reports/paySlip/paySlipMaster.jrxml"));
			InputStream input = new ClassPathResource("reports/paySlip/paySlipMaster.jrxml").getInputStream();

			JasperReport jasperReport = JasperCompileManager.compileReport(input);

			Map parameters = new HashMap<>(params);
			JasperPrint print = JasperFillManager.fillReport(jasperReport, parameters,
					new JRResultSetDataSource(paySlipService.findAll(pathVariable)));


			ByteArrayOutputStream baos = new ByteArrayOutputStream();

			JRPdfExporter exporter = new JRPdfExporter();
			exporter.setExporterInput(new SimpleExporterInput(print));
			exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(baos));
			SimplePdfExporterConfiguration configuration = new SimplePdfExporterConfiguration();
			configuration.setEncrypted(true);
			configuration.set128BitKey(true);
			configuration.setUserPassword(pin);
			configuration.setOwnerPassword("reports");
			configuration.setPermissions(PdfWriter.ALLOW_COPY | PdfWriter.ALLOW_PRINTING);
			exporter.setConfiguration(configuration);
			exporter.exportReport();
			DataSource attachment = new ByteArrayDataSource(baos.toByteArray(), "application/pdf");
			MimeMessage message = emailSender.createMimeMessage();
			message.setFrom(new InternetAddress("BRAC University<noreply@bracu.ac.bd>"));

			MimeMessageHelper helper = new MimeMessageHelper(message, true);

			helper.setSubject("Payslip for the month of " + new DateFormatSymbols().getMonths()[Integer.parseInt(criteria.get("salaryMonth").toString()) - 1] + ", " + criteria.get("salaryYear").toString());
			helper.setText("Dear " + employee.getFullName() + ", \r\n \r\nPlease find the payslip for the month of " + new DateFormatSymbols().getMonths()[Integer.parseInt(criteria.get("salaryMonth").toString()) - 1] +
					", " + criteria.get("salaryYear").toString() + " as attached file. \r\n \r\n N.B. This Payslip is machine generated and" +
					" password protected pdf file. Please use your 8 digit PIN to open. (e.g : 00000001).");
			helper.setTo(employee.getEmail().toString());
			helper.setFrom("BRAC University <noreply@bracu.ac.bd>");

			helper.addAttachment(new DateFormatSymbols().getMonths()[Integer.parseInt(criteria.get("salaryMonth").toString()) - 1] + "_" + criteria.get("salaryYear").toString() + "_Payslip.pdf", attachment);

			emailSender.send(message);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public ModelAndView sendGsuitData(Map criteria) {
		JasperReportsPdfView view = new JasperReportsPdfView();
		Map<String, Object> params = new HashMap<>();
		try {
			Map<String, String> pathVariable = new HashMap<String, String>();

			view.setUrl("classpath:reports/studentEmail/studentBulkEmail.jasper");
			view.setApplicationContext(appContext);

			pathVariable.put("offSet", criteria.get("offSet").toString());
//			pathVariable.put("studentId", "11108016");
			params.put("masterReportTitle", "Requisition List");
			params.put("masterCurrentUser", "Nahid Hasan");
			params.put("SUBREPORT_DIR", "classpath:reports/subreports/");
			params.put("transactionDateFromDMY", "01-01-2017");
			params.put("transactionDateToDMY", "10-01-2017");
			params.put("title", "test");
			params.put("companyLogo", getClass().getClassLoader().getResource("reports/subreports/bracu_logo.png").getFile());
			params.put("studentEmailFooter", getClass().getClassLoader().getResource("reports/subreports/student_email_footer.png").getFile());
			params.put("studentEmailGSuite", getClass().getClassLoader().getResource("reports/subreports/student_email_g_suite.png").getFile());
			//"classpath:reports/subreports/bracu_logo.png");
			params.put("datasource", paySlipService.findAllStudent(pathVariable));

			List resultList = paySlipService.findAllStudent(pathVariable);

			Set<String> uniqueStudentIdList = new HashSet<String>();
			for (Object student : resultList) {
				uniqueStudentIdList.add(((HashMap) student).get("student_id").toString());
			}

			for (String pin : uniqueStudentIdList) {
				Map<String, String> studentQuery = new HashMap<String, String>();
				studentQuery.put("studentId", pin);
				Object oneStudent = paySlipService.findByStudentId(studentQuery).get(0);
				//Employee employee = employeeService.findByPin(pin);
				if ((((HashMap) oneStudent).get("personal_email").toString()).contains("@")) {
					Map<String, String> emailVariable = new HashMap<String, String>();
					//pathVariable.put("salaryType", "1");
					//pathVariable.put("pinNo", pin);
					//pathVariable.put("salaryMonth", criteria.get("salaryMonth").toString());
					//pathVariable.put("salaryYear", criteria.get("salaryYear").toString());
					//pathVariable.put("departmentId", criteria.get("departmentId").toString());
					//InputStream input = new FileInputStream(new File("/Users/rana/Works/eclipse-workspace/hrm-payroll/src/main/resources/reports/paySlip/paySlipMaster.jrxml"));
					InputStream input = new ClassPathResource("reports/studentEmail/studentBulkEmail.jrxml").getInputStream();

					JasperReport jasperReport = JasperCompileManager.compileReport(input);

					Map parameters = new HashMap<>(params);
					JasperPrint print = JasperFillManager.fillReport(jasperReport, parameters,
							new JRBeanCollectionDataSource(paySlipService.findByStudentId(studentQuery)));


					ByteArrayOutputStream baos = new ByteArrayOutputStream();

					JRPdfExporter exporter = new JRPdfExporter();
					exporter.setExporterInput(new SimpleExporterInput(print));
					exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(baos));
					SimplePdfExporterConfiguration configuration = new SimplePdfExporterConfiguration();
					configuration.setEncrypted(true);
					configuration.set128BitKey(true);
					configuration.setUserPassword(pin);
					configuration.setOwnerPassword("reports");
					configuration.setPermissions(PdfWriter.ALLOW_COPY | PdfWriter.ALLOW_PRINTING);
					exporter.setConfiguration(configuration);
					exporter.exportReport();
					DataSource attachment = new ByteArrayDataSource(baos.toByteArray(), "application/pdf");
					MimeMessage message = emailSender.createMimeMessage();

					message.setFrom(new InternetAddress("BRAC University<bracu-gsuite@g.bracu.ac.bd>"));

					MimeMessageHelper helper = new MimeMessageHelper(message, true);

//								helper.setSubject("Student email address allocation");
					String messageBody = "Dear All, "+
							"\n" +
							"\nGreetings and Good Afternoon." +
							"\n" +
							"\n" +
							"I would like to inform you that in order to perform some urgent maintenance work, we would like to shut down all services from the USIS with effect from 4:00 pm Friday, 2 November 2018 till  10:00 am Sunday,4 November, 2018,  which is a 42 hours shutdown period. "+
							"\n" +
							"\n" +
							"This shut-down program has been approved by our Acting VC and Treasurer." +
							"\n" +
							"\n" +
							"Please be informed that all other IT related services shall remain operational during this shutdown period. "+
							"\n" +
							"\n" +
							"Thanks and best regards."+
							"\n" +
							"N. Alamgir"+
							"\n" +
							"Head of I.T. "

							;
//								helper.setTo((((HashMap) oneStudent).get("personal_email").toString()));
//								helper.setFrom("BRAC University <noreply@bracu.ac.bd>");

					helper.addAttachment("g_suite.pdf", attachment);
					((ByteArrayDataSource) attachment).setName("g_suite.pdf");
					try {
//									emailSender.send(message);


						MimeBodyPart attachmentPart = new MimeBodyPart();
						attachmentPart.setDataHandler(new DataHandler(attachment));

						String username = "mursalin";
						String password = "Moon#@lighT10";

						String concatenated = username + ":" + password;
						String header = "Basic " + Base64.getEncoder().encodeToString(concatenated.getBytes());

						ContentType PLAIN_UTF8 = ContentType.create("text/plain", StandardCharsets.UTF_8);


						HttpClient httpClient = HttpClients.custom()

								.setConnectionManager(new PoolingHttpClientConnectionManager())

								.build();


						HttpPost httpPost = new HttpPost("https://dgpq8.api.infobip.com/email/1/send");

						httpPost.setHeader("authorization", header);


						MultipartEntityBuilder entityBuilder = MultipartEntityBuilder.create();

						entityBuilder.addPart("from", new StringBody("BRAC University <no-reply@g.bracu.ac.bd>", PLAIN_UTF8));

						entityBuilder.addPart("to", new StringBody((("nur.nahid@bracu.ac.bd").toString()), PLAIN_UTF8));

						entityBuilder.addPart("subject", new StringBody("IMPORTANT ANNOUNCEMENT REGARDING FEES PAYMENT FOR FALL 2018 SEMESTER", PLAIN_UTF8));

						entityBuilder.addPart("text", new StringBody(messageBody, PLAIN_UTF8));
//									entityBuilder.addPart("attachment", attachmentPart);
						//entityBuilder.addBinaryBody("attachment", );

						//	entityBuilder.addBinaryBody("attachment", attachment.getInputStream());
						//	entityBuilder.addBinaryBody("attachment", input);

						httpPost.setEntity(entityBuilder.build());

						HttpResponse httpResponse = httpClient.execute(httpPost);


					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ModelAndView(view, params);
	}
//	}public ModelAndView sendGsuitData(Map criteria){
//		JasperReportsPdfView view = new JasperReportsPdfView();
//		Map<String, Object> params = new HashMap<>();
//		try {
//			Map<String, String> pathVariable = new HashMap<String, String>();
//
//				view.setUrl("classpath:reports/studentEmail/studentBulkEmail.jasper");
//				view.setApplicationContext(appContext);
//
//				pathVariable.put("offSet",criteria.get("offSet").toString());
//				params.put("masterReportTitle", "Requisition List");
//				params.put("masterCurrentUser", "Nahid Hasan");
//				params.put("SUBREPORT_DIR", "classpath:reports/subreports/");
//				params.put("transactionDateFromDMY", "01-01-2017");
//				params.put("transactionDateToDMY", "10-01-2017");
//				params.put("title", "test");
//				params.put("companyLogo", getClass().getClassLoader().getResource("reports/subreports/bracu_logo.png").getFile());
//				params.put("studentEmailFooter", getClass().getClassLoader().getResource("reports/subreports/student_email_footer.png").getFile());
//				params.put("studentEmailGSuite", getClass().getClassLoader().getResource("reports/subreports/student_email_g_suite.png").getFile());
//						//"classpath:reports/subreports/bracu_logo.png");
//				params.put("datasource", paySlipService.findAllStudent(pathVariable));
//
//				List resultList = paySlipService.findAllStudent(pathVariable);
//
//				Set<String> uniqueStudentIdList = new HashSet<String>();
//				for (Object student : resultList) {
//					uniqueStudentIdList.add(((HashMap) student).get("student_id").toString());
//				}
//
//				for (String pin : uniqueStudentIdList) {
//					Map<String, String> studentQuery = new HashMap<String, String>();
//					studentQuery.put("studentId", pin);
//					Object oneStudent  = paySlipService.findByStudentId(studentQuery).get(0);
//					//Employee employee = employeeService.findByPin(pin);
//							if ((((HashMap) oneStudent).get("personal_email").toString()).contains("@")) {
//								Map<String, String> emailVariable = new HashMap<String, String>();
//								//pathVariable.put("salaryType", "1");
//								//pathVariable.put("pinNo", pin);
//								//pathVariable.put("salaryMonth", criteria.get("salaryMonth").toString());
//								//pathVariable.put("salaryYear", criteria.get("salaryYear").toString());
//								//pathVariable.put("departmentId", criteria.get("departmentId").toString());
//								//InputStream input = new FileInputStream(new File("/Users/rana/Works/eclipse-workspace/hrm-payroll/src/main/resources/reports/paySlip/paySlipMaster.jrxml"));
//								InputStream input = new ClassPathResource("reports/studentEmail/studentBulkEmail.jrxml").getInputStream();
//
//								JasperReport jasperReport = JasperCompileManager.compileReport(input);
//
//								Map parameters = new HashMap<>(params);
//								JasperPrint print = JasperFillManager.fillReport(jasperReport, parameters,
//										new JRBeanCollectionDataSource(paySlipService.findByStudentId(studentQuery)));
//
//
//								ByteArrayOutputStream baos = new ByteArrayOutputStream();
//
//								JRPdfExporter exporter = new JRPdfExporter();
//								exporter.setExporterInput(new SimpleExporterInput(print));
//								exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(baos));
//								SimplePdfExporterConfiguration configuration = new SimplePdfExporterConfiguration();
//								configuration.setEncrypted(true);
//								configuration.set128BitKey(true);
//								configuration.setUserPassword(pin);
//								configuration.setOwnerPassword("reports");
//								configuration.setPermissions(PdfWriter.ALLOW_COPY | PdfWriter.ALLOW_PRINTING);
//								exporter.setConfiguration(configuration);
//								exporter.exportReport();
//								DataSource attachment = new ByteArrayDataSource(baos.toByteArray(), "application/pdf");
//								MimeMessage message = emailSender.createMimeMessage();
//
//								message.setFrom(new InternetAddress("BRAC University<bracu-gsuite@g.bracu.ac.bd>"));
//
//								MimeMessageHelper helper = new MimeMessageHelper(message, true);
//
////								helper.setSubject("Student email address allocation");
//								String messageBody = "<h2 style='color:#569CCA'> WELCOME MESSAGE FROM HEAD OF IT</h2> " +
//										"\n" +
//										"<p>Welcome to your BRAC University G Suite account, which you will use for email communications, word processing, calendar scheduling, file storage, and several other daily tasks.</p>\n" +
//										"<h3 style='color:#569CCA'>What you need to do</h3> \n" +
//										"<p>You can start using your email right away. The best way to get started is to spend some time now to set up your account.</p>\n" +
//										"<p>First, start by signing in to your account (<span style='color:#FF0000' >Please see below for your access credential</span>):</p>\n" +
//
//										"<p> &nbsp;&nbsp;&nbsp;&nbsp; 1. Go to <a href='www.google.com'>www.google.com </a> and in the top-right corner, click Sign in, enter your G Suite email full address (for example you@g.bracu.ac.bd ) and password</p>\n" +
//										"<p> &nbsp;&nbsp;&nbsp;&nbsp; or</p>\n" +
//										"<p> &nbsp;&nbsp;&nbsp;&nbsp; go to <a href='https://mail.google.com/a/g.bracu.ac.bd'>https://mail.google.com/a/g.bracu.ac.bd</a> and enter your G Suite email address (for example: email prefix) and password</p>\n" +
//										"<p> &nbsp;&nbsp;&nbsp;&nbsp; 2. Click <b> Sign in</b></p>\n" +
//										"<p> &nbsp;&nbsp;&nbsp;&nbsp; 3. After the first sign-in, students are advised to change the password immediately with a new password with a minimum of 8 characters consisting of at least one small and caps letters, one number and min one special characters (@,#,*) etc.</p>\n </br>" +
//
//										"<h3 style='color:#569CCA'>Make the most of your G Suite services</h3> \n" +
//										"<p>Visit the <a href='https://gsuite.google.com/learning-center/'>G Suite Learning Center</a> to get basic training guides, expert tips, customer examples, and more.</p>\n</br>" +
//										"<h3 style='color:#569CCA'>Get help or provide feedback</h3> \n" +
//										"<p>To help you get the most from your new G Suite services, we've prepared webpage that contains the following resources:</p>\n" +
//										"<ul> "+
//										"<li>Quick Start guide</li> "+
//										"<li>Getting Started guide</li> "+
//										"<li>FAQ</li> "+
//										"<li>Support contact information</li> "+
//										"</ul> "+
//										"<p>To access the site, go to:</p>\n" +
//										"<p><a href='http://www.bracu.ac.bd/bracu-it/g-suite'>http://www.bracu.ac.bd/bracu-it/g-suite</a></p>\n" +
//										"<p><i>Head of IT</i></p>\n" +
//										"<p><i>BRAC University</i></p>\n" +
//
//										"<h2 style='color:#569CCA'>Your access credential</h2>" +
//										"<table style='border:1px solid black;border-collapse: collapse;'>" +
//												"<tr>" +
//													"<td style='border:1px solid black;border-collapse: collapse;'> Name </td>"+
//													"<td style='border:1px solid black;border-collapse: collapse;'> "+(((HashMap) oneStudent).get("full_name").toString())+"</td>"+
//												"</tr>"+
//											"<tr style='border:1px solid black;'>" +
//													"<td style='border:1px solid black;border-collapse: collapse;'> Email </td>"+
//													"<td style='border:1px solid black;border-collapse: collapse;'> "+(((HashMap) oneStudent).get("email").toString())+"</td>"+
//												"</tr>"+
//											"<tr style='border:1px solid black;'>" +
//													"<td style='border:1px solid black;border-collapse: collapse;'> Password </td>"+
//													"<td style='border:1px solid black;border-collapse: collapse;'> "+(((HashMap) oneStudent).get("password").toString())+"</td>"+
//												"</tr>"+
//
//										"</table>"
//										;
////								helper.setTo((((HashMap) oneStudent).get("personal_email").toString()));
////								helper.setFrom("BRAC University <noreply@bracu.ac.bd>");
//
//								helper.addAttachment( "g_suite.pdf", attachment);
//								((ByteArrayDataSource) attachment).setName("g_suite.pdf");
//								try {
////									emailSender.send(message);
//
//
//									MimeBodyPart attachmentPart = new MimeBodyPart();
//									attachmentPart.setDataHandler(new DataHandler(attachment));
//
//									String username = "mursalin";
//									String password = "Moon#@lighT10";
//
//									String concatenated = username + ":" + password;
//									String header = "Basic " + Base64.getEncoder().encodeToString(concatenated.getBytes());
//
//									ContentType PLAIN_UTF8 = ContentType.create("text/plain", StandardCharsets.UTF_8);
//
//
//
//									HttpClient httpClient =  HttpClients.custom()
//
//											.setConnectionManager(new PoolingHttpClientConnectionManager())
//
//											.build();
//
//
//
//									HttpPost httpPost = new HttpPost("https://dgpq8.api.infobip.com/email/1/send");
//
//									httpPost.setHeader("authorization", header);
//
//
//
//									MultipartEntityBuilder entityBuilder = MultipartEntityBuilder.create();
//
//									entityBuilder.addPart("from", new StringBody("BRAC University <no-reply@g.bracu.ac.bd>", PLAIN_UTF8));
//
//									entityBuilder.addPart("to", new StringBody((((HashMap) oneStudent).get("personal_email").toString()), PLAIN_UTF8));
//
//									entityBuilder.addPart("subject", new StringBody("BRAC University G Suite access credential", PLAIN_UTF8));
//
//									entityBuilder.addPart("html", new StringBody(messageBody, PLAIN_UTF8));
////									entityBuilder.addPart("attachment", attachmentPart);
//									//entityBuilder.addBinaryBody("attachment", );
//
//								//	entityBuilder.addBinaryBody("attachment", attachment.getInputStream());
//								//	entityBuilder.addBinaryBody("attachment", input);
//
//									httpPost.setEntity(entityBuilder.build());
//
//									HttpResponse httpResponse = httpClient.execute(httpPost);
//
//
//								} catch (Exception e) {
//									e.printStackTrace();
//								}
//							}
//				}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//		return 	new ModelAndView(view, params);
//	}

	public String getMailList() {
		List list = paySlipDao.getRequisitionList();
		String resultJson = "";
		try {
			Gson gson = new Gson();
			resultJson = gson.toJson(list);
		}catch (JsonIOException e){
			System.out.println(e.getMessage());
		}catch (RuntimeException e){
			System.out.println(e.getMessage());
		}catch (Exception e){
			System.out.println(e.getMessage());
		}
		return resultJson;
	}

	public ModelAndView generatePfSlip(Map criteria){
		JasperReportsPdfView view = new JasperReportsPdfView();
		Map<String, Object> params = new HashMap<>();
		try {
			Map<String, String> pathVariable = new HashMap<String, String>();
			pathVariable.put("salaryType", criteria.get("salaryType").toString());
			if(Long.parseLong(criteria.get("salaryType").toString())==1){
				pathVariable.put("pinNo", "pin in (RIGHT(REPLICATE('0', 8) + '"+criteria.get("pinNo").toString()+"', 8))");
				pathVariable.put("pinNo2", "pin in (RIGHT(REPLICATE('0', 8) + '"+criteria.get("pinNo").toString()+"', 8))");
			}else {
				if((criteria.get("departmentId").toString()).equals("ALL")){
					pathVariable.put("pinNo", "id in (select employee_id from tempEmployeeAccDepartment)");
					pathVariable.put("pinNo2", "id in (select employee_id from tempEmployeeAccDepartment) AND he.job_status_code='0003' ");
				}else {
					pathVariable.put("pinNo", "id in (select employee_id from tempEmployeeAccDepartment WHERE budget_dept_id = "+criteria.get("departmentId").toString()+")");
					pathVariable.put("pinNo2", "id in (select employee_id from tempEmployeeAccDepartment WHERE budget_dept_id = "+criteria.get("departmentId").toString()+") AND he.job_status_code='0003' ");
				}

			}
			//pathVariable.put("pinNo", criteria.get("pinNo").toString());
			pathVariable.put("strFinancialYear", criteria.get("salaryYear").toString());
			pathVariable.put("departmentId", criteria.get("departmentId").toString());
			if(criteria.get("printOrEmail").toString().equals("print")) {
				view.setUrl("classpath:reports/paySlip/prov_fund.jasper");
				view.setApplicationContext(appContext);
				params.put("masterReportTitle", "Requisition List");
				params.put("masterCurrentUser", "Nahid Hasan");
				params.put("SUBREPORT_DIR", "classpath:reports/subreports/");
				params.put("transactionDateFromDMY", "01-01-2017");
				params.put("transactionDateToDMY", "10-01-2017");
				params.put("strFinancialYear", criteria.get("salaryYear").toString());
				params.put("title", "test");
				params.put("companyLogo", getClass().getClassLoader().getResource("reports/subreports/bracu_logo.png").getFile());
				params.put("datasource", new JRResultSetDataSource(paySlipService.findAllPf(pathVariable)));
			}else {
				view.setUrl("classpath:reports/paySlip/prov_fund.jasper");
				view.setApplicationContext(appContext);
				params.put("masterReportTitle", "Requisition List");
				params.put("masterCurrentUser", "Nahid Hasan");
				params.put("SUBREPORT_DIR", "classpath:reports/subreports/");
				params.put("transactionDateFromDMY", "01-01-2017");
				params.put("transactionDateToDMY", "10-01-2017");
				params.put("strFinancialYear", criteria.get("salaryYear").toString());
				params.put("title", "test");
				params.put("companyLogo", getClass().getClassLoader().getResource("reports/subreports/bracu_logo.png").getFile());
				params.put("datasource", new JRResultSetDataSource(paySlipService.findAllPf(pathVariable)));

				ResultSet rs = paySlipService.findAllPf(pathVariable);


				ResultSetMetaData md = rs.getMetaData();
				Set<String> uniquePinList = new HashSet<String>();
				while (rs.next()){
					uniquePinList.add(rs.getString("employeePin"));
				}

				for (String pin : uniquePinList) {
					Employee employee = employeeService.findByPin(pin);
					if (employee != null) {
						if (employee.getEmail() != null) {
							if (employee.getEmail().contains("@")) {

								Map<String, String> emailVariable = new HashMap<String, String>();
								pathVariable.put("salaryType", "1");
								pathVariable.put("pinNo", "pin in (RIGHT(REPLICATE('0', 8) + '"+pin+"', 8))");
								pathVariable.put("pinNo2", "pin in (RIGHT(REPLICATE('0', 8) + '"+pin+"', 8))");
								//pathVariable.put("pinNo", pin);
								//pathVariable.put("salaryMonth", criteria.get("salaryMonth").toString());
								pathVariable.put("salaryYear", criteria.get("salaryYear").toString());
								pathVariable.put("departmentId", criteria.get("departmentId").toString());
								//InputStream input = new FileInputStream(new File("/Users/rana/Works/eclipse-workspace/hrm-payroll/src/main/resources/reports/paySlip/paySlipMaster.jrxml"));
								InputStream input = new ClassPathResource("reports/paySlip/prov_fund.jrxml").getInputStream();

								JasperReport jasperReport = JasperCompileManager.compileReport(input);

								Map parameters = new HashMap<>(params);
								JasperPrint print = JasperFillManager.fillReport(jasperReport, parameters,
										new JRResultSetDataSource(paySlipService.findAllPf(pathVariable)));


								ByteArrayOutputStream baos = new ByteArrayOutputStream();

								JRPdfExporter exporter = new JRPdfExporter();
								exporter.setExporterInput(new SimpleExporterInput(print));
								exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(baos));
								SimplePdfExporterConfiguration configuration = new SimplePdfExporterConfiguration();
								configuration.setEncrypted(true);
								configuration.set128BitKey(true);
								configuration.setUserPassword(pin);
								configuration.setOwnerPassword("reports");
								configuration.setPermissions(PdfWriter.ALLOW_COPY | PdfWriter.ALLOW_PRINTING);
								exporter.setConfiguration(configuration);
								exporter.exportReport();
								DataSource attachment = new ByteArrayDataSource(baos.toByteArray(), "application/pdf");
								MimeMessage message = emailSender.createMimeMessage();
								message.setFrom(new InternetAddress("BRAC University<noreply@bracu.ac.bd>"));

								MimeMessageHelper helper = new MimeMessageHelper(message, true);

								helper.setSubject("Provident Fund Statement for the financial year " + criteria.get("salaryYear").toString());
								helper.setText("Dear " + employee.getFullName() + ", \r\n \r\nPlease find the Provident Fund Statement for the financial year " +
										"" + criteria.get("salaryYear").toString() + " as attached file. \r\n \r\n N.B. This Provident Fund Statement is machine generated and" +
										" password protected pdf file. Please use your 8 digit PIN to open. (e.g : 00000001).");
								helper.setTo(employee.getEmail().toString());
								helper.setFrom("BRAC University <noreply@bracu.ac.bd>");

								helper.addAttachment("PF_Statement_" + criteria.get("salaryYear").toString() + "_pfSlip.pdf", attachment);
								try {
									emailSender.send(message);
								} catch (Exception e) {
									e.printStackTrace();
								}

							}
						}
					}
				}
			}
			Connection con  = new SQLDataSource().getSqlConnection();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return 	new ModelAndView(view, params);
	}
	public ModelAndView generateEmployeeId(Map criteria){
		JasperReportsPdfView view = new JasperReportsPdfView();
		Map<String, Object> params = new HashMap<>();
		try {
			Map<String, String> pathVariable = new HashMap<String, String>();

			pathVariable.put("employeeId", criteria.get("employeeId").toString());


				view.setUrl("classpath:reports/paySlip/employeeId.jasper");
				view.setApplicationContext(appContext);
				params.put("masterReportTitle", "Requisition List");
				params.put("masterCurrentUser", "Nahid Hasan");
				params.put("SUBREPORT_DIR", "classpath:reports/subreports/");
				params.put("transactionDateFromDMY", "01-01-2017");
				params.put("transactionDateToDMY", "10-01-2017");
				params.put("employeeId", criteria.get("employeeId").toString());
				params.put("title", "test");
				params.put("companyLogo", getClass().getClassLoader().getResource("reports/subreports/bracu_logo.png").getFile());
				params.put("datasource", new JRResultSetDataSource(paySlipService.findAllId(pathVariable)));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return 	new ModelAndView(view, params);
	}
	public String sendAdmitCard(){
		paySlipDao.sendAdmitCard();
		return  "";
	}
}
