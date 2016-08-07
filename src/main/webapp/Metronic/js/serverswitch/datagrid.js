//获得ID
function DataGrid_GetID(id){
	return document.all("DataGrid_Body_"+id);
}

//获得datagrid的表头的ID
function DataGrid_GetHeaderID(id){
	return document.all("DataGrid_Header_Table_"+id);
}

//数据表格的列宽调整--鼠标按下去的操作
function DataGrid_MouseDown2Resize(tdObj,dataTableObj){
	//如果以前存在，那么释放以前存在的
	tdObj.releaseCapture();
	tdObj.mouseDownX=0;

	tdObj.mouseDownX = event.clientX;
	tdObj.pareneTdW = tdObj.parentElement.offsetWidth;
	tdObj.pareneTableW = dataTableObj.offsetWidth;
	tdObj.setCapture();
}

//数据表格的列宽调整--鼠标移动的操作
function DataGrid_MouseMove2Resize(tdObj,dataTableObj){
	if(!tdObj.mouseDownX){
		return false;
	}

	var newWidth = tdObj.pareneTdW*1+event.clientX*1-tdObj.mouseDownX;

	if(newWidth>0){
		var cellIndex = parseInt(tdObj.parentElement._cellIndex);

		//调整的表格数据头，进行重新的宽度设置
		tdObj.parentElement.style.width = newWidth;
		tdObj.parentElement.width = newWidth;

		//调整的表格的数据表格宽度的重新调整
		for(var i=0;i<dataTableObj.rows.length;i++){
			dataTableObj.rows(i).cells(cellIndex).width = newWidth;
			dataTableObj.rows(i).cells(cellIndex).style.width = newWidth;
		}
	}
}

//数据表格的列宽调整--鼠标弹起的操作
function DataGrid_MouseUp2Resize(tdObj,dataTableObj){
	tdObj.releaseCapture();
	tdObj.mouseDownX=0;
}


//对表格指定列进行排序
function DataGrid_OrderTD(dataTableObj){
	if(event.srcElement._sort!="asc" && event.srcElement._sort!='desc'){
		return;
	}

	if(event.srcElement.tagName=='TD'){
		var index = parseInt(event.srcElement._cellIndex);
		//默认是排正序
		var sortType = "asc";

		if(event.srcElement._sort!=null || event.srcElement._sort!='null' ){
			sortType=event.srcElement._sort;
			if(sortType=='asc'){
				sortType = 'desc';
			}
			else{
				sortType = 'asc';
			}
		}

		//获取此时的列宽
		var tmpWidth = new Array();
		for(var i=0;i<dataTableObj.rows.length;i++){
			var tmpCell = dataTableObj.rows(i).cells;
			for(var j=0;j<tmpCell.length;j++){
				tmpWidth[j] = tmpCell[j].offsetWidth;
			}
			break;
		}


		for(var i=0;i<(dataTableObj.rows.length-1);i++){
			for(var j=i+1;j<dataTableObj.rows.length;j++){
				var tmp1 = null;
				var tmp2 = null;
				if(dataTableObj.rows[j].cells[index].firstChild.value==undefined){
					tmp1 = dataTableObj.rows[j].cells[index].innerText;
				}
				else{
					tmp1 = dataTableObj.rows[j].cells[index].firstChild.value;
				}

				if(dataTableObj.rows[i].cells[index].firstChild.value==undefined){
					tmp2 = dataTableObj.rows[i].cells[index].innerText;
				}
				else{
					tmp2 = dataTableObj.rows[i].cells[index].firstChild.value;
				}


				if(parseInt(tmp1) && parseInt(tmp2)){
					if(parseInt(tmp1)>parseInt(tmp2)){
						if(sortType=='desc'){
							dataTableObj.moveRow(j,i);
						}
					}
					else if(parseInt(tmp1)<parseInt(tmp2)){
						if(sortType=='asc'){
							dataTableObj.moveRow(j,i);
						}
					}
				}
				else{
					if(tmp1>tmp2){
						if(sortType=='desc'){
							dataTableObj.moveRow(j,i);
						}
					}
					else if(tmp1<tmp2){
						if(sortType=='asc'){
							dataTableObj.moveRow(j,i);
						}
					}
				}
			}
		}

		//设置下一次的排序类型
		event.srcElement._sort = sortType;

		//设置排序图片
		var tmpChild = event.srcElement.childNodes;
		for(var i=0;i<tmpChild.length;i++){
			if(tmpChild[i].tagName!=null && tmpChild[i].tagName!=undefined && tmpChild[i].tagName=='IMG'){
				if(sortType=='desc'){
					tmpChild[i].width=10;
					tmpChild[i].src=_context_path+"/ajaxtags/img/desc.gif";
				}
				else if(sortType=='asc'){
					tmpChild[i].width=10;
					tmpChild[i].src=_context_path+"/ajaxtags/img/asc.gif";
				}
				break;
			}
		}
		//其他列的排序图片设置为空
		var tmpCell = event.srcElement.parentElement.cells;
		for(var i=0;i<tmpCell.length;i++){
			if(event.srcElement!=tmpCell[i]){
				var tmpObj = tmpCell[i].childNodes;
				for(var j=0;j<tmpObj.length;j++){
					if(tmpObj[j]!=null && tmpObj[j].tagName!=null && tmpObj[j].tagName!=undefined && tmpObj[j].tagName=='IMG'){
						tmpObj[j].width=0;
					}
				}
			}
		}

		//排序后,对列宽重新调整
		for(var i=0;i<dataTableObj.rows.length;i++){
			var tmpCell = dataTableObj.rows(i).cells;
			for(var j=0;j<tmpCell.length;j++){
				tmpCell[j].style.width = tmpWidth[j];
			}
		}
		//return false;
	}
}

