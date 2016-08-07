//���ID
function DataGrid_GetID(id){
	return document.all("DataGrid_Body_"+id);
}

//���datagrid�ı�ͷ��ID
function DataGrid_GetHeaderID(id){
	return document.all("DataGrid_Header_Table_"+id);
}

//���ݱ����п����--��갴��ȥ�Ĳ���
function DataGrid_MouseDown2Resize(tdObj,dataTableObj){
	//�����ǰ���ڣ���ô�ͷ���ǰ���ڵ�
	tdObj.releaseCapture();
	tdObj.mouseDownX=0;

	tdObj.mouseDownX = event.clientX;
	tdObj.pareneTdW = tdObj.parentElement.offsetWidth;
	tdObj.pareneTableW = dataTableObj.offsetWidth;
	tdObj.setCapture();
}

//���ݱ����п����--����ƶ��Ĳ���
function DataGrid_MouseMove2Resize(tdObj,dataTableObj){
	if(!tdObj.mouseDownX){
		return false;
	}

	var newWidth = tdObj.pareneTdW*1+event.clientX*1-tdObj.mouseDownX;

	if(newWidth>0){
		var cellIndex = parseInt(tdObj.parentElement._cellIndex);

		//�����ı������ͷ���������µĿ������
		tdObj.parentElement.style.width = newWidth;
		tdObj.parentElement.width = newWidth;

		//�����ı������ݱ���ȵ����µ���
		for(var i=0;i<dataTableObj.rows.length;i++){
			dataTableObj.rows(i).cells(cellIndex).width = newWidth;
			dataTableObj.rows(i).cells(cellIndex).style.width = newWidth;
		}
	}
}

//���ݱ����п����--��굯��Ĳ���
function DataGrid_MouseUp2Resize(tdObj,dataTableObj){
	tdObj.releaseCapture();
	tdObj.mouseDownX=0;
}


//�Ա��ָ���н�������
function DataGrid_OrderTD(dataTableObj){
	if(event.srcElement._sort!="asc" && event.srcElement._sort!='desc'){
		return;
	}

	if(event.srcElement.tagName=='TD'){
		var index = parseInt(event.srcElement._cellIndex);
		//Ĭ����������
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

		//��ȡ��ʱ���п�
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

		//������һ�ε���������
		event.srcElement._sort = sortType;

		//��������ͼƬ
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
		//�����е�����ͼƬ����Ϊ��
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

		//�����,���п����µ���
		for(var i=0;i<dataTableObj.rows.length;i++){
			var tmpCell = dataTableObj.rows(i).cells;
			for(var j=0;j<tmpCell.length;j++){
				tmpCell[j].style.width = tmpWidth[j];
			}
		}
		//return false;
	}
}

//������е�ĳ��cell��˫����
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

//����������ˮƽ����������������ʱ��
function DataGrid_TableDivScroll(headDivId){
	var obj = window.event.srcElement;
	document.all(headDivId).scrollLeft = obj.scrollLeft;
}

//���·�ҳ
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

//��ǰ��ҳ
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

//��һҳ
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

//���һҳ
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

//��λ����һҳ
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
		alert("ָ����ҳ���ܴ������һҳ");
		return;
	}

	if(page<1){
		alert("ָ����ҳ����С�ڵ�һҳ");
		return;
	}

	var rtn = AjaxPost("/turnpage?page="+(page-1)+"&uuid="+uuid+"&rowcount="+rowcount,null);

	dataTableObj.parentElement.parentElement.innerHTML = rtn;
}

//ˢ��ҳ��
function DataGrid_RefreshPage(tableid){
	var dataTableObj = DataGrid_GetID(tableid);
	var uuid     = dataTableObj._uuid;
	var rtn = AjaxPost("/turnpage?page="+0+"&uuid="+uuid,null);

	dataTableObj.parentElement.parentElement.innerHTML = rtn;
}

//��������ˢ��ҳ��
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

//���������岿�� toHtmlString
function DataGrid_ToHtmlString(tableid){
	var dataTableObj = DataGrid_GetID(tableid);
	return dataTableObj.parentElement.parentElement.innerHTML;
}

//���xml toXmlString
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

//��ͷ��checkboxѡ�У���ô���е�checkbox����ͬ���Ĳ���
function DataGrid_TopCheckBoxOnClick(dataTableObj){
	var tmpCheck = event.srcElement.checked;
	var cellIndex = parseInt(event.srcElement.parentElement._cellIndex);

	for(var i=0;i<dataTableObj.rows.length;i++){
		dataTableObj.rows(i).cells(cellIndex).firstChild.checked = tmpCheck;
	}
}

//���ѡ����е����
function DataGrid_GetSelectedRow(dataTableObj){
	var rtn = new Array();
	if(dataTableObj.rows.length>0){
		var cellIndex = -1;
		var tmpCell = dataTableObj.rows(0).cells;

		//checkbox��radio���ǿ��Ե�
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

//�����кź�������ȡcell����
function DataGrid_GetValue(id,rowIndex,colIndex){
	return DataGrid_GetID(id).rows(rowIndex).cells(colIndex).innerText.trim();
}

//�����ܹ�����
function DataGrid_GetRowCount(id){
	return DataGrid_GetID(id).rows.length;
}

//����ָ������ѡ��״̬
function DataGrid_SetRowSelected(id,rowIndex,cellIndex){
	DataGrid_GetID(id).rows(rowIndex).cells(cellIndex).firstChild.checked = "true";
}

//�������ɸѡ
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

//ɾ��ָ����
function DataGrid_DeleteRow(id,rowIndex){
  DataGrid_GetID(id).deleteRow(parseInt(rowIndex));
}

