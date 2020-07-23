//父框架中id="frameid"自适应高度
function frame_auto_height(id_frame){
	var frame = $(window.parent.document).find(id_frame);
	var thisheight = $(document.body).height()+5;
	frame.height(thisheight);
}
//父框架的父框架中id="frameid"自适应高度
function frame_auto_height2(id_frame){
	var frame = $(window.parent.parent.document).find(id_frame);
	var thisheight = $(window.parent.document.body).height()+5;
	frame.height(thisheight);
}

//报表自适应父框架中框架id="frameid"高度
function auto_height(id_frame){
    var reportFrame = document.getElementById(id_frame);
    // 获得页面中的所有行 
    var tr = reportFrame.contentWindow.document.getElementsByTagName("tr");
    // 由于报表页面还存在页边距，因此框架高度是大于所有行累计的高度的，这里赋一个初始值以表示边距的大小  
    var height = 7;
    for(var i=0;i<tr.length;i++){  
    	height = height + tr[i].offsetHeight;
    }
    reportFrame.height = height;
}

function auto_height2(id_frame,parent_frame){
	var reportFrame = document.getElementById(id_frame);
    // 获得页面中的所有行 
    var tr = reportFrame.contentWindow.document.getElementsByTagName("tr");
    // 由于报表页面还存在页边距，因此框架高度是大于所有行累计的高度的，这里赋一个初始值以表示边距的大小  
    var height = 7;
    for(var i=0;i<tr.length;i++){  
    	height = height + tr[i].offsetHeight;
    }
    reportFrame.height = height;
			    
	var parent_frame = $(window.parent.document).find(parent_frame);
	var thisheight = $(document.body).height()+5;
	parent_frame.height(thisheight);
}

var pathName = location.pathname;
pathName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);