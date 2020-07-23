$(function(){
	//输入框的重置按钮
	$("#reset").on("click",function(){
		$(":input").not(":radio,:checkbox,:submit,:reset,:button,:hidden").val('').removeAttr("checked").removeAttr("selected");
		$(":input[type=radio],:input[type=checkbox]").removeAttr("checked");
	});
	
	$("tr td:nth-child(n+2)").css("min-width", "60px");
});

//String.startsWith()方法重写，保证浏览器兼容
String.prototype.startsWith = function(str) {
	var reg = new RegExp('^'+str);
	return reg.test(this);
};
String.prototype.endsWith = function(str) {
	var reg = new RegExp(str+"$");
	return reg.test(this);
};

//转化为字符串方法
function objToString(obj) {
	switch(typeof obj) {
	case "string":
		return obj;
	case "object":
		if(obj instanceof Array) {
			var returns = "";
			for(var i=0;i<obj.length-1;i++) {
				returns += (arguments.callee(obj[i]) + "；");
			}
			return returns + (arguments.callee(obj[obj.length-1]));
		}else{
			var returns = [];
			for( var k in obj) {
				if(obj[k]){
					returns.push(k+"="+arguments.callee(obj[k]));
				}
			}
			return arguments.callee(returns);
		}
	default:
		return obj;	
	}
}
/**
 * " < > 替换为转义字符
*/
function replace(str) {
	str = str.replace(/"/g, '&quot');
	str = str.replace(/</g, '&lt');
	str = str.replace(/>/g, '&gt');
	return str;
}

function checkReg(obj, reg, message) {
	if(message==null) {
		message = "格式错误";
	}
	var label = '<label class="noempty">' + message + '</label>';
	if(!reg.test(obj.val())){
		obj.after(label);
		return true;
	} else {
		return false;
	}
}

//判断不为空方法
function checkNull() {
	for(var i=0;i<arguments.length;i++) {
		arguments[i].on({
			"blur":blurNull,
			"focus":focusNull
		});
	}
}
//输入框失去焦点时触发检查
function blurNull(){
	if($(this).next("label")) $(this).next("label").remove();
	if($(this).val()==null || $(this).val().length==0 || $.trim($(this).val()).length==0){
		var id = $(this).attr("id");
		var label = '<label for="'+id+'" class="noempty">必须填写</label>';
		$(this).after(label);
	};
};
//输入框获得焦点时检查
function focusNull(){
	$(this).next("label").remove();
	if($(this).val()!=null || $(this).val().length!=0 || $(this).val().trim().length!=0){
		$(this).next("label").remove();
	};
};
//提交时检查必填的输入框
function isNull() {
	var res = 0;
	for(var i=0;i<arguments.length;i++) {
		arguments[i].next("label").remove();
		
		if(arguments[i].val()==null || arguments[i].val().length==0 || arguments[i].val().replace(/(^\s*)|(\s*$)/g,"").length==0){
			var id = arguments[i].attr("id");
			var label = '<label for="'+id+'" class="noempty">必须填写</label>';
			arguments[i].after(label);
			res++;
		};
	}
	
	if(res) {
		return true;
	} else {
		return false;
	}
}

//Date对象添加方法，日期格式转换（format:例如'yyyy-MM-dd HH:mm:ss'）
Date.prototype.format = function(format) {
	var o = {
		'M+': this.getMonth() + 1,
		'd+': this.getDate(),
		'H+': this.getHours(),
		'm+': this.getMinutes(),
		's+': this.getSeconds(),
		'q+': Math.floor((this.getMonth()+3)/3),
		'S': this.getMilliseconds()
	};
	if(/(y+)/.test(format)) {
		format = format.replace(RegExp.$1, (this.getFullYear() + '').substr(4-RegExp.$1.length));
	}
	for(var k in o) {
		if(new RegExp('('+k+')').test(format)) {
			format = format.replace(RegExp.$1, RegExp.$1.length==1?o[k]:('00'+o[k]).substr((''+o[k]).length));
		}
	}
	return format;
};

//字符串trim()方法，用于支持IE8等（速度更快）
function trimStr(str){
	str = str.replace(/^\s/, '');
	for(var i=str.length-1;i>=0;i--) {
		if(/\S/.test(str.charAt(i))) {
			str = str.substring(0, i);
			break;
		}
	}
	return str;
}

/**
 * 格式化html文档字符串，只保留标签和内容，去除标签中指定的属性（任务详情查看，格式化html源码）;
 * 基于jQuery插件
 */
function formatHtml(html, unKeepAttrs) {
	//去除的属性列表
	if(!unKeepAttrs) unKeepAttrs = ['href', 'class', 'style', 'src','onClick', 'onDbClick',  'onMouseUp', 
	                                'onMouseOver', 'onMouseDown', 'onMouseMove', 'onMouseOut', 'onKeyPress', 
	                                'onKeyDown', 'onKeyUp', 'onBlur', 'onFocus', 'onReset'];
	if(html.indexOf('<body') >= 0 && html.indexOf('</body>')>0) {
		html = html.substring(html.indexOf('<body'), html.indexOf('</body>', html.indexOf('<body')+5)+7);
	}
	if(html.indexOf('<BODY') >= 0 && html.indexOf('</BODY>')>0) {
		html = html.substring(html.indexOf('<BODY'), html.indexOf('</BODY>', html.indexOf('<BODY')+5)+7);
	}
	var str = '';
	try {
		var jHtml = $(html);
		var jE = dealEle(jHtml, unKeepAttrs);
		jE.children().each(function() {
			str += $(this)[0].outerHTML;
		});
	} catch(error) {
		str = html;
	}
	
	return str;
}
/**
 * 递归处理节点
 * @param ele
 * @param unKeepAttrs
 * @returns
 */
function dealEle(ele, unKeepAttrs) {
	var jEles = ele.children();
	ele.removeClass();
	for(var i=0;i<unKeepAttrs.length;i++) {
		ele.removeAttr(unKeepAttrs[i]);
	}
	if(ele.attr('type') == 'submit' || ele.attr('type') == 'image') {
		ele.attr('type', 'button');
	}
	if(ele.attr('action')) {
		ele.attr('action', '#');
	}
	
	if(jEles && jEles.length>0) {
		jEles.each(function() {
			var cEle = $(this);
			dealEle(cEle, unKeepAttrs);
		});
	}
	return ele;
}
/**
 * 格式化html文档字符串，只保留标签和内容，及指定的属性
 * （废弃使用，留待观察）
 * @param html
 * @param attrs
 * @returns {String}
 */
function formatHtml2(html, attrs) {
	if(!attrs) attrs = ['type', 'id', "name", 'value', 'action', 'src', 'width', 'height'];

	var start = false;
	var blank = false;
	var isAttrStart = false;
	var isContainAttr = false;
	//是否双引号开始了
	var isDoubleQutoationMarks = false;
	//是否单引号开始了
	var isSingleQuotationMarks = false;
	//是否注释开始
	var startAnnotation = false
	var endAnnotation = false
	
	//html字符串
	var str = '';
	//标签
	var label = '';
	//属性
	var attr = '';
	
	var c = '';
	//去除注释符号'<!-- -->'
	var annotation = '';
	
	for(var i=0;i<html.length;i++) {
		c = html.charAt(i);
		if(c == '') {
			continue;
		}
		//判断注释开始结束
		annotation = '';
		if(c == '<') {
			annotation = c + html.charAt(i+1) + html.charAt(i+2) + html.charAt(i+3);
			if(annotation = '<!--') {
				startAnnotation = true;
				endAnnotation = false;
			}
		}
		if(c == '>') {
			annotation = html.charAt(i-2) + html.charAt(i-1) + c;
			if(annotation = '-->') {
				startAnnotation = false;
				endAnnotation = true;
			}
		}

		if(c == '\'') {
			isSingleQuotationMarks = isSingleQuotationMarks? false:true;
		}
		if(c == '\"') {
			isDoubleQutoationMarks = isDoubleQutoationMarks? false:true;
		}
		/*if(startAnnotation) {
			str += c;
			continue;
		}*/
		
		if(c == '<' && (!start)) {
			start = true;
			label = '';
			attr = '';
		} else if(c == '>' && start) {
			start = false;
			blank = false;
			isAttrStart = false;
			if(isContainAttr) {
				label += ' ';
				label += analyseAttr(attr);
			}
			isContainAttr = false;
			str += label;
		}
		if(start) {
			if(c == ' ' && !blank) {
				blank = true;
				attr = '';
			}
			if(blank) {
				if(c == ' ' && !isAttrStart) {
					continue;
				} else {
					isAttrStart = true;
				}
				if(isAttrStart) {
					if(c == '=') {
						if(arrayContains(attrs, attr)) {
							attr += c;
							isContainAttr = true;
						} else {
							isContainAttr = false;
							continue;
						}
					} else if(c == ' ') {
						if(isContainAttr) {
							label += ' ';
							label += analyseAttr(attr);
						}
						isAttrStart = false;
						attr = '';
					} else {
						attr += c;
					}
				}
				continue;
			} else {
				label += c;
			}
		} else {
			str += c;
		}
	}
	if(str.indexOf('<body') >= 0) str = str.substring(str.indexOf('<body'), str.indexOf('</body>', str.indexOf('<body')+5)+7);
	if(str.indexOf('<BODY') >= 0) str = str.substring(str.indexOf('<BODY'), str.indexOf('</BODY>', str.indexOf('<BODY')+5)+7);
	return str;
}
/**处理html字符串，注释掉script和form表单的action，任务结果，网页快照时处理，防止js和form跳转*/
function simplifyHtml(html) {
	var reg = new RegExp('action="', ['g']);
	html = html.replace(reg, 'replaceaction="')
	
	html = html.replace(new RegExp('<script', ['g']), '<!-- <script');
	html = html.replace(new RegExp('</script>', ['g']), '</script> -->');
	return html;
}
/**
 * 处理属性
 * @param attr
 */
function analyseAttr(attr) {
	//form表单的action属性设置为'#'，submit按钮替换为button
	if(attr.startsWith('action')) {
		attr = 'action="#"';
	} else if(attr.startsWith('type="submit"') || attr.startsWith('type=submit')) {
		attr = 'type="button"';
	}
	return attr;
}

function arrayContains(attrs, attr) {
	for(var i=0;i<attrs.length;i++) {
		if(attr.toLowerCase() == attrs[i].toLowerCase()) {
			return true;
		}
	}
	return false;
}













