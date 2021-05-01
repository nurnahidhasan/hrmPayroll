package com.bracu.hrm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DashboardController {

    @RequestMapping(value = "/dashboard", method = RequestMethod.GET)
    public ModelAndView admin() {
    	ModelAndView model = new ModelAndView();
    	model.setViewName("admin");
    	return model;
    }
    
    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public ModelAndView user() {
    	ModelAndView model = new ModelAndView();
    	model.setViewName("user");
    	return model;
    }

}