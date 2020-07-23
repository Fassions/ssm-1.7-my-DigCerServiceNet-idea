<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<meta name="viewport" content="width=device-width,initial-scale=1">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<%@ include file="../../init.jsp" %>
<%@ include file="../../inc/header.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>数字证书服务网</title>
    <%--<link type="text/css" href="<%=path%>/css/css.css" rel="stylesheet" />--%>
    <%--<link type="text/css" href="<%=path%>/css/bootstrap.min.css" rel="stylesheet" />--%>
    <%-- 		<script type="text/javascript" src="<%=path%>/js/validate/jquery.validate.js"></script>
            <script type="text/javascript" src="<%=path%>/js/validate/additional-methods.js"></script>
            <script type="text/javascript" src="<%=path%>/js/validate/messages_zh.js"></script> --%>
    <%--<script type="text/javascript" src="<%=path%>/js/validate.js"></script>--%>
    <script type="text/javascript" src="<%=path%>/js/datepicker/WdatePicker.js"></script>
    <%--<script type="text/javascript" src="<%=path%>/js/example.js"></script>--%>
    <%--<script type="text/javascript" src="<%=path%>/js/layer.js"></script>--%>
    <%--<link type="text/css" href="<%=path%>/css/main.css" rel="stylesheet" />--%>
    <%--<link type="text/css" href="<%=path%>/js/themes/jquery-ui.min.css"--%>
          <%--rel="stylesheet" />--%>
    <style type="text/css">
        .checkText{
            height: 25px !important;
            width: 300px !important;
        }
        input[type=radio]{
            margin-left: 1.5%;
        }
        input{
            margin-left: 1%;
            margin-right: 1%;
        }

        input.file{ vertical-align:middle; position:relative; filter:alpha(opacity=0); opacity:0; z-index:1; width:382px; height: 36px; line-height: 36px; cursor:pointer; top: -36px; }

        form input.viewfile { z-index:99;  padding:2px; width:300px; vertical-align:middle; color:#999; }

        input[type=text],
        .viewfile{
            border: 1px solid #707070;
        }
        .tdiv_a_5{
            width: 532px !important;
        }
    </style>
    <script type="text/javascript">
        $(function() {
            //跳转任务页面
//				window.location.href = path;

//				$("#page_daohang", window.parent.frames["daohangframe"].document).html("您所在的位置 &gt;&gt; 证书订购");

        //缩略展开+-
        $("p").hover(function() {
            $(this).addClass("p_hover");
        }, function() {
            $(this).removeClass("p_hover");
        });
//            $("div[class='unit']").next().hide();
        $("p[class='unit']").parent().next().show();
        $("p[class^='unit']").on(
                'click',
                function() {
                    if ($(this).parent().next().css('display') == 'none') {
//                            $("div[class='unit_select']").prev().attr("class", "menu-i");
//                            $(this).prev().attr("class", "menu-i");
//                            $(".unit_select").parent().next().slideUp(300).removeClass();
//                            $(this).attr("class", "unit_select").parent().next().slideDown(300);

                        $(this).prev().attr("class", "menu-i");
                        $(this).parent().next().slideUp(300).removeClass();
                        $(this).parent().next().slideDown(300);
                    } else {
                        $(this).prev().attr("class", "menu-c");

                        $(this).removeClass();
                        $(this).parent().next().slideUp(300);
                        $(this).addClass("p_hover");
                    }
                });
        })
    </script>
</head>





<body>
<div class="page_content">


<input type="hidden" name = "orderId" id = "orderId" value="${orderId}"/>
<input type="hidden" name = "flowId" id = "flowId" value="${order.flowId}"/>
<input type="hidden" name = "orderUnitName" id = "orderUnitName" value="${order.orderUnitName}"/>

<div class="tdiv" >
    <div class="tdiv_title">
        <i class="menu-i"></i>
        <p class="unit">功能</p>
    </div>
    <div>
        <div class="tdiv_a_z">
            <div class="tdiv_a_1">订单流水号：</div>
            <div class="tdiv_a_4">${order.flowId}</div>
            <c:choose>
                <c:when test="${sessionScope.gaUser.userType==1}">
                    <c:choose>
                        <c:when test="${order.orderStatus==0}">
                            <div class="tdiv_a_1 ">订单状态：</div><div class="tdiv_a_4">待受理</div>
                            <div class="tdiv_a_1 tdiv_a_1_left">
                                <input type="button" class="btn btn-info" onclick="_putOrderStatus(1)"  value="接受订单"/>
                            </div>
                            <div class="tdiv_a_5" style="float: right;">
                                <input type="button" class="btn btn-info" onclick="_downLoadOrderFile()" value="下载附件"/>
                               

                                <input type="button" class="btn btn-info" data-toggle="modal" data-target="#returnModal" value="退回到买家"/>
                                <input type="button" class="btn btn-info" onclick="editOrder('${order.id}')" value="编辑订单"/>
                                <c:if test="${order.orderType==4}">
	                                <c:choose>
	                                    <c:when test="${order.borrowType==0}">
	                                        <input type="button" class="btn btn-info" onclick="_putBorrowType(1)" value="进入借转销"/>
	                                    </c:when>
	                                    <c:otherwise>
	                                        <input type="button" class="btn btn-info" onclick="_putBorrowType(0)" value="完成借转销"/>
	                                    </c:otherwise>
	                                </c:choose>
                                </c:if>
                            </div>
                        </c:when>
                        <c:when test="${order.orderStatus==1}">
                            <div class="tdiv_a_1">订单状态：</div><div class="tdiv_a_4">已受理待付款</div>
                            <div class="tdiv_a_1 tdiv_a_1_left">
                                <input type="button" class="btn btn-info" onclick="_putOrderStatus(7)"  value="进入待生产"/>
                            </div>
                            <div class="tdiv_a_5" style="float: right">
                                <input type="button" class="btn btn-info" onclick="_downLoadOrderFile()" value="下载附件"/>
                                <input type="button" class="btn btn-info" data-toggle="modal" data-target="#returnModal" value="退回到买家"/>
                                <input type="button" class="btn btn-info" onclick="editOrder('${order.id}')" value="编辑订单"/>
                                <input type="button" class="btn btn-info" data-toggle="modal" data-target="#invalidModal" value="作废订单"/>
                                <c:if test="${order.orderType==4}">
	                                <c:choose>
	                                    <c:when test="${order.borrowType==0}">
	                                        <input type="button" class="btn btn-info" onclick="_putBorrowType(1)" value="进入借转销"/>
	                                    </c:when>
	                                    <c:otherwise>
	                                        <input type="button" class="btn btn-info" onclick="_putBorrowType(0)" value="完成借转销"/>
	                                    </c:otherwise>
	                                </c:choose>
                                </c:if>
                                    <%--<input type="button" value="拆分子单"/>--%>
                            </div>
                        </c:when>
                        <c:when test="${order.orderStatus==7}">
                            <div class="tdiv_a_1">订单状态：</div><div class="tdiv_a_4">待生产</div>
                            <div class="tdiv_a_1 tdiv_a_1_left">
                                <input type="button" class="btn btn-info" onclick="_putOrderStatus(2)"  value="进入生产"/>
                            </div>
                            <div class="tdiv_a_5" style="float: right;width: 410px !important;">
                                <input type="button" class="btn btn-info" onclick="_downLoadOrderFile()" value="下载附件"/>
                                <c:if test="${order.orderType==4}">
	                                <c:choose>
	                                    <c:when test="${order.borrowType==0}">
	                                        <input type="button" class="btn btn-info" onclick="_putBorrowType(1)" value="进入借转销"/>
	                                    </c:when>
	                                    <c:otherwise>
	                                        <input type="button" class="btn btn-info" onclick="_putBorrowType(0)" value="完成借转销"/>
	                                    </c:otherwise>
	                                </c:choose>
                                </c:if>
                                <%--<input type="button" class="btn btn-info" data-toggle="modal" data-target="#returnModal" value="退回到买家"/>--%>
                                <%--<input type="button" class="btn btn-info" onclick="editOrder('${order.id}')" value="编辑订单"/>--%>
                                    <%--<input type="button" value="拆分子单"/>--%>
                            </div>
                        </c:when>

                        <c:when test="${order.orderStatus==2}">
                            <div class="tdiv_a_1">订单状态：</div><div class="tdiv_a_4">生产中—待生产</div>
                            <div class="tdiv_a_5 tdiv_a_1_left">
                                <c:choose>
                                    <c:when test="${order.productionErrorStatus==1}">
                                        <input type="button" class="btn btn-danger" onclick="_putproductionErrorStatus(0)"  value="完成重审"/>
                                        <input type="button" class="btn btn-info" onclick="editOrder('${order.id}')" value="编辑订单"/>
                                    </c:when>
                                    <c:otherwise>
                                        <input type="button" class="btn btn-info" onclick="_putOrderStatus(2.1)"  value="派发生产"/>
                                        <input type="button" class="btn btn-danger" onclick="_putproductionErrorStatus(1)"  value="退回重审"/>
                                    </c:otherwise>
                                </c:choose>
                            </div>
                            <div class="tdiv_a_5" style="float: right;">
                                <input type="button" class="btn btn-info" onclick="_downLoadOrderFile()" value="下载附件"/>
                                <c:if test="${order.orderType==4}">
	                                <c:choose>
	                                    <c:when test="${order.borrowType==0}">
	                                        <input type="button" class="btn btn-info" onclick="_putBorrowType(1)" value="进入借转销"/>
	                                    </c:when>
	                                    <c:otherwise>
	                                        <input type="button" class="btn btn-info" onclick="_putBorrowType(0)" value="完成借转销"/>
	                                    </c:otherwise>
	                                </c:choose>
                                </c:if>
                                <%--<input type="button" class="btn btn-info" data-toggle="modal" data-target="#returnModal" value="退回到买家"/>--%>
                                <%--<input type="button" class="btn btn-info" onclick="editOrder('${order.id}')" value="编辑订单"/>--%>
                            </div>
                        </c:when>
                        <c:when test="${order.orderStatus==2.1}">
                            <div class="tdiv_a_1">订单状态：</div><div class="tdiv_a_4">生产中—生产中</div>
                            <div class="tdiv_a_5 tdiv_a_1_left">
                                <c:choose>
                                    <c:when test="${order.productionErrorStatus==1}">
                                        <input type="button" class="btn btn-danger" onclick="_putproductionErrorStatus(0)"  value="完成重审"/>
                                        <input type="button" class="btn btn-info" onclick="editOrder('${order.id}')" value="编辑订单"/>
                                    </c:when>
                                    <c:otherwise>
                                        <input type="button" class="btn btn-info" onclick="_putOrderStatus(2.2)"  value="完成生产"/>
                                        <input type="button" class="btn btn-danger" onclick="_putproductionErrorStatus(1)"  value="退回重审"/>
                                    </c:otherwise>
                                </c:choose>
                            </div>
                            <div class="tdiv_a_5" style="float: right;">
                                <input type="button" class="btn btn-info" onclick="_downLoadOrderFile()" value="下载附件"/>
                                <c:if test="${order.orderType==4}">
	                                <c:choose>
	                                    <c:when test="${order.borrowType==0}">
	                                        <input type="button" class="btn btn-info" onclick="_putBorrowType(1)" value="进入借转销"/>
	                                    </c:when>
	                                    <c:otherwise>
	                                        <input type="button" class="btn btn-info" onclick="_putBorrowType(0)" value="完成借转销"/>
	                                    </c:otherwise>
	                                </c:choose>
                                </c:if>
                                <%--<input type="button" class="btn btn-info" data-toggle="modal" data-target="#returnModal" value="退回到买家"/>--%>
                                <%--<input type="button" class="btn btn-info" onclick="editOrder('${order.id}')" value="编辑订单"/>--%>
                            </div>
                        </c:when>
                        <c:when test="${order.orderStatus==2.2}">
                            <div class="tdiv_a_1">订单状态：</div><div class="tdiv_a_4">生产中—生产完待发货</div>
                            <div class="tdiv_a_5 tdiv_a_1_left">
                                <c:choose>
                                    <c:when test="${order.productionErrorStatus==1}">
                                        <input type="button" class="btn btn-danger" onclick="_putproductionErrorStatus(0)"  value="完成重审"/>
                                        <input type="button" class="btn btn-info" onclick="editOrder('${order.id}')" value="编辑订单"/>
                                    </c:when>
                                    <c:otherwise>
                                        <input type="button" class="btn btn-info" onclick="_putOrderStatus(3)"  value="进入发运中"/>
                                        <input type="button" class="btn btn-danger" onclick="_putproductionErrorStatus(1)"  value="退回重审"/>
                                    </c:otherwise>
                                </c:choose>
                            </div>
                            <div class="tdiv_a_5" style="float: right;">
                                <input type="button" class="btn btn-info" onclick="_downLoadOrderFile()" value="下载附件"/>
                                <c:if test="${order.orderType==4}">
	                                <c:choose>
	                                    <c:when test="${order.borrowType==0}">
	                                        <input type="button" class="btn btn-info" onclick="_putBorrowType(1)" value="进入借转销"/>
	                                    </c:when>
	                                    <c:otherwise>
	                                        <input type="button" class="btn btn-info" onclick="_putBorrowType(0)" value="完成借转销"/>
	                                    </c:otherwise>
	                                </c:choose>
                                </c:if>
                                <%--<input type="button" class="btn btn-info" data-toggle="modal" data-target="#returnModal" value="退回到买家"/>--%>
                                <%--<input type="button" class="btn btn-info" onclick="editOrder('${order.id}')" value="编辑订单"/>--%>
                            </div>
                        </c:when>
                        <c:when test="${order.orderStatus==3}">
                            <div class="tdiv_a_1">订单状态：</div><div class="tdiv_a_4">发运中（如未填写快递信息,请填写快递信息）</div>
                            <div class="tdiv_a_5" style="float: right">
                                <input type="button" class="btn btn-info" onclick="_downLoadOrderFile()" value="下载附件"/>
                                <input type="button" class="btn btn-info" onclick="_downLoadOrderReportFile()" value="下载签收单"/>
                                <c:if test="${order.orderType==4}">
	                                <c:choose>
	                                    <c:when test="${order.borrowType==0}">
	                                        <input type="button" class="btn btn-info" onclick="_putBorrowType(1)" value="进入借转销"/>
	                                    </c:when>
	                                    <c:otherwise>
	                                        <input type="button" class="btn btn-info" onclick="_putBorrowType(0)" value="完成借转销"/>
	                                    </c:otherwise>
	                                </c:choose>
                                </c:if>
                            </div>
                        </c:when>
                        <c:when test="${order.orderStatus==4}">
                            <div class="tdiv_a_1">订单状态：</div><div class="tdiv_a_4">完成</div>
                            <div class="tdiv_a_5" style="float: right">
                                <input type="button" class="btn btn-info" onclick="_downLoadOrderFile()" value="下载附件"/>
                                <input type="button" class="btn btn-info" onclick="_downLoadOrderReportFile()" value="下载签收单"/>
                                <c:if test="${order.orderType==4}">
	                                <c:choose>
	                                    <c:when test="${order.borrowType==0}">
	                                        <input type="button" class="btn btn-info" onclick="_putBorrowType(1)" value="进入借转销"/>
	                                    </c:when>
	                                    <c:otherwise>
	                                        <input type="button" class="btn btn-info" onclick="_putBorrowType(0)" value="完成借转销"/>
	                                    </c:otherwise>
	                                </c:choose>
                                </c:if>
                            </div>
                        </c:when>
                        <c:when test="${order.orderStatus==5}">
                            <div class="tdiv_a_1">订单状态：</div><div class="tdiv_a_4">退回</div>
                            <div class="tdiv_a_5" style="float: right">
                                <input type="button" class="btn btn-info" onclick="_downLoadOrderFile()" value="下载附件"/>
                                <c:if test="${order.orderType==4}">
	                                <c:choose>
	                                    <c:when test="${order.borrowType==0}">
	                                        <input type="button" class="btn btn-info" onclick="_putBorrowType(1)" value="进入借转销"/>
	                                    </c:when>
	                                    <c:otherwise>
	                                        <input type="button" class="btn btn-info" onclick="_putBorrowType(0)" value="完成借转销"/>
	                                    </c:otherwise>
	                                </c:choose>
                                </c:if>
                            </div>
                        </c:when>
                        <c:when test="${order.orderStatus==6}">
                            <div class="tdiv_a_1">订单状态：</div><div class="tdiv_a_4">作废</div>
                            <div class="tdiv_a_5" style="float: right">
                                <input type="button" class="btn btn-info" onclick="_downLoadOrderFile()" value="下载附件"/>
                                <c:if test="${order.orderType==4}">
	                                <c:choose>
	                                    <c:when test="${order.borrowType==0}">
	                                        <input type="button" class="btn btn-info" onclick="_putBorrowType(1)" value="进入借转销"/>
	                                    </c:when>
	                                    <c:otherwise>
	                                        <input type="button" class="btn btn-info" onclick="_putBorrowType(0)" value="完成借转销"/>
	                                    </c:otherwise>
	                                </c:choose>
                                </c:if>
                            </div>
                        </c:when>
                    </c:choose>
                    <%--<div class="tdiv_a_5" style="float: right;">--%>
                    <%--<input type="button" onclick="_downLoadOrderFile()" value="下载附件"/>--%>
                    <%--<input type="button" data-toggle="modal" data-target="#returnModal" value="退回到买家"/>--%>
                    <%--<input type="button" onclick="editOrder('${order.id}')" value="编辑订单"/>--%>
                    <%--<input type="button" data-toggle="modal" data-target="#invalidModal" value="作废订单"/>--%>
                    <%--<input type="button" value="拆分子单"/>--%>
                    <%--</div>--%>

                </c:when>
                <c:otherwise>
                    <div class="tdiv_a_1">
                        <input type="button" class="btn btn-info" onclick="_downLoadOrderFile()" value="下载附件"/>
                    </div>
                </c:otherwise>
            </c:choose>
        </div>
        <div class="clearfloat"></div>
        <div class="tdiv_bottom"></div>
    </div>
</div>
<div class="tdiv">
    <div class="tdiv_title">
        <i class="menu-i"></i>
        <p class="unit">产品选购</p>
    </div>
    <div>
        <c:if test="${sessionScope.gaUser.userType==1}">
            <div class="tdiv_a_z">
                <div class="tdiv_a_1" >订购类型：</div>
                <div class="tdiv_a_3">
                    <select style="width:150px;background-color: #e6e6e6" disabled id="orderType" name="orderType">
                        <c:if test="${order.orderType == 0}">
                            <option value="${order.orderType}" selected>订购</option>
                        </c:if>
                        <c:if test="${order.orderType == 1}">
                            <option value="${order.orderType}" selected>代购</option>
                        </c:if>
                        <c:if test="${order.orderType == 2}">
                            <option value="${order.orderType}" selected>项目内</option>
                        </c:if>
                        <c:if test="${order.orderType == 3}">
                            <option value="${order.orderType}" selected>赠送</option>
                        </c:if>
                        <c:if test="${order.orderType == 4}">
                            <option value="${order.orderType}" selected>提前发货</option>
                        </c:if>
                        <c:if test="${order.orderType == 5}">
                            <option value="${order.orderType}" selected>合同已包含</option>
                        </c:if>
                        <c:if test="${order.orderType == 6}">
                            <option value="${order.orderType}" selected>先货后款</option>
                        </c:if>
                    </select>
                </div>
                <div class="clearfloat"></div>
            </div>
        </c:if>

        <div class="tdiv_a_z">
            <div class="tdiv_a_1" >订购地区：</div>
            <div class="tdiv_a_3">
                <select style="width:150px;background-color: #e6e6e6" disabled id="areaProvice" name="areaProvice">
                    <option value="${province.id}" selected>${province.name}</option>
                </select>
            </div>
            <div class="tdiv_a_3">
                <select style="width:150px;background-color: #e6e6e6" disabled id="areaCity" name="areaCity">
                    <option value="${city.id}" selected>${city.name}</option>
                </select>
            </div>
        </div>
        <div class="clearfloat"></div>

        <div class="tdiv_a_z">
            <div class="tdiv_a_1">订购单位全称：</div>
            <div class="tdiv_a_3" style="width: 400px;">
                <input type="text" style="width: 350px" disabled name="orderUnitName"   value="${order.orderUnitName}">
            </div>
        </div>
        <div class="tdiv_a_z">
            <div class="tdiv_a_1">订购单位地址：</div>
            <div class="tdiv_a_3" style="width: 400px;">
                <input type="text" style="width: 350px" disabled name="orderUnitAddress" id="orderUnitAddress" value="${order.orderUnitAddress}" >
            </div>
        </div>
        <div class="clearfloat"></div>
        <div class="tdiv_a_z">
            <div class="tdiv_a_1">订购联系人：</div>
            <div class="tdiv_a_3"><input type="text" disabled name="purchaser" id="purchaser" value="${order.purchaser}"></div>
        </div>
        <div class="clearfloat"></div>
        <div class="tdiv_a_z">
            <div class="tdiv_a_1">手机号码：</div>
            <div class="tdiv_a_3"><input type="text" disabled name="purchaserMobile" id="purchaserMobile" value="${order.purchaserMobile}"></div>
            <div class="tdiv_a_1">电话号码：</div>
            <div class="tdiv_a_3"><input type="text" disabled name="purchaserPhone" id="purchaserPhone" value="${order.purchaserPhone}"></div>
        </div>
        <div class="clearfloat"></div>
        <div class="tdiv_bottom"></div>
    </div>
</div>



<div class="tdiv" >
    <div class="tdiv_title">
        <i class="menu-i"></i>
        <p class="unit">收件地址</p>
    </div>
    <div>
        <div class="tdiv_a_z">
            <div  class="tdiv_a_1">收货单位：</div>
            <div class="tdiv_a_3">
                <span id="receiveUnitName">${order.receiveUnitName}</span>
            </div>
            <div class="tdiv_a_1">收货地址：</div>
            <div class="tdiv_a_3">
                <span id="receiveUnitAddress">${order.receiveUnitAddress}</span>
            </div>
            <div class="clearfloat"></div>
            <div class="tdiv_a_1">收货联系人：</div>
            <div class="tdiv_a_3">
                <span id="receiveName">${order.receiveName}</span>
            </div>
            <div class="tdiv_a_1">联系方式：</div>
            <div class="tdiv_a_3">
                <span id="receivePhone">${order.receiveMobile}/${order.receivePhone}</span>
            </div>
            <div class="clearfloat"></div>
            <div class="tdiv_a_1">备选收货联系人：</div>
            <div class="tdiv_a_3">
                <span id="standbyName">${order.standbyName}</span>
            </div>
            <div class="tdiv_a_1">联系方式：</div>
            <div class="tdiv_a_3">
                <span id="standbyPhone">${order.standbyMobile}/${order.standbyPhone}</span>
            </div>
            <div class="clearfloat"></div>
        </div>
        <div class="clearfloat"></div>
        <div class="tdiv_bottom"></div>
    </div>
</div>


<div class="tdiv">

    <div class="tdiv_title">
        <i class="menu-i"></i>
        <p class="unit">订购的产品</p>
    </div>
    <div>
    <div class="tableStyle neir_1_kcgl neir_2_kcgl_bod2">
    <table width="100%" class="table divExpress" id="tableProductList" border="0" cellpadding="0" cellspacing="0">
        <%--<thead class="thead">--%>
        <tr>
            <th class="table_td th">产品名称</th>
            <th class="table_td th">产品型号</th>
            <th class="table_td th">数量(支)</th>
            <th class="table_td th">单价(元)</th>
            <th class="table_td th">金额(元)</th>
            <th class="table_td th">编号</th>
            <th class="table_td th">快递单号</th>
        </tr>
        <%--</thead>--%>

    </table>
    <div class="tdiv_a_z">
        <div class="tdiv_a_3 tdiv_bottom" style="margin-left: 10px;"><div class="warn"></div><strong>注：证书产品10支起售，保质期：1年</strong></div>
    </div>
    <div class="clearfloat"></div>
    <div class="tdiv_bottom"></div>
</div>
</div>
</div>

<div class="tdiv" >

    <div class="tdiv_title">
        <i class="menu-i"></i>
        <p class="unit">开票信息</p>
    </div>
    <div>

        <div class="tableStyle neir_1_kcgl neir_2_kcgl_bod2">
        <c:if test="${sessionScope.gaUser.userType!=0}">
            <div class="tdiv_a_z">
                <div class="tdiv_a_1"></div>
                <div class="tdiv_a_3"><div class="left_tubiao warn"></div><strong>提醒：上传的文件名为该订单流水号。</strong></div>
            </div>
        </c:if>
        <div class="tdiv_a_z" style="margin-bottom: 20px">
            <div class="tdiv_a_1">
                电子发票：
            </div>
            <div class="tdiv_a_2" style="width: 580px;">
                <form id="uploadInvoiceForm" enctype="multipart/form-data" method="post">
                    <input name="orderId" type="hidden" value="${order.id}"/>
                    <c:if test="${sessionScope.gaUser.userType!=0}">
                        <span>
                            <input type="text"  name="fileName" id = "fileName" style="height: 33px;width: 280px" value="${electronicInvoice.fileName}"  class = "form-control viewfile"/>
                        </span>
                        <label  class="btn btn-info " style="margin-left: 5px;">选择文件</label>
                        <label  class="btn btn-info " style="margin-left: 10px;margin-right:10px;" onclick="_uploadInvoiceFile()">上传</label>
                    </c:if>
                    <c:if test="${not empty electronicInvoice.fileName}">
                        <label class="btn btn-danger downLoadInvoice"  onclick="_downLoadInvoiceFile()">下载电子发票</label>
                    </c:if>
                    <c:if test="${sessionScope.gaUser.userType!=0}">
                        <input type="file" id="zipFile" name="zipFile" style="margin-left: -1%"  onchange="document.getElementById('fileName').value=this.value;" class="file">
                    </c:if>
                </form>
            </div>
        </div>
            
		<div class="tdiv_a_z" style="margin-top: -15px;margin-bottom: 20px;">
             <div class="tdiv_a_1">
                	 发票快递单号：
             </div>
             <c:if test="${not empty invoiceExpress}">
	             <div class="tdiv_a_2">
	                 <a style="cursor: pointer;line-height: 26px;" onclick="_getOrderInvoiceExpress('${invoiceExpress.expressName},${invoiceExpress.expressNo},${invoiceExpress.expressMessage},${invoiceExpress.deliveryDate}')">${invoiceExpress.expressNo}</a>
	             </div>
             </c:if>
             <c:if test="${empty invoiceExpress && sessionScope.gaUser.userType==1}">
	             <div class="tdiv_a_2">
	                 <label class="btn btn-info" style="margin-left: 5px;" onclick="_getOrderInvoiceExpressPost()">新增</label>
	             </div>
             </c:if>
		</div>
		
        <table width="100%" class="table" id="tableInvoiceProductList" border="0" cellpadding="0" cellspacing="0">
            <%--<thead class="thead">--%>
            <tr>
                <th class="table_td th">单位名称</th>
                <th class="table_td th">产品名称</th>
                <th class="table_td th">产品型号</th>
                <th class="table_td th">数量</th>
                <th class="table_td th">金额(元)</th>
                <th class="table_td th">税号及其它信息</th>
            </tr>
            <%--</thead>--%>
            <tbody class = "tbody">
            <c:forEach items="${orderInvoiceVos}" var="orderInvoiceVo">
                <tr>
                    <input type="hidden" class="id" value="${orderInvoiceVo.orderInvoiceId}">
                    <input type="hidden" class="pm" value="${orderInvoiceVo.productName}">
                    <td class="table_td merge">${orderInvoiceVo.invoiceUnitName}</td>
                    <td class="table_td ">${orderInvoiceVo.productName}</td>
                    <td class="table_td">${orderInvoiceVo.productType}</td>
                    <td class="table_td">${orderInvoiceVo.productNumber}</td>
                    <td class="table_td">${orderInvoiceVo.productAmount}</td>
                    <td class="table_td mergeInvoice">

                        <c:if test="${orderInvoiceVo.socialCreditCode!=''}">
                            税号：${orderInvoiceVo.socialCreditCode}<br/>
                        </c:if>
                        <c:if test="${orderInvoiceVo.address!=''|| orderInvoiceVo.phone!=''}">
                            地址、电话： ${orderInvoiceVo.address}${orderInvoiceVo.phone}<br/>
                        </c:if>
                        <c:if test="${orderInvoiceVo.depositBank!='' || orderInvoiceVo.account!=''}">
                            开户行及账号：${orderInvoiceVo.depositBank}${orderInvoiceVo.account}
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <div class="clearfloat"></div>
        <div class="tdiv_a_z">
            <div class="tdiv_a_1">开票信息：</div>

            <c:choose>
                <c:when test="${order.invoiceType==0}">
                    <div class="tdiv_a_3">增值税专用发票</div>
                </c:when>
                <c:otherwise>
                    <div class="tdiv_a_3">增值税普通发票</div>
                </c:otherwise>
            </c:choose>

        </div>
        <div class="clearfloat"></div>
        <div class="tdiv_a_z">
            <div class="tdiv_a_1">汇款类型：</div>
            <c:choose>
                <c:when test="${order.remitterType==0}">
                    <div class="tdiv_a_3"><input type="radio"  name="remitterType" disabled value="0" checked/>单位</div>
                    <div class="clearfloat"></div>
                    <div class="tdiv_a_z">
                        <div class="tdiv_a_1" id="remitterName">汇款单位名称：</div>
                        <div class="tdiv_a_2">
                            <input type="text" disabled value="${order.remitter}" style="width: 350px;"/>
                        </div>
                    </div>
                </c:when>
                <c:otherwise>
                    <div class="tdiv_a_3">个人</div>
                    <div class="tdiv_a_z">
                        <div class="tdiv_a_1" id="remitterName">汇款人姓名：</div>
                        <div class="tdiv_a_2">
                            <input type="text"  disabled value="${order.remitter}" style="width: 350px;"/>
                        </div>
                    </div>
                </c:otherwise>
            </c:choose>
        </div>

        <div class="clearfloat"></div>
        <div class="tdiv_a_z">
            <div class="tdiv_a_1">订购日期：</div>
            <div class="tdiv_a_3">
                <input type="text" id="orderDate" name="orderDate" disabled value="<fmt:formatDate value="${order.orderDate}" pattern="yyyy-MM-dd"/>" />
            </div>
        </div>
        <div class="clearfloat"></div>
        <div class="tdiv_a_z">
            <div class="tdiv_a_1">备注：</div>
            <div class="tdiv_a_2">
                <textarea  id="remark" name="remark" disabled style="margin: 0px; width: 572px; height: 75px;">${order.remark}</textarea>
            </div>
        </div>
    <div class="clearfloat"></div>
    <div class="tdiv_bottom"></div>
    </div>
    </div>
</div>
<div class="clearfloat"></div>
<c:if test="${sessionScope.gaUser.userType==1}">
<div class="tdiv" >
    <div class="tdiv_title">
        <i class="menu-i"></i>
        <p class="unit">开票方式</p>
    </div>
    <div>
        <div class="tdiv_a_z">
            <div class="tdiv_a_1">开票方式：</div>
            <c:choose>
                <c:when test="${order.needBeforeInvoice ==1}">
                    <div class="tdiv_a_2" name="needBeforeInvoice">先开票</div>
                </c:when>
                <c:when test="${order.needBeforeInvoice ==0}">
                    <div class="tdiv_a_2" name="needBeforeInvoice">后开票</div>
                </c:when>
                <c:when test="${order.needBeforeInvoice ==2}">
                    <div class="tdiv_a_2" name="needBeforeInvoice">不开票</div>
                </c:when>
                <c:otherwise>
                    <div class="tdiv_a_2" name="needBeforeInvoice"></div>
                </c:otherwise>
            </c:choose>
            <div class="clearfloat"></div>
        </div>
        <div class="tdiv_a_z">
            <div class="tdiv_a_1">开票日期：</div>
            <div class="tdiv_a_2" name="billingDate"><fmt:formatDate value="${order.billingDate}" pattern="yyyy-MM-dd"/></div>
            <div class="clearfloat"></div>
        </div>
        <div class="clearfloat"></div>
        <div class="tdiv_bottom"></div>
    </div>
</div>
</c:if>
    <c:if test="${sessionScope.gaUser.userType==1}">
    <div class="tdiv" >
    <div class="tdiv_title">
        <i class="menu-i"></i>
        <p class="unit">到款记录</p>
    </div>
        <div>
    <div class="neir_1_kcgl neir_2_kcgl_bod2">
        <table width="100%" class="table" border="0" cellpadding="0" cellspacing="0">
            <%--<thead class="thead">--%>
            <tr>
                <th class="table_td th">到款日期</th>
                <th class="table_td th">到款账户</th>
                <th class="table_td th">到款金额</th>
                <th class="table_td th">汇款单位/汇款人</th>
                <th class="table_td th">是否全部到款</th>
                <th class="table_td th">备注</th>
                <th class="table_td th">操作</th>
            </tr>
            <%--</thead>--%>
            <tbody class = "tbody paidTbody">

            </tbody>
        </table>

    </div>
    <div class="clear_float"></div>
    <div class="tdiv_bottom"></div>
    </div>
</div>
</c:if>
    <%--<div class="tdiv" >--%>
    <%--<div class="tdiv_title">--%>
        <%--<i class="menu-i"></i>--%>
        <%--<p class="unit">快递信息</p>--%>
    <%--</div>--%>
        <%--<div>--%>
    <%--<div class="neir_1_kcgl neir_2_kcgl_bod2">--%>


        <%--<table width="100%" class="table" border="0" cellpadding="0" cellspacing="0">--%>
            <%--&lt;%&ndash;<thead class="thead">&ndash;%&gt;--%>
            <%--<tr>--%>
                <%--<th class="table_td th">品名</th>--%>
                <%--<th class="table_td th">快递公司</th>--%>
                <%--<th class="table_td th">快递单号</th>--%>
                <%--<th class="table_td th">快递发货日期</th>--%>
                <%--<th class="table_td th">操作</th>--%>

            <%--</tr>--%>
            <%--&lt;%&ndash;</thead>&ndash;%&gt;--%>
            <%--<tbody class = "tbody divExpress">--%>

            <%--</tbody>--%>
        <%--</table>--%>
        <%--<div class="clearfloat"></div>--%>
    <%--</div>--%>
            <%--</div>--%>
<%--</div>--%>
    <div class="clearfloat"></div>

    <%--卖家操作区--%>
    <c:if test="${sessionScope.gaUser.userType==1}">
    <div class="tdiv" >
        <div class="tdiv_title">
            <i class="menu-i"></i>
            <p class="unit">操作区</p>
        </div>
        <div>
            <ul class="nav1 nav-tabs" role="tablist">
                <li role="presentation" class="active"><a href="#check" class="btn" aria-controls="check"  role="tab" data-toggle="tab">信息登记</a></li>
                <li role="presentation" ><a href="#message" onclick="_putOrderMessageWarn('${order.id}')" class="btn"  role="tab" data-toggle="tab">留言区</a></li>
                <li role="presentation" ><a href="#memoModal"  class="btn"  role="tab" data-toggle="tab">备忘区</a></li>
                <li role="presentation" ><a href="#productionMemo" class="btn"  role="tab" data-toggle="tab">生产注意事项</a></li>
                <li role="presentation" ><a href="#histroy" class="btn"  role="tab" data-toggle="tab">操作记录</a></li>
            </ul>
            <%--<li class="active">--%>
                <%--<a href="#rylb" class="btn rylb" aria-controls="rylb" role="tab" data-toggle="tab">信息登记</a>--%>
            <%--</li>--%>
            <%--<li><a href="#ryhz" class="btn ryhz" aria-controls="ryhz" role="tab" data-toggle="tab">留言区</a></li>--%>
        <div class="clearfloat"></div>
        <%--信息登记区--%>
        <div class="tab-content">
            <%--登记--%>
            <div role="tabpanel" class="tab-pane active" id = "check" style="background-color: white;">
                    <div style="    float: left;width: 50%;margin: 10px 3% 4px 0;">
                        <div class="tdiv_a_z">
                            <div class="tdiv_a_1">开票方式：</div>
                            <div class="tdiv_a_3">
                                <select style="padding: 2px;" class="form-control classData"  id="needBeforeInvoice">
                                    <option value="1" selected>先开票</option>
                                    <option value="0" >后开票</option>
                                    <option value="2" >不开票</option>
                                </select>
                            </div>
                            <div class="tdiv_a_4"><input type="button" class="btn btn-info" onclick="_putOrderNeedBeforeInvoice()" value="提交"/></div>
                        </div>
                        <div class="clear_float"></div>
                        <div class="tdiv_a_z" style="margin-top: 16px;">
                            <div class="tdiv_a_1">开票日期：</div>
                            <div class="tdiv_a_3">
                                <input type="text" class="form-control classData" id="billingDate"  onclick="WdatePicker()"/>
                            </div>
                            <div class="tdiv_a_4"><input type="button" class="btn btn-info" onclick="_putOrderbillingDate()" value="提交"/></div>
                        </div>
                        <div class="clear_float"></div>
                    </div>


                <div  style="    float: left;width: 1px;height: 77px;background-color: #e2e2e2;margin: 14px 3% 4px 0;"></div>
                <div style="    float: left;margin: 10px 3% 4px 0;">
                    <div class="tdiv_a_z">
                        <input type="button" class="btn btn-info" width="30px" data-toggle="modal" data-target="#orderPaidModal" value="登记到款信息"/>
                    </div>
                    <%--<div class="tdiv_a_z">--%>
                        <%--<input type="button" class="btn btn-info" data-toggle="modal" data-target="#orderExpressModal" value="登记快递信息"/>--%>
                    <%--</div>--%>
                </div>
                <div class="clearfloat"></div>
                <div class="tdiv_bottom"></div>

                <script type="text/javascript">
                    $(function () {
                        //到款信息
                        parserAsyncClassDataFormData4();

                    })
                    //提交开票方式
                    function _putOrderNeedBeforeInvoice() {
                        var orderId = "${orderId}";
                        var needBeforeInvoice = $("#needBeforeInvoice").val();
                        openLoading();
                        $.ajax({
                            url: "<%=path%>/commonCompanyOrderQuery/putOrder.do",
                            type: "POST",
                            data:{"id":orderId,"needBeforeInvoice":needBeforeInvoice},
                            success: function(data){
                                if(data.result=="SUCCESS"){
                                    alert("提交成功！");
                                    closeLoading();
                                    $("div[name=needBeforeInvoice]").text(needBeforeInvoice=="1"?"先开票":needBeforeInvoice=="0"?"后开票":needBeforeInvoice=="2"?"不开票":"");
                                }else{
                                    alert(data.errorMsg);
                                    closeLoading();
                                }
                            }
                        });
                    }
                    //提交开票日期
                    function _putOrderbillingDate() {
                        var orderId = "${orderId}";
                        var billingDate = $("#billingDate").val();
                        if(billingDate==""){
                            alert("请选择日期。");
                            return false;
                        }
                        openLoading();
                        $.ajax({
                            url: "<%=path%>/commonCompanyOrderQuery/putOrder.do",
                            type: "POST",
                            data:{"id":orderId,"billingDate":billingDate},
                            success: function(data){
                                if(data.result=="SUCCESS"){
                                    alert("提交成功！");
                                    closeLoading();
                                    $("div[name=billingDate]").text(billingDate);
                                }else{
                                    alert(data.errorMsg);
                                    closeLoading();
                                }
                            }
                        });

                    }


                    function _postOrderPaid() {
                        var priceRe = /^\d*\.{0,1}\d*$/;
                        if($("input[name=arrivalDate]").val()==""){
                            alert("请填写到款日期。");
                            return false;
                        }else if(!priceRe.test($("input[name=arrivalAmount]").val())||$("input[name=arrivalAmount]").val()==""){
                            alert("到款金额格式错误。");
                            return false;
                        }else if($("input[name=remitter]").val()==""){
                            alert("请填写汇款单位/汇款人信息。");
                            return false;
                        }
                        if ($("input[name=isTotalArrival]:checked").val()==1) {
                        	var oldSum = Number($("#arrivedSum").html());
                        	var lastNum =  Number($("input[name=arrivalAmount]").val());
                        	if (${order.productTotalAmount}!=(oldSum+lastNum)) {
                        		alert("全部到款金额与订单总计不符！");
                        		return false;
                        	}
                        }
                        openLoading();
                        $.ajax({
                            url: "<%=path%>/commonCompanyOrderQuery/postOrderPaid.do",
                            type: "POST",
                            data:$("#checkForm").serialize(),
                            success: function(data){
                                if(data.result=="SUCCESS"){
                                    alert("提交成功！");
                                    closeLoading();
                                    parserAsyncClassDataFormData4();
                                    window.location.reload();
                                }else{
                                    alert(data.errorMsg);
                                    closeLoading();
                                }
                            }
                        });
                    }
                    
                    function  parserAsyncClassDataFormData4(){
                        var json = {OrderPaid: {}};
                        json.OrderPaid["orderId"] = $("#orderId").val();
                        $.ajax({
                            url: "<%=path%>/commonCompanyOrderQuery/showOrderPaidList.do",
                            type: "POST",
                            data: json.OrderPaid,
                            success: function(datas){
                                classDataViewAdapter4(datas);  //数据
                                closeLoading();
                            }
                        })
                    }

                    function classDataViewAdapter4(datas) {
                        var view = $(".paidTbody");
                        view.empty();
                        var i = 0;
                        var param = "";
                        var arrivedSum = 0;
                        for (var data in datas) {
                            var item = datas[data];
                            param += Objects.format("<tr>");
                            param += Objects.format("<td class=\"table_td\">{0}</td>",getLocalTimeYYYYMMDD(item.arrivalDate));

                            param += Objects.format("<td class=\"table_td\">{0}</td>",item.arrivalAccount==0?"辰锐":item.arrivalAccount==1?"三所":"");
                            param += Objects.format("<td class=\"table_td\">{0}</td>",item.arrivalAmount);
                            arrivedSum += item.arrivalAmount;
                            param += Objects.format("<td class=\"table_td\">{0}</td>",item.remitter);
                            param += Objects.format("<td class=\"table_td\">{0}</td>",item.isTotalArrival==0?"否":"是");
                            param += Objects.format("<td class=\"table_td\">{0}</td>",item.arrivalMessage);
                            param += Objects.format("<td class=\"table_td\"><input type=\"button\" class=\"btn btn-info\" onclick=\"_getOrderPaid('{0}')\" value=\"编辑\"/></td>",
                                    item.id
                                    +","+getLocalTimeYYYYMMDD(item.arrivalDate)
                                    +","+item.arrivalAccount
                                    +","+item.arrivalAmount
                                    +","+item.remitter
                                    +","+item.isTotalArrival
                                    +","+item.arrivalMessage);
                            param += Objects.format("</tr>");
                        }
                        param += Objects.format("<tr><td class='table_td'>总到款</td><td class='table_td'></td>");
                        param += Objects.format("<td id ='arrivedSum' class='table_td'>"+arrivedSum+"</td>");
                        param += Objects.format("<td class='table_td'></td><td class='table_td'></td><td class='table_td'></td><td class='table_td'></td></tr>");
                        view.append(param);
                        tableAddClass();
                    }
                    //获取到款信息回调模态框
                    function _getOrderPaid(data) {
                        $("#orderPaidId").val(data.split(',')[0]);
                        $("input[name=arrivalDate]").val(data.split(',')[1]);
                        var arrivalAccount = data.split(',')[2];
                        $("select[name=arrivalAccount]").val(arrivalAccount);
                        $("input[name=arrivalAmount]").val(data.split(',')[3]);
                        $("input[name=remitter]").val(data.split(',')[4]);
                        var isTotalArrival = data.split(',')[5];
                        $("input[name=isTotalArrival][value='"+isTotalArrival+"']").prop("checked",true);
                        $("textarea[name=arrivalMessage]").val(data.split(',')[6]);
                        $("#orderPaidButtion").attr("onclick","_editOrderPaid()").val("保存");
                        $("#orderPaidModal").modal('show');
                    }
                    //模态框取消 按键重置
                    $("#orderPaidModal").on('hide.bs.modal',function (event) {
                        $("#orderPaidId").val("");
                        $("#orderPaidButtion").attr("onclick","_postOrderPaid()").val("提交");
                    })
                    //编辑保存到款信息
                    function _editOrderPaid() {
                        var priceRe = /^\d*\.{0,1}\d*$/;
                        //var priceRe = /^\d*\.{0,1}\d{0,1}$/;
                        if($("input[name=arrivalDate]").val()==""){
                            alert("请填写到款日期。");
                            return false;
                        }else if(!priceRe.test($("input[name=arrivalAmount]").val())||$("input[name=arrivalAmount]").val()==""){
                            alert("到款金额格式错误。");
                            return false;
                        }else if($("input[name=remitter]").val()==""){
                            alert("请填写汇款单位/汇款人信息。");
                            return false;
                        }
                        if ($("input[name=isTotalArrival]").val()==1) {
                        	var oldSum = Number($("#arrivedSum").html());
                        	var lastNum =  Number($("input[name=arrivalAmount]").val());
                        	if (${order.productTotalAmount}!=(oldSum+lastNum)) {
                        		alert("全部到款金额与订单总计不符！");
                        		return false;
                        	}
                        }
                        openLoading();
                        $.ajax({
                            url: "<%=path%>/commonCompanyOrderQuery/editOrderPaid.do",
                            type: "POST",
                            data:$("#checkForm").serialize(),
                            success: function(data){
                                if(data.result=="SUCCESS"){
                                    alert("提交成功！");
                                    closeLoading();
                                    parserAsyncClassDataFormData4();
                                    window.location.reload();
                                }else{
                                    alert(data.errorMsg);
                                    closeLoading();
                                }
                            }
                        });
                    }

                </script>
            </div>
            <%--留言--%>
            <div role="tabpanel" class="tab-pane" id = "message" style="background-color: white">
                <div class="tdiv_a_z">
                    <div class="tdiv_a_1"></div>
                    <div class="tdiv_a_2"> <textarea  id="message1" name="message1" style="margin: 0px; width: 572px; height: 75px;"></textarea></div>
                    <div class="tdiv_a_3"><input type="button" class="btn btn-info" onclick="_postOrderMessage1()" value="提交" style="margin-top: 11%;margin-left: 25%;"/></div>
                </div>
                <div class="clearfloat"></div>
                <div class="tdiv_bottom"></div>
                <div class="tdiv" >
                    <div class="tdiv_title">
                        <i class="menu-i"></i>
                        <p class="unit">历史留言</p>
                    </div>
                    <div>
                    <div class="neir_1_kcgl neir_2_kcgl_bod2">
                        <table width="100%" class="table" id="tableMessageList" border="0" cellpadding="0" cellspacing="0">
                            <%--<thead class="thead">--%>
                            <tr>
                                <th class="table_td th" width="20%">提交日期</th>
                                <th class="table_td th">留言内容</th>
                                <th class="table_td th" width="20%">留言人</th>
                            </tr>
                            <%--</thead>--%>
                            <tbody class = "tbody messageTbody">

                            </tbody>
                        </table>
                    </div>
                        <div class="control-group nav-fenye">
                            <div class="pull-left nav-fenye"><span id="pageSizeInfo1"></span>
                                <select id="selectPageSizeId1" class="nopad" style="width: 45px" onchange="selectPageSize1(this)">
                                    <option selected="selected" value="5">5</option>
                                    <option value="10">10</option>
                                    <option  value="20">20</option>
                                </select> 条
                            </div>
                            <%--<nav aria-label="Page navigation" class="pull-right">--%>
                                <%--<div id="pagination1">--%>
                                    <%--<ul class="pagination">--%>
                                    <%--</ul>--%>
                                <%--</div>--%>
                            <%--</nav>--%>
                            <div class="row-paginate">
                                <ul id="pagination1" class="pagination">
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
                <script type="text/javascript">
                    var currentPageIndex = 1;
                    var limit;
                    var dataCount = 0;
                    var pageSize = 5;

                    $(function () {
                        //分页查询
//                        $("body").on("click", ".pagination li.item a", itemOfpageViewOnSelected1);
                        $("body").on("click", ".pagination li a", itemOfpageViewOnSelected1);

                        parserAsyncClassDataFormData1();
                    })
                    function itemOfpageViewOnSelected1() {
                        openLoading();
                        var p = $(this).attr("data");
                        currentPageIndex = parseInt(p);
                        computePaginationEx('1');
                        parserAsyncClassDataFormData1();
                    }
                    function selectPageSize1(data) {
                        var size = $("#selectPageSizeId1 option:selected").val();
                        pageSize = size;
                        currentPageIndex = 1;
                        parserAsyncClassDataFormData1();
                    }
                    function  parserAsyncClassDataFormData1(){
                        var json = {OrderMessageSearcher: {}};
                        json.OrderMessageSearcher["orderId"] = $("#orderId").val();
                        json.OrderMessageSearcher["pageCount"] = pageSize;
                        json.OrderMessageSearcher["offset"] = (currentPageIndex - 1) * pageSize;
                        $.ajax({
                            url: "<%=path%>/commonUserOrderQuery/showOrderMessagePage.do",
                            type: "POST",
                            data: json.OrderMessageSearcher,
                            success: function(datas){
                                closeLoading();
//                var datas = JSON.parse(data);
                                limit = datas.limit;
                                limit.total = datas.total;
                                if (datas.data) {
                                    dataCount = datas.data.length;
                                } else {
                                    dataCount = 0;
                                }
                                computePaginationEx('1'); //分页
                                classDataViewAdapter1(datas);  //数据
                                $("#pageSizeInfo1").html("总共"+datas.total+"条，每页显示");
                                closeLoading();

                            }
                        })
                    }
                    function classDataViewAdapter1(datas) {
                        var view = $(".messageTbody");
                        view.empty();
                        var i = 0;
                        var param = "";
                        for (var data in datas.data) {
                            var item = datas.data[data];
                            param += Objects.format("<tr>");
                            param += Objects.format("<td class=\"table_td\">{0}</td>",getLocalTime(item.dateCreated));
                            param += Objects.format("<td class=\"table_td\">{0}</td>",item.message);
                            if(item.userType=="0"){
                                param += Objects.format("<td class=\"table_td\">{0}</td>",item.userName);
                            }else if(item.userType=="1"){
                                param += Objects.format("<td class=\"table_td\">三所</td>");
                            }
                            param += Objects.format("</tr>");
                        }
                        view.append(param);
                        tableAddClass();
                    }
                    <%--function computePagination1() {--%>
                        <%--var pageView = $("#pagination1 ul.pagination");--%>
                        <%--pageView.empty();--%>
                        <%--var pageCount = limit.total % limit.count == 0 ? limit.total / limit.count : limit.total / limit.count + 1;--%>
                        <%--limit.pageCount = pageCount;--%>
                        <%--pageView.append(itemTemplateOfpageView("item", 1, "&laquo;", limit));--%>
                        <%--for (var i = 4; i > 0; i--) {--%>
                            <%--var p = currentPageIndex - i;--%>
                            <%--if (p > 0) {--%>
                                <%--pageView.append(itemTemplateOfpageView("item", p, p, limit));--%>
                            <%--}--%>
                        <%--}--%>
                        <%--pageView.append(itemTemplateOfpageView("item active", currentPageIndex, currentPageIndex, limit));--%>
                        <%--for (var i = 1; i < 5; i++) {--%>
                            <%--var p = currentPageIndex + i;--%>
                            <%--if (p < limit.pageCount) {--%>
                                <%--pageView.append(itemTemplateOfpageView("item", p, p, limit));--%>
                            <%--}--%>
                        <%--}--%>
                        <%--pageView.append(itemTemplateOfpageView("item", limit.pageCount, "&raquo;", limit));--%>
                        <%--pageView.append(Objects.format("<li class=\"disabled\"><a href=\"#\">{0}/{1}</a></li>", limit.offset + dataCount, limit.total));--%>
                    <%--}--%>
                    function itemTemplateOfpageView1(cls, pageValue, text, limit) {
                        if (pageValue < 0) {
                            pageValue = 0;
                        } else if (pageValue > limit.pageCount) {
                            pageValue = limit.pageCount;
                        }
                        return Objects.format("<li class=\"{0}\"><a data=\"{1}\">{2}</a></li>", cls, pageValue, text);
                    }
                    function _postOrderMessage1() {
                        var message = $("#message1").val();
                        var orderId = $("#orderId").val();
                        if(isNull(message)){
                            alert("请填写留言信息。");
                            return false;
                        }else if(orderId==""){
                            alert("请重新选择订单。");
                            return false;
                        }
                        openLoading();
                        $.ajax({
                            url: "<%=path%>/commonUserOrderQuery/postOrderMessage.do",
                            type: "POST",
                            data:{"orderId":orderId,"message":message},
                            success: function(data){
//                                data = JSON.parse(data);
                                if(data.result=="SUCCESS"){
                                    alert("提交成功！");
                                    closeLoading();
                                    $("#message").val("");
                                    parserAsyncClassDataFormData1();
                                    <%--location.href= "<%=path%>/file/downloadTemp.do?fileName="+data.errorMsg;--%>
                                }else{
                                    alert(data.errorMsg);
                                    closeLoading();
                                }
                            }
                        });
                    }

                </script>
            </div>
            <%--备忘--%>
            <div role="tabpanel" class="tab-pane" id = "memoModal" style="background-color: white">
                <div class="tdiv_a_z">
                    <div class="tdiv_a_1"></div>
                    <div class="tdiv_a_2"> <textarea  id="memo" name="memo" style="margin: 0px; width: 572px; height: 75px;"></textarea></div>
                    <div class="tdiv_a_3"><input type="button" class="btn btn-info" onclick="_postOrderMemo()" value="提交" style="margin-top: 11%;margin-left: 25%;"/></div>
                </div>
                <div class="clearfloat"></div>
                <div class="tdiv_bottom"></div>
                <div class="tdiv" >
                    <div class="tdiv_title">
                        <i class="menu-i"></i>
                        <p class="unit">历史备忘</p>
                    </div>
                    <div>
                    <div class="neir_1_kcgl neir_2_kcgl_bod2">
                        <table width="100%" class="table" id="tableMemoList" border="0" cellpadding="0" cellspacing="0">
                            <%--<thead class="thead">--%>
                            <tr>
                                <th class="table_td th" width="20%">记录日期</th>
                                <th class="table_td th">备忘内容</th>
                                <th class="table_td th" width="20%">记录人</th>
                            </tr>
                            <%--</thead>--%>
                            <tbody class = "tbody memoTbody">

                            </tbody>
                        </table>
                    </div>
                        <div class="clear_float"></div>
                        <div class="control-group nav-fenye">
                            <div class="pull-left nav-fenye"><span id="pageSizeInfo3"></span>
                                <select id="selectPageSizeId3" class="nopad" style="width: 45px" onchange="selectPageSize3(this)">
                                    <option selected="selected" value="5">5</option>
                                    <option value="10">10</option>
                                    <option  value="20">20</option>
                                </select> 条
                            </div>
                            <%--<nav aria-label="Page navigation" class="pull-right">--%>
                                <%--<div id="pagination3">--%>
                                    <%--<ul class="pagination">--%>
                                    <%--</ul>--%>
                                <%--</div>--%>
                            <%--</nav>--%>
                            <div class="row-paginate">
                                <ul id="pagination3" class="pagination">
                                </ul>
                            </div>
                        </div>
                    </div>
                    <div class="clear_float"></div>
                </div>
                <script type="text/javascript">
                    var currentPageIndex = 1;
                    var limit;
                    var dataCount = 0;
                    var pageSize = 5;
                    $(function () {
                        //分页查询
//                        $("body").on("click", ".pagination li.item a", itemOfpageViewOnSelected3);
                        $("body").on("click", ".pagination li a", itemOfpageViewOnSelected3);

                        parserAsyncClassDataFormData3();
                    })
                    function itemOfpageViewOnSelected3() {
                        openLoading();
                        var p = $(this).attr("data");
                        currentPageIndex = parseInt(p);
                        computePaginationEx('3');
                        parserAsyncClassDataFormData3();
                    }
                    function selectPageSize3(data) {
                        var size = $("#selectPageSizeId3 option:selected").val();
                        pageSize = size;
                        currentPageIndex = 1;
                        parserAsyncClassDataFormData3();
                    }
                    function  parserAsyncClassDataFormData3(){
                        var json = {OrderMessageSearcher: {}};
                        json.OrderMessageSearcher["orderId"] = $("#orderId").val();
                        json.OrderMessageSearcher["pageCount"] = pageSize;
                        json.OrderMessageSearcher["offset"] = (currentPageIndex - 1) * pageSize;
                        $.ajax({
                            url: "<%=path%>/commonCompanyOrderQuery/showOrderMemoPage.do",
                            type: "POST",
                            data: json.OrderMessageSearcher,
                            success: function(datas){
                                closeLoading();
//                var datas = JSON.parse(data);
                                limit = datas.limit;
                                limit.total = datas.total;
                                if (datas.data) {
                                    dataCount = datas.data.length;
                                } else {
                                    dataCount = 0;
                                }
                                computePaginationEx('3'); //分页
                                classDataViewAdapter3(datas);  //数据
                                $("#pageSizeInfo3").html("总共"+datas.total+"条，每页显示");
                                closeLoading();

                            }
                        })
                    }
                    function classDataViewAdapter3(datas) {
                        var view = $(".memoTbody");
                        view.empty();
                        var i = 0;
                        var param = "";
                        for (var data in datas.data) {
                            var item = datas.data[data];
                            param += Objects.format("<tr>");
                            param += Objects.format("<td class=\"table_td\">{0}</td>",getLocalTime(item.dateCreated));
                            param += Objects.format("<td class=\"table_td\">{0}</td>",item.memo);
                            param += Objects.format("<td class=\"table_td\">{0}</td>",item.userName);
                            param += Objects.format("</tr>");
                        }
                        view.append(param);
                        tableAddClass();
                    }
                    <%--function computePagination3() {--%>
                        <%--var pageView = $("#pagination3 ul.pagination");--%>
                        <%--pageView.empty();--%>
                        <%--var pageCount = limit.total % limit.count == 0 ? limit.total / limit.count : limit.total / limit.count + 1;--%>
                        <%--limit.pageCount = pageCount;--%>
                        <%--pageView.append(itemTemplateOfpageView("item", 1, "&laquo;", limit));--%>
                        <%--for (var i = 4; i > 0; i--) {--%>
                            <%--var p = currentPageIndex - i;--%>
                            <%--if (p > 0) {--%>
                                <%--pageView.append(itemTemplateOfpageView("item", p, p, limit));--%>
                            <%--}--%>
                        <%--}--%>
                        <%--pageView.append(itemTemplateOfpageView("item active", currentPageIndex, currentPageIndex, limit));--%>
                        <%--for (var i = 1; i < 5; i++) {--%>
                            <%--var p = currentPageIndex + i;--%>
                            <%--if (p < limit.pageCount) {--%>
                                <%--pageView.append(itemTemplateOfpageView("item", p, p, limit));--%>
                            <%--}--%>
                        <%--}--%>
                        <%--pageView.append(itemTemplateOfpageView("item", limit.pageCount, "&raquo;", limit));--%>
                        <%--pageView.append(Objects.format("<li class=\"disabled\"><a href=\"#\">{0}/{1}</a></li>", limit.offset + dataCount, limit.total));--%>
                    <%--}--%>
                    function itemTemplateOfpageView3(cls, pageValue, text, limit) {
                        if (pageValue < 0) {
                            pageValue = 0;
                        } else if (pageValue > limit.pageCount) {
                            pageValue = limit.pageCount;
                        }
                        return Objects.format("<li class=\"{0}\"><a data=\"{1}\">{2}</a></li>", cls, pageValue, text);
                    }
                    function _postOrderMemo() {
                        var memo = $("#memo").val();
                        var orderId = $("#orderId").val();
                        if(memo==""){
                            alert("请填写备忘信息。");
                            return false;
                        }else if(orderId==""){
                            alert("请重新选择订单。");
                            return false;
                        }
                        openLoading();
                        $.ajax({
                            url: "<%=path%>/commonCompanyOrderQuery/postOrderMemo.do",
                            type: "POST",
                            data:{"orderId":orderId,"memo":memo},
                            success: function(data){
                                data = JSON.parse(data);
                                if(data.result=="SUCCESS"){
                                    alert("提交成功！");
                                    closeLoading();
                                    $("#message").val("");
                                    parserAsyncClassDataFormData3();
                                    <%--location.href= "<%=path%>/file/downloadTemp.do?fileName="+data.errorMsg;--%>
                                }else{
                                    alert(data.errorMsg);
                                    closeLoading();
                                }
                            }
                        });
                    }

                </script>
            </div>

            <%--生产注意事项--%>
            <div role="tabpanel" class="tab-pane" id = "productionMemo" style="background-color: white">
                <div class="tdiv_a_z">
                    <div class="tdiv_a_1"></div>
                    <div class="tdiv_a_2"> <textarea  id="orderProductionMemo" name="memo" style="margin: 0px; width: 572px; height: 75px;">${productionMemo.memo}</textarea></div>
                    <div class="tdiv_a_3"><input type="button" class="btn btn-info" onclick="_postOrderProductionMemo()" value="提交" style="margin-top: 11%;margin-left: 25%;"/></div>
                </div>
                <div class="clearfloat"></div>
                <div class="tdiv_bottom"></div>

                <script type="text/javascript">

                    function _postOrderProductionMemo() {
                        var productionMemo = $("#orderProductionMemo").val();
                        var orderId = $("#orderId").val();
                        if(productionMemo==""){
                            alert("请填写备忘信息。");
                            return false;
                        }else if(orderId==""){
                            alert("请重新选择订单。");
                            return false;
                        }
                        openLoading();
                        $.ajax({
                            url: "<%=path%>/commonCompanyOrderQuery/postOrderProductionMemo.do",
                            type: "POST",
                            data:{"orderId":orderId,"productionMemo":productionMemo},
                            success: function(data){
                                data = JSON.parse(data);
                                if(data.result=="SUCCESS"){
                                    alert("提交成功！");
                                    closeLoading();
                                }else{
                                    alert(data.errorMsg);
                                    closeLoading();
                                }
                            }
                        });
                    }

                </script>
            </div>
            <%--历史流转记录--%>
            <div role="tabpanel" class="tab-pane" id = "histroy" style="background-color: white">
                <div class="tdiv" >
                    <div class="tdiv_title">
                        <i class="menu-i"></i>
                        <p class="unit">操作记录</p>
                    </div>
                    <div>
                    <div class="neir_1_kcgl neir_2_kcgl_bod2">
                        <table width="100%" class="table" id="tableHistroyList" border="0" cellpadding="0" cellspacing="0">
                            <%--<thead class="thead">--%>
                            <tr>
                                <th class="table_td th" width="20%">操作日期</th>
                                <th class="table_td th">操作内容</th>
                                <th class="table_td th" width="20%">操作人员</th>
                            </tr>
                            <%--</thead>--%>
                            <tbody class = "tbody historyTbody">

                            </tbody>
                        </table>
                    </div>

                        <div class="control-group nav-fenye">
                            <div class="pull-left nav-fenye"><span id="pageSizeInfo2"></span>
                                <select id="selectPageSizeId2" class="nopad" style="width: 45px" onchange="selectPageSize2(this)">
                                    <option selected="selected" value="5">5</option>
                                    <option value="10">10</option>
                                    <option  value="20">20</option>
                                </select> 条
                            </div>
                            <div class="clear_float"></div>
                            <%--<nav aria-label="Page navigation" class="pull-right">--%>
                                <%--<div id="pagination2">--%>
                                    <%--<ul class="pagination">--%>
                                    <%--</ul>--%>
                                <%--</div>--%>
                            <%--</nav>--%>
                            <div class="row-paginate">
                                <ul id="pagination2" class="pagination">
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
                <script type="text/javascript">
                    var currentPageIndex = 1;
                    var limit;
                    var dataCount = 0;
                    var pageSize = 5;
                    $(function () {
                        //分页查询
//                        $("body").on("click", ".pagination li.item a", itemOfpageViewOnSelected2);
                        $("body").on("click", ".pagination li a", itemOfpageViewOnSelected2);
                        parserAsyncClassDataFormData2();
                    })
                    function itemOfpageViewOnSelected2() {
                        openLoading();
                        var p = $(this).attr("data");
                        currentPageIndex = parseInt(p);
                        computePaginationEx('2');
                        parserAsyncClassDataFormData2();
                    }
                    function selectPageSize2(data) {
                        var size = $("#selectPageSizeId2 option:selected").val();
                        pageSize = size;
                        currentPageIndex = 1;
                        parserAsyncClassDataFormData2();
                    }
                    function  parserAsyncClassDataFormData2(){
                        var json = {OrderHistroySearcher: {}};
                        json.OrderHistroySearcher["orderId"] = $("#orderId").val();
                        json.OrderHistroySearcher["pageCount"] = pageSize;
                        json.OrderHistroySearcher["offset"] = (currentPageIndex - 1) * pageSize;
                        $.ajax({
                            url: "<%=path%>/commonCompanyOrderQuery/showOrderHistoryStatusPage.do",
                            type: "POST",
                            data: json.OrderHistroySearcher,
                            success: function(datas){
                                closeLoading();
//                var datas = JSON.parse(data);
                                limit = datas.limit;
                                limit.total = datas.total;
                                if (datas.data) {
                                    dataCount = datas.data.length;
                                } else {
                                    dataCount = 0;
                                }
                                computePaginationEx('2'); //分页
                                classDataViewAdapter2(datas);  //数据
                                $("#pageSizeInfo2").html("总共"+datas.total+"条，每页显示");
                                closeLoading();

                            }
                        })
                    }
                    function classDataViewAdapter2(datas) {
                        var view = $(".historyTbody");
                        view.empty();
                        var i = 0;
                        var param = "";
                        for (var data in datas.data) {
                            var item = datas.data[data];
                            param += Objects.format("<tr>");
                            param += Objects.format("<td class=\"table_td\">{0}</td>",getLocalTime(item.dateCreated));
                            if(item.historyStatus=="0"){
                                param += Objects.format("<td class=\"table_td\">订单操作状态变更：等待受理</td>");
                            }else if(item.historyStatus=="1"){
                                param += Objects.format("<td class=\"table_td\">订单操作状态变更：已受理待汇款</td>");
                            }else if(item.historyStatus=="2"){
                                param += Objects.format("<td class=\"table_td\">订单操作状态变更：生产中未下单</td>");
                            }else if(item.historyStatus=="2.1"){
                                param += Objects.format("<td class=\"table_td\">订单操作状态变更：生产中已下单</td>");
                            }else if(item.historyStatus=="2.2"){
                                param += Objects.format("<td class=\"table_td\">订单操作状态变更：生产中已完成</td>");
                            }else if(item.historyStatus=="3"){
                                param += Objects.format("<td class=\"table_td\">订单操作状态变更：发运中</td>");
                            }else if(item.historyStatus=="4"){
                                param += Objects.format("<td class=\"table_td\">订单操作状态变更：完成</td>");
                            }else if(item.historyStatus=="5"){
                                param += Objects.format("<td class=\"table_td\">订单操作状态变更：已退回{0}</td>",item.returnReason!=null?item.returnReason:"");
                            }else if(item.historyStatus=="6"){
                                param += Objects.format("<td class=\"table_td\">订单操作状态变更：作废{0}</td>",item.orderInvalid!=null?item.orderInvalid:"");
                            }else if(item.historyStatus=="7"){
                                param += Objects.format("<td class=\"table_td\">订单操作状态变更：待生产</td>");
                            }else if(item.historyStatus=="10"){
                                param += Objects.format("<td class=\"table_td\">订单操作状态变更：订购单下单提交</td>");
                            }else if(item.historyStatus=="11"){
                                param += Objects.format("<td class=\"table_td\">订单操作状态变更：管理员编辑保存订购单</td>");
                            }else if(item.historyStatus=="12"){
                                param += Objects.format("<td class=\"table_td\">订单操作状态变更：管理员编辑保存到款信息</td>");
                            }else if(item.historyStatus=="13"){
                                param += Objects.format("<td class=\"table_td\">订单操作状态变更：管理员编辑保存快递信息</td>");
                            }else if(item.historyStatus=="14"){
                                param += Objects.format("<td class=\"table_td\">订单操作变更：管理员开启生产中订单退回重审操作</td>");
                            }else if(item.historyStatus=="15"){
                                param += Objects.format("<td class=\"table_td\">订单操作变更：管理员进行生产中订单完成重审操作</td>");
                            }
                            param += Objects.format("<td class=\"table_td\">{0}</td>",item.userName);
                            param += Objects.format("</tr>");
                        }
                        view.append(param);
                        tableAddClass();
                    }
                    <%--function computePagination2() {--%>
                        <%--var pageView = $("#pagination2 ul.pagination");--%>
                        <%--pageView.empty();--%>
                        <%--var pageCount = limit.total % limit.count == 0 ? limit.total / limit.count : limit.total / limit.count + 1;--%>
                        <%--limit.pageCount = pageCount;--%>
                        <%--pageView.append(itemTemplateOfpageView("item", 1, "&laquo;", limit));--%>
                        <%--for (var i = 4; i > 0; i--) {--%>
                            <%--var p = currentPageIndex - i;--%>
                            <%--if (p > 0) {--%>
                                <%--pageView.append(itemTemplateOfpageView("item", p, p, limit));--%>
                            <%--}--%>
                        <%--}--%>
                        <%--pageView.append(itemTemplateOfpageView("item active", currentPageIndex, currentPageIndex, limit));--%>
                        <%--for (var i = 1; i < 5; i++) {--%>
                            <%--var p = currentPageIndex + i;--%>
                            <%--if (p < limit.pageCount) {--%>
                                <%--pageView.append(itemTemplateOfpageView("item", p, p, limit));--%>
                            <%--}--%>
                        <%--}--%>
                        <%--pageView.append(itemTemplateOfpageView("item", limit.pageCount, "&raquo;", limit));--%>
                        <%--pageView.append(Objects.format("<li class=\"disabled\"><a href=\"#\">{0}/{1}</a></li>", limit.offset + dataCount, limit.total));--%>
                    <%--}--%>
                    function itemTemplateOfpageView2(cls, pageValue, text, limit) {
                        if (pageValue < 0) {
                            pageValue = 0;
                        } else if (pageValue > limit.pageCount) {
                            pageValue = limit.pageCount;
                        }
                        return Objects.format("<li class=\"{0}\"><a data=\"{1}\">{2}</a></li>", cls, pageValue, text);
                    }
                </script>
            </div>
            <%--登记-修改到款信息model--%>
            <div class="modal fade bs-example-modal-lg" id="orderPaidModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"  aria-hidden="true" data-backdrop="static" style="display: none;">
                <div class="modal-dialog modal-lg" role="document">
                    <div class="modal-content" style="width: 100%">
                        <div class="modal-header">
                            <button type="button" class="close invoiceDetailsClose" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
                            <h4 class="modal-title" id="myModalLabel">登记到款</h4>
                        </div>
                        <div class="modal-body">
                            <form id="checkForm">

                                <input type="hidden" name="orderId" value="${orderId}"/>

                                <input type="hidden" name="id" id="orderPaidId" value=""/>

                                <div class="tdiv_a_z">
                                    <div class="tdiv_a_1">到款日期：</div>
                                    <div class="tdiv_a_3"> <input type="text" class=" form-control   "  name="arrivalDate" onclick="WdatePicker()" value="<fmt:formatDate value="<%= new java.util.Date()%>" type="date" pattern="yyyy-MM-dd"/>"/></div>
                                    <div class="tdiv_a_1">到款账户：</div>
                                    <div class="tdiv_a_3">
                                        <select name="arrivalAccount">
                                            <option value="0">辰锐</option>
                                            <option value="1">三所</option>
                                        </select>
                                    </div>

                                </div>
                                <div class="clearfloat"></div>
                                <div class="tdiv_a_z">
                                    <div class="tdiv_a_1">到款金额：</div>
                                    <div class="tdiv_a_3">
                                        <input type="text" class=" form-control   "   name="arrivalAmount" value="${order.productTotalAmount}" />
                                    </div>
                                        <%--<c:if test="${order.remitterType==0}">--%>
                                        <%--<div class="tdiv_a_1">汇款单位名称：</div>--%>
                                        <%--</c:if>--%>
                                        <%--<c:if test="${order.remitterType==1}">--%>
                                        <%--<div class="tdiv_a_1">汇款人姓名：</div>--%>
                                        <%--</c:if>--%>
                                    <div class="tdiv_a_1">汇款单位/汇款人：</div>
                                    <div class="tdiv_a_3">
                                        <input type="text" class=" form-control" id ="remitter"  name="remitter" value="${order.remitter}"/>
                                    </div>
                                </div>
                                <div class="clearfloat"></div>
                                <div class="tdiv_a_z">
                                    <div class="tdiv_a_1">是否全部到款：</div>
                                    <div class="tdiv_a_3">
                                        <input type="radio" value="1" name="isTotalArrival" checked>是
                                        <input type="radio" value="0" name="isTotalArrival">否
                                    </div>
                                </div>
                                <div class="clearfloat"></div>
                                <div class="tdiv_a_z">
                                    <div class="tdiv_a_1">备注：</div>
                                    <div class="tdiv_a_2">
                                        <textarea  id="arrivalMessage" name="arrivalMessage" style="margin: 0px; width: 555px; height: 75px;"></textarea>
                                    </div>
                                    <div class="tdiv_a_4"><input type="button" class="btn btn-info" style="margin-top: 11%;margin-left: 100%;" id="orderPaidButtion" onclick="_postOrderPaid()" value="提交" /></div>
                                </div>
                                <%--<div class="clearfloat"></div>--%>
                                <%--<div class="tdiv_a_z">--%>
                                    <%--<div class="tdiv_a_1"></div>--%>
                                    <%----%>
                                <%--</div>--%>
                            </form>
                            <div class="clearfloat"></div>
                            <div class="tdiv_bottom"></div>
                            <div class="tdiv" >
                                <div class="tdiv_title">
                                    <i class="menu-i"></i>
                                    <p class="unit">到款记录</p>
                                </div>
                                <div>
                                <div class="neir_1_kcgl neir_2_kcgl_bod2">

                                    <table width="100%" class="table" border="0" cellpadding="0" cellspacing="0">
                                            <%--<thead class="thead">--%>
                                        <tr>
                                            <th class="table_td th">到款日期</th>
                                            <th class="table_td th">到款账户</th>
                                            <th class="table_td th">到款金额</th>
                                            <th class="table_td th">汇款单位/汇款人</th>
                                            <th class="table_td th">是否全部到款</th>
                                            <th class="table_td th">备注</th>
                                            <th class="table_td th">操作</th>
                                        </tr>
                                            <%--</thead>--%>
                                        <tbody class = "tbody paidTbody">

                                        </tbody>
                                    </table>
                                    <div class="clearfloat"></div>
                                </div>
                            </div>
                                </div>
                            <br/>
                            <%--<div class="modal-footer"></div>--%>
                        </div>
                           </div>
                </div>
            </div>



        </div>
    </div>
    </div>

    </c:if>

<c:if test="${sessionScope.gaUser.userType!=1}">
<div class="tdiv" >
    <div class="tdiv_title">
        <i class="menu-i"></i>
        <p class="unit">用户留言区</p>
    </div>
    <div>
        <div class="tdiv_a_z">
            <div class="tdiv_a_1"></div>
            <div class="tdiv_a_2"> <textarea  id="message" name="message" style="margin: 0px; width: 572px; height: 75px;"></textarea></div>
            <div class="tdiv_a_3"><input type="button" class="btn btn-info" onclick="_postOrderMessage()" value="提交" style="margin-top: 11%;margin-left: 25%;"/></div>
        </div>
        <div class="clearfloat"></div>
        <div class="tdiv_bottom"></div>
        <div class="tdiv" >
            <div class="tdiv_title">
                <i class="menu-i"></i>
                <p class="unit">历史留言</p>
            </div>
            <div>
            <div class="neir_1_kcgl neir_2_kcgl_bod2">
                <table width="100%" class="table" id="tableMessageList" border="0" cellpadding="0" cellspacing="0">
                <%--<table width="100%" class="table" id="tableMessageList" border="0" cellpadding="0" cellspacing="0">--%>
                    <%--<thead class="thead">--%>
                    <tr>
                        <th class="table_td th" width="20%">提交日期</th>
                        <th class="table_td th">留言内容</th>
                        <th class="table_td th" width="20%">留言人</th>
                    </tr>
                    <%--</thead>--%>
                    <tbody class = "tbody messageTbody">

                    </tbody>
                </table>
            </div>
                <div class="control-group nav-fenye">
                    <div class="pull-left nav-fenye"><span id="pageSizeInfo"></span>
                        <select id="selectPageSizeId" class="nopad" style="width: 45px" onchange="selectPageSize(this)">
                            <option selected="selected" value="5">5</option>
                            <option value="10">10</option>
                            <option  value="20">20</option>
                        </select> 条
                    </div>
                    <%--<nav aria-label="Page navigation" class="pull-right">--%>
                        <%--<div id="pagination">--%>
                            <%--<ul class="pagination">--%>
                            <%--</ul>--%>
                        <%--</div>--%>
                    <%--</nav>--%>
                    <div class="row-paginate">
                        <ul id="pagination" class="pagination">
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</c:if>
<div class="clearfloat"></div>
<%--退回订单modal--%>
<div class="modal fade bs-example-modal-lg" id="returnModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"  aria-hidden="true" data-backdrop="static" style="display: none;">
    <div class="modal-dialog modal-lg" role="document">
        <div class="modal-content" style="width: 100%">
            <div class="modal-header">
                <button type="button" class="close invoiceDetailsClose" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
                <h4 class="modal-title" id="myModalLabel">订单退回</h4>
                <br>
                <div style="min-height: 150px;">
                    <div class="tdiv_a_z">
                        <div class="tdiv_a_1">订单退回原因：</div>
                        <div class="tdiv_a_2"> <textarea  id="returnReason"  style="margin: 0px; width: 572px; height: 75px;"></textarea></div>
                        <div class="tdiv_a_4"><input type="button" class="btn btn-info" onclick="_putOrderReturnReason()" value="提交" style="margin-top: 11%;margin-left: 25%;"/></div>
                    </div>
                </div>
                <div class="clearfloat"></div>
            </div>
        </div>
    </div>
</div>

<%--作废订单modal--%>
<div class="modal fade bs-example-modal-lg" id="invalidModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"  aria-hidden="true" data-backdrop="static" style="display: none;">

    <div class="modal-dialog modal-lg" role="document">
        <div class="modal-content" style="width: 100%">
            <div class="modal-header">
                <button type="button" class="close invoiceDetailsClose" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
                <h4 class="modal-title" id="myModalLabel">订单作废</h4>
                <br>
                <div style="min-height: 150px;">
                    <div class="tdiv_a_z">
                        <div class="tdiv_a_1">订单作废原因：</div>
                        <div class="tdiv_a_2"> <textarea  id="orderInvalid"  style="margin: 0px; width: 572px; height: 75px;"></textarea></div>
                        <div class="tdiv_a_4"><input type="button" class="btn btn-info" onclick="_putOrderinvalid()" value="提交" style="margin-top: 11%;margin-left: 25%;"/></div>
                    </div>
                </div>
                <div class="clearfloat"></div>
            </div>
        </div>
    </div>
</div>

<%--登记-修改产品快递信息model--%>
<div class="modal fade bs-example-modal-lg" id="orderExpressModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" >
	<div class="modal-dialog modal-lg" role="document">
		<div class="modal-content" style="width: 100%">
        	<div class="modal-header">
               <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
               <h4 class="modal-title" id="myModalLabel">登记快递信息</h4>
           	</div>
           	<div class="modal-body">
               <form id="expressForm">
                   <input type="hidden" name="orderId" value="${orderId}"/>

                   <input type="hidden" name="id" id="orderExpressId" value=""/>

                   <input type="hidden" name="productionFlowId" id="productionFlowId" value=""/>

                   <div class="tdiv_a_z expressType">
                       <div class="tdiv_a_1">货品说明：</div>
                       <div class="tdiv_a_3">
                           <select name="expressType" style="width:200px;padding: 0px 0px;height: 24px;float: left;margin-right: 40px;" class="form-control">
                               <!-- <option value="1">发票</option> -->
                               <option value="2">证书</option>
                               <option value="3" selected>证书和发票</option>
                           </select>
                       </div>
                   </div>
                   <div class="clearfloat"></div>
                   <div class="tdiv_a_z">
                       <div class="tdiv_a_1">快递公司：</div>
                       <div class="tdiv_a_3">
                           <input  class="form-control checkText  " name="expressName" value="中通快递" />
                       </div>
                   </div>

                   <div class="clearfloat"></div>
                   <div class="tdiv_a_z">
                       <div class="tdiv_a_1">快递单号：</div>
                       <div class="tdiv_a_3">
                           <input  class="form-control checkText  " name="expressNo"  />
                       </div>
                   </div>

                   <div class="clearfloat"></div>
                   <div class="tdiv_a_z">
                       <div class="tdiv_a_1">发货日期：</div>
                       <div class="tdiv_a_3">
                           <input  class="checkText form-control   "  name="deliveryDate" onclick="WdatePicker()" value="<fmt:formatDate value="<%= new java.util.Date()%>" type="date" pattern="yyyy-MM-dd"/>"/>
                       </div>
                   </div>

                   <div class="clearfloat"></div>
                   <div class="tdiv_a_z">
                       <div class="tdiv_a_1">备注：</div>
                       <div class="tdiv_a_2">
                           <textarea class="form-control" name="expressMessage" style="margin: 0px; width: 572px; height: 75px;"></textarea>
                       </div>
                       <div class="tdiv_a_4"><input type="button" id="orderExpressButtion" style="margin-top: 11%;margin-left: 100%;" class="btn btn-info" onclick="_postOrderExpress()" value="提交"/></div>
                    </div>
                    <div class="clearfloat"></div>
                    <div class="tdiv_bottom"></div>
                </form>
           	</div>
        </div>
	</div>
</div>

<%--登记-开票快递信息model--%>
<div class="modal fade bs-example-modal-lg" id="orderInvoiceExpressModal" tabindex="-1" 
	role="dialog" aria-labelledby="myModalLabel" >
    <div class="modal-dialog modal-lg" role="document">
        <div class="modal-content" style="width: 100%">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                	<span aria-hidden="true">×</span>
                </button>
                <h4 class="modal-title" id="myModalLabel">发票快递信息</h4>
            </div>
            <div class="modal-body">
                <form id="expressInvoiceForm">
                	<!-- 空值，匹配使用 -->
                    
                    <input type="hidden" name="orderId" value="${orderId}"/>
                    <input type="hidden" name="flowId" value="${order.flowId}"/>
                    <div class="tdiv_a_z">
                        <div class="tdiv_a_1">快递公司：</div>
                        <div class="tdiv_a_3">
                            <input class="form-control checkText" name="expressName" 
                            	value="中通快递" />
                        </div>
                    </div>

                    <div class="clearfloat"></div>
                    <div class="tdiv_a_z">
                        <div class="tdiv_a_1">快递单号：</div>
                        <div class="tdiv_a_3">
                            <input  class="form-control checkText" name="expressNo"  />
                        </div>
                    </div>

                    <div class="clearfloat"></div>
                    <div class="tdiv_a_z">
                        <div class="tdiv_a_1">发货日期：</div>
                        <div class="tdiv_a_3">
                            <input class="checkText form-control" name="deliveryDate" 
                            	onclick="WdatePicker()" 
                            	value="<fmt:formatDate value="<%= new java.util.Date()%>" 
                            	type="date" pattern="yyyy-MM-dd"/>"/>
                        </div>
                    </div>

                    <div class="clearfloat"></div>
                    <div class="tdiv_a_z">
                        <div class="tdiv_a_1">备注：</div>
                        <div class="tdiv_a_2">
                            <textarea class="form-control" name="expressMessage" 
                            style="margin: 0px; width: 572px; height: 75px;"></textarea>
                        </div>
                        <div class="tdiv_a_4">
                        	<input type="button" id="orderInvoiceExpressButtion" 
	                        	style="margin-top:11%;margin-left:100%;" class="btn btn-info" 
	                        	onclick="_postOrderInvoiceExpress()" value="提交"/>
                        </div>
                    </div>
                    <div class="clearfloat"></div>
                    <div class="tdiv_bottom"></div>
                </form>
            </div>
        </div>
    </div>
</div>

</div>
<script type="text/javascript">
    var currentPageIndex = 1;
    var limit;
    var dataCount = 0;
    var pageSize = 5;
    $(function () {

        <%--$("#orderDate").val(getLocalTime("${order.orderDate}"));--%>


//        //tableAddClass();
        //分页查询
//        $("body").on("click", ".pagination li.item a", itemOfpageViewOnSelected);
        $("body").on("click", ".pagination li a", itemOfpageViewOnSelected);

        parserAsyncClassDataFormData();

        //快递信息
        parserAsyncClassDataFormDataExpress();
        
     	// 用户禁止修改开票快递form
        if(${sessionScope.gaUser.userType==0}){
            $("#expressInvoiceForm .form-control").attr( "disabled", "disabled" );
            $("#orderInvoiceExpressButtion").remove();
        }
    })


    // 产品快递信息提交
    function _postOrderExpress() {
        if(isNull($("#productionFlowId").val())){
            alert("生产流水号不能为空，请核对！");
            return false;
        }
        if(isNull($("#expressForm input[name=expressNo]").val())){
            alert("快递单号不能为空");
            return false;
        }
        openLoading();
        $.ajax({
            url: "<%=path%>/commonCompanyOrderQuery/postOrderExpress.do",
            type: "POST",
            data:$("#expressForm").serialize(),
            success: function(data){
                if(data.result=="SUCCESS"){
                    alert("提交成功！");
                    $("#orderExpressModal").modal("hide");
                    closeLoading();
                    //快递信息回调拼装
                    parserAsyncClassDataFormDataExpress();
//                                    location.reload();
                }else{
                    alert(data.errorMsg);
                    closeLoading();
                }
            }
        });
    }
    // 开票快递信息提交
    function _postOrderInvoiceExpress() {
        if(isNull($("#expressInvoiceForm input[name=flowId]").val())){
            alert("流水号不能为空，请核对！");
            return false;
        }
        if(isNull($("#expressInvoiceForm input[name=expressNo]").val())){
        	 alert("快递单号不能为空");
            return false;
        }
        openLoading();
        $.ajax({
            url: "<%=path%>/commonCompanyOrderQuery/postOrderInvoiceExpress.do",
            type: "POST",
            //async: false,
            data: $("#expressInvoiceForm").serialize(),
            success: function(data){
                if(data.result=="SUCCESS"){
                    alert("提交成功！");
                    $("#orderInvoiceExpressModal").modal("hide");
                    closeLoading();
                    window.location.reload();
                }else{
                    alert(data.errorMsg);
                    closeLoading();
                }
            }
        });
    }
    function  parserAsyncClassDataFormDataExpress(){
        var json = {OrderExpress: {}};
        json.OrderExpress["orderId"] = $("#orderId").val();
        $.ajax({
            url: "<%=path%>/commonCompanyOrderQuery/showOrderExpressList.do",
            type: "POST",
            data: json.OrderExpress,
            success: function(datas){
                classDataViewAdapterExpress(datas);  //数据
                closeLoading();
            }
        })
    }

    function classDataViewAdapterExpress(datas) {
        var view = $(".divExpress");
        view.children('tbody.tbody').remove();
        var i = 0;
        var param = "";
        param += Objects.format("<tbody class = \"tbody context1\">");
        for (var data in datas) {
            var item = datas[data];
            if(item.productListId !=0){
                param += Objects.format("<tr  class=\"nonePart\">");
                param += Objects.format("<td class=\"table_td\" hidden>{0}</td>",item.productionFlowId);
                param += Objects.format("<td class=\"table_td\">{0}</td>",item.productName);
                param += Objects.format("<td class=\"table_td\">{0}</td>",item.productType);
                param += Objects.format("<td class=\"table_td tableProductNumber\">{0}</td>",item.productNumber);
                param += Objects.format("<td class=\"table_td\">{0}</td>",item.productPrice);
                param += Objects.format("<td class=\"table_td tableProductAmount\">{0}</td>",item.productAmount);
                param += Objects.format("<td class=\"table_td\">{0}-{1}</td>",item.labelStart,item.labelEnd);
                if(isNull(item.expressNo)){  //快递单号为空新增
                    if(${sessionScope.gaUser.userType==0}) { //用户显示为空
                        param += Objects.format("<td class=\"table_td\"></td>");
                    }else {
                        param += Objects.format("<td class=\"table_td\"><input type=\"button\" class=\"btn btn-info\" onclick=\"_getOrderExpressPost('{0}')\" value=\"新增\"/></td>",
                                item.productionFlowId);
                    }
                }else {
                    param += Objects.format("<td class=\"table_td\"><a style='cursor: pointer' onclick=\"_getOrderExpress('{1}')\">{0}</a></td>",item.expressNo,
                            item.expressId
                            +"|"+item.expressType
                            +"|"+item.expressName
                            +"|"+item.expressNo
                            +"|"+item.expressMessage);
                }

                param += Objects.format("</tr>");
            }

//                            param += Objects.format("<tr>");
//                            param += Objects.format("<td class=\"table_td\">{0}</td>",item.expressType=="1"?"发票":item.expressType=="2"?"证书":item.expressType=="3"?"证书和发票":"");
//                            param += Objects.format("<td class=\"table_td\">{0}</td>",item.expressName);
//                            param += Objects.format("<td class=\"table_td\">{0}</td>",item.expressNo);
//                            param += Objects.format("<td class=\"table_td\">{0}</td>",getLocalTimeYYYYMMDD(item.deliveryDate));
//                            param += Objects.format("<td class=\"table_td\"><input type=\"button\" class=\"btn btn-info\" onclick=\"_getOrderExpress('{0}')\" value=\"编辑\"/></td>",
//                                    item.id
//                                    +","+item.expressType
//                                    +","+item.expressName
//                                    +","+item.expressNo
//                                    +","+item.expressMessage);
//                            param += Objects.format("</tr>");
        }
        param += Objects.format("</tbody>");
        param += Objects.format("<tbody class=\"tbody contextTotal1\">");
        param += Objects.format("</tbody>");
        param += Objects.format("<tbody class = \"tbody context2\">");
        for (var data in datas) {
            var item = datas[data];
            if(item.productListId ==0){
                param += Objects.format("<tr class=\"donePart\">");
                param += Objects.format("<td class=\"table_td\">{0}</td>",item.productName);
                param += Objects.format("<td class=\"table_td\">{0}</td>",item.productType);
                param += Objects.format("<td class=\"table_td tableProductNumber\">{0}</td>",item.productNumber);
                param += Objects.format("<td class=\"table_td\">{0}</td>",item.productPrice);
                param += Objects.format("<td class=\"table_td tableProductAmount\">{0}</td>",item.productAmount);
                param += Objects.format("<td class=\"table_td\">{0}-{1}</td>",item.labelStart,item.labelEnd);
                if(isNull(item.expressNo)){  //快递单号为空新增
                    if(${sessionScope.gaUser.userType==0}) { //用户显示为空
                        param += Objects.format("<td class=\"table_td\"></td>");
                    }else {
                        param += Objects.format("<td class=\"table_td\"><input type=\"button\" class=\"btn btn-info\" onclick=\"_getOrderExpressPost('{0}')\" value=\"新增\"/></td>",
                                item.productionFlowId);
                    }
                }else {
                    param += Objects.format("<td class=\"table_td\"><a style='cursor: pointer' onclick=\"_getOrderExpress('{1}')\">{0}</a></td>",item.expressNo,
                            item.expressId
                            +","+item.expressType
                            +","+item.expressName
                            +","+item.expressNo
                            +","+item.expressMessage);
                }
                param += Objects.format("</tr>");
            }
        }
        param += Objects.format("</tbody>");
        param += Objects.format("<tbody class=\"tbody contextTotal2\">");
        param += Objects.format("</tbody>");
        view.append(param);
        tableAddClass();

        //产品列表总计显示计算
        tableOrderProductTotal();
    }

    function _getOrderExpressPost(data) {
        $("#productionFlowId").val(data);
        $("#orderExpressModal").modal('show');
    }
    function _getOrderInvoiceExpressPost() {
        $("#orderInvoiceExpressModal").modal('show');
    }

    //获取快递信息回调模态框
    function _getOrderExpress(data) {
        $("#orderExpressId").val(data.split('|')[0]);
        var arrivalAccount = data.split('|')[1];
        $("#expressForm select[name=expressType]").val(arrivalAccount);
        $("#expressForm input[name=expressName]").val(data.split('|')[2]);
        $("#expressForm input[name=expressNo]").val(data.split('|')[3]);
        var expressMessage = data.split('|')[4];
        $("#expressForm textarea[name=expressMessage]").val(expressMessage=="null"?"":expressMessage);
        $("#orderExpressButtion").attr("onclick","_editOrderExpress()").val("保存");
        $("#orderExpressModal").modal('show');
        $("#productionFlowId").val('');//变更无需绑定流水号

        // 用户禁用form
        if(${sessionScope.gaUser.userType==0}){
            $("#expressForm .form-control").attr( "disabled", "disabled" );
            $("#orderExpressButtion").remove();
            //删除品名
            $(".expressType").remove();
        }
    }
    //模态框取消 按键重置
    $("#orderExpressModal").on('hide.bs.modal',function (event) {
        $("#orderExpressId").val("");
        $("#orderExpressButtion").attr("onclick","_postOrderExpress()").val("提交");
    })

    //编辑提交快递信息
    function _editOrderExpress() {
    	if(isNull($("#expressForm input[name=expressNo]").val())){
            alert("快递单号不能为空");
            return false;
        }
        openLoading();
        $.ajax({
            url: "<%=path%>/commonCompanyOrderQuery/editOrderExpress.do",
            type: "POST",
            data:$("#expressForm").serialize(),
            success: function(data){
                if(data.result=="SUCCESS"){
                    alert("提交成功！");
                    $("#orderExpressModal").modal("hide");
                    closeLoading();
                    //快递信息回调拼装
                    parserAsyncClassDataFormDataExpress();
//                                    location.reload();
                }else{
                    alert(data.errorMsg);
                    closeLoading();
                }
            }
        });
    }

    //获取开票快递信息回调模态框
    function _getOrderInvoiceExpress(data) {
        $("#expressInvoiceForm input[name=expressName]").val(data.split(',')[0]);
        $("#expressInvoiceForm input[name=expressNo]").val(data.split(',')[1]);
        var expressMessage = data.split(',')[2];
        $("#expressInvoiceForm textarea[name=expressMessage]").val(expressMessage=="null"?"":expressMessage);
        var d = new Date(data.split(',')[3]);
        var expressDeliveryDate = d.getFullYear()+'-'+(d.getMonth()+1)+'-'+d.getDate();
        $("#expressInvoiceForm input[name=deliveryDate]").val(expressDeliveryDate);
        $("#orderInvoiceExpressModal").modal('show');
    }




    function itemOfpageViewOnSelected() {
        openLoading();
        var p = $(this).attr("data");
        currentPageIndex = parseInt(p);
        computePagination();
        parserAsyncClassDataFormData();
    }
    function selectPageSize(data) {
        var size = $("#selectPageSizeId option:selected").val();
        pageSize = size;
        currentPageIndex = 1;
        parserAsyncClassDataFormData();
    }
    function  parserAsyncClassDataFormData(){
        var json = {OrderMessageSearcher: {}};
        json.OrderMessageSearcher["orderId"] = $("#orderId").val();
        json.OrderMessageSearcher["pageCount"] = pageSize;
        json.OrderMessageSearcher["offset"] = (currentPageIndex - 1) * pageSize;
        $.ajax({
            url: "<%=path%>/commonUserOrderQuery/showOrderMessagePage.do",
            type: "POST",
            data: json.OrderMessageSearcher,
            success: function(datas){
                closeLoading();
//                var datas = JSON.parse(data);
                limit = datas.limit;
                limit.total = datas.total;
                if (datas.data) {
                    dataCount = datas.data.length;
                } else {
                    dataCount = 0;
                }
                computePagination(); //分页
                classDataViewAdapter(datas);  //数据
                $("#pageSizeInfo").html("总共"+datas.total+"条，每页显示");
                closeLoading();

            }
//            ,error : function(XMLHttpRequest, textStatus, errorThrown) {
//                closeLoading();
//                alert("加入失败" + errorThrown);
//            }
        })
    }
    function classDataViewAdapter(datas) {
        var view = $(".messageTbody");
        view.empty();
        var i = 0;
        var param = "";
        for (var data in datas.data) {
            var item = datas.data[data];
            param += Objects.format("<tr>");
            param += Objects.format("<td class=\"table_td\">{0}</td>",getLocalTime(item.dateCreated));
            param += Objects.format("<td class=\"table_td\">{0}</td>",item.message);
            if(item.userType=="0"){
                param += Objects.format("<td class=\"table_td\">{0}</td>",item.userName);
            }else if(item.userType=="1"){
                param += Objects.format("<td class=\"table_td\">三所</td>");
            }
            param += Objects.format("</tr>");
        }
        view.append(param);
        tableAddClass();
    }
    <%--function computePagination() {--%>
        <%--var pageView = $("#pagination ul.pagination");--%>
        <%--pageView.empty();--%>
        <%--var pageCount = limit.total % limit.count == 0 ? limit.total / limit.count : limit.total / limit.count + 1;--%>
        <%--limit.pageCount = pageCount;--%>
        <%--pageView.append(itemTemplateOfpageView("item", 1, "&laquo;", limit));--%>
        <%--for (var i = 4; i > 0; i--) {--%>
            <%--var p = currentPageIndex - i;--%>
            <%--if (p > 0) {--%>
                <%--pageView.append(itemTemplateOfpageView("item", p, p, limit));--%>
            <%--}--%>
        <%--}--%>
        <%--pageView.append(itemTemplateOfpageView("item active", currentPageIndex, currentPageIndex, limit));--%>
        <%--for (var i = 1; i < 5; i++) {--%>
            <%--var p = currentPageIndex + i;--%>
            <%--if (p < limit.pageCount) {--%>
                <%--pageView.append(itemTemplateOfpageView("item", p, p, limit));--%>
            <%--}--%>
        <%--}--%>
        <%--pageView.append(itemTemplateOfpageView("item", limit.pageCount, "&raquo;", limit));--%>
        <%--pageView.append(Objects.format("<li class=\"disabled\"><a href=\"#\">{0}/{1}</a></li>", limit.offset + dataCount, limit.total));--%>
    <%--}--%>
    function itemTemplateOfpageView(cls, pageValue, text, limit) {
        if (pageValue < 0) {
            pageValue = 0;
        } else if (pageValue > limit.pageCount) {
            pageValue = limit.pageCount;
        }
        return Objects.format("<li class=\"{0}\"><a data=\"{1}\">{2}</a></li>", cls, pageValue, text);
    }


    //产品列表计算合计
    function tableOrderProductTotal(){
        $(".contextTotal1").empty();
        $(".contextTotal2").empty();
        if($('tr').is('.nonePart')&& $('tr').is('.donePart')){
            var tableProductList = $("#tableProductList");
            var tableProductNumberNonePart = 0;
            var tableProductAmountNonePart = 0;
            var tableProductNumberdonePart = 0;
            var tableProductAmountdonePart = 0;
            var tableProductNumber = 0;
            var tableProductAmount = 0;
            $("#tableProductList tr td").each(function () {
                if($(this).parent("tr").hasClass("nonePart")){
                    if($(this).hasClass("tableProductNumber")){
                        tableProductNumberNonePart+= Number($(this).text());
                    }else if($(this).hasClass("tableProductAmount")){
                        tableProductAmountNonePart+=(($(this).text())*1);
                    }
                }else if($(this).parent("tr").hasClass("donePart")){
                    if($(this).hasClass("tableProductNumber")){
                        tableProductNumberdonePart+= Number($(this).text());
                    }else if($(this).hasClass("tableProductAmount")){
                        tableProductAmountdonePart+=(($(this).text())*1);
                    }
                }
                if($(this).hasClass("tableProductNumber")){
                    tableProductNumber+= Number($(this).text());
                }else if($(this).hasClass("tableProductAmount")){
                    tableProductAmount+=(($(this).text())*1);
                }
            })
//            var context = tableProjectListContext("小计",tableProductNumberNonePart,"",tableProductAmountNonePart,"");
//            $(".contextTotal1").append(context);
//            var context = tableProjectListContext("总计",tableProductNumber,"tableProductNumber",tableProductAmount,"tableProductAmount");

            var context = tableProjectListContext("小计",tableProductNumberNonePart,"tableProductNumber",tableProductAmountNonePart,"");
            $(".contextTotal1").append(context);
//            var context = tableProjectListContext("总计",tableProductNumber,"tableProductNumber",tableProductAmount,"tableProductAmount");
            var context = tableProjectListContext("总计","","",tableProductAmount,"tableProductAmount");

        }else if($('tr').is('.nonePart')){
            var tableProductList = $("#tableProductList");
            var tableProductNumberNonePart = 0;
            var tableProductAmountNonePart = 0;
            var tableProductNumber = 0;
            var tableProductAmount = 0;
            $("#tableProductList tr td").each(function () {
                if($(this).parent("tr").hasClass("nonePart")){
                    if($(this).hasClass("tableProductNumber")){
                        tableProductNumberNonePart+= Number($(this).text());
                    }else if($(this).hasClass("tableProductAmount")){
                        tableProductAmountNonePart+=(($(this).text())*1);
                    }
                }
                if($(this).hasClass("tableProductNumber")){
                    tableProductNumber+= Number($(this).text());
                }else if($(this).hasClass("tableProductAmount")){
                    tableProductAmount+=(($(this).text())*1);
                }
            })
            var context = tableProjectListContext("小计",tableProductNumberNonePart,"tableProductNumber",tableProductAmountNonePart,"");
            $(".contextTotal1").append(context);
//            var context = tableProjectListContext("总计",tableProductNumber,"tableProductNumber",tableProductAmount,"tableProductAmount");
            var context = tableProjectListContext("总计","","",tableProductAmount,"tableProductAmount")

        }else if ($('tr').is('.donePart')){
            var tableProductList = $("#tableProductList");
            var tableProductNumberdonePart = 0;
            var tableProductAmountdonePart = 0;
            var tableProductNumber = 0;
            var tableProductAmount = 0;
            $("#tableProductList tr td").each(function () {
                if($(this).parent("tr").hasClass("donePart")){
                    if($(this).hasClass("tableProductNumber")){
                        tableProductNumberdonePart+= Number($(this).text());
                    }else if($(this).hasClass("tableProductAmount")){
                        tableProductAmountdonePart+=(($(this).text())*1);
                    }
                }
                if($(this).hasClass("tableProductNumber")){
                    tableProductNumber+= Number($(this).text());
                }else if($(this).hasClass("tableProductAmount")){
                    tableProductAmount+=(($(this).text())*1);
                }
            })
//            var context = tableProjectListContext("小计",tableProductNumberdonePart,"",tableProductAmountdonePart,"");
//            var context = tableProjectListContext("总计",tableProductNumber,"tableProductNumber",tableProductAmount,"tableProductAmount");
            var context = tableProjectListContext("小计",tableProductNumberNonePart,"tableProductNumber",tableProductAmountNonePart,"");
            var context = tableProjectListContext("总计","","",tableProductAmount,"tableProductAmount")
        }
        $(".contextTotal2").append(context);
        tableAddClass();
    }
    function tableProjectListContext(label,projectNumber,numId,productAmount,amountId){
        var context = "";
        context += Objects.format("<tr>");
        context += Objects.format("<td class=\"table_td\">{0}</td>",label);
        context += Objects.format("<td class=\"table_td\"></td>");
        context += Objects.format("<td class=\"table_td tableProductListNum\" id=\"{0}\">{1}</td>",numId,projectNumber);
        context += Objects.format("<td class=\"table_td\"></td>");
        context += Objects.format("<td class=\"table_td tableProductListAmount\" id=\"{0}\">{1}</td>",amountId,productAmount);
        context += Objects.format("<td class=\"table_td\"></td>");
        context += Objects.format("<td class=\"table_td\"></td>");
        context += Objects.format("</tr>");
        return context;
    }
    function _postOrderMessage() {
        var message = $("#message").val();
        var orderId = $("#orderId").val();
        if(isNull(message)){
            message = $("#message1").val();    //卖家留言框
            if(isNull(message)){
                alert("请填写留言信息。");
                return false;
            }
        }else if(isNull(orderId)){
            alert("请重新选择订单。");
            return false;
        }
        openLoading();
        $.ajax({
            url: "<%=path%>/commonUserOrderQuery/postOrderMessage.do",
            type: "POST",
            data:{"orderId":orderId,"message":message},
            success: function(data){
//                data = JSON.parse(data);
                if(data.result=="SUCCESS"){
                    alert("提交成功！");
                    closeLoading();
                    $("#message").val("");
                    parserAsyncClassDataFormData();
                    <%--location.href= "<%=path%>/file/downloadTemp.do?fileName="+data.errorMsg;--%>
                }else{
                    alert(data.errorMsg);
                    closeLoading();
                }
            }
        });
    }
    function _downLoadOrderFile() {
        var orderId = $("#orderId").val();
        var orderUnitName = $("#orderUnitName").val();
        var flowId = $("#flowId").val();
        if(orderId==""){
            alert("请重新选择订单。");
        }
        openLoading();
        location.href= "<%=path%>/file/downLoadOrderFile.do?orderId="+orderId+"&orderUnitName="+orderUnitName+"&flowId="+flowId;
        closeLoading();
    }

    function _downLoadOrderReportFile() {
        var orderId = $("#orderId").val();
        if(orderId==""){
            alert("请重新选择订单。");
        }
        openLoading();
        location.href= "<%=path%>/file/downLoadOrderReportFile.do?orderId="+orderId;
        closeLoading();
    }
    //更改借转销类型
    function _putBorrowType(borrowType) {
        var orderId = $("#orderId").val();
        if(orderId==""){
            alert("请重新选择订单。");
            return false;
        }else if(isEmpty(borrowType)){
            alert("请重新选择。");
            return;
        }
        openLoading();
        $.ajax({
            url: "<%=path%>/commonCompanyOrderQuery/putBorrowType.do",
            type: "POST",
            data:{"borrowType":borrowType,"orderId":orderId},
            success: function(data){
                if(data.result=="SUCCESS"){
                    alert("操作成功！");
                    closeLoading();
                    location.reload();
                }else{
                    alert(data.errorMsg);
                    closeLoading();
                }
            }
        })
    }

    //卖家更改退回
    function _putOrderReturnReason() {
        var orderId = $("#orderId").val();
        var returnReason = $("#returnReason").val();
        if(orderId==""){
            alert("请重新选择订单。");
        }
        openLoading();
        $.ajax({
            url: "<%=path%>/commonCompanyOrderQuery/putOrderReturnReason.do",
            type: "POST",
            data:{"returnReason":returnReason,"orderId":orderId},
            success: function(data){
                if(data.result=="SUCCESS"){
                    alert("操作成功！");
                    closeLoading();
                    location.reload();
                }else{
                    alert(data.errorMsg);
                    closeLoading();
                }
            }
        })

    }

    //卖家更改作废
    function _putOrderinvalid() {
        var orderId = $("#orderId").val();
        var orderInvalid = $("#orderInvalid").val();
        if(orderId==""){
            alert("请重新选择订单。");
        }
        openLoading();
        $.ajax({
            url: "<%=path%>/commonCompanyOrderQuery/putOrderinvalid.do",
            type: "POST",
            data:{"orderInvalid":orderInvalid,"orderId":orderId},
            success: function(data){
                if(data.result=="SUCCESS"){
                    alert("操作成功！");
                    closeLoading();
                    location.reload();
                }else{
                    alert(data.errorMsg);
                    closeLoading();
                }
            }
        })
    }

    //卖家更改生产异常状态
    function _putproductionErrorStatus(productionErrorStatus) {
        var orderId = $("#orderId").val();
        if(orderId==""){
            alert("请重新选择订单。");
            return false;
        }else if(isEmpty(productionErrorStatus)){
            alert("请重新选择。");
            return;
        }
        var operator = false;
        if(productionErrorStatus==0){
            if(confirm("确认是否完成重审，请核实该订单信息是否正确，您的当前操作日志会被系统记录！")){
                operator = true;
            }
        }else if(productionErrorStatus==1){
            if(confirm("确认是否需要退回重审，请核实该订单信息是否需要修改，您的当前操作日志会被系统记录！")){
                operator =true;
            }
        }
//        确认操作
        if(operator){
            openLoading();
            $.ajax({
                url: "<%=path%>/commonCompanyOrderQuery/putDetailsProductionErrorStatus.do",
                type: "POST",
                data:{"productionErrorStatus":productionErrorStatus,"orderId":orderId},
                success: function(data){
                    if(data.result=="SUCCESS"){
                        alert("操作成功！");
                        closeLoading();
                        location.reload();
                    }else{
                        alert(data.errorMsg);
                        closeLoading();
                    }
                }
            })
        }

    }
    //卖家更改订单状态
    function _putOrderStatus(orderStatus) {
        var orderId = $("#orderId").val();
        if(orderId==""){
            alert("请重新选择订单。");
            return false;
        }else if(orderStatus==""){
            alert("状态不能为空。");
            return false;
        }
        openLoading();
        $.ajax({
            url: "<%=path%>/commonCompanyOrderQuery/putDetailsOrderStatus.do",
            type: "POST",
            data:{"orderStatus":orderStatus,"orderId":orderId},
            success: function(data){
                if(data.result=="SUCCESS"){
                    alert("操作成功！");
                    closeLoading();
                    location.reload();
                }else{
                    alert(data.errorMsg);
                    closeLoading();
                }
            }
        })
    }

    //消息提醒取消
    function _putOrderMessageWarn(orderId) {
        if(orderId==""){
            alert("请重新选择订单。");
        }
        $.ajax({
            url: "<%=path%>/commonCompanyOrderQuery/putOrderMessageWarn.do",
            type: "POST",
            data:{"orderId":orderId},
        })
    }

    //编辑
    function editOrder(orderId) {
        changeDaoHangFrame('订单查询`<%=path%>/commonCompanyOrderQuery/getOrderIndex.do,订单详情`/commonUserOrderQuery/showDetailsOrder.do?orderId='+orderId+',编辑`<%=path%>/commonUserOrder/backEditOrder.do?orderId='+orderId);
        window.location.href='<%=path%>/commonUserOrder/backEditOrder.do?orderId='+orderId;
    }

    var ff = 0; //需要合并的行数
    var inxElement = 0; //第一次记录TD
    var inxVal = 0; //第一次记录开票单位Id

    //合并开票单位单元格
    $(".merge").each(function (index,element) {
        merge(index,element,"merge");
    })
    //合并开票信息单元格
    $(".mergeInvoice").each(function (index,element) {
        merge(index,element,"mergeInvoice");
    })
    //合并单元格
    function merge(index,element,classString) {
//        if(!$(this).hasClass('hide')) {
        var next = $(element).parent('tr').next('tr').find('.'+classString);
        var thisInx = $(element).parent('tr').find('.id');
        var nextInx = $(element).parent('tr').next('tr').find('.id');
        var pmthis = $(element).parent('tr').find('.pm');
        var pmnext = $(element).parent('tr').next('tr').find('.pm');
//        console.log("进入前==="+pmthis.val()+"："+thisInx.val()+","+pmnext.val()+"："+nextInx.val()+";");
        if (index == 0) {
            $(element).prop('rowspan', 1);
            inxVal = thisInx.val();
            inxElement = $(element);
            ff = 0;
        }
        var ss =  nextInx.val();
        if (inxVal == nextInx.val() && inxVal != undefined) { //等于后置，不等于空。 累加数值，隐藏后置。
            ff = ff + 1;
            next.hide();
            next.addClass('hide');
        }else if (inxVal != nextInx.val() && nextInx.val() != undefined){  //不等于后置，不等于空，合并数值，重新计数。
//            console.log("累加当前行数："+inxElement.parent('tr').find('.pm').val()+ff);
            inxElement.prop('rowspan', parseInt(inxElement.attr('rowspan')) + ff);
            $(next).prop('rowspan', 1);
            inxVal = nextInx.val();
            inxElement = next;
            ff = 0;
        }else if(nextInx.val() == undefined){ //后置为空，合并数值，结束。
//            console.log("累加当前行数："+inxElement.parent('tr').find('.pm').val()+ff);
            inxElement.prop('rowspan', parseInt(inxElement.attr('rowspan')) + ff);
        }
    }

    //上传电子发票
    function _uploadInvoiceFile() {
        if($("#fileName").val()==""){
            alert("请传入上传电子发票文件。");
            return false;
        }
        openLoading();
        $("#uploadInvoiceForm").ajaxSubmit({
            type:'post',
            url: "<%=path%>/commonCompanyOrderQuery/uploadInvoiceFile.do",
            success: function(data){
                data = JSON.parse(data);
                if(data.result == "SUCCESS"){
                    closeLoading();
                    alert("上传成功");
                    closeLoading();
                    $(".downLoadInvoice").remove();
                    var view = $("#zipFile");
                    var param = "";
                    param += Objects.format("<label  class=\"btn btn-danger downLoadInvoice\" style=\"margin-left: 10px;\" onclick=\"_downLoadInvoiceFile()\">下载电子发票</label>");
                    view.before(param);
                }else{
                    closeLoading();
                    alert(data.errorMsg);
                }

            },error : function(XMLHttpRequest, textStatus, errorThrown) {
                closeLoading();
                alert("加入失败" + errorThrown);
            }
        })
    }

    //下载电子发票
    function _downLoadInvoiceFile() {
        var orderId = $("#orderId").val();
        if(orderId==""){
            alert("请重新选择订单。");
        }
        openLoading();
        location.href= "<%=path%>/commonCompanyOrderQuery/downLoadInvoiceFile.do?orderId="+orderId;
        closeLoading();
    }



    //分页样式
    function computePaginationEx(index) {
        var pageView = $("#pagination"+index);
        pageView.empty();
        var pageCount = Math.floor(limit.total % limit.count == 0 ? limit.total / limit.count : limit.total / limit.count + 1);
        pageCount= pageCount==0 ? 1:pageCount;
        limit.pageCount = pageCount;
        //开头
        pageView.append("<li><a  class='pg-item pg-prev-up' data='1'></a></li>");
        //上一页
        upPage = currentPageIndex-1>1?currentPageIndex-1:1;
        pageView.append("<li><a  class='pg-item pg-prev' data='"+upPage+"'></a></li>");
        var limitFrontIndex = pageCount-currentPageIndex<3?7-(Math.ceil(pageCount)-currentPageIndex):3;
        for (var i = limitFrontIndex; i > 0; i--) {
            var p = currentPageIndex - i;
            if (p > 0) {
                pageView.append(itemTemplateOfpageView("pg-item pg-item", p, p, limit));
            }
        }

        var ss = currentPageIndex;
        var dd = limit;
        var limitIndex = currentPageIndex>3?3:currentPageIndex-1;
        pageView.append(itemTemplateOfpageView("pg-item pg-item active", currentPageIndex, currentPageIndex, limit));
        for (var i = 1; i < 7-limitIndex; i++) {
            var p = currentPageIndex + i;
            if (p <= limit.pageCount) {
                pageView.append(itemTemplateOfpageView("pg-item pg-item", p, p, limit));
            }
        }
        // }
        //下一页
        nextPage = currentPageIndex+1<=limit.pageCount?currentPageIndex+1:currentPageIndex;
        pageView.append("<li><a  class='pg-item pg-next' data='"+nextPage+"'></a></li>");
        //结尾
        pageView.append("<li><a  class='pg-item pg-next-down' data='"+limit.pageCount+"'></a></li>");
        pageView.append(Objects.format("<li class=\"disabled\"><span>{0}/{1}</span></li>", currentPageIndex, pageCount));
        pageView.append(Objects.format("<li ><span style=\"min-width: 175px;\">跳转<input  onkeyup=\"this.value=this.value.replace(/\\D/g,'')\" style=\"width:50px\" id=\"currentPage{0}\" name=\"currentPage{0}\"  type='text'>页 <input type=\"button\" value=\"跳转\"  onclick=\"limitSearchEx('{0}')\"></span> </li>" ,index));
    }

    //分页跳转
    function limitSearchEx(index) {
        openLoading();
        var p = $("#currentPage"+index).val();
        currentPageIndex = parseInt(p);
        var pageCount = Math.floor(limit.total % limit.count == 0 ? limit.total / limit.count : limit.total / limit.count + 1);
        if(p>pageCount){
            closeLoading();
            return false;
        }
        computePaginationEx(index);
        eval("parserAsyncClassDataFormData"+index+"()");
    }

</script>

</body>
</html>