//当表格中的某个cell被双击后
function DataGrid_CellOnClick(dataTableObj){
	var cell = event.srcElement;
	if(!cell._editable){
		return;
	}

	if(cell._celltype && cell._celltype!=null && cell._celltype!=''){

		var cell_editor = null;

		if(cell._thisCellObj==null){
			eval("cell_editor = new DataGrid_Cell_"+cell._celltype+"(dataTableObj,cell)");
			cell._thisCellObj = cell_editor;
		}
		else{
			cell_editor = cell._thisCellObj;
		}

		if(cell_editor!=null){
			cell_editor.edit();
		}
	}
}

//当整个表格的水平滚动条发生滚动的时候
function DataGrid_TableDivScroll(headDivId){
	var obj = window.event.srcElement;
	document.all(headDivId).scrollLeft = obj.scrollLeft;
}

//向下翻页
function DataGrid_TurnNextPage(dataTableObj,tableid){
	var uuid     = dataTableObj._uuid;
	var curpage  = parseInt(dataTableObj._curpage);
	var lastpage = parseInt(dataTableObj._lastpage);
	var rowcount = parseInt(dataTableObj._rowcount);
	if(lastpage==0 || (curpage+1)>=lastpage){
		return;
	}
	var nextpage = curpage+1;
	var rtn = AjaxPost("/turnpage?page="+nextpage+"&uuid="+uuid+"&rowcount="+rowcount,null)

	dataTableObj.parentElement.parentElement.innerHTML = rtn;
}

//向前翻页
function DataGrid_TurnPrePage(dataTableObj,tableid){
	var uuid     = dataTableObj._uuid;
	var curpage  = parseInt(dataTableObj._curpage);
	var lastpage = parseInt(dataTableObj._lastpage);
	var rowcount = parseInt(dataTableObj._rowcount);
	if(lastpage==0 || curpage==0){
		return;
	}
	var nextpage = curpage-1;
	var rtn = AjaxPost("/turnpage?page="+nextpage+"&uuid="+uuid+"&rowcount="+rowcount,null)

	dataTableObj.parentElement.parentElement.innerHTML = rtn;
}

//第一页
function DataGrid_TurnFirstPage(dataTableObj,tableid){
	  var uuid     = dataTableObj._uuid;
	var lastpage = parseInt(dataTableObj._lastpage);
	var rowcount = parseInt(dataTableObj._rowcount);
	if(lastpage==0){
		return;
	}
	var rtn = AjaxPost("/turnpage?page="+0+"&uuid="+uuid+"&rowcount="+rowcount,null)

	dataTableObj.parentElement.parentElement.innerHTML = rtn;
}

//最后一页
function DataGrid_TurnLastPage(dataTableObj,tableid){
	var uuid     = dataTableObj._uuid;
	var lastpage = parseInt(dataTableObj._lastpage)-1;
	var rowcount = parseInt(dataTableObj._rowcount);
	if(lastpage==0){
		return;
	}
	var rtn = AjaxPost("/turnpage?page="+lastpage+"&uuid="+uuid+"&rowcount="+rowcount,null);

	dataTableObj.parentElement.parentElement.innerHTML = rtn;
}

//定位任意一页
function DataGrid_TurnAnyPage(dataTableObj,tableid,gotoPageId){

	if(!parseInt(gotoPageId.value)){
		return;
	}

	var uuid     = dataTableObj._uuid;
	var page      = parseInt(gotoPageId.value);
	var lastpage  = parseInt(dataTableObj._lastpage);
	var firstpage = parseInt(dataTableObj._firstpage);
	var rowcount = parseInt(dataTableObj._rowcount);

	if(page>lastpage){
		alert("指定的页不能大于最后一页");
		return;
	}

	if(page<1){
		alert("指定的页不能小于第一页");
		return;
	}

	var rtn = AjaxPost("/turnpage?page="+(page-1)+"&uuid="+uuid+"&rowcount="+rowcount,null);

	dataTableObj.parentElement.parentElement.innerHTML = rtn;
}

//刷新页面
function DataGrid_RefreshPage(tableid){
	var dataTableObj = DataGrid_GetID(tableid);
	var uuid     = dataTableObj._uuid;
	var rtn = AjaxPost("/turnpage?page="+0+"&uuid="+uuid,null);

	dataTableObj.parentElement.parentElement.innerHTML = rtn;
}

