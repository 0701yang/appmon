<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<!--[if IE 8]><html lang="en" class="ie8 no-js"><![endif]-->
<!--[if IE 9]><html lang="en" class="ie9 no-js"><![endif]-->
<!--[if !IE]><!-->
<html lang="en" class="no-js">
<!--<![endif]-->
<head>
    <jsp:include page="../../common/common_css.jsp"/>
    <meta http-equiv="refresh" content="60">
</head>
<body>
<div class="page-container">
    <div class="page-head">
        <div class="container">
            <div class="page-title">
                <h1>
                    健康度检查
                    <small>${count.name}</small>
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
            <div class="tabbable tabbable-custom tabbable-noborder">
                <div class="tab-content">
                    <div class="tab-pane active">
                        <div class="tab-content">
                            <div id="tab_grey" class="tab-pane active">
                                <div class="row">
                                    <div class="col-md-12">
                                        <h3>${count.name}</h3>
                                        <div class="row">
                                            <div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
                                                <div class="dashboard-stat grey">
                                                    <div class="visual">
                                                        <i class="fa fa-bar-chart-o"></i>
                                                    </div>
                                                    <div class="details">
                                                        <div class="number">
                                                            <span class="uppercase font-lg" style="color:${count.crmcolor}">${count.crm}分</span>
                                                        </div>
                                                        <div class="desc"> CRM-APP : <a href="${pageContext.request.contextPath}/weblogic/crm/${count.url}">288</a></div>
                                                    </div>
                                                </div>

                                                <div class="dashboard-stat grey">
                                                    <div class="visual">
                                                        <i class="fa fa-bar-chart-o"></i>
                                                    </div>
                                                    <div class="details">
                                                        <div class="number">
                                                            <span class="uppercase font-lg" style="color:${count.osbcolor}">${count.osb}分</span>
                                                        </div>
                                                        <div class="desc"> OSB : <a href="${pageContext.request.contextPath}/weblogic/osb/${count.url}">48</a></div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="../../common/common_js.jsp"/>
<script>
    jQuery(document).ready(function () {
        Metronic.init();
        Layout.init(); // init current layout
        Demo.init(); // init demo features
    });
</script>
</body>
</html>