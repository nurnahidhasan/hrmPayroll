package com.bracu.hrm.model;

import javax.persistence.*;

import org.hibernate.validator.constraints.NotEmpty;

import com.bracu.hrm.model.org.Company;
import com.bracu.hrm.model.org.Department;

import lombok.Data;

import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "app_user")
@Data
public class User implements Serializable{
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String username;
    private String password;
    
    @Transient
    private String passwordConfirm;
    
    @ManyToMany
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;
    @NotEmpty
	@Column(name="full_name", nullable=false)
	private String fullName;

	@NotEmpty
	@Column(name="EMAIL", nullable=false)
	private String email;
	
	
	@ManyToOne(targetEntity = Company.class, fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
	@JoinColumn(name =  "company_id",nullable = false)
	private Company company;
    
    
}

