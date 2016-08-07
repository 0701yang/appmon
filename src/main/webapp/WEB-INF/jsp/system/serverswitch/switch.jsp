<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
<div class="page-container">
    <div class="page-head">
        <div class="container">
            <div class="page-title">
                <h1>
                    日常工作台
                    <small>集群切换</small>
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
            <c:forEach varStatus="indexs" items="${rootTree}" var="tree">
                <div class="row">
                    <div class="col-md-12">
                        <div class="portlet light">
                            <div class="portlet-title">
                                <div class="caption">
                                    <i class="fa fa-cogs"></i>
                                        ${tree.name}
                                </div>
                            </div>
                            <c:if test="${indexs.index==0}">
                                <div class="portlet-body">
                                    <div class="row margin-bottom-40">
                                        <c:forEach items="${childrenNode0}" var="node">
                                            <div class="col-md-2">
                                                <div class="pricing hover-effect">
                                                    <div class="pricing-head">
                                                        <h3 style="background: #36d7ac">
                                                                ${node.name}
                                                        </h3>
                                                    </div>
                                                    <div class="pricing-footer">
                                                        <a href="${pageContext.request.contextPath}${(node.url).split('.jsp')[0]}" class="btn yellow-crusta">
                                                            点击操作 <i class="m-icon-swapright m-icon-white"></i>
                                                        </a>
                                                    </div>
                                                </div>
                                            </div>


                                        </c:forEach>
                                    </div>
                                </div>
                            </c:if>
                            <c:if test="${indexs.index==1}">
                                <div class="portlet-body">
                                    <div class="row margin-bottom-40">
                                        <c:forEach items="${childrenNode1}" var="node">

                                            <div class="col-md-2">
                                                <div class="pricing hover-effect">
                                                    <div class="pricing-head">
                                                        <h3 style="background: #36d7ac">
                                                                ${node.name}
                                                        </h3>
                                                    </div>

                                                    <div class="pricing-footer">
                                                        <a href="${pageContext.request.contextPath}${((node.url).split('.jsp')[0]).concat((node.url).split('.jsp')[1])}" class="btn yellow-crusta">
                                                            点击操作 <i class="m-icon-swapright m-icon-white"></i>
                                                        </a>
                                                    </div>
                                                </div>
                                            </div>
                                        </c:forEach>
                                    </div>
                                </div>
                            </c:if>
                            <c:if test="${indexs.index==2}">
                                <div class="portlet-body">
                                    <div class="row margin-bottom-40">
                                        <c:forEach items="${childrenNode2}" var="node">
                                            <div class="col-md-2">
                                                <div class="pricing hover-effect">
                                                    <div class="pricing-head">
                                                        <h3 style="background: #36d7ac">
                                                                ${node.name}
                                                        </h3>
                                                    </div>

                                                    <div class="pricing-footer">
                                                        <a href="${pageContext.request.contextPath}${((node.url).split('.jsp')[0]).concat((node.url).split('.jsp')[1])}" class="btn yellow-crusta">
                                                            点击操作 <i class="m-icon-swapright m-icon-white"></i>
                                                        </a>
                                                    </div>
                                                </div>
                                            </div>
                                        </c:forEach>
                                    </div>
                                </div>
                            </c:if>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</div>
</body>
</html>