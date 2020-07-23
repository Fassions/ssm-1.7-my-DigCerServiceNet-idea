<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ include file="../inc/header.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>left</title>
<link type="text/css" href="<%=path%>/css/left.css" rel="stylesheet" />
<script type="text/javascript">
	$(function() {
		$("p").hover(function() {
			$(this).addClass("p_hover");
		}, function() {
			$(this).removeClass("p_hover");
		});
		$("p[class='unit']").next().hide();
		$("p[class^='unit']").on(
				'click',
				function() {
					if ($(this).next().css('display') == 'none') {
						$("p[class='unit_select']").prev().attr("class",
								"left_todown");
						$(this).prev().attr("class", "left_toup");

						$(".unit_select").next().slideUp(300).removeClass();
						$("p[class='unit_select']").removeClass();
						if($(this).next().children().length>0) {
							$(this).attr("class", "unit_select").next().slideDown(
									300);
						}else{
							$(this).attr("class", "unit_select");
						}

					}else {
						$(this).prev().attr("class", "left_todown");

						$(this).removeClass();
						$(this).next().slideUp(300);
						$(this).addClass("p_hover");
					}
				});

		//设置图标背景图
		setImg();
		for (var i = 0; i < titleImg.length; i++) {
			var title = titleImg[i];
			$("#" + title.id).attr("img0", title.img0);
			$("#" + title.id).attr("img1", title.img1);
			$("#" + title.id).attr("clicked", 0);
			$("#" + title.id).on(
					"click",
					function() {
						var clicked = $(this).attr("clicked");
						setImg();
						if (clicked == 0) {
							$(this).attr("clicked", 1);
							$(this).prev().prev().css("background-image",
									"url(" + $(this).attr("img1") + ")");

						} else {
							$(this).attr("clicked", 0);
							$(this).prev().prev().css("background-image",
									"url(" + $(this).attr("img0") + ")");
						}
					});
		}

		$("li").bind('click', function() {
			$("li").each(function() {
				if ($(this).attr("class") == 'li_select') {
					$(this).attr("class", "li_s");
				}
			});

			$(this).attr("class", "li_select");
		});
		var web = "<%=path%>";
		//证书订购
		$("#dinggou").bind(
				"click",
				function() {
					$("#rightframe", window.parent.document).attr("src",
							web + "/commonUserOrder/showOrderProcess.do");
					changeDaoHang('证书订购');
				});

		$("#dizhi").bind(
				"click",
				function() {
					$("#rightframe", window.parent.document).attr("src",
							web + "/commonUserAddress/showAddressInfo.do");
					changeDaoHang('收货地址管理');
				});
		$("#chaxun").bind(
				"click",
				function() {
					$("#rightframe", window.parent.document).attr("src",
							web + "/commonUserOrderQuery/getOrderList.do");
					changeDaoHang('订单查询');
				});
//
//		$("#log").bind("click", function() {
//			$("#rightframe", window.parent.document).attr("src", web + "/log");
//			changeDaoHang('日志审计', '操作日志');
//		});

		//卖家订单查询
		$("#dingdanchaxun").bind(
				"click",
				function() {
					$("#rightframe", window.parent.document).attr("src",
							web + "/commonCompanyOrderQuery/getOrderIndex.do");
					changeDaoHang('订单查询');
				});

		//卖家导出导入
		$("#daochu").bind(
				"click",
				function() {
					$("#rightframe", window.parent.document).attr("src",
							web + "/commonCompanyImportExport/getImportExportIndex.do");
					changeDaoHang('系统管理', '导出导入');
				});

		//卖家报表下载
		$("#reportDownload").bind(
				"click",
				function() {
					$("#rightframe", window.parent.document).attr("src",
							web + "/report/getReportDownload.do");
					changeDaoHang('系统管理', '报表下载');
				});

		//卖家模板管理
		$("#moban").bind(
				"click",
				function() {
					$("#rightframe", window.parent.document).attr("src",
							web + "/excelTemplate/getTemplateIndex.do");
					changeDaoHang('模板管理');
				});



		$("#userMan").bind(
				"click",
				function() {
					$("#rightframe", window.parent.document).attr("src",
							web + "/system/getUserMamage.do");
					changeDaoHang('系统管理', '买家用户管理');
				});
		$("#fileMan").bind(
				"click",
				function() {
					$("#rightframe", window.parent.document).attr("src",
							web + "/system/getfileMamage.do");
					changeDaoHang('系统管理', '文件上传');
				});

		$("#sellProduct").bind(
				"click",
				function() {
					$("#rightframe", window.parent.document).attr("src",
							web + "/system/getSellProduct.do");
					changeDaoHang('系统管理', '已售证书标号管理');
				});

	});

	var titleImg = [ {
		id : "chaxun",
		img0 : "/images/left/chaxun2.png",
		img1 : "/images/left/chaxun2.png"
	}, {
		id : "dinggou",
		img0 : "/images/left/dinggou2.png",
		img1 : "/images/left/dinggou2.png"
	}, {
		id : "dizhi",
		img0 : "/images/left/dizhi2.png",
		img1 : "/images/left/dizhi2.png"
	}, {
        id : "dingdanchaxun",
        img0 : "/images/left/chaxun2.png",
        img1 : "/images/left/chaxun2.png"
    }, {
        id : "reportMan",
        img0 : "/images/left/daoru2.png",
        img1 : "/images/left/daoru2.png"
    }, {
        id : "xitong",
        img0 : "/images/left/xitong2.png",
        img1 : "/images/left/xitong2.png"
    }, {
        id : "moban",
        img0 : "/images/left/moban2.png",
        img1 : "/images/left/moban2.png"
    }, {
		id : "zhichi",
		img0 : "/images/left/zhichi.png",
		img1 : "/images/left/zhichi.png"
	} ];

	function setImg() {
		for (var i = 0; i < titleImg.length; i++) {
			var title = titleImg[i];
			var ss = $("#" + title.id);
			var tubiao = $("#" + title.id).prev().prev();
			tubiao.css("background-image", "url(" + title.img0 + ")");
			$("#" + title.id).attr("clicked", 0);
		}
	}
