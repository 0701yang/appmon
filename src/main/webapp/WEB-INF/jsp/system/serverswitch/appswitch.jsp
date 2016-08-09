<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*" %>
<%@ page import="com.shsnc.entity.serverswitch.MonServer" %>
<%@ page import="com.shsnc.util.appswitch.ParallelUtil" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ include file="/Metronic/js/serverswitch/BaseHeaderInclude.jsp"%>
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
<%
    Object serverObj=request.getAttribute("serverType");
    if(serverObj==null){
        request.getRequestDispatcher("/appmon/mon/control/AppSwitch?server='server'").forward(request,response);
        return;
    }
%>
<div class="page-container">
    <div class="page-head">
        <div class="container">
            <div class="page-title">
                <h1>
                    切换集群
                    <small></small>
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
            <table width="100%" align="center" border="1" style="TABLE-LAYOUT: fixed; BORDER-COLLAPSE: collapse;"
                   borderColor="#000000" bgColor="#FAFCFF" style="font-size:12px">
                <tr>
                    <td>APP集群</td>
                    <td>配置连接到此APP集群上的服务器列表</td>
                    <td>可选列表</td>
                    <td>切换</td>
                </tr>

                <%
                    MonServer[] objMonServer=(MonServer[])request.getAttribute("objMonServer");
                    ParallelUtil.ConnectApp[] objConnectApp=(ParallelUtil.ConnectApp[])request.getAttribute("objConnectApp");
                    TreeMap map = (TreeMap) (request.getAttribute("map"));
                    Set key1 = map.keySet();
                    HashMap mapping=(HashMap)request.getAttribute("mapping");
                    TreeMap canSelectedMap=(TreeMap)request.getAttribute("canSelectedMap");
                    for (Iterator iter = key1.iterator(); iter.hasNext(); ) {
                        String appClusterName = (String) iter.next();
                        out.print("<tr>\n");
                        out.print("<td>　<input type='hidden' id='gw_" + appClusterName + "' value='" + appClusterName + "'/> " + appClusterName + "</td>\n");
                        ArrayList l = (ArrayList) map.get(appClusterName);
                        String[] str = (String[]) l.toArray(new String[0]);
                        if (!l.isEmpty()) {
                            java.util.Arrays.sort(str, new com.shsnc.util.appswitch.StringNumberCompator());
                        }

                        ArrayList list = new ArrayList();
                        for (int i = 0; i < str.length; i++) {
                            list.add(mapping.get(str[i]));
                        }

                        StringBuffer sb = new StringBuffer();
                        for (int i = 1; i < str.length + 1; i++) {
                            sb.append(str[i - 1]);
                            if (i % 2 == 1) {
                                sb.append(",");
                            }
                            if (i != 0 && i % 2 == 0) {
                                sb.append("<br/>");
                            }
                        }
                        sb.append("<br/>");

                        out.print("<td>　<input type='hidden' class='inputcla' id='gw_" + appClusterName + "_serverList' value='" + org.apache.commons.lang.StringUtils.join(list.iterator(), ",") + "'/><div align='left'>" + sb.toString() + "</div></td>\n");
                        ;

                       out.print("<td><input type='text' class='inputcla' id='gw_sel_"+appClusterName+"' value='"+appClusterName+"'/>");
                        out.print("<div class='divcla controlhide'><ul class='ulcla'>");
                        HashMap tmp = (HashMap) canSelectedMap.get(appClusterName);
                        String[] str2 = (String[]) tmp.keySet().toArray(new String[0]);
                        java.util.Arrays.sort(str2, new com.shsnc.util.appswitch.StringNumberCompator());
                        for (int i = 0; i < str2.length; i++) {

                                out.print("<li >" + str2[i] + "</li>");
                        }
                        out.print("</ul></div> </td>\n");

                        out.print("<td align='center'><input class='formButton' onclick=\"switchGroupApp('" + appClusterName + "')\" type='button' value='批量切换集群'/></td>");
                        out.print("</tr>\n");
                    }

                %>
            </table>

            <script type="text/javascript">

                function switchGroupApp(appClusterName) {
                    var oldApp = document.getElementById('gw_' + appClusterName).value;
                    var newApp = document.getElementById("gw_sel_" + appClusterName).value;

                    if (!confirm("确信要批量操作吗?")) {
                        return;
                    }
//        if(oldApp==newApp){
//          alert("当然连接的APP和选择的APP一样")
//          return;
//        }


                    var srvList = document.getElementById("gw_" + appClusterName + "_serverList").value;
                    var condition = "";
                    var tmp = srvList.split(",");
                    for (var i = 0; i < tmp.length; i++) {
                        condition += tmp[i] + ":" + newApp + "^";
                    }

                    var rtn = AjaxPost("/business/com.shsnc.service.serverswitch.switchaction.AppSwitchAction", "action=switchApp&condition=" + condition);

                    alert("切换集群执行情况\n" + rtn);
                    location.reload();
                }
            </script>

            <br/>
            <br/>
            <br/>

            <div align="center">
                <table width="100%" align="center" border="1" style="TABLE-LAYOUT: fixed; BORDER-COLLAPSE: collapse;"
                       borderColor="#000000" bgColor="#FAFCFF" style="font-size:12px">
                    <tr>
                        <td>web或客户端服务器名称</td>
                        <td>配置连接app的集群</td>
                        <td>当前连接app的集群</td>
                        <td>是否修改</td>
                        <td>修改app的集群</td>
                    </tr>

                    <%
                        try {
                            for (int i = 0; i < objMonServer.length; i++) {
                                if (!objConnectApp[i].oldAppCluster.equalsIgnoreCase(objConnectApp[i].currentAppCluster)) {
                                    out.print("<tr bgcolor=\"red\">\n");
                                } else {
                                    out.print("<tr>\n");
                                }

                                out.print("<td>　<input type='hidden' id='id_" + objMonServer[i].getName() + "' value='" + objMonServer[i].getServerId() + "'/> " + objMonServer[i].getName() + "</td>\n");
                                out.print("<td>　 " + objConnectApp[i].oldAppCluster + "</td>\n");
                                out.print("<td>　<input type='hidden' id='cur_" + objMonServer[i].getName() + "' value='" + objConnectApp[i].currentAppCluster + "'/> " + objConnectApp[i].currentAppCluster + "</td>\n");
                                if (objConnectApp[i].isOk) {
                                    out.print("<td>　 <input type=\"checkbox\" id='" + objMonServer[i].getName() + "' value='" + objMonServer[i].getName() + "'> </td>\n");
                                } else {
                                    out.print("<td>　 <input type=\"checkbox\" id='" + objMonServer[i].getName() + "' value='" + objMonServer[i].getName() + "' disabled> </td>\n");
                                }


                                out.print("<td><input type='text' class='inputcla' id='gw_sel_"+objConnectApp[i].currentAppCluster+"' value='"+objConnectApp[i].currentAppCluster+"'/>");
                                out.print("<div class='divcla controlhide'><ul class='ulcla'>");
                                String[] tmp = objConnectApp[i].canSelectAppCluster;
                                for (int j = 0; j < tmp.length; j++) {
                                    out.print("<li value=\"" + tmp[j] + "\">" + tmp[j] + "</li>");
                                }
                                out.print("</ul></div> </td>\n");
                                out.print("</tr>\n");
                            }

                        } catch (Throwable ex) {
                            ex.printStackTrace();
                        }

                    %>
                </table>
                <input type="button" class='formButton' value="切换集群" onclick="switchApp();"/>
            </div>

        </div>
    </div>
