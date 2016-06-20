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
<c:forEach items="${userlist.datas}" var="u">
        <td>名字</td>
       </c:forEach>
    </tr>
    </thead>
    <tbody>
    <tr>
        <c:forEach items="${userlist.datas}" var="u">
            <td>${u.USERNAME}</td>
        </c:forEach>
    </tr>

    </tbody>
    <c:if test="${userlist.total > 0}">
        <jsp:include page="/Metronic/jsp/common/Page.jsp">
            <jsp:param value="${userlist.total }" name="totalRecord"/>
            <jsp:param value="listUsers" name="url"/>
        </jsp:include>
    </c:if>
</table>
</body>
</html>
