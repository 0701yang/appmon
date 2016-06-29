<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<!--[if IE 8]><html lang="en" class="ie8 no-js"><![endif]-->
<!--[if IE 9]><html lang="en" class="ie9 no-js"><![endif]-->
<!--[if !IE]><!-->
<html lang="en" class="no-js">
<!--<![endif]-->
<head>
    <jsp:include page="../common/common_css.jsp"/>
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

                                        <c:forEach items="${map}" var="msg" >
                                            <div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
                                                <div class="dashboard-stat grey">
                                                    <a href="${pageContext.request.contextPath}/healthof/${msg.key}">
                                                        <div class="visual">
                                                            <i class="fa fa-briefcase fa-icon-medium"></i>
                                                        </div>
                                                        <div class="details">
                                                            <div class="number">
                                                                <span class="uppercase font-lg font-red-flamingo">100分</span>
                                                            </div>
                                                            <div class="desc">
                                                            ${msg.value}
                                                            </div>
                                                        </div>
                                                    </a>
                                                    <a class="more" href="javascript:;">
                                                        CRM-App : <span class="uppercase font-lg font-red-flamingo ">100分</span>
                                                        <i class="m-icon-swapright m-icon-white"></i>
                                                    </a>
                                                    <a class="more" href="javascript:;">
                                                        OSB : <span class="uppercase font-lg font-red-flamingo">100分</span>
                                                        <i class="m-icon-swapright m-icon-white"></i>
                                                    </a>
                                                </div>
                                            </div>
                                        </c:forEach>





