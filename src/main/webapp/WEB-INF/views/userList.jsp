<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/1/24 0024
  Time: 16:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>UserList</title>
</head>
<body>
    <table border="1" cellpadding="0" cellspacing="0">
        <thead>
            <tr>
                <td>id</td>
                <td>name</td>
            </tr>
        </thead>
        <c:forEach items="${userList}" var="user">
            <tr>
                <td>${user.id}</td>
                <td>${user.userName}</td>
            </tr>
        </c:forEach>
    </table>

</body>
</html>
