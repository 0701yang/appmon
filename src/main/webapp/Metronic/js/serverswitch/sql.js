function Criteria(){
  this.array = new Array();

  this.addNumberEqual = function(key,value){
    this.array.push(key+"="+value);
  }
  this.addStringEqual = function(key,value){
    if(!this.checkStringNotNull(key)){
	    return;
    }
    if(!this.checkStringNotNull(value)){
	    return;
    }
    this.array.push(key+"='"+value+"'");
  }
  this.addStringLike = function(key,value){
    if(!this.checkStringNotNull(key)){
	    return;
    }
    if(!this.checkStringNotNull(value)){
	    return;
    }
    this.array.push(key+" like '"+value+"%'");
  }
  this.addStringNotEqual = function(key,value){
    if(!this.checkStringNotNull(key)){
	    return;
    }
    if(!this.checkStringNotNull(value)){
	    return;
    }
    this.array.push(key+"!='"+value+"'");
  }

  this.addNumberNotEqual = function(key,value){
    if(!this.checkStringNotNull(key)){
	    return;
    }
    if(!this.checkNumberNotNull(value)){
	    return;
    }
    this.array.push(key+"!="+value);
  }
  this.addNumberLess = function(key,value){
    if(!this.checkStringNotNull(key)){
	    return;
    }
    if(!this.checkNumberNotNull(value)){
	    return;
    }
    this.array.push(key+"<"+value);
  }

  this.checkStringNotNull = function(str){
	  var rtn = true;
	if(str==null || str==''){
		    rtn = false;
	    }
	return rtn;
  }
  this.checkNumberNotNull = function(str){
	  var rtn = true;
	if(str==null || str==''){
		    rtn = false;
	    }
	return rtn;
  }

  this.toString = function(){
    var sql = "";
    for(var i=0;i<this.array.length;i++){
	if(i==0){
	  sql+=this.array[i];
	}
	else{
		  sql+=" and "+this.array[i];
	}
    }
    return sql;
  }

  this.isEmpty = function(){
	  var rtn = true;
	      if(this.array.length>0){
		      rtn = false;
	}
	return rtn;
  }
}


//条件参数
function QueryCondition(){
  this.array = new Array();

  this.addNumber = function(key,value){
    if(!this.checkStringNotNull(key)){
	    return;
    }
    if(!this.checkNumberNotNull(value)){
	    return;
    }
    this.array.push(key+"="+value);
  }
  this.addString = function(key,value){
    if(!this.checkStringNotNull(key)){
	    return;
    }
    if(!this.checkStringNotNull(value)){
	    return;
    }
    this.array.push(key+"="+value+"");
  }
  this.checkStringNotNull = function(str){
	var rtn = true;
	if(str==null || str=='' || str.trim().length==0){
		    rtn = false;
	}
	return rtn;
  }
  this.checkNumberNotNull = function(str){
	  var rtn = true;
	if(str==null || str==''){
		    rtn = false;
	    }
	return rtn;
  }

  this.toString = function(){
    var sql = "";
    for(var i=0;i<this.array.length;i++){
	if(i==0){
	  sql+=this.array[i];
	}
	else{
		  sql+="&"+this.array[i];
	}
    }
    return sql;
  }

  this.isEmpty = function(){
	  var rtn = true;
	      if(this.array.length>0){
		      rtn = false;
	}
	return rtn;
  }
}
