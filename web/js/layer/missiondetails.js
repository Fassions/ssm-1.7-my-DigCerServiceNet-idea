/**
 * missiondetails.jsp
 */
//项目路径、当前页、总页数
var pathName = location.pathname;
var path = pathName.substring(0,pathName.substr(1).indexOf('/')+1)+"/mission";
var currentPage=10;//${page.number};
var totalPages=10;//${page.totalPages};
//跳转
function gowhere() {
	var page=$('#currentPage').val();
	if (page==""||page==0||page>totalPages) {
		document.getElementById("currentPage").focus();
		return false;
	}
	page--;
	location.href =path+ "/checkmission/?currentPage=" + page +"&pagesize=" + $("#nmData").val()+ "&"
			+ $("#subForm").serialize();
}
//每页显示数
function cnpage(page) {
    window.location.href = path+"/checkmission/?pagesize=" + page + "&"
		+ $("#subForm").serialize();
}
//首页、上一页、下一页、末页
function fpnl(method) {
	switch(method){
	case "first":
		currentPage=0;
		break;
	case "previous":
		if(currentPage>0)
		currentPage--;
		break;
	case "next":
		if(currentPage<totalPages-1)
		currentPage++;
		break;
	case "last":
		currentPage=0;
		if(totalPages>0)
		currentPage=totalPages-1;
		break;
  }
   window.location.href = path+"/checkmission/?currentPage="+currentPage+"&pagesize=" + $("#nmData").val()+"&" + $("#logForm").serialize();
}	

$(function(){
	$("#manage").click(function(){
		window.location.href = "https://localhost:18082/mgr/mission";
		$("#page_daohang",window.parent.frames["daohangframe"].document).html("您所在的位置 &gt;&gt; 任务下发管理 &gt;&gt; 任务管理");
	});
	$("#create").click(function(){
		window.location.href = "https://localhost:18082/mgr/mission/creatmission";
		$("#page_daohang",window.parent.frames["daohangframe"].document).html("您所在的位置 &gt;&gt; 任务下发管理 &gt;&gt; 创建任务");
	});
	
	
});