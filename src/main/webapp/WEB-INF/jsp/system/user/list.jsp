<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<div class="page-content">
    <div class="container">
        <div class="col-md-12">
            <ul class="page-breadcrumb breadcrumb">
                <li>
                    <a href="#">主页</a>
                    <i class="fa fa-circle"></i>
                </li>
                <li>
                    <a href="#">基础模块</a>
                    <i class="fa fa-circle"></i>
                </li>
                <li>
                    <a href="#">系统管理</a>
                    <i class="fa fa-circle"></i>
                </li>
                <li class="active"> 用户管理</li>
            </ul>
        </div>

        <div class="col-md-12">
            <div class="portlet light">
                <div class="portlet-title">
                    <div class="caption">
                        <i class="fa fa-cogs font-green-sharp"></i>
                        <span class="caption-subject font-green-sharp bold uppercase">用户列表</span>
                    </div>
                    <div class="actions btn-set">
                        <button class="btn green-haze btn-circle">
                            <i class="fa fa-check"></i>
                            添加
                        </button>
                    </div>
                    <div class="actions btn-set">
                        <button class="btn yellow btn-circle">
                            <i class="fa fa-check-circle"></i>
                            刷新
                        </button>
                    </div>
                </div>
                <div class="portlet-body flip-scroll">
                    <table class="table table-bordered table-striped table-condensed flip-content">
                        <thead class="flip-content">
                        <tr>
                            <th>登入名</th>
                            <th>姓名</th>
                            <th>电子邮件地址</th>
                            <th>联系电话</th>
                            <th>角色名称</th>
                            <th>创建时间</th>
                            <th>最后登录时间</th>
                            <th>ip地址</th>
                            <th>备注</th>
                            <th>相关操作</th>
                        </tr>
                        </thead>
                        <tbody>

                        <c:forEach items="${userList.datas}" var="user">
                            <tr>
                                <td>${user.username}</td>
                                <td>${user.fullname}</td>
                                <td>${user.email}</td>
                                <td>${user.telephone}</td>
                                <td>${user.rolename}</td>
                                <td>${user.rolename}</td>
                                <td>${user.createdate}</td>
                                <td>${user.lastdate}</td>
                                <td>${user.ip}</td>
                                <td>${user.bz}</td>
                                <td>
                                    <a class="edit btn btn-icon-only btn-circle red"
                                       href="user_editUI.action?id=${u.id}"
                                       data-toggle="tooltip" data-placement="top" title="编辑" target="mainFrame">编辑 </a>
                                    <a class="delete btn btn-icon-only btn-circle purple"
                                       href="user_delete.action?id=${u.id} "
                                       data-toggle="tooltip" data-placement="top" title="删除"
                                       onclick="return window.confirm('您确定要删除吗？')" target="mainFrame">删除 </a>
                                    <a class="delete btn btn-icon-only btn-circle purple"
                                       href="'user_initPassword.action?id=${u.id}"
                                       onclick="return window.confirm('您确定要初始化密码吗？')" data-toggle="tooltip"
                                       data-placement="top" title="初始化密码" target="mainFrame">初始化密码 </a>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>

                    </table>
                </div>
            </div>
        </div>

    </div>
</div>
<jsp:include page="../../common/common_js.jsp"/>
<script src="${pageContext.request.contextPath}/Metronic/js/tooltip.js" type="text/javascript"></script>
<script>
    jQuery(document).ready(function () {
        Metronic.init();
        Layout.init(); // init current layout
        Demo.init(); // init demo features
    });
</script>
</body>
</html>



