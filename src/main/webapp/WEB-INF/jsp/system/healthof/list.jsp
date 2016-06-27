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
                                    <div class="row">
                                        <div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
                                            <div class="dashboard-stat grey">
                                                <a href="Weblogic/history">
                                                    <div class="visual">
                                                        <i class="fa fa-briefcase fa-icon-medium"></i>
                                                    </div>
                                                    <div class="details">
                                                        <div class="number">
                                                            <span class="uppercase font-lg font-red-flamingo">100分</span>
                                                        </div>
                                                        <div class="desc"> 营业厅渠道</div>
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