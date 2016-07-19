<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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
                                            <a href="
                                                    <c:if test="${empty sub.url}">javascript:;</c:if>
                                                    <c:if test="${(!empty sub.url) && (fn:trim(sub.url) ne 'http://10.78.136.42/dashboard/db/gdash')}">${pageContext.request.contextPath}${sub.url}</c:if>
                                                    <c:if test="${fn:trim(sub.url) eq 'http://10.78.136.42/dashboard/db/gdash'}" >http://10.78.136.42/dashboard/db/gdash</c:if>
                                                    "
                                               target="mainFrame">
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
            </ul>
        </div>
    </div>
</div>

