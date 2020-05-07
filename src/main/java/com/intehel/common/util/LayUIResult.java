package com.intehel.common.util;

import java.util.List;

/**
 * @Author：席林达
 * @Description：laiui接收返回样式
 * @CreateDate：2019/11/20 16:14
 * @Version：
 */
public class LayUIResult {
    /**数据状态的字段名称 默认code*/
    private Integer code=200;

    /**总记录数量*/
    private Integer totals;

    /**列表内容*/
    private List<?> list;

    /**开始日期*/
    private String regDate;

    /**结束日期*/
    private String regDateEnd;

    /**提示错误信息*/
    private String msg;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Integer getTotals() {
        return totals;
    }

    public void setTotals(Integer totals) {
        this.totals = totals;
    }

    public List <?> getList() {
        return list;
    }

    public void setList(List <?> list) {
        this.list = list;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getRegDate() {
        return regDate;
    }

    public void setRegDate(String regDate) {
        this.regDate = regDate;
    }

    public String getRegDateEnd() {
        return regDateEnd;
    }

    public void setRegDateEnd(String regDateEnd) {
        this.regDateEnd = regDateEnd;
    }

    @Override
    public String toString() {
        return "PageDataResult{" +
                "code=" + code +
                ", totals=" + totals +
                ", list=" + list +
                ", msg='" + msg + '\'' +
                ", regDate=" + regDate +
                ", regDateEnd='" + regDateEnd + '\'' +
                '}';
    }
}
