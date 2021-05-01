package com.bracu.hrm.controller;

import com.bracu.hrm.model.util.HrYear;
import com.bracu.hrm.service.HrYearService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

@Controller
@SessionAttributes("roles")
@RequestMapping("/hryear")
public class HrYearController {
    @Autowired
    private HrYearService hrYearService;
    @Autowired
    MessageSource messageSource;

    @RequestMapping(value = { "/create" }, method = RequestMethod.GET)
    public String create(Model model) {
        model.addAttribute("hrYear",new HrYear());
        return "hryear/create";
    }

    @ResponseBody
    @RequestMapping(value="/save",method = RequestMethod.POST)
    public String save(@RequestBody HrYear hrYear, BindingResult result, ModelMap model){
        System.err.println(hrYear.getDateFrom());
        String message;
        if(result.hasErrors()){
            return "hryear/create";
        }
        else{
            hrYearService.save(hrYear);
            }
        message = messageSource.getMessage("save.successful.message", new String[]{"Human Resource Year ", String.valueOf(hrYear.getDateFrom())}, Locale.getDefault());
        return message;

    }
    @ResponseBody
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public String getList(ModelMap model){
        String list = hrYearService.getlist();
        return list ;
    }

}