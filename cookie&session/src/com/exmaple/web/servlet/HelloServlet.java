package com.exmaple.web.servlet;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(name = "helloServlet", value = "/helloServlet")
public class HelloServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        Cookie[] cs = request.getCookies();
        Cookie c1 = null;

        if(null!=cs){
            for (Cookie c : cs) {
                if("lastTime".equals(c.getName())){
                    c1=c;
                    break;
                }
            }
        }
        if(null!=c1){
            String value = c1.getValue();
            value = URLDecoder.decode(value,"utf-8");
            response.getWriter().write("欢迎回来！您上一次访问是在 "+value+"<br/>");
            value = encodeDateValue();
            c1.setValue(value);
            response.addCookie(c1);

        }else{
            String value = encodeDateValue();
            Cookie lastTime = new Cookie("lastTime", value);
            lastTime.setMaxAge(3600*24*30);//一个月
            response.addCookie(lastTime);
            response.getWriter().write("初次见面~");
        }
    }
    private String encodeDateValue() throws UnsupportedEncodingException {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat formater = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String value = formater.format(date);
        value =  URLEncoder.encode(value,"utf-8");
        return value;
    }
}
