<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% String path = request.getContextPath();%>
<div class="page-header-menu">
    <div class="container">
        <div class="hor-menu ">
            <ul class="nav navbar-nav">
                <li class="active">
                    <a href="#">主页</a>
                </li>
                <li class="menu-dropdown mega-menu-dropdown ">
                    <a data-hover="megamenu-dropdown" data-close-others="true" data-toggle="dropdown" href="javascript:;" class="dropdown-toggle">
                        基础模块 <i class="fa fa-angle-down"></i>
                    </a>
                    <ul class="dropdown-menu" style="min-width: 710px">
                        <li>
                            <div class="mega-menu-content">
                                <div class="row">
                                    <div class="col-md-4">
                                        <ul class="mega-menu-submenu">
                                            <li>
                                                <a href="ecommerce_index.html" class="iconify">
                                                    <i class="icon-home"></i>
                                                    系统管理 </a>
                                            </li>
                                            <li>
                                                <a href="ecommerce_orders.html" class="iconify">
                                                    <i class="icon-basket"></i>
                                                    操作审计 </a>
                                            </li>
                                            <li>
                                                <a href="ecommerce_orders_view.html" class="iconify">
                                                    <i class="icon-tag"></i>
                                                    信息看板 </a>
                                            </li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                        </li>
                    </ul>
                </li>
                <li class="menu-dropdown mega-menu-dropdown mega-menu-full ">
                    <a data-hover="megamenu-dropdown" data-close-others="true" data-toggle="dropdown" href="javascript:;" class="dropdown-toggle">
                        系统健康指数动态监控 <i class="fa fa-angle-down"></i>
                    </a>
                    <ul class="dropdown-menu">
                        <li>
                            <div class="mega-menu-content">
                                <div class="row">
                                    <div class="col-md-3">
                                        <ul class="mega-menu-submenu">
                                            <li>
                                                <a href="ui_general.html">
                                                    <i class="fa fa-angle-right"></i>
                                                    系统健康指数动态监控 </a>
                                            </li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                        </li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
</div>

