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
<body class="page-404-3">
<div class="page-header">
    <jsp:include page="../common/common_top.jsp"/>
    <jsp:include page="../common/common_menu.jsp"/>
</div>
<div class="page-inner">
    <img src="${pageContext.request.contextPath}/Metronic/img/earth.jpg" class="img-responsive" alt="">
</div>
<div class="container error-404">
    <h1>应用程序异常 (500)</h1>
    <h2>抱歉！您访问的页面出现异常，请稍后重试或联系管理员。</h2>
    <p>
        ${exception}
    </p>
    <p>
        <a href="${pageContext.request.contextPath}/user/index">
            Return home </a>
        <br>
    </p>
</div>
<jsp:include page="../common/common_foot.jsp"/>
<jsp:include page="../common/common_js.jsp"/>
<script>
    jQuery(document).ready(function () {
        Metronic.init(); // init metronic core componets
        Layout.init(); // init layout
        Demo.init(); // init demo features
    });
</script>
</body>
</html>



