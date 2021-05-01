package com.bracu.hrm.dao;

import com.bracu.hrm.model.email.Mail;
import com.bracu.hrm.model.settings.ApplicantAdmitCardInformation;
import com.bracu.hrm.util.SQLDataSource;
import com.mashape.unirest.http.Unirest;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.activation.DataHandler;
import javax.mail.internet.MimeBodyPart;
import java.io.File;
import java.io.FileNotFoundException;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;


@Repository("PaySlipDao")
public class PaySlipDaoImpl extends AbstractDao<Integer, Mail>  implements PaySlipDao {

	static final Logger logger = LoggerFactory.getLogger(PaySlipDaoImpl.class);

	@Autowired
	private ApplicantAdmitCardDao applicantAdmitCardDao;

	@SuppressWarnings("unchecked")
	public ResultSet findAll(Map params) {
		Connection con  = new SQLDataSource().getSqlConnection();
		List allResult = new ArrayList();
		String pinNo = " 1=1 AND ";
		String departmentId = " 1=1 AND ";
		if(Integer.parseInt(params.get("salaryType").toString())==1){
			pinNo = " hr_employee_t.pin =RIGHT(REPLICATE('0', 8) + '"+params.get("pinNo")+"', 8) AND ";
		}
		if((params.get("departmentId").toString()).equals("ALL")){
			departmentId = " 1=1 AND ";
		}else {
			departmentId = " pr_budget_departnent_t.code ="+params.get("departmentId")+" AND ";

		}


		ResultSet rs = null;
		try
		{
			con = new SQLDataSource().getSqlConnection();
			Statement s1 = con.createStatement();
			String sqlString = "select\n" +
					" ISNULL(aa.id, bb.id) id,\n" +
					" ISNULL(aa.serialNo, bb.serialNo) serialNo,\n" +
					" ISNULL(aa.salaryNumber, '') salaryNumber,\n" +
					"  ISNULL(aa.name, '') name,\n" +
					"\tISNULL(aa.pin, bb.pin2) pin,\n" +
					"\tISNULL(aa.designation, '') designation,\n" +
					"\tISNULL(aa.breakupName, '') breakupName,\n" +
					"\tISNULL(aa.present_email, '') email,\n" +
					"\tISNULL(aa.amount, 00.00) amount,\n" +
					"\tISNULL(aa.pay_date, null)pay_date,\n" +
					"\tISNULL(aa.pf_id, '') pf_id,\n" +
					"\tISNULL(aa.paymentMode, '') paymentMode,\n" +
					"\tISNULL(aa.salaryMonth, '') salaryMonth,\n" +
					"\tISNULL(aa.salaryYear, '') salaryYear,\n" +
					"\tISNULL(aa.deparment, '') deparment,\n" +
					"\tISNULL(bb.id, null) bbId,\n"+
					"\tISNULL(bb.serialNo, null) serialNo2,\n"+
					"\tISNULL(bb.deductName, '') deductName,\n"+
					"\tISNULL(bb.deductAmount, 0.00) deductAmount\n"+
					"\n" +
					"from (\n" +
					"\n" +
					"SELECT\n" +
					"hr_employee_t.id id,hr_employee_t.present_email,\n" +
					"row_number() over(partition by hr_employee_t.id order by hr_employee_t.id) serialNo,\n" +
					"pr_employee_salary_generation_parent_t.number salaryNumber ,\n" +
					"hr_employee_t.name,hr_employee_t.pin ,hr_designation_t.name designation,\n" +
					"pr_salary_breakup_t.name breakupName,pr_employee_salary_generation_child_employee_breakup_t.amount,\n" +
					"CONVERT(VARCHAR(10), pr_employee_salary_generation_parent_t.pay_date, 105) AS pay_date,\n" +
					"pf_employee_information_t.pf_id,\n" +
					"pr_payment_mode_t.name paymentMode,\n" +
					"DateName( month , DateAdd( month , pr_employee_salary_generation_parent_t.salary_for_the_month , 0 ) - 1 ) salaryMonth, pr_employee_salary_generation_parent_t.salary_for_the_year salaryYear,\n" +
					"pr_budget_departnent_t.name deparment\n" +
					"\n" +
					"FROM hr_employee_t\n" +
					"INNER JOIN hr_designation_t ON hr_designation_t.code =hr_employee_t.designation_code\n" +
					"INNER JOIN pr_employee_salary_generation_parent_t ON\n" +
					"pr_employee_salary_generation_parent_t.employee_id = hr_employee_t.id\n" +
					"INNER JOIN pr_employee_salary_generation_child_employee_breakup_t ON\n" +
					"pr_employee_salary_generation_child_employee_breakup_t.number =pr_employee_salary_generation_parent_t.number\n" +
					"INNER JOIN pr_salary_breakup_t ON\n" +
					"pr_salary_breakup_t.code =pr_employee_salary_generation_child_employee_breakup_t.salary_breakup_id\n" +
					"LEFT JOIN pf_employee_information_t on pf_employee_information_t.employee_id = hr_employee_t.id\n" +
					"INNER JOIN pr_payment_mode_t on pr_payment_mode_t.code = pr_employee_salary_generation_parent_t.payment_mode_code\n" +
					//"INNER JOIN section_t on section_t.code = hr_employee_t.section_code\n" +
					"LEFT JOIN tempEmployeeAccDepartment on hr_employee_t.id = tempEmployeeAccDepartment.employee_id\n" +
					"LEFT JOIN pr_budget_departnent_t on tempEmployeeAccDepartment.budget_dept_id = pr_budget_departnent_t.code\n"+
					"\n" +
					"WHERE "+ departmentId + pinNo +" pr_employee_salary_generation_parent_t.salary_for_the_year='"+params.get("salaryYear") +"' and pr_employee_salary_generation_parent_t.salary_for_the_month='"+params.get("salaryMonth") +"'\n" +
					") as aa\n" +
					"Left join (\n" +
					"\n" +
					"SELECT\n" +
					"pr_employee_salary_generation_child_employee_deduction_t.employee_id id,hr_employee_t.pin pin2,\n" +
					"row_number() over(partition by pr_employee_salary_generation_parent_t.employee_id  order by pr_employee_salary_generation_parent_t.employee_id ) serialNo,\n" +
					"pr_salary_deduction_breakup_t.name deductName,\n" +
					"pr_employee_salary_generation_child_employee_deduction_t.amount deductAmount\n" +
					"\n" +
					"FROM pr_employee_salary_generation_parent_t\n" +
					"INNER JOIN  hr_employee_t on hr_employee_t.id = pr_employee_salary_generation_parent_t.employee_id\n" +
					"\n" +
					"INNER JOIN pr_employee_salary_generation_child_employee_deduction_t on\n" +
					"pr_employee_salary_generation_child_employee_deduction_t.number =\n" +
					"pr_employee_salary_generation_parent_t.number\n" +
					"INNER JOIN pr_salary_deduction_breakup_t on  pr_salary_deduction_breakup_t.code =\n" +
					"pr_employee_salary_generation_child_employee_deduction_t.salary_deduction_breakup_code\n" +
					"LEFT JOIN tempEmployeeAccDepartment on hr_employee_t.id = tempEmployeeAccDepartment.employee_id\n" +
					"LEFT JOIN pr_budget_departnent_t on tempEmployeeAccDepartment.budget_dept_id = pr_budget_departnent_t.code\n"+
					"WHERE "+ departmentId + pinNo +" pr_employee_salary_generation_parent_t.salary_for_the_year='"+params.get("salaryYear")+"' and pr_employee_salary_generation_parent_t.salary_for_the_month='"+params.get("salaryMonth")+"'\n" +
					"\n" +
					") as bb\n" +
					"on aa.id = bb.id and aa.serialNo = bb.serialNo\n" +
					"order by pin";
			 rs =  s1.executeQuery(sqlString);

			//allResult = Utility.convertResultSetToList(rs);
			//con.close();
			//String result = new result[20];

		}catch (SQLException e) {


		}catch
				(Exception e)
		{
			e.printStackTrace();
		}


		return rs;
	}
	public ResultSet findAllPf(Map params) {
		Connection con  = new SQLDataSource().getSqlConnection();
		List allResult = new ArrayList();
		String pinNo = params.get("pinNo").toString();
		String pinNo2 = params.get("pinNo2").toString();
		String strFinancialYear = params.get("strFinancialYear").toString();
		String departmentId = " 1=1 AND ";



		ResultSet rs = null;
		try
		{
			con = new SQLDataSource().getSqlConnection();
			Statement s1 = con.createStatement();
			String sqlString = "\n" +
					"select *,\n" +
					"\n" +
					"(ISNULL(mm.currentYearOwnContribution,0)+\n" +
					"\tISNULL(mm.currentYearOrgContribution,0)+\n" +
					"\tISNULL(mm.currentOwnInterest,0)+\n" +
					"\tISNULL(mm.currentorgwnInterest,0)+\n" +
					"\tISNULL(mm.openingYearOwnContribution,0)+\n" +
					"\tISNULL(mm.openingYearOrgContribution,0)+\n" +
					"\tISNULL(mm.openingOwnInterest,0)+\n" +
					"\tISNULL(cc.openingYearOwnContributionBack,0)+\n" +
					"\tISNULL(cc.openingYearOrgContributionBack,0)+\n" +
					"\tISNULL(cc.opening_balance_own_interest_back,0)+\n" +
					"\tISNULL(cc.opening_balance_org_interest_back,0)+\n"+
					"\tISNULL(mm.openingOrgInterest,0)) - (ISNULL(mm.own_contribution_Withdrawl,0)) sumBalance,\n" +
					"\tleft('"+ strFinancialYear +"',4) fromYear,\n" +
					"\tright('"+ strFinancialYear +"',4) toYear\n" +
					"\n" +
					"from (\n" +
					"SELECT\n" +
					"pt.[current_date],\n"+
					"he.id employeeId,\n" +
					"he.pin employeePin,\n" +
					"he.name employeeName,\n" +
					"hd.name + ' ('+ hd.code+')' designation,\n" +
					"st.name + ' ('+ st.code+')' sectionCode,\n" +
					"pei.pf_id pfId,\n" +
					"convert(varchar, pei.joining_date, 106) pfJoiningDate,\n" +
					"DateName( month , DateAdd( month , pt.month , -1 ) ) monthName,\n" +
					"(select  after_fourth_quarter\n" +
					"\t\t\tfrom pf_interest_breakup_t pib\n" +
					"\t\t\twhere pib.year= '"+ strFinancialYear +"') interestRate,\n" +
					"pt.own_contribution_amount,\n" +
					"pt.org_contribution_amount,\n" +
					"ISNULL(pei.opening_balance_own_contribution,0) openingYearOwnContributionBack,\n" +
					"ISNULL(pei.opening_balance_org_contribution,0) openingYearOrgContributionBack,\n" +
					"ISNULL(pei.opening_balance_own_interest,0) opening_balance_own_interest_back,\n" +
					"ISNULL(pei.opening_balance_org_interest,0) opening_balance_org_interest_back\n"+
					"\n" +
					"from pf_monthly_contribution_collection_t pt\n" +
					"INNER JOIN hr_employee_t he on he.id = pt.employee_id\n" +
					"INNER JOIN hr_designation_t hd on hd.code = he.designation_code\n" +
					"INNER JOIN section_t st on st.code = he.section_code\n" +
					"INNER JOIN pf_employee_information_t pei on pei.employee_id = he.id\n" +
					"where he."+ pinNo2+"\n" +
					"and collection_date BETWEEN left('"+ strFinancialYear +"',4)+'-07-01' and right('"+ strFinancialYear +"',4)+'-06-30'\n" +
					"\n" +
					") cc\n" +
					"LEFT JOIN\n" +
					"(\n" +
					"select\n" +
					"\taa.employee_id,\n" +
					"\taa.currentYearOwnContribution,\n" +
					"\taa.currentYearOrgContribution,\n" +
					"\taa.currentOwnInterest,\n" +
					"\taa.currentorgwnInterest,\n" +
					"\tbb.openingYearOwnContribution,\n" +
					"\tbb.openingYearOrgContribution,\n" +
					"\taa.own_contribution_Withdrawl,\n" +
					"\taa.own_contribution_Withdrawl_refund,\n" +
					"\taa.currentYearLoanReceived,\n" +
					"\taa.currentYearLoanRefund,\n" +
					"\tbb.openingOwnInterest,\n" +
					"\tbb.openingOrgInterest\n" +
					" from (\n" +
					"select\n" +
					"dd.employee_id,\n" +
					"\t\t\tdd.own_contribution currentYearOwnContribution,\n" +
					"\t\t\tdd.org_contribution currentYearOrgContribution,\n" +
					"\t\t\tdd.own_contribution_Withdrawl own_contribution_Withdrawl,\n" +
					"\t\t\tdd.own_interest currentOwnInterest,\n" +
					"\t\t\tdd.org_interest currentorgwnInterest,\n" +
					"\t\t\tdd.own_contribution_Withdrawl_refund,\n"+
					"\t\t\tISNULL(dd.loan_received,0) currentYearLoanReceived,	"+
					"\t\t\tISNULL(dd.loan_refund,0) currentYearLoanRefund	"+
					"from pf_yearly_balance_t dd\n" +
					"where dd.employee_id in (select id from hr_employee_t where "+pinNo+")\n" +
					"and dd.year='"+ strFinancialYear +"') aa\n" +
					"LEFT JOIN\n" +
					"(\n" +
					"select\n" +
					"dd.employee_id,\n" +
					"(sum(ISNULL(dd.own_contribution,0)) + SUM(ISNULL(dd.own_contribution_Withdrawl_refund,0))+ SUM(ISNULL(dd.loan_refund,0))) - sum(ISNULL(own_contribution_Withdrawl,0))- SUM(ISNULL(dd.loan_received,0)) openingYearOwnContribution,\n" +
					"sum(dd.org_contribution) openingYearOrgContribution,\n" +
					"(sum(ISNULL(dd.own_interest,0))+ SUM(ISNULL(dd.own_interest_Withdrawl_refund,0))) - SUM(ISNULL(dd.own_interest_Withdrawl,0)) openingOwnInterest,\n" +
					"sum(dd.org_interest) openingOrgInterest\n" +
					"from pf_yearly_balance_t dd\n" +
					"where dd.employee_id in(select id from hr_employee_t where "+pinNo+")\n" +
					"and left(dd.year,4) < left('"+ strFinancialYear +"',4)\n" +
					"GROUP BY dd.employee_id\n" +
					") bb\n" +
					"on aa.employee_id = bb.employee_id\n" +
					"\n" +
					"\n" +
					"\n" +
					") mm on mm.employee_id = cc.employeeId\n" +
					"where cc.employeeId not in (select id from hr_employee_t where pin in ('00000070','00113780','00000709','00155778','00004970','00170217','00053453','00175087','00053459','00133803','00062412','00134619','00080205','00137021','00080218','00142060','00080339','00098545','00153609','00153809','00153879','00098549','00098830','00098833','00098938'))\n"+
					"order by  cc.employeePin,[current_date]";
			 rs = s1.executeQuery(sqlString);

			//allResult = Utility.convertResultSetToList(rs);
			//con.close();
			//String result = new result[20];

		}catch (SQLException e) {


		}catch
				(Exception e)
		{
			e.printStackTrace();
		}


		return rs;
	}
	public ResultSet findAllId(Map params) {
		Connection con  = new SQLDataSource().getSqlConnection();
		List allResult = new ArrayList();




		ResultSet rs = null;
		try
		{
			con = new SQLDataSource().getSqlConnection();
			Statement s1 = con.createStatement();
			String sqlString = "SELECT top 1 * from hr_employee_t";
			 rs = s1.executeQuery(sqlString);

			//allResult = Utility.convertResultSetToList(rs);
			//con.close();
			//String result = new result[20];

		}catch (SQLException e) {


		}catch
				(Exception e)
		{
			e.printStackTrace();
		}


		return rs;
	}
//	public Comparator<Map<String, String>> mapComparator = new Comparator<Map<String, String>>() {
//		public int compare(Map<String, String> m1, Map<String, String> m2) {
//			return m1.get("id").compareTo(m2.get("id"));
//		}
//	};



