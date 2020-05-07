package com.intehel.service.admin.impl;

import com.intehel.common.util.MD5;
import com.intehel.common.util.Result;
import com.intehel.common.util.ResultCode;
import com.intehel.dao.SysUserMapper;
import com.intehel.model.SysUser;
import com.intehel.service.admin.SysUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author 余发
 * @Description:
 * @CreateDate:
 * @UpdateDate:
 * @Version: v1
 */
@Service
public class SysUserServiceImpl implements SysUserService {
    private static final Logger logger = LoggerFactory.getLogger(SysUserServiceImpl.class);
    @Autowired
    SysUserMapper sysUserMapper;
    @Override
    public String verify(SysUser user, HttpServletRequest request) {
        //根据用户名去数据库查询用户密码
        String userName = user.getSysUserName();
        SysUser sysUser = sysUserMapper.selectSysUserByName(userName);
        if (sysUser==null){
            return Result.error(ResultCode.SYS_USER_NAME_NULL.desc(),ResultCode.SYS_USER_NAME_NULL.code());
        }
        //对password进行md5加密再赋值
        String password = MD5.md5Str(user.getSysUserPassword());
        logger.info("用户输入密码="+password +"   数据库密码="+sysUser.getSysUserPassword());
        //密码判断
        if(sysUser.getSysUserPassword().equals(password)){
            request.getSession().setAttribute("SysUser", sysUser);
            //session超时时间，单位：秒
            request.getSession().setMaxInactiveInterval(36000);
            logger.info("sysUserName="+user.getSysUserName());
            //先添加到session,在跳转
            logger.info(userName+"密码正确");
            return Result.success();
        }else {
            return Result.error(ResultCode.SYS_USER_PASSWORD_ERROR.desc(),ResultCode.SYS_USER_PASSWORD_ERROR.code());
        }
    }

    @Override
    public String updatePassword(String oldPassword, String surePassword, HttpServletRequest request) {
        logger.info("修改用户密码入参 原密码oldPassword = "+oldPassword+" , 新密码surePassword = "+surePassword);
        try {
            //判断是否存在session，如果不存在，跳转到登录页面重新登录
            if (request.getSession().getAttribute("SysUser") == null) {
                return Result.error(ResultCode.SYS_USER_LOGIN_OVERTTIME.desc(), ResultCode.SYS_USER_LOGIN_OVERTTIME.code());
            }
            SysUser sysUser = (SysUser) request.getSession().getAttribute("SysUser");
            //获取session里面的用户信息
            String sysUserPassword = sysUser.getSysUserPassword();
            //传参原密码是否有等于session里的密码，
            if (sysUserPassword.equals(MD5.md5Str(oldPassword))) {
                //如果相同，获取传参新密码加密后对用户密码进行更新
                int status = sysUserMapper.updateByIdUpdatePassword(sysUser.getSysUserId(), MD5.md5Str(surePassword));
                if (status == 1) {
                    return Result.success();
                } else {
                    return Result.error(ResultCode.SYS_USER_UPDATE_PASSWORD_ERROR.desc(), ResultCode.SYS_USER_UPDATE_PASSWORD_ERROR.code());
                }
            } else {
                //如果不同，提示用户密码错误
                return Result.error(ResultCode.SYS_USER_PASSWORD_ERROR.desc(), ResultCode.SYS_USER_PASSWORD_ERROR.code());
            }
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("修改密码异常");
        }
    }
}
