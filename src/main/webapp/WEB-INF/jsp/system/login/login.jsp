<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<!--[if IE 8]><html lang="en" class="ie8 no-js"><![endif]-->
<!--[if IE 9]><html lang="en" class="ie9 no-js"><![endif]-->
<!--[if !IE]><!-->
<html lang="en" class="no-js">
<!--<![endif]-->
<head>
    <jsp:include page="../../common/common_css.jsp"/>
    <link href="${pageContext.request.contextPath}/Metronic/css/login-soft.css" rel="stylesheet" type="text/css"/>
    <link id="style_color" href="${pageContext.request.contextPath}/Metronic/css/default.css" rel="stylesheet" type="text/css"/>
</head>
<body class="login">
<div class="logo">
    <a href="#">
        <img src="${pageContext.request.contextPath}/Metronic/img/logo-light.png" alt=""/>
    </a>
</div>
<div class="menu-toggler sidebar-toggler"></div>
<div class="content" id="loginbox">
    <!-- 开始登入表单 -->
    <form class="login-form" action="" method="post" enctype="multipart/form-data”>
        <h3 class="form-title">登入您的账户</h3>
        <div class="form-group">
            <label class="control-label visible-ie8 visible-ie9">用户名</label>
            <div class="input-icon">
                <i class="fa fa-user"></i>
                <input class="form-control placeholder-no-fix" type="text" autocomplete="off" placeholder="用户名"
                       name="username" id="username"/>
            </div>
        </div>
        <div class="form-group">
            <label class="control-label visible-ie8 visible-ie9">密码</label>
            <div class="input-icon">
                <i class="fa fa-lock"></i>
                <input class="form-control placeholder-no-fix" type="password" autocomplete="off" placeholder="密码"
                       name="password" id="password"/>
            </div>
        </div>
        <div class="form-actions">
            <a onclick="saveCheck();" class="btn blue pull-right" onkeydown="onkeydown();">登录<i class="m-icon-swapright m-icon-white"></i></a>
        </div>
        <div class="forget-password">
        </div>
        <div class="create-account">
        </div>
    </form>
</div>
<jsp:include page="../../common/common_js.jsp"/>
<script src="${pageContext.request.contextPath}/Metronic/js/jquery/jquery.backstretch.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/Metronic/js/jquery/jquery.tips.js"></script>
<script type="text/javascript">
   //服务器验证
    function saveCheck() {
        if(check()){
            var username = $("#username").val();
            var password = $("#password").val();
            $.ajax({
                type: "POST",
                url: "${pageContext.request.contextPath}/login_login",
                data: {"username" : username , "password" : password},
                dataType:'json',
                cache: false,
                success: function(data){
                    if("success" == data.result){
                        //saveCookie();//记住密码
                        window.location.href="${pageContext.request.contextPath}/main/index";
                    }else if("usererror" == data.result){
                        $("#username").tips({
                            side : 1,
                            msg : "用户名或密码有误",
                            bg : '#FF5080',
                            time : 15
                        });
                        $("#username").focus();
                    }else if("errornouser" == data.result){
                        $("#username").tips({
                            side : 1,
                            msg : "用户不存在",
                            bg : '#FF5080',
                            time : 15
                        });
                        $("#username").focus();
                    }else if("身份验证失败!" == data.result){
                        $("#username").tips({
                            side : 1,
                            msg : "身份验证失败!",
                            bg : '#FF5080',
                            time : 15
                        });
                        $("#username").focus();
                    }else {
                        $("#username").tips({
                            side : 1,
                            msg : "参数传递失败!",
                            bg : '#FF5080',
                            time : 15
                        });
                        $("#username").focus();
                    }
                }
            });
        }
    }
   //客户端校验
   function check() {
       if ($("#username").val() == "") {

           $("#username").tips({
               side : 2,
               msg : '用户名不得为空',
               bg : '#AE81FF',
               time : 3
           });

           $("#username").focus();
           return false;
       } else {
           $("#username").val(jQuery.trim($('#username').val()));
       }

       if ($("#password").val() == "") {

           $("#password").tips({
               side : 2,
               msg : '密码不得为空',
               bg : '#AE81FF',
               time : 3
           });

           $("#password").focus();
           return false;
       }
       $("#loginbox").tips({
           side : 1,
           msg : '正在登录 , 请稍后 ...',
           bg : '#68B500',
           time : 10
       });
       return true;
   }
</script>

<script>
    //按下回车触发
    document.onkeydown = function(event) {
         var e = event ? event : (window.event ? window.event : null);
        if (e.keyCode == 13) {
            saveCheck();
        }
    }
</script>
<script>
    jQuery(document).ready(
            function () {
                Metronic.init();
                Layout.init();
                Demo.init();
                $.backstretch([
                            "${pageContext.request.contextPath}/Metronic/img/1.jpg",
                            "${pageContext.request.contextPath}/Metronic/img/2.jpg",
                            "${pageContext.request.contextPath}/Metronic/img/3.jpg",
                            "${pageContext.request.contextPath}/Metronic/img/4.jpg"],
                        {
                            fade: 1000,
                            duration: 8000
                        });
            });
</script>
</body>
</html>

