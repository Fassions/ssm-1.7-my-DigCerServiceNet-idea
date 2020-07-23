/*
* pop-up_box-1.2.js
* 弹出可拽变大小的弹出层（支持IE8）
* 依赖资源：1、pop-up_box.css;2、相关图片./images/tanchu/*。
* 使用前提：在需要添加的页面上，添加一个如下格式的div:
* <div id="***" class="drag_layer" style="display:none;z-index:9999;"></div>
* 有多个div时，用z-index控制层次。
* 用法：
* 1、var box = new PopUpBox(id, width; height)，新建弹出框，指定div容器ID，指定宽度和高度(px)；
* 2、调用 box.setTitle(title)，设置弹出框的标题栏；
* 3、调用 box.setContent(content)，设置弹出框的内容。
*/

//容器对象
function PopUpBox(id, width, height) {
	this.id = id;
	if(width) {
		this.width = width;
	} else {
		this.width = 480;
	}
	if(height) {
		this.height = height;
	} else {
		this.height = 200;
	}
	
	this.oMsgbox=null;
	this.msgboxBody=null;

	this.g_orgTop=0;
	this.g_orgHeight=0;
	this.g_orgLeft=0;
	this.g_orgWidth=0;

	this.oContent=null;
	this.oDragBoth=null;
	this.oDragL=null;
	this.oDragT=null;
	this.oDragR=null;
	this.oDragB=null;
	this.init();
	
	//打开回调函数
	this.openFunction;
	//关闭回调函数
	this.closeFunction;
};

PopUpBox.prototype.setTitle = function(title) {
	document.getElementById(this.id+'_title').innerHTML=title;
}
PopUpBox.prototype.setContent = function(content) {
	document.getElementById(this.id+'_body').innerHTML=content;
}
PopUpBox.prototype.close = function() {
	this.oMsgbox.style.display='none';
	this.oBtnRevert.style.display='none';
	this.oBtnMax.style.display='inline-block';
	if(this.closeFunction) this.closeFunction();
}
PopUpBox.prototype.open = function() {
	if(this.oMsgbox.style.display == 'block') {
		return;
	}
	this.oMsgbox.style.display='block';
	this.oBtnRevert.style.display='inline-block';
	this.oBtnMax.style.display='none';
	if(this.openFunction) this.openFunction();
}
PopUpBox.prototype.ifOpen = function() {
	if(this.oMsgbox.style.display == 'block') {
		return true;
	} else {
		return false;
	}
}

