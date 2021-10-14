package com.example.web.servlet;

import com.example.domain.User;
import com.example.service.UserService;
import com.example.service.impl.UserServiceImpl;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String verifycode = request.getParameter("verifycode");
        String checkcode_server = (String) request.getSession().getAttribute("CHECKCODE_SERVER");
        // request.getSession().removeAttribute("CHECKCODE_SERVER");
        if(checkcode_server==null||!checkcode_server.equalsIgnoreCase(verifycode)){
            request.setAttribute("login_error","验证码错误");
            request.getRequestDispatcher("/login.jsp").forward(request,response);
            return;
        }
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User user = new User();
        user.setEmail(username);
        user.setPassword(password);
        UserService service = new UserServiceImpl();
        User loginUser = service.login(user);
        if(loginUser==null){
            request.setAttribute("login_error","用户名或密码错误");
            request.getRequestDispatcher("/login.jsp").forward(request,response);
            return;
        }else {
            request.getSession().setAttribute("loginUser",loginUser);
            response.sendRedirect(request.getContextPath()+"/index.jsp");
        }
    }
}
