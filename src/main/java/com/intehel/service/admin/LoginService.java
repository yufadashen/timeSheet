//package com.intehel.service.admin;
//
//import com.intehel.model.admin.LoginResult;
//import org.apache.shiro.session.Session;
//
//import javax.servlet.http.HttpSession;
//
///**
// *@Description 用户登录service
// *@Author 侯森林
// *@Date
// */
//public interface LoginService {
//
//        /**
//         *@Description 用户认证
//         *@Param
//         *@Return
//         *@Author 侯森林
//         *@Date
//         */
//        LoginResult login(String userName, String password);
//
//        /**
//         *@Description 获取当前用户名
//         *@Param
//         *@Return
//         *@Author 侯森林
//         *@Date 2019-9-18
//         */
//        String getCurrentUserName();
//        /**
//         *@Description 获取当前session
//         *@Param
//         *@Return
//         *@Author 侯森林
//         *@Date
//         */
//        Session getSession();
//        /**
//         *@Description 用户退出系统
//         *@Param
//         *@Return
//         *@Author 侯森林
//         *@Date
//         */
//        void logout();
//
//        /**
//        *@Description 获取用户权限菜单
//        *@Param
//        *@Return
//        *@Author 侯森林
//        *@Date
//        *@Time
//        */
//        Object getMenu();
//    }
