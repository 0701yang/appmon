function DataGrid_Cell_event_detach(){
	if(event.srcElement.parentNode._thisCellObj!=null){
		event.srcElement.parentNode._thisCellObj.detach();
	}
}

//可编辑的cell－文本
function DataGrid_Cell_text(datagrid,cell){
		this.cell = cell;
		this.datagrid = datagrid;

		this.edit = function(){
			var oldVal = this.getValue();

			var tmpTextArea = document.createElement("TEXTAREA");
			tmpTextArea.style.width = "100%";
			tmpTextArea.style.height = (this.cell.offsetHeight-4)+"px";
			tmpTextArea.style.border = "0px";
			tmpTextArea.style.margin = "0px";
			tmpTextArea.style.padding = "0px";
			tmpTextArea.style.overflow = "hidden";
			tmpTextArea.wrap = "soft";
			tmpTextArea.style.textAlign = this.cell.align;
			tmpTextArea.style.color = "red";
			//tmpTextArea.onclick = function(e){(e||event).cancelBubble = true}
			tmpTextArea.value = oldVal;

			this.cell.innerHTML = "";
			this.cell.appendChild(tmpTextArea);

			tmpTextArea.focus();
			tmpTextArea.focus();
			focusLast(tmpTextArea);
			
			tmpTextArea.onblur = DataGrid_Cell_event_detach;
		}

		this.getValue = function(val){
			return this.cell.innerHTML.toString();
		}

		this.detach = function(){
			var tmp = this.cell.innerText;
			event.srcElement.parentNode.removeChild(event.srcElement.parentNode.lastChild);
			this.cell.innerHTML = tmp;
			this.cell.innerText = tmp;
		}
}


//可编辑的cell－下拉框
function DataGrid_Cell_select(datagrid,cell){
		this.cell = cell;
		this.datagrid = datagrid;

		this.edit = function(){
			this.val = this.getValue();

			var tmpOption = document.createElement("OPTION");
			tmpOption.text = "abc";
			tmpOption.value = "123"
			
			var tmpOption1 = document.createElement("OPTION");
			tmpOption1.text = "xyz";
			tmpOption1.value = "246"


			var tmpOption2 = document.createElement("OPTION");
			tmpOption2.text = "opq";
			tmpOption2.value = "681"

			
			var tmpSelect = document.createElement("SELECT");
			tmpSelect.add(tmpOption);
			tmpSelect.add(tmpOption1);
			tmpSelect.add(tmpOption2);
			tmpSelect.style.width="100%";

			this.cell.innerHTML = "";
			this.cell.appendChild(tmpSelect);

			tmpSelect.focus();
			tmpSelect.onblur = DataGrid_Cell_event_detach;
		}

		this.getValue = function(val){
			return this.cell.innerHTML.toString();
		}

		this.detach = function(){
			var tmp = this.cell.innerText;
			event.srcElement.parentNode.removeChild(event.srcElement.parentNode.lastChild);
			this.cell.innerHTML = tmp;
			this.cell.innerText = tmp;
		}
}

//可编辑的cell－日期
function DataGrid_Cell_date(datagrid,cell){
		this.cell = cell;
		this.datagrid = datagrid;

		this.edit = function(){
			this.val = this.getValue();

			var tmp = (new Date()).getTime();
		
			var tmpDiv = document.createElement("DIV");
			tmpDiv.style.width="100%";
			
			var tmpInput = document.createElement("input");
			tmpInput.id = tmp+"input";
			tmpInput.type = "text";
			tmpInput.disabled = "true";
			tmpInput.value="yh";
			tmpInput.style.width="80%";
			
			var tmpButton = document.createElement("button");
			tmpButton.id = tmp+"date";
			tmpButton.value = "...";
			tmpButton.style.width="20%";
			
			tmpDiv.appendChild(tmpInput);
			tmpDiv.appendChild(tmpButton);
			
			//alert(tmpDiv.innerHTML);
			
			this.cell.innerHTML = "";
			this.cell.appendChild(tmpDiv);

			//tmpSelect.focus();
			tmpDiv.onblur = DataGrid_Cell_event_detach;

			//日历的
			Calendar.setup(
			    {
			      button      : tmp+"date",
			      inputField  : tmp+"input",         // ID of the input field
			      ifFormat    : "%Y-%m-%d 00:00:00"   // the date format
			    }
			 );	
		}

		this.getValue = function(val){
			return this.cell.innerHTML.toString();
		}

		this.detach = function(){
			var tmp = this.cell.innerText;
			event.srcElement.parentNode.removeChild(event.srcElement.parentNode.lastChild);
			this.cell.innerHTML = tmp;
			this.cell.innerText = tmp;
		}
}