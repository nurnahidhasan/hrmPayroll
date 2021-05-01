package com.bracu.hrm.controller;

import java.util.Locale;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.bracu.hrm.model.Employee;
import com.bracu.hrm.model.org.Company;
import com.bracu.hrm.service.CompanyService;

@Controller
@RequestMapping("/company")
@SessionAttributes("roles")
public class CompanyController {

	@Autowired
	CompanyService companyService;
	
	@Autowired
	MessageSource messageSource;

	
	@RequestMapping(value = { "/create","/new" }, method = RequestMethod.GET)
	public String create(ModelMap model) {
		//Employee employee = new Employee();
		//model.addAttribute("empForm", employee);
		return "company/create";
	}

	
	@RequestMapping(value = { "/save" }, method = RequestMethod.POST)
	public String save(@ModelAttribute("companyForm") @Valid Company company, BindingResult result,
			ModelMap model) {

		if (result.hasErrors()) {
			return "company/create";
		}

		if(!companyService.isUnique(company.getId(), company.getName())){
			FieldError pinError =new FieldError("employee","pin",messageSource.getMessage("non.unique.copnayname", new String[]{company.getName()}, Locale.getDefault()));
		    result.addError(pinError);
			return "employee/createEmployee";
		}
		
		companyService.save(company);

		model.addAttribute("successMsg", "Employee " + company.getName() +" ("+ company.getShortName()+")"+ " created successfully");
		//model.addAttribute("loggedinuser", getPrincipal());
		//return "success";

		return "company/create";
	}
}
