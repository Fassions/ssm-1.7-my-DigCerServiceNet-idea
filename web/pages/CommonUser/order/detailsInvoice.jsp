<%@ page language="java" pageEncoding="UTF-8"
         contentType="text/html; charset=UTF-8"%>
<meta name="viewport" content="width=device-width,initial-scale=1">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<%@ include file="../../init.jsp" %>
<%@ include file="../../inc/header.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>数字证书服务网</title>
    <%--<link type="text/css" href="<%=path%>/css/css.css" rel="stylesheet" />--%>
    <%--<link type="text/css" href="<%=path%>/css/main.css" rel="stylesheet" />--%>
    <%--<link type="text/css" href="<%=path%>/css/bootstrap.min.css" rel="stylesheet" />--%>
    <%--<link type="text/css" href="<%=path%>/js/themes/jquery-ui.min.css"--%>
          <%--rel="stylesheet" />--%>

    <%--<script type="text/javascript" src="<%=path%>/js/jquery-1.7.2.min.js"></script>--%>
    <%--<script type="text/javascript" src="<%=path%>/js/bootstrap.min.js"></script>--%>
    <%-- 		<script type="text/javascript" src="<%=path%>/js/validate/jquery.validate.js"></script>
            <script type="text/javascript" src="<%=path%>/js/validate/additional-methods.js"></script>
            <script type="text/javascript" src="<%=path%>/js/validate/messages_zh.js"></script> --%>
    <%--<script type="text/javascript" src="<%=path%>/js/validate.js"></script>--%>
    <style type="text/css">
        .red_labelHide{
            color: red;
        }
        .tdiv_a_1{
            width: 23%!important;
        }
        .tdiv_a_z{
            margin: 20px 8% 0px 10px!important;
        }


    </style>
    <script type="text/javascript">


    </script>
