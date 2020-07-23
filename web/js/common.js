
// JavaScript Document
$(function(){

	$('input[class^=input_button]').bind('mouseover',function(){
		$(this).css('color','#ffbb00');	
	});
	$('input[class^=input_button]').bind('mouseout',function(){
		$(this).css('color','#2b2b2b');	
	});

	$('#top_icon img').bind('mouseover',function(){
		var src = $(this).attr('src').replace('1','2');
		$(this).attr('src',src);	
	});
	$('#top_icon img').bind('mouseout',function(){
		var src = $(this).attr('src').replace('2','1');
		$(this).attr('src',src);	
	});

	
	
	/*Tab*/
/*	$('table td[class^=table_tab_title]').each(function(){
		var className = $(this).attr('class');
		if('table_tab_title_middle'==className||'table_tab_title_middle1'==className){
			$(this).next().css('background-image','url(images/tab/tab05.png)');
		}else if('table_tab_title_last'==className){
			$(this).next().css('background-image','url(images/tab/tab04.png)');
		}
	});*/
	tabInit('');
	$('table td[class^=table_tab_title]').bind('click',function(){
		var url = $(this).attr('url');
		location.href=url;
	});
	
	$('.a_css').each(function() {
		$(this).on({
			"mouseover": function() {
				$(this).css({'color': '#0000ff'});
			},
			"mouseout": function() {
				$(this).css({'color': '#0061CE'});
			}
		});
	});

	/**
	 * table 表格样式
	 */
	// $("tbody tr:odd").addClass("tbodyodd");
	// $("tbody tr:even").addClass("tbodyeven");

	//输入框的重置按钮
	$("#reset").on("click",function(){
		$(":input").not(":radio,:checkbox,:submit,:reset,:button,:hidden").val('').removeAttr("checked").removeAttr("selected");
		//清除多选下拉
		$(".ms-choice span").html('');
		$("li[class='selected']").removeClass();
		$("input[data-name='selectItem']").removeAttr("checked");
	});


});


function isNull(param) {
	if(param=="null"||param==null||param==undefined||param==""||param.length==0){
		return true;
	}
	return false;
}

//检查身份证号
function isIdCardNo(card){
	// 身份证号码为15位或者18位，15位时全为数字，18位前17位为数字，最后一位是校验位，可能为数字或字符X
	var reg = /(^\d{15}$)|(^\d{17}(\d|X)$)/;
	if (reg.test(card) === false) {
		return false;
	}
	return true;
}


function tabInit(relative){
	$('table td[class^=table_tab_title]').each(function(){
		var className = $(this).attr('class');
		$(this).css('background-image','url('+relative+'images/tab/tab_bg02.png)');
		$(this).css('color','#153f60');
		if('table_tab_title_middle'==className||'table_tab_title_middle1'==className){
			$(this).next().css('background-image','url('+relative+'images/tab/tab05.png)');
		}else if('table_tab_title_last'==className){
			$(this).next().css('background-image','url('+relative+'images/tab/tab04.png)');
		}else if('table_tab_title_first'==className){
			$(this).next().css('background-image','url('+relative+'images/tab/tab05.png)');
			$(this).prev().css('background-image','url('+relative+'images/tab/tab_corner2.png)');
		}
		var sel=$(this).attr('sel');
		if('sel'==sel){
			$(this).css('background-image','url('+relative+'images/tab/tab_bg01.png)');
			$(this).css('color','#ffffff');
			if('table_tab_title_middle'==className||'table_tab_title_middle1'==className){
				$(this).next().css('background-image','url('+relative+'images/tab/tab01.png)');
				$(this).prev().css('background-image','url('+relative+'images/tab/tab02.png)');
			} else if('table_tab_title_last'==className){
				$(this).next().css('background-image','url('+relative+'images/tab/tab03.png)');
				$(this).prev().css('background-image','url('+relative+'images/tab/tab02.png)');
			} else if('table_tab_title_first'==className){
				$(this).next().css('background-image','url('+relative+'images/tab/tab01.png)');
				$(this).prev().css('background-image','url('+relative+'images/tab/tab_corner.png)');
			}			
		}
	});
}

