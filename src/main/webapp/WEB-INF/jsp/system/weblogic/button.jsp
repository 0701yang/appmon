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
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Metronic/plugins/bootstrap-daterangepicker/daterangepicker-bs3.css"/>
</head>
<body>
<!-- 开始 中间配置 -->
<div id="block">
    <!-- 开始 中部 -->
    <div class="col-md-12">

        <div class="page-head">
            <!-- 开始  -->
            <div class="page-title">
                <h1>
                    WebLogic性能监控
                    <small></small>
                </h1>
            </div>
            <!-- 结束 -->
        </div>
        <!-- 开始 Radios-->
        <div class="row">
            <div class="col-md-12">
                <div class="portlet light">
                    <!--开始 radios选择-->
                    <div class="portlet-body form">
                        <!--开始 表单的提交-->
                        <form name="form" id="crm_radios" class="form-horizontal form-row-sepe">
                            <div class="form-body">
                                <!--开始 多选择按钮-->
                                <div class="form-group">
                                    <div class="col-md-3">
                                        <label class="control-label" for="system">系统</label>
                                        <select id="system" class="form-control input-medium select2me">
                                            <option value="">Select...</option>
                                            <c:forEach items="${systems}" var="systems">
                                                <option value="${systems}">${systems}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                    <div class="col-md-3">
                                        <label class="control-label" for="value">模块</label>
                                        <select id="value" class="form-control input-medium select2me">
                                        </select>
                                    </div>
                                    <div class="col-md-3">
                                        <label class="control-label" for="name">集群</label>
                                        <select id="name" class="form-control input-medium select2me">
                                        </select>
                                    </div>
                                </div>
                                <!--结束 多选择按钮-->
                                <!--开始 选择日期-->
                                <div class="form-group">
                                    <div class="md-checkbox-inline md-radio-inline">
                                        <!--开始 显示BUG按钮-->
                                        <div class="md-checkbox has-error">
                                            <input id="checkbox15" class="md-check" type="checkbox" value="bug"
                                                   name="bug">
                                            <label for="checkbox15">
                                                <span></span>
                                                <span class="check"></span>
                                                <span class="box"></span>
                                                只显示异常实例
                                            </label>
                                        </div>

                                        <!--结束 显示BUG按钮  -->
                                        <!--开始 当前时间-->
                                        <div class="md-radio has-success">
                                            <input id="radios_nowtime" class="md-radiobtn" type="radio"
                                                   name="time" value="nowtime" checked="checked">
                                            <label for="radios_nowtime">
                                                <span></span>
                                                <span class="check"></span>
                                                <span class="box"></span>
                                                当前时间
                                            </label>
                                        </div>
                                        <!--结束 当前时间-->
                                        <!--开始 指定时间-->
                                        <div class="md-radio has-success">
                                            <input id="radios_zdtime" class="md-radiobtn" type="radio"
                                                   name="time" value="zdtime">
                                            <label for="radios_zdtime">
                                                <span></span>
                                                <span class="check"></span>
                                                <span class="box"></span>
                                                指定时间
                                            </label>
                                        </div>
                                        <!--结束 指定时间-->
                                        <!--开始 日历  -->
                                        <div id="reportrange" class="btn default">
                                            <i class="fa fa-calendar"></i>
                                            &nbsp;
                                            <span></span>
                                            <b class="fa fa-angle-down"></b>
                                        </div>
                                        <!--结束 日历  -->
                                        <!--开始 确定按钮-->
                                        <div class="btn-group">
                                            <a type="submit" class="btn btn-default"
                                               onclick="crm_weblogic_table_search();" href="#">查询</a>
                                        </div>
                                        <!--结束 确定按钮-->
                                    </div>
                                </div>
                                <!--结束 选择日期  -->
                                <!--结束 表单的提交-->
                            </div>
                        </form>
                    </div>
                    <!--结束 radios选择  -->
                </div>
            </div>
        </div>
        <!--结束 radios-->
        <div class="row">
            <div class="col-md-12">
                <!--开始 列表-->
                <div id="crm_weblogic_table">
                    <div id="loading"></div>
                    <div id="error"></div>
                </div>
                <!--end 列表-->
            </div>
        </div>
    </div>
