package com.intehel.service.admin;

import com.intehel.common.util.LayUIResult;
import com.intehel.common.util.Result;
import com.intehel.model.SysUser;

/**
 * @Author: 席林达
 * @Description : 用户管理接口
 * @CreateDate: 2019/11/20 16:21
 * @Version V1
*/
public interface SysUserManagementService {

    /**
     * getSysUserList
     * @author: 席林达
     * @description : 根据条件查询系统用户列表
     * @param pageNo
     * @param pageSize
     * @param sysUserName
     * @param sysUserRealName
     * @param sysUserSex
     * @return : java.util.List<com.intehel.model.SysUser>
     * @createDate: 2019/11/20 16:22
     * @Version V1
    */
    LayUIResult getSysUserList(Integer pageNo,Integer pageSize,String sysUserName,String sysUserRealName,Integer sysUserSex);

    /**
     * addSysUser
     * @author: 席林达
     * @description : 新增系统用户
     * @param sysUser
     * @return : com.intehel.common.util.LayUIResult
     * @CreateDate: 2019/11/20 16:49
     * @Version V1 
    */
    Object addSysUser(SysUser sysUser);

    /**
     * getSysUserById
     * @author: 席林达
     * @description : 根据系统用户的ID查询对应的用户信息   用于修改用户
     * @param sysUserId
     * @return : com.intehel.common.util.LayUIResult
     * @createDate: 2019/11/20 23:35
     * @version V1
     */
    Object getSysUserById(Integer sysUserId);

    /**
     * updateSysUserById
     * @author: 席林达
     * @description : 修改用户信息
     * @param sysUser
     * @return : com.intehel.common.util.LayUIResult
     * @createDate: 2019/11/20 23:47
     * @version V1
     */
    Object updateSysUserById(SysUser sysUser);

    /**
     * delSysUserById
     * @author: 席林达
     * @description  根据ID删除用户信息
     * @param  sysUserId
     * @return : com.intehel.common.util.LayUIResult
     * @createDate: 2019/11/21 0:02
     * @version V1
     */
    Object delSysUserById(Integer sysUserId);

}
