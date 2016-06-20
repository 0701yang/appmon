<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% String path = request.getContextPath();%>
<div class="page-header-top">
    <div class="container">
        <div class="page-logo">
            <a href="#"><img src="<%=path %>/img/logo-default.png" alt="logo" class="logo-default"></a>
        </div>
        <a href="javascript:;" class="menu-toggler"></a>
        <div class="top-menu">
            <ul class="nav navbar-nav pull-right">
                <li class="dropdown dropdown-user dropdown-dark">
                    <a href="javascript:;" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown"
                       data-close-others="true">
                        <span class="username username-hide-on-mobile">${user.fullname} </span>
                        <img alt="" class="img-circle" src="<%=path%>/Metronic/imge/avatar9.jpg"/>
                    </a>
                    <ul class="dropdown-menu dropdown-menu-default">
                        <li>
                            <a href="javascript:exitSystem()">
                                <i class="icon-key"></i>
                                登出
                            </a>
                        </li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
</div>
<script type="text/javascript">
    function exitSystem() {
        window.parent.location.href = $("#contextPath").val()+ "/user_logout.action";
    }
</script>