//非空验证
function ok_or_error(l) {
        var content = $(l).val();
        if (content != "") {
            $(l).next().next().css("display", "none");
            $(l).next().css("display", "inline-block");
        }
        if (content == "") {
            $(l).next().next().css("display", "inline-block");
            $(l).next().css("display", "none");
            return false;
        }
}

/**
 * 改变导航栏
 */
function changeDaoHang() {
	if(arguments.length == 0) {
		$("#page_daohang",window.parent.frames["daohangframe"].document).html("");
		$("#whiteDiv",window.parent.frames["daohangframe"].document).show();
		return;
	}
	if(window.parent.frames["daohangframe"]!=undefined){
		$("#whiteDiv",window.parent.frames["daohangframe"].document).hide();
	}

	var spanHtml = '<span>您所在的位置</span>';
	for(var i=0;i<arguments.length;i++) {
		spanHtml += '<span>&gt;&gt;</span><span>' + arguments[i] + '</span>';

	}
	if(window.parent.frames["daohangframe"]!=undefined){
		$("#page_daohang",window.parent.frames["daohangframe"].document).html(spanHtml);
	}
}

/**
 * 改变导航栏url 二级菜单
 * "text`url,test`url"
 */
function changeDaoHangFrame() {
	if(arguments.length == 0) {
		$("#page_daohang",window.parent.frames["daohangframe"].document).html("");
		$("#whiteDiv",window.parent.frames["daohangframe"].document).show();
		return;
	}
	if(window.parent.frames["daohangframe"]!=undefined){
		$("#whiteDiv",window.parent.frames["daohangframe"].document).hide();
	}

	var spanHtml = '<span>您所在的位置</span>';
	var param = "";
	arguments = arguments[0].split(",");
	for(var i=0;i<arguments.length;i++) {
		if(arguments[i]==""){
			break;
		}
		var text =  arguments[i].split('`')[0];
		var url =  arguments[i].split('`')[1];
		// spanHtml += '<span>&gt;&gt;</span><span>' + arguments[i] + '</span>';
		for(var j = 0;j<=i;j++){
			param += arguments[j]+",";
		}
		spanHtml += Objects.format("<span>&gt;&gt;</span><a class='span' onclick=\"HrefClick('{0}');changeCallBack('{1}');\" style='cursor: pointer;'>{2}</a>",url,param,text);
		param = "";
	}
	if(window.parent.frames["daohangframe"]!=undefined){
		$("#page_daohang",window.parent.frames["daohangframe"].document).html(spanHtml);
	}
}

function Map(){//初始化map_
	var map_=new Object();
	//属性加个特殊字符，以区别方法名，统一加下划线_
	map_.put=function(key,value){    map_[key]=value;};
	map_.get=function(key){    return map_[key];};
	map_.remove=function(key){    delete map_[key];};
	map_.size = function () {
		return map_.value.length;
	};
	map_.keyset=function(){
		var ret="";
		for(var p in map_){
			if(typeof p =='string' && p.substring(p.length-1)=="_"){
				ret+=",";
				ret+=p;
			}
		}
		if(ret==""){
			return ret.split(","); //empty array
		}else{
			return ret.substring(1).split(",");
		}
	};
	map_.emptyAll = function () {
		for (var p in map_){
			var item = map_[p];
			if (typeof item == "function"){
				continue;
			}
			delete map_[p];
		}
	};
	map_.contain=function (key) {
		if (map_[key] == null){
			return false;
		}else {
			return true;
		}
	};
	return map_;
}

/**
 * js实现list
 *
 */
