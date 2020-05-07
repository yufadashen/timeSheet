package com.intehel.service.admin.impl;

import com.intehel.common.util.LayUIResult;
import com.intehel.common.util.MD5;
import com.intehel.common.util.Result;
import com.intehel.common.util.ResultCode;
import com.intehel.dao.SysUserMapper;
import com.intehel.model.SysUser;
import com.intehel.service.admin.SysUserManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


/**
 * @Author：席林达
 * @Description：用户管理接口
 * @CreateDate：2019/11/20 16:23
 * @Version：
 */
@Service
public class SysUserManagementServiceImpl implements SysUserManagementService {

    @Autowired
    private SysUserMapper sysUserMapper;

    /**
     * @Author: 席林达
     * @Description : 根据条件查询系统用户列表
     * @param pageNo
     * @param pageSize
     * @param sysUserName
     * @param sysUserRealName
     * @param sysUserSex
     * @Return : java.util.List<com.intehel.model.SysUser>
     * @CreateDate: 2019/11/20 16:22
     * @Version V1
     */
    @Override
    public LayUIResult getSysUserList(Integer pageNo,Integer pageSize,String sysUserName,String sysUserRealName,Integer sysUserSex) {
        try {
            Integer pageNos = pageNo;
            Integer pageSizes = pageSize;
            if(pageNos == 0){
                pageNo = 1;
            }
            if(pageSizes == 0){
                pageSize = 10;
            }
            pageNo = (pageNo-1)*pageSize;
            if(null != sysUserName || null != sysUserRealName){
                if(pageNos == 0){
                    pageNo = 1;
                }
                if(pageSizes == 0){
                    pageSize = 10;
                }
            }
            List<SysUser> sysUsers = new ArrayList<>();
                    //根据条件查询列表
            sysUsers = sysUserMapper.getSysUserList(sysUserName,sysUserRealName,sysUserSex,pageNo,pageSize);
            if(sysUsers.size() == 0){
                pageNo = 1;
                pageSize = 10;
                sysUsers = sysUserMapper.getSysUserList(sysUserName,sysUserRealName,sysUserSex,pageNo,pageSize);
            }
            //查询列表总数
            int count = sysUserMapper.getsysUserCount(sysUserName,sysUserRealName,sysUserSex);
            LayUIResult sysUserListResult = new LayUIResult();
            sysUserListResult.setList(sysUsers);
            sysUserListResult.setTotals(count);
            sysUserListResult.setCode(200);
            return sysUserListResult;
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("系统用户查询失败");
        }
    }

    /**
     * @Author: 席林达
     * @Description : 新增系统用户
     * @Param :
     * @Return : com.intehel.common.util.LayUIResult
     * @CreateDate: 2019/11/20 16:49
     * @Version V1
     */
    @Override
    public Object addSysUser(SysUser sysUser) {
        try {
            //查询是否重复添加用户
            int cou = sysUserMapper.selectSysUserByUserName(sysUser.getSysUserName());
            if(cou == 1){
                return Result.error(ResultCode.SYS_USER_NAME_EXITENCE.desc(),ResultCode.SYS_USER_NAME_EXITENCE.code());
            }
            if(cou == 0){
                //将密码进行MD5加密
                String s = MD5.md5Str(sysUser.getSysUserPassword());
                sysUser.setSysUserPassword(s);
                int count = sysUserMapper.insertSelective(sysUser);
                if(count == 0){
                    return Result.error(ResultCode.SYS_USER_NAME_INSERT_FALSE.desc(),ResultCode.SYS_USER_NAME_INSERT_FALSE.code());
                }
            }
            return Result.success();
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("新增系统用户失败");
        }
    }

    /**
     * @author: 席林达
     * @description : 根据系统用户的ID查询对应的用户信息   用于修改用户
     * @param sysUserId
     * @return : com.intehel.common.util.LayUIResult
     * @createDate: 2019/11/20 23:35
     * @version V1
     */
    @Override
    public Object getSysUserById(Integer sysUserId) {
        try {
            SysUser sysUser = sysUserMapper.selectByPrimaryKey(sysUserId);
            if(null == sysUser){
                return Result.error(ResultCode.SYS_USER_NAME_NULL.desc(),ResultCode.SYS_USER_NAME_NULL.code());
            }
            return Result.success(sysUser);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("查询系统用户失败");
        }
    }

    /**
     * @author: 席林达
     * @description : 修改用户信息
     * @param sysUser
     * @return : com.intehel.common.util.LayUIResult
     * @createDate: 2019/11/20 23:47
     * @version V1
     */
    @Override
    public Object updateSysUserById(SysUser sysUser) {
        try {
            //判断传入的密码是否为原密码
            //查询原密码
            SysUser sysUserago = sysUserMapper.selectByPrimaryKey(sysUser.getSysUserId());
            //新密码与原密码作比较,若密码相同，则原样传入,否则进行MD5加密
            if(!sysUserago.getSysUserPassword().equals(sysUser.getSysUserPassword())){
                String s = MD5.md5Str(sysUser.getSysUserPassword());
                sysUser.setSysUserPassword(s);
            }
            int sysUsers = sysUserMapper.updateByPrimaryKeySelective(sysUser);
            if(sysUsers == 0){
                return Result.error(ResultCode.SYS_USER_UPDATE_FALSE.desc(),ResultCode.SYS_USER_UPDATE_FALSE.code());
            }
            return Result.success();
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("系统用户修改失败");
        }
    }

    /**
     * @author: 席林达
     * @description : 根据ID删除用户信息  此为逻辑删除，并非物理删除，修改删除状态即可
     * @param  sysUserId
     * @return : com.intehel.common.util.LayUIResult
     * @createDate: 2019/11/21 0:02
     * @version V1
     */
    @Override
    public Object delSysUserById(Integer sysUserId) {
        try {
            int sysUsers = sysUserMapper.delSysUserById(sysUserId);
            if(sysUsers == 0){
                return Result.error(ResultCode.SYS_USER_DELETE_FALSE.desc(),ResultCode.SYS_USER_DELETE_FALSE.code());
            }
            return Result.success();
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("系统用户信息删除失败");
        }
    }

}
