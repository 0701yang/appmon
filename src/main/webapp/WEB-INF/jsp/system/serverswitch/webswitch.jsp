<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ include file="/Metronic/js/serverswitch/BaseHeaderInclude.jsp"%>
<!DOCTYPE html>
<!--[if IE 8]><html lang="en" class="ie8 no-js"><![endif]-->
<!--[if IE 9]><html lang="en" class="ie9 no-js"><![endif]-->
<!--[if !IE]><!-->
<html lang="en" class="no-js">
<!--<![endif]-->
<head>
    <jsp:include page="../../common/common_css.jsp"/>
    <link href="${pageContext.request.contextPath}/Metronic/css/pricing-table.css" rel="stylesheet" type="text/css">
</head>
<body>
<%
    Object serverObj=request.getAttribute("serverType");
    if(serverObj==null){
        request.getRequestDispatcher("/mon/control/AppSwitch?server='server'").forward(request,response);
        return;
    }
%>
<div class="page-container">
    <div class="page-head">
        <div class="container">
            <div class="page-title">
                <h1>
                    上线切换集群
                    <small>一键式切换_WEB</small>
                </h1>
            </div>
            <div class="page-toolbar">
                <div class="btn-group btn-theme-panel">
                    <a class="btn dropdown-toggle" type="button" href="javascript:history.go(-1);">
                        <i class="fa fa-reply"></i>
                    </a>
                </div>
            </div>
        </div>
    </div>
    <div class="page-content">
        <div class="container">
            <h1 align="center" fontsize="1">One Touch Cluster Switch WEB</h1>
            <tr><input type="button" onclick="getValue()" value=" CLUSTER SWITCH "></tr>
            </br>
            <TABLE id =tb1 width=100% border=1 >
                <tr>
                    <td><font size=4 color=red>SERVER_ID</font></td>
                    <td><font size=4 color=red>NAME</font></td>
                    <td><font size=4 color=red>TARGET</font></td>
                    <td><font size=4 color=red>SERVER_TYPE</font></td>
                </tr>
                <%
                    String cluster19="crm-app-g19";
                    String cluster20="crm-app-g20";
                %>
                <c:forEach var="servers" items="${webServer}" varStatus="status">
                <c:if test="${(status.begin)%2 == 0}">
                <tr><td>${servers.serverId} </td><td>${servers.name}</td><td><%=cluster19 %></td><td>${servers.serverType}</td>
                    </c:if>
                    <c:if test="${(status.begin)%2 != 0}">
                <tr><td>${servers.serverId} </td><td>${servers.name}</td><td><%=cluster20 %></td><td>${servers.serverType}</td>
                    </c:if>
                    </c:forEach>
            </table>
        </div>
    </div>
</div>
</body>
</html>
<SCRIPT LANGUAGE="JavaScript">
    function getValue()
    {
        if(confirm("Are you sure ?")){
            if (!confirm("Are you really sure ?")){
                return;
            }
            var table = document.getElementById("tb1")
            var condition="";
            for(var i=1;i<table.rows.length;i++)
            {
                condition+=table.rows[i].cells[0].innerText+":"+table.rows[i].cells[2].innerText+"^";
            }
            var rtn=AjaxPost("/business/com.shsnc.service.serverswitch.switchaction.AppSwitchAction","action=switchApp&condition="+condition);
            alert("The resulte of switch\n"+rtn);
            location.reload();
        }
    }
</SCRIPT>