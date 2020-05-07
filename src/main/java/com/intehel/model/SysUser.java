package com.intehel.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * @author 席林达
 */
public class SysUser {
    /**用户ID*/
    private Integer sysUserId;
    /**用户名*/
    private String sysUserName;
    /**用户密码  MD5加密之后存储*/
    private String sysUserPassword;
    /**真实姓名*/
    private String sysUserRealName;
    /**性别 0：女  1：男*/
    private Integer sysUserSex;
    /**联系方式 手机号不超过11位*/
    private String sysUserTel;
    /**用户类型  0：超级用户  1：普通用户*/
    private Integer sysUserType;
    /**是否删除  0：正常  1：已删除*/
    private Integer isDel;
    /**备注*/
    private String note;
    /**创建时间*/
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    /**修改时间*/
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    public String getSysUserRealName() {
        return sysUserRealName;
    }

    public void setSysUserRealName(String sysUserRealName) {
        this.sysUserRealName = sysUserRealName;
    }

    public Integer getSysUserId() {
        return sysUserId;
    }

    public void setSysUserId(Integer sysUserId) {
        this.sysUserId = sysUserId;
    }

    public String getSysUserName() {
        return sysUserName;
    }

    public void setSysUserName(String sysUserName) {
        this.sysUserName = sysUserName == null ? null : sysUserName.trim();
    }

    public String getSysUserPassword() {
        return sysUserPassword;
    }

    public void setSysUserPassword(String sysUserPassword) {
        this.sysUserPassword = sysUserPassword == null ? null : sysUserPassword.trim();
    }

    public Integer getSysUserSex() {
        return sysUserSex;
    }

    public void setSysUserSex(Integer sysUserSex) {
        this.sysUserSex = sysUserSex;
    }

    public String getSysUserTel() {
        return sysUserTel;
    }

    public void setSysUserTel(String sysUserTel) {
        this.sysUserTel = sysUserTel;
    }

    public Integer getSysUserType() {
        return sysUserType;
    }

    public void setSysUserType(Integer sysUserType) {
        this.sysUserType = sysUserType;
    }

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
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

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "SysUser{" +
                "sysUserId=" + sysUserId +
                ", sysUserName='" + sysUserName + '\'' +
                ", sysUserPassword='" + sysUserPassword + '\'' +
                ", sysUserRealName='" + sysUserRealName + '\'' +
                ", sysUserSex=" + sysUserSex +
                ", sysUserTel='" + sysUserTel + '\'' +
                ", sysUserType=" + sysUserType +
                ", isDel=" + isDel +
                ", note='" + note + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}