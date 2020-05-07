package com.intehel.dao.admin;


import com.intehel.model.admin.SysRole;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
*@Description 角色dao层
*@Author 侯森林
*@Date
*@Time
*/
@Component
public interface SysRoleMapper {
    int deleteByPrimaryKey(Integer roleId);

    int insert(SysRole record);

    /**
    *@Description 新增角色
    *@Param record 角色信息
    *@Return
    *@Author 侯森林
    *@Date
    *@Time
    */
    int insertSelective(SysRole record);

    SysRole selectByPrimaryKey(Integer roleId);

    int updateByPrimaryKeySelective(SysRole record);

    /**
    *@Description 更新角色信息
    *@Param record 角色信息
    *@Return
    *@Author 侯森林
    *@Date
    *@Time
    */
    int updateByPrimaryKey(SysRole record);
    /**
    *@Description 根据用户id获取用户角色
    *@Param  userId 用户id
    *@Return 用户所拥有角色
    *@Author 侯森林
    *@Date
    *@Time
    */
    List<SysRole> selectRoleByUserId(Integer userId);
    /**
    *@Description 获取角色列表
    *@Param sysRole 角色信息
    *@Return
    *@Author 侯森林
    *@Date
    *@Time
    */
    List<SysRole> findRole(SysRole sysRole);
    /**
    *@Description 获取角色数量
    *@Param sysRole 角色信息
    *@Return
    *@Author 侯森林
    *@Date
    *@Time
    */
    Integer findRoleCount(SysRole sysRole);

    /**
    *@Description 根据角色名称获取角色信息
    *@Param sysrole 角色
    *@Return
    *@Author 侯森林
    *@Date
    *@Time
    */
    SysRole findRoleByName(SysRole sysRole);


    /**
    *@Description 角色授权
    *@Param roleId 角色菜单id list：权限id集合
    *@Return
    *@Author 侯森林
    *@Date
    *@Time
    */
    void grantResource(@Param("roleId") Integer roleId, @Param("list") List<Integer> list);

    /**
    *@Description 删除角色权限
    *@Param roleId 角色id
    *@Return
    *@Author 侯森林
    *@Date
    *@Time
    */
    void deleteResourceByRoleId(Integer roleId);
}