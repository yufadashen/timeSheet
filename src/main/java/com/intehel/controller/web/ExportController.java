package com.intehel.controller.web;

import com.intehel.model.TimeSheet;
import com.intehel.service.TimeSheetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 余发
 * @version : v1
 * @description : 导出为表格
 */
@Controller
@RequestMapping("/export")
public class ExportController {

    @Autowired
    TimeSheetService timeSheetService;
    @RequestMapping("/exportOutpatientRecord")
    @ResponseBody
    @CrossOrigin
    public void exportRegisterRecord(TimeSheet timeSheet,String sheetDateStart,String sheetDateEnd ,
                                     HttpServletRequest request, HttpServletResponse response) {
        try {
            timeSheetService.exportOutpatientRecord(timeSheet,sheetDateStart,sheetDateEnd,request,response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
