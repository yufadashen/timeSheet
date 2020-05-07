package com.intehel.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 余发
 * @version : v1
 * @description :
 */
@Controller
@RequestMapping("/jump")
public class JumpLoginController {

    @RequestMapping("login")
    public String loginSh(){
        return "web/login";
    }
    @RequestMapping("/index")
    public String indexSh(){
        return "web/index";
    }
    @RequestMapping("addRecording")
    public String addRecording(){
        return "web/addTimeSheet";
    }
    @RequestMapping("/timesheet/list")
    public String timesheetList(){
        return "admin/timesheet/list";
    }
    @RequestMapping("/detailsMonth")
    public String datailsMonth(){
        return "web/detailsMonth";
    }
}
