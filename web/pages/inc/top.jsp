<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ include file="../inc/header.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<link type="text/css" href="<%=path%>/css/top.css" rel="stylesheet"/>
<script type="text/javascript">
function mainPage(){
	$("#rightframe",window.parent.document).attr("src","home.do");
	changeDaoHang();
}

function about(){
	$("#rightframe",window.parent.document).attr("src","abt.do");
	changeDaoHang('关于系统');
}

function wclose() {
	if(confirm("确认退出系统？")){
		$("#rightframe",window.parent.document).attr("src","<%=path%>/logOut.do");
	}
}

</script>
<title>top</title>
</head>

<body>
	<div class="div_1"><img src="<%=path%>/images/top/topLogo.png"/></div>
	<div class="div_2"><p>数字证书服务网</p></div>
	<div class="div_3">
		欢迎回来：${userName}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<%=nowDate%>&nbsp;&nbsp;&nbsp;&nbsp;
	</div>
	<div class="div_l"></div>

	<div class="div_4">
		<c:if test="${userType==0 }">
			<a onclick="javascript:mainPage()" title="主页" ><img src="/images/top/icon_home.png"/></a>
		</c:if>
		<a onclick="javascript:about()" title="关于"><img src="<%=path%>/images/top/icon_about.png"/></a>
		<a title="退出" onclick="wclose()"><img src="<%=path%>/images/top/icon_exit.png"/></a>
	</div>

</body>
</html>
