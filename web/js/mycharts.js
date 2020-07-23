/**
*绘制饼图
*renderTo  		载入图的容器名
*title_text  	图表标题
*url 			访问后台的地址
*/
function pie(renderTo,title_text,url){
	var options = {
	//配置chart选项
	chart: {
		renderTo: renderTo,  							//容器名，和body部分的div id要一致
		type: 'pie'         							//图表类型，这里选择饼图
	},
	//导出及打印选项 
	exporting:{
		enabled:true,
		url:'/CMAS/chart/down',
		filename:'chart'
	},
	//图表标题选项
	title: {
		text: title_text								//标题文本内容
	},
	//数据点提示框选项
	tooltip: {
		headerFormat: '<b>{point.key}</b><br />',
		pointFormat: '{series.name}:<b>{point.y}%</b>'
	},
	//数据点选项
	plotOptions: {
		pie: {
			//animation: false,  						//动画效果 默认true
			allowPointSelect: true,
			cursor: 'pointer',
			showInLegend: true,							//是否显示图例
			dataLabels: {
				enabled: true,							//是否启用数据标签
				//color: '#000000',						//字体颜色
				//connectorColor: '#000000',			//连接线颜色
				connectorWidth:1,						//连接线宽度
				distance:5,								//数据标签到饼图边缘的距离
				//softConnector:false,					//是否把连接线作为一个软弧线或者锐利的线来渲染
				//y:20,									//相对于点的y偏移量
				//rotation:360,							//文本旋转角度
				formatter: function() {
					return this.point.y +' %';
					//return this.point.name +'：'+ this.point.y +' %';
				}
			}
		}
	},
	series: [{
		type: 'pie',
		name: '比例'
	}]
	}
	var datas = [];
	$.ajax({
		type:"GET",										//使用GET方法访问后台
		dataType:"json",								//返回json格式的数据
		url:url,										//访问后台的地址
		cache: false,									//缓存页面
		async: true,									//关闭同步请求，异步请求
		success:function(msg){							//msg为返回的数据，在这里做数据绑定
			$.each(msg,function(i,n){
				datas.push([n[0],n[1]]);
			});
			options.series[0].data = datas;
			$('#'+renderTo).highcharts(options);
		}
	});
}

