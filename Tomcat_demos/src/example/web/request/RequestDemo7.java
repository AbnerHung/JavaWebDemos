package example.web.request;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Map;

@WebServlet("/requestDemo7")
public class RequestDemo7 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().write("<br/>");
        response.getWriter().write("77777被访问了");
        response.getWriter().write("<br/>");
        System.out.println("77777被访问了");
        //存储数据到request域中
        request.setAttribute("msg", "hello");
        

        //转发到demo8
        request.getRequestDispatcher("/requestDemo8").forward(request,response);

    }
}