function List() {
	this.value = [];

	/* 添加 */
	this.add = function(obj) {
		return this.value.push(obj);
	};

	/* 大小 */
	this.size = function() {
		return this.value.length;
	};

	/* 返回指定索引的值 */
	this.get = function(index) {
		return this.value[index];
	};

	/* 删除指定索引的值 */
	this.remove = function(index) {
		this.value.splice(index,1);
		return this.value;
	};

	/* 删除全部值 */
	this.removeAll = function() {
		return this.value = [];
	};

	/* 是否包含某个对象 */
	this.constains = function(obj) {
		for ( var i in this.value) {
			if (obj == this.value[i]) {
				return true;
			} else {
				continue;
			}
		}
		return false;
	};

	/* 是否包含某个对象 */
	this.getAll = function() {
		var allInfos = '';
		for ( var i in this.value) {
			if(i != (value.length-1)){
				allInfos += this.value[i]+",";
			}else{
				allInfos += this.value[i];
			}
		}
		alert(allInfos);
		return allInfos += this.value[i]+",";;
	};

}

function getLocalTime(nS) {
	var date = new Date(nS);
	Y = date.getFullYear() + '-';
	M = (date.getMonth()+1 < 10 ? '0'+(date.getMonth()+1) : date.getMonth()+1) + '-';
	D = date.getDate() + ' ';
	h = date.getHours() + ':';
	m = date.getMinutes()<10?('0'+date.getMinutes()):date.getMinutes();
	return Y+M+D+h+m;

}

function getLocalTimeYYYYMMDD(nS) {
	if(isNull(nS)){
		return "";
	}
	var date = new Date(nS);
	Y = date.getFullYear() + '-';
	M = (date.getMonth()+1 < 10 ? '0'+(date.getMonth()+1) : date.getMonth()+1) + '-';
	D = date.getDate();
	if(D < 10){
		D = "0"+D;
	}
	//D = date.getDate() + ' ';
	//h = date.getHours() + ':';
	//m = date.getMinutes()<10?('0'+date.getMinutes()):date.getMinutes();
	return Y+M+D;

}
//获取当前周周一日期
function week(){
	var now = new Date();
	// var nowDayOfWeek=now.getDay();
	// var nowDay = now.getDate();
	// var nowMonth = now.getMonth()+1;
	// var nowYear = now.getFullYear();
	var day = now.getDay() || 7;
	var weekStartDate = new Date(now.getFullYear(),now.getMonth(),now.getDate() + 1 - day);
	return getLocalTimeYYYYMMDD(weekStartDate);

}


//手机号码格式验证
function validatorIsTelephone(phone) {
        // if(!/^1[3456789]\d{9}$/.test(phone)){
         //    return  false;
        // }else{
		// 	return true;
		// }
	if(!/^\d{11}$/.test(phone)){
            return  false;
        }else{
			return true;
		}
}
//字符转数值 保留两位小数
function mathFloorByString(string) {
	var num = Number(string);
	return mathFloor(num);
}
//数值 保留两位小数
function mathFloor(num) {
	return Math.floor(num*100)/100;
}
function tableAddClass(){
		/**
		 * table 表格样式
		 */
//		$("tbody tr:odd").addClass("tbodyodd");
//		$("tr:odd").addClass("tbodyodd");
//		$("tr:even").addClass("tbodyeven");
    $("table tr").removeClass("tbodyodd");
    $("table tr").removeClass("tbodyeven");
		// $("table .tbody tr:odd:not(:has(th))").addClass("tbodyodd");
		// $("table .tbody tr:even:not(:has(th))").addClass("tbodyeven");

		// $("table:has(tbody) tr:odd").addClass("tbodyodd");
		// $("table:has(tbody) tr:even").addClass("tbodyeven");
    // $("table tr").each(function (i) {
    //     this.style.backgroundColor=['#ccc','#fff'][i%2]
    // })
    // $(".tableStyle table tr:odd").addClass("tbodyodd");
    // $(".tableStyle table tr:even").addClass("tbodyeven");
    $("body").find("table").each(function (index,element) {
        $(element).find("tr").each(function (idx,el) {
            if(idx%2==1) {
                $(this).addClass("tbodyodd");
            }
            else {
                $(this).addClass("tbodyeven");
            }
        })

    })
}

//乘法
function accMul(arg1,arg2) {
	var m=0,s1=arg1.toString(),s2=arg2.toString();
	try{m+=s1.split(".")[1].length}catch (e){}
	try{m+=s2.split(".")[1].length}catch (e){}
	return Number(s1.replace(".",""))*Number(s2.replace(".",""))/Math.pow(10,m);
}
