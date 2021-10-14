<%--
  Created by IntelliJ IDEA.
  User: Long-yim Hung
  Date: 2021/9/28
  Time: 17:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <script>
        window.onload = function (){
            let img = document.getElementById("checkCode");
            img.onclick = function (){
            //加时间戳
            let date = new Date();
            img.src = "${pageContext.request.contextPath}/checkCode?"+date;
            }
            /*let change = document.getElementById("change");
            change.onclick = function (){
            //加时间戳
            let date = new Date();
            img.src = "/cookie_session/checkCode?"+date;
            }*/
        }
    </script>
    <style>
        div{
            color: red;
        }
    </style>
</head>
<body>
    <form action="${pageContext.request.contextPath}/loginServlet" method="post">
        <table>
            <tr>
                <td>用户名</td>
                <td><input type="text" name="username"></td>
            </tr>
            <tr>
                <td>密码</td>
                <td><input type="password" name="password"></td>
            </tr>
            <tr>
                <td>验证码</td>
                <td><input type="text" name="check"></td>
            </tr>
            <tr>
                <td colspan="2"><img src="${pageContext.request.contextPath}/checkCode" id="checkCode"></td>
            </tr>
            <tr>
                <td colspan="2"><input type="submit" value="登录"></td>
            </tr>

        </table>
    </form>

    <div><%=request.getAttribute("cc_error")==null?"":request.getAttribute("cc_error")%></div>
    <div><%=request.getAttribute("login_error")==null?"":request.getAttribute("login_error")%></div>
</body>
</html>