</script>
</head>
<body id="page_left">
	<div id="levelmenu" class="left_d">


		<div class="left_l">
			<div class="left_tubiao"></div>
			<div class="left_todown"></div>
			<p class="unit" id="dinggou">证书订购</p>
			<ul></ul>
		</div>
		<c:if test="${userType==0 }">
			<div class="left_l">
				<div class="left_tubiao"></div>
				<div class="left_todown"></div>
				<p class="unit" id="chaxun">订单查询</p>
				<ul></ul>
			</div>
			<div class="left_l">
				<div class="left_tubiao"></div>
				<div class="left_todown"></div>
				<p class="unit" id="dizhi">收货地址管理</p>
				<ul></ul>
			</div>
			<%--<div class="left_l">--%>
				<%--<div class="left_tubiao"></div>--%>
				<%--<div class="left_todown"></div>--%>
				<%--<p class="unit" id="zhichi">技术支持</p>--%>
				<%--<ul></ul>--%>
			<%--</div>--%>
		</c:if>
		<c:if test="${userType!=0 }">
		<div class="left_l">
			<div class="left_tubiao"></div>
			<div class="left_todown"></div>
			<p class="unit" id="dingdanchaxun">订单查询</p>
			<ul></ul>
		</div>

		<div class="left_l">
			<div class="left_tubiao"></div>
			<div class="left_todown"></div>
			<p class="unit" id="reportMan">报表管理</p>
			<ul>
				<li id="daochu" class="li_s"><div class="li_s_d"></div>导出导入</li>
				<li id="reportDownload" class="li_s"><div class="li_s_d"></div>报表下载</li>
			</ul>
		</div>
		<div class="left_l">
			<div class="left_tubiao"></div>
			<div class="left_todown"></div>
			<p id="xitong" class="unit">系统管理</p>
			<ul>
				<li id="userMan" class="li_s"><div class="li_s_d"></div>买家用户管理</li>
				<li id="sellProduct" class="li_s"><div class="li_s_d"></div>已售证书标号管理</li>
				<li id="fileMan" class="li_s"><div class="li_s_d"></div>文件上传</li>
			</ul>
		</div>
		<div class="left_l">
			<div class="left_tubiao"></div>
			<div class="left_todown"></div>
			<p class="unit" id="moban">模板管理</p>
			<ul></ul>
		</div>
		</c:if>

	</div>
</body>
</html>