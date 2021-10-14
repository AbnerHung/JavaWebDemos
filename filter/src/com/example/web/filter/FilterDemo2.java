package com.example.web.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

//@WebFilter("/*")
public class FilterDemo2 implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        //对request对象请求消息增强
        System.out.println("filter Demo...");
        chain.doFilter(request, response);
        //对response对象的响应消息增强
        System.out.println("filter Demo... again");
    }
}
