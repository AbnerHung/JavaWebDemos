package com.example.web.servlet;

import com.example.domain.User;
import com.example.service.UserService;
import com.example.service.impl.UserServiceImpl;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import org.apache.commons.beanutils.BeanUtils;

@WebServlet(name = "submitToDbServlet", value = "/submitToDbServlet")
public class SubmitToDbServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        Map<String, String[]> parameterMap = request.getParameterMap();
        User insertUser = new User();

        try {
            BeanUtils.populate(insertUser,parameterMap);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        //System.out.println(insertUser);

        UserService service = new UserServiceImpl();
        boolean b = service.insertInto(insertUser);
        if(!b){
            System.out.println("false");
        }
        response.sendRedirect(request.getContextPath()+"/findUserByPageServlet");

    }
}
