package example.web.request;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Enumeration;

@WebServlet(name = "RequestDemo3", value = "/requestDemo3")
public class RequestDemo3 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //演示获取请求头数据: user-agnet

        String agent = request.getHeader("user-agent");

        if(agent.contains("Chrome")){
            System.out.println("Google yyds!");
        }else if(agent.contains("FireFox")){
            System.out.println("FireFox");
        }else if(agent.contains("Edg")){
            System.out.println("Egde");
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
