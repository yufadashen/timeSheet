package com.intehel.service.admin;

import com.intehel.model.SysUser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @Author 余发
 * @Description:
 * @CreateDate:
 * @UpdateDate:
 * @Version: v1
 */
public interface SysUserService {

    /**
     * 登录验证
     * @param user 用户信息
     * @param request session
     * @return 成功，失败
     */
    String verify(SysUser user, HttpServletRequest request);

    /**修改密码*/
    String updatePassword(String oldPassword, String surePassword, HttpServletRequest request);
}
