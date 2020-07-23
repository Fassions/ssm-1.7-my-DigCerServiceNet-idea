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
<%--<link type="text/css" href="<%=path%>/css/main.css" rel="stylesheet" />--%>
<%--<link type="text/css" href="<%=path%>/js/themes/jquery-ui.min.css"--%>
	<%--rel="stylesheet" />--%>

<%-- 		<script type="text/javascript" src="<%=path%>/js/validate/jquery.validate.js"></script>
        <script type="text/javascript" src="<%=path%>/js/validate/additional-methods.js"></script>
        <script type="text/javascript" src="<%=path%>/js/validate/messages_zh.js"></script> --%>
<%--<script type="text/javascript" src="<%=path%>/js/validate.js"></script>--%>
	<style type="text/css">
		.neir_2_kcgl_bod2 {
			background-color: white;
		}
		.neir_2_kcgl_bod {
			background-color: white;
		}
		/*.xxkc_xcqd_2 p { margin-top: 26px; line-height: 12px;}*/
		/*.neir_2 { padding:0 20px;}*/
		/*.neir_2_dl dt , .neir_2_dl dd { float: left; padding: 8px; border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; }*/
		/*.neir_2_dl dt { width: 35%; background-color: #f9f9f9;}*/
		/*.neir_2_dl dd { width: 65%; border-right: 1px solid #ddd; }*/

		/*.neir_2_kcgl { border:1px solid #ddd; padding: 16px; margin-bottom: 20px !important;}*/

		/*.neir_2_kcgl h4 { font-weight: 700; }*/
		/*.neir_2_kcgl h4 span { font-weight: 500; font-size:14px; }*/
		/*.neir_2_kcgl label input { margin-top: 28px; }*/
		/*.neir_2_kcgl_bod { border-left: 8px solid #2b57ae; }*/
		/*.neir_2_kcgl_span1_1 { margin-left: 30px; background-color: #f5f5f5; padding: 4px 0; color: #999; }*/
		/*p {*/
			/*!* margin: 0 0 10px; *!*/
		/*}*/
	</style>
<script type="text/javascript">

$(function () {
	changeDaoHang("收货地址管理");
	
})