</head>
<body class="">
<div align="center">
    <div class="content">

        <div class="down_area neir_2_kcgl_bod2">

            <div class="neir_2">

                <table class="table">
                    <%--<thead class="thead">--%>
                    <tr>
                        <th class="table_td th">产品名称</th>
                        <th class="table_td th">产品型号</th>
                        <th class="table_td th">数量</th>
                        <th class="table_td th">金额</th>
                        <th class="table_td th" style="width:20%">操作</th>
                    </tr>
                    <%--</thead>--%>
                    <tbody class="tbody invoiceTable">


                    </tbody>
                </table>
                <form class="form-inline" id="postInvoiceForm" action="" method="post" accept-charset="UTF-8">
                    <input type="hidden" id="orderId" value="${orderId}"/>
                    <input type="hidden" id="orderInvoiceId" name="orderInvoiceId" value="${invoiceDetails.id}" />
                    <input type="hidden" id="invoiceType" name="invoiceType" value="${invoiceDetails.invoiceType}" />
                <div class="tdiv_a_z tdiv_center">
                    <div class="tdiv_a_1 tdiv_a_left" >单位全称<span class="red_label">★</span>：</div>
                    <div class="tdiv_a_3">
                        <input name="invoiceUnitName" id="invoiceUnitName" value="${invoiceDetails.invoiceUnitName}" class="form-control"  onblur="javascript:ok_or_error(this)" type="text" >
                        <i class="glyphicon glyphicon-ok" style="color: #5cb85c;display: none;"></i>
                        <i class="glyphicon glyphicon-remove" style="color: red;display: none;"></i>
                    </div>
                    <div class="tdiv_a_1">纳税人识别号<span class="red_labelHide" >★</span>：<br/><span style="transform: scale(0.833333)">(或统一社会信用代码)</span></div>
                    <div class="tdiv_a_3">
                        <input name="socialCreditCode" id="socialCreditCode" value="${invoiceDetails.socialCreditCode}" class="form-control"   type="text" >
                    </div>
                </div>

                <div class="tdiv_a_z tdiv_center">
                    <div class="tdiv_a_1 tdiv_a_left" >开户银行<span class="red_labelHide" >★</span>：</div>
                    <div class="tdiv_a_3">
                        <input name="depositBank" id="depositBank" value="${invoiceDetails.depositBank}" class="form-control"   type="text" >
                    </div>
                    <div class="tdiv_a_1" >账号<span class="red_labelHide" >★</span>：</div>
                    <div class="tdiv_a_3">
                        <input name="account" id="account" value="${invoiceDetails.account}" class="form-control"   type="text" >
                    </div>
                </div>

                <div class="tdiv_a_z tdiv_center">
                    <div class="tdiv_a_1 tdiv_a_left" >地址<span class="red_labelHide" >★</span>：</div>
                    <div class="tdiv_a_3">
                        <input name="address" id="address" value="${invoiceDetails.address}" class="form-control"   type="text" >
                    </div>
                    <div class="tdiv_a_1" >电话<span class="red_labelHide" >★</span>：</div>
                    <div class="tdiv_a_3">
                        <input name="phone" id="phone"  value="${invoiceDetails.phone}" class="form-control"   type="text" >
                    </div>
                </div>

                <div class="clearfloat"></div>
                <hr class="hr_style"/>

                    <div class="seqx_gly">
                    <c:choose>
                        <c:when test="${invoiceDetails.id!=null}">
                            <a class="btn btn-info btn-lg" id="next" type="button" onclick="_editInvoice()">保存</a>
                            <a class="btn btn-info btn-lg" id="next" type="button" onclick="_returnInvoice()">返回</a>
                        </c:when>
                        <c:otherwise>
                            <a class="btn btn-info btn-lg" id="next" type="button" onclick="_postInvoice()">新建</a>
                        </c:otherwise>
                    </c:choose>

                </form>
                <%--<form class="form-inline" id="postInvoiceForm" action="" method="post" accept-charset="UTF-8">--%>

                    <%--<input type="hidden" id="orderId" value="${orderId}"/>--%>
                    <%--<input type="hidden" id="orderInvoiceId" name="orderInvoiceId" value="${invoiceDetails.id}" />--%>
                    <%--<input type="hidden" id="invoiceType" name="invoiceType" value="${invoiceDetails.invoiceType}" />--%>

                    <%--<div id="first">--%>
                        <%--<div class="neir_1">--%>
                            <%--<dl class="neir_xjpc_dl">--%>
                                <%--<dt>单位全称：<span class="red_label">★</span></dt>--%>
                                <%--<dd>--%>
                                    <%--<input name="invoiceUnitName" id="invoiceUnitName" value="${invoiceDetails.invoiceUnitName}" class="form-control"  onblur="javascript:ok_or_error(this)" type="text" >--%>
                                    <%--<i class="glyphicon glyphicon-ok" style="color: #5cb85c;display: none;"></i>--%>
                                    <%--<i class="glyphicon glyphicon-remove" style="color: red;display: none;"></i>--%>
                                <%--</dd>--%>
                                <%--<dt class="showRedLabel" style="height: 0px">纳税人识别号：<br/>(或统一社会信用代码)<span class="red_labelHide" >★</span></dt>--%>
                                <%--<dd>--%>
                                    <%--<input name="socialCreditCode" id="socialCreditCode" value="${invoiceDetails.socialCreditCode}" class="form-control"   type="text" >--%>
                                <%--</dd>--%>
                                <%--<dt class="showRedLabel">开户银行：<span class="red_labelHide" >★</span></dt>--%>
                                <%--<dd>--%>
                                    <%--<input name="depositBank" id="depositBank" value="${invoiceDetails.depositBank}" class="form-control"   type="text" >--%>
                                <%--</dd>--%>
                                <%--<dt class="showRedLabel">账号：<span class="red_labelHide" >★</span></dt>--%>
                                <%--<dd>--%>
                                    <%--<input name="account" id="account" value="${invoiceDetails.account}" class="form-control"   type="text" >--%>
                                <%--</dd>--%>
                                <%--<dt class="showRedLabel">地址：<span class="red_labelHide" >★</span></dt>--%>
                                <%--<dd>--%>
                                    <%--<input name="address" id="address" value="${invoiceDetails.address}" class="form-control"   type="text" >--%>
                                <%--</dd>--%>
                                <%--<dt class="showRedLabel">电话：<span class="red_labelHide" >★</span></dt>--%>
                                <%--<dd>--%>
                                    <%--<input name="phone" id="phone" value="${invoiceDetails.phone}" class="form-control"   type="text" >--%>
                                <%--</dd>--%>

                                <%--<div class="seqx_gly">--%>
                                    <%--<c:choose>--%>
                                        <%--<c:when test="${invoiceDetails.id!=null}">--%>
                                            <%--<a class="btn btn-danger" id="next" type="button" onclick="_editInvoice()">添加</a>--%>
                                        <%--</c:when>--%>
                                        <%--<c:otherwise>--%>
                                            <%--<a class="btn btn-danger" id="next" type="button" onclick="_postInvoice()">新建</a>--%>
                                        <%--</c:otherwise>--%>
                                    <%--</c:choose>--%>

                                <%--</div>--%>
                                <%--<div class="clearfloat"></div>--%>
                            <%--</dl>--%>
                        <%--</div>--%>
                    <%--</div>--%>
                <%--</form>--%>
            </div>
