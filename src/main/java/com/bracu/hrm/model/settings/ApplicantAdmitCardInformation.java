package com.bracu.hrm.model.settings;

import com.bracu.hrm.model.Employee;

import javax.persistence.*;

@Entity
@Table(name = "applicant_admit_card_information")
public class ApplicantAdmitCardInformation {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(name = "applicant_id", nullable = false, unique = true)
	private String applicantId;

	@Column(name = "email", nullable = false, unique = true)
	private String email;

	@Column(name = "applicationProcessId", nullable = true, unique = false)
	private String application_process_id;

	@Column(name = "comments", nullable = true, unique = false)
	private String comments;

	@Column
	private Boolean isSend;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getApplicantId() {
		return applicantId;
	}

	public void setApplicantId(String applicantId) {
		this.applicantId = applicantId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getApplication_process_id() {
		return application_process_id;
	}

	public void setApplication_process_id(String application_process_id) {
		this.application_process_id = application_process_id;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public Boolean getSend() {
		return isSend;
	}

	public void setSend(Boolean send) {
		isSend = send;
	}
}
