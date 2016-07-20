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
<div class="page-container">
    <div class="page-head">
        <div class="container">
            <div class="page-title">
                <h1>
                    基础模块
                    <small>用户添加</small>
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
            <div class="portlet light">
                <div class="portlet-title">
                    <div class="caption">
                        <i class="fa fa-cogs font-green-sharp"></i>
                        <span class="caption-subject font-green-sharp bold uppercase">用户添加</span>
                    </div>
                </div>
                <div class="portlet-body form">
                    <!-- 开始表单-->
                    <form id="userForm" class="form-horizontal" method="post" action="/user/${msg}">
                        <input type="hidden" name="id" id="user_id" value="${user.id }"/>
                        <div class="form-body">
                            <div class="form-group">
                                <label class="control-label col-md-3" for="role_id">
                                    角色
                                    <span class="required"> * </span>
                                </label>
                                <div class="col-md-4">
                                    <select class="form-control" name="roleid" id="role_id">
                                        <option value="">请选择职位...</option>
                                        <c:forEach items="${roleList.datas}" var="role">
                                            <option value="${role.id}"
                                                    <c:if test="${role.id == user.roleid}">selected</c:if> >
                                                    ${role.name}
                                            </option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-md-3" for="name">
                                    姓名
                                    <span class="required"> * </span>
                                </label>
                                <div class="col-md-4">
                                    <input type="text" name="fullname" id="name" value="${user.fullname}" class="form-control" placeholder="姓名">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-md-3" for="email">
                                    电子邮件地址
                                    <span class="required"> * </span>
                                </label>
                                <div class="col-md-4">
                                    <input type="text" name="email" id="email" value="${user.email}" class="form-control" placeholder="电子邮件地址">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-md-3" for="telephone">
                                    联系电话
                                    <span class="required"> * </span>
                                </label>
                                <div class="col-md-4">
                                    <input type="text" name="telephone" id="telephone" value="${user.telephone}" class="form-control" placeholder="联系电话">
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="control-label col-md-3" for="bz">
                                    备注
                                    <span class="required">  </span>
                                </label>
                                <div class="col-md-4">
                                    <input type="text" name="bz" id="bz" value="${user.bz}" class="form-control" placeholder="备注">
                                </div>
                            </div>

                            <h3 class="form-section">输入您的帐户详细信息：</h3>
                            <div class="form-group">
                                <label class="control-label col-md-3" for="loginname">
                                    登入用户名
                                    <span class="required"> * </span>
                                </label>
                                <div class="col-md-4">
                                    <input type="text" name="username" id="loginname" value="${user.username}" class="form-control" placeholder="登入用户名">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-md-3" for="password">
                                    密码
                                    <span class="required"> * </span>
                                </label>
                                <div class="col-md-4">
                                    <input type="password" name="password" id="password" value="${user.password}" class="form-control" placeholder="密码">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-md-3" for="re_password">
                                    再次输入密码
                                    <span class="required"> * </span>
                                </label>
                                <div class="col-md-4">
                                    <input type="password" name="rpassword" id="re_password" value="" class="form-control" placeholder="再次输入密码">
                                </div>
                            </div>
                        </div>
                        <div class="form-actions">
                            <div class="row">
                                <div class="col-md-offset-3 col-md-9">
                                    <a onclick="save();" class="btn green">确定</a>
                                    <a href="javascript:history.go(-1);" class="btn default" type="submit">返回</a>
                                </div>
                            </div>
                        </div>
                    </form>
                    <!--结束表单-->
                </div>
            </div>
        </div>
    </div>
</div>

<jsp:include page="../../common/common_js.jsp"/>
<script src="${pageContext.request.contextPath}/Metronic/js/jquery/jquery.tips.js"></script>
<script type="text/javascript">
    $(document).ready(function () {
        if ("" != $("#user_id").val()) {
            $("#loginname").attr("readonly", "readonly");
            $("#loginname").css("color", "gray");
        }
    });

    //保存
    function save() {
        if ($("#role_id").val() == "") {
            $("#role_id").tips({
                side: 3,
                msg: '选择角色',
                bg: '#AE81FF',
                time: 2
            });
            $("#role_id").focus();
            return false;
        }
        if ($("#name").val() == "") {
            $("#name").tips({
                side: 3,
                msg: '输入姓名',
                bg: '#AE81FF',
                time: 3
            });
            $("#name").focus();
            return false;
        }
        if ($("#email").val() == "") {

            $("#email").tips({
                side: 3,
                msg: '输入邮箱',
                bg: '#AE81FF',
                time: 3
            });
            $("#email").focus();
            return false;
        } else if (!ismail($("#email").val())) {
            $("#email").tips({
                side: 3,
                msg: '邮箱格式不正确',
                bg: '#AE81FF',
                time: 3
            });
            $("#email").focus();
            return false;
        }
        var myreg = /^(((13[0-9]{1})|159)+\d{8})$/;
        if ($("#telephone").val() == "") {

            $("#telephone").tips({
                side: 3,
                msg: '输入手机号',
                bg: '#AE81FF',
                time: 3
            });
            $("#telephone").focus();
            return false;
        } else if ($("#telephone").val().length != 11 && !myreg.test($("#telephone").val())) {
            $("#telephone").tips({
                side: 3,
                msg: '手机号格式不正确',
                bg: '#AE81FF',
                time: 3
            });
            $("#telephone").focus();
            return false;
        }
        if ($("#loginname").val() == "" || $("#loginname").val() == "此用户名已存在!") {
            $("#loginname").tips({
                side: 3,
                msg: '输入登入用户名',
                bg: '#AE81FF',
                time: 2
            });
            $("#loginname").focus();
            $("#loginname").val('');
            $("#loginname").css("background-color", "white");
            return false;
        } else {
            $("#loginname").val(jQuery.trim($('#loginname').val()));
        }
        if ($("#user_id").val() == "" && $("#password").val() == "") {

            $("#password").tips({
                side: 3,
                msg: '输入密码',
                bg: '#AE81FF',
                time: 2
            });
            $("#password").focus();
            return false;
        }
        if ($("#password").val() != $("#re_password").val()) {
            $("#re_password").tips({
                side: 3,
                msg: '两次密码不相同',
                bg: '#AE81FF',
                time: 3
            });
            $("#re_password").focus();
            return false;
        }
        if ($("#user_id").val() == "") {
            hasU();
        } else {
            $("#userForm").submit();
        }
    }
    function ismail(mail) {
        return (new RegExp(/^(?:[a-zA-Z0-9]+[_\-\+\.]?)*[a-zA-Z0-9]+@(?:([a-zA-Z0-9]+[_\-]?)*[a-zA-Z0-9]+\.)+([a-zA-Z]{2,})+$/).test(mail));
    }

    function hasU() {
        var username = $.trim($("#loginname").val());
        $.ajax({
            type: "POST",
            url: "${pageContext.request.contextPath}/user/hasU",
            data: {username: username},
            dataType: 'json',
            cache: false,
            success: function (data) {
                if ("success" == data.result) {
                    $("#userForm").submit();
                } else {
                    $("#loginname").css("background-color", "#D16E6C");
                    setTimeout("$('#loginname').val('此用户名已存在!')", 500);
                }
            }
        });
    }
</script>
<script>
    jQuery(document).ready(function () {
        $('[data-toggle="tooltip"]').tooltip();
        Metronic.init();
        Layout.init(); // init current layout
        Demo.init(); // init demo features
    });
</script>
</body>
</html>



