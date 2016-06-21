<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="page-header-top">
    <div class="container">
        <div class="page-logo">
            <a href="#"><img src="${pageContext.request.contextPath}/Metronic/img/logo-default.png" alt="logo" class="logo-default"></a>
        </div>
        <a href="javascript:;" class="menu-toggler"></a>
        <div class="top-menu">
            <ul class="nav navbar-nav pull-right">
                <li class="dropdown dropdown-user dropdown-dark">
                    <a href="javascript:;" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" data-close-others="true">
                        <span class="username username-hide-on-mobile">登入名 </span>
                        <img alt="" class="img-circle" src="${pageContext.request.contextPath}/Metronic/img/avatar9.jpg"/>
                    </a>
                    <ul class="dropdown-menu dropdown-menu-default">
                        <li>
                            <a href="#">
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
