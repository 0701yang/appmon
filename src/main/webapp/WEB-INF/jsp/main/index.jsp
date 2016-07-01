<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
</div>
<div class="page-container">
    <div class="page-content">
        <div class="container">
            <iframe name="mainFrame" id="mainFrame" src="dashboard/index" style="width: 100% ;height: 100% ;" frameborder="0"  marginheight="0" marginwidth="0"></iframe>
        </div>
    </div>
</div>
<jsp:include page="../common/common_foot.jsp"/>
<jsp:include page="../common/common_js.jsp"/>
<script src="${pageContext.request.contextPath}/Metronic/js/quick-sidebar.js" type="text/javascript"></script>
<script>
    <!--iframe宽度适应-->
    function loginFrame() {
        var mainFrame = document.getElementById("mainFrame");
        var bheight = document.documentElement.clientHeight;//可见区域高度
        //alert(bheight);
        mainFrame.style.width = '100%';
        mainFrame.style.height = (bheight-51) + 'px';
    }
    loginFrame();
    window.onresize = function () {
        loginFrame();
    }
</script>
<script>
    jQuery(document).ready(function () {
        Metronic.init(); // init metronic core componets
        Layout.init(); // init layout
        Demo.init(); // init demo features
        QuickSidebar.init(); // init quick sidebar
    });
</script>
</body>
</html>



