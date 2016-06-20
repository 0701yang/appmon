<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% String path = request.getContextPath();%>
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en" class="no-js">
<!--<![endif]-->
<head>
    <jsp:include page="../common/common_css.jsp"/>
</head>
<body>
<div class="page-header">
    <jsp:include page="../common/common_top.jsp"/>
    <jsp:include page="../common/common_menu.jsp"/>
    <div class="page-container">
        <div class="page-head">
            <div class="container">
                <div class="page-title">
                    <h1>Dashboard
                        <small>statistics & reports</small>
                    </h1>
                </div>
            </div>
        </div>
        <div class="page-content">
            <div class="row">
                <div class="col-md-12 col-sm-12">
                    <iframe name="mainFrame" id="mainFrame" frameborder="0" src="home_home.action" style="margin:0 auto;width:100%;height:100%;"></iframe>
                </div>
            </div>
        </div>
    </div>
    <jsp:include page="../common/common_foot.jsp"/>
</div>
<jsp:include page="../common/common_js.jsp"/>
<script src="<%=path %>/Metronic/js/quick-sidebar.js" type="text/javascript"></script>
<script>
    <!--iframe宽度适应-->
    function loginFrame() {
        var mainFrame = document.getElementById("mainFrame");
        var bheight = document.documentElement.clientHeight;
        mainFrame.style.width = '100%';
        mainFrame.style.height = (bheight - 51) + 'px';
    }
    loginFrame();
    window.onresize = function () {
        loginFrame();
    }

    jQuery(document).ready(function () {
        Metronic.init(); // init metronic core componets
        Layout.init(); // init layout
        Demo.init(); // init demo features
        QuickSidebar.init(); // init quick sidebar
    });
</script>
</body>
</html>



