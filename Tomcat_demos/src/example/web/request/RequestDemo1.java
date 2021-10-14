package example.web.request;

import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.*;

import java.io.IOException;

/**
 *  Request对象获取请求行数据
 */

@WebServlet("/requestDemo1")
public class RequestDemo1 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取请求方式: GET
        String method = request.getMethod();
        System.out.println(method);
        // 获取虚拟目录
        String contextPath = request.getContextPath();
        System.out.println(contextPath);
        // 获取Servlet路径
        String servletPath = request.getServletPath();
        System.out.println(servletPath);
        // 获取get方式请求参数
        String queryString = request.getQueryString();
        System.out.println(queryString);
        // 获取请求URI
        String requestURI = request.getRequestURI();
        System.out.println(requestURI);
        StringBuffer requestURL = request.getRequestURL();
        System.out.println(requestURL);
        // 获取协议及版本
        String protocol = request.getProtocol();
        System.out.println(protocol);
        // 获取客户机IP
        String remoteAddr = request.getRemoteAddr();
        System.out.println(remoteAddr);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