PopUpBox.prototype.init = function() {
	var obj = this;
	
	var id = this.id;
	this.oMsgbox=document.getElementById(id);
	//容器初始化
	this.oMsgbox.innerHTML=
		'<h2>'+
			'<strong title="这里是标题" id="'+id+'_title">标题</strong>'+
			'<div class="top_icon">'+
				'<a href="#" class="max" id="'+id+'_toMax" title="最大化"></a>'+
				'<a href="#" class="revert" id="'+id+'_toRevert" style="display:none" title="还原"></a>'+
				'<a href="#" class="close" id="'+id+'_toClose" title="关闭"></a>'+
			'</div>'+
		'</h2>'+
		'<div class="content" id="'+id+'_body">'+
		'</div>'+
		'<div class="drag" id="'+id+'_drag_d"></div>'+
		'<div class="bar_t" id="'+id+'_bar_t_d"></div>'+
		'<div class="bar_r" id="'+id+'_bar_r_d"></div>'+
		'<div class="bar_b" id="'+id+'_bar_b_d"></div>'+
		'<div class="bar_l" id="'+id+'_bar_l_d"></div>';
	
	this.msgboxBody=document.getElementById(id+'_body');
	this.oBtnClose=document.getElementById(id+'_toClose');
	this.oBtnMax=document.getElementById(id+'_toMax');
	this.oBtnRevert=document.getElementById(id+'_toRevert');
	
	this.oH2Title=this.oMsgbox.getElementsByTagName('h2')[0];
	this.aDiv=this.oMsgbox.getElementsByTagName('div');
	this.oDragBoth=document.getElementById(id+'_drag_d');
	this.oDragL=document.getElementById(id+'_bar_l_d')
	this.oDragT=document.getElementById(id+'_bar_t_d')
	this.oDragR=document.getElementById(id+'_bar_r_d')
	this.oDragB=document.getElementById(id+'_bar_b_d')
	
	var width = this.width;
	var height = this.height;
	
	var oMsgbox=this.oMsgbox;
	var msgboxBody=this.msgboxBody;

	var g_orgTop=this.g_orgTop;
	var g_orgHeight=this.g_orgHeight;
	var g_orgLeft=this.g_orgLeft;
	var g_orgWidth=this.g_orgWidth;

	var oContent=this.oContent;
	var oDragBoth=this.oDragBoth;
	var oDragL=this.oDragL;
	var oDragT=this.oDragT;
	var oDragR=this.oDragR;
	var oDragB=this.oDragB;
	
	document.getElementById(id).style.display='block';

	var oBtnClose=this.oBtnClose;
	var oBtnMax=this.oBtnMax;
	var oBtnRevert=this.oBtnRevert;
	var oH2Title=this.oH2Title;
	var aDiv=this.aDiv;
	
	var fnDrag=null;
	
	var i=0;

	//关闭按钮
	oBtnClose.onmousedown=function () {
		oMsgbox.style.display='none';
		oBtnRevert.style.display='none';
		oBtnMax.style.display='inline-block';
		//关闭回调函数
		if(obj.closeFunction) obj.closeFunction();
	};
	//最大化按钮
	oBtnMax.onmousedown=function() {
		//记录最大化之前的状态
		g_orgTop=oMsgbox.offsetTop;
		g_orgHeight=oMsgbox.offsetHeight;
		g_orgLeft=oMsgbox.offsetLeft;
		g_orgWidth=oMsgbox.offsetWidth;
		
		var sMargin = 5;
		var sWidth = document.documentElement.clientWidth;
		var sHeight = document.documentElement.clientHeight;
		var mHeight = sHeight-3*sMargin;
		var mWidth = sWidth-3*sMargin;
		oMsgbox.style.top=sMargin+'px';
		oMsgbox.style.left=sMargin+'px';
		oMsgbox.style.height=mHeight+'px';
		oMsgbox.style.width=mWidth+'px';

		oDragL.style.height=mHeight+10+'px';
		oDragR.style.height=mHeight+10+'px';
		oDragT.style.width=mWidth+10+'px';
		oDragB.style.width=mWidth+10+'px';

		oDragBoth.style.top=mHeight-6+'px';
		
		//msgboxBody大小设置
		setMsgboxBodySize(obj);
		
		oBtnMax.style.display='none';
		oBtnRevert.style.display='inline-block';
	};
	//还原按钮
	oBtnRevert.onmousedown=function() {
		oMsgbox.style.top=g_orgTop+'px';
		oMsgbox.style.left=g_orgLeft+'px';
		oMsgbox.style.height=g_orgHeight+'px';
		oMsgbox.style.width=g_orgWidth+'px';

		oDragL.style.height=g_orgHeight+10+'px';
		oDragR.style.height=g_orgHeight+10+'px';
		oDragT.style.width=g_orgWidth+10+'px';
		oDragB.style.width=g_orgWidth+10+'px';

		oDragBoth.style.top=g_orgHeight-6+'px';
		
		setMsgboxBodySize(obj);
		
		oBtnRevert.style.display='none';
		oBtnMax.style.display='inline-block';
	};
	
	oMsgbox.style.width=width+'px';
	oMsgbox.style.height=height+'px';
	
	var initTop = (document.body.scrollLeft||document.documentElement.scrollLeft)+(document.documentElement.clientWidth-width)/2;
	var initLeft = (document.body.scrollTop||document.documentElement.scrollTop)+(document.documentElement.clientHeight-height)/2;
	if(initTop>0){
		oMsgbox.style.left=initTop+'px';
	} else {
		oMsgbox.style.left=0+'px';
	}
	if(initLeft>0){
		oMsgbox.style.top=initLeft+'px';
	} else {
		oMsgbox.style.top=0+'px';
	}
	setMsgboxBodySize(obj);
	
	//设置四边及右下拖拽图标位置
	oDragL.style.height=height+10+'px';
	oDragR.style.height=height+10+'px';
	oDragT.style.width=width+10+'px';
	oDragB.style.width=width+10+'px';
	oDragBoth.style.top=height-6+'px';
	//拖拽标头区域
	new PerfectDraging(
		obj,
		oH2Title,
		function (obje) {
			return {x:obje.oMsgbox.offsetLeft, y:obje.oMsgbox.offsetTop};
		},
		function (obje,x, y) {
			var iSTop=document.body.scrollTop || document.documentElement.scrollTop;
			
			if(x<0){
				x=0;
			}else if(x+obje.oMsgbox.offsetWidth>document.documentElement.clientWidth){
				x=document.documentElement.clientWidth-obje.oMsgbox.offsetWidth;
			}

			if(y<iSTop){
				y=iSTop;
			}else if(y+obje.oMsgbox.offsetHeight>document.documentElement.clientHeight+iSTop){
				y=document.documentElement.clientHeight-obje.oMsgbox.offsetHeight+iSTop;
			}
			obje.oMsgbox.style.left=x+'px';
			obje.oMsgbox.style.top=y+'px';
		}
	);
	//四周拖拽边区域
	for(i=0;i<aDiv.length;i++){
		fnDrag=null;
		switch(aDiv[i].className)
		{
		case 'drag':
		fnDrag=doBothDrag;
		break;
		case 'bar_t':
		fnDrag=doTDrag;
		break;
		case 'bar_r':
		fnDrag=doRDrag;
		break;
		case 'bar_b':
		fnDrag=doBDrag;
		break;
		case 'bar_l':
		fnDrag=doLDrag;
		break;
		case 'content':
		oContent=aDiv[i];
		break;
		}

		if(!fnDrag){
			continue;
		}

		new PerfectDraging (
			obj,
			aDiv[i],
			function (obje)
			{
				obje.g_orgTop=obje.oMsgbox.offsetTop;
				obje.g_orgHeight=obje.oMsgbox.offsetHeight;
				obje.g_orgLeft=obje.oMsgbox.offsetLeft;
				obje.g_orgWidth=obje.oMsgbox.offsetWidth;
				return {x:obje.oMsgbox.offsetWidth, y:obje.oMsgbox.offsetHeight};
			},
			fnDrag
		);
	}
	//打开回调函数
	if(obj.openFunction) obj.openFunction();
}