//带条件的刷新页面
function DataGrid_RefreshPage(tableid,condition){
	var dataTableObj = DataGrid_GetID(tableid);
	var uuid     = dataTableObj._uuid;

	var url = "/turnpage?page="+0+"&uuid="+uuid+"&condition=true";

	if(!condition.isEmpty()){
	  url+= "&"+condition.toString();
	}

	var rtn = AjaxPost(url,null);

	dataTableObj.parentElement.parentElement.innerHTML = rtn;
}

//输出表格主体部分 toHtmlString
function DataGrid_ToHtmlString(tableid){
	var dataTableObj = DataGrid_GetID(tableid);
	return dataTableObj.parentElement.parentElement.innerHTML;
}

//输出xml toXmlString
function DataGrid_ToXmlString(tableid){
	var dataTableObj = DataGrid_GetID(tableid);

	var cellIndexArray = new Array();
	var cellTitleArray = new Array();
	var cellPropertiesArray = new Array();
	var dataTableHeaderObj = DataGrid_GetHeaderID(tableid);
	var headerCells = dataTableHeaderObj.rows(0).cells;
	for(var i=0;i<headerCells.length;i++){
	  if(headerCells(i).FieldProperty!=null){
	    cellIndexArray.push(i);
	    cellPropertiesArray.push(headerCells(i).FieldProperty);
	    cellTitleArray.push(headerCells(i).innerText);
	  }
	}
	var xml = "<?xml version=\"1.0\" encoding=\"GBK\"?>\n<datagrid id=\""+tableid+"\">\n";
	xml+="<titles>\n";
	for(var j=0;j<cellTitleArray.length;j++){
	    xml+="<title prop=\""+cellPropertiesArray[j]+"\">"+cellTitleArray[j].trim()+"</title>\n";
	}
	xml+="</titles>\n";

	for(var i=0;i<dataTableObj.rows.length;i++){
	  xml+="<row index=\""+i+"\">\n";
	  for(var j=0;j<cellIndexArray.length;j++){
	    xml+="<cell prop=\""+cellPropertiesArray[j]+"\">"+dataTableObj.rows(i).cells(cellIndexArray[j]).innerText.trim()+"</cell>\n";
	  }
	  xml+="</row>\n";
	}
	xml+="</datagrid>\n";
	return xml;
}

//表头的checkbox选中，那么所有的checkbox都做同样的操作
function DataGrid_TopCheckBoxOnClick(dataTableObj){
	var tmpCheck = event.srcElement.checked;
	var cellIndex = parseInt(event.srcElement.parentElement._cellIndex);

	for(var i=0;i<dataTableObj.rows.length;i++){
		dataTableObj.rows(i).cells(cellIndex).firstChild.checked = tmpCheck;
	}
}

//获得选择的行的序号
function DataGrid_GetSelectedRow(dataTableObj){
	var rtn = new Array();
	if(dataTableObj.rows.length>0){
		var cellIndex = -1;
		var tmpCell = dataTableObj.rows(0).cells;

		//checkbox和radio都是可以的
		for(var i=0;i<tmpCell.length;i++){
			var tmp = tmpCell[i].firstChild;
			if( (tmp.tagName == "INPUT" && tmp.type == "checkbox")
			    || (tmp.tagName == "INPUT" && tmp.type == "radio") ){
				cellIndex = i;
				break;
			}
		}

		if(cellIndex>=0){
			for(var i=0;i<dataTableObj.rows.length;i++){
				if(dataTableObj.rows(i).cells(cellIndex).firstChild.checked == true){
					rtn.push(i);
				}
			}
		}
	}
	return rtn;
}

//根据行号和列名获取cell数据
function DataGrid_GetValue(id,rowIndex,colIndex){
	return DataGrid_GetID(id).rows(rowIndex).cells(colIndex).innerText.trim();
}

//根据总共行数
function DataGrid_GetRowCount(id){
	return DataGrid_GetID(id).rows.length;
}

//设置指定的行选中状态
function DataGrid_SetRowSelected(id,rowIndex,cellIndex){
	DataGrid_GetID(id).rows(rowIndex).cells(cellIndex).firstChild.checked = "true";
}

//表格数据筛选
function DataGrid_ColumnFilter(dataTableObj,cellIndex,value){
	if(value==null || value==''){
		for(var i=0;i<dataTableObj.rows.length;i++){
			dataTableObj.rows[i].style.display="block";
		}
		return;
	}

	for(var i=0;i<dataTableObj.rows.length;i++){
		if(dataTableObj.rows[i].cells[cellIndex].innerText.indexOf(value)==0){
			dataTableObj.rows[i].style.display="block";
		}
		else{
			dataTableObj.rows[i].style.display="none";
		}
	}
}

//删除指定行
function DataGrid_DeleteRow(id,rowIndex){
  DataGrid_GetID(id).deleteRow(parseInt(rowIndex));
}

