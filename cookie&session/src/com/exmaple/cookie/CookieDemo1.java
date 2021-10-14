package com.exmaple.cookie;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "CookieDemo1", value = "/cookieDemo1")
public class CookieDemo1 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.创建Cookie对象
        Cookie c = new Cookie("msg", "hello");
        //设置存活时间
        c.setMaxAge(30);//将cookie持久化到硬盘，30s后自动删除文件
        //设置路径，通过设置更大的路径可以实现共享Cookie
        c.setPath("/");
        //不同服务器间的Cookie共享可以通过setDomain(String Path)实现
        //设置一级域名相同，那么多个服务器间可以共享Cookie

        //2.发送Cookie
        response.addCookie(c);
    }
}
