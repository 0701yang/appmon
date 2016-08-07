<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*" %>
<%@ page import="com.shsnc.entity.serverswitch.MonServer" %>
<%@ page import="com.shsnc.util.appswitch.ParallelUtil" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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

                        out.print("<td>　<input type='hidden' id='gw_" + appClusterName + "_serverList' value='" + org.apache.commons.lang.StringUtils.join(list.iterator(), ",") + "'/><div align='left'>" + sb.toString() + "</div></td>\n");
                        ;
                        out.print("<td>　<select size=\"1\" id='gw_sel_" + appClusterName + "'>");
                        HashMap tmp = (HashMap) canSelectedMap.get(appClusterName);
                        String[] str2 = (String[]) tmp.keySet().toArray(new String[0]);
                        java.util.Arrays.sort(str2, new com.shsnc.util.appswitch.StringNumberCompator());
                        for (int i = 0; i < str2.length; i++) {
                            if (str2[i].equalsIgnoreCase(appClusterName)) {
                                out.print("<option value=\"" + str2[i] + "\" selected>" + str2[i] + "</option>");
                            } else {
                                out.print("<option value=\"" + str2[i] + "\">" + str2[i] + "</option>");
                            }
                        }
                        out.print("</select> </td>\n");

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
                                out.print("<td>　<select size=\"2\" id='sel_" + objMonServer[i].getName() + "'>");
                                String[] tmp = objConnectApp[i].canSelectAppCluster;
                                for (int j = 0; j < tmp.length; j++) {
                                    out.print("<option value=\"" + tmp[j] + "\">" + tmp[j] + "</option>");
                                }
                                out.print("</select> </td>\n");
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