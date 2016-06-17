<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
<table>
    <thead>
    <tr>
<c:forEach items="${userList}" var="u">
        <td>名字</td>
       </c:forEach>
    </tr>
    </thead>
    <tbody>
    <tr>
        <c:forEach items="${userList}" var="u">
            <td>${u.username}</td>
        </c:forEach>
    </tr>
    </tbody>
</table>
</body>
</html>
