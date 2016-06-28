<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                        <th class="numeric">集群组</th>
                        <th class="numeric">集群</th>
                        <th class="numeric">IP地址</th>
                        <th class="numeric">端口</th>
                        <th class="numeric">实例名</th>
                        <th class="numeric">线程总数</th>
                        <th class="numeric">空闲线程数</th>
                        <th class="numeric">占用线程数</th>
                        <th class="numeric">查看历史信息</th>
                    </tr>
                    </thead>
                    <tbody>

                    <c:forEach items="${bean.datas}" var="bean">
                        <tr>
                            <c:choose>
                                <c:when test="${bean.threadrun ge bean.threadthreshold}">
                                    <td>${bean.app}</td>
                                    <td>${bean.module}</td>
                                    <td>${bean.ip}</td>
                                    <td class="label-danger">${bean.port}</td>
                                    <td class="label-danger">
                                        <a onclick="crm_app_charts('${bean.server}');" data-toggle="modal" data-target="#crm-app-modal">${bean.server}</a>
                                    </td>
                                    <td class="label-danger">&nbsp;&nbsp;</td>
                                    <td class="label-danger">&nbsp;&nbsp;</td>
                                    <td class="label-danger">DOWN or HANG</td>
                                    <td>
                                        <a type="button" class="btn btn-primary" href="weblogic_history.action?ip=${bean.ip}&port=${bean.port}" target="_blank">历史数据</a>
                                        |
                                        <a onclick="weblogic_history_charts( '${bean.ip}' , '${bean.port}' );" class="btn btn-primary" data-toggle="modal" data-target="#weblogic_history_modal">曲线</a>
                                    </td>
                                </c:when>
                                <c:when test="${bean.threadtotal eq '-1' || bean.threadidle eq '-1' || bean.threadrun eq '-1'}">
                                    <td>${bean.app}</td>
                                    <td>${bean.module}</td>
                                    <td>${bean.ip}</td>
                                    <td class="label-warning">${bean.port}</td>
                                    <td class="label-warning">${bean.server}</td>
                                    <td class="label-warning">${bean.threadtotal}</td>
                                    <td class="label-warning">${bean.threadidle}</td>
                                    <td class="label-warning">${bean.threadrun}</td>
                                    <td>
                                        <a type="button" class="btn btn-primary" href="weblogic_history.action?ip=${bean.ip}&port=${bean.port}" target="_blank">历史数据</a>
                                        |
                                        <a onclick="weblogic_history_charts( '${bean.ip}' , '${bean.port}' );" class="btn btn-primary" data-toggle="modal" data-target="#weblogic_history_modal">曲线</a>
                                    </td>
                                </c:when>
                                <c:otherwise>
                                    <td>${bean.app}</td>
                                    <td>${bean.module}</td>
                                    <td>${bean.ip}</td>
                                    <td>${bean.port}</td>
                                    <td>${bean.server}</td>
                                    <td>${bean.threadtotal}</td>
                                    <td>${bean.threadidle}</td>
                                    <td>${bean.threadrun}</td>
                                    <td>
                                        <a type="button" class="btn btn-primary" href="weblogic_history.action?ip=${bean.ip}&port=${bean.port}" target="_blank">历史数据</a>
                                        |
                                        <a onclick="weblogic_history_charts( '${bean.ip}' , '${bean.port}' );" class="btn btn-primary" data-toggle="modal" data-target="#weblogic_history_modal">曲线</a>
                                    </td>
                                </c:otherwise>
                            </c:choose>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <div>
                    <c:if test="${bean.total > 0}">
                        <jsp:include page="../../common/Page.jsp">
                            <jsp:param value="${bean.total }" name="totalRecord"/>
                            <jsp:param value="${list}" name="url"/>
                        </jsp:include>
                    </c:if>
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



