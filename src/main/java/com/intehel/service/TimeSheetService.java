package com.intehel.service;

import com.intehel.common.util.LayUIResult;
import com.intehel.model.TimeSheet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface TimeSheetService {
    String addTimeSheet(TimeSheet timeSheet);

    String delTimeSheet(int id);

    String updateTimeSheet(TimeSheet timeSheet);

    LayUIResult selectList(String sysUserRealName,String sheetDateStart,String sheetDateEnd ,int pageNo, int pageSize);

    /**
     * 导出表格
     * @return
     */
    void exportOutpatientRecord( TimeSheet timeSheet,String sheetDateStart,String sheetDateEnd , HttpServletRequest request, HttpServletResponse response);

    String getDetailsMonth(String key,String sysUserId,Integer pageNo,int pageSize);

    String viewDetailsById(String id);

    String updateDetailsById(TimeSheet timeSheet);

    Object getDetails(String key, String sysUserId, Integer pageNo, int pageSize);

    String sheetListsById(int id);
}
