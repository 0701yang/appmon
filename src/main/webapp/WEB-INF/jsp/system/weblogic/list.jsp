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
<div>
    <div class="col-md-12">
        <div class="portlet light">
            <div class="portlet-body flip-scroll">
                <table class="table table-bordered table-striped table-condensed flip-content">
                    <thead class="flip-content">
                    <tr>
                        <th class="numeric">集群组</th>
                        <th class="numeric">集群</th>
                        <th class="numeric">IP地址</th>
                        <th class="numeric">端口</th>
                        <th class="numeric">实例名</th>
                        <th class="numeric">线程总数</th>
                        <th class="numeric">空闲线程数</th>
                        <th class="numeric">占用线程数</th>
                        <th class="numeric">查看历史信息</th>
                    </tr>
                    </thead>
                    <tbody>

                    <c:forEach items="${bean.datas}" var="bean">
                        <tr>
                            <c:choose>
                                <c:when test="${bean.threadrun ge bean.threadthreshold}">
                                    <td>${bean.app}</td>
                                    <td>${bean.module}</td>
                                    <td>${bean.ip}</td>
                                    <td class="label-danger">${bean.port}</td>
                                    <td class="label-danger">
                                        <a onclick="crm_app_charts('${bean.server}');"
                                           data-toggle="modal" data-target="#crm-app-modal">${bean.server}</a>
                                    </td>
                                    <td class="label-danger">&nbsp;&nbsp;</td>
                                    <td class="label-danger">&nbsp;&nbsp;</td>
                                    <td class="label-danger">DOWN or HANG</td>
                                    <td>
                                        <a type="button" class="btn btn-primary"
                                           href="${pageContext.request.contextPath}/weblogic/history?ip=${bean.ip}&port=${bean.port}" >历史数据</a>
                                        |
                                        <a onclick="weblogic_history_charts( '${bean.ip}' , '${bean.port}' );"
                                           class="btn btn-primary" data-toggle="modal" data-target="#weblogic_history_modal">曲线</a>
                                    </td>
                                </c:when>
                                <c:when test="${bean.threadtotal eq '-1' || bean.threadidle eq '-1' || bean.threadrun eq '-1'}">
                                    <td>${bean.app}</td>
                                    <td>${bean.module}</td>
                                    <td>${bean.ip}</td>
                                    <td class="label-warning">${bean.port}</td>
                                    <td class="label-warning">${bean.server}</td>
                                    <td class="label-warning">${bean.threadtotal}</td>
                                    <td class="label-warning">${bean.threadidle}</td>
                                    <td class="label-warning">${bean.threadrun}</td>
                                    <td>
                                        <a type="button" class="btn btn-primary"
                                           href="${pageContext.request.contextPath}/weblogic/history?ip=${bean.ip}&port=${bean.port}"
                                        >历史数据</a>
                                        |
                                        <a onclick="weblogic_history_charts( '${bean.ip}' , '${bean.port}' );"
                                           class="btn btn-primary"
                                           data-toggle="modal" data-target="#weblogic_history_modal">曲线</a>
                                    </td>
                                </c:when>
                                <c:otherwise>
                                    <td>${bean.app}</td>
                                    <td>${bean.module}</td>
                                    <td>${bean.ip}</td>
                                    <td>${bean.port}</td>
                                    <td>${bean.server}</td>
                                    <td>${bean.threadtotal}</td>
                                    <td>${bean.threadidle}</td>
                                    <td>${bean.threadrun}</td>
                                    <td>
                                        <a type="button" class="btn btn-primary"
                                           href="${pageContext.request.contextPath}/weblogic/history?ip=${bean.ip}&port=${bean.port}">历史数据</a>
                                        |
                                        <a onclick="weblogic_history_charts( '${bean.ip}' , '${bean.port}' );"
                                           class="btn btn-primary" data-toggle="modal" data-target="#weblogic_history_modal">曲线</a>
                                    </td>
                                </c:otherwise>
                            </c:choose>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <div class="row">
                    <c:if test="${bean.total > 0}">
                        <jsp:include page="../../common/Page.jsp">
                            <jsp:param value="${bean.total }" name="totalRecord"/>
                            <jsp:param value="${list}" name="url"/>
                        </jsp:include>
                    </c:if>
                </div>
            </div>
        </div>
    </div>
</div>
<!--model曲线-->
<div id="weblogic_history_modal" class="modal fade bs-example-modal-lg" aria-labelledby="myLargeModalLabel" role="dialog" tabindex="-1"
     style="display: none;">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 id="myLargeModalLabel" class="modal-title">曲线</h4>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div id="weblogic_history_charts" class="col-md-12"></div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>
<!--end 曲线 -->


<jsp:include page="../../common/common_js.jsp"/>
<!--导入charts-->
<script type="text/javascript" src="${pageContext.request.contextPath}/Metronic/js/highcharts.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/Metronic/js/exporting.js"></script>

<!--当modal关闭时，即把数据清除即可：-->
<script>
    $('#weblogic_history_charts').on("hidden.bs.modal", function() {
        $(this).removeData("bs.modal");
    });
</script>

<script>
    jQuery(document).ready(function () {
        Metronic.init();
        Layout.init();
        Demo.init();

    });
</script>
<!--曲线-->
<script type="text/javascript">
    function weblogic_history_charts(ip, port) {
        var chart;

        $.post('/weblogic/charts', {
            "ip": ip,
            "port": port
        }, function (data) {

            //声明数组
            var recodeArry = [];
            //循环取出回调函数返回数据(时分秒)
            $.each(data, function (i, item) {
                recodeArry.push(data[i].time);
            });
            chart = new Highcharts.Chart({
                chart: {
                    //显示的div
                    renderTo: 'weblogic_history_charts',
                    //图形类型
                    defaultSeriesType: 'scatter'
                },
                credits: {enabled: false},
                title: {
                    text: 'Thread Run'
                },
                tooltip: {
                    formatter: function () {
                        return '<b>' + this.x + ':' + '</b><br/>' + '<b>'
                                + 'count: ' + this.y + '</b>';
                    }
                },
                //将时间的数组设置到X轴上
                xAxis: {
                    categories: recodeArry
                },
                yAxis: {
                    title: {
                        text: 'Count'
                    }
                },
                plotOptions: {
                    series: {
                        lineWidth: 1,
                        marker:{
                            enabled: false
                        },
                        point: {
                            events: {
                                //点击删除曲线
                                'click': function () {
                                    if (this.series.data.length > 1)
                                        this.remove();
                                }
                            }
                        }
                    }
                },
                series: [{
                    name: '线程数量',
                    data: []
                }]
            });
            var arry = [];
            $.each(data, function (i, item) {
                arry.push(data[i].threadrun)
            });
            chart.series[0].setData(arry);

        });

    }
</script>
</body>
</html>



