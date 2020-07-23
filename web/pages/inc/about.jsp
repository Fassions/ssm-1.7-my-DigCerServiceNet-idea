<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<style type="text/css">
.body_class {
	width: 80%;
	margin: auto;
	text-indent: 24px;
	font-family: "Microsoft YaHei","STHeiti Light";
}
.title{
 	text-align:center;
 	text-indent: 0px;
}
.d_body{
 	font-size: 12px;
 	line-height:28px;
 	margin-bottom: 48px;
}
</style>
</head>
<body class="page_content" >
<div class="body_class">
	<div class="title"><h2>${systemName}</h2></div>
  	<div class="d_body">
  		<p>证书服务网，是由上海辰锐信息科技公司自主研发的数字证书介质网上订购和生产管理的项目。实现提高用户订购证书和查询证书订购进度的灵活性，减轻市场人员接电话的压力，同时推动证书订购到派单生产的电子化进程。</p>
  	</div>
  	<div class="d_body">
  		<p>版本号：${systemVersionInfo}</p>
  		<p>产品ID：${systemId}</p>
  		<p>发布日期：${systemPublicDate}</p>
  		<p>支持浏览器：IE8、IE9、IE10和IE11、Chrome</p>
  	</div>
</div>  
</body>
</html>