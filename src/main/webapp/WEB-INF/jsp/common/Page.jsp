<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="pg" uri="http://jsptags.com/tags/navigation/pager" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--
Offset：传过来的值是偏移量，是选择的页面的页数。
        比如你的记录/页pageSize=3，那么传过来的offset应该如下处理：offset/3+1
        这样得到的就是要生成的页数！(在filter中)
url:分页的链接根地址，
    pager标签会在这个链接的基础上附加分页参数，
    但不允许像给页面跳转的url那样，
    在后面直接添加参数，而参数的传递时需要使用pg:param来指定。
items:总记录数，pager标签正是根据这个值来计算分页参数，很重要。
maxPageItems:每页显示的行数，默认为10.
maxIndexPages:在循环输出页码的时候，最大输出多少个页码，默认是10.
pg:first 第一页的标签
pg:pre 上一页标签
pg:next 下一页标签
pg:last 最后一页标签
pg:pages 循环输出页码信息
对于上面的标签都有类似的export变量：
pageUrl - 分页链接URL地址（最重要的export参数）
pageNumber- 页码
firstItem –对应页第一行的索引值
lastItem -对应页最后一行的索引值
--%>

<pg:pager export="curPage=pageNumber"
          items="${param.totalRecord }"
          maxPageItems="${param.pagesize}"
          maxIndexPages="10"
          url="${param.url }">

    <c:forEach items="${param.params }" var="p">
        <pg:param name="${p}"/>
    </c:forEach>

    <div class="col-md-5 col-sm-5">
        <div class="dataTables_info">
            共 <pg:last>
            <strong style="color: red">${pageNumber }</strong>页
        </pg:last>
            当前页第<strong style="color: red">${curPage}</strong>页
            &nbsp;
            每页显示<strong style="color: red">${param.pagesize}
        </strong>条
            &nbsp;
            共<strong style="color: red">${param.totalRecord }</strong>条记录
        </div>
    </div>

    <div class="col-md-7 col-sm-12">
        <div class="dataTables_paginate paging_simple_numbers">
            <ul class="pagination">
                <pg:first>
                    <li class="paginate_button previous">
                        <a id="firstPage" href="${pageUrl }">首页</a>
                    </li>
                </pg:first>
                <pg:prev>
                    <li class="paginate_button ">
                        <a href="${pageUrl }"><i class="fa fa-angle-left"></i></a>
                    </li>
                </pg:prev>
                <pg:pages>
                    <%--当点击了页码变暗--%>
                    <c:if test="${curPage eq pageNumber }">
                        <li class="paginate_button active"><a>${pageNumber }</a></li>
                    </c:if>
                    <c:if test="${curPage != pageNumber }">
                        <li class="paginate_button"><a href="${pageUrl}">${pageNumber }</a></li>
                    </c:if>
                </pg:pages>
                    <%--下一页--%>
                <pg:next>
                    <li class="paginate_button next">
                        <a href="${pageUrl }">
                            <i class="fa fa-angle-right"></i>
                        </a>
                    </li>
                </pg:next>

                <pg:last>
                    <c:choose>
                        <c:when test="${pageNumber eq 1}">
                            <li class="paginate_button previous disabled">
                                <a href="javascript:;">尾页</a>
                            </li>
                        </c:when>
                        <c:otherwise>
                            <li class="paginate_button previous">
                                <a href="${pageUrl }">尾页</a>
                            </li>
                        </c:otherwise>
                    </c:choose>
                </pg:last>

                <label>
                    <select name="pagesize" onchange="selectPagesize(this)" class="form-control input-xsmall input-inline select2-offscreen">

                        <option value="10"
                                <c:if test="${param.pagesize eq 10}">selected</c:if>>
                            10
                        </option>

                        <option value="50"
                                <c:if test="${param.pagesize eq 50}">selected</c:if>>
                            50
                        </option>

                        <option value="${param.totalRecord }"
                                <c:if test="${param.totalRecord eq param.pagesize}">selected</c:if>>
                            ALL
                        </option>

                    </select>
                </label>

            </ul>
        </div>
    </div>
</pg:pager>
<%--页面选择页码--%>
<script type="text/javascript">
    function selectPagesize(field) {
        alert(document.getElementById("firstPage").href + "&pageSize=" + field.value);
        window.location = document.getElementById("firstPage").href + "&pageSize=" + field.value;
    }
</script>

