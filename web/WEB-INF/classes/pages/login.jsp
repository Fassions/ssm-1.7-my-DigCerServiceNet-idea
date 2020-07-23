<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="./inc/header.jsp" %>
<%@ include file="./init.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>登录</title>
		<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
		<!-- <OBJECT id=CaluNumCtrl codeBase="ActiveX/AtlTest.CAB#version=1,0,0,1" classid="clsid:632519B6-4A72-4951-BF66-7E68C30A213A"></OBJECT> -->
		<%--<link rel="shortcut icon" href="../images/favicon.ico" />--%>
		<link rel="stylesheet" type="text/css" href="<c:url value="/css/login-css.css"/>">
		<script type="text/javascript" src="<c:url value="/js/jquery-1.10.2.min.js"/>"></script>
		<script type="text/javascript">
			if(top.location != self.location){
				top.location = self.location;
			}
			
		
			function entry() {
				/* if(CaluNumCtrl.GetCerKeyStatus(0)!=0){
				     alert("未插入数字证书");
				     return;
				} */
					$("#loginP").css("background-image", "url(images/login/button_check.png)");
					location.href="https://<%=serverName%>:<%=httpsPort%><%=path%>/login.do";
			}
		
		</script>
	</head>
	<body>
		<img class="back_img" src="../images/login/login_background.png">
		<div id="maindiv" class="div1">
			<div id="content" class="content_d">
				<div class="login_l">
					<img class="logo" src="../images/login/bglogo.png"/>
				</div>
				<div class="login_title">
					<p>数字证书服务网</p>
				</div>
				<div class="login_d">
					<div class="login_d_tl" style="width:100%"><p>数字证书登录</p></div>
					<!-- <div class="login_d_tf"><p>管理员注册</p></div> -->
					<div id="showDiv">
						<div>
							<img class="login_d_i1" src="../images/login/certlogin.png"/>
						</div>
						<div>
							<div class="login_d_i2" id="loginP" onclick="entry()">
							</div>
						</div>
					</div>
				</div>
				<div style="margin:6% 0px 0px 0px"></div>
				<%--<span id='copyright'>|&nbsp;&nbsp;<a href="<%=path%>/showFaq.do?div=1">常见问题</a>&nbsp;&nbsp;|&nbsp;&nbsp;<a href="<%=path%>/showFaq.do?div=2">相关下载</a>&nbsp;&nbsp;|&nbsp;&nbsp;<a href="<%=path%>/showFaq.do?div=3">工作流程</a>&nbsp;&nbsp;|&nbsp;&nbsp;<a href="<%=path%>/showFaq.do?div=5">证书出货日期查询</a>&nbsp;&nbsp;|&nbsp;&nbsp;<a href="<%=path%>/showFaq.do?div=4">联系我们</a>&nbsp;&nbsp;|</span>--%>
				<div class="bottom">
					<b>版权所有©2019 上海辰锐信息科技公司</b>
				</div>

			</div>
		</div>
	</body>
</html>