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
<div id="block" class="page-container">
    <div class="page-head">
        <div class="container">
            <div class="page-title">
                <h1>
                    日常工作台
                    <small>memcache数据可视化工具</small>
                </h1>
            </div>
            <div class="page-toolbar">
                <div class="btn-group btn-theme-panel">
                    <a class="btn dropdown-toggle" type="button" href="javascript:history.go(-1);">
                        <i class="fa fa-reply"></i>
                    </a>
                </div>
            </div>
        </div>
    </div>
    <div class="page-content">
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <div class="portlet light">
                        <div class="portlet-body form">
                            <form name="form" id="crm_radios" class="form-horizontal form-row-sepe">
                                <div class="form-body">
                                    <div class="form-group">
                                        <div class="col-md-3">
                                            <label class="control-label" for="system">系统</label>
                                            <select id="system" class="form-control input-medium select2me">
                                                <option value="">Select...</option>
                                                <option value="crm.server.list">老版营业厅</option>
                                                <option value="scrm.server.list">新版营业厅</option>
                                                <option value="ccs.server.list">合作渠道</option>
                                            </select>
                                        </div>
                                        <div class="col-md-3">
                                            <label class="control-label" for="value">IP和端口</label>
                                            <select id="value" class="form-control input-medium select2me">
                                            </select>
                                        </div>
                                        <div class="col-md-3">
                                            <label class="control-label">
                                                KEY值：
                                                <span class="required"> * </span>
                                            </label>
                                            <input type="text" name="key" id="key" value="" class="form-control" placeholder="这里输入KEY" title="KEY" data-required="1"/>
                                        </div>
                                        <div class="col-md-3">
                                            <div style="padding-top: 27px">
                                                <a type="submit" class="btn btn-default" onclick="sersearch();" href="#">查询</a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <!--开始 列表-->
                    <div id="table"></div>
                    <!--end 列表-->
                </div>
            </div>
        </div>
    </div>
    <jsp:include page="../../common/common_js.jsp"/>
    <!--loading -->
    <script src="${pageContext.request.contextPath}/Metronic/js/spin.min.js"></script>
    <script src="${pageContext.request.contextPath}/Metronic/js/jquery/jquery.tips.js"></script>
    <%--下拉框查询--%>
    <script type="text/javascript">
        $(document).ready(
                function () {
                    //一级下拉联动二级下拉
                    $("#system").change(function () {
                        //清除二级下拉列表
                        $("#value").empty();
                        $("#value").append($("<option/>").text("ALL").attr("value", "ALL"));
                        //要请求的二级下拉JSON获取页面

                        //将选中的一级下拉列表项的id传过去
                        $.post('${pageContext.request.contextPath}/memcache/select', {server: $(this).attr("value")}, function (data) {
                            //对请求返回的JSON格式进行分解加载
                            for (var i = 0; i < data.length; i++) {
                                $("#value").append($("<option/>").text(data[i]).attr("value", data[i]));
                            }
                        });
                    });
                });
    </script>

    <%--查询按钮--%>
    <script type="text/javascript">
        function sersearch() {
            if ($("#system").val() == "") {
                $("#system").tips({
                    side: 3,
                    msg: '请选择系统',
                    bg: '#AE81FF',
                    time: 3
                });
                $("#system").focus();
                return false;
            }

            if ($("#key").val() == "") {
                $("#key").tips({
                    side: 3,
                    msg: '输入KEY值',
                    bg: '#AE81FF',
                    time: 3
                });
                $("#key").focus();
                return false;
            }
            Metronic.blockUI({
                target: '#block',
                boxed: true,
                textOnly: true
            });

            var hostip, keyname, server;
            server = $("#system").attr("value");
            hostip = $("#value").attr("value");
            keyname = $("#key").attr("value");
            $("#table").load('${pageContext.request.contextPath}/memcache/showlist',
                    {"server": server, "hostip": hostip, "key": keyname},
                    function (response, status, xhr) {
                        Metronic.unblockUI('#block');
                        $("#crm_weblogic_table").fadeIn('slow');
                    });
        }
    </script>

</body>