</script>
</head>
<body class=" page_color">
	<div align="center">
		<div class="content">
			<div class="down_area">

			<input type="hidden" id="addressType" value="${addressType}" />

				<div class="tdiv_a_z">
					<div class="neir_2">
						<input type="button" class="btn btn-info" id="submit" onclick="createAddress()" value="新增收货地址"/>
					</div>
				</div>
				<div class="clearfloat"></div>
			<%--<table width="100%" border="0" cellpadding="0" cellspacing="0">--%>
					<%--<tr>--%>
						<%--<td class="table_tbody_rc"></td>--%>
						<%--<td class="table_tbody_title" style="text-indent: 0px;">收货地址列表管理</td>--%>
						<%--<td class="table_tbody">&nbsp;</td>--%>
						<%--<td class="table_tbody_lc"></td>--%>
					<%--</tr>--%>
				<%--</table>--%>
				<div class="neir_2">
					<div id="classDataView" style="text-align: left;">
						<%--<div class="tdiv neir_2_kcgl" >--%>
							<%--<div class="tdiv_title"><p>地址列表管理</p></div>--%>
							<%--<div class="clearfloat"></div>--%>
						<%--</div>--%>
							<c:forEach items="${addresses}" var="address">
								<c:choose>
									<c:when test="${address.isDefault==1}">
										<input type="hidden" id="addressId" value="${address.id}"/>

										<div class="neir_2_kcgl neir_2_kcgl_bod">
                                            <input type="hidden" id = "receiveUnitName" value="${address.receiveUnitName}"/>
                                            <input type="hidden" id = "receiveUnitAddress" value="${address.receiveUnitAddress}"/>
                                            <input type="hidden" id = "receiveName" value="${address.receiveName}"/>
                                            <input type="hidden" id = "receiveMobile" value="${address.receiveMobile}"/>
                                            <input type="hidden" id = "receivePhone" value="${address.receivePhone}"/>
                                            <input type="hidden" id = "standbyName" value="${address.standbyName}"/>
                                            <input type="hidden" id = "standbyMobile" value="${address.standbyMobile}"/>
                                            <input type="hidden" id = "standbyPhone" value="${address.standbyPhone}"/>
											<div style="color: black;float:left;" ><span>${address.receiveName}</span>&nbsp;&nbsp;&nbsp;<span>${address.receiveMobile}</span></div>
											<span class="neir_2_kcgl_span1_1" style="color: red;"><i class="glyphicon glyphicon-pencil"></i> 默认</span>
											<%--<span style="float: right" class="showSpan"><input type="button"  disabled="" class="main-btn main-btn-gray isDefaultButton"  onclick="editIsDefault('${address.id}')" value="设为默认"> </span>--%>
											<span style="float: right;display: none" class="hideSpan"><input type="button"   class="btn btn-info isDefaultButton" id="span_${address.id}"   onclick="clickOrderAddress('${address.id}')" value="选择">&nbsp;&nbsp;</span>
											<%--<span style="float: right;display: none" class="hideSpan"><input type="button"  disabled="" class="main-btn main-btn-gray isDefaultButton"  onclick="putOrderAddress('${address.id}')" value="选择"> </span>--%>
											<span style="float: right"><input type="button" class="btn btn-info" onclick="editAddress('${address.id}')" value="编辑">&nbsp;&nbsp;</span>
											<span style="float: right"><input type="button" class="btn btn-info" onclick="deleteAddress('${address.id}')" value="删除">&nbsp;&nbsp;</span>
											<p>收货地址：<span>${address.receiveUnitAddress}</span></p>
										</div>
									</c:when>
									<c:otherwise>
										<div class="neir_2_kcgl neir_2_kcgl_bod2">
											<div style="color: black;float:left;" >${address.receiveName}&nbsp;&nbsp;&nbsp;${address.receiveMobile}&nbsp;&nbsp;&nbsp;</div>
											<%--<span style="float: right" class="showSpan"><input type="button" class="main-btn main-btn-red isDefaultButton" onclick="editIsDefault('${address.id}')" value="设为默认"> </span>--%>
                                            <span style="float: right;display: none" class="hideSpan"><input type="button"   class="btn btn-info isDefaultButton" id="span_${address.id}"   onclick="clickOrderAddress('${address.id}')" value="选择">&nbsp;&nbsp;</span>
											<span style="float: right"><input type="button" class="btn btn-info" onclick="editAddress('${address.id}')" value="编辑">&nbsp;&nbsp;</span>
											<span style="float: right"><input type="button" class="btn btn-danger" onclick="deleteAddress('${address.id}')" value="删除">&nbsp;&nbsp;</span>
											<p>收货地址：${address.receiveUnitAddress}</p>
										</div>
									</c:otherwise>
								</c:choose>

							</c:forEach>
			</div>
		</div>
		</div>
            </div>
        </div>
