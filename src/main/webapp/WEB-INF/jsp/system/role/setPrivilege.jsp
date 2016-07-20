<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<!--[if IE 8]><html lang="en" class="ie8 no-js"><![endif]-->
<!--[if IE 9]><html lang="en" class="ie9 no-js"><![endif]-->
<!--[if !IE]><!-->
<html lang="en" class="no-js">
<!--<![endif]-->
<head>
    <jsp:include page="../../common/common_css.jsp"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/Metronic/js/jquery/zTree_v3-master/css/zTreeStyle/zTreeStyle.css" type="text/css">
</head>
<body>
<div id="role" class="page-container">
    <div class="page-head">
        <div class="container">
            <div class="page-title">
                <h1>
                    健康度检查
                    <small>营业厅渠道</small>
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
            <div class="portlet light">
                <div class="portlet-title">
                    <div class="caption">
                        <i class="fa fa-cogs font-green-sharp"></i>
                        <span class="caption-subject font-green-sharp bold uppercase">权限列表</span>
                    </div>
                </div>
                <div class="portlet-body flip-scroll">
                    <div class="tree-demo jstree jstree-2 jstree-default jstree-checkbox-selection">
                        <ul id="tree" class="ztree"></ul>
                    </div>

                    <div class="form-actions">
                        <div class="row">
                            <div class="col-md-offset-3 col-md-9">
                                <a onclick="save();" class="btn green">确定</a>
                                <a href="javascript:history.go(-1);" class="btn default" type="submit">返回</a>
                            </div>
                        </div>
                    </div>

                </div>
            </div>
        </div>
    </div>
</div>

<jsp:include page="../../common/common_js.jsp"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/Metronic/js/jquery/zTree_v3-master/js/jquery.ztree.core.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/Metronic/js/jquery/zTree_v3-master/js/jquery.ztree.excheck.js"></script>
<script>
    jQuery(document).ready(function () {
        Metronic.init();
        Layout.init();
        Demo.init();
    });
</script>
<script type="text/javascript">
    var zTree;
    $(document).ready(function () {

        var setting = {
            check: {
                enable: true  //设置 zTree 的节点上是否显示 checkbox / radio
            },
            data: {
                simpleData: {
                    enable: true,
                    idKey: "id",
                    pIdKey: "pId",
                    rootPId: 0
                }
            }
        };
        var zn = '${zTreeNodes}';
        var zTreeNodes = eval(zn);
        zTree = $.fn.zTree.init($("#tree"), setting, zTreeNodes);
    });
</script>
<script type="text/javascript">

    function save() {

        var nodes = zTree.getCheckedNodes();
        var tmpNode;
        var ids = "";
        for (var i = 0; i < nodes.length; i++) {
            tmpNode = nodes[i];
            if (i != nodes.length - 1) {
                ids += tmpNode.id + ",";
            } else {
                ids += tmpNode.id;
            }
        }

        var roleId = "${roleid}";
        var url = "${pageContext.request.contextPath}/role/saveSetPriv";
        var postData;
        postData = {"roleid": roleId, "menuIds": ids};

        $.post(url, postData, function (data) {
            $("#role").html(data);
        });
    }

</script>
</body>
</html>

