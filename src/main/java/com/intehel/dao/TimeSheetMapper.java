package com.intehel.dao;

import com.intehel.model.TimeSheet;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface TimeSheetMapper {

    int updateTimeSheet(TimeSheet timeSheet);
    int insertSelective(TimeSheet timeSheet);
    int delById(@Param("id") int id);
    List<TimeSheet> timeSheetList(String sysUserName, @Param("pageNo") Integer pageNo, @Param("pageSize") Integer pageSize);

    int countNum(@Param("sysUserName") String sysUserName,@Param("pageNo")  int pageNo,@Param("pageSize")  int pageSize);

    List<TimeSheet> timeSheetLists(@Param("sysUserRealName")  String sysUserRealName,@Param("sheetDateStart")  String sheetDateStart,@Param("sheetDateEnd")  String sheetDateEnd);

    List<TimeSheet> getDetailsMonth(@Param("keys")String keys,@Param("sysUserId")String sysUserId ,@Param("pageNo")   int pageNo,@Param("pageSize")  int pageSize);
}
