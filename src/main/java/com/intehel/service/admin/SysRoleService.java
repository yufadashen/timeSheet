package com.intehel.service.admin;

import com.intehel.model.admin.SysRole;

import java.util.List;

/**
*@Description 角色管理service
*@Author 侯森林
*@Date
*@Time
*/
public interface SysRoleService {
    /**
    *@Description 获取角色列表
    *@Param role：角色信息 pageNumber :页数 pageSize：每页大小
    *@Return 角色列表
    *@Author 侯森林
    *@Date
    *@Time
    */
    List<SysRole> findRole(SysRole role, Integer pageNumber, Integer pageSize);

    /**
    *@Description 获取角色数量
    *@Param sysRole 角色信息
    *@Return Integer符合条件的角色数量
    *@Author 侯森林
    *@Date
    *@Time
    */
    Integer countRoleList(SysRole sysRole);

    /**
    *@Description 新增角色
    *@Param sysrole 角色信息
    *@Return
    *@Author 侯森林
    *@Date
    *@Time
    */
    void insertRole(SysRole sysRole);

    /**
    *@Description 更新角色
    *@Param sysRole 角色信息
    *@Return
    *@Author 侯森林
    *@Date
    *@Time
    */
    void updateRoleById(SysRole sysRole);

    /**
    *@Description 根据角色名称获取角色信息
    *@Param role 角色信息
    *@Return
    *@Author 侯森林
    *@Date
    *@Time
    */
    SysRole findRoleByRoleName(SysRole role);


    /**
    *@Description 角色授权
    *@Param
    *@Return
    *@Author 侯森林
    *@Date
    *@Time
    */
    void grandResource(Integer roleId, List<Integer> list);

    /**
    *@Description 解除角色权限
    *@Param roleId　角色id
    *@Return
    *@Author 侯森林
    *@Date
    *@Time
    */
   void deleteResourceByRoleId(Integer roleId);

   /**
   *@Description 根据角色id获取菜单
   *@Param roleId 角色id
   *@Return
   *@Author 侯森林
   *@Date
   *@Time
   */
    Object findResourceByRoleId(Integer roleId);

    /**
    *@Description 删除角色
    *@Param role 角色
    *@Return
    *@Author 侯森林
    *@Date
    *@Time
    */
    void deleteRole(SysRole role);
}
