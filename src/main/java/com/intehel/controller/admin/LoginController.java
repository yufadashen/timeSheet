package com.intehel.controller.admin;

import com.intehel.common.util.Result;
import com.intehel.model.SysUser;
import com.intehel.service.admin.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author 余发
 * @Description: 登录
 * @CreateDate:
 * @UpdateDate:
 * @Version: v1
 */
@Controller
@RequestMapping("admin")
public class LoginController {

    @Autowired
    SysUserService sysUserService;

    /**
     * 登录
     * @return
     */
    @RequestMapping("/login")
    public String showlogin(){
        return "admin/main/login";
    }

    /**
     * @Author 余发
     * @Description: 首页
     * @CreateDate: 2019/11/21 17:23
     * @Param:
     * @return:
     */
    @RequestMapping("/index")
    public String showindex(){
        return "admin/main/index";
    }
    /**
     * @Author 余发
     * @Description: 注销
     * @CreateDate: 2019/11/21 17:23
     * @Param:
     * @return:
     */

    @ResponseBody
    @RequestMapping("/logout")
    public String showlogout(HttpServletRequest request){
        try {
        request.getSession().setAttribute("SysUser",null);
        }catch (Exception e){
            e.printStackTrace();
            return Result.error("注销失败",80004);
        }
        return Result.success();
    }

    /**
     * @Author 余发
     * @Description: 验证登录
     * @CreateDate: 2019/11/21 17:23
     * @Param:
     * @return:
     */
    @ResponseBody
    @RequestMapping("/yanzheng")
    public String yanzheng(SysUser user , HttpServletRequest request){
        return sysUserService.verify(user,request);

    }
    /**
     * @Author 余发
     * @Description: 修改密码
     * @CreateDate: 2019/11/21 17:23
     * @Param:
     * @return:
     */
    @ResponseBody
    @RequestMapping("/updatePassword")
    public String updatePassword(String oldPassword,String surePassword,HttpServletRequest request){

        return sysUserService.updatePassword(oldPassword,surePassword,request);

    }
}
