/**
 * @name 列表操作(排序，修改值，状态切换，批量操作)
 * @description Based on jQuery 1.4+
 * @author Horace.zhang
 * @url http://www.pinphp.com
 */
;(function($) {
	$.fn.listTable = function(options) {
		var self = this,
			local_url = window.location.search,
		    //local_url = window.parent.frames["rightframe"].document.location.search;
		    // local_url =window.parent.frames["rightframe"] location.search,
			/*local_url=pathName + "/illegal?currentPage=" + $('#currentPage').val()
			+ "&pagesize=" + $("#nmData").val() + "&"
			+ $("#add1").serialize(),*/
		
			settings = {
				url: $(self).attr('data-acturi')
			}
		//alert(local_url);
		//如果options不为空,替换默认设置
		if(options) {
			$.extend(settings, options);
		}
		/*
		 * 遍历参数列表
		 *    &sort="s"
		 *    &order="o"
		 *    &test="t"
		 * 
		 * 
		 */
		var params  = local_url.substr(1).split('&');
		var sort,order;
		for(var i=0; i < params.length; i++) {
			var param = params[i];
			var arr   = param.split('=');
			if(arr[0] == 'sort') {
				sort = arr[1];
			}
			if(arr[0] == 'order') {
				order = arr[1];
			}
		}
		//点击行选中本行
/*
		$('tbody tr', $(self)).live('click', function(){
			var tr = this;
			if($('.J_checkitem', $(this)).attr('checked')){
				$('.J_checkitem', $(this)).attr('checked', false);
				$(tr).removeClass('on');
			}else{
				$('.J_checkitem', $(this)).attr('checked', true);
				$(tr).addClass('on');
			}
		});

		$('.J_checkitem', $(self)).live('click', function(){
			$(this).attr('checked', this.checked ? false : true);
		});
*/
		//全选反选
		$('.J_checkall').live('click', function(){
			$('.J_checkitem').attr('checked', this.checked);
			$('.J_checkall').attr('checked', this.checked);
    	});
    	//历史排序  
		$('span[data-tdtype="order_by"]', $(self)).each(function() {
			if($(this).attr('data-field') == sort) {
				if(order == 'asc') {
					$(this).attr('data-order', 'asc');
					$(this).addClass("sort_asc");
				} else if (order == 'desc') {
					$(this).attr('data-order', 'desc');
					$(this).addClass("sort_desc");
				}
			}
		}).addClass('sort_th');
		//排序  span标签需要传递的数据用data-表示
		$('span[data-tdtype="order_by"]', $(self)).live('click', function() {
			//alert(local_url);
			var s_name = $(this).attr('data-field'),
				s_order  = $(this).attr('data-order'),
				sort_url = (local_url.indexOf('?') < 0) ? '?' : '';
		
			   /* console.debug(local_url);
			    console.debug(sort_url);*/
				sort_url += '&sort=' + s_name + '&order=' + (s_order =='asc' ? 'desc' : 'asc');
				local_url = local_url.replace(/&sort=(.+?)&order=(.+?)$/, '');
				if(local_url.indexOf("&sort")>0){
					local_url=local_url.substring(0,local_url.indexOf("&sort"));
				}
				//alert("local_url + sort_url"+local_url + sort_url);
			    location.href = local_url + sort_url;
			    return false;
		});
		//修改
		$('span[data-tdtype="edit"]', $(self)).live('click', function() {
			var s_val   = $(this).text(),//值
			s_name  = $(this).attr('data-field'),//字段  
			s_id    = $(this).attr('data-id'),//id
			width   = $(this).width();//宽度
			$('<input type="text" class="lt_input_text" value="'+s_val+'" />').width(width).focusout(function(){
				$(this).prev('span').show().text($(this).val());
				if($(this).val() != s_val) {
					$.getJSON(settings.url, {id:s_id, field:s_name, val:$(this).val()}, function(result){
						//如果出错，出错信息，原值
						if(result.status == 0) {
							$.pinphp.tip({content:result.msg, icon:'error'});
							$('span[data-field="'+s_name+'"][data-id="'+s_id+'"]').text(s_val);
							return;
						}
					});
				}
				$(this).remove();
			//插入input标签
			}).insertAfter($(this)).focus().select();
			$(this).hide();
			return false;
		});
		//切换
		$('img[data-tdtype="toggle"]', $(self)).live('click', function() {
			alert("toggle test");
			var img    = this,
				s_val  = ($(img).attr('data-value')),
				s_name = $(img).attr('data-field'),
				s_id   = $(img).attr('data-id'),
				s_src  = $(img).attr('src');
			$.getJSON(settings.url, {id:s_id, field:s_name, val:s_val}, function(result){
				if(result.status == 1) {
					if(s_src.indexOf('disabled')>-1) {
						$(img).attr({'src':s_src.replace('disabled','enabled'),'data-value':s_val});
					} else {
						$(img).attr({'src':s_src.replace('enabled','disabled'),'data-value':s_val});
					}
				}
			});
			return false;
		});
		

		
		//批量操作
		$('input[data-tdtype="batch_action"]').live('click', function() {

			//选中的元素
			var btn = this;
			//如果没有选中
			if($('.J_checkitem:checked').length == 0){
                $.pinphp.tip({content:"请选择要操作的项目", icon:'alert'});
				return false;
            }
			//组装ids参数
			var ids = '';
			$('.J_checkitem:checked').each(function(){
				ids += $(this).val() + ',';
			});
			ids = ids.substr(0, (ids.length - 1));
			//获取url
			var uri = $(btn).attr('data-uri') + '?' + $(btn).attr('data-name') + '=' + ids,
				msg = $(btn).attr('data-msg'),
				acttype = $(btn).attr('data-acttype'),
				//titile如果存在用，不存在，用默认的确认titile
				title = ($(btn).attr('data-title') != undefined) ? $(this).attr('data-title') :"确认信息";
			//如果msg不为空，对话框，为空，直接操作action();
			if(msg != undefined){
				$.dialog({
					id:'confirm',
					title:title,
					width:200,
					padding:'10px 20px',
					lock:true,
					content:msg,
					ok:function(){
						action();
					},
					cancel:function(){}
				});
			}else{
				action();
			}
			function action(){
				//判断act种类
				if(acttype == 'ajax_form'){
					var did = $(btn).attr('data-id'),
						dwidth = parseInt($(btn).attr('data-width')),
						dheight = parseInt($(btn).attr('data-height'));
					$.dialog({
						id:did,
						title:title,
						width:dwidth ? dwidth : 'auto',
						height:dheight ? dheight : 'auto',
						padding:'',
						lock:true,
						ok:function(){
							var info_form = this.dom.content.find('#info_form');
							if(info_form[0] != undefined){
								info_form.submit();
								return false;
							}
						},
						cancel:function(){}
					});
					$.getJSON(uri, function(result){
						if(result.status == 1){
							$.dialog.get(did).content(result.data);
						}
					});
				 }else if(acttype == 'ajax'){
					$.getJSON(uri, function(result){
						if(result.status == 1){
							$.pinphp.tip({content:result.msg});
							window.location.reload();
						}else{
							$.pinphp.tip({content:result.msg, icon:'error'});
						}
					});
				}else{
					location.href = uri;
				}
			}
		});
	};
})(jQuery);