	public List getRequisitionList() {
		List list = new ArrayList<>();
		String sql = "SELECT mail.id, mail.content, mail.note,"+
		"case when mail.status = false then 'Failed' else 'Success' end status"+
				",concat(to_char(to_timestamp(cast(mail.salary_month as char), 'MM'), 'Month'), ', ' , mail.salary_year) \"monthAndYear\" FROM mail";
		String a="select \n" +
			"mail.id,\n" +
				"mail.content content,\n" +
			"mail.content \"content\",\n" +
			"mail.note,\n" +
			"case when mail.status = false then 'Failed' else 'Success' end status,\n" +
			"concat(to_char(to_timestamp(to_char(mail.salary_month::int, '999'), 'MM'), 'Month'), ', ' , mail.salary_year) \"monthAndYear\"\n" +
			"  \n" +
			"from mail order by id desc\n" +
			"\n";
		try {

			return executeSQL(sql);
		} catch	(Exception e) {
			e.printStackTrace();
		}
		return  list;
	}

	public List findAllStudent(Map params) {
		String studentId = " 1=1 ";
		long offset = Long.parseLong(params.get("offSet").toString());
		if(params.get("studentId")!=null && params.get("studentId")!=""){
			studentId = " student_id =  '" + params.get("studentId") + "'";
		}



		String sql = "select \n" +
				"concat(first_name, ' ', last_name ) full_name,\n" +
				"email, \"password\", student_id, email personal_email\n" +
				"\n" +
				"from student_email_list_test \n" +
				"WHERE " + studentId + " and student_id is not null \n" +
				"order by student_id\n";

		return  executeSQL(sql);
	}
//	public List findAllStudent(Map params) {
//		String studentId = " 1=1 ";
//		long offset = Long.parseLong(params.get("offSet").toString());
//		if(params.get("studentId")!=null && params.get("studentId")!=""){
//			studentId = " student_id =  '" + params.get("studentId") + "'";
//		}
//
//
//
//		String sql = "select \n" +
//				"concat(first_name, ' ', last_name ) full_name,\n" +
//				"email, \"password\", student_id, email personal_email\n" +
//				"\n" +
//				"from student_email_list \n" +
//				"WHERE " + studentId + "\n" +
//				"order by student_id\n"+
//				"Limit 500 offset " + offset;
//
//		return  executeSQL(sql);
//	}
	public List findByStudentId(Map params) {
		String studentId = " 1=1 ";
		int offset = 0;
		if(params.get("studentId")!=null && params.get("studentId")!=""){
			studentId = " student_id =  '" + params.get("studentId") + "'";
		}
		String sql = "select \n" +
				"concat(first_name, ' ', last_name ) full_name,\n" +
				"email, \"password\", student_id, email personal_email\n" +
				"\n" +
				"from student_email_list \n" +
				"WHERE " + studentId + "\n" +
				"order by student_id\n";

		return  executeSQL(sql);
	}
	public Void sendAdmitCard() {

		try {
			List<ApplicantAdmitCardInformation> applicantAdmitCardInformationList = applicantAdmitCardDao.findAllByStatus(false);
			if(applicantAdmitCardInformationList.size() > 0){
				for (ApplicantAdmitCardInformation applicantAdmitCardInformation : applicantAdmitCardInformationList) {

					try {
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
						entityBuilder.addPart("to", new StringBody((applicantAdmitCardInformation.getEmail()), PLAIN_UTF8));
						entityBuilder.addPart("subject", new StringBody("Admit Card For Summer-2019", PLAIN_UTF8));
						entityBuilder.addPart("text", new StringBody("Dear Applicant,\nPlease find the attached file as your admit card for the semester Summer-2019.", PLAIN_UTF8));
						entityBuilder.addPart("attachment", new FileBody(new File("/home/rana/allAdmitcard/" + applicantAdmitCardInformation.getApplicantId() + ".pdf")));
						//entityBuilder.addBinaryBody("attachment", );

						//	entityBuilder.addBinaryBody("attachment", attachment.getInputStream());
						//	entityBuilder.addBinaryBody("attachment", input);

						httpPost.setEntity(entityBuilder.build());

						HttpResponse httpResponse = httpClient.execute(httpPost);
						if (httpResponse.getStatusLine().getStatusCode() == 200) {
							applicantAdmitCardInformation.setSend(true);
							applicantAdmitCardDao.save(applicantAdmitCardInformation);
						}
					}catch (FileNotFoundException a){
						applicantAdmitCardInformation.setComments("file not found for the applicant :: "+applicantAdmitCardInformation.getApplicantId());
						applicantAdmitCardDao.save(applicantAdmitCardInformation);
					}
				}
			}


		}catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}
}
