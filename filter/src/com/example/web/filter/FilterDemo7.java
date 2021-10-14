package com.example.web.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

//@WebFilter("/*")
public class FilterDemo7 implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        System.out.println("Filter Demo 7 执行了....");
        chain.doFilter(request, response);
        System.out.println("Filter Demo 7 回来了....");
    }
}
