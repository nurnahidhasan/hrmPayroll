package com.bracu.hrm.model;

import com.bracu.hrm.model.util.HrYear;
import lombok.Data;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;


/**
 * Created by HP on 1/14/2018.
 */
@MappedSuperclass
public abstract class BaseEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	private Integer version;
	
	
	@ManyToOne(targetEntity=User.class,fetch=FetchType.LAZY,cascade= CascadeType.DETACH)
	@JoinColumn(name="user_created_id")
	
    private User userCreated;
	@ManyToOne(targetEntity=User.class,fetch=FetchType.LAZY,cascade= CascadeType.DETACH)
	@JoinColumn(name="user_updated_id")
    private User userLastUpdated;
	
	
	@Column(name = "date_created")
    private Date dateCreate;
	@Column(name = "date_updated")
    private Date dateLastUpdate;
	
	
	
	
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
	public Date getDateCreate() {
		return dateCreate;
	}
	public void setDateCreate(Date dateCreate) {
		this.dateCreate = dateCreate;
	}
	public Date getDateLastUpdate() {
		return dateLastUpdate;
	}
	public void setDateLastUpdate(Date dateLastUpdate) {
		this.dateLastUpdate = dateLastUpdate;
	}



}
