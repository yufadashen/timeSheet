package com.intehel.model.admin;

import java.util.List;

/**
 *@Description 菜单实体类
 *@Author 侯森林
 *@Date 2019-9-18
 */
public class Menu {
    String text;//菜单名称
    String url;//路径
    Integer id;//id
    Integer pId;//父id
    Integer menuOrder;//菜单顺序
    List<Menu> children;//子菜单列表

    public Integer getMenuOrder() {
        return menuOrder;
    }

    public void setMenuOrder(Integer menuOrder) {
        this.menuOrder = menuOrder;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getpId() {
        return pId;
    }

    public void setpId(Integer pId) {
        this.pId = pId;
    }

    public List<Menu> getChildren() {
        return children;
    }

    public void setChildren(List<Menu> children) {
        this.children = children;
    }
}
