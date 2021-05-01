package com.bracu.hrm.controller;

import com.bracu.hrm.model.settings.JobLevel;
import com.bracu.hrm.service.JobLevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

@Controller
@RequestMapping("/joblevel")
public class JobLevelController {

    @Autowired
    private JobLevelService jobLevelService;

    @Autowired
    private MessageSource messageSource;

    @RequestMapping(value = {"create", "new", "view"}, method = RequestMethod.GET)
    public String create(Model model) {
        return "jobLevel/jobLevelCreate";
    }

    @ResponseBody
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(@RequestBody JobLevel jobLevel, BindingResult result, ModelMap modelMap) {
        String message;
        if (result.hasErrors()) {
            return "jobLevel/jobLevelCreate";
        } else {
//            if(!jobLevelService.uniqueNameCheck(jobLevel.getId(), jobLevel.getLevel())){
//                message = messageSource.getMessage("non.unique.name", new String[]{"Job Level Information", jobLevel.getLevel()}, Locale.getDefault());
//                return message;
//            }
//            else{
                jobLevelService.save(jobLevel);
//            }
        }
        message = messageSource.getMessage("save.successful.message", new String[]{"Job Level Information", jobLevel.getLevel()}, Locale.getDefault());
        return message;
    }

    @ResponseBody
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public String list(ModelMap modelMap) {
        String subGroupJson = jobLevelService.getJobLevelList();
//        System.err.println(subGroupJson);
        return subGroupJson;
    }

    @ResponseBody
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public String edit(@PathVariable("id") String id, ModelMap modelMap) {
        String subGroupJson = jobLevelService.getJobLevelById(Integer.parseInt(id));
        return subGroupJson;
    }

    @ResponseBody
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public  String update(@RequestBody JobLevel jobLevel, BindingResult result){
//        System.err.println(jobLevel.getName());
        if (result.hasErrors()) {
            return "jobLevel/jobLevelCreate";
        } else{
            return jobLevelService.update(jobLevel);
        }
    }

    @ResponseBody
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public String delete(@PathVariable("id") String id, ModelMap model){
        String subGroupJson = jobLevelService.delete(Integer.parseInt(id));
        return subGroupJson ;
    }
}
