/**
 * �Զ���ͼ������
 */

Highcharts.theme = {
	//��ɫ  ����չʾͼ������/��״/��״��ͼ����ɫ��������ʽ,Ĭ���Ǵӵ�һ������������õ�һ����ɫ���룬�ж��ٸ������е�����Ӧ��������ɫ�������д���Ĭ����ɫ����ʱ���ظ��ӵ�һ����ɫ���ǵ���
	//colors: ['#4572A7', '#AA4643', '#89A54E', '#80699B', '#3D96AE', '#DB843D', '#92A8CD', '#A47D7C', '#B5CA92'],
	//colors: ["#DDDF0D", "#7798BF", "#55BF3B", "#DF5353", "#aaeeee", "#ff0066", "#eeaaee", "#55BF3B", "#DF5353", "#7798BF", "#aaeeee"],
	//colors: ['#058DC7', '#50B432', '#ED561B', '#DDDF00', '#24CBE5', '#64E572', '#FF9655', '#FFF263', '#6AF9C4'],
	//colors: ["#42A07B","#514F78", "#9B5E4A", "#72727F", "#1F949A", "#82914E", "#86777F", "#42A07B"],
	//colors: ["#DDDF0D", "#55BF3B", "#DF5353", "#7798BF", "#aaeeee", "#ff0066", "#eeaaee", "#55BF3B", "#DF5353", "#7798BF", "#aaeeee"],
	colors: ['#2f7ed8', '#1aadce', '#8bbc21', '#77a1e5', '#a6c96a', '#492970','#f28f43', '#c42525', '#0d233a', '#910000'],
	//����chartѡ��
	chart: {
		backgroundColor:'#E6E6E6',//'rgba(255, 255, 255, .8)',
		/*backgroundColor: {				//ͼ�򱳾���ɫ Ĭ��#FFFFFF
			linearGradient: { x1: 0, y1: 1, x2: 0, y2: 0 },
			stops: [
				[0, 'rgb(255, 255, 255)'],
				[1, 'rgb(230, 230, 230)']
			]
		},*/
		borderColor:'#012647', 			//ͼ��߿���ɫ Ĭ��#4572A7
		borderWidth: 0,					//ͼ��߿��� Ĭ��0
		borderRadius: 8,				//ͼ���Բ�Ǵ�С Ĭ��5
		//plotBackgroundColor: '#B7B6C7',		//ͼ������ɫ Ĭ��null
		plotBackgroundColor: 'rgba(183, 182, 199, .0)',
		plotShadow: false,				//ͼ���Ƿ�ʹ����Ӱ Ĭ��false
		plotBorderWidth: 0				//ͼ��ı߿��С Ĭ��0
	},
	//���ñ���
	title: {
		style: {
			color: '#000',
			font: '13px "Trebuchet MS", Verdana, sans-serif'
		}
	},
	//�������Ӽ�����ѡ�� 
	credits: {
		enabled : false
	},
	//���ø�����
	subtitle: {
		text: ''						//����������
	},
	//��������ӡѡ�� 
	/*exporting:{
		enabled:true,
		url:'/chart/down',
		filename:'zlj'
	},*/
	//X������
	xAxis: {
		gridLineWidth: 1,				//X�������߿�� Ĭ��0
		lineColor: '#000000',				//X������ɫ Ĭ��#C0D0E0
		tickColor: '#000000',				//X����(����ļǺ�)����ɫ Ĭ��#C0D0E0
		gridLineColor: 'rgba(0, 0, 0, .2)',//X����������ɫ Ĭ��#C0C0C0
		labels: {						//X��ı�ǩ���������
			style: {
				color: '#000',
				font: '10px Trebuchet MS, Verdana, sans-serif'
			}
		},
		title: {						//����X�����������������
			style: {
				color: '#333',
				fontWeight: 'normal',	//Ĭ��bold
				fontSize: '12px',
				fontFamily: 'Trebuchet MS, Verdana, sans-serif'

			}
		}
	},
	//Y������
	yAxis: {
		min: 0,							//Y�����Сֵ   
		lineColor: '#000000',			//Y������ɫ Ĭ��#C0D0E0
		lineWidth: 1,					//Y��Ŀ�� Ĭ��0
		tickWidth: 1,					//Y�������(����ļǺ�)�Ŀ�� Ĭ��0
		tickColor: '#000000',			//Y�������(����ļǺ�)����ɫ Ĭ��#C0D0E0
		
		minorTickWidth:1,				//Y�ḱ��ǵĿ��
		minorTickLength:4,				//Y�ḱ��ǵĳ���
		minorTickColor:'#666666',		//Y�ḱ��ǵ�������ɫ
		minorTickInterval: 'auto',		//Y�ḱ��ǵļ�� Ĭ��null
		
		gridLineColor: 'rgba(0, 0, 0, .2)',//Y����������ɫ Ĭ��#C0C0C0
		//gridLineDashStyle:'Long',
		minorGridLineColor:'rgba(0, 0, 0, .1)',//Y�ḱ��������ɫ
		//minorGridLineDashStyle: 'dashes',
		labels: {						//Y��ı�ǩ���������
			style: {
				color: '#000',
				font: '10px Trebuchet MS, Verdana, sans-serif'
			}
		},
		title: {						//����Y�����������������
			style: {
				color: '#333',
				fontWeight: 'normal',	//Ĭ��bold
				fontSize: '12px',
				fontFamily: 'Trebuchet MS, Verdana, sans-serif'
			}
		}
	},
	//ͼ������
	legend: {
		y:10,
		itemStyle:{						//ͼ������ʽ
			fontSize: '12px',
			color: '#3E576F'			//ͼ����ɫ Ĭ��#3E576F
		},
		padding:5,						//ͼ�����ӵ��ڱ߾� Ĭ��8.0
		margin:8,						//ͼ�����ӵ���߾� Ĭ��15
		itemHoverStyle: {				//���ŵ�ĳһͼ��˵���ϣ�������ɫ�ı仯��ɫ Ĭ��#000
			color: '#000'
		},
		itemHiddenStyle: {
			color: '#CCC'
		}
	},
	//���ݵ���ʾ������
	tooltip: {
		backgroundColor: {
			linearGradient: { x1: 0, y1: 0, x2: 0, y2: 1 },
			stops: [
				[0, 'rgba(255, 255, 255, .7)'],
				[1, 'rgba(255, 255, 255, .7)']
			]
		},
		shared: true,					//��������ʾ����
		borderWidth: 1,
		style: {
			color: '#333333',			//Ĭ��#333333
			fontSize: '9pt',			//Ĭ��9pt
			padding: '5px'				//Ĭ��5px
		}
	}
};
// Ӧ������
var highchartsOptions = Highcharts.setOptions(Highcharts.theme);