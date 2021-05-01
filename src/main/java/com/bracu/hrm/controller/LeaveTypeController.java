package com.bracu.hrm.controller;

import com.bracu.hrm.model.leave.LeaveType;
import com.bracu.hrm.service.LeaveTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Locale;

/**
 * Created by HP on 1/14/2018.
 */
@Controller
@SessionAttributes("roles")
@RequestMapping("/leavetype")
public class LeaveTypeController {
    @Autowired
    private LeaveTypeService leaveTypeService;
    @Autowired
    MessageSource messageSource;

    @RequestMapping(value = { "/create" }, method = RequestMethod.GET)
    public String create(Model model) {
        model.addAttribute("leaveType",new LeaveType());
        return "leavetype/create";
    }

    @ResponseBody
    @RequestMapping(value="/save",method = RequestMethod.POST)
    public String save(@RequestBody LeaveType leaveType, BindingResult result, ModelMap model, Principal principal){
        String message;
        if(result.hasErrors()){
            return "leavetype/create";
        }
        else{
            if(!leaveTypeService.uniqueNameCheck(leaveType.getId(), leaveType.getName())){
                message = messageSource.getMessage("non.unique.name", new String[]{"Leave Type", leaveType.getName()}, Locale.getDefault());
                return message;
            }
            else{
                leaveTypeService.save(leaveType,principal);
            }

        }
        message = messageSource.getMessage("save.successful.message", new String[]{"Leave Type", leaveType.getName()}, Locale.getDefault());
        return message;

    }
    @ResponseBody
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public String getLeaveTypeList(ModelMap model){
        String subGroupJson = leaveTypeService.getLeaveTypeList();
        return subGroupJson ;
    }
    @ResponseBody
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public String edit(@PathVariable("id") String id, ModelMap model){
        String subGroupJson = leaveTypeService.getLeaveTypeById(Integer.parseInt(id));
        System.err.println(subGroupJson);
        return subGroupJson ;
    }
    @ResponseBody
    @RequestMapping(value = { "/update" }, method = RequestMethod.POST)
    public String update( @RequestBody LeaveType leaveType,
                          BindingResult resultItem) {
        System.err.println(leaveType);
        if (resultItem.hasErrors()) {
            return "leavetype/create";
        } else{
            return leaveTypeService.update(leaveType);
        }

        //return message;
    }
    @ResponseBody
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public String delete(@PathVariable("id") String id, ModelMap model){
        String subGroupJson = leaveTypeService.delete(Integer.parseInt(id));
        return subGroupJson ;
    }

}