<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="page-header-menu">
    <div class="container">
        <div class="hor-menu ">
            <ul class="nav navbar-nav">
                <li class="active">
                    <a href="#">主页</a>
                </li>
                <li class="menu-dropdown mega-menu-dropdown">
                    <a data-hover="megamenu-dropdown" data-close-others="true" data-toggle="dropdown" href="javascript:;" class="dropdown-toggle">
                        基础模块 <i class="fa fa-angle-down"></i>
                    </a>
                    <ul class="dropdown-menu pull-left">
                        <li class="dropdown-submenu">
                            <a href="javascript:;"><i class="icon-settings"></i>系统管理 </a>
                            <ul class="dropdown-menu">
                                <li class=" ">
                                    <a href="${pageContext.request.contextPath}/user/list" target="mainFrame">用户管理 </a>
                                </li>
                                <li class=" ">
                                    <a href="${pageContext.request.contextPath}/role/list" target="mainFrame">角色管理 </a>
                                </li>
                                <li class=" ">
                                    <a href="${pageContext.request.contextPath}/privilege/list" target="mainFrame">权限管理 </a>
                                </li>
                            </ul>
                        </li>
                    </ul>
                </li>
                <li class="menu-dropdown classic-menu-dropdown ">
                    <a data-hover="megamenu-dropdown" data-close-others="true" data-toggle="dropdown" href="javascript:;">
                        系统健康度指数动态监控 <i class="fa fa-angle-down"></i>
                    </a>
                    <ul class="dropdown-menu pull-left">
                        <li class=" dropdown-submenu">
                            <a href=":;"><i class="icon-briefcase"></i>系统健康度指数动态监控 </a>
                            <ul class="dropdown-menu">
                                <li class=" ">
                                    <a href="#">
                                        Basic Datatables </a>
                                </li>
                                <li class=" ">
                                    <a href="#">
                                        Tree Datatables </a>
                                </li>
                            </ul>
                        </li>
                    </ul>

                </li>
            </ul>
        </div>
    </div>
</div>