</div>
</body>
</html>
<script type="text/javascript">
    function switchApp() {

        var el = document.getElementsByTagName('input');
        var len = el.length;

        var tmp = new Array();
        for (var i = 0; i < len; i++) {
            if ((el[i].type == "checkbox") && (el[i].checked)) {
                var tmp1 = document.getElementById('cur_' + el[i].value);
                var tmp2 = document.getElementById('sel_' + el[i].value);
                if (tmp2.value == null || tmp2.value == "") {
                    alert("服务器[" + el[i].value + "]的必须选择一个修改后的集群");
                    return;
                }

                if (tmp1.value == tmp2.value) {
                    alert("服务器[" + el[i].value + "]的当前集群和修改后的集群一样");
                    return;
                }
                else {
                    var tmp3 = document.getElementById('id_' + el[i].value)
                    tmp.push(tmp3.value + ":" + tmp2.value);
                }
            }
        }

        if (tmp.length <= 0) {
            alert("必须选择一个服务器进行操作")
            return;
        }

        var condition = "";
        for (var i = 0; i < tmp.length; i++) {
            condition += tmp[i] + "^";
        }
        var rtn = AjaxPost("/business/com.shsnc.service.serverswitch.switchaction.AppSwitchAction", "action=switchApp&condition=" + condition);
        alert("切换集群执行情况\n" + rtn);
        location.reload();
    }
</script>
<script type="text/javascript">
    $(function(){


        $(".inputcla").focus(function () {

            $(this).select();
            $(this).next().removeClass("controlhide");
            $(this).next().addClass("controlshow");

            $(this).next().find("ul").children().hover(function () {
                $(this).css("background-color", "#1E90FF");
            }, function () {
                $(this).css("background-color", "");
            });
            $(this).next().find("ul").children().click(function () {
                var inner = $(this).context;
                $(this).parent().parent().val(inner.innerHTML);
                $(this).parent().parent().removeClass("controlshow");
                $(this).parent().parent().addClass("controlhide");

            });
            $(this).keydown(function (event) {
                if (event.keyCode == 13) {
                    var conval = $(this).val();
                    var content = $(this).next().find("ul").children();
                    for (var i = 0; i < content.length; i++) {
                        if (content[i].innerHTML.indexOf(conval) != -1) {
                            $(this).val(content[i].innerHTML);
                            $(this).next().removeClass("controlshow");
                            $(this).next().addClass("controlhide");
                            break;
                        }
                    }
                }
            });
        });




    });

</script>