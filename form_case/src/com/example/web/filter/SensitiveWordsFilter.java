package com.example.web.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;

import java.io.*;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * 过滤敏感词
 */
@WebFilter("/*")
public class SensitiveWordsFilter implements Filter {
    private List<String> list = new ArrayList<String>(); //敏感词汇集合

    public void init(FilterConfig config) throws ServletException {

        try {
            //1.获取文件真实路径
            ServletContext context = config.getServletContext();
            String realPath = context.getRealPath("/WEB-INF/classes/banWords.txt");
            //2.读取文件
            BufferedReader br = new BufferedReader(new FileReader(realPath));
            //3.将文件的每一行数据加到List中
            String line = null;
            while ((line = br.readLine()) != null) {
                list.add(line);
            }
            System.out.println(list.toString());
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        //0.强制转换
        HttpServletRequest req = (HttpServletRequest) request;
        //1.获取资源的请求路径
        String uri = req.getRequestURI();
        if(uri.contains("/css")|| uri.contains("/js")|| uri.contains("/fonts")|| uri.contains("/checkCode")){
            chain.doFilter(request, response);
        } else{
            //1.创建代理对象，增强getParameter方法
            ServletRequest proxy_req = (ServletRequest) Proxy.newProxyInstance(request.getClass().getClassLoader(), request.getClass().getInterfaces(), new InvocationHandler() {
                @Override
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                    //增强getParameter方法
                    if ("getParameter".equals(method.getName())) {
                        //增强返回值
                        //获取返回值
                        String value = (String) method.invoke(request, args);
                        if(value != null) {
                            for (String str : list) {
                                if(value.contains(str)){
                                    value = value.replaceAll(str,"***");
                                }
                            }
                        }
                        return value;
                    }
                    return method.invoke(request,args);
                }
            });

            //2.放行
            chain.doFilter(proxy_req, response);
        }


    }
}
