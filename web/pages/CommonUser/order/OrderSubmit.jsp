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

	<%-- 		<script type="text/javascript" src="<%=path%>/js/validate/jquery.validate.js"></script>
            <script type="text/javascript" src="<%=path%>/js/validate/additional-methods.js"></script>
            <script type="text/javascript" src="<%=path%>/js/validate/messages_zh.js"></script> --%>
	<%--<script type="text/javascript" src="<%=path%>/js/validate.js"></script>--%>
	<%--<script type="text/javascript" src="<%=path%>/js/example.js"></script>--%>
	<script type="text/javascript" src="<%=path%>/js/jquery-form.js"></script>

	<link href="<%=path%>/js/swf/css/default.css" rel="stylesheet" type="text/css" />
	<%--<link type="text/css" href="<%=path%>/js/themes/jquery-ui.min.css"--%>
		  <%--rel="stylesheet" />--%>
	<style type="text/css">
		input[type=radio]{
			margin-left: 1.5%;
		}
		input{
			margin-left: 1%;
			margin-right: 1%;
		}
		.spanLabel{
			background-color: gainsboro;
			text-align: center !important;
			width: 40px !important;
			/*margin-left: 6px;*/
			margin-right: 12px !important;
			margin-left: 12px !important;

		}
		.spanLabelLeft{
			text-align: center !important;
			width: 40px !important;
			/*margin-left: 6px;*/
			margin-right: 12px !important;
		}

		/*table{*/
			/*border: solid 1px gray;*/
			/*!*table-layout: fixed;*!*/
			/*!*background-image: url(../../../images/table_td.png);*!*/
		/*}*/
		/*tr{*/
			/*!*background-image: url(../../../images/table_td.png);*!*/
		/*}*/
		.button_left15{
			margin-left: 15%;
		}
		.width_30{
			width: 70px!important;
		}
		.red_label{
			/*padding-right: 3%;*/
			color: red;
		}

        /*body{*/
            /*background-color: #f3f3f3;*/
        /*}*/

        input.file{ vertical-align:middle; position:relative; filter:alpha(opacity=0); opacity:0; z-index:1; width:382px; height: 36px; line-height: 36px; cursor:pointer; top: -36px; }

        form input.viewfile { z-index:99; border:1px solid #ccc; padding:2px; width:300px; vertical-align:middle; color:#999; }

        form p span { float:left; }
	</style>
	<script type="text/javascript">
		$(function() {
			//跳转任务页面
//				window.location.href = path;
//			changeDaoHang('证书订购');
//				$("#page_daohang", window.parent.frames["daohangframe"].document).html("您所在的位置 &gt;&gt; 证书订购");
		})
	</script>
</head>





<body class="page_content page_color">
<%--<div align="center">--%>
	<%--<div class="content">--%>
	<form id="purchaserForm">

		<input type="hidden" name = "orderId" id = "orderId" value="${orderId}"/>
		<input type="hidden" name = "deliveryAddressId" id = "deliveryAddressId"/>


		<div class="tdiv tdiv_top" >
			<div class="tdiv_title"><p>订购基本信息</p></div>
			<c:if test="${sessionScope.gaUser.userType==1}">
			<div class="tdiv_a_z">
				<div class="tdiv_a_1" >订购类型：</div>
				<div class="tdiv_a_3">
					<select style="padding-top: 0px; padding-bottom: 0px;" class="form-control"  id="orderType" name="orderType">
						<c:choose>
							<c:when test="${order.orderType==0}">
								<option value="1" >代购</option>
								<option value="0" selected>订购</option>
								<option value="2" >项目内</option>
								<option value="3" >赠送</option>
								<option value="4" >提前发货</option>
								<option value="5" >合同已包含</option>
								<option value="6" >先货后款</option>
							</c:when>
							<c:when test="${order.orderType==1}">
								<option value="1" selected>代购</option>
								<option value="0" >订购</option>
								<option value="2" >项目内</option>
								<option value="3" >赠送</option>
								<option value="4" >提前发货</option>
								<option value="5" >合同已包含</option>
								<option value="6" >先货后款</option>
							</c:when>
							<c:when test="${order.orderType==2}">
								<option value="1" >代购</option>
								<option value="0" >订购</option>
								<option value="2" selected>项目内</option>
								<option value="3" >赠送</option>
								<option value="4" >提前发货</option>
								<option value="5" >合同已包含</option>
								<option value="6" >先货后款</option>
							</c:when>
							<c:when test="${order.orderType==3}">
								<option value="1" >代购</option>
								<option value="0" >订购</option>
								<option value="2" >项目内</option>
								<option value="3" selected>赠送</option>
								<option value="4" >提前发货</option>
								<option value="5" >合同已包含</option>
								<option value="6" >先货后款</option>
							</c:when>
							<c:when test="${order.orderType==4}">
								<option value="1" >代购</option>
								<option value="0" >订购</option>
								<option value="2" >项目内</option>
								<option value="3" >赠送</option>
								<option value="4" selected>提前发货</option>
								<option value="5" >合同已包含</option>
								<option value="6" >先货后款</option>
							</c:when>
							<c:when test="${order.orderType==5}">
								<option value="1" >代购</option>
								<option value="0" >订购</option>
								<option value="2" >项目内</option>
								<option value="3" >赠送</option>
								<option value="4" >提前发货</option>
								<option value="5" selected>合同已包含</option>
								<option value="6" >先货后款</option>
							</c:when>
							<c:when test="${order.orderType==6}">
								<option value="1" >代购</option>
								<option value="0" >订购</option>
								<option value="2" >项目内</option>
								<option value="3" >赠送</option>
								<option value="4" >提前发货</option>
								<option value="5" >合同已包含</option>
								<option value="6" selected>先货后款</option>
							</c:when>
							<c:otherwise>
								<option value="1" selected>代购</option>
								<option value="0" >订购</option>
								<option value="2" >项目内</option>
								<option value="3" >赠送</option>
								<option value="4" >提前发货</option>
								<option value="5" >合同已包含</option>
							</c:otherwise>
						</c:choose>
					</select>
				</div>
			</div>
			<div class="clearfloat"></div>
			</c:if>
			<div class="tdiv_a_z">
				<div class="tdiv_a_1" >订购地区：</div>
				<div class="tdiv_a_3">
						<select style="padding-top: 0px; padding-bottom: 0px;" class="form-control" id="areaProvice" name="areaProvice">
							<c:forEach items="${area}" var="areas">
								<c:choose>
									<c:when test="${provinceCode == areas.id}">
										<option value="${areas.id}" selected>${areas.name}</option>
									</c:when>
									<c:otherwise>
										<option value="${areas.id}">${areas.name}</option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</select>
						<span class="red_label">★</span>
				</div>
				<div class="tdiv_a_3">
						<select style="padding-top: 0px; padding-bottom: 0px;width: 220px;" class="form-control" id="areaCity" name="areaCity">
							<c:forEach items="${city}" var="citys">
								<c:choose>
									<c:when test="${cityCode == citys.id}">
										<option value="${citys.id}" selected>${citys.name}</option>
									</c:when>
									<c:otherwise>
										<option value="${citys.id}">${citys.name}</option>
									</c:otherwise>
								</c:choose>

							</c:forEach>
						</select>
						<span class="red_label">★</span>
				</div>
			</div>
			<div class="clearfloat"></div>

			<div class="tdiv_a_z">
				<div class="tdiv_a_1">订购单位全称：</div>
				<div class="tdiv_a_3" style="width: 400px;">
					<c:choose>
                        <c:when test="${not empty order.orderUnitName}">
                            <input type="text" class = "form-control" style="width: 350px" name="orderUnitName" id="orderUnitName" value="${order.orderUnitName}">
                        </c:when>
						<c:when test="${not empty sessionScope.gaUser.orderUnitName}">
							<input type="text" class = "form-control" style="width: 350px" name="orderUnitName" id="orderUnitName" value="${sessionScope.gaUser.orderUnitName}">
						</c:when>
						<c:otherwise>
							<input type="text" class = "form-control" style="width: 350px" name="orderUnitName" id="orderUnitName" >
						</c:otherwise>
					</c:choose>
					<span class="red_label">★</span></div>

			</div>
			<div class="clearfloat"></div>
			<div class="tdiv_a_z">
				<div class="tdiv_a_1">订购单位地址：</div>
				<div class="tdiv_a_3" style="width: 400px;">
					<input type="text" class = "form-control" style="width: 350px" name="orderUnitAddress" id="orderUnitAddress" value="${order.orderUnitAddress}" >
					<span class="red_label">★</span>
				</div>
			</div>
			<div class="clearfloat"></div>
			<div class="tdiv_a_z">
				<div class="tdiv_a_1">订购联系人：</div>
				<div class="tdiv_a_3">
					<input type="text" class = "form-control" name="purchaser" id="purchaser" value="${order.purchaser}"/>
					<span class="red_label">★</span>
				</div>
			</div>
			<div class="clearfloat"></div>
			<div class="tdiv_a_z">
				<div class="tdiv_a_1">手机号码：</div>
				<div class="tdiv_a_3"><input type="text" class = "form-control" name="purchaserMobile" id="purchaserMobile" value="${order.purchaserMobile}">
					<span class="red_label">★</span>
				</div>
				<div class="tdiv_a_1">电话号码：</div>
				<div class="tdiv_a_3"><input type="text" class = "form-control" name="purchaserPhone" id="purchaserPhone" value="${order.purchaserPhone}"></div>
				<div class="tdiv_a_4">
					<label  class="btn btn-info" onclick="addOrderSave()">保存</label>
				</div>
			</div>
			<div class="clearfloat"></div>
			<hr class="hr_style"/>
			<div class="tdiv_a_z">
				<div class="tdiv_a_left"><div class="left_tubiao warn"></div> <strong>提醒：订购单位全称需与订购单位公章保持一致。</strong></div>
			</div>
			<div class="clearfloat"></div>
			<div class="tdiv_bottom"></div>
		</div>
	</form>

<form id="orderAddressForm">
<div class="tdiv" >
	<div class="tdiv_title"><p>收件地址</p></div>
    <%--<c:choose>--%>
        <%--<c:when test="${not empty order.receiveUnitName}">--%>
            <%--默认显示addressId--%>
            <input type="hidden" id="orderAddressId" >
            <div class="tdiv_a_z">
                <div  class="tdiv_a_1">收货单位：</div>
                <div class="tdiv_a_3">
                    <span id="receiveUnitName" name="receiveUnitName">${order.receiveUnitName}</span>
                </div>
                <div class="tdiv_a_1">收货地址：</div>
                <div class="tdiv_a_3">
                    <span id="receiveUnitAddress" name="receiveUnitAddress">${order.receiveUnitAddress}</span>
                </div>
                <div class="clearfloat"></div>
                <div class="tdiv_a_1">收货联系人：</div>
                <div class="tdiv_a_3">
                    <span id="receiveName" name="receiveName">${order.receiveName}</span>

                </div>
                <div class="tdiv_a_1">联系方式：</div>
                <div class="tdiv_a_3">
                    <%--<input type="hidden" id="receiveMobile" value="${order.receiveMobile}"/>--%>
                    <span id="receiveMobile" name="receiveMobile">${order.receiveMobile}</span>
                    /
                    <span id="receivePhone" name="receivePhone">${order.receivePhone}</span>
                </div>
                <div class="clearfloat"></div>
                <div class="tdiv_a_1">备选收货联系人：</div>
                <div class="tdiv_a_3">
                    <span id="standbyName" name="standbyName">${order.standbyName}</span>
                </div>
                <div class="tdiv_a_1">联系方式：</div>
                <div class="tdiv_a_3">
                    <span id="standbyMobile" name="standbyMobile">${order.standbyMobile}</span>
                    /
                    <span id="standbyPhone" name="standbyPhone">${order.standbyPhone}</span>
                </div>
                <%--<p>&nbsp;&nbsp;<span id="defaultMobile">${address.receiveMobile}</span></p>--%>
                <%----%>
                <%--<p>收货地址：<span id="defaultReceiveAddress">${address.receiveUnitAddress}</span></p>--%>
            </div>
        <%--</c:when>--%>
        <%--<c:otherwise>--%>
            <%--&lt;%&ndash;默认显示addressId&ndash;%&gt;--%>
            <%--<input type="hidden" id="orderAddressId" value="${address.id}">--%>
            <%--<div class="tdiv_a_z">--%>
                    <%--<div  class="tdiv_a_1">收货单位：</div>--%>
                    <%--<div class="tdiv_a_3">--%>
                        <%--<span id="receiveUnitName" name="receiveUnitName">${address.receiveUnitName}</span>--%>
                    <%--</div>--%>
                    <%--<div class="tdiv_a_1">收货地址：</div>--%>
                    <%--<div class="tdiv_a_3">--%>
                        <%--<span id="receiveUnitAddress" name="receiveUnitAddress">${address.receiveUnitAddress}</span>--%>
                    <%--</div>--%>
                    <%--<div class="clearfloat"></div>--%>
                    <%--<div class="tdiv_a_1">收货联系人：</div>--%>
                    <%--<div class="tdiv_a_3">--%>
                        <%--<span id="receiveName" name="receiveName">${address.receiveName}</span>--%>
                    <%--</div>--%>
                    <%--<div class="tdiv_a_1">联系方式：</div>--%>
                    <%--<div class="tdiv_a_3">--%>
                        <%--&lt;%&ndash;<input type="hidden" id="receiveMobile" value="${address.receiveMobile}"/>&ndash;%&gt;--%>
                        <%--<span id="receiveMobile" name="receiveMobile">${address.receiveMobile}</span>--%>
                        <%--/--%>
                        <%--<span id="receivePhone" name="receivePhone">${address.receivePhone}</span>--%>
                    <%--</div>--%>
                    <%--<div class="clearfloat"></div>--%>
                    <%--<div class="tdiv_a_1">备选收货联系人：</div>--%>
                    <%--<div class="tdiv_a_3">--%>
                        <%--<span id="standbyName" name="standbyName">${address.standbyName}</span>--%>
                    <%--</div>--%>
                    <%--<div class="tdiv_a_1">联系方式：</div>--%>
                    <%--<div class="tdiv_a_3">--%>
                        <%--<span id="standbyMobile" name="standbyMobile">${address.standbyMobile}</span>--%>
                        <%--/--%>
                        <%--<span id="standbyPhone" name="standbyPhone">${address.standbyPhone}</span>--%>
                    <%--</div>--%>
                    <%--&lt;%&ndash;<p>&nbsp;&nbsp;<span id="defaultMobile">${address.receiveMobile}</span></p>&ndash;%&gt;--%>
                    <%--&lt;%&ndash;&ndash;%&gt;--%>
                    <%--&lt;%&ndash;<p>收货地址：<span id="defaultReceiveAddress">${address.receiveUnitAddress}</span></p>&ndash;%&gt;--%>
                    <%--<div class="clearfloat"></div>--%>
            <%--</div>--%>
            <%--<div class="clearfloat"></div>--%>
                <%--&lt;%&ndash;<div class="tdiv">&ndash;%&gt;--%>
                    <%--&lt;%&ndash;<div class="tdiv_s">&ndash;%&gt;--%>

                        <%--&lt;%&ndash;<a id="relevanceButton" type="button" class="tdiv_sub_c">设置</a></div>&ndash;%&gt;--%>
                    <%--&lt;%&ndash;<div class="clearfloat"></div>&ndash;%&gt;--%>
                <%--&lt;%&ndash;</div>&ndash;%&gt;--%>
        <%--</c:otherwise>--%>
	<%--</c:choose>--%>
	<div class="clearfloat"></div>
	<hr class="hr_style"/>
	<div class="tdiv_a_z" >
		<div class="tdiv_a_1">收货地址选项：</div>
		<%--<div class="tdiv_a_3">--%>
			<%--<div class="left_tubiao warn"></div>--%>
			<%--<strong>提醒：如需修改收货地址可按设置键。</strong>--%>
		<%--</div>--%>
		<c:choose>
		<c:when test="${order.addressSetting==1}">
			<div class="tdiv_a_3">
				<input type="radio"  name="addressSetting" value="0" />与订购基本信息同步
			</div>
			<div class="tdiv_a_3">
				<input type="radio"  name="addressSetting" value="1" checked/>设置收货地址
			</div>
			<div class="tdiv_a_4" id="addressButton" >
				<label  class="btn btn-info" data-toggle="modal" data-target="#myModal" >设置</label>
			</div>
		</c:when>
		<c:otherwise>
			<div class="tdiv_a_3">
				<input type="radio"  name="addressSetting" value="0" checked/>与订购基本信息同步
			</div>
			<div class="tdiv_a_3">
				<input type="radio"  name="addressSetting" value="1"/>设置收货地址
			</div>
			<div class="tdiv_a_4" id="addressButton" style="display: none">
				<label  class="btn btn-info" data-toggle="modal" data-target="#myModal" >设置</label>
			</div>
		</c:otherwise>
		</c:choose>
    </div>
		<div class="clearfloat"></div>
    <div class="tdiv_bottom" ></div>
</div>
</form>


<div class="tdiv" >
	<div class="tdiv_title"><p>选购</p></div>
	<form id = "productForm">
	<div>
	<div class="tdiv_a_z">
		<div  class="tdiv_a_1">产品名称：</div>
		<c:forEach items="${productLists}" var="productLists" varStatus="xh">
			<!-- 公安数字证书 -->
			<c:if test="${productLists.id==1}">
				<div  class="tdiv_a_3">
					<input type="radio" name="productradio" class="productName" value="${productLists.productName}`${productLists.productType}`${productLists.labelSuffix}`${productLists.supplier}`${productLists.id}"/>${productLists.productName}
				</div>
			</c:if>
		</c:forEach>
		<c:forEach items="${productLists}" var="productLists" varStatus="xh">
			<!-- 公安指纹数字证书 -->
			<c:if test="${productLists.id==4}">
				<div  class="tdiv_a_3">
					<input type="radio" name="productradio" class="productName" value="${productLists.productName}`${productLists.productType}`${productLists.labelSuffix}`${productLists.supplier}`${productLists.id}"/>${productLists.productName}
				</div>
			</c:if>
		</c:forEach>
		<c:forEach items="${productLists}" var="productLists" varStatus="xh">
			<!-- 专网数字证书 -->
			<c:if test="${productLists.id==6}">
				<div  class="tdiv_a_3">
					<input type="radio" name="productradio" class="productName" value="${productLists.productName}`${productLists.productType}`${productLists.labelSuffix}`${productLists.supplier}`${productLists.id}"/>${productLists.productName}
				</div>
			</c:if>
		</c:forEach>
		<c:forEach items="${productLists}" var="productLists" varStatus="xh">
			<%--配件--%>
			<c:if test="${productLists.id==0}">
				<div  class="tdiv_a_3">
					<input type="radio" name="productradio" class="productName" value="${productLists.productName}`${productLists.productType}`${productLists.labelSuffix}`${productLists.supplier}`${productLists.id}"/>${productLists.productName}
				</div>
			</c:if>
		</c:forEach>
		<div class="clearfloat"></div>
		<div class="tdiv_a_1"></div>
		<c:forEach items="${productLists}" var="productLists" varStatus="xh">
			<!-- 警辅数字证书 -->
			<c:if test="${productLists.id==2}">
				<div  class="tdiv_a_3">
					<input type="radio" name="productradio" class="productName" value="${productLists.productName}`${productLists.productType}`${productLists.labelSuffix}`${productLists.supplier}`${productLists.id}"/>${productLists.productName}
				</div>
			</c:if>
		</c:forEach>
		<c:forEach items="${productLists}" var="productLists" varStatus="xh">
			<!-- 警辅指纹数字证书 -->
			<c:if test="${productLists.id==5}">
				<div  class="tdiv_a_3">
					<input type="radio" name="productradio" class="productName" value="${productLists.productName}`${productLists.productType}`${productLists.labelSuffix}`${productLists.supplier}`${productLists.id}"/>${productLists.productName}
				</div>
			</c:if>
		</c:forEach>
		<c:forEach items="${productLists}" var="productLists" varStatus="xh">
			<!-- 公安、警辅数字证书解锁卡 -->
			<c:if test="${productLists.id==3}">
				<div  class="tdiv_a_3">
					<input type="radio" name="productradio" class="productName" value="${productLists.productName}`${productLists.productType}`${productLists.labelSuffix}`${productLists.supplier}`${productLists.id}"/>${productLists.productName}
				</div>
			</c:if>
		</c:forEach>
		<%-- <c:forEach items="${productLists}" var="productLists" varStatus="xh">
			<c:if test="${(xh.index-1)%4==0 && xh.index!=1 && !xh.first}">
				<div class="clearfloat"></div>
				<div class="tdiv_a_1"></div>
			</c:if>
			<c:if test="${productLists.id!=0}">
				<div  class="tdiv_a_3">
					<input type="radio" name="productradio" class="productName" value="${productLists.productName}`${productLists.productType}`${productLists.labelSuffix}`${productLists.supplier}`${productLists.id}"/>${productLists.productName}
				</div>
			</c:if>
		</c:forEach>
		配件
		<c:forEach items="${productLists}" var="productLists" varStatus="xh">
			<c:if test="${productLists.id==0}">
				<div  class="tdiv_a_3">
					<input type="radio" name="productradio" class="productName" value="${productLists.productName}`${productLists.productType}`${productLists.labelSuffix}`${productLists.supplier}`${productLists.id}"/>${productLists.productName}
				</div>
			</c:if>
		</c:forEach> --%>

		<div class="clearfloat"></div>
		<div id = productTable></div>

		<input type="hidden" id="productName" name="productName" />
		<input type="hidden" id="provinceCode" name="provinceCode" />
		<input type="hidden" id="cityCode" name="cityCode"/>
		<input type="hidden" name="id" value="${orderId}"/>

		<input type="hidden" id="productListId" name="productListId"/>

	</div>
	</div>
		</form>
	<div class="clearfloat"></div>
	<hr class="hr_style">
	<div class="tdiv_a_z">
		<div class="tdiv_a_left">
			<div class="left_tubiao warn"></div><strong>提醒：如证书编号不详，可联系上级单位或市局信通部门。谢谢！</strong>
		</div>
	</div>

	<%--<div class="tdiv_a_z">--%>
		<%--<div class="tdiv_a_1"></div>--%>
		<%--<div class="tdiv_a_3">--%>
			<%--<label class="btn btn-info" onclick="javascript:addProduct();">加入订购</label>--%>
		<%--</div>--%>
		<%--<div class="tdiv_a_left">--%>
			<%--<div class="left_tubiao warn"></div><strong>提醒：如证书编号不详，可联系上级单位或市局信通部门。谢谢！</strong>--%>
		<%--</div>--%>
	<%--</div>--%>
	<div class="clearfloat"></div>
	<div class="tdiv_bottom"></div>
</div>






		<div class="down_area">
			<div class="tdiv" >
			<div class="tdiv_title"><p>已选产品</p></div>
            <div class="neir_1_kcgl neir_2_kcgl_bod2">
			<table width="100%" class="table" id="tableProductList" border="0" cellpadding="0" cellspacing="0">
				<%--<thead class="thead">--%>
				<tr>
					<th class="table_td th">产品名称</th>
					<th class="table_td th">产品型号</th>
					<th class="table_td th">数量(支)</th>
					<th class="table_td th">单价(元)</th>
					<th class="table_td th">金额(元)</th>
					<th class="table_td th">编号</th>
					<th class="table_td th" style="width:20%">操作</th>
				</tr>
					<%--</thead>--%>
                    <tbody class = "tbody context1">
                    <c:forEach items="${orderProducts}" var="orderProduct">
                        <c:if test="${orderProduct.productListId !=0}">
                            <tr  class="nonePart">
                                <td class="table_td">${orderProduct.productName}</td>
                                <td class="table_td productType">${orderProduct.productType}</td>
                                <td class="table_td tableProductNumber">${orderProduct.productNumber}</td>
                                <td class="table_td productPrice">${orderProduct.productPrice}</td>
                                <td class="table_td tableProductAmount">${orderProduct.productAmount}</td>
                                <td class="table_td">${orderProduct.labelStart}-${orderProduct.labelEnd}</td>
                                <td class="table_td"><a href="javascript:void(0)" onclick="javascript:deleteUnit(this,'${orderProduct.id}')">删除</a></td>
                            </tr>
                        </c:if>
                    </c:forEach>
                    </tbody>
                    <tbody class="tbody contextTotal1">

                    </tbody>
                    <tbody class = "tbody context2">
                    <c:forEach items="${orderProducts}" var="orderProduct">
                        <c:if test="${orderProduct.productListId ==0}">
                            <tr  class="donePart">
                                <td class="table_td">${orderProduct.productName}</td>
                                <td class="table_td productType">${orderProduct.productType}</td>
                                <td class="table_td tableProductNumber">${orderProduct.productNumber}</td>
                                <td class="table_td productPrice">${orderProduct.productPrice}</td>
                                <td class="table_td tableProductAmount">${orderProduct.productAmount}</td>
                                <td class="table_td">${orderProduct.labelStart}-${orderProduct.labelEnd}</td>
                                <td class="table_td"><a href="javascript:void(0)" onclick="javascript:deleteUnit(this,'${orderProduct.id}')">删除</a></td>
                            </tr>
                        </c:if>
                    </c:forEach>
                    </tbody>
                    <tbody class="tbody contextTotal2">

                    </tbody>
                    </table>
				<div class="clearfloat"></div>
				<div class="tdiv_a_z">
					<div class="tdiv_a_3 tdiv_bottom" style="margin-left: 10px;"><div class="warn"></div><strong>注：证书产品10支起售，保质期：1年</strong></div>
				</div>
				<div class="clearfloat"></div>
                </div>
				</div>
            </div>

        <form id="remitterForm">
            <div class="tdiv" >
                <div class="tdiv_title"><p>开票信息</p></div>

                <div class="clearfloat"></div>
                <div class="tdiv_a_z">
					<c:choose>
						<c:when test="${order.invoiceType==0}">
							<div class="tdiv_a_1">开票信息：</div>
							<div class="tdiv_a_3">
								<input type="radio"  name="invoiceType" value="1" />增值税普通发票
							</div>
							<div class="tdiv_a_3">
								<input type="radio"  name="invoiceType" value="0" checked/>增值税专用发票
							</div>
						</c:when>
						<c:otherwise>
							<div class="tdiv_a_1">开票信息：</div>
							<div class="tdiv_a_3">
								<input type="radio"  name="invoiceType" value="1" checked/>增值税普通发票
							</div>
							<div class="tdiv_a_3">
								<input type="radio"  name="invoiceType" value="0" />增值税专用发票
							</div>
						</c:otherwise>
					</c:choose>

                </div>
                    <div class="clearfloat"></div>
                    <div class="tdiv_a_z">
                        <c:choose>
                            <c:when test="${order.remitterType==1}">
                                <div class="tdiv_a_1">汇款类型：</div>
                                <div class="tdiv_a_3">
                                    <input type="radio"  name="remitterType" value="0"/>单位
                                </div>
                                <div class="tdiv_a_3">
                                    <input type="radio"  name="remitterType" value="1" checked />个人
                                </div>
                            </c:when>
                            <c:otherwise>
                                <div class="tdiv_a_1">汇款类型：</div>
                                <div class="tdiv_a_3">
                                    <input type="radio"  name="remitterType" value="0" checked/>单位
                                </div>
                                <div class="tdiv_a_3">
                                    <input type="radio"  name="remitterType" value="1" />个人
                                </div>
                            </c:otherwise>
                        </c:choose>
                    </div>
                    <div class="clearfloat"></div>
                    <div class="tdiv_a_z">
                        <div class="tdiv_a_1" id="remitterName">汇款单位名称：</div>
                        <div class="tdiv_a_2">
							<c:choose>
								<c:when test="${not empty order.remitter}">
									<input type="text" class = "form-control" id="remitter" name="remitter" style="width: 350px;" value="${order.remitter}" />
								</c:when>
								<c:when test="${not empty sessionScope.gaUser.orderUnitName}">
									<input type="text" class = "form-control" id="remitter" name="remitter" style="width: 350px;" value="${sessionScope.gaUser.orderUnitName}"/>
								</c:when>
								<c:otherwise>
									<input type="text" class = "form-control" id="remitter" name="remitter" style="width: 350px;"/>
								</c:otherwise>
							</c:choose>
                            <span class="red_label">★</span>
                        </div>

                    </div>
                    <div class="clearfloat"></div>
                <div class="tdiv_a_z">
                    <div class="tdiv_a_1">备注：</div>
                    <div class="tdiv_a_2 ">
                        <textarea id="remark" name="remark" style="margin: 0px; width: 530px; height: 75px;"></textarea>
                    </div>
					<div class="tdiv_a_3"><input type="button" class="btn btn-info" onclick="addOrderSave()" value="保存" style="margin-top: 11%;margin-left: 25%;"></div>
					<div class="clearfloat"></div>
					<hr class="hr_style"/>
					<div class="tdiv_a_z">
						<div class="tdiv_a_4">
							<div class="tdiv_a_left"><label class="btn btn-info bottom" data-toggle="modal" data-target="#invoiceDetailsModal">填写开票信息</label></div>
						</div>
						<div class="tdiv_a_4">
							<div class="tdiv_a_left"><label class="btn btn-info bottom" data-toggle="modal" data-target="#invoiceModal">查看开票信息</label></div>
						</div>
							<div class="tdiv_a_4"><div class="tdiv_a_left left_tubiao warn"></div><strong>提醒：订购单位为非企业单位的可以不开具纳税人识别号，具体以订购单位财务为准。</strong></div>
					</div>
				</div>
                <div class="clearfloat"></div>
				<div class="tdiv_bottom"></div>

			</div>
        </form>

    <div class="tdiv" >
        <div class="tdiv_title"><p>订单盖章上传</p></div>
            <div class="tdiv_a_z">
				<div class="tdiv_a_4">
					<div class="tdiv_a_left">
						<strong>请先下载证书订购单，盖章后请将订单扫描件上传，最后确认下单。</strong>
					</div>
				</div>
                <div class="tdiv_a_4">
                    <label class="btn btn-info bottom" onclick="downloadOrder()">下载订单</label>
                </div>
            </div>
            <div class="clearfloat"></div>
		<hr class="hr_style"/>
        <form id="uploadForm" enctype="multipart/form-data" method="post">
            <input type="hidden" name="orderId" value="${orderId}"/>
            <div class="tdiv_a_z" style="margin-top: 28px;">
                <div class="tdiv_a_1">
                    盖章后订单上传：
                </div>
                <%--<div class="tdiv_a_2">--%>
				<%--<input type="file" id="zipFile" name="zipFile" class="uplado_input_t" >--%>
                <%--<input id="fileName" type="text" value="${order.fileName}"/>--%>
			<%--</div>--%>
			<%--<div class="tdiv_a_1">--%>
				<%--<input type="button" onclick="uploadOrder()" value="上传"/>--%>
			<%--</div>--%>
			<div class="tdiv_a_2" style="width: 580px;">
                <span>
                    <input type="text"  name="fileName" id = "fileName" style="height: 33px;width: 280px" value="${order.fileName}" class = "form-control viewfile"/>
                </span>
                <label  class="btn btn-info bottom" style="margin-left: 5px;">选择文件</label>
				<label  class="btn btn-info bottom" style="margin-left: 10px;" onclick="uploadOrder()">上传</label>
                <input type="file" id="zipFile" name="zipFile" style="margin-left: -1%" multiple="multiple" onchange="document.getElementById('fileName').value=this.value;" class="file">
            </div>
            <%--<div >--%>
                <%--<p class="clear"><input class="btn btn-primary submit" type="button" onclick="uploadOrder()" value="上传"/></p>--%>
            <%--</div>--%>
		</div>
		<div class="tdiv_a_z">
			<div class="tdiv_a_1"></div>
			<div class="tdiv_a_2">
				<div class="container">
					<div class="demo">
						<div style="width: 610px; height: auto; /*border: 1px solid #e1e1e1;*/ /*padding: 10px;*/">
							<%--<span id="spanButtonPlaceholder"></span>--%>
							<%--<div id="divFileProgressContainer"></div>--%>
							<div id="thumbnails">
								<ul id="pic_list" style="margin: 5px;margin-left: -10%;">
									<c:forEach items="${orderUploadFiles}" var="uploadFile">
										<li id="img_${uploadFile.id}">
											<img class="content" onclick="clickImg(this.src)" src="/file/ioReadImage.do?id=${uploadFile.id}" style="width:100px;height:100px;">
											<img class="button" onclick="_deleteOrderUnloadFiles('${uploadFile.id}')" src="/js/swf/images/fancy_close.png" title="删除">
										</li>
									</c:forEach>
								</ul>
								<div style="clear: both;"></div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	<div class="clearfloat"></div>
	</form>
</div>

<%--<div class="tdiv">--%>
	<%--<div class="tdiv_title"></div>--%>
	<%--<div class="tdiv_s">--%>
		<%--<div class="tdiv_sub_r" onclick="addOrderSave()"  style="cursor : pointer!important;"><p>保存</p></div>--%>
		<%--&lt;%&ndash;卖家编辑不需要显示&ndash;%&gt;--%>
        <%--<c:if test="${hidePostButton!=true}">--%>
            <%--<div class="tdiv_sub_c"  onclick="addOrder()" style="cursor : pointer!important;"><p>确定下单</p></div>--%>
        <%--</c:if>--%>
		<%--<div class="clearfloat"></div>--%>
	<%--</div>--%>
<%--</div>--%>
<div class="neir_2">
	<div class="col-sm" style="text-align: center;">
		<input type="button" class="btn btn-info btn-lg" style="cursor : pointer!important;" onclick="addOrderSave()" value="保存"/>
		<c:if test="${hidePostButton!=true}">
			<input type="button" class="btn btn-info btn-lg" onclick="addOrder()"  style="cursor : pointer!important;" value="确定下单"/>
		</c:if>
	</div>
</div>
<div class="neir_bottom"></div>


        <%--注意事项--%>
		<%--<div class="down_area">--%>
			<%--<table width="100%" border="0" cellpadding="0" cellspacing="0" style="background-color: #d5d5d5;border-left: 1px solid;border-right: 1px solid;border-bottom: 1px solid;">--%>
				<%--<tr style="background-color: #d5d5d5;border:1px solid">--%>
					<%--<td>注意事项</td>--%>
				<%--</tr>--%>
				<%--<tr style="background-color: #d5d5d5">--%>
					<%--<td align="left">一、请您以采购单位的名称汇款，若需以其他单位或个人的名称汇款，请务必在订购单位的其他栏中注明，或在采购合同的付款方式条款中约定，并在汇款时注明订购单位全称及用途，并及时通知本公司，以便确认汇款来源。</td>--%>
				<%--</tr>--%>
				<%--<tr style="background-color: #d5d5d5">--%>
					<%--<td  align="left">二、为了配合国家相关部门的检查，请您务必在订购单上加盖单位公章。</td>--%>
				<%--</tr>--%>
				<%--<tr style="background-color: #d5d5d5">--%>
					<%--<td align="left">三、若贵单位需委托其他非公安单位代购数字证书，为了满足国家相关部门的规定，需贵单位为被委托单位出具授权书，注明被授权单位全称，证书类型及采购数量。并加盖贵单位公章后传真本公司。授权书格式请见附件一或由贵单位自行设定。</td>--%>
				<%--</tr>--%>


			<%--</table>--%>
		<%--</div>--%>


<!-- 收货地址关联框（Modal） -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" >
	<div class="modal-dialog modal-lg" role="document">
		<div class="modal-content" style="width: 100%">
			<div class="modal-header">
				<button type="button" class="close addressClose" id="" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
				<h4 class="modal-title" id="myModalLabel">收货地址设置</h4>
				<br>
				<iframe src="<%=path%>/commonUserAddress/showAddressInfo.do" id = "callback" name="callback" style="height: 500px;width: 100%"></iframe>
			</div>
		</div>
	</div>
</div>

<!-- 查看开票信息模态框（Modal） -->
<div class="modal fade bs-example-modal-lg" id="invoiceModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"  aria-hidden="true" data-backdrop="static" style="display: none;">
	<div class="modal-dialog modal-lg" role="document">
		<div class="modal-content" style="width: 100%">
			<div class="modal-header">
				<button type="button" class="close addressClose" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
				<h4 class="modal-title" >开票信息列表</h4>
				<br>
				<iframe src="<%=path%>/commonUserOrder/chooseInvoice.do?orderId=${orderId}" id = "iframeInvoice" name="iframeInvoice" style="height: 500px;width: 100%"></iframe>
			</div>

		</div>
	</div>
</div>

<div class="modal fade bs-example-modal-lg" id="invoiceDetailsModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"  aria-hidden="true" data-backdrop="static" style="display: none;">
	<div class="modal-dialog modal-lg" role="document">
		<div class="modal-content" style="width: 1000px">
			<div class="modal-header">
				<button type="button" class="close invoiceDetailsClose" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
				<h4 class="modal-title" >开票信息填写</h4>
				<br>
				<iframe src="<%=path%>/commonUserOrder/createInvoice.do?orderId=${orderId}" id = "iframeCreateInvoice" name="iframeCreateInvoice" style="height: 500px;width: 100%"></iframe>
			</div>
			</div>

		</div>
	</div>


<script type="text/javascript">
	var gaUserType = ${gauser.userType};
	if(${sessionScope.gaUser.userType==0}){  //用户隐藏 台湾深圳香港
		$("#areaProvice option[value='710000']").hide();
		$("#areaProvice option[value='810000']").hide();
		$("#areaProvice option[value='820000']").hide();
	}


	window.defaultUnitName = "";	//输入订购单位默认值
//    $("#remitter").val($("#orderUnitName").val());
	$("#orderUnitName").change(function () {	//汇款单位名称默认订购单位输入
        defaultUnitName = $(this).val();
		var ss = $('input:radio[name=remitterType]:checked').val();
		if($('input:radio[name=remitterType]:checked').val()==0) {  //选择汇款单位需要同步
			$("#remitter").val(defaultUnitName);
		}

		if($("input[name=addressSetting]:checked").val()==0){
			$("#receiveUnitName").text(defaultUnitName);
		}

	})
    $("#orderUnitAddress").change(function () {	//收货地址信息填充
        var defaultUnitAddress = $(this).val();
        var receiveUnitAddress = $("#receiveUnitAddress").text();
		if($("input[name=addressSetting]:checked").val()==0){
			$("#receiveUnitAddress").text(defaultUnitAddress);
		}
    })
    $("#purchaser").change(function () {	//收货地址信息填充
        var defaultPurchaser = $(this).val();
        var receiveName = $("#receiveName").text();
		if($("input[name=addressSetting]:checked").val()==0){
			$("#receiveName").text(defaultPurchaser);
		}
    })
    $("#purchaserMobile").change(function () {	//收货地址信息填充
        var purchaserMobile = $(this).val();
        var receiveMobile = $("#receiveMobile").text();
		if($("input[name=addressSetting]:checked").val()==0){
			$("#receiveMobile").text(purchaserMobile);
		}

    })
    $("#purchaserPhone").change(function () {	//收货地址信息填充
        var purchaserPhone = $(this).val();
        var receivePhone = $("#receivePhone").text();
		if($("input[name=addressSetting]:checked").val()==0){
			$("#receivePhone").text(purchaserPhone);
		}
    })

	$(function () {
		

		<%--window.$orderProductsMap = new Map();--%>

//		$("#relevanceButton").on('click',function () {
//			$("#myModal").modal('show');
//		})
		$("#remark").text("${order.remark}");
		//地址模态框
		$("#myModal").on('hide.bs.modal',function (event) {
			var modal = $(this);
			var url = '<%=path%>/commonUserAddress/showAddressInfo.do';
			modal.find('iframe').attr('src',url);
		})


		$("#myModal").on('show.bs.modal',function (event) {
			//隐藏默认
//			$(".showSpan",window.frames["callback"].document).hide();
			//显示选择
//			$(".hideSpan",window.frames["callback"].document).show();

		})
		$("#invoiceDetailsModal").on('show.bs.modal',function (event) {
			$("#invoiceUnitName",window.frames["iframeCreateInvoice"].document).val($("#orderUnitName").val());
		})
        $("#invoiceModal").on('hide.bs.modal',function (event) {
			var modal = $(this);
			var url = '<%=path%>/commonUserOrder/chooseInvoice.do?orderId=${orderId}';
			modal.find('iframe').attr('src',url);
			//刷新开票新建界面
			url = '<%=path%>/commonUserOrder/createInvoice.do?orderId=${orderId}';
			$("#invoiceDetailsModal").find('iframe').attr('src',url);
		})
        $("#invoiceDetailsModal").on('hide.bs.modal',function (event) {
			var modal = $(this);
			var url = '<%=path%>/commonUserOrder/createInvoice.do?orderId=${orderId}';
			modal.find('iframe').attr('src',url);
            //刷新开票列表界面
            var url = '<%=path%>/commonUserOrder/chooseInvoice.do?orderId=${orderId}';
            $("#invoiceModal").find('iframe').attr('src',url);
		})


		//模态框刷新页面
		$(".addressClose").click(function () {

//			$("#receiveUnitName").text($("#receiveUnitName",window.frames["callback"].document).val());
//			$("#receiveUnitAddress").text($("#receiveUnitAddress",window.frames["callback"].document).val());
//
//			$("#receiveName").text($("#receiveName",window.frames["callback"].document).val());
//			var receiveMobile = $("#receiveMobile",window.frames["callback"].document).val();
//			var receivePhone = $("#receivePhone",window.frames["callback"].document).val();
//			$("#receivePhone").text((receiveMobile!=undefined?receiveMobile:"")+"/"+(receivePhone!=undefined?receivePhone:""));
//			$("#receiveMobile").val(receiveMobile!=undefined?receiveMobile:"");
//
//			$("#standbyName").text($("#standbyName",window.frames["callback"].document).val());
//			var standbyMobile = $("#standbyMobile",window.frames["callback"].document).val();
//			var standbyPhone = $("#standbyPhone",window.frames["callback"].document).val();
//			$("#standbyPhone").text((standbyMobile!=undefined?standbyMobile:"")+"/"+(standbyPhone!=undefined?standbyPhone:""));

//			window.location.reload();
		})

		if($('input:radio[name=addressSetting]:checked').val()==0) {
			//信息同步
			$("#receiveUnitName").text($("#orderUnitName").val());
			$("#receiveUnitAddress").text($("#orderUnitAddress").val());
			$("#receiveName").text($("#purchaser").val());
			$("#receiveMobile").text($("#purchaserMobile").val());
			$("#receivePhone").text($("#purchaserPhone").val());
			$("#addressButton").hide();
		}
		$('input:radio[name=addressSetting]').click(function(){		//收货地址 同步选项
			if($('input:radio[name=addressSetting]:checked').val()==0) {
				//信息同步
				$("#receiveUnitName").text($("#orderUnitName").val());
				$("#receiveUnitAddress").text($("#orderUnitAddress").val());
				$("#receiveName").text($("#purchaser").val());
				$("#receiveMobile").text($("#purchaserMobile").val());
				$("#receivePhone").text($("#purchaserPhone").val());
				$("#standbyName").text(''); //备选为空
				$("#standbyMobile").text(''); //备选为空
				$("#standbyPhone").text(''); //备选为空
				$("#addressButton").hide();
			}
			if($('input:radio[name=addressSetting]:checked').val()==1) {
				$("#addressButton").show();
				var orderAddressId = $('#orderAddressId').val();		//id回传 重置收货地址列表选择按钮
				<%--//刷新开票新建界面--%>
				<%--url = '<%=path%>/commonUserOrder/createInvoice.do?orderId=${orderId}';--%>
				<%--$("#invoiceDetailsModal").find('iframe').attr('src',url);--%>
				$("#span_"+orderAddressId,window.frames["callback"].document).prop({"class":"btn btn-info isDefaultButton","disabled":false});
			}
		})

		$('input:radio[name=invoiceType]').click(function(){
			if($('input:radio[name=invoiceType]:checked').val()==0) {
				$(".red_labelHide", window.frames["iframeCreateInvoice"].document).show();
			}else{
				$(".red_labelHide", window.frames["iframeCreateInvoice"].document).hide();
			}
		})
		$('input:radio[name=remitterType]').click(function(){
			if($('input:radio[name=remitterType]:checked').val()==0){
				$("#remitterName").text("汇款单位名称：");
				$("#remitter").val($("#orderUnitName").val());
			}else{
				$("#remitterName").text("汇款人姓名：");
			}
		})
		
		window.$productPrefix = "";
		window.$productSuffixId = "";
		window.$tblAreaLabelSuffix = "";
		showPrefix($("#areaProvice").val());

        //产品列表总计显示计算
        tableOrderProductTotal();

		//是否显示订购地区下拉列表
		controlAreaSelect();

		//型号后缀
		$(".productName:radio").click(function () {
			$("#productType").empty();
			productSuffix = $(this).val().split("`");
			productSuffixProductName = productSuffix[0];
			productSuffixProductType = productSuffix[1];
			productSuffixProductSuffix = productSuffix[2];
			productSuffixProductsupplier = productSuffix[3];
			$productSuffixId = productSuffix[4];
			
//			showTblAreaLabelSuffix($productSuffixId);

			$("#provinceCode").val($("#areaProvice").val());
			$("#cityCode").val($("#areaCity").val());
			$("#productName").val(productSuffixProductName);

			$("#productListId").val($productSuffixId);

//				$("#productType").append('<option value="'+productSuffix[1]+'">'+productSuffix[1]+'</option>');
//				$("#labelSuffix").text(productSuffix[2]);
//				$("#supplier").text(productSuffix[3]);
//				$("#productPrefix").val($productPrefix.prefixName);
			var productTable = $("#productTable");
			var param = "";
			$(".removeProductTable").remove();


			productSuffixProductType = productSuffixProductType.split(",");
			param +=Objects.format("<div class=\" removeProductTable\">");
			param +=Objects.format("<div class=\"tdiv_a_z\">");
			param +=Objects.format("<div class=\"tdiv_a_1\">产品型号：</div>");
			param +=Objects.format("<div class=\"tdiv_a_2\">");
			param +=Objects.format("<select name=\"productType\" style=\"width:200px\"  id=\"productType\">");
			for( var type in productSuffixProductType){
				//公安指纹数字证书型号特殊
				if($productSuffixId=="4"){
					if($productPrefix!=null){
						if($productPrefix.prefixName=="GD"){
							param +=Objects.format("<option value=\"{0}\">{0}</option>",productSuffixProductType[1]);
							break;
						}else {
							param +=Objects.format("<option value=\"{0}\">{0}</option>",productSuffixProductType[0]);
							break;
						}
					}
				}
				param +=Objects.format("<option onclick=\"clickProductType('{0}')\" value=\"{0}\">{0}</option>",productSuffixProductType[type]);
			}
			param +=Objects.format("</select>");
			param +=Objects.format("<span class=\"red_label\">★</span>");
			param +=Objects.format("</div>");

			param +=Objects.format("<div class=\"tdiv_a_1\">");
			param +=Objects.format("单价(元)：");
			param +=Objects.format("</div>");
			param +=Objects.format("<div class=\"tdiv_a_2\">");
			if(productSuffixProductType[0]=='延长线'){
				//管理员gaUserType=1，用户=0
				if(gaUserType==1) {
					param +=Objects.format("<input type=\"text\" name=\"productPrice\" value='4' onkeyup=\"this.value=this.value.replace(/[^\\d.]/g,'')\" /><span class=\"red_label\">★</span>");
				} else {
					param +=Objects.format("<input type=\"text\" name=\"productPrice\" readonly=\"readonly\"  style='background-color:gainsboro' value='4' onkeyup=\"this.value=this.value.replace(/[^\\d.]/g,'')\" /><span class=\"red_label\">★</span>");
				}
			}else if(productSuffixProductType[0]=='吊带'){
				if(gaUserType==1) {
					param +=Objects.format("<input type=\"text\" name=\"productPrice\" value='2' onkeyup=\"this.value=this.value.replace(/[^\\d.]/g,'')\" /><span class=\"red_label\">★</span>");
				} else {
					param +=Objects.format("<input type=\"text\" name=\"productPrice\" readonly=\"readonly\"  style='background-color:gainsboro' value='2' onkeyup=\"this.value=this.value.replace(/[^\\d.]/g,'')\" /><span class=\"red_label\">★</span>");
				}
			}else {
				param +=Objects.format("<input type=\"text\" name=\"productPrice\"onkeyup=\"this.value=this.value.replace(/[^\\d.]/g,'')\" /><span class=\"red_label\">★</span>");
			}
			param +=Objects.format("</div>");
			param +=Objects.format("<div class=\"clearfloat\"></div>");
			
			//配件
			if($productSuffixId=="0"){
				param +=Objects.format("<div class=\"tdiv_a_z\">");
				param +=Objects.format("<div class=\"tdiv_a_1\">");
				param +=Objects.format("数量(个)：");
				param +=Objects.format("</div>");
				param +=Objects.format("<div class=\"tdiv_a_2\">");
				param +=Objects.format("<input type=\"text\"  name=\"productNumber\" onkeyup=\"this.value=this.value.replace(/\\D/g,'')\" />");
				param +=Objects.format("</div>");
				param +=Objects.format("<div class=\"tdiv_a_1\">");
//				/*param +=Objects.format("<input type=\"button\" class=\"button_left15\" onclick=\"javascript:addProduct();\" value=\"加入已选\"/>");*/
				param +=Objects.format("<input type=\"button\" class=\"btn btn-info \" onclick=\"javascript:addProduct();\" value=\"加入订购\"/>");
				param +=Objects.format("</div>");
				param +=Objects.format("<div class=\"clearfloat\"></div>");
				param +=Objects.format("</div>");
				productTable.append(param);
				return;
			}

			//公安，辅警数字证书
			if($productSuffixId=="3"){
				$("#supplier").val(productSuffixProductsupplier);
				$("#labelSuffix").val(productSuffixProductSuffix);
				param +=Objects.format("<div class=\" removeProductTable\">");
				param +=Objects.format("<div class=\"tdiv_a_z\" style=\"min-width: 1000px;\">");
				param +=Objects.format("<div class=\"tdiv_a_1\">起始编号：</div>");
				param +=Objects.format("<input type=\"text\" name=\"labelPrefix\" class=\"spanLabelLeft\" id=\"labelPrefix\"  style=\"width: 30px\" value=\"{0}\"/>",$productPrefix!=null?($productPrefix.prefixName!=undefined?$productPrefix.prefixName:""):"");

				param +=Objects.format("<input type=\"text\" id=\"supplier\" class=\"spanLabel\" readonly=\"readonly\" name=\"supplier\" value=\"{0}\"/>",productSuffixProductsupplier);
				param +=Objects.format("<input type=\"text\"  name=\"productRegionLabelInfix\" onkeyup=\"this.value=this.value.replace(/\\D/g,'')\" class=\"width_30\">");
				param +=Objects.format("<input type=\"text\" id=\"labelSuffix\" class=\"spanLabel\" readonly=\"readonly\" name=\"labelSuffix\" value=\"{0}\"/>",productSuffixProductSuffix);
				param +=Objects.format("<input type=\"text\" name=\"labelNumStart\" onkeyup=\"this.value=this.value.replace(/\\D/g,'')\" class=\"width_30\">");
				param +=Objects.format("-");
				param +=Objects.format("<input type=\"text\" name=\"labelNumEnd\" onkeyup=\"this.value=this.value.replace(/\\D/g,'')\" class=\"width_30\">");
				param +=Objects.format("<input type=\"button\" class=\"btn btn-info \" onclick=\"javascript:addProduct();\" value=\"加入订购\"/>");
				param +=Objects.format("</td>");
				/*param +=Objects.format("<input type=\"button\" class=\"button_left15\" onclick=\"javascript:addProduct();\" value=\"加入已选\"> </td>");*/
				param +=Objects.format("</div>");
				productTable.append(param);
				return;
			}
			//专网数字证书
			if($productSuffixId=="6"){
				$("#supplier").val("");
				$("#labelSuffix").val(productSuffixProductSuffix);
				param +=Objects.format("<div class=\" removeProductTable\">");
				param +=Objects.format("<div class=\"tdiv_a_z\" style=\"min-width: 1000px;\">");
				param +=Objects.format("<div class=\"tdiv_a_1\">起始编号：</div>");
                param +=Objects.format("<input type=\"text\" id=\"labelSuffix\" class=\"spanLabelLeft\" style='    background-color: gainsboro;' readonly=\"readonly\" name=\"labelSuffix\" value=\"{0}\"/>",productSuffixProductSuffix);
				param +=Objects.format("<input type=\"text\" name=\"labelPrefix\" class=\"spanLabelLeft\" id=\"labelPrefix\"  style=\"width: 30px\" value=\"{0}\"/>",$productPrefix!=null?($productPrefix.prefixName!=undefined?$productPrefix.prefixName:""):"");

				param +=Objects.format("<input name=\"labelNumStart\" onkeyup=\"this.value=this.value.replace(/\\D/g,'')\"  type=\"text\">");
				param +=Objects.format("-");
				param +=Objects.format("<input name=\"labelNumEnd\" onkeyup=\"this.value=this.value.replace(/\\D/g,'')\" type=\"text\">");
				/*param +=Objects.format("<input type=\"button\" class=\"button_left15\" onclick=\"javascript:addProduct();\" value=\"加入已选\"> </td>");*/
				param +=Objects.format("<input type=\"button\" class=\"btn btn-info \" onclick=\"javascript:addProduct();\" value=\"加入订购\"/>");
				param +=Objects.format("</td>");
				param +=Objects.format("</p></div>");
				productTable.append(param);
				return;
			}
			//公安指纹数字证书
			if($productSuffixId=="4"){

				param +=Objects.format("<div class=\" removeProductTable\">");
				param +=Objects.format("<div class=\"tdiv_a_z\" style=\"min-width: 1000px;\">");
				param +=Objects.format("<div class=\"tdiv_a_1\">起始编号：</div>");
				param +=Objects.format("<input type=\"text\" class='spanLabelLeft' name=\"labelPrefix\" id=\"labelPrefix\"  style=\"width: 30px\" value=\"{0}\"/>",$productPrefix!=null?($productPrefix.prefixName!=undefined?$productPrefix.prefixName:""):"");
				//浙江后缀为GZ 无中间字符
				if($productPrefix!=null && $productPrefix.prefixName=="ZJ"){
					param +=Objects.format("<input name=\"labelNumStart\" onkeyup=\"this.value=this.value.replace(/\\D/g,'')\"  type=\"text\">");
					param +=Objects.format("-");
					param +=Objects.format("<input name=\"labelNumEnd\" onkeyup=\"this.value=this.value.replace(/\\D/g,'')\"  type=\"text\">");

					param +=Objects.format("<input type=\"text\" class='spanLabel' readonly=\"readonly\" id=\"labelSuffix\" name=\"labelSuffix\" value=\"{0}\"/>",productSuffixProductSuffix.split(",")[1]);
				}else {
					param +=Objects.format("<input type=\"text\" class='spanLabel' readonly=\"readonly\" id=\"supplier\" name=\"supplier\" value=\"{0}\"/>",productSuffixProductsupplier);
					param +=Objects.format("<input name=\"labelNumStart\" onkeyup=\"this.value=this.value.replace(/\\D/g,'')\"  type=\"text\">");
					param +=Objects.format("-");
					param +=Objects.format("<input name=\"labelNumEnd\" onkeyup=\"this.value=this.value.replace(/\\D/g,'')\"  type=\"text\">");
					param +=Objects.format("<input type=\"text\" class='spanLabel' readonly=\"readonly\" id=\"labelSuffix\" name=\"labelSuffix\" value=\"{0}\"/>",productSuffixProductSuffix.split(",")[0]);
				}
				param +=Objects.format("<input type=\"button\" class=\"btn btn-info \" onclick=\"javascript:addProduct();\" value=\"加入订购\"/>");
				/*param +=Objects.format("<input type=\"button\" class=\"button_left15\" onclick=\"javascript:addProduct();\" value=\"加入已选\"> </td>");*/
				param +=Objects.format("</td>");
				param +=Objects.format("</p></div>");
				productTable.append(param);
				return;
			}

			//警辅指纹数字证书
			if($productSuffixId=="5"){

				param +=Objects.format("<div class=\" removeProductTable\">");
				param +=Objects.format("<div class=\"tdiv_a_z\" style=\"min-width: 1000px;\">");
				param +=Objects.format("<div class=\"tdiv_a_1\">起始编号：</div>");
				param +=Objects.format("<input type=\"text\" class='spanLabelLeft' name=\"labelPrefix\" id=\"labelPrefix\"  style=\"width: 30px\" value=\"{0}\"/>",$productPrefix!=null?($productPrefix.prefixName!=undefined?$productPrefix.prefixName:""):"");
				//浙江后缀为FZ 无中间字符
				if($productPrefix!=null && $productPrefix.prefixName=="ZJ"){
					param +=Objects.format("<input name=\"labelNumStart\" onkeyup=\"this.value=this.value.replace(/\\D/g,'')\"  type=\"text\">");
					param +=Objects.format("-");
					param +=Objects.format("<input name=\"labelNumEnd\" onkeyup=\"this.value=this.value.replace(/\\D/g,'')\"  type=\"text\">");

					param +=Objects.format("<input type=\"text\" class='spanLabel' readonly=\"readonly\" id=\"labelSuffix\" name=\"labelSuffix\" value=\"{0}\"/>",productSuffixProductSuffix.split(",")[1]);
				}else {
					param +=Objects.format("<input type=\"text\" class='spanLabel' readonly=\"readonly\" id=\"supplier\" name=\"supplier\" value=\"{0}\"/>",productSuffixProductsupplier);
					param +=Objects.format("<input name=\"labelNumStart\" onkeyup=\"this.value=this.value.replace(/\\D/g,'')\"  type=\"text\">");
					param +=Objects.format("-");
					param +=Objects.format("<input name=\"labelNumEnd\" onkeyup=\"this.value=this.value.replace(/\\D/g,'')\"  type=\"text\">");
					param +=Objects.format("<input type=\"text\" class='spanLabel' readonly=\"readonly\" id=\"labelSuffix\" name=\"labelSuffix\" value=\"{0}\"/>",productSuffixProductSuffix.split(",")[0]);
				}
				param +=Objects.format("<input type=\"button\" class=\"btn btn-info \" onclick=\"javascript:addProduct();\" value=\"加入订购\"/>");
				/*param +=Objects.format("<input type=\"button\" class=\"button_left15\" onclick=\"javascript:addProduct();\" value=\"加入已选\"> </td>");*/
				param +=Objects.format("</td>");
				param +=Objects.format("</p></div>");
				productTable.append(param);
				return;
			}else {
				param +=Objects.format("<div class=\" removeProductTable\">");
				param +=Objects.format("<div class=\"tdiv_a_z\" style=\"min-width: 1000px;\">");
				param +=Objects.format("<div class=\"tdiv_a_1\">起始编号：</div>");
				param +=Objects.format("<input type=\"text\" class='spanLabelLeft' name=\"labelPrefix\" id=\"labelPrefix\"  style=\"width: 30px\" value=\"{0}\"/>",$productPrefix!=null?($productPrefix.prefixName!=undefined?$productPrefix.prefixName:""):"");

				param +=Objects.format("<input type=\"text\" class='spanLabel' readonly=\"readonly\" id=\"supplier\" name=\"supplier\" value=\"{0}\"/>",productSuffixProductsupplier);
				param +=Objects.format("<input name=\"labelNumStart\" onkeyup=\"this.value=this.value.replace(/\\D/g,'')\"  type=\"text\">");
				param +=Objects.format("-");
				param +=Objects.format("<input name=\"labelNumEnd\" onkeyup=\"this.value=this.value.replace(/\\D/g,'')\"  type=\"text\">");
				param +=Objects.format("<input type=\"text\" class='spanLabel' readonly=\"readonly\" id=\"labelSuffix\" name=\"labelSuffix\" value=\"{0}\"/>",productSuffixProductSuffix);
				param +=Objects.format("<input type=\"button\" class=\"btn btn-info \" onclick=\"javascript:addProduct();\" value=\"加入订购\"/>");
				/*param +=Objects.format("<input type=\"button\" class=\"button_left15\" onclick=\"javascript:addProduct();\" value=\"加入已选\"> </td>");*/
				param +=Objects.format("</td>");
				param +=Objects.format("</p></div>");
				productTable.append(param);
				return;
			}
		})

		<%--获取用户首页是否勾选产品--%>
		var homeProduct = "${product}";
		if(!isNull(homeProduct)){	//不为空
			if(homeProduct=="0.1"){	//配件选中为延长线
				$(":radio[name='productradio']").eq($(":radio[name='productradio']").length-1).click();
			}else if(homeProduct=="0.2"){//配件选中为吊带
				$(":radio[name='productradio']").eq($(":radio[name='productradio']").length-1).click();
				$("select[id='productType']").prop("value","吊带");
			}
			//$(":radio[name='productradio']").eq(homeProduct-1).click();
			$(".productName:radio").eq(homeProduct-1).trigger("click");
		}
		//地区联动
		$("#areaProvice").change(function () {
			var areaProviceId = $(this).val();
			url = "<%=path%>/common/showCity.do?areaProviceId="+areaProviceId;
//				$.ajax({
//					url:url,
//					type:"GET",
//					cache:false,
//					data:{"areaProviceId": areaProviceId},
//					success:function (city) {
//						if (city == undefined) {
//							alert("未有该分类");
//						} else {
//							$("#areaCity").empty();
////							var cityVal = JSON.parse(city);
//							var cityVal = city;
//							for (var i = 0; i < cityVal.length; i++) {
//								$("#areaCity").append("<option value='" + cityVal[i].id + "'>" + cityVal[i].name + "</option>")
//							}
//						}
//					},error : function(XMLHttpRequest, textStatus, errorThrown) {
//						alert("异常，异常信息" + errorThrown);
//						}
//				})
			$.ajax({
				url:url,
				type:"GET",
				async: false,
				success: function(cityVal) {
					if (cityVal == undefined) {
						alert("未有该分类");
					} else {
						$("#areaCity").empty();
						//var cityVal = JSON.parse(city);
						for (var i = 0; i < cityVal.length; i++) {
							$("#areaCity").append("<option value='" + cityVal[i].id + "'>" + cityVal[i].name + "</option>")
						}
						showPrefix(areaProviceId);
					}
				}
			});
			if($(".productName:radio:checked").val()!=null) {
				$(".productName:radio:checked").trigger("click");
			}
			
			closeLoading();
		})
	})
	//配件延长线 价格填写
	$(document).on("change","#productType",function () {
		if($(this).val()=='延长线'){
			$("input[name=productPrice]").val('4');
		}else if($(this).val()=='吊带'){
			$("input[name=productPrice]").val('2');
		}
	})


	//订购地区下拉是否需要显示
	function controlAreaSelect() {
		//是否可用订购下拉
		if($("tr").hasClass("nonePart")){
			$("#areaProvice,#areaCity").attr("disabled",true).css({
				"background-color":"gainsboro"
			});
		}else{
			$("#areaProvice,#areaCity").attr("disabled",false).css({
				"background-color":"white"
			});
		}
	}
    //验证同一类型产品单价是否不一致
    function verifyProductPrice() {
        var value = null;
        //全局产品类型验证
        $.each($("#tableProductList .nonePart"),function () {
            if(value!=null){
                return true;
            }
            var productType=$(this).children("td.productType").text();
            var productPrice=$(this).children("td.productPrice").text();
            $.each($("#tableProductList .nonePart"),function () {
                if(productType!=$(this).children("td.productType").text()){
                    return true;
                }else if(productPrice!=$(this).children("td.productPrice").text()){
                    value = productType;
                    return false;
                }
            })
        })
        return value;
    }


	function showPrefix(areaProviceId) {
		openLoading();
		url = "<%=path%>/common/showPrefix.do?areaProviceId="+areaProviceId;
		$.ajax({
			url: url,
			dataType: 'json',
			async: false,
			success: function(data) {
				closeLoading();
				$productPrefix = data;
			}
		})
		closeLoading();
	}

	function showTblAreaLabelSuffix(productListId) {
		var tblAreaLabelPrefixId = $productPrefix.id;
		url = "<%=path%>/common/showTblAreaLabelSuffix.do?productListId="+productListId+"&tblAreaLabelPrefixId+"+tblAreaLabelPrefixId;
		Ajax.jsonGet(url,function (data) {
			$tblAreaLabelSuffix = data;
		})
	}


	function addProduct() {

		var priceRe = /^\d*\.{0,1}\d*$/;
		var productNumRe = /^\d{7}$/;
		var re=/^[A-Z]{2}\d{7}|[A-Z]{2}\d{4}JM\d{3}|[A-Z]{2}\d{2}JM\d{3}$/;

		//配件
		if($productSuffixId=="0") {
			if($("input[name=productNumber]").val()==""){
				alert("数量不能为空");
				return false;
			}else if(!priceRe.test($("input[name=productPrice]").val())||$("input[name=productPrice]").val()==""){
				alert("单价格式错误");
				return false;
			}
		}else if($productPrefix!=null && $productPrefix.otherPrefix.indexOf($("#labelPrefix").val())<0){
			alert("起始编号:前缀不符合要求");
			return false;
		}else if(gaUserType!=1 && $("#labelPrefix").val() == 'CR'){//用户不允许添加测试证书
			alert("起始编号:前缀不符合要求");
			return false;
		}else if($productSuffixId=="3"){ //公安辅警证书验证格式
			var productRegionLabelRe = /^((\d{4})|(\d{2}))$/;
//			var productRegionLabelNumRe = /^\d{0,3}$/;
			var productRegionLabelNumRe = /^\d{3}$/;
			if(!productRegionLabelRe.test($("input[name=productRegionLabelInfix]").val())||$("input[name=productRegionLabelInfix]").val()==""){
				alert("起始编号:证书机构编号不符合要求");
				return false;
			}else if(!priceRe.test($("input[name=productPrice]").val())||$("input[name=productPrice]").val()==""){
                alert("单价格式错误");
                return false;
            }else if(!productRegionLabelNumRe.test($("input[name=labelNumStart]").val())||$("input[name=labelNumStart]").val()==""){
				alert("起始编号:证书开始编号格式错误");
				return false;
			} else if(!productRegionLabelNumRe.test($("input[name=labelNumEnd]").val())||$("input[name=labelNumEnd]").val()==""){
				if($("input[name=labelNumEnd]").val()==""){
					$("input[name=labelNumEnd]").val($("input[name=labelNumStart]").val());
				}else{
					alert("结束编号:证书结束编号格式错误");
					return false;
				}
			}else if($("input[name=labelNumEnd]").val()-$("input[name=labelNumStart]").val()<0){
				alert("起始编号无法小于结束编号");
				return false;
			}
		}else if($productSuffixId=="6") { //专网证书验证格式
            var productNumRez = /^\d{6}$/;
            if(!priceRe.test($("input[name=productPrice]").val())||$("input[name=productPrice]").val()==""){
                alert("单价格式错误");
                return false;
            }else if(!productNumRez.test($("input[name=labelNumStart]").val())||$("input[name=labelNumStart]").val()==""){
                alert("起始编号:证书开始编号格式错误");
                return false;
            }else if(!productNumRez.test($("input[name=labelNumEnd]").val())||$("input[name=labelNumEnd]").val()==""){
				if($("input[name=labelNumEnd]").val()==""){
					$("input[name=labelNumEnd]").val($("input[name=labelNumStart]").val());
				}else{
					alert("结束编号:证书结束编号格式错误");
					return false;
				}
            }else if($("input[name=labelNumEnd]").val()-$("input[name=labelNumStart]").val()<0){
                alert("起始编号无法小于结束编号");
                return false;
            }
        } else{
			if(!priceRe.test($("input[name=productPrice]").val())||$("input[name=productPrice]").val()==""){
				alert("单价格式错误");
				return false;
			}else if(!productNumRe.test($("input[name=labelNumStart]").val())||$("input[name=labelNumStart]").val()==""){
				alert("起始编号:证书开始编号格式错误");
				return false;
			}else if(!productNumRe.test($("input[name=labelNumEnd]").val())||$("input[name=labelNumEnd]").val()==""){
				if($("input[name=labelNumEnd]").val()==""){
					$("input[name=labelNumEnd]").val($("input[name=labelNumStart]").val());
				}else{
					alert("结束编号:证书结束编号格式错误");
					return false;
				}
			}else if($("input[name=labelNumEnd]").val()-$("input[name=labelNumStart]").val()<0){
				alert("起始编号无法小于结束编号");
				return false;
			}
		}

		if($productSuffixId=="0"){
			//配件类型为产品名称
			$("#productName").val($("#productType").val());
		}
//			if($productSuffixId!="0"){
//				if(isNULL("#cerNumber")){
//					alert("请填写证书编号");
//					return false;
//				}
		openLoading();

		$.ajax({
			url: "<%=path%>/commonUserOrder/postProduct.do",
			type: "POST",
			data: $("#productForm").serialize(),
			success: function(data){
				if(data.result=="SUCCESS"){
					closeLoading();
                    alert("添加成功");
                    if(data.message!=null) {
                    	alert(data.message);
                    }
                    //添加产品类型 table
                    openLoading();
					//清空产品列表
					$("input[name=productNumber]").val("");
//					$("input[name=productPrice]").val("");
					$("input[name=labelNumStart]").val("");
					$("input[name=labelNumEnd]").val("");

                    tableOrderProduct(JSON.parse(data.errorMsg));
                    //验证同一产品型号 单价是否相同
                    var productType = verifyProductPrice();
                    if(productType!=null){
                        alert("已选产品："+productType+"型号请保持产品单价一致。");
                        return false;
                    }
					//是否可用订购地区下拉列表
					controlAreaSelect();
					//刷新开票信息
					$("#invoiceModal").modal('hide');
					$("#invoiceDetailsModal").modal('hide');

				} else{
					closeLoading();
					alert(data.errorMsg);
				}

//				location.reload();
			},error : function(XMLHttpRequest, textStatus, errorThrown) {
				alert("加入失败" + errorThrown);
				closeLoading();
			}
		});
	}
	//确定下单
	function addOrder() {
		//输入值格式校验
		if(!verifyVal())
		return false;


//		if($("#fileName").val()==""){
//			alert("请进行上传文件操作。");
//			return false;
//		}
		<%--var orderId = "${orderId}";--%>
		var orderUnitName = $("#orderUnitName").val();
		var purchaser= $("#purchaser").val();
		var purchaserMobile= $("#purchaserMobile").val();
		var purchaserPhone= $("#purchaserPhone").val();
		var invoiceType = $("input[name=invoiceType]:checked").val();
		var remitterType = $("input[name=remitterType]:checked").val();
		var remitter= $("#remitter").val();
		var remark= $("#remark").val();
		var productTotalAmount = $("#tableProductAmount").text();
		var productTotalCount = $("#tableProductNumber").text();

		var receiveUnitName = $("#receiveUnitName").text();
		var receiveUnitAddress = $("#receiveUnitAddress").text();
		var receiveName = $("#receiveName").text();
		var receiveMobile = $("#receiveMobile").text();
		var receivePhone = $("#receivePhone").text();
		var standbyName = $("#standbyName").text();
		var standbyMobile = $("#standbyMobile").text();
		var standbyPhone = $("#standbyPhone").text();

		var orderId = $("#orderId").val();

		var orderType = $("#orderType").val();

		var orderUnitAddress = $("#orderUnitAddress").val();

		var addressSetting = $("input[name=addressSetting]:checked").val();

		var data = {"id":orderId,"orderUnitName":orderUnitName,"purchaser":purchaser,"purchaserMobile":purchaserMobile,"purchaserPhone":purchaserPhone,"invoiceType":invoiceType,
			"remitterType":remitterType,"remitter":remitter,"remark":remark,"productTotalAmount":productTotalAmount,"productTotalCount":productTotalCount,
			"receiveUnitName":receiveUnitName,"receiveUnitAddress":receiveUnitAddress,"receiveName":receiveName,"receiveMobile":receiveMobile,
			"receivePhone":receivePhone,"standbyName":standbyName,"standbyMobile":standbyMobile,"standbyPhone":standbyPhone,"orderType":orderType,"orderUnitAddress":orderUnitAddress,
			"addressSetting":addressSetting};
		openLoading();
		$.ajax({
			url: "<%=path%>/commonUserOrder/postOrder.do",
			type: "POST",
			data: data,
			success: function(data){
				if(data.result=="SUCCESS"){
					alert("下单成功");
					//添加产品类型 table
					closeLoading();
					window.location.reload();
					<%--window.location.href='<%=path%>/commonUserOrderQuery/getOrderList.do';--%>
				}else {
					closeLoading();
					alert(data.errorMsg);
				}
			},error : function(XMLHttpRequest, textStatus, errorThrown) {
				closeLoading();
				alert("加入失败" + errorThrown);
			}
		})
	}
	//保存
	function addOrderSave() {
		<%--var orderId = "${orderId}";--%>
		var orderUnitName = $("#orderUnitName").val();
		var purchaser= $("#purchaser").val();
		var purchaserMobile= $("#purchaserMobile").val();
		var purchaserPhone= $("#purchaserPhone").val();
		var invoiceType = $("input[name=invoiceType]:checked").val();
		var remitterType = $("input[name=remitterType]:checked").val();
		var remitter= $("#remitter").val();
		var remark= $("#remark").val();
		var productTotalAmount = $("#tableProductAmount").text();
		var productTotalCount = $("#tableProductNumber").text();

		var receiveUnitName = $("#receiveUnitName").text();
		var receiveUnitAddress = $("#receiveUnitAddress").text();
		var receiveName = $("#receiveName").text();
		var receiveMobile = $("#receiveMobile").text();
		var receivePhone = $("#receivePhone").text();
		var standbyName = $("#standbyName").text();
		var standbyMobile = $("#standbyMobile").text();
		var standbyPhone = $("#standbyPhone").text();
		var orderId = $("#orderId").val();

		var orderType = $("#orderType").val();

		var orderUnitAddress = $("#orderUnitAddress").val();

		var addressSetting = $("input[name=addressSetting]:checked").val();

		var provinceCode = $("#areaProvice").val();
		var cityCode = $("#areaCity").val();
		
		var userId = '${order.userId}';

		var data = {"id":orderId,"orderUnitName":orderUnitName,"purchaser":purchaser,"purchaserMobile":purchaserMobile,"purchaserPhone":purchaserPhone,"invoiceType":invoiceType,
			"remitterType":remitterType,"remitter":remitter,"remark":remark,"productTotalAmount":productTotalAmount,"productTotalCount":productTotalCount,
			"receiveUnitName":receiveUnitName,"receiveUnitAddress":receiveUnitAddress,"receiveName":receiveName,"receiveMobile":receiveMobile,
			"receivePhone":receivePhone,"standbyName":standbyName,"standbyMobile":standbyMobile,"standbyPhone":standbyPhone,"orderType":orderType,"orderUnitAddress":orderUnitAddress,
			"addressSetting":addressSetting,"provinceCode":provinceCode,"cityCode":cityCode,"userId":userId};
		openLoading();
		$.ajax({
			url: "<%=path%>/commonUserOrder/postOrderSave.do",
			type: "POST",
			data: data,
			success: function(data){
				if(data.result=="SUCCESS"){
					alert("保存成功");
					//添加产品类型 table
					closeLoading();
//					location.reload();
					<%--window.location.href='<%=path%>/commonUserOrderQuery/getOrderList.do';--%>
				}else {
					closeLoading();
					alert(data.errorMsg);
				}
			},error : function(XMLHttpRequest, textStatus, errorThrown) {
				closeLoading();
				alert("加入失败" + errorThrown);
			}
		})
	}
	function deleteUnit(e,id) {

		if(confirm("是否删除！")){
			openLoading();
			$.ajax({
				url: "<%=path%>/commonUserOrder/deleteProduct.do",
				type:"POST",
				data:{"orderProductId":id},
				success: function(data){
					alert(data);
					closeLoading();
                    //清除列表
                    var $this = e.parentNode.parentNode;
					objectRemove($this);

                    tableAddClass();
                    tableOrderProductTotal();

					//是否显示下拉列表
					controlAreaSelect();
//					location.reload();
				},error : function(XMLHttpRequest, textStatus, errorThrown) {
					closeLoading();
					alert("删除失败" + errorThrown);
				}
			})
		}
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

	function tableOrderProduct(orderProduct){
        var tbodyContext1 = $(".context1");
        var tbodyContext2 = $(".context2");
        tbodyContext1.empty();
        tbodyContext2.empty();
        var context1 = "";
        var context2 = "";
        //非配件
        for(var i in orderProduct){
            if(orderProduct[i].productListId !="0"){
                context1 += Objects.format("<tr  class=\"nonePart\">");
                context1 += tableOrderProduct2(orderProduct[i]);
                context1 += Objects.format("</tr>");
            }
        }

        //配件
        for(var i in orderProduct){
            if(orderProduct[i].productListId =="0"){
                context2 += Objects.format("<tr  class=\"donePart\">");
                context2 += tableOrderProduct2(orderProduct[i]);
                context2 += Objects.format("</tr>");
            }
        }
        tbodyContext1.append(context1);
        tbodyContext2.append(context2);
        tableOrderProductTotal();
        closeLoading();
    }
    function tableOrderProduct2(orderProduct){
        var context = "";
        context += Objects.format("<td class=\"table_td\">{0}</td>",orderProduct.productName);
        context += Objects.format("<td class=\"table_td productType\">{0}</td>",orderProduct.productType);
        context += Objects.format("<td class=\"table_td tableProductNumber\">{0}</td>",orderProduct.productNumber);
        context += Objects.format("<td class=\"table_td productPrice\">{0}</td>",orderProduct.productPrice);
        context += Objects.format("<td class=\"table_td tableProductAmount\">{0}</td>",orderProduct.productAmount);
        context += Objects.format("<td class=\"table_td\">{0}-{1}</td>",orderProduct.labelStart!=null?orderProduct.labelStart:"",orderProduct.labelEnd!=null?orderProduct.labelEnd:"");
        context += Objects.format("<td class=\"table_td\"><a href=\"javascript:void(0)\" onclick=\"javascript:deleteUnit(this,'{0}')\">删除</a></td>",orderProduct.id);
        return context;
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
                        tableProductAmountNonePart+=mathFloorByString($(this).text());
                    }
                }else if($(this).parent("tr").hasClass("donePart")){
                    if($(this).hasClass("tableProductNumber")){
                        tableProductNumberdonePart+= Number($(this).text());
                    }else if($(this).hasClass("tableProductAmount")){
                        tableProductAmountdonePart+=mathFloorByString($(this).text());
                    }
                }
                if($(this).hasClass("tableProductNumber")){
                    tableProductNumber+= Number($(this).text());
                }else if($(this).hasClass("tableProductAmount")){
                    tableProductAmount+=mathFloorByString($(this).text());
                }
            })
            //小计删除会影响下单证书统计（订单总个数只统计证书个数）
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
                        tableProductAmountNonePart+=mathFloorByString($(this).text());
                    }
                }
                if($(this).hasClass("tableProductNumber")){
                    tableProductNumber+= Number($(this).text());
                }else if($(this).hasClass("tableProductAmount")){
                    tableProductAmount+=mathFloorByString($(this).text());
                }
            })
			//小计删除会影响下单证书统计（订单总个数只统计证书个数）
			var context = tableProjectListContext("小计",tableProductNumberNonePart,"tableProductNumber",tableProductAmountNonePart,"");
			$(".contextTotal1").append(context);
