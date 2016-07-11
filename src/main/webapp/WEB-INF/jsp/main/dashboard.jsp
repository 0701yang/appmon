<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html >
<!--[if IE 8]><html lang="en" class="ie8 no-js"><![endif]-->
<!--[if IE 9]><html lang="en" class="ie9 no-js"><![endif]-->
<!--[if !IE]><!-->
<html lang="en" class="no-js">
<!--<![endif]-->
<head>
    <jsp:include page="../common/common_css.jsp"/>
    <meta http-equiv="refresh" content="60">
</head>
<body>
<div>
    <div class="col-md-12">
        <div class="tabbable tabbable-custom tabbable-noborder">
            <div class="tab-content">
                <div class="tab-pane active">
                    <div class="tab-content">
                        <div id="tab_grey" class="tab-pane active">
                            <div class="row">
                                <div class="col-md-12">
                                    <div class="row">

                                        <c:forEach items="${list}" var="list" >
                                            <div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
                                                <div class="dashboard-stat grey">
                                                    <a href="${pageContext.request.contextPath}/healthof/${list.url}">
                                                        <div class="visual">
                                                            <i class="fa fa-briefcase fa-icon-medium"></i>
                                                        </div>
                                                        <div class="details">
                                                            <div class="number">
                                                                <span class="uppercase font-lg font-red-flamingo">${list.crmtotal}分</span>
                                                            </div>
                                                            <div class="desc">
                                                            ${list.name}
                                                            </div>
                                                        </div>
                                                    </a>
                                                    <a class="more" href="javascript:;">
                                                        CRM-App : <span class="uppercase font-lg font-red-flamingo ">${list.crm}分</span>
                                                        <i class="m-icon-swapright m-icon-white"></i>
                                                    </a>
                                                    <a class="more" href="javascript:;">
                                                        OSB : <span class="uppercase font-lg font-red-flamingo">${list.osb}分</span>
                                                        <i class="m-icon-swapright m-icon-white"></i>
                                                    </a>
                                                </div>
                                            </div>
                                        </c:forEach>

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

<jsp:include page="../common/common_js.jsp"/>
<script>
    jQuery(document).ready(function () {
        Metronic.init();
        Layout.init(); // init current layout
        Demo.init(); // init demo features
    });
</script>
</body>
</html>