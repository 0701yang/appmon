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

<div class="page-container">
    <div class="page-head">
        <div class="container">
            <div class="page-title">
                <h1>
                    基础模块
                    <small>角色列表</small>
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
            <div class="portlet light">
                <div class="portlet-title">
                    <div class="caption">
                        <i class="fa fa-cogs font-green-sharp"></i>
                        <span class="caption-subject font-green-sharp bold uppercase">角色列表</span>
                    </div>
                    <div class="actions btn-set">
                        <a type="botton" class="btn green-haze btn-circle" href="javascript:history.go(-1);">
                            <i class="fa fa-check-circle"></i>
                            返回
                        </a>
                    </div>
                    <div class="actions btn-set">
                        <a type="botton" class="btn green-haze btn-circle" href="${pageContext.request.contextPath}/role/add">
                            <i class="fa fa-check-circle"></i>
                            添加
                        </a>
                    </div>
                    <div class="actions btn-set">
                        <a type="botton" class="btn green-haze btn-circle" href="${pageContext.request.contextPath}/role/list">
                            <i class="fa fa-check-circle"></i>
                            刷新
                        </a>
                    </div>
                </div>
                <div class="portlet-body flip-scroll">
                    <table class="table table-bordered table-striped table-condensed flip-content">
                        <thead class="flip-content">
                        <tr>
                            <th>编号</th>
                            <th>角色名称</th>
                            <th>角色说明</th>
                            <th>相关操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:if test="${!empty roleList.datas}">
                            <c:forEach items="${roleList.datas}" var="role" varStatus="i">
                                <tr>
                                    <td>${i.index+1}</td>
                                    <td>${role.name}</td>
                                    <td>${role.description}</td>
                                    <td>
                                        <a class="btn btn-icon-only btn-circle red"
                                           href="${pageContext.request.contextPath}/role/toEdit?id=${role.id}"
                                           data-toggle="tooltip" data-placement="top" title="编辑" target="mainFrame"><i class="fa fa-edit"></i> </a>
                                        <a class="btn btn-icon-only btn-circle purple"
                                           href="${pageContext.request.contextPath}/role/del?id=${role.id} "
                                           data-toggle="tooltip" data-placement="top" title="删除"
                                           onclick="return window.confirm('您确定要删除吗？')" target="mainFrame"><i class="fa fa-times"></i> </a>
                                        <a class="btn btn-icon-only btn-circle grey-cascade"
                                           href="${pageContext.request.contextPath}/role/setPrivilege?id=${role.id}"
                                           data-toggle="tooltip"
                                           data-placement="top" title="设置权限" target="mainFrame"><i class="fa fa-link"></i> </a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </c:if>
                        <c:if test="${empty roleList.datas}">
                            <tr>
                                <td colspan="4" align="center" bgcolor="#EFF3F7">
                                    没有找到相应的记录
                                </td>
                            </tr>
                        </c:if>
                        </tbody>
                    </table>
                    <div class="row">
                        <jsp:include page="../../common/Page.jsp">
                            <jsp:param value="${roleList.total }" name="totalRecord"/>
                            <jsp:param value="list" name="url"/>
                            <jsp:param value="<%=SystemContext.getPageSize()%>" name="pagesize"/>
                        </jsp:include>
                    </div>

                </div>
            </div>
        </div>
    </div>
</div>

<jsp:include page="../../common/common_js.jsp"/>
<script src="${pageContext.request.contextPath}/Metronic/js/tooltip.js" type="text/javascript"></script>
<script>
    jQuery(document).ready(function () {
        $('[data-toggle="tooltip"]').tooltip();
        Metronic.init();
        Layout.init(); // init current layout
        Demo.init(); // init demo features
    });
</script>
</body>
</html>



