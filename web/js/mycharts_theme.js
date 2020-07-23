/**
 * 自定义图表主题
 */

Highcharts.theme = {
	//颜色  用于展示图表，折线/柱状/饼状等图的颜色，数组形式,默认是从第一个数据列起调用第一个颜色代码，有多少个数据列调用相应数量的颜色当数据列大于默认颜色数量时，重复从第一个颜色看是调用
	//colors: ['#4572A7', '#AA4643', '#89A54E', '#80699B', '#3D96AE', '#DB843D', '#92A8CD', '#A47D7C', '#B5CA92'],
	//colors: ["#DDDF0D", "#7798BF", "#55BF3B", "#DF5353", "#aaeeee", "#ff0066", "#eeaaee", "#55BF3B", "#DF5353", "#7798BF", "#aaeeee"],
	//colors: ['#058DC7', '#50B432', '#ED561B', '#DDDF00', '#24CBE5', '#64E572', '#FF9655', '#FFF263', '#6AF9C4'],
	//colors: ["#42A07B","#514F78", "#9B5E4A", "#72727F", "#1F949A", "#82914E", "#86777F", "#42A07B"],
	//colors: ["#DDDF0D", "#55BF3B", "#DF5353", "#7798BF", "#aaeeee", "#ff0066", "#eeaaee", "#55BF3B", "#DF5353", "#7798BF", "#aaeeee"],
	colors: ['#2f7ed8', '#1aadce', '#8bbc21', '#77a1e5', '#a6c96a', '#492970','#f28f43', '#c42525', '#0d233a', '#910000'],
	//配置chart选项
	chart: {
		backgroundColor:'#E6E6E6',//'rgba(255, 255, 255, .8)',
		/*backgroundColor: {				//图框背景颜色 默认#FFFFFF
			linearGradient: { x1: 0, y1: 1, x2: 0, y2: 0 },
			stops: [
				[0, 'rgb(255, 255, 255)'],
				[1, 'rgb(230, 230, 230)']
			]
		},*/
		borderColor:'#012647', 			//图框边框颜色 默认#4572A7
		borderWidth: 0,					//图框边框宽度 默认0
		borderRadius: 8,				//图框的圆角大小 默认5
		//plotBackgroundColor: '#B7B6C7',		//图表背景颜色 默认null
		plotBackgroundColor: 'rgba(183, 182, 199, .0)',
		plotShadow: false,				//图表是否使用阴影 默认false
		plotBorderWidth: 0				//图表的边框大小 默认0
	},
	//配置标题
	title: {
		style: {
			color: '#000',
			font: '13px "Trebuchet MS", Verdana, sans-serif'
		}
	},
	//配置链接及名称选项 
	credits: {
		enabled : false
	},
	//配置副标题
	subtitle: {
		text: ''						//副标题名称
	},
	//导出及打印选项 
	/*exporting:{
		enabled:true,
		url:'/chart/down',
		filename:'zlj'
	},*/
	//X轴设置
	xAxis: {
		gridLineWidth: 1,				//X轴网格线宽度 默认0
		lineColor: '#000000',				//X轴线颜色 默认#C0D0E0
		tickColor: '#000000',				//X轴标记(坐标的记号)的颜色 默认#C0D0E0
		gridLineColor: 'rgba(0, 0, 0, .2)',//X轴网格线颜色 默认#C0C0C0
		labels: {						//X轴的标签的相关属性
			style: {
				color: '#000',
				font: '10px Trebuchet MS, Verdana, sans-serif'
			}
		},
		title: {						//设置X轴坐标标题的相关属性
			style: {
				color: '#333',
				fontWeight: 'normal',	//默认bold
				fontSize: '12px',
				fontFamily: 'Trebuchet MS, Verdana, sans-serif'

			}
		}
	},
	//Y轴设置
	yAxis: {
		min: 0,							//Y轴的最小值   
		lineColor: '#000000',			//Y轴线颜色 默认#C0D0E0
		lineWidth: 1,					//Y轴的宽度 默认0
		tickWidth: 1,					//Y轴主标记(坐标的记号)的宽度 默认0
		tickColor: '#000000',			//Y轴主标记(坐标的记号)的颜色 默认#C0D0E0
		
		minorTickWidth:1,				//Y轴副标记的宽度
		minorTickLength:4,				//Y轴副标记的长度
		minorTickColor:'#666666',		//Y轴副标记的线条颜色
		minorTickInterval: 'auto',		//Y轴副标记的间隔 默认null
		
		gridLineColor: 'rgba(0, 0, 0, .2)',//Y轴网格线颜色 默认#C0C0C0
		//gridLineDashStyle:'Long',
		minorGridLineColor:'rgba(0, 0, 0, .1)',//Y轴副网格线颜色
		//minorGridLineDashStyle: 'dashes',
		labels: {						//Y轴的标签的相关属性
			style: {
				color: '#000',
				font: '10px Trebuchet MS, Verdana, sans-serif'
			}
		},
		title: {						//设置Y轴坐标标题的相关属性
			style: {
				color: '#333',
				fontWeight: 'normal',	//默认bold
				fontSize: '12px',
				fontFamily: 'Trebuchet MS, Verdana, sans-serif'
			}
		}
	},
	//图例设置
	legend: {
		y:10,
		itemStyle:{						//图例的样式
			fontSize: '12px',
			color: '#3E576F'			//图例颜色 默认#3E576F
		},
		padding:5,						//图例盒子的内边距 默认8.0
		margin:8,						//图例盒子的外边距 默认15
		itemHoverStyle: {				//鼠标放到某一图例说明上，文字颜色的变化颜色 默认#000
			color: '#000'
		},
		itemHiddenStyle: {
			color: '#CCC'
		}
	},
	//数据点提示框设置
	tooltip: {
		backgroundColor: {
			linearGradient: { x1: 0, y1: 0, x2: 0, y2: 1 },
			stops: [
				[0, 'rgba(255, 255, 255, .7)'],
				[1, 'rgba(255, 255, 255, .7)']
			]
		},
		shared: true,					//多条线显示数据
		borderWidth: 1,
		style: {
			color: '#333333',			//默认#333333
			fontSize: '9pt',			//默认9pt
			padding: '5px'				//默认5px
		}
	}
};
// 应用主题
var highchartsOptions = Highcharts.setOptions(Highcharts.theme);