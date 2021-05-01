package com.bracu.hrm.controller;

import com.bracu.hrm.service.DepartmentService;
import com.bracu.hrm.service.payslip.PaySlipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Nahid on 07-Jan-18.
 */
@Controller
@RequestMapping("/admitCard")
public class StudentAdmitCardController {
	@Autowired
	PaySlipService paySlipService;
	@Autowired
	DepartmentService departmentService;


	@RequestMapping(value = { "/email" }, method = RequestMethod.GET)
	public String create(ModelMap model) {
		return "studentAdmitCard/admitCard";
	}


	@ResponseBody
	@RequestMapping(value = { "/printAlladmitCard" }, method = RequestMethod.GET)
	public String list(ModelMap model) {
		String subGroupJson = paySlipService.sendAdmitCard();
		return subGroupJson ;
	}



}
