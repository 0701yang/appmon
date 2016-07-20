<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
<div class="page-container">
    <div class="page-head">
        <div class="container">
            <div class="page-title">
                <h1>
                    基础模块
                    <small>角色添加</small>
                </h1>
            </div>
            <div class="page-toolbar">
                <div class="btn-group btn-theme-panel">
                    <a class="btn dropdown-toggle" type="button" href="javascript:history.go(-1);" >
                        <i class="fa fa-reply"></i>
                    </a>
                </div>
            </div>
        </div>
    </div>
    <div class="page-content">
        <div class="container">
            <div class="portlet light">
                <div class="portlet-title">
                    <div class="caption">
                        <i class="fa fa-cogs font-green-sharp"></i>
                        <span class="caption-subject font-green-sharp bold uppercase">角色添加</span>
                    </div>
                </div>
                <div class="portlet-body form">
                    <!-- 开始表单-->
                    <form id="roleForm" class="form-horizontal" method="post"  action="/role/${msg}">
                        <input type="hidden" name="id" id="role_id" value="${role.id }"/>
                        <div class="form-body">

                            <div class="form-group">
                                <label class="control-label col-md-3" for="name">
                                    角色名称
                                    <span class="required"> * </span>
                                </label>
                                <div class="col-md-4">
                                    <input type="text" name="name" id="name" value="${role.name}" class="form-control" placeholder="角色名称">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-md-3" for="description">
                                    备注
                                    <span class="required">  </span>
                                </label>
                                <div class="col-md-4">
                                    <input type="text" name="description" id="description" value="${role.description}" class="form-control" placeholder="备注">
                                </div>
                            </div>
                        </div>
                        <div class="form-actions">
                            <div class="row">
                                <div class="col-md-offset-3 col-md-9">
                                    <a  onclick="save();" class="btn green" >确定</a>
                                    <a href="javascript:history.go(-1);" class="btn default" type="submit">返回</a>
                                </div>
                            </div>
                        </div>
                    </form>
                    <!--结束表单-->
                </div>
            </div>
        </div>
    </div>
</div>

<jsp:include page="../../common/common_js.jsp"/>
<script src="${pageContext.request.contextPath}/Metronic/js/jquery/jquery.tips.js"></script>
<script type="text/javascript">
    $(document).ready(function () {
        if ("" != $("#role_id").val()) {
            $("#name").attr("readonly", "readonly");
            $("#name").css("color", "gray");
        }
    });

    //保存
    function save(){
        if ($("#name").val() == "") {
            $("#name").tips({
                side: 3,
                msg: '输入角色名称',
                bg: '#AE81FF',
                time: 3
            });
            $("#name").focus();
            return false;
        }
        if($("#role_id").val()==""){
            hasR();//判断角色名称是否存在
        }else{
            $("#roleForm").submit();
        }
    }

    function hasR() {
        var rolename = $.trim($("#name").val());
        $.ajax({
            type: "POST",
            url: "${pageContext.request.contextPath}/role/hasR",
            data: {name: rolename},
            dataType: 'json',
            cache: false,
            success: function (data) {
                if ("success" == data.result) {
                    $("#roleForm").submit();
                } else {
                    $("#name").css("background-color", "#D16E6C");
                    setTimeout("$('#name').val('此角色名已存在!')", 500);
                }
            }
        });
    }
</script>
<script>
    jQuery(document).ready(function () {
        $('[data-toggle="tooltip"]').tooltip();
        Metronic.init();
        Layout.init(); // init current layout
        Demo.init(); // init demo features
    });
</script>
</body>
</html>



