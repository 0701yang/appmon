<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${!empty datas}">
    <c:forEach items="${datas}" var="datas" varStatus="vs">
        <div class="portlet box blue-hoki">
            <div class="portlet-title">
                <div class="caption">
                    <i class="fa fa-globe"></i>
                    Server：${datas.ip}&nbsp;&nbsp;KEY：${datas.key}
                </div>
                <div class="tools"></div>
            </div>
            <div class="portlet-body">
                <table class="table table-striped table-bordered table-hover">
                    <thead>
                    <tr>
                        <c:forEach items="${datas.list[0]}" var="a">
                            <th> ${a.substring(0,a.indexOf(":" , 0 ))}</th>
                        </c:forEach>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="j" begin="0" end="${datas.list.size()-1}">
                        <tr>
                            <c:forEach items="${datas.list[j]}" var="b">
                                <td>${b.substring(b.indexOf(":" , 0 )+1)}</td>
                            </c:forEach>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </c:forEach>
</c:if>
<c:if test="${empty datas}">
    <tr>
        <td colspan="${datas.list.size()-1}" align="center" bgcolor="#EFF3F7">
            没有找到相应的记录
        </td>
    </tr>
</c:if>


