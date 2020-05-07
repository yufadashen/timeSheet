package com.intehel.common.config;

/**
 * @Author 余发
 * @Description:
 * @CreateDate:
 * @UpdateDate:
 * @Version: v1
 */
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Configuration
public class WebSecurityConfig implements WebMvcConfigurer {
    @Value("${imageDir}")
    private String imageDir;
    private static final Logger logger = LoggerFactory.getLogger(WebSecurityConfig.class);
    @Bean
    public SecurityInterceptor getSecurityInterceptor() {
        return  new SecurityInterceptor();
    }
    private class SecurityInterceptor extends HandlerInterceptorAdapter {
        @Override
        public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)throws IOException {

//            logger.info(request.getServletPath());
            HttpSession session = request.getSession();
            //判断是否已有该用户登录的session
            if(session.getAttribute("SysUser") !=null){
                request.getSession().setAttribute("SysUser", session.getAttribute("SysUser"));
                //session超时时间，单位：秒
                request.getSession().setMaxInactiveInterval(36000);
                return  true;
            }else {
                if (request.getServletPath().contains("/admin/") && !request.getServletPath().contains("/admin/login")){
                    logger.info("后台管理没有session");
                    response.sendRedirect("/admin/login");
                    return false;
                }else {
                    logger.info("公众号未存session");
//                    response.set();
                    response.sendRedirect("/jump/login");
                    return false;
                }
            }
        }
    }
    @Override
    public  void addInterceptors(InterceptorRegistry registry){
        InterceptorRegistration addInterceptor = registry.addInterceptor(getSecurityInterceptor());
        //排除配置
        addInterceptor.excludePathPatterns("/admin/login","/jump/login","/admin/yanzheng","/admin/main/login","/css/**","/images/**","/js/**","/login.html");
        //拦截配置
        addInterceptor.addPathPatterns("/admin/**");
        addInterceptor.addPathPatterns("/jump/**");
        addInterceptor.addPathPatterns("/appointment/**");
        addInterceptor.addPathPatterns("/ReservationRecord/**");
    }
    /**
    *@Description 配置本地资源映射
    *@Param
    *@Return
    *@Author 侯森林
    *@Date
    *@Time
    */

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/systemImage/**").addResourceLocations("file:"+imageDir);
    }
}