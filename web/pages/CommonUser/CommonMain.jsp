<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="../inc/header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="pragma" content="no-cache" />
	<meta http-equiv="cache-control" content="no-cache" />
	<%--<link rel="shortcut icon" href="<%=path%>/images/favicon.ico" />--%>
	<%--<link type="text/css" href="<%=path%>/css/new-css.css" rel="stylesheet"/>--%>
	<%--<script type="text/javascript" src="<%=path%>/js/jquery-1.10.2.min.js"></script>--%>
	<%--<script type="text/javascript" src="<%=path%>/js/layer/layer.min.js"></script>--%>
	<title>数字证书服务网</title>
	<script>
		var index_parent =0;
		function layer_loading_parent(msg){
			index_parent= layer.load(msg);
		}
		function layer_loading_close_parent(){
			if(index_parent){
				layer.close(index_parent);
			}
		}
	</script>
</head>
<frameset rows="64,*" frameborder="0" framespacing="0">
	<frame src="top.do" id="topframe" name="topframe" noresize="noresize" frameborder="0" scrolling="no"/>
	<frameset cols="220,*">
		<frame src="left.do" id="leftframe" name="leftframe" noresize="noresize" frameborder="0" scrolling="auto"/>

		<frameset rows="25,*,50" frameborder="0" framespacing="0">
			<frame src="navbar.do" id="daohangframe" name="daohangframe" noresize="noresize" frameborder="0" scrolling="auto"/>
			<frame src="${url}" id="rightframe" name="rightframe" allowtransparency="true" noresize="noresize" frameborder="0" scrolling="auto"/>
			<frame src="bottom.do" id="bottomframe" name="bottomframe" noresize="noresize" frameborder="0" scrolling="auto"/>
		</frameset>
	</frameset>

</frameset>
<noframes>
	<body>您的浏览器无法处理框架！</body>
</noframes>
</html>