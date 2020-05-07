package com.intehel.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 用户对象模型，不要使用该类
 * @author SUNF
 *
 */
@ApiModel(value="用户对象模型")
public class DemoUser {
    @ApiModelProperty(value="id" ,required=true)
    private Integer id;
    @ApiModelProperty(value="用户姓名" ,required=true)
    private String name;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "DemoUser [id=" + id + ", name=" + name + "]";
    }

}