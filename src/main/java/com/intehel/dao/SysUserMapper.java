package com.intehel.dao;


import com.intehel.model.SysUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
/**
 * @Author：席林达
 * @Description：用户管理接口
 * @CreateDate：2019/11/20 16:27
 * @Version：
 */
@Component
public interface SysUserMapper {

    /**
     * getSysUserList根据条件查询系统用户列表
     * @Author: 席林达
     * @param sysUserName
     * @param sysUserRealName
     * @param sysUserSex
     * @param pageNo
     * @param pageSize
     * @return : java.util.List<com.intehel.model.SysUser>
     * @createDate : 2019/11/20 16:22
     * @Version V1
     */
    List<SysUser> getSysUserList(@Param("sysUserName") String sysUserName,@Param("sysUserRealName") String sysUserRealName,@Param("sysUserSex") Integer sysUserSex,
                                 @Param("pageNo") Integer pageNo,@Param("pageSize") Integer pageSize);

    /**
     * getsysUserCount根据条件查询系统用户列表总数
     * @Author: 席林达
     * @param sysUserName
     * @param sysUserRealName
     * @param sysUserSex
     * @return :
     * @CreateDate: 2019/11/20 16:22
     * @Version V1
     */
    int getsysUserCount(@Param("sysUserName") String sysUserName,@Param("sysUserRealName") String sysUserRealName,@Param("sysUserSex") Integer sysUserSex);

    /**
     * deleteByPrimaryKey
     * @author: 席林达
     * @param sysUserId
     * @return int
     * @createDate: 2019/11/25 11:02
     * @version V1
    */
    int deleteByPrimaryKey(Integer sysUserId);

    /**
     * insert
     * @author: 席林达
     * @param record
     * @return int
     * @createDate: 2019/11/25 11:02
     * @version V1
    */
    int insert(SysUser record);

    /**
     * selectSysUserByUserName查询是否重复添加用户
     * @author: 席林达
     * @param  sysUserName
     * @return : int
     * @createDate: 2019/11/21 14:57
     * @version V1
    */
    int selectSysUserByUserName(String sysUserName);

    /**
     * 新增系统用户insertSelective
     * @Author: 席林达
     * @param record
     * @return : int
     * @CreateDate: 2019/11/20 16:49
     * @Version V1
     */
    int insertSelective(SysUser record);

    /**
     * selectByPrimaryKey根据系统用户的ID查询对应的用户信息   用于修改用户
     * @author: 席林达
     * @param  sysUserId
     * @return : com.intehel.model.SysUser
     * @createDate: 2019/11/20 23:40
     * @version V1
     */
    SysUser selectByPrimaryKey(Integer sysUserId);

    /**
     * updateByPrimaryKeySelective修改用户信息
     * @author: 席林达
     * @param record
     * @return : com.intehel.common.util.LayUIResult
     * @createDate: 2019/11/20 23:47
     * @version V1
     */
    int updateByPrimaryKeySelective(SysUser record);

    /**
     * delSysUserById根据ID删除用户信息
     * @author: 席林达
     * @param  sysUserId
     * @return : int
     * @createDate: 2019/11/21 0:05
     * @version V1
    */
    int delSysUserById(Integer sysUserId);

    /**
     * updateByPrimaryKey
     * @author: 席林达
     * @param record
     * @return int
     * @createDate: 2019/11/25 11:01
     * @version V1
    */
    int updateByPrimaryKey(SysUser record);

    /**
     * selectSysUserByName
     * @author: 席林达
     * @param sysUserName
     * @return com.intehel.model.SysUser
     * @createDate: 2019/11/25 11:01
     * @version V1
    */
    SysUser selectSysUserByName(String sysUserName);

    /**
     * updateByIdUpdatePassword根据id修改密码
     * @param sysUserId 用户id
     * @param oldPassword 新密码
     * @return 1成功 0失败
     */
    int updateByIdUpdatePassword(@Param("sysUserId") Integer sysUserId,@Param("sysUserPassword") String oldPassword);
}