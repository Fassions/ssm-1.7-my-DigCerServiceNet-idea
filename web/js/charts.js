
function pie(renderTo,title_text,param,url,urlc){
	var options = {
	chart: {
		renderTo: renderTo,  		
		type: 'pie'         		
	},
	exporting:{
		enabled:false
	},
	title: {
		text: title_text						
	},
	tooltip: {
		headerFormat: '<b>{point.key}</b><br />',
		pointFormat: '数量:<b>{point.y}</b>'
	},
	plotOptions: {
		pie: {
			cursor: 'pointer',
			showInLegend: true,					
			dataLabels: {
				enabled: true,
                color: '#000000',
                connectorColor: '#000000',
                format: '{point.percentage:.1f} %'
			},
			point: {
            	events: {
                	click:function(e) {/*
                		var directUrl = e.point.url;
                		if(directUrl)location.href=directUrl;
                		else{
                			if(urlc)location.href=urlc;
                		}
                 	*/if(urlc)location.href=urlc;
                		}
                 }
             }
		}
	},
	series: [{}]
	}
	$.ajax({
		type:"GET",						
		dataType:"json",
		data:param,
		url:url,								
		cache: false,						
		async: true,									
		success:function(msg){						
			options.series[0].data = msg;
			return new Highcharts.Chart(options);
		}
	});
}

function pline(renderTo,title_text,yAxis_text,yAxis_unit,url,param){
	  
	var options = {
	chart: {
		renderTo: renderTo,
		type: 'line'
	},
/* 	colors:[
		'#66ccff'
	], */
	exporting:{
		enabled:false
	},
	title: {
		text: title_text
	},
    xAxis: {
        type:'category',
        labels: {
            rotation: -45,
            align: 'right',
            style: {
                fontSize: '13px',
                fontFamily: 'Verdana, sans-serif'
            }
        }
    },
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
	plotOptions: {
		line: {
			//cursor: 'pointer',
			point: {
            	events: {
                	/*click:function() {
                	location.href=herf;
                 	} */
                 }
             }
		}
	},
	legend: {
        enabled: false
    },
	tooltip: {
		headerFormat: '<b>{point.key}</b><br />',
		pointFormat:'<b>{point.y}</b>',
		valueSuffix: yAxis_unit
	},
	series: [{
	}]
	}
				
	$.ajax({
		type:"GET",	
		dataType:"json",
		data:param,
		url:url,
		cache: false,
		async: true,
		success:function(msg){
			options.series[0].data=msg;
			chart=new Highcharts.Chart(options);
		}
	});
}

function line(renderTo,title_text,yAxis_text,yAxis_unit,url,herf){
	  
	var options = {
	chart: {
		renderTo: renderTo,
		type: 'line'
	},
/* 	colors:[
		'#66ccff'
	], */
	exporting:{
		enabled:false
	},
	title: {
		text: title_text
	},
    xAxis: {
        type:'category',
        labels: {
            rotation: -45,
            align: 'right',
            style: {
                fontSize: '13px',
                fontFamily: 'Verdana, sans-serif'
            }
        }
    },
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
	plotOptions: {
		line: {
			cursor: 'pointer',
			point: {
            	events: {
                	click:function() {
                	location.href=herf;
                 	} 
                 }
             }
		}
	},
	legend: {
        enabled: false
    },
	tooltip: {
		headerFormat: '<b>{point.key}</b><br />',
		pointFormat:'<b>{point.y}</b>',
		valueSuffix: yAxis_unit
	},
	series: [{
	}]
	}
				
	$.ajax({
		type:"GET",	
		dataType:"json",	
		url:url,
		cache: false,
		async: true,
		success:function(msg){
			options.series[0].data=msg;
			chart=new Highcharts.Chart(options);
		}
	});
}



function columnchart(renderTo,url,title,ytitle,xtitle,sname){
	$.getJSON(url, function (dt) {
		chart=new Highcharts.Chart({
	        chart: {
	        	renderTo:renderTo,
	        	type: 'column'
	        },
	        credits:{
	        	enabled:false
	        },
	        title: {
	            text: title
	        },
	        xAxis: {
	            type:'category',
	            labels: {
                    rotation: -45,
                    align: 'right',
                    style: {
                        fontSize: '13px',
                        fontFamily: 'Verdana, sans-serif'
                    }
                },
	            title: {
	                text: xtitle
	            }
	        },
	        yAxis: {
	        	allowDecimals:false,
	            min: 0,
	            title: {
	                text: ytitle
	            }
	        },
	        legend: {
                enabled: false
            },
	        tooltip: {
	        	headerFormat: '',
	    		pointFormat: ytitle+':<b>{point.y}</b>'
	        },
	        series: [{
	        	type:'column',
	            name: sname,
	            data: dt
	        }]
	    });
	});
	
}

