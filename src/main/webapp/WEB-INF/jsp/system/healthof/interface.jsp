<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<!--[if IE 8]><html lang="en" class="ie8 no-js"><![endif]-->
<!--[if IE 9]><html lang="en" class="ie9 no-js"><![endif]-->
<!--[if !IE]><!-->
<html lang="en" class="no-js">
<!--<![endif]-->
<head>
    <jsp:include page="../../common/common_css.jsp"/>
    <link href="${pageContext.request.contextPath}/Metronic/css/pricing-table.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class="page-container">
    <div class="page-head">
        <div class="container">
            <div class="page-title">
                <h1>
                    健康度检查
                    <small>核心能力中心层</small>
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
            <!--第一块:接口子域-->
            <div class="row">
                <div class="col-md-12">
                    <div class="portlet light">
                        <div class="portlet-title">
                            <div class="caption">
                                <i class="fa fa-cogs"></i>
                                接口子域
                            </div>
                        </div>
                        <div class="portlet-body">
                            <!--第一行:CBOSS、统一银行接口-->
                            <div class="row margin-bottom-40">
                                <div class="col-md-6">
                                    <div class="pricing hover-effect">
                                        <div class="pricing-head">
                                            <h3 style="background: #36d7ac">
                                                CBOSS
                                            </h3>
                                        </div>
                                        <ul class="pricing-content list-unstyled">
                                            <li>
                                                <i class="fa fa-tags"></i> 测试1
                                            </li>
                                            <li>
                                                <i class="fa fa-asterisk"></i> 测试2
                                            </li>
                                            <li>
                                                <i class="fa fa-heart"></i> 测试3
                                            </li>
                                            <li>
                                                <i class="fa fa-star"></i> 测试4
                                            </li>
                                            <li>
                                                <i class="fa fa-shopping-cart"></i> 测试5
                                            </li>
                                        </ul>
                                        <div class="pricing-footer">
                                            <a href="javascript:;" class="btn yellow-crusta">
                                                点击详情 <i class="m-icon-swapright m-icon-white"></i>
                                            </a>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="pricing hover-effect">
                                        <div class="pricing-head">
                                            <h3 style="background: #36d7ac">
                                                统一银行接口
                                            </h3>
                                        </div>
                                        <ul class="pricing-content list-unstyled">
                                            <li>
                                                <i class="fa fa-tags"></i> 测试1
                                            </li>
                                            <li>
                                                <i class="fa fa-asterisk"></i> 测试2
                                            </li>
                                            <li>
                                                <i class="fa fa-heart"></i> 测试3
                                            </li>
                                            <li>
                                                <i class="fa fa-star"></i> 测试4
                                            </li>
                                            <li>
                                                <i class="fa fa-shopping-cart"></i> 测试5
                                            </li>
                                        </ul>
                                        <div class="pricing-footer">
                                            <a href="javascript:;" class="btn yellow-crusta">
                                                点击详情 <i class="m-icon-swapright m-icon-white"></i>
                                            </a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>