</div>
<!--model曲线-->
<div id="weblogic_history_modal" class="modal fade bs-example-modal-lg" aria-labelledby="myLargeModalLabel" role="dialog" tabindex="-1" style="display: none;">
    <div id="row" class="modal-dialog modal-lg" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h4 id="myLargeModalLabel" class="modal-title">曲线</h4>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div id="weblogic_history_charts" style="padding-left: 15px; padding-right: 15px"></div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>
<!--end 曲线 -->

<!--model曲线-->
<div id="crm-app-modal" class="modal fade bs-example-modal-lg" aria-labelledby="myLargeModalLabel" role="dialog" tabindex="-1" style="display: none;">
    <div id="row2" class="modal-dialog modal-lg" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h4 id="myLargeModalLabel2" class="modal-title">连接池曲线</h4>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div id="crm_app_charts" style="padding-left: 15px; padding-right: 15px"></div>
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
<!--loading -->
<script src="${pageContext.request.contextPath}/Metronic/js/spin.min.js"></script>
<!--导入charts-->
<script type="text/javascript" src="${pageContext.request.contextPath}/Metronic/js/highcharts.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/Metronic/js/exporting.js"></script>
<!--时间-->
<script type="text/javascript" src="${pageContext.request.contextPath}/Metronic/plugins/bootstrap-daterangepicker/moment.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/Metronic/plugins/bootstrap-daterangepicker/daterangepicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/Metronic/js/components-pickers.js"></script>

<!--当modal关闭时，即把数据清除即可：-->
<script>
    $('#weblogic_history_charts').on("hidden.bs.modal", function () {
        $(this).removeData("bs.modal");
    });

    $('#crm-app-modal').on("hidden.bs.modal", function () {
        $(this).removeData("bs.modal");
    });
</script>

<!--下拉框-->
<script type="text/javascript">
    $(document).ready(
            function () {
                //一级下拉联动二级下拉
                $("#system").change(function () {
                    //清除二级下拉列表
                    $("#value").empty();
                    $("#name").empty();
                    $("#value").append($("<option/>").text("Select...").attr("value", ""));
                    //要请求的二级下拉JSON获取页面
                    var url = "${pageContext.request.contextPath}/weblogic/findValue";
                    //将选中的一级下拉列表项的id传过去
                    $.post(url, {systemValue: $(this).attr("value")}, function (data) {
                        //对请求返回的JSON格式进行分解加载
                        for (var i = 0; i < data.length; i++) {
                            //alert(data[i]);
                            $("#value").append($("<option/>").text(data[i]).attr("value", data[i]));
                        }
                    });
                });
                //二级下拉联动三级下拉
                $("#value").change(function () {
                    $("#name").empty();
                    $("#name").append($("<option/>").text("Select...").attr("value", ""));
                    var url = "${pageContext.request.contextPath}/weblogic/findName";
                    $.post(url, {systemName: $(this).attr("value")}, function (data) {
                        for (var i = 0; i < data.length; i++) {
                            $("#name").append($("<option/>").text(data[i]).attr("value", data[i]));
                        }
                    });
                });

            });
</script>

