<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
                            <th>所属部门</th>
                            <th>岗位</th>
                            <th>创建时间</th>
                            <th>最后登录时间</th>
                            <th>ip地址</th>
                            <th>相关操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <th>登入名</th>
                            <th>姓名</th>
                            <th>电子邮件地址</th>
                            <th>联系电话</th>
                            <th>所属部门</th>
                            <th>岗位</th>
                            <th>创建时间</th>
                            <th>最后登录时间</th>
                            <th>ip地址</th>
                            <th>相关操作</th>
                        </tr>
                        </tbody>

                    </table>
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



