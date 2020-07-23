 String.prototype.trim = function() {return this.replace(/^\s+|\s+$/g,"");}

function isNUM(id_str) {
	var value = $(id_str).val();
	var newPar = /^(\d.)+$/;
	var newPar1 = /^^[0-9]+(.[0-9]{1,2})?$/;
	if (!newPar.test(value)&&!newPar1.test(value)) {
		return false;
	} else {
		return true;
	}
}

function isNULL(id_str) {
	var value = $(id_str).val();
	if (value == null || value.trim() == "") {
		return true;
	} else {
		return false;
	}
}
function isEmpty(str) {
	var str = ""+str+"".trim();
	if (str == null || str.trim() == "") {
		return true;
	} else {
		return false;
	}
}

 /**
  * ie 兼容性 判断是否属于
  * @returns {boolean}
  */
 function isIE() {
	if(window.ActiveXObject || "ActiveXObject" in window){
		return true;
	}else {
		return false;
	}
}

function isIE11() {
	if((/Trident\/7\./).test(navigator.userAgent)){
		return true;
	}else {
		return false;
	}
}

//ie兼容性 remove
function objectRemove(e){
	if(isIE() || isIE11()){
		e.removeNode(true);
	}else{
		e.remove();
	}
}

//ie兼容 remove
 function objectRemoves(obj,inx){
	 // if(isIE() || isIE11()){
	 obj.remove(inx);
	 // return objectRemovekk(obj,inx);
	 // delete obj[inx];
	 // obj.remove(inx);
		//  var ss = obj;
	 // var ss = "";
	 // }else{
		//  obj.remove(inx);
	 // }
 }

 function objectRemovekk(obj,n) {
	 if(n<0){
		 return obj;
	 }
	 var map = new Map();
		 for (var p in obj){
		 var item = obj[p];
		 if (p==n){
			 continue;
		 }
			 map.put(p,item);
	 	}
	 	return map;
 }