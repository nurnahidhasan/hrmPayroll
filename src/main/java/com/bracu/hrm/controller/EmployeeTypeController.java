package com.bracu.hrm.controller;

import com.bracu.hrm.model.settings.EmployeeType;
import com.bracu.hrm.service.EmployeeTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

@Controller
@RequestMapping("/emptype")
public class EmployeeTypeController {

    @Autowired
    private EmployeeTypeService employeeTypeService;

    @Autowired
    private MessageSource messageSource;

    @RequestMapping(value = {"create", "new", "view"}, method = RequestMethod.GET)
    public String create(Model model) {
        return "employeeType/empTypeCreate";
    }

    @ResponseBody
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(@RequestBody EmployeeType employeeType, BindingResult result, ModelMap modelMap) {
        String message;
        if (result.hasErrors()) {
            return "employeeType/empTypeCreate";
        } else {
            if(!employeeTypeService.uniqueNameCheck(employeeType.getId(), employeeType.getName())){
                message = messageSource.getMessage("non.unique.name", new String[]{"Employee Type Information", employeeType.getName()}, Locale.getDefault());
                return message;
            }
            else{
                    employeeTypeService.save(employeeType);

            }
        }
        message = messageSource.getMessage("save.successful.message", new String[]{"Employee Type Information", employeeType.getName()}, Locale.getDefault());
        return message;
    }

    @ResponseBody
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public String list(ModelMap modelMap) {
        String subGroupJson = employeeTypeService.getEmployeeTypeList();
//        System.err.println(subGroupJson);
        return subGroupJson;
    }

    @ResponseBody
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public String edit(@PathVariable("id") String id, ModelMap modelMap) {
        String subGroupJson = employeeTypeService.getEmployeeTypeById(Integer.parseInt(id));
        return subGroupJson;
    }

    @ResponseBody
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public  String update(@RequestBody EmployeeType employeeType, BindingResult result){
//        System.err.println();
        // unique name test
        if (result.hasErrors()) {
            return "employeeType/empTypeCreate";
        } else{
            return employeeTypeService.update(employeeType);
        }
    }

    @ResponseBody
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public String delete(@PathVariable("id") String id, ModelMap model){
        String subGroupJson = employeeTypeService.delete(Integer.parseInt(id));
        return subGroupJson ;
    }
}