<%--
                                        <div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
                                            <div class="dashboard-stat grey">
                                                <a href="${pageContext.request.contextPath}/healthof/view2">
                                                    <div class="visual">
                                                        <i class="fa fa-briefcase fa-icon-medium"></i>
                                                    </div>
                                                    <div class="details">
                                                        <div class="number">
                                                            <span class="uppercase font-lg font-red-flamingo">100分</span>
                                                        </div>
                                                        <div class="desc"> ${msg2}</div>
                                                    </div>
                                                </a>
                                                <a class="more" href="javascript:;">
                                                    CRM-App : <span class="uppercase font-lg font-red-flamingo ">100分</span>
                                                    <i class="m-icon-swapright m-icon-white"></i>
                                                </a>
                                                <a class="more" href="javascript:;">
                                                    OSB : <span class="uppercase font-lg font-red-flamingo">100分</span>
                                                    <i class="m-icon-swapright m-icon-white"></i>
                                                </a>
                                            </div>
                                        </div>

                                        <div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
                                            <div class="dashboard-stat grey">
                                                <a href="${pageContext.request.contextPath}/healthof/view3">
                                                    <div class="visual">
                                                        <i class="fa fa-briefcase fa-icon-medium"></i>
                                                    </div>
                                                    <div class="details">
                                                        <div class="number">
                                                            <span class="uppercase font-lg font-red-flamingo">100分</span>
                                                        </div>
                                                        <div class="desc"> ${msg3}</div>
                                                    </div>
                                                </a>
                                                <a class="more" href="javascript:;">
                                                    CRM-App : <span class="uppercase font-lg font-red-flamingo ">100分</span>
                                                    <i class="m-icon-swapright m-icon-white"></i>
                                                </a>
                                                <a class="more" href="javascript:;">
                                                    OSB : <span class="uppercase font-lg font-red-flamingo">100分</span>
                                                    <i class="m-icon-swapright m-icon-white"></i>
                                                </a>
                                            </div>
                                        </div>

                                        <div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
                                            <div class="dashboard-stat grey">
                                                <a href="${pageContext.request.contextPath}/healthof/view4">
                                                    <div class="visual">
                                                        <i class="fa fa-briefcase fa-icon-medium"></i>
                                                    </div>
                                                    <div class="details">
                                                        <div class="number">
                                                            <span class="uppercase font-lg font-red-flamingo">100分</span>
                                                        </div>
                                                        <div class="desc"> ${msg4}</div>
                                                    </div>
                                                </a>
                                                <a class="more" href="javascript:;">
                                                    CRM-App : <span class="uppercase font-lg font-red-flamingo ">100分</span>
                                                    <i class="m-icon-swapright m-icon-white"></i>
                                                </a>
                                                <a class="more" href="javascript:;">
                                                    OSB : <span class="uppercase font-lg font-red-flamingo">100分</span>
                                                    <i class="m-icon-swapright m-icon-white"></i>
                                                </a>
                                            </div>
                                        </div>

                                        <div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
                                            <div class="dashboard-stat grey">
                                                <a href="${pageContext.request.contextPath}/healthof/view5">
                                                    <div class="visual">
                                                        <i class="fa fa-briefcase fa-icon-medium"></i>
                                                    </div>
                                                    <div class="details">
                                                        <div class="number">
                                                            <span class="uppercase font-lg font-red-flamingo">100分</span>
                                                        </div>
                                                        <div class="desc"> ${msg5}</div>
                                                    </div>
                                                </a>
                                                <a class="more" href="javascript:;">
                                                    CRM-App : <span class="uppercase font-lg font-red-flamingo ">100分</span>
                                                    <i class="m-icon-swapright m-icon-white"></i>
                                                </a>
                                                <a class="more" href="javascript:;">
                                                    OSB : <span class="uppercase font-lg font-red-flamingo">100分</span>
                                                    <i class="m-icon-swapright m-icon-white"></i>
                                                </a>
                                            </div>
                                        </div>

                                        <div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
                                            <div class="dashboard-stat grey">
                                                <a href="${pageContext.request.contextPath}/healthof/view6">
                                                    <div class="visual">
                                                        <i class="fa fa-briefcase fa-icon-medium"></i>
                                                    </div>
                                                    <div class="details">
                                                        <div class="number">
                                                            <span class="uppercase font-lg font-red-flamingo">100分</span>
                                                        </div>
                                                        <div class="desc"> ${msg6}</div>
                                                    </div>
                                                </a>
                                                <a class="more" href="javascript:;">
                                                    CRM-App : <span class="uppercase font-lg font-red-flamingo ">100分</span>
                                                    <i class="m-icon-swapright m-icon-white"></i>
                                                </a>
                                                <a class="more" href="javascript:;">
                                                    OSB : <span class="uppercase font-lg font-red-flamingo">100分</span>
                                                    <i class="m-icon-swapright m-icon-white"></i>
                                                </a>
                                            </div>
                                        </div>

                                        <div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
                                            <div class="dashboard-stat grey">
                                                <a href="${pageContext.request.contextPath}/healthof/view7">
                                                    <div class="visual">
                                                        <i class="fa fa-briefcase fa-icon-medium"></i>
                                                    </div>
                                                    <div class="details">
                                                        <div class="number">
                                                            <span class="uppercase font-lg font-red-flamingo">100分</span>
                                                        </div>
                                                        <div class="desc"> ${msg7}</div>
                                                    </div>
                                                </a>
                                                <a class="more" href="javascript:;">
                                                    CRM-App : <span class="uppercase font-lg font-red-flamingo ">100分</span>
                                                    <i class="m-icon-swapright m-icon-white"></i>
                                                </a>
                                                <a class="more" href="javascript:;">
                                                    OSB : <span class="uppercase font-lg font-red-flamingo">100分</span>
                                                    <i class="m-icon-swapright m-icon-white"></i>
                                                </a>
                                            </div>
                                        </div>

                                        <div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
                                            <div class="dashboard-stat grey">
                                                <a href="${pageContext.request.contextPath}/healthof/view8">
                                                    <div class="visual">
                                                        <i class="fa fa-briefcase fa-icon-medium"></i>
                                                    </div>
                                                    <div class="details">
                                                        <div class="number">
                                                            <span class="uppercase font-lg font-red-flamingo">100分</span>
                                                        </div>
                                                        <div class="desc"> ${msg8}</div>
                                                    </div>
                                                </a>
                                                <a class="more" href="javascript:;">
                                                    CRM-App : <span class="uppercase font-lg font-red-flamingo ">100分</span>
                                                    <i class="m-icon-swapright m-icon-white"></i>
                                                </a>
                                                <a class="more" href="javascript:;">
                                                    OSB : <span class="uppercase font-lg font-red-flamingo">100分</span>
                                                    <i class="m-icon-swapright m-icon-white"></i>
                                                </a>
                                            </div>
                                        </div>--%>

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