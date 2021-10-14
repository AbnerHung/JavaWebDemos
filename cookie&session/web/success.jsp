<%--
  Created by IntelliJ IDEA.
  User: Long-yim Hung
  Date: 2021/9/28
  Time: 22:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>欢迎</title>
</head>
<body>
    <h1><%=request.getSession().getAttribute("user")%>,欢迎您</h1>
</body>
</html>
