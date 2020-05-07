package com.intehel.controller.admin;

import com.intehel.common.util.LayUIResult;
import com.intehel.model.SysUser;
import com.intehel.service.admin.SysUserManagementService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author：席林达
 * @Description：用户管理接口
 * @CreateDate：2019/11/20 11:48
 * @Version：
 */
@Controller
@RequestMapping("/admin/sysUserManagement")
public class SysUserManagementController {

    private static final Logger logger = LoggerFactory.getLogger(SysUserManagementController.class);

    @Autowired
    private SysUserManagementService sysUserManagementService;

    /**
     * @Author: 席林达
     * @Description : 跳转到用户管理界面
     * @Param :
     * @Return : java.lang.String
     * @CreateDate: 2019/11/20 11:55
     * @Version V1
    */
    @RequestMapping("sysusermanageView")
    public String jumpUsermanageView(){
        logger.info("跳转到用户管理界面");
        return "admin/usermanage/sysusermanageView";
    }

    /**
     * @Author: 席林达
     * @Description :根据条件查询系统用户列表
     * @param pageNo
     * @param pageSize
     * @param sysUserName
     * @param sysUserRealName
     * @param sysUserSex
     * @Return :
     * @CreateDate: 2019/11/20 16:09
     * @Version V1
    */
    @RequestMapping("sysUserList")
    @ResponseBody
    public LayUIResult getSysUserList(Integer pageNo,Integer pageSize,String sysUserName,String sysUserRealName,Integer sysUserSex){
        logger.info("根据条件查询系统用户列表");
        LayUIResult sysUserResult = sysUserManagementService.getSysUserList(pageNo,pageSize,sysUserName,sysUserRealName,sysUserSex);
        return sysUserResult;
    }

    /**
     * @Author: 席林达
     * @Description :新增系统用户
     * @param :
     * @Return :
     * @CreateDate: 2019/11/20 16:09
     * @Version V1
     */
    @RequestMapping("addSysUser")
    @ResponseBody
    public Object addSysUser(SysUser sysUser){
        logger.info("新增系统用户---------->>>" + sysUser);
        Object addSysUserResult = sysUserManagementService.addSysUser(sysUser);
        return addSysUserResult;
    }

    /**
     * @author: 席林达
     * @description : 根据系统用户的ID查询对应的用户信息   用于修改用户
     * @param sysUserId
     * @return : com.intehel.common.util.LayUIResult
     * @createDate: 2019/11/20 23:35
     * @version V1
    */
    @RequestMapping("getSysUserById")
    @ResponseBody
    public Object getSysUserById(Integer sysUserId){
        logger.info("根据系统用户的ID查询对应的用户信息---------->>>" + sysUserId);
        Object getSysUserByIdResult = sysUserManagementService.getSysUserById(sysUserId);
        return getSysUserByIdResult;
    }

    /**
     * @author: 席林达
     * @description : 修改用户信息
     * @param sysUser
     * @return : com.intehel.common.util.LayUIResult
     * @createDate: 2019/11/20 23:47
     * @version V1
    */
    @RequestMapping("updateSysUserById")
    @ResponseBody
    public Object updateSysUserById(SysUser sysUser){
        logger.info("修改用户信息---------->>>" + sysUser);
        Object updateSysUserByIdResult = sysUserManagementService.updateSysUserById(sysUser);
        return updateSysUserByIdResult;
    }

    /**
     * @author: 席林达
     * @description : 根据ID删除用户信息
     * @param  sysUserId
     * @return : com.intehel.common.util.LayUIResult
     * @createDate: 2019/11/21 0:02
     * @version V1
    */
    @RequestMapping("delSysUserById")
    @ResponseBody
    public Object delSysUserById(Integer sysUserId){
        logger.info("根据ID删除用户信息---------->>>" + sysUserId);
        Object delSysUserByIdResult = sysUserManagementService.delSysUserById(sysUserId);
        return delSysUserByIdResult;
    }

}