<!--点击查询-->
<script type="text/javascript">
    function crm_weblogic_table_search() {
        Metronic.blockUI({
            target: '#block',
            boxed: true,
            textOnly: true
        });
        var threeName, twoValue, oneSystem, bug, time, datetime, date, start, end;
        oneSystem = $("#system").attr("value");
        twoValue = $("#value").attr("value");
        threeName = $("#name").attr("value");
        bug = $("input[name='bug']:checked").val(); //bug
        if (typeof bug == "undefined") {
            bug = "";
        } else {
            bug
        }
        time = $("input[name='time']:checked").val(); //当前时间
        datetime = $("#reportrange span").html();//
        date = datetime.split("--");
        start = $.trim(date[0].toString()); //开始时间
        end = $.trim(date[1].toString()); //结束时间
        $("#crm_weblogic_table").load("${pageContext.request.contextPath}/weblogic/button", {
            system: oneSystem,   //第一个下拉框
            value: twoValue,//第二个下拉框
            name: threeName,//第三个下拉框
            bug: bug,
            time: time,
            start: start,
            end: end
        }, function (response, status, xhr) {
            if (status == "error") {
                var msg = "有错误：";
                $("#error").html(msg + xhr.status + " " + xhr.statusText + " " + response);
                Metronic.unblockUI('#block');
            }
            if (status == "success") {
                Metronic.unblockUI('#block');
                $("#crm_weblogic_table").fadeIn('slow');
            }


        });
    }
</script>

<!--曲线-->
<script type="text/javascript">

    function weblogic_history_charts(ip, port) {
        var chart;

        $.post("${pageContext.request.contextPath}/weblogic/charts", {
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
                        marker: {
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


<script type="text/javascript">
    function crm_app_charts(name) {
        var chart;
        Highcharts.setOptions({
            global: {
                useUTC: false
            }
        });
        $.post("${pageContext.request.contextPath}/weblogic/crmCharts", {
            "name": name
        }, function (data) {
            //声明数组
            var recodeArry;
            //循环取出回调函数返回数据(时分秒)
            $.each(data, function (a, b) {
                recodeArry = b.ctime;//向数组的末尾添加一个或多个元素，并返回新的长度
            });

            chart = new Highcharts.Chart({
                chart: {
                    //显示的div
                    renderTo: 'crm_app_charts',
                    //图形类型
                    defaultSeriesType: 'scatter'
                },
                credits: {enabled: false},
                title: {
                    text: '数据库活动链接数'
                },
                tooltip: {
                    formatter: function () {
                        return '<b>' + this.series.name + ':' + '</b><br/>' + '<b>'
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
                    },
                    plotLines: [{
                        value: 0,
                        width: 1,
                        color: '#808080'
                    }]
                },
                plotOptions: {
                    series: {
                        lineWidth: 1,
                        marker: {
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
                        },
                        events: {
                            legendItemClick: function (e) {
                                /*
                                 * 默认实现是显示或隐藏当前数据列，e 代表事件， this 为当前数据列
                                 */
                                var index = this.index;
                                var series = this.chart.series;
                                for (var i = 0; i < series.length; i++) {
                                    var s = series[i];
                                    if (index != i) {
                                        if (!series[i].visible) {
                                            s.show();
                                        } else {
                                            s.hide();
                                        }
                                    } else {
                                        s.show();
                                    }
                                }
                                return false;
                            }
                        }
                    }

                }

            });
            var arry;
            var names;
            //循环取出回调函数返回数据(时分秒)
            $.each(data, function (i, item) {
                names = data[i].cname;
                arry = data[i].cnumber;
                chart.addSeries({
                    id: i,
                    name: names,
                    data: arry
                }, false);
            });
            chart.redraw();//重绘
        });
    }
</script>

<!--修改图表宽度-->
<script>
    function updateChartSize() {
        var width_web = $("#row").width();
        var width_app = $("#row2").width();

        //动态修改容器大小
        $("#weblogic_history_charts").width(width_web - 50);
        $("#crm_app_charts").width(width_web - 50);

    }
</script>

<script>
    jQuery(document).ready(function () {
        ComponentsPickers.init();
        updateChartSize();
        //监听窗体大小变更事件
        $(window).resize(updateChartSize);
        $(document).resize(updateChartSize);
    });
</script>
</body>
</html>



