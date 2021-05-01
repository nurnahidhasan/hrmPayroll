package com.bracu.hrm.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import jdk.nashorn.internal.objects.annotations.Getter;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import com.bracu.hrm.model.org.Designation;
import com.bracu.hrm.model.settings.SetupEntity;

@Entity
@Table(name = "employee")
public class Employee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	private Integer version;
	
	@NotEmpty
	@Column(name = "pin", nullable = false, unique = true)
	private String pin;
	
	@NotEmpty
	@Column(name = "full_name", nullable = false)
	private String fullName;

	@NotEmpty
	@Column(name = "email", nullable = true)
	private String email;
	
	@Column(name = "father_name")
	private String fatherName;
	@Column(name = "mother_name")
	private String motherName;
	@Column(name = "spouse_name")
	private String spouseName;

	@DateTimeFormat(pattern = "dd-MM-yyyy")
	@Temporal(TemporalType.DATE)
	@Column(name = "date_birth", nullable = false)
	private Date dateOfBirith;

	@Column(name = "date_joining")
	private Date dateOfJoining;
	@Column(name = "date_confirmation")
	private Date dateOfConfirmation;
	
	@ManyToOne(targetEntity=SetupEntity.class,fetch=FetchType.LAZY,cascade= CascadeType.DETACH)
	@JoinColumn(name="gender_id")
	private SetupEntity gender;
	
	@ManyToOne(targetEntity=SetupEntity.class,fetch=FetchType.LAZY,cascade= CascadeType.DETACH)
	@JoinColumn(name="country_id")
	private SetupEntity country;
	
	@ManyToOne(targetEntity=SetupEntity.class,fetch=FetchType.LAZY,cascade= CascadeType.DETACH)
	@JoinColumn(name="nationality_id")
	private SetupEntity nationality;
	
	
	@ManyToOne(targetEntity=SetupEntity.class,fetch=FetchType.LAZY,cascade= CascadeType.DETACH)
	@JoinColumn(name="religion_id")
	private SetupEntity religion;
	
	@ManyToOne(targetEntity=SetupEntity.class,fetch=FetchType.LAZY,cascade= CascadeType.DETACH)
	@JoinColumn(name="marrital_status_id")
	private SetupEntity marritalStatus;
	

	private String nid;
	private String tin;
	
	private String passport;
	
	@Column(name="driving_license")
	private String drivingLicense;
	
	
	@ManyToOne(targetEntity = Designation.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "current_designation_id")
	private Designation currentDesignation;
	
	
	@ManyToOne(targetEntity = User.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id")
	private User user;
	
	@ManyToOne(targetEntity = Employee.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "reported_employee")
	private Employee reportedEmployee;
	
	
	
	@ManyToOne(targetEntity = User.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "created_user_id")
	private User userCreated;
	
	@ManyToOne(targetEntity = User.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "updated_user_id")
	private User userLastUpdated;
	
	
	@Column(name = "date_created")
	private Date dateCreated;
	
	@Column(name = "date_updated")
	private Date dateLastUpdated;
	
	
	
	
	
	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public Integer getVersion() {
		return version;
	}


	public void setVersion(Integer version) {
		this.version = version;
	}


	public String getPin() {
		return pin;
	}


	public void setPin(String pin) {
		this.pin = pin;
	}


	public String getFullName() {
		return fullName;
	}


	public void setFullName(String fullName) {
		this.fullName = fullName;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getFatherName() {
		return fatherName;
	}


	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}


	public String getMotherName() {
		return motherName;
	}


	public void setMotherName(String motherName) {
		this.motherName = motherName;
	}


	public String getSpouseName() {
		return spouseName;
	}


	public void setSpouseName(String spouseName) {
		this.spouseName = spouseName;
	}


	public Date getDateOfBirith() {
		return dateOfBirith;
	}


	public void setDateOfBirith(Date dateOfBirith) {
		this.dateOfBirith = dateOfBirith;
	}


	public Date getDateOfJoining() {
		return dateOfJoining;
	}


	public void setDateOfJoining(Date dateOfJoining) {
		this.dateOfJoining = dateOfJoining;
	}


	public Date getDateOfConfirmation() {
		return dateOfConfirmation;
	}


	public void setDateOfConfirmation(Date dateOfConfirmation) {
		this.dateOfConfirmation = dateOfConfirmation;
	}


	public SetupEntity getGender() {
		return gender;
	}


	public void setGender(SetupEntity gender) {
		this.gender = gender;
	}


	public SetupEntity getCountry() {
		return country;
	}


	public void setCountry(SetupEntity country) {
		this.country = country;
	}


	public SetupEntity getNationality() {
		return nationality;
	}


	public void setNationality(SetupEntity nationality) {
		this.nationality = nationality;
	}


	public SetupEntity getReligion() {
		return religion;
	}


	public void setReligion(SetupEntity religion) {
		this.religion = religion;
	}


	public Designation getCurrentDesignation() {
		return currentDesignation;
	}


	public void setCurrentDesignation(Designation currentDesignation) {
		this.currentDesignation = currentDesignation;
	}


	public SetupEntity getMarritalStatus() {
		return marritalStatus;
	}


	public void setMarritalStatus(SetupEntity marritalStatus) {
		this.marritalStatus = marritalStatus;
	}


	public String getNid() {
		return nid;
	}


	public void setNid(String nid) {
		this.nid = nid;
	}


	public String getTin() {
		return tin;
	}


	public void setTin(String tin) {
		this.tin = tin;
	}


	public String getPassport() {
		return passport;
	}


	public void setPassport(String passport) {
		this.passport = passport;
	}


	public String getDrivingLicense() {
		return drivingLicense;
	}


	public void setDrivingLicense(String drivingLicense) {
		this.drivingLicense = drivingLicense;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public Employee getReportedEmployee() {
		return reportedEmployee;
	}


	public void setReportedEmployee(Employee reportedEmployee) {
		this.reportedEmployee = reportedEmployee;
	}


	public User getUserCreated() {
		return userCreated;
	}


	public void setUserCreated(User userCreated) {
		this.userCreated = userCreated;
	}


	public User getUserLastUpdated() {
		return userLastUpdated;
	}


	public void setUserLastUpdated(User userLastUpdated) {
		this.userLastUpdated = userLastUpdated;
	}


	public Date getDateCreated() {
		return dateCreated;
	}


	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}


	public Date getDateLastUpdated() {
		return dateLastUpdated;
	}


	public void setDateLastUpdated(Date dateLastUpdated) {
		this.dateLastUpdated = dateLastUpdated;
	}
	
	
	
}