/**
*绘制折线图
*renderTo  		载入图的容器名
*title_text  	图表标题
*yAxis_text 	y轴标题
*yAxis_unit 	提示框数据单位
*url 			访问后台的地址
*/
function line(renderTo,title_text,yAxis_text,yAxis_unit,url){
	var options = {
	//配置chart选项
	chart: {
		renderTo: renderTo,  							//容器名，和body部分的div id要一致
		type: 'line'         							//图表类型，这里选择折线图
	},
	//导出及打印选项 
	exporting:{
		enabled:true,
		url:'/CMAS/chart/down',
		filename:'chart'
	},
	//配置标题
	title: {
		text: title_text
	},
	//配置x轴
	xAxis: {},
	// 配置y轴
	yAxis: {
		title: {
			text: yAxis_text
		},
		labels: {
			formatter: function() {
				return this.value +' '+yAxis_unit
			}
		}
	},
	//配置数据点提示框
	tooltip: {
		headerFormat: '<b>{point.key}</b><br />',
		valueSuffix: yAxis_unit
	},
	series:[]
	}
				
	$.ajax({
		type:"GET",										//使用GET方法访问后台
		dataType:"json",								//返回json格式的数据
		url:url,										//访问后台的地址
		cache: false,									//缓存页面
		async: true,									//关闭同步请求，异步请求
		beforeSend:function(){$("#load").html("<font color='red'>Loading...</font>");},
		complete:function(){$("#load").html("");},
		success:function(msg){							//msg为返回的数据，在这里做数据绑定
			var times = [];
			var name;
			$.each(msg,function(i,n){
				var datas = [];
				$.each(n,function(j,m){
					if(j == 0){
						name = m[1];
					}
					datas.push([m[2]]);//或者datas.push([m[0],m[2]]);
					if(i==0){
						times.push([m[0]]);
					}	
				});
				options.series.push({name: name,data: datas});
			});
			options.xAxis.categories = times;
			$('#'+renderTo).highcharts(options);
		}
	});
}
/**
*绘制折线图
*renderTo  					载入图的容器名
*rotation  					是否倾斜
*dataLabels_enabledurl 		是否启用数据标签
*step  						x轴标签的相隔显示的步长
*url 						访问后台的地址
*/
function line2(renderTo,rotation,dataLabels_enabled,step,url){
	var options = {
	//配置chart选项
	chart: {
		renderTo: renderTo,  						//容器名，和body部分的div id要一致
		type: 'line'         						//图表类型，这里选择折线图
	},
	//导出及打印选项 
	exporting:{
		enabled:true,
		url:'/CMAS/chart/down',
		filename:'chart'
	},
	//配置标题
	title: {},
	//配置x轴
	xAxis: {
		labels: {									//设置横轴坐标的显示样式   
			rotation: rotation,						//倾斜度 
			step: step								//标签的相隔显示的步长 默认1
		}
	},
	// 配置y轴
	yAxis: {
		title: {
			text: ''
		},
		labels: {
			formatter: function() {
				return this.value;
			}
		}
	},
	//数据点选项
	plotOptions: {
		line: {
			animation: true,  						//动画效果
			//allowPointSelect: true,
			cursor: 'pointer',
			showInLegend: true,						//是否显示图例
			dataLabels: {
				enabled: dataLabels_enabled,		//是否启用数据标签
				formatter: function() {
					return this.point.y;
					//return this.point.name +'：'+ this.point.y +' %';
				}
			}
		}
	},
	//配置数据点提示框
	tooltip: {
		headerFormat: '<b>{point.key}</b><br />'
	},
	series:[]
	}
				
	$.ajax({
		type:"GET",									//使用GET方法访问后台
		dataType:"json",							//返回json格式的数据
		url:url,									//访问后台的地址
		cache: false,								//缓存页面
		async: true,								//关闭同步请求，异步请求
		beforeSend:function(){$("#load").html("<font color='red'>Loading...</font>");},
		complete:function(){$("#load").html("");},
		success:function(msg){						//msg为返回的数据，在这里做数据绑定
			var times = [];
			var name;
			var text = msg.shift();					//text[0][0]：图表名，text[0][1]：y轴名称，text[0][2]：单位
			$.each(msg,function(i,n){
				var datas = [];
				$.each(n,function(j,m){
					if(j == 0){
						name = m[1];
					}
					datas.push([m[2]]);				//或者datas.push([m[0],m[2]]);
					if(i==0){
						times.push([m[0]]);
					}	
				});
				options.series.push({name: name,data: datas});
			});
			
			//if(dataLabels_enabled) options.plotOptions.line.dataLabels.formatter = function() {return this.point.y +' '+text[0][2]};
			options.yAxis.labels.formatter = function() {return this.value +' '+text[0][2]};
			options.yAxis.title.text = text[0][1];
			options.tooltip.valueSuffix = text[0][2];
			options.title.text = text[0][0];
			options.xAxis.categories = times;
			
			$('#'+renderTo).highcharts(options);
		}
	});
}

/**
*绘制柱形图
*renderTo  				载入图的容器名
*rotation  				是否倾斜
*dataLabels_enabledurl 	是否启用数据标签
*step  					x轴标签的相隔显示的步长
*url 					访问后台的地址
*color 					主图表区背景色
*/
function column(renderTo,rotation,dataLabels_enabled,step,url,color){
	var options = {
	//配置chart选项
	chart: {
		//backgroundColor: color,						//主图表区背景色，即X轴与Y轴围成的区域的背景色
		renderTo: renderTo,  						//容器名，和body部分的div id要一致
		type: 'column'	        					//图表类型，这里选择折线图
	},
	//导出及打印选项 
	exporting:{
		enabled:true,
		url:'/CMAS/chart/down',
		filename:'chart'
	},
	//配置标题
	title: {},
	//配置x轴
	xAxis: {
		labels: {									//设置横轴坐标的显示样式   
			rotation: rotation,						//倾斜度 
			step: step								//标签的相隔显示的步长 默认1
		}
	},
	// 配置y轴
	yAxis: {
		title: {
			text: ''
		},
		labels: {
			formatter: function() {
				return this.value;
			}
		}
	},
	//数据点选项
	plotOptions: {
		column: {
			animation: true,  						//动画效果
			//allowPointSelect: true,
			cursor: 'pointer',
			showInLegend: true,						//是否显示图例
			dataLabels: {
				enabled: dataLabels_enabled,		//是否启用数据标签
				formatter: function() {
					return this.point.y;
					//return this.point.name +'：'+ this.point.y +' %';
				}
			}
		}
	},
	//配置数据点提示框
	tooltip: {
		headerFormat: '<b>{point.key}</b><br />'
	},
	series:[]
	}//options结束
				
	$.ajax({
		type:"GET",								//使用GET方法访问后台
		dataType:"json",						//返回json格式的数据
		url:url,								//访问后台的地址
		cache: false,							//缓存页面
		async: true,							//关闭同步请求，异步请求
		beforeSend:function(){$("#load").html("<font color='red'>Loading...</font>");},
		complete:function(){$("#load").html("");},
		success:function(msg){					//msg为返回的数据，在这里做数据绑定
			var times = [];
			var name;
			var text = msg.shift();				//text[0][0]：图表名，text[0][1]：y轴名称，text[0][2]：单位
			$.each(msg,function(i,n){
				var datas = [];
				$.each(n,function(j,m){
					if(j == 0){
						name = m[1];
					}
					datas.push([m[2]]);			//或者datas.push([m[0],m[2]]);
					if(i==0){
						times.push([m[0]]);
					}	
				});
				options.series.push({name: name,data: datas});
			});
			
			//if(dataLabels_enabled) options.plotOptions.column.dataLabels.formatter = function() {return this.point.y +' '+text[0][2]};
			options.yAxis.labels.formatter = function() {return this.value +' '+text[0][2]};
			options.yAxis.title.text = text[0][1];
			options.tooltip.valueSuffix = text[0][2];
			options.title.text = text[0][0];
			options.xAxis.categories = times;
			
			$('#'+renderTo).highcharts(options);
		}
	});
}

