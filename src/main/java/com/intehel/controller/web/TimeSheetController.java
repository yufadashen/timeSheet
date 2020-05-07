package com.intehel.controller.web;

import com.intehel.common.util.LayUIResult;
import com.intehel.model.TimeSheet;
import com.intehel.service.TimeSheetService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 余发
 * @version : v1
 * @description : 工时统计控制层
 */
@RestController
@RequestMapping("/timeSheet")
public class TimeSheetController {


    @Autowired
    TimeSheetService timeSheetService;

    /**
     * 增加
     * @return
     */
    @RequestMapping(value = "/add",method = RequestMethod.GET)
    public String addTimeSheet(TimeSheet timeSheet){
        System.err.println(timeSheet);
        return timeSheetService.addTimeSheet(timeSheet);
    }

    /**
     * 删除
     * @return
     */
    @RequestMapping(value = "/deleteDetails",method = RequestMethod.GET)
    public String deleteTimeSheet(int id){
        return timeSheetService.delTimeSheet(id);
    }

    /**
     * 修改
     * @return
     */
    @RequestMapping(value = "/update",method = RequestMethod.GET)
    public String updateTimeSheet(TimeSheet timeSheet){


        return timeSheetService.updateTimeSheet(timeSheet);
    }

    /**
     * 查询
     * @return
     */
    @RequestMapping(value = "/selectList",method = RequestMethod.GET)
    public Object selectList(@Param("sysUserRealName") String sysUserRealName,@Param("sheetDateStart") String sheetDateStart,
                             @Param("sheetDateEnd") String sheetDateEnd , Integer pageNo, Integer pageSize){

        LayUIResult layUIResult = timeSheetService.selectList(sysUserRealName,sheetDateStart,sheetDateEnd,pageNo,pageSize);
        return layUIResult;
    }


    /**
     * 查询月详情
     * @return
     */
    @RequestMapping(value = "/getDetailsMonth",method = RequestMethod.GET)
    public Object selectList(String key,String sysUserId,Integer pageNo,int pageSize){

        return timeSheetService.getDetailsMonth(key,sysUserId,pageNo,pageSize);
    }

    /**
     * 查询日详情
     * @return
     */
    @RequestMapping(value = "/getDetails",method = RequestMethod.GET)
    public Object getDetails(String key,String sysUserId,Integer pageNo,int pageSize){

        return timeSheetService.getDetails(key,sysUserId,pageNo,pageSize);
    }


    /**
     * 根据id查询详情
     * @return
     */
    @RequestMapping(value = "/viewDetailsById",method = RequestMethod.GET)
    public String viewDetailsById(String id){

        return timeSheetService.viewDetailsById(id);
    }

    /**
     * 根据id修改详情
     * @return
     */
    @RequestMapping(value = "/updateDetailsById",method = RequestMethod.GET)
    public String updateDetailsById(TimeSheet timeSheet){

        return timeSheetService.updateDetailsById(timeSheet);
    }


    /**
     * 根据id查询有工时日期
     * @return
     */
    @RequestMapping(value = "/sheetListsById",method = RequestMethod.GET)
    public String sheetListsById(Integer id){

        return timeSheetService.sheetListsById(id);
    }


}
