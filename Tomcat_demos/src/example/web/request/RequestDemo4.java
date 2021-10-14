package example.web.request;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "RequestDemo4", value = "/requestDemo4")
public class RequestDemo4 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //演示获取请求头数据: referer

        String referrer = request.getHeader("referer");

        System.out.println(referrer);

        // 防盗链
        if(referrer!=null){
            if(referrer.contains("local")){
                // 正常访问
                response.setContentType("text/html;charset=utf-8");
                response.getWriter().write("你好世界");
            }else{
                System.out.println("AbnerHung.github.io");
            }
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
