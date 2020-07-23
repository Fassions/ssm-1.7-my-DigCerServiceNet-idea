<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ include file="../inc/header.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>daohang</title>
<style type="text/css">
* {
	margin: 0px;
	padding: 0px;
	overflow: hidden;
}
body {
	font-size: 15px;
	font-family: "Microsoft YaHei","STHeiti Light";
	background-color: #E6E6E6;
	background-repeat: repeat;
}
/*导航条*/
.daohang {
	color: #285d9a;
	background-color: #E6E6E6;
	height: 25px;
	line-height: 25px;
	margin-left: 8px;
}
.daohang span,.span {
	margin-right: 12px;
}
/*白色背景*/
.white_c {
	position: relative;
	width: 100%;
	height: 25px;
	padding: 8px 8px 0px 8px;
}
.white_back {
	position: absolute;
	z-index: 10;
	left: 8px;
	width: 100%;
	height: 17px;
	background-color: #fff; 
}
.white_right {
	position: absolute;
	z-index: 11;
	right: 16px;
	width: 8px;
	height: 17px;
	background-color: #E6E6E6; 
}
a:hover{
	text-decoration: underline;
}
</style>
<script type="text/javascript">
	$(function () {
		
	})
</script>
</head>

<body>
	<div class="white_c" id="whiteDiv" style="display: none;">
		<div class="white_back"></div>
		<div class="white_right"></div>
	</div>
	<div id="page_daohang" class="daohang" style="background-color:#E6E6E6">
		<span>您所在的位置</span><span>&gt;&gt;</span><span>证书订购</span>
	</div>
<script type="text/javascript">
	function HrefClick(url) {
		var web = "<%=path%>";
		$("#rightframe", window.parent.document).attr("src",
				web + url);
	}
	function changeCallBack(arguments) {
		changeDaoHangFrame(arguments);
	}
</script>
</body>
</html>