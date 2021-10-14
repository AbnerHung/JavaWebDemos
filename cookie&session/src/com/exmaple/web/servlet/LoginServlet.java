package com.exmaple.web.servlet;

import com.exmaple.dao.UserDao;
import com.exmaple.domain.User;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.apache.commons.beanutils.BeanUtils;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.Set;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        User loginuser = new User();
        String checkCode = request.getParameter("check");
        String password = request.getParameter("password");
        String username = request.getParameter("username");
        // System.out.println(checkCode+"---"+username+"---"+password);
        loginuser.setPassword(password);
        loginuser.setUsername(username);
        // System.out.println(loginuser);
        HttpSession session = request.getSession();
        String checkCode_session = (String) session.getAttribute("checkCode_session");
        //删除session中存储的验证码
        session.removeAttribute("checkCode_session");
        // System.out.println(checkCode_session);
        if(checkCode_session!=null&&checkCode_session.equalsIgnoreCase(checkCode)){
            //忽略大小写比较字符串
            //调用UserDao的login方法
            UserDao dao = new UserDao();

            User user = dao.login(loginuser);
            //判断user
            if(user==null){
                //登录失败
                request.setAttribute("login_error","用户名或密码错误");
                request.getRequestDispatcher("/login.jsp").forward(request,response);
            }else{
                //登录成功
                //存储数据
                session.setAttribute("user",user.getUsername());
                //重定向
                response.sendRedirect(request.getContextPath()+"/success.jsp");
            }
        }else{
            request.setAttribute("cc_error","验证码错误");
            //转发到登录页面
            request.getRequestDispatcher("/login.jsp").forward(request,response);
        }



    }
}
