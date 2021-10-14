package com.exmaple.session;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet(name = "sessionDemo2", value = "/sessionDemo2")
public class SessionDemo2 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        //希望客户端关闭后，session也能相同，创建Cookie
        Cookie cookie = new Cookie("JSSIONID", session.getId());
        cookie.setMaxAge(3600);
        response.addCookie(cookie);
        Object msg = session.getAttribute("msg");
        System.out.println(msg);
    }
}
