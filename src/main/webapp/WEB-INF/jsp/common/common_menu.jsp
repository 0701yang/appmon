<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="page-header-menu">
    <div class="container">
        <div class="hor-menu ">
            <ul class="nav navbar-nav">
                <c:forEach items="${userpriviegeList}" var="upl">
                    <c:if test="${empty upl.parentid}">
                        <li class="menu-dropdown mega-menu-dropdown">
                            <a data-hover="megamenu-dropdown" data-close-others="true" data-toggle="dropdown" href="javascript:;" class="dropdown-toggle">
                                    ${upl.name} <i class="fa fa-angle-down"></i>
                            </a>

                            <ul class="dropdown-menu pull-left">
                                <c:forEach items="${userpriviegeList}" var="sub">
                                    <c:if test="${upl.id eq sub.parentid}">
                                        <li class="${(empty sub.url) ? 'dropdown-submenu' :'menu-dropdown classic-menu-dropdown' }">
                                            <a href="${pageContext.request.contextPath}${(empty sub.url) ? 'javascript:;' : sub.url}" target="mainFrame">
                                                    ${sub.name} </a>
                                            <ul class="dropdown-menu">
                                                <c:forEach items="${userpriviegeList}" var="c">
                                                    <c:if test="${c.parentid eq sub.id}">
                                                        <li class=" ">
                                                            <a href="${pageContext.request.contextPath}${c.url}" target="mainFrame">${c.name} </a>
                                                        </li>
                                                    </c:if>
                                                </c:forEach>
                                            </ul>
                                        </li>
                                    </c:if>
                                </c:forEach>
                            </ul>
                        </li>
                    </c:if>
                </c:forEach>

                <%--<li class="menu-dropdown mega-menu-dropdown">--%>
                <%--<a data-hover="megamenu-dropdown" data-close-others="true" data-toggle="dropdown" href="javascript:;" class="dropdown-toggle">--%>
                <%--基础模块 <i class="fa fa-angle-down"></i>--%>
                <%--</a>--%>
                <%--<ul class="dropdown-menu pull-left">--%>
                <%--<li class="dropdown-submenu">--%>
                <%--<a href="javascript:;"><i class="icon-settings"></i>系统管理 </a>--%>
                <%--<ul class="dropdown-menu">--%>
                <%--<li class=" ">--%>
                <%--<a href="${pageContext.request.contextPath}/user/list" target="mainFrame">用户管理 </a>--%>
                <%--</li>--%>
                <%--<li class=" ">--%>
                <%--<a href="${pageContext.request.contextPath}/role/list" target="mainFrame">角色管理 </a>--%>
                <%--</li>--%>
                <%--</ul>--%>
                <%--</li>--%>


                <%--<li class="dropdown-submenu">--%>
                <%--<a href="${pageContext.request.contextPath}/druid/index.html" target="mainFrame"><i class="icon-settings"></i>数据库监控 </a>--%>
                <%--</li>--%>
                <%--</ul>--%>
                <%--</li>--%>


                <%--<li class="menu-dropdown classic-menu-dropdown ">--%>
                <%--<a  href="${pageContext.request.contextPath}/dashboard/index" target="mainFrame">--%>
                <%--系统健康度指数动态监控--%>
                <%--</a>--%>
                <%--</li>--%>


            </ul>
        </div>
    </div>
</div>

