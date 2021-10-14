<%--
  Created by IntelliJ IDEA.
  User: Long-yim Hung
  Date: 2021/9/29
  Time: 20:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>el获取域中的值</title>
</head>
<body>
    <%
        request.setAttribute("name","张三");
        session.setAttribute("age",23);
    %>
<h3>el获取值</h3>
${requestScope.name}
${sessionScope.age}
${sessionScope.haha}
    ${name}
<%--没有的值会显示空串--%>
</body>
</html>
