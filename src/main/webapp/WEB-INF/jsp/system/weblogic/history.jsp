<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="com.shsnc.util.pager.SystemContext" %>
<!DOCTYPE html>
<!--[if IE 8]><html lang="en" class="ie8 no-js"><![endif]-->
<!--[if IE 9]><html lang="en" class="ie9 no-js"><![endif]-->
<!--[if !IE]><!-->
<html lang="en" class="no-js">
<!--<![endif]-->
<head>
    <jsp:include page="../../common/common_css.jsp"/>
</head>
<body>
<div>
    <div class="col-md-12">
        <div class="portlet light">
            <div class="portlet-body flip-scroll">
                <table class="table table-bordered table-striped table-condensed flip-content">
                    <thead class="flip-content">
                    <tr>
                        <th>时间</th>
                        <th>线程总数</th>
                        <th>空闲线程数</th>
                        <th>占用线程数</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${historyList.datas}" var="his">
                        <tr>
                            <c:choose>
                                <c:when test="${his.threadtotal eq -1 || his.threadidle eq -1 || his.threadrun eq -1 }">
                                    <td style="background: red"><fmt:formatDate value="${his.time}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                                    <td style="background: red">&nbsp;&nbsp;</td>
                                    <td style="background: red">&nbsp;&nbsp;</td>
                                    <td style="background: red">DOWN or HANG</td>
                                </c:when>
                                <c:otherwise>
                                    <td><fmt:formatDate value="${his.time}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                                    <td>${his.threadtotal}</td>
                                    <td>${his.threadidle}</td>
                                    <td>${his.threadrun}</td>
                                </c:otherwise>
                            </c:choose>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>

                <div class="row">
                        <jsp:include page="../../common/Page.jsp">
                            <jsp:param value="${historyList.total }" name="totalRecord"/>
                            <jsp:param value="history?ip=${ip}&port=${port}" name="url"/>
                            <jsp:param value="<%=SystemContext.getPageSize()%>" name="pagesize"/>
                        </jsp:include>
                </div>

            </div>
        </div>
    </div>
</div>
<jsp:include page="../../common/common_js.jsp"/>
<script>
    jQuery(document).ready(function () {
        Metronic.init();
        Layout.init();
        Demo.init();

    });
</script>
</body>
</html>



