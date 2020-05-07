package com.intehel.service.Impl;

import com.alibaba.fastjson.JSON;
import com.intehel.common.util.ExportExcel;
import com.intehel.common.util.LayUIResult;
import com.intehel.common.util.Result;
import com.intehel.dao.TimeSheetMapper;
import com.intehel.model.TimeSheet;
import com.intehel.service.TimeSheetService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * @author 余发
 * @version : v1
 * @description :
 */
@Service
public class TimeSheetServiceImpl implements TimeSheetService {

    @Autowired
    TimeSheetMapper timeSheetMapper;


    @Override
    public String addTimeSheet(TimeSheet timeSheet) {
        try {
            int i = timeSheetMapper.insertSelective(timeSheet);
            if (i == 1) {
                return Result.success();
            } else {
                return Result.error("失败", 1);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("失败", 1);
        }

    }

    @Override
    public String delTimeSheet(int id) {
        try {
            int i = timeSheetMapper.delById(id);
            if (i == 1) {
                return Result.success();
            } else {
                return Result.error("失败", 1);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("失败", 1);
        }
    }

    @Override
    public String updateTimeSheet(TimeSheet timeSheet) {
        try {
            int i = timeSheetMapper.updateTimeSheet(timeSheet);
            if (i == 1) {
                return Result.success();
            } else {
                return Result.error("失败", 1);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("失败", 1);
        }
    }

    @Override
    public LayUIResult selectList(String sysUserName, int pageNo, int pageSize) {
        try {


            Integer pageNos = pageNo;
            Integer pageSizes = pageSize;
            if (pageNos == 0) {
                pageNo = 1;
            }
            if (pageSizes == 0) {
                pageSize = 10;
            }
            pageNo = (pageNo - 1) * pageSize;
            List<TimeSheet> timeSheet1 = timeSheetMapper.timeSheetList(sysUserName, pageNo, pageSize);
            int count = timeSheetMapper.countNum(sysUserName, pageNo, pageSize);
            LayUIResult sysUserListResult = new LayUIResult();
            sysUserListResult.setList(timeSheet1);
            sysUserListResult.setTotals(count);
            sysUserListResult.setCode(0);
            return sysUserListResult;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void exportOutpatientRecord(TimeSheet timeSheet, String sheetDateStart,String sheetDateEnd ,HttpServletRequest request, HttpServletResponse response) {

        List<TimeSheet> List = timeSheetMapper.timeSheetLists(timeSheet.getSysUserRealName(),sheetDateStart,sheetDateEnd);
        //设置表头
        Map<String,String> titleMap = new LinkedHashMap<String,String>();
        titleMap.put("sysUserId","用户编号");
        titleMap.put("sysUserRealName","姓名");
        titleMap.put("sheetDate","日期");
        titleMap.put("projectId","项目ID");
        titleMap.put("projectCode","项目名称");
        titleMap.put("taskCode","工作任务");
        titleMap.put("workTime","工作时间");
        titleMap.put("overTime","超时时间");
        titleMap.put("remark","备注");
        List<Map<String, Object>> rowDataList = new ArrayList<>();
        Map<String, Object> aRowMap;
        for(TimeSheet timeSheet2 : List){
            aRowMap = new HashMap<String, Object>();
            aRowMap.put("sysUserId",timeSheet2.getSysUserId());
            aRowMap.put("sysUserRealName",timeSheet2.getSysUserRealName());
            aRowMap.put("sheetDate",timeSheet2.getSheetDate());
            aRowMap.put("projectId",timeSheet2.getProjectId());
            aRowMap.put("projectCode",timeSheet2.getProjectCode());
            aRowMap.put("taskCode",timeSheet2.getTaskCode());
            aRowMap.put("workTime",timeSheet2.getWorkTime());
            aRowMap.put("overTime",timeSheet2.getOverTime());
            aRowMap.put("remark",timeSheet2.getRemark());
            rowDataList.add(aRowMap);
        }

        ExportExcel.exportExcel(titleMap,rowDataList,"TimeSheet-"+timeSheet.getSysUserRealName(),"CRC中心工作日志",request,response);

    }

    @Override
    public String getDetailsMonth(String key,String sysUserId,Integer pageNo,int pageSize) {
        Integer pageNos = pageNo;
        Integer pageSizes = pageSize;
        if (pageNos == 0) {
            pageNo = 1;
        }
        if (pageSizes == 0) {
            pageSize = 10;
        }
        pageNo = (pageNo - 1) * pageSize;
        List<TimeSheet> List = timeSheetMapper.getDetailsMonth(key,sysUserId,pageNo,pageSize);
        if (List.size()!=0) {
            return Result.success(List);
        }else {
            return Result.error("暂无数据",-1);
        }
    }
}
