package com.heyy.study.springbootstudycentral.interceptor;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Classname MyInterceptor
 * @Description interceptor test
 * @Date 2019/5/13 20:32
 * @Created by Breeze
 */
public class MyInterceptor extends HandlerInterceptorAdapter{

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        boolean checkIsOk = false;
        if(null!=request){
            String staffId = request.getHeader("staffId");
            if(null!=staffId&&"Breeze".equals(staffId)){
                checkIsOk = true;
            }
        }
        if(!checkIsOk){
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED,"staffId check failed");
        }
        return checkIsOk;
    }
}
