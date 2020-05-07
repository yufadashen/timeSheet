package com.intehel.model;

import java.util.Date;

/**
 * @author 余发
 * @version : v1
 * @description : 工时实体类
 */
public class TimeSheet {

    //主键id
    private Integer id;
    //用户id
    private Integer sysUserId;
    //用户id
    private String sysUserRealName;
    //项目id
    private Integer projectId;
    //工时日期
    private String sheetDate;
    //项目名称
    private String projectCode;
    //任务id
    private Integer taskId;
    //任务名称
    private String taskCode;
    //工作时间
    private Double workTime;
    //超时时间
    private Double overTime;
    //备注
    private String remark;
    //创建时间
    private Date createTime;
    //修改时间
    private Date updateTime;

    @Override
    public String toString() {
        return "TimeSheet{" +
                "id=" + id +
                ", sysUserId=" + sysUserId +
                ", sysUserRealName='" + sysUserRealName + '\'' +
                ", projectId=" + projectId +
                ", sheetDate='" + sheetDate + '\'' +
                ", projectCode='" + projectCode + '\'' +
                ", taskId=" + taskId +
                ", taskCode='" + taskCode + '\'' +
                ", workTime=" + workTime +
                ", overTime=" + overTime +
                ", remark='" + remark + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }

    public String getSysUserRealName() {
        return sysUserRealName;
    }

    public void setSysUserRealName(String sysUserRealName) {
        this.sysUserRealName = sysUserRealName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSysUserId() {
        return sysUserId;
    }

    public void setSysUserId(Integer sysUserId) {
        this.sysUserId = sysUserId;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public String getTaskCode() {
        return taskCode;
    }

    public void setTaskCode(String taskCode) {
        this.taskCode = taskCode;
    }

    public Double getWorkTime() {
        return workTime;
    }

    public void setWorkTime(Double workTime) {
        this.workTime = workTime;
    }

    public Double getOverTime() {
        return overTime;
    }

    public void setOverTime(Double overTime) {
        this.overTime = overTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public String getSheetDate() {
        return sheetDate;
    }

    public void setSheetDate(String sheetDate) {
        this.sheetDate = sheetDate;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
