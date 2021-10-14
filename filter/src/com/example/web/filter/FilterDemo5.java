package com.example.web.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

//浏览器直接请求index.jsp资源时该过滤器会执行(转发请求不会执行)
//@WebFilter(value = "/index.jsp", dispatcherTypes = DispatcherType.REQUEST)

//只有转发访问index.jsp资源时该过滤器才会执行
//@WebFilter(value = "/index.jsp", dispatcherTypes = DispatcherType.FORWARD)

//可配置多个
//@WebFilter(value = "/index.jsp", dispatcherTypes = {DispatcherType.FORWARD, DispatcherType.REQUEST})
public class FilterDemo5 implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        System.out.println("Demo5...");
        chain.doFilter(request, response);
    }
}
