//package com.intehel.service.admin.impl;
//
//import com.alibaba.fastjson.JSON;
//import com.intehel.common.util.Result;
//import com.intehel.dao.admin.SysUserMapper;
//import com.intehel.model.admin.LoginResult;
//import com.intehel.model.admin.Menu;
//import com.intehel.model.admin.SysResource;
//import com.intehel.model.admin.SysUser;
//import com.intehel.service.admin.LoginService;
//import com.intehel.service.admin.SysUserService;
//import com.sun.corba.se.spi.orbutil.fsm.Guard;
//import org.apache.shiro.SecurityUtils;
//import org.apache.shiro.authc.AuthenticationException;
//import org.apache.shiro.authc.IncorrectCredentialsException;
//import org.apache.shiro.authc.UnknownAccountException;
//import org.apache.shiro.authc.UsernamePasswordToken;
//import org.apache.shiro.session.Session;
//import org.apache.shiro.subject.Subject;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import javax.servlet.http.HttpSession;
//import java.util.ArrayList;
//import java.util.Comparator;
//import java.util.List;
//import java.util.stream.Collectors;
//
///**
//*@Description 登录service实现类
//*@Author 侯森林
//*@Date
//*@Time
//*/
//@Service
//public class LoginServiceImpl implements LoginService {
//    @Autowired
//    private SysUserMapper userMapper;
//    @Autowired
//    private SysUserService userService;
//    /**
//     *@Description 用户认证
//     *@Param userName用户名  password密码
//     *@Return
//     *@Author 侯森林
//     *@Date 2019-9-18
//     */
//    @Override
//    public LoginResult login(String userName, String password) {
//        LoginResult loginResult = new LoginResult();
//        if(userName==null || userName.isEmpty())
//        {
//            loginResult.setLogin(false);
//            loginResult.setResult("用户名为空");
//            return loginResult;
//        }
//        SysUser user=userMapper.findByUserName(userName);
//        String msg="";
//        // 1、获取Subject实例对象
//        Subject currentUser = SecurityUtils.getSubject();
//
//        // 2、判断当前用户是否登录
//        if (currentUser.isAuthenticated() == false) {
//
//        }
//        // 3、将用户名和密码封装到UsernamePasswordToken
//        UsernamePasswordToken token = new UsernamePasswordToken(userName, password);
//
//        // 4、认证
//        try {
//            currentUser.login(token);// 传到MyAuthorizingRealm类中的方法进行认证
//            Session session = currentUser.getSession();
//            session.setAttribute("userName", userName);
//            session.setAttribute("user",user);
//            loginResult.setLogin(true);
//            return loginResult;
//            //return "/index";
//        }catch (UnknownAccountException e)
//        {
//            e.printStackTrace();
//            msg = "账号不存在";
//        }
//        catch (IncorrectCredentialsException e)
//        {
//            msg = "密码不正确";
//        }
//        catch (AuthenticationException e) {
//            e.printStackTrace();
//            msg="用户验证失败";
//        }
//
//        loginResult.setLogin(false);
//        loginResult.setResult(msg);
//
//        return loginResult;
//    }
//
//    /**
//     *@Description 用户退出系统
//     *@Param
//     *@Return
//     *@Author 侯森林
//     *@Date 2019-9-18
//     */
//    @Override
//    public void logout() {
//        Subject subject = SecurityUtils.getSubject();
//        subject.logout();
//    }
//
//    /**
//    *@Description 获取用户权限菜单
//    *@Param
//    *@Return
//    *@Author 侯森林
//    *@Date
//    *@Time
//    */
//    @Override
//    public Object getMenu() {
//        Session session=getSession();
//        SysUser user=(SysUser) session.getAttribute("user");
//        List<SysResource> sysResourceList=userService.findResourceByUserId(user.getUserId());
//        List<Menu> menuList=new ArrayList<>();
//        for (SysResource sysResource:sysResourceList){
//            if(sysResource.getLevel()<=3){
//                if(sysResource.getParentId()==0){
//                    continue;
//                }else {
//                    Menu menu=new Menu();
//                    menu.setId(sysResource.getResourceId());
//                    menu.setText(sysResource.getMenuName());
//                    menu.setpId(sysResource.getParentId());
//                    menu.setUrl(sysResource.getMenuUrl());
//                    menu.setMenuOrder(sysResource.getSequence());
//                    menuList.add(menu);
//                }
//            }
//        }
//        List<Menu> childMenu=new ArrayList<>();
//        for(Menu m:menuList){
//            if(m.getpId()==1){
//                childMenu.add(m);
//            }
//        }
//        for (Menu c:childMenu){
//            List<Menu> grandsonMenu=new ArrayList<>();
//            for (Menu m:menuList){
//                if(m.getpId().equals(c.getId())){
//                    grandsonMenu.add(m);
//                }
//            }
//            grandsonMenu = grandsonMenu.stream().sorted(Comparator.comparing(Menu::getMenuOrder))
//                    .collect(Collectors.toList());
//            c.setChildren(grandsonMenu);
//        }
//        childMenu = childMenu.stream().sorted(Comparator.comparing(Menu::getMenuOrder))
//                .collect(Collectors.toList());
//        return Result.success(childMenu);
//    }
//
//    /**
//     *@Description 获取当前用户名
//     *@Param
//     *@Return String 用户名
//     *@Author 侯森林
//     *@Date 2019-9-18
//     */
//    @Override
//    public String getCurrentUserName() {
//        Subject currentUser = SecurityUtils.getSubject();
//        Session session = currentUser.getSession();
//        return (String)session.getAttribute("userName");
//    }
//    /**
//     *@Description 获取当前session
//     *@Param
//     *@Return
//     *@Author 侯森林
//     *@Date 2019-9-18
//     */
//    @Override
//    public Session getSession() {
//        Subject currentUser = SecurityUtils.getSubject();
//        Session session = currentUser.getSession();
//        return session;
//    }
//}
