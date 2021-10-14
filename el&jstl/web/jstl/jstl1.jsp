<%--
  Created by IntelliJ IDEA.
  User: Long-yim Hung
  Date: 2021/9/30
  Time: 20:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>if标签</title>
</head>
<body>
    <%--
    c:if标签
        1.属性：
            * test必须属性，接受boolean表达式
                * 如果表达式为true显示标签体内容，否则不显示
                * 一般情况下，test属性值会结合el表达式一起使用
        2.注意：
            c:if标签没有else情况

    --%>
    <c:if test="true">TRUE</c:if>


</body>
</html>
