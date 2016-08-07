function AjaxPost(url,xml) {
//alert(url)
 //var start = new Date();
 //var req = new ActiveXObject("Microsoft.XMLHTTP");
var req;
if(window.ActiveXObject){
req = new ActiveXObject("Microsoft.XMLHTTP");
}
else if(window.XMLHttpRequest){
req = new XMLHttpRequest()
}
 req.open("POST", "<%=request.getContextPath()%>"+url, false);
 req.setRequestHeader("CONTENT-TYPE","application/x-www-form-urlencoded");
 req.send(xml);
 //window.status = "Call Finished,time:"+((new Date())-start)+":ms";
 return (req.responseText)
}


var _context_path = "<%=request.getContextPath()%>";
