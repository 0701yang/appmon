<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.shsnc.util.pager.SystemContext" %>
<div>
    <div class="col-md-12">
        <div class="portlet light">
            <div class="portlet-body">
                <table class="table table-striped table-hover table-bordered">
                    <thead>
                    <tr>
                        <th>集群组</th>
                        <th>集群</th>
                        <th>IP地址</th>
                        <th>端口</th>
                        <th>实例名</th>
                        <th>线程总数</th>
                        <th>空闲线程数</th>
                        <th>占用线程数</th>
                        <th>查看历史信息</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:if test="${!empty bean.datas}">
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
                                            <a type="button" class="btn btn-primary"
                                               href="${pageContext.request.contextPath}/weblogic/history?ip=${bean.ip}&port=${bean.port}">历史数据</a>
                                            |
                                            <a onclick="weblogic_history_charts( '${bean.ip}' , '${bean.port}' );"
                                               class="btn btn-primary" data-toggle="modal" data-target="#weblogic_history_modal">曲线</a>
                                        </td>
                                    </c:when>
                                    <c:when test="${bean.threadtotal eq '-1' || bean.threadidle eq '-1' || bean.threadrun eq '-1'}">
                                        <td>${bean.app}</td>
                                        <td>${bean.module}</td>
                                        <td>${bean.ip}</td>
                                        <td class="label-warning">${bean.port}</td>
                                        <td class="label-warning">
                                            <a onclick="crm_app_charts('${bean.server}');" data-toggle="modal" data-target="#crm-app-modal">${bean.server}</a>
                                        </td>
                                        <td class="label-warning">${bean.threadtotal}</td>
                                        <td class="label-warning">${bean.threadidle}</td>
                                        <td class="label-warning">${bean.threadrun}</td>
                                        <td>
                                            <a type="button" class="btn btn-primary"
                                               href="${pageContext.request.contextPath}/weblogic/history?ip=${bean.ip}&port=${bean.port}"
                                            >历史数据</a>
                                            |
                                            <a onclick="weblogic_history_charts( '${bean.ip}' , '${bean.port}' );"
                                               class="btn btn-primary"
                                               data-toggle="modal" data-target="#weblogic_history_modal">曲线</a>
                                        </td>
                                    </c:when>
                                    <c:otherwise>
                                        <td>${bean.app}</td>
                                        <td>${bean.module}</td>
                                        <td>${bean.ip}</td>
                                        <td>${bean.port}</td>
                                        <td>
                                            <a onclick="crm_app_charts('${bean.server}');" data-toggle="modal" data-target="#crm-app-modal">${bean.server}</a>
                                       </td>
                                        <td>${bean.threadtotal}</td>
                                        <td>${bean.threadidle}</td>
                                        <td>${bean.threadrun}</td>
                                        <td>
                                            <a type="button" class="btn btn-primary"
                                               href="${pageContext.request.contextPath}/weblogic/history?ip=${bean.ip}&port=${bean.port}">历史数据</a>
                                            |
                                            <a onclick="weblogic_history_charts( '${bean.ip}' , '${bean.port}' );"
                                               class="btn btn-primary" data-toggle="modal" data-target="#weblogic_history_modal">曲线</a>
                                        </td>
                                    </c:otherwise>
                                </c:choose>
                            </tr>
                        </c:forEach>
                    </c:if>
                    <c:if test="${empty bean.datas}">
                        <tr>
                            <td colspan="9" align="center" bgcolor="#EFF3F7">
                                没有找到相应的记录
                            </td>
                        </tr>
                    </c:if>
                    </tbody>
                </table>

                <div class="row">
                    <jsp:include page="../../common/Page.jsp">
                        <jsp:param value="${bean.total }" name="totalRecord"/>
                        <jsp:param value="${pageContext.request.contextPath}/weblogic/button" name="url"/>
                        <jsp:param value="<%=SystemContext.getPageSize()%>" name="pagesize"/>
                    </jsp:include>
                </div>
            </div>
        </div>
    </div>
</div>