//            var context = tableProjectListContext("总计",tableProductNumber,"tableProductNumber",tableProductAmount,"tableProductAmount");
			var context = tableProjectListContext("总计","","",tableProductAmount,"tableProductAmount");

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
                        tableProductAmountdonePart+=mathFloorByString($(this).text());
                    }
                }
                if($(this).hasClass("tableProductNumber")){
                    tableProductNumber+= Number($(this).text());
                }else if($(this).hasClass("tableProductAmount")){
                    tableProductAmount+=mathFloorByString($(this).text());
                }
            })
//            var context = tableProjectListContext("小计",tableProductNumberdonePart,"",tableProductAmountdonePart,"");
//            var context = tableProjectListContext("总计",tableProductNumber,"tableProductNumber",tableProductAmount,"tableProductAmount");
			var context = tableProjectListContext("总计","","",tableProductAmount,"tableProductAmount");

		}
        $(".contextTotal2").append(context);
        tableAddClass();
    }

    function downloadOrder(){
		//输入值格式校验
		if(!verifyVal())
		return false;
		openLoading();
		var orderAddress = "";
		orderAddress += "&receiveUnitName=" + $("#receiveUnitName").text();
		orderAddress += "&receiveUnitAddress=" + $("#receiveUnitAddress").text();
		orderAddress += "&receiveName=" + $("#receiveName").text();
		orderAddress += "&receiveMobile=" + $("#receiveMobile").text();
		orderAddress += "&receivePhone=" + $("#receivePhone").text();
		orderAddress += "&standbyName=" + $("#standbyName").text();
		orderAddress += "&standbyMobile=" + $("#standbyMobile").text();
		orderAddress += "&standbyPhone=" + $("#standbyPhone").text();
		$.ajax({
			url: "<%=path%>/file/downloadOrderInfo.do",
			type: "POST",
			data:$("#purchaserForm").serialize().concat("&",$("#remitterForm").serialize()).concat("&",orderAddress),
			success: function(data){
				if(data.result=="SUCCESS"){
					closeLoading();
					location.href= "<%=path%>/file/downloadTemp.do?fileName="+data.errorMsg;


				}else{
					alert(data.errorMsg);
					closeLoading();
				}

//				location.reload();
			},error : function(XMLHttpRequest, textStatus, errorThrown) {
				closeLoading();
				alert("加入失败" + errorThrown);
			}
		});
		<%--<%=path%>/file/downloadOrderInfo?orderId=${orderId}--%>
	}
	function uploadOrder() {
		//输入值格式校验
		if(!verifyVal()){
			return false;
		}
		if($("#fileName").val()==""){
			alert("请传入上传文件。");
			return false;
		}
//		var theform = $("#uploadForm");
		<%--var theform = document.getElementById("uploadForm");--%>
		<%--theform.action = "<%=path%>/file/uploadOrderInfo.do";--%>
		<%--theform.submit();--%>
//		var formData = new FormData($("#uploadForm")[0]);
		var orderUnitName =$("#orderUnitName").val();

		openLoading();
		$("#uploadForm").ajaxSubmit({
			type:'post',
			url: "<%=path%>/file/uploadOrderInfo.do?orderUnitName="+orderUnitName,
			success: function(data){
				data = JSON.parse(data);
				if(data.result == "SUCCESS"){
					closeLoading();
					alert("上传成功");
					//回传页面
					$("#fileName").val('');
					for(var i in data.message){
						var src = data.message[i];
//						var src = data.errorMsg;
						var ioReadImg = "/file/ioReadImage.do?id="+src;
						var newElement = "<li id='img_"+src+"' ><img class='content' onclick='clickImg(this.src)'  src=" + ioReadImg + " style=\"width:100px;height:100px;\"><img class='button' onclick=\"_deleteOrderUnloadFiles('"+src+"')\" src='/js/swf/images/fancy_close.png'></li>";
						$("#pic_list").append(newElement);
					}

				}else{
					closeLoading();
					alert(data.errorMsg);
				}

			},error : function(XMLHttpRequest, textStatus, errorThrown) {
				closeLoading();
				alert("加入失败" + errorThrown);
			}
		})
		<%--$.ajax({--%>
			<%--url: "<%=path%>/file/uploadOrderInfo.do",--%>
			<%--type: "POST",--%>
			<%--data:formData,--%>
			<%--cache:false,--%>
			<%--contentType:false,--%>
			<%--processData:false,--%>
			<%--success: function(data){--%>
				<%--data = JSON.parse(data);--%>
				<%--if(data.result=="SUCCESS"){--%>
					<%--closeLoading();--%>
					<%--alert("上传成功");--%>
					<%--$isUpLoad = true;--%>
				<%--}else{--%>
					<%--closeLoading();--%>
					<%--alert(data.errorMsg);--%>
				<%--}--%>

			<%--},error : function(XMLHttpRequest, textStatus, errorThrown) {--%>
				<%--closeLoading();--%>
				<%--alert("加入失败" + errorThrown);--%>
			<%--}--%>
		<%--});--%>
	}

	function clickImg(src) {
		var newwin = window.open();
		newwin.document.write("<img src='"+src+"'/>");
	}
	function _deleteOrderUnloadFiles(unloadFilesId) {
		if(confirm("是否删除！")){
			openLoading();
			$.ajax({
				url: "<%=path%>/commonUserOrder/deleteOrderUnloadFiles.do",
				type: "POST",
				data: {"id":unloadFilesId},
				success: function(data){
					data = JSON.parse(data);
					if(data.result=="SUCCESS"){
						closeLoading();
						alert("删除成功");
						$("#img_"+unloadFilesId).remove();
					}else {
						closeLoading();
						alert(data.errorMsg);
					}
				},error : function(XMLHttpRequest, textStatus, errorThrown) {
					alert("加入失败" + errorThrown);
					closeLoading();
				}
			});
		}
	}

	//订购全页面格式校验
	function verifyVal(){


		if($("#orderUnitName").val()==""){
			alert("订购基本信息：订购单位全称不能为空！");
			return false;
		}
		if($("#orderUnitAddress").val()==""){
			alert("订购基本信息：订购单位地址不能为空！");
			return false;
		}

		if($("#purchaser").val()==""){
			alert("订购基本信息：订购联系人不能为空！");
			return false;
		}if($("#purchaserMobile").val()==""){
			alert("订购基本信息：手机号码不能为空！");
			return false;
		}if(!validatorIsTelephone($("#purchaserMobile").val())){
			alert("订购基本信息：手机号码格式错误！");
			return false;
		}
		if($("#receiveUnitName").text()==""){
			alert("收件地址：收货单位不能为空！");
			return false;
		}
		if($("#receiveUnitAddress").text()==""){
			alert("收件地址：收货地址不能为空！");
			return false;
		}
		if($("#receiveName").text()==""){
			alert("收件地址：收货联系人不能为空！");
			return false;
		}
		if($("#receiveMbile").val()==""){
			alert("收件地址：收货人手机号不能为空！");
			return false;
		}

		if(!$("tr").hasClass("nonePart")){
			alert("请先选购证书产品。");
			return false;
		}
		
		
		var num = $(".tableProductListNum")[0].innerText; //产品数量
		if(Number(num)<10){
			//管理员gaUserType=1，用户=0
			if(gaUserType!=1) {
				alert("证书产品最少需要购买10支。");
				return false;
			} else {
				if(confirm("证书产品少于10支，需要继续下单吗？")) {
					return true;
				} else {
					return false;
				}
			}
		}
		var productType = verifyProductPrice();
		//验证同一产品型号 单价是否相同
		if(productType!=null){
            alert("已选产品："+productType+"型号请保持产品单价一致。");
            return false;
        }

		//开票总额是否和证书总额一致
		var invoiceAmount = $("#invoiceAmount", window.frames["iframeInvoice"].document).val();
		var productAmount = $("#tableProductAmount").text();
		if(invoiceAmount != productAmount){
			alert("开票金额总额："+invoiceAmount+"元\r\n产品金额总额："+productAmount+"元。\r\n请保持和产品金额总额一致。");
			return false;
		}
		if($("input[name=remitterType]:checked").val()=="0" && $("#remitter").val()==""){
			alert("开票信息：汇款单位名称不能为空！");
			return false;
		}else if($("input[name=remitterType]:checked").val()=="1" && $("#remitter").val()==""){
			alert("开票信息：汇款人姓名不能为空！");
			return false;
		}
		return true;

	}


</script>
</body>

</html>