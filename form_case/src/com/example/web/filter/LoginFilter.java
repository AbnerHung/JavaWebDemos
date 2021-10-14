package com.example.web.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.io.ObjectStreamClass;

@WebFilter("/*")
public class LoginFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        //0.强制转换
        HttpServletRequest req = (HttpServletRequest) request;
        //1.获取资源的请求路径
        String uri = req.getRequestURI();
        //2.判断是否包含登录相关的资源路径,要注意排除css/js/图片/验证码
        if(uri.contains("/login.jsp") || uri.contains("/loginServlet")|| uri.contains("/css")|| uri.contains("/js")|| uri.contains("/fonts")|| uri.contains("/checkCode")){
            chain.doFilter(request, response);
        } else {
            //不包含需要验证用户是否登录
            //3.从session中获取user
            Object user = ((HttpServletRequest) request).getSession().getAttribute("loginUser");
            if(user!=null){
                chain.doFilter(request, response);
            } else {
                request.setAttribute("login_error", "您尚未登录,请登录");
                request.getRequestDispatcher("/login.jsp").forward(request,response);
            }
        }

    }
}
