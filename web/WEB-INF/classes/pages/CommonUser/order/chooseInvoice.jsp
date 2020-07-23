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
    </style>
    <script type="text/javascript">

        $(function () {

        })

    </script>
</head>
<body class=" page_color">
<div align="center">
    <div class="content">

        <div class="down_area">

            <%--<div class="tdiv">--%>
                <%--<div class="tdiv_s">--%>
                    <%--<div class="tdiv_sub_c" onclick="createInvoice()" style="cursor : pointer"><p>添加新开票信息</p></div>--%>
                    <%--<div class="clearfloat"></div>--%>
                <%--</div>--%>
            <%--</div>--%>
                <div class="tdiv_a_z">
                    <div class="neir_2">
                        <input type="button" class="btn btn-info" onclick="createInvoice()" value="添加新开票信息"/>
                    </div>
                </div>
                <div class="clearfloat"></div>


            <div class="neir_2">
                <input type="hidden" id = "invoiceAmount"/>
                <div id="classDataView">
                    <c:forEach items="${invoices}" var="invoice">
                        <div class="neir_2_kcgl neir_2_kcgl_bod2 doneInvoiceClass">
                            <span style="color: black;float:left;">开票单位全称：${invoice.invoiceUnitName}</span>
                            <span style="float: right"><input type="button" class="btn btn-info" onclick="editInvoice('${invoice.id}')" value="编辑"></span>
                            <span style="float: right"><input type="button" class="btn btn-danger" onclick="deleteInvoice('${invoice.id}')" value="删除">&nbsp;&nbsp; </span>
                            <br><br>
                                <table width="100%" class="table" id="tableProductList" border="0" cellpadding="0" cellspacing="0">
                                    <%--<thead class="thead">--%>
                                    <tr class="thead_color">
                                        <th class="table_td th">产品名称</th>
                                        <th class="table_td th">产品型号</th>
                                        <th class="table_td th">数量(支)</th>
                                        <th class="table_td th">金额(元)</th>
                                        <th class="table_td th">税号及其他信息</th>
                                    </tr>
                                    <%--</thead>--%>
                                    <tbody class="tbody">
                                    <%--取相同开票单位 产品列表--%>
                                    <c:set value="0" var="x"/>
                                    <c:forEach items="${invoiceProducts}" var="invoiceProduct" varStatus="index">
                                        <c:choose>
                                            <c:when test="${invoiceProduct.orderInvoiceId==invoice.id}">
                                                <tr>
                                                    <td class="table_td">${invoiceProduct.productName}</td>
                                                    <td class="table_td">${invoiceProduct.productType}</td>
                                                    <td class="table_td">${invoiceProduct.productNumber}</td>
                                                    <td class="table_td">${invoiceProduct.productAmount}</td>
                                                    <c:if test="${x==0}">
                                                        <td rowspan="${invoiceProducts.size()}" class="table_td">

                                                            <c:if test="${invoice.socialCreditCode!=''}">
                                                                税号：${invoice.socialCreditCode}<br/>
                                                            </c:if>
                                                            <c:if test="${invoice.address!='' || invoice.phone!=''}">
                                                                地址、电话： ${invoice.address}${invoice.phone}<br/>
                                                            </c:if>
                                                            <c:if test="${invoice.depositBank!='' || invoice.account!=''}">
                                                                开户行及账号：${invoice.depositBank}${invoice.account}
                                                            </c:if>
                                                        </td>
                                                        <c:set value="1" var="x"/>
                                                    </c:if>


                                                </tr>
                                            </c:when>
                                        </c:choose>
                                    </c:forEach>
                                    </tbody>
                                </table>
                                <%--<p>${invoiceProduct.productName}&nbsp;${invoiceProduct.productType}&nbsp;${invoiceProduct.productNumber}支&nbsp;${invoiceProduct.productAmount}&nbsp;</p>--%>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    
    $(function () {
        tableAddClass();
        $("#invoiceAmount").val(countAmount);
    })


    //计算开票总额
    function countAmount() {
        var count = 0;
        <c:forEach items="${invoiceProducts}" var="invoiceProduct">
        count+= ${invoiceProduct.productAmount};
        </c:forEach>
        return count;
    }


    function createInvoice() {
        var orderUnitName = "";
        if($('#orderUnitName',window.parent.document).length>0){
            orderUnitName =$('#orderUnitName',window.parent.document).val();
        }
        window.location.href='<%=path%>/commonUserOrder/createInvoice.do?orderId=${orderId}&orderUnitName='+orderUnitName;
    }

    function editInvoice(data){
        window.location.href='<%=path%>/commonUserOrder/editInvoice.do?id='+data;
    }

    function deleteInvoice(data) {
        if(confirm("是否删除！")){
            openLoading();
            $.ajax({
                url: "<%=path%>/commonUserOrder/deleteInvoice.do",
                type: "POST",
                data: {"id":data},
                success: function(data){
                    closeLoading();
                    alert(data);
//                    changeDaoHang("收货地址管理")
                    window.location.reload();
                    <%--window.location.href='<%=path%>/commonUserOrder/chooseInvoice.do';--%>
                },error : function(XMLHttpRequest, textStatus, errorThrown) {
                    alert("删除失败" + errorThrown);
                    closeLoading();
                }
            });
        }
    }


</script>
</body>
</html>