function doBothDrag(obj, x, y){
	if(x<110){
		x=110;
	}

	obj.oMsgbox.style.width=x-8+'px';
	obj.oDragT.style.width=x+'px';
	obj.oDragB.style.width=x+'px';

	if(y<35){
		y=35;
	}

	obj.oMsgbox.style.height=y-8+'px';
	obj.oDragL.style.height=y+'px';
	obj.oDragR.style.height=y+'px';
	obj.oDragBoth.style.top=y-16+'px';
	
	setMsgboxBodySize(obj);
}

function doTDrag(obj,x, y){
	var h=2*obj.g_orgHeight-y;
	if(h<35){
		h=35;
	}

	obj.oMsgbox.style.top=obj.g_orgTop+obj.g_orgHeight-h+'px';
	obj.oMsgbox.style.height=h-8+'px';

	obj.oDragL.style.height=h+2+'px';
	obj.oDragR.style.height=h+2+'px';

	obj.oDragBoth.style.top=h-16+'px';
	
	setMsgboxBodySize(obj);
}

function doRDrag(obj,x, y){
	if(x<110){
		x=110;
	}
	obj.oMsgbox.style.width=x-8+'px';

	obj.oDragT.style.width=x+2+'px';
	obj.oDragB.style.width=x+2+'px';
	
	setMsgboxBodySize(obj);
}

function doBDrag(obj,x, y){
	if(y<35){
		y=35;
	}
	obj.oMsgbox.style.height=y-8+'px';

	obj.oDragL.style.height=y+2+'px';
	obj.oDragR.style.height=y+2+'px';

	obj.oDragBoth.style.top=y-16+'px';
	
	setMsgboxBodySize(obj);
}

function doLDrag(obj,x, y){
	var w=2*obj.g_orgWidth-x;
	if(w<110){
		w=110;
	}
	obj.oMsgbox.style.left=obj.g_orgLeft+obj.g_orgWidth-w+'px';
	obj.oMsgbox.style.width=w-8+'px';

	obj.oDragT.style.width=w+2+'px';
	obj.oDragB.style.width=w+2+'px';
	
	setMsgboxBodySize(obj);
}

function PerfectDraging(containElement, oElement, fnGetPos, fnOnDrag){
	var obj=this;

	this.containElement = containElement;
	this.oElement=oElement;
	this.fnGetPos=fnGetPos;
	this.fnOnDrag=fnOnDrag;
	this.__oStartOffset__={x:0, y:0};

	this.fnOnMouseUp=function (ev){
		obj.stopDrag(window.event || ev);
	};

	this.fnOnMouseMove=function (ev){
		obj.doDrag(window.event || ev);
	};

	this.oElement.onmousedown=function (ev){
		obj.startDrag(window.event || ev);
		return false;
	};
}

PerfectDraging.prototype.startDrag=function (oEvent){
	var oPos=this.fnGetPos(this.containElement);
	var x=oEvent.clientX;
	var y=oEvent.clientY;
	this.__oStartOffset__.x=x-oPos.x;
	this.__oStartOffset__.y=y-oPos.y;

	if(this.oElement.setCapture){
		this.oElement.setCapture();

		this.oElement.onmouseup=this.fnOnMouseUp;
		this.oElement.onmousemove=this.fnOnMouseMove;
	}else{
		document.addEventListener("mouseup", this.fnOnMouseUp, true);
		document.addEventListener("mousemove", this.fnOnMouseMove, true);

		window.captureEvents(Event.MOUSEMOVE | Event.MOUSEUP);
	}
};

PerfectDraging.prototype.stopDrag=function (oEvent){
	if(this.oElement.releaseCapture){
		this.oElement.releaseCapture();

		this.oElement.onmouseup=null;
		this.oElement.onmousemove=null;
	}else{
		document.removeEventListener("mouseup", this.fnOnMouseUp, true);
		document.removeEventListener("mousemove", this.fnOnMouseMove, true);

		window.releaseEvents(Event.MOUSE_MOVE | Event.MOUSE_UP);
	}
};

PerfectDraging.prototype.doDrag=function (oEvent){
	var x=oEvent.clientX;
	var y=oEvent.clientY;
	this.fnOnDrag(this.containElement, x-this.__oStartOffset__.x, y-this.__oStartOffset__.y);
};

function setMsgboxBodySize(obj) {
	obj.msgboxBody.style.width=obj.oMsgbox.offsetWidth-6+'px';
	obj.msgboxBody.style.height=obj.oMsgbox.offsetHeight-40+'px';
}