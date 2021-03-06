<%--
  Created by IntelliJ IDEA.
  User: Long-yim Hung
  Date: 2021/10/1
  Time: 9:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>choose标签</title>
</head>
<body>

    <%--
    完成数字编号对应星期几的案例
        1.域中存储一数字
        2.使用choose标签取出数字        相当于switch
        3.使用when标签做数字判断        相当于case
        4.otherwise标签做其他情况的声明 相当于default
    --%>

    <%
        request.setAttribute("number",2);
    %>
    <c:choose>
        <c:when test="${number == 1}">星期一</c:when>
        <c:when test="${number == 2}">星期二</c:when>
        <c:when test="${number == 3}">星期三</c:when>
        <c:when test="${number == 4}">星期四</c:when>
        <c:when test="${number == 5}">星期五</c:when>
        <c:when test="${number == 6}">星期六</c:when>
        <c:when test="${number == 7}">星期日</c:when>
        <c:otherwise>输入有误</c:otherwise>
    </c:choose>

</body>
</html>