<script type="text/javascript">
	$(function () {
		var addressFrame = $('#callback',window.parent.document);
		if(addressFrame.length>0){	//当前页面是否为订购单嵌套
			$(".showSpan").hide();
			$(".hideSpan").show();
			var orderAddressId = $('#orderAddressId',window.parent.document).val();	//获取订购单页面地址id 重置 选择按钮
			if($("#span_"+orderAddressId).length>0){
				$("#span_"+orderAddressId).prop({"class":"btn btn-gray isDefaultButton","disabled":true});
//				$("#span_"+orderAddressId).find("div").prop("class","neir_2_kcgl neir_2_kcgl_red");
			}
		}
	})
    function createAddress() {
//        是否由订单页面跳转
        <%--var $iframeShow = "${$iframeShow}";--%>
        <%--if($iframeShow!=""){--%>
            <%--$(".showSpan").hide();--%>
            <%--$(".showSpan").hide();--%>
        <%--}--%>

		var orderUnitName = "";
		if($('#orderUnitName',window.parent.document).length>0){
			orderUnitName =$('#orderUnitName',window.parent.document).val();
		}
		window.location.href='<%=path%>/commonUserAddress/createAddress.do?orderUnitName='+orderUnitName;
		changeDaoHangFrame('收货地址管理`<%=path%>/commonUserAddress/showAddressInfo.do,新建`<%=path%>/commonUserAddress/createAddress.do');
//		changeDaoHang('收货地址管理','新建');
        <%--$("#rightframe",window.parent.document).attr("src","<%=path%>/commonUserAddress/createAddress.do");--%>
    }
    function editAddress(data) {

		window.location.href='<%=path%>/commonUserAddress/editAddress.do?id='+data;
		changeDaoHangFrame('收货地址管理`<%=path%>/commonUserAddress/showAddressInfo.do,编辑`<%=path%>/commonUserAddress/editAddress.do?id='+data);
//		changeDaoHang('收货地址管理','编辑');
		<%--$("#rightframe",window.parent.document).attr("src","<%=path%>/commonUserAddress/editAddress.do?id="+data);--%>
	}
    function editIsDefault(data) {
		openLoading();
		$.ajax({
			url: "<%=path%>/commonUserAddress/editIsDefault.do",
			type: "POST",
			data: {"id":data},
			success: function(data){
				closeLoading();
				alert(data);
//				changeDaoHang("收货地址管理")
				<%--window.location.href='<%=path%>/commonUserAddress/showAddressInfo.do';--%>
				window.location.reload();
			},error : function(XMLHttpRequest, textStatus, errorThrown) {
				alert("加入失败" + errorThrown);
				closeLoading();
			}
		});
	}
	function deleteAddress(data) {
		if(confirm("是否删除！")){
			openLoading();
			$.ajax({
				url: "<%=path%>/commonUserAddress/deleteAddress.do",
				type: "POST",
				data: {"id":data},
				success: function(data){
					closeLoading();
					alert(data);
					changeDaoHang("收货地址管理")
					window.location.href='<%=path%>/commonUserAddress/showAddressInfo.do';
				},error : function(XMLHttpRequest, textStatus, errorThrown) {
					alert("加入失败" + errorThrown);
					closeLoading();
				}
			});
		}
	}
	function clickOrderAddress(data) {  //订购单 根据addressId 回传地址信息
		openLoading();
		$.ajax({
			url: "<%=path%>/commonUserAddress/clickOrderAddress.do",
			type: "POST",
			data: {"id":data},
			success: function(data){
				if(data.result=="ERROR"){
					closeLoading();
					alert(data.errorMsg);
				}else{
					closeLoading();
					var address = data;
					var addressFrame = $('#callback',window.parent.document);
					if(addressFrame.length>0) {	//当前页面是否为订购单嵌套

						$('#receiveUnitName', window.parent.document).text(address.receiveUnitName != "" ? address.receiveUnitName : "");
						$('#receiveUnitAddress', window.parent.document).text(address.receiveUnitAddress != "" ? address.receiveUnitAddress : "");
						$('#receiveName', window.parent.document).text(address.receiveName != "" ? address.receiveName : "");

						var receiveMobile = address.receiveMobile != "" ? address.receiveMobile : "";
						var receivePhone = address.receivePhone != "" ? address.receivePhone : "";
						var receivePhoneValue = receiveMobile + "/" + receivePhone;
						$('#receivePhone', window.parent.document).text(receivePhone);
						$('#receiveMobile', window.parent.document).text(receiveMobile);
						$('#standbyName', window.parent.document).text(address.standbyName != "" ? address.standbyName : "");
						var standbyMobile = address.standbyMobile != "" ? address.standbyMobile : "";
						var standbyPhone = address.standbyPhone != "" ? address.standbyPhone : "";
						var standbyPhoneValue = standbyMobile + "/" + standbyPhone;
						$('#standbyPhone', window.parent.document).text(standbyPhone);
						$('#standbyMobile', window.parent.document).text(standbyMobile);

//						$('#receiveMobile', window.parent.document).val(address.receiveMobile);
						$('#orderAddressId', window.parent.document).val(address.id);		//id回传
//						var ss = $('#myModal', window.parent.document);
//						ss.modal('hide');
						window.parent.$('#myModal').modal('hide');

					}

				}
			},error : function(XMLHttpRequest, textStatus, errorThrown) {
				alert(errorThrown);
				closeLoading();
			}
		});
	}

</script>
</body>
</html>