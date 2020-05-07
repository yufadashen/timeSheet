package com.intehel.dao.admin;


import com.intehel.model.admin.SysResource;
import org.springframework.stereotype.Component;

import java.util.List;

/**
*@Description 权限菜单Mapper
*@Author 侯森林
*@Date
*@Time
*/
@Component
public interface SysResourceMapper {
    int deleteByPrimaryKey(Integer resourceId);


    int insert(SysResource record);

    int insertSelective(SysResource record);

    SysResource selectByPrimaryKey(Integer resourceId);

    int updateByPrimaryKeySelective(SysResource record);

    int updateByPrimaryKey(SysResource record);
    /**
    *@Description 根据用户id获取用户权限
    *@Param userId用户id
    *@Return
    *@Author 侯森林
    *@Date
    *@Time
    */
    List<SysResource> findResourceByUserId(Integer userId);
    /**
    *@Description 根据角色id 获取菜单信息
    *@Param roleId 角色id
    *@Return
    *@Author 侯森林
    *@Date
    *@Time
    */
    List<SysResource> findResourceByRoleId(Integer roleId);

    /**
    *@Description 获取所有菜单信息
    *@Param
    *@Return
    *@Author 侯森林
    *@Date
    *@Time
    */
    List<SysResource> findAllResource();
}