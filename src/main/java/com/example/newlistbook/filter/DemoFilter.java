package com.example.newlistbook.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * @Author: 李圣
 * @Date:2023/4/21 16:22
 * @Version: 1.0
 */
//@WebFilter(urlPatterns = "/*")
public class DemoFilter implements Filter {
    @Override   //初始化方法只调用一次
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("已经初始化");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("已经拦截");
        //放行操作
        filterChain.doFilter(servletRequest, servletResponse);

        System.out.println("已经放行");
    }

    @Override    //销毁方法只调用一次
    public void destroy() {
        System.out.println("已经销毁");
    }
}