</div>
</div>
    </div>
<script type="text/javascript">
    $(function () {
        window.$orderProductsMap = new Map();
        detailsTable();
        var orderUnitName = "${orderUnitName}";
        if(orderUnitName!=""&&orderUnitName!=undefined){       //默认单位名称
            $("#invoiceUnitName").val(orderUnitName);
        }


        if($(':radio[name="invoiceType"]:checked',window.parent.document).val()=="0"){
            $(".red_labelHide").show();
        }else{
            $(".red_labelHide").hide();
        }

        //开票产品列表数量更改
        $(".tableProductNumber").change(function () {
            if($(this).val()=="0"){
                alert("产品数量必须大于0");
                $(this).val("");
                return;
            }
            var mapIndex = $(this).parents("tr")[0].id.split("_")[1];
            var mapArray = $orderProductsMap.get(mapIndex).split(";");
            var mapContext = "";
            objectRemove($orderProductsMap,mapIndex);
            for(var i in mapArray){
                if(i==4){
                    mapContext += $(this).val()+";";
                }else{
                    mapContext += mapArray[i]+";";
                }
            }
            $orderProductsMap.put(mapIndex,mapContext);
        })
        //开票产品列表总金额更改
        $(".tableProductAmount").change(function () {
            var priceRe = /^\d*\.{0,1}\d*$/;
            if(!priceRe.test($(this).val())){
                alert("请填入有效金额");
                return;
            }
            var mapIndex = $(this).parents("tr")[0].id.split("_")[1];
            var mapArray = $orderProductsMap.get(mapIndex).split(";");
            var mapContext = "";
            $orderProductsMap.remove(mapIndex);
            for(var i in mapArray){
                if(i==6){
                    mapContext += $(this).val()+";";
                }else{
                    mapContext += mapArray[i]+";";
                }
            }
            $orderProductsMap.put(mapIndex,mapContext);
        })
    })
    function changeInvoiceProduceNumber(index){
//        $("#invoiceProductTr_"+index).remove();
        var number = $("#tableProductNumber_"+index).val();
        var price = $("#tableProductPrice_"+index).val();
        $("#tableProductAmount_"+index).val(accMul(number,price));
        var mapArray = $orderProductsMap.get(index).split(";");
        $orderProductsMap.remove(index);
        var mapContext = "";
        for(var i in mapArray){
            if(i==4){
                mapContext +=  $("#tableProductNumber_"+index).val()+";";
            }else if(i==6){
                mapContext +=  $("#tableProductAmount_"+index).val()+";";
            }else{
                mapContext += mapArray[i]+";";
            }
        }
        $orderProductsMap.put(index,mapContext);
    }
    function deleteInvoiceProduct(index){
        $("#invoiceProductTr_"+index).remove();
        $orderProductsMap.remove(index);
    }

    //开票信息产品table
    function detailsTable(){
        var invoiceTable = $(".invoiceTable");
        invoiceTable.empty();
        param = "";
        <c:forEach items="${invoiceOrderProducts}" var="orderProduct" varStatus="xh">
        $orderProductsMap.put("${xh.index}","${orderProduct.orderId}"+";"+"${orderProduct.id}"+";"+"${orderProduct.productName}"+";"
                +"${orderProduct.productType}"+";"+"${orderProduct.productNumber}"+";"
                +"${orderProduct.productPrice}"+";"+"${orderProduct.productAmount}");
        param +=Objects.format("<tr class=\"tbodyeven\" id=\"invoiceProductTr_${xh.index}\">");
        param +=Objects.format("<input type=\"hidden\" id=\"tableProductPrice_${xh.index}\" value=\"${orderProduct.productPrice}\" />");

        param +=Objects.format("<td class=\"table_td\" style=\"min-width: 60px;\">${orderProduct.productName}</td>");
        param +=Objects.format("<td class=\"table_td\" style=\"min-width: 60px;\">${orderProduct.productType}</td>");
        param +=Objects.format("<td class=\"table_td\" style=\"min-width: 60px;\"><input type=\"text\"  class=\"form-control tableProductNumber\" id=\"tableProductNumber_${xh.index}\"onblur=\"changeInvoiceProduceNumber('${xh.index}')\" value = \"${orderProduct.productNumber}\" onkeyup=\"this.value=this.value.replace(/\\D/g,'')\" /></td>");
        if(${sessionScope.gaUser.userType==1}){ //管理员允许修改金额
            param +=Objects.format("<td class=\"table_td\" style=\"min-width: 60px;\"><input type=\"text\" id=\"tableProductAmount_${xh.index}\"  class=\"form-control tableProductAmount \" value = \"${orderProduct.productAmount}\" onkeyup=\"this.value=this.value.replace(/[^\\d.]/g,'')\" /></td>");
        }else {
            param +=Objects.format("<td class=\"table_td\" style=\"min-width: 60px;\"><input type=\"text\" id=\"tableProductAmount_${xh.index}\" class=\"form-control tableProductAmount tableProductAmount_${xh.index}\" disabled=disabled value = \"${orderProduct.productAmount}\" onkeyup=\"this.value=this.value.replace(/[^\\d.]/g,'')\" /></td>");
        }

        param +=Objects.format("<td class=\"table_td\" style=\"min-width: 60px;\"><a href=\"#\" class=\"a_css\" onclick=\"deleteInvoiceProduct('${xh.index}')\">删除</a></td>");
        param +=Objects.format("</td>");
        param +=Objects.format("</tr>");
        </c:forEach>
        invoiceTable.append(param);
        tableAddClass();
    }
        //返回
        function _returnInvoice() {
            window.location.href = "<%=path%>/commonUserOrder/chooseInvoice.do?orderId=${orderId}";
        }

        //编辑
        function _editInvoice() {
            var invoiceType = $(':radio[name="invoiceType"]:checked',window.parent.document).val();
            var socialCreditCode = $("#socialCreditCode").val();
            if($("#invoiceUnitName").val()==""){
                alert("单位全称不能为空");
                return;
            }
            if(invoiceType=="0"){
                if($("#socialCreditCode").val()==""||$("#depositBank").val()==""||$("#account").val()==""||$("#address").val()==""||$("#phone").val()==""){
                    alert("您选择为增值税专用发票。税人识别号、开户银行、账号、地址、电话为必填项，不能为空。");
                    return;
                }
            }
            if(socialCreditCode!=""){
                var socialCreditCodeRe = /^(([A-Za-z0-9]{15})|([A-Za-z0-9]{18}))$/;
                if(!socialCreditCodeRe.test(socialCreditCode)){
                    alert("纳税人识别号(社会信用代码)，只能15位或18位，且只能是F数字或字母。");
                    return false;
                }
            }
            var json = {orderInvoice:{},orderProductsMap:{}};
            json.orderInvoice['orderId']=$("#orderId").val();
            json.orderInvoice['id']=$("#orderInvoiceId").val();
            json.orderInvoice['invoiceType'] = $("#invoiceType").val();
            json.orderInvoice['invoiceUnitName'] = $("#invoiceUnitName").val();
            json.orderInvoice['invoiceType'] = $(':radio[name="invoiceType"]:checked').val();
            json.orderInvoice['socialCreditCode'] = $("#socialCreditCode").val();
            json.orderInvoice['depositBank'] = $("#depositBank").val();
            json.orderInvoice['account'] = $("#account").val();
            json.orderInvoice['address'] = $("#address").val();
            json.orderInvoice['phone'] = $("#phone").val();
            json.orderProductsMap = $orderProductsMap;
            openLoading();
            var url = "<%=path%>/commonUserOrder/putInvoice.do";
            Ajax.jsonPost(url,json,function (data) {
                closeLoading();
                var resultMessage = data;
                if(resultMessage.result == "SUCCESS"){
                    alert("添加成功！");
//                    $("#invoiceDetailsModal").modal('hide');
//                    location.reload();
                    window.location.href = "<%=path%>/commonUserOrder/chooseInvoice.do?orderId=${orderId}";
                }else{
                    alert(resultMessage.errorMsg);
                }
            })
        }
        //新建
        function _postInvoice() {
            var invoiceType = $(':radio[name="invoiceType"]:checked',window.parent.document).val();
            var socialCreditCode = $("#socialCreditCode").val();
            if($("#invoiceUnitName").val()==""){
                alert("单位全称不能为空");
                return;
            }
            if(invoiceType=="0"){
                if($("#socialCreditCode").val()==""||$("#depositBank").val()==""||$("#account").val()==""||$("#address").val()==""||$("#phone").val()==""){
                    alert("您选择为增值税专用发票。税人识别号、开户银行、账号、地址、电话为必填项，不能为空。");
                    return;
                }
            }
            if(socialCreditCode!=""){
                var socialCreditCodeRe = /^(([A-Za-z0-9]{15})|([A-Za-z0-9]{18}))$/;
                if(!socialCreditCodeRe.test(socialCreditCode)){
                    alert("纳税人识别号(社会信用代码)，只能15位或18位，且只能是数字或字母。");
                    return false;
                }
            }

            var json = {orderInvoice:{},orderProductsMap:{}};
            json.orderInvoice['orderId']=$("#orderId").val();
            json.orderInvoice['invoiceType'] = $("#invoiceType").val();
            json.orderInvoice['invoiceUnitName'] = $("#invoiceUnitName").val();
            json.orderInvoice['invoiceType'] = $(':radio[name="invoiceType"]:checked').val();
            json.orderInvoice['socialCreditCode'] = $("#socialCreditCode").val();
            json.orderInvoice['depositBank'] = $("#depositBank").val();
            json.orderInvoice['account'] = $("#account").val();
            json.orderInvoice['address'] = $("#address").val();
            json.orderInvoice['phone'] = $("#phone").val();
            json.orderProductsMap = $orderProductsMap;
            openLoading();
            var url = "<%=path%>/commonUserOrder/postInvoice.do";
            Ajax.jsonPost(url,json,function (data) {
                closeLoading();
                var resultMessage = data;
                if(resultMessage.result == "SUCCESS"){
                    alert("添加成功！");
//                    $("#invoiceDetailsModal").modal('hide');
//                    location.reload();
                    window.location.href = "<%=path%>/commonUserOrder/chooseInvoice.do?orderId=${orderId}";
                }else{
                    alert(resultMessage.errorMsg);
                }
            })

        }


</script>
</body>
</html>