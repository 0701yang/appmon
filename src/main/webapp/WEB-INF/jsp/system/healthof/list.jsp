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
                                    <h3>${msg}</h3>
                                    <div class="row">
                                        <div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
                                            <div class="dashboard-stat grey">
                                                <div class="visual">
                                                    <i class="fa fa-bar-chart-o"></i>
                                                </div>
                                                <div class="details">
                                                    <div class="number">
                                                        <span class="uppercase font-lg font-red-flamingo">100分</span>
                                                    </div>
                                                    <div class="desc"> CRM-APP : <a href="${pageContext.request.contextPath}/weblogic/crm/${crm}">288</a></div>
                                                </div>
                                            </div>

                                            <%--<div class="dashboard-stat grey">--%>
                                                <%--<div class="visual">--%>
                                                    <%--<i class="fa fa-bar-chart-o"></i>--%>
                                                <%--</div>--%>
                                                <%--<div class="details">--%>
                                                    <%--<div class="number">--%>
                                                        <%--<span class="uppercase font-lg font-red-flamingo">100分</span>--%>
                                                    <%--</div>--%>
                                                    <%--<div class="desc"> HTTP : <a href="#">30</a></div>--%>
                                                <%--</div>--%>
                                            <%--</div>--%>

                                            <div class="dashboard-stat grey">
                                                <div class="visual">
                                                    <i class="fa fa-bar-chart-o"></i>
                                                </div>
                                                <div class="details">
                                                    <div class="number">
                                                        <span class="uppercase font-lg font-red-flamingo">100分</span>
                                                    </div>
                                                    <div class="desc"> OSB : <a href="${pageContext.request.contextPath}/weblogic/osb/${crm}">48</a></div>
                                                </div>
                                            </div>

                                            <%--<div class="dashboard-stat grey">--%>
                                                <%--<div class="visual">--%>
                                                    <%--<i class="fa fa-bar-chart-o"></i>--%>
                                                <%--</div>--%>
                                                <%--<div class="details">--%>
                                                    <%--<div class="number">--%>
                                                        <%--<span class="uppercase font-lg font-red-flamingo">100分</span>--%>
                                                    <%--</div>--%>
                                                    <%--<div class="desc"> 关键字错误 : </div>--%>
                                                <%--</div>--%>
                                            <%--</div>--%>


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