/**
*绘制堆积柱形图
*renderTo  				载入图的容器名
*rotation  				是否倾斜
*dataLabels_enabledurl 	是否启用数据标签
*step  					x轴标签的相隔显示的步长
*url 					访问后台的地址
*color 					主图表区背景色
*/
function column_stack(renderTo,rotation,dataLabels_enabled,step,url,color){
	var options = {
	//配置chart选项
	chart: {
		//backgroundColor: color,						//主图表区背景色，即X轴与Y轴围成的区域的背景色
		renderTo: renderTo,  						//容器名，和body部分的div id要一致
		type: 'column'	         					//图表类型，这里选择柱形图
	},
	//导出及打印选项 
	exporting:{
		enabled:true,
		url:'/CMAS/chart/down',
		filename:'chart'
	},
	//配置标题
	title: {},
	//配置x轴
	xAxis: {
		labels: {									//设置横轴坐标的显示样式   
			rotation: rotation,						//倾斜度 
			step: step								//标签的相隔显示的步长 默认1
		}
	},
	// 配置y轴
	yAxis: {
		title: {
			text: ''
		},
		labels: {
			formatter: function() {
				return this.value;
			}
		},
		stackLabels: {
			enabled: true,
			style: {
				//fontWeight: 'bold',
				color: (Highcharts.theme && Highcharts.theme.textColor) || 'gray'
			}
		}
	},
	//数据点选项
	plotOptions: {
		column: {
			stacking: 'normal',
			animation: true,  						//动画效果
			//allowPointSelect: true,
			cursor: 'pointer',
			showInLegend: true,						//是否显示图例
			dataLabels: {
				enabled: dataLabels_enabled,		//是否启用数据标签
				formatter: function() {
					return this.point.y;
					//return this.point.name +'：'+ this.point.y +' %';
				}
			}
		}
	},
	//配置数据点提示框
	tooltip: {
		headerFormat: '<b>{point.key}</b><br />'
	},
	series:[]
	}
				
	$.ajax({
		type:"GET",									//使用GET方法访问后台
		dataType:"json",							//返回json格式的数据
		url:url,									//访问后台的地址
		cache: false,								//缓存页面
		async: true,								//关闭同步请求，异步请求
		beforeSend:function(){$("#load").html("<font color='red'>Loading...</font>");},
		complete:function(){$("#load").html("");},
		success:function(msg){						//msg为返回的数据，在这里做数据绑定
			var times = [];							//x轴值
			var name;								//系列名
			var stack;								//堆积分类
			var text = msg.shift();					//text[0][0]：图表名，text[0][1]：y轴名称，text[0][2]：单位
			$.each(msg,function(i,n){
				var datas = [];
				$.each(n,function(j,m){
					if(j == 0){
						name = m[1];
						stack = m[3];
					}
					datas.push([m[2]]);				//或者datas.push([m[0],m[2]]);
					if(i==0){
						times.push([m[0]]);
					}	
				});
				options.series.push({name: name,data: datas,stack: stack});
			});
			
			//if(dataLabels_enabled) options.plotOptions.column.dataLabels.formatter = function() {return this.point.y +' '+text[0][2]};
			options.yAxis.labels.formatter = function() {return this.value +' '+text[0][2]};
			//options.yAxis.stackLabels.formatter = function() {return this.total +' '+text[0][2]};
			options.yAxis.title.text = text[0][1];
			options.tooltip.valueSuffix = text[0][2];
			options.title.text = text[0][0];
			options.xAxis.categories = times;
			
			$('#'+renderTo).highcharts(options);
		}
	});
}