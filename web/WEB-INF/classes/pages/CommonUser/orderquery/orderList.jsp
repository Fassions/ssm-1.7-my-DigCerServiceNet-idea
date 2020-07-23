<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<meta name="viewport" content="width=device-width,initial-scale=1">
<meta http-equiv="X-UA-Compatible" content="IE=edge">

<%@ include file="../../inc/header.jsp" %>
<%@ include file="../../init.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>数字证书服务网</title>
    <%--<link type="text/css" href="<%=path%>/css/css.css" rel="stylesheet" />--%>
    <link type="text/css" href="<%=path%>/css/bootstrap.min.css" rel="stylesheet" />
    <link href="<%=path%>/js/swf/css/default.css" rel="stylesheet" type="text/css" />
    <%-- 		<script type="text/javascript" src="<%=path%>/js/validate/jquery.validate.js"></script>
            <script type="text/javascript" src="<%=path%>/js/validate/additional-methods.js"></script>
            <script type="text/javascript" src="<%=path%>/js/validate/messages_zh.js"></script> --%>
    <%--<script type="text/javascript" src="<%=path%>/js/validate.js"></script>--%>
    <%--<script type="text/javascript" src="<%=path%>/js/example.js"></script>--%>
    <%--<script type="text/javascript" src="<%=path%>/js/layer.js"></script>--%>
    <script type="text/javascript" src="<%=path%>/js/datepicker/WdatePicker.js"></script>
    <link type="text/css" href="<%=path%>/css/main.css" rel="stylesheet" />
    <%--<link type="text/css" href="<%=path%>/js/themes/jquery-ui.min.css"--%>
          <%--rel="stylesheet" />--%>
    <style type="text/css">
        .checkText{
            height: 25px !important;
            width: 172px !important;
        }
        body{
            background-color: #e6e6e6;
        }
        input.file{ vertical-align:middle; position:relative; filter:alpha(opacity=0); opacity:0; z-index:1; width:382px; height: 36px; line-height: 36px; cursor:pointer; top: -36px; }

        form input.viewfile { z-index:99; border:1px solid #ccc; padding:2px; width:300px; vertical-align:middle; color:#999; }

    </style>
    <script type="text/javascript">
        $(function() {
            //跳转导航页面
            changeDaoHang('订单查询');
        })
    </script>
</head>
<body class="page_content">


    <div class="tdiv" >
        <div class="tdiv_title"><p>查询条件</p></div>
        <div class="neir_2">
            <div class="col-sm" style="margin: 0 auto;width: 100%;">

                <div class="tdiv_a_z">
                    <div class="tdiv_a_1" >证书类型名称：</div>
                    <%--<div class="tdiv_a_3">--%>
                    <%--<select style="padding: 0px 0px;height: 24px;width: 125px;" class="classData form-control"  name="selectProductName">--%>
                    <%--<option value="0" selected>当前用户</option>--%>
                    <%--<option value="1">公司用户</option>--%>
                    <%--</select>--%>
                    <%--<input type="text" style="padding: 0px 0px;height: 24px;" class=" form-control classData" name="productName"/>--%>
                    <%--</div>--%>
                    <div class="tdiv_a_5 appendProductName" style="width: 330px!important;">
                        <%--<input type="text" class="checkText form-control col-md-10 col-xs-4 classData" style="width: 148px!important;" name="productName"/>--%>
                    </div>
                    <div class="tdiv_a_1" >证书个数：</div>
                    <div class="tdiv_a_3">
                        <input type="text" class="checkText form-control col-md-10 col-xs-4 classData" name="productNumber"/>
                    </div>
                </div>
                <div class="clearfloat"></div>
                <div class="tdiv_a_z">
                    <div class="tdiv_a_1" >下单时间：</div>
                    <div class="tdiv_a_5" style="width: 330px!important;">
                        <input type="text" class="checkText form-control col-md-10 col-xs-4 classData" name="orderDateStart" onclick="WdatePicker()" style="width: 140px!important;"/>
                        <div class="col-md-1 col-xs-1" style="width: 45px;">
                            到
                        </div>
                        <input type="text" class="checkText form-control col-md-10 col-xs-4 classData" name="orderDateEnd" onclick="WdatePicker()" style="width: 140px!important;"/>
                    </div>
                    <div class="tdiv_a_1" >订单提交人：</div>
                    <select style="padding: 0px 0px;height: 24px;" class="classData form-control" id="orderUser" name="orderUser">
                        <option value="0" selected>当前用户</option>
                        <option value="1">本单位用户</option>
                    </select>
                </div>
                <div class="clearfloat"></div>
                <div class="tdiv_a_z" style="display: none;" id="orderUserDiv">
                    <div class="tdiv_a_1" >订单提交人姓名：</div>
                    <div class="tdiv_a_5">
                        <input type="text" class="checkText classData form-control col-md-10 col-xs-4" name="purchaser"/>
                    </div>
                    <div class="tdiv_a_1" >订单提交人身份证：</div>
                    <div class="tdiv_a_3">
                        <input type="text" class="checkText form-control col-md-10 col-xs-4 classData" name="certificateCode"/>
                    </div>
                </div>
            </div>
        </div>

        <div class="clearfloat"></div>
        <hr class="hr_style"/>
        <div class="neir_2">
            <div class="col-sm" style="text-align: center;">
                <input type="button" class="btn btn-info" style="cursor : pointer!important;width: 6%" value="查询" id="basicInfoSearch"/>
                <input type="button" class="btn btn-info" style="cursor : pointer!important;width: 6%" value="重置" id="reset"/>
            </div>
        </div>
        <div class="neir_bottom"></div>


    <%--<div class="tdiv" >--%>
            <%--<div class="tdiv">--%>
                <%--<div class="tdiv_s">--%>
                    <%--<a  type="button" class="tdiv_sub_c" id="basicInfoSearch">查询</a></div>--%>
                <%--<div class="clearfloat"></div>--%>
            <%--</div>--%>
        <%--</div>--%>
        </div>

    <div class="tdiv">

        <div class="tdiv_title"><p>订单信息</p></div>
        <div id="classDataView" style="min-height: 400px">


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

    <%--快递信息model--%>
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
                                    <option value="1">发票</option>
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
                        </div>
                        <div class="clearfloat"></div>
                        <div class="tdiv_bottom"></div>
                    </form>
                </div>
            </div>
        </div>
    </div>

    <%--签收单上传模态框--%>
    <div class="modal fade" id="receiptModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog modal-lg" role="document">
            <div class="modal-content" style="width: 100%">
                <div class="modal-header">
                    <button type="button" class="close invoiceDetailsClose" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
                    <h4 class="modal-title">签收单上传</h4>
                </div>
                <div class="modal-body">
                    <form id="receiptUploadForm" enctype="multipart/form-data" method="post">
                        <input type="hidden" name="orderId" class="orderId"/>
                        <div class="tdiv_a_z" style="margin-top: 28px;">
                            <div class="tdiv_a_1">
                                <%--上传：--%>
                            </div>
                            <div class="tdiv_a_2" style="width: 580px;">
                                <span>
                                    <input type="text"  name="fileName" id = "fileName" style="height: 33px;width: 280px" class = "form-control viewfile"/>
                                </span>
                                <label  class="btn btn-info bottom" style="margin-left: 5px;">选择文件</label>
                                <label  class="btn btn-info bottom" style="margin-left: 10px;" onclick="_receiptUpload()">上传</label>
                                <input type="file" id="zipFile" name="zipFile" style="margin-left: -1%" multiple="multiple" onchange="document.getElementById('fileName').value=this.value;" class="file">
                            </div>
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
                                                <ul id="pic_list">

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
                    <div class="clearfloat"></div>
                </div>

                <div class="modal-footer">
                    <%--<div class="tdiv_a_z">--%>
                    <%--</div>--%>
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
        var appendProductName = $(".appendProductName");       //证书类型 通用下拉选项
        appendProductName.prepend(appendProductNames());
        //基本信息搜索
        $("body").on("click","#basicInfoSearch",function () {
            currentPageIndex = 1;
            openLoading();
            parserAsyncClassDataFormData();
        })

        $("#orderUser").change(function () {    //公司用户显示 个人搜索条件
            if($(this).val()=="1"){
                $("#orderUserDiv").show();
            }else{
                $("#orderUserDiv").hide();
            }
        })
        //分页查询
//        $("body").on("click", ".pagination li.item a", itemOfpageViewOnSelected);
        $("body").on("click", ".pagination li a", itemOfpageViewOnSelected);
        parserAsyncClassDataFormData();

    })
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
        var json = {OrderQuerySearcher: {}};
        $.each($(".classData"), function (index, item) {
            var key = $(item).attr("name"), _val = $(item).val();
            json.OrderQuerySearcher[key] = _val;
        });
        json.OrderQuerySearcher["pageCount"] = pageSize;
        json.OrderQuerySearcher["offset"] = (currentPageIndex - 1) * pageSize;
        $.ajax({
            url: "<%=path%>/commonUserOrderQuery/showOrderPage.do",
            type: "POST",
            data: json.OrderQuerySearcher,
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
        var view = $("#classDataView");
        view.empty();
        var i = 0;
        var param = "";
        param += Objects.format("<div class=\"kcgl_modal_neir\">");
        param += Objects.format("<div class=\"\" style=\"width: 98%;margin: 0 auto;\">");
        for (var data in datas.data) {
            var item = datas.data[data];
//            param += Objects.format("<div class=\"kcgl_modal_neir\">");
//            param += Objects.format("<div class=\"neir_3_kcgl kcgl_modal_1\" style=\"width: 100%\">");
            param += Objects.format(" <div class=\"tdiv_a_z\" style=\"margin-bottom: 5px\">");
            param += Objects.format("<div class=\"tdiv_a_3 strong\" >订单号：{0}</div>",item.flowId!=null?item.flowId:"");
            //订单状态
            param += Objects.format("<div class=\"tdiv_a_3 strong\"><strong>订单状态：<span class='red_label'>{0}</span></strong></div>",getOrderStatusUser(item.orderStatus,item.returnReason));
            param += Objects.format("<div class=\" strong\" style=\"float: right;margin-left: 3%;\" >订购日期：{0}</div>",item.orderDate!=null?getLocalTimeYYYYMMDD(item.orderDate):"");
            param += Objects.format("<div class=\" strong\" style=\"float: right\" >提交人：{0} {1}</div>",item.userName!=null?item.userName:"",item.certificateCode!=null?item.certificateCode:"");
            param += Objects.format("</div>");
            param += Objects.format("<div class=\"clearfloat\"></div>");
            param += Objects.format("<table width=\"100%\" class=\"table\" id=\"tableProductList\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">");
//            param += Objects.format("<thead class=\"thead\">");
            param += Objects.format("<tr>");
            param += Objects.format("<th class=\"table_td th\">证书类型名称</th>");
            param += Objects.format("<th class=\"table_td th\">证书型号</th>");
            param += Objects.format("<th class=\"table_td th\">数量(支)</th>");
            param += Objects.format("<th class=\"table_td th\">单价(元)</th>");
            param += Objects.format("<th class=\"table_td th\">金额(元)</th>");
            param += Objects.format("<th class=\"table_td th\">编号</th>");
            param += Objects.format("<th class=\"table_td th\">快递单号</th>");
            param += Objects.format("</tr>");
//            param += Objects.format("</thead>");
            param += Objects.format("<tbody class = \"tbody\">");
            for(var i in item.orderProductExpressVos){
                var product = item.orderProductExpressVos[i];
                param += Objects.format("<tr>");
                param += Objects.format("<td class=\"table_td\">{0}</td>",product.productName!=null?product.productName:"");
                param += Objects.format("<td class=\"table_td\">{0}</td>",product.productType!=null?product.productType:"");
                param += Objects.format("<td class=\"table_td\">{0}</td>",product.productNumber!=null?product.productNumber:"");
                param += Objects.format("<td class=\"table_td\">{0}</td>",product.productPrice!=null?product.productPrice:"");
                param += Objects.format("<td class=\"table_td\">{0}</td>",product.productAmount!=null?product.productAmount:"");
                param += Objects.format("<td class=\"table_td\">{0}-{1}</td>",product.labelStart!=null?product.labelStart:"",product.labelEnd!=null?product.labelEnd:"");
                if(isNull(product.expressNo)){
                    param += Objects.format("<td class=\"table_td\"></td>");
                }else {
                    param += Objects.format("<td class=\"table_td\"><a style='cursor: pointer' onclick=\"_getOrderExpress('{1}')\">{0}</a></td>", product.expressNo,
                            product.expressId
                            + "," + product.expressType
                            + "," + product.expressName
                            + "," + product.expressNo
                            + "," + product.expressMessage);
                    param += Objects.format("</tr>");
                }
            }
            param += Objects.format("</tbody>");
            param += Objects.format("</table>");
            param += Objects.format("<div class=\"tdiv_a_z\" style=\"margin-bottom: 15px;width: 100%;\">");
            param += Objects.format("<div class=\"tdiv_a_3 strong\" >收货人：{0}</div>",item.receiveName!=null?item.receiveName:"");
//            param += Objects.format("<div class=\"tdiv_a_2\" >{0}</div>",item.receiveName!=null?item.receiveName:"");

            param += Objects.format("<div class=\"tdiv_text\" style=\"float: right\" ><input type='button' class='btn btn-info' value='查看详情' onclick=\"detailsOrder('{0}')\"/></div>",item.orderId);
            if(Number(item.orderStatus)=="5"){
                param += Objects.format("<div class=\"tdiv_text\" style=\"float: right\"><input type='button' class='btn btn-info' value='编辑' onclick=\"editOrder('{0}')\"/></div>",item.orderId);
            }
            if(Number(item.orderStatus)=="3"){
                if(item.complateType=="0"){
                    param += Objects.format("<div class=\"tdiv_text\"  style=\"float: right\" ><input type='button' class='btn btn-info' onclick=\"_putComplate('{0}')\" value='确认收货'/> </div>",item.orderId);
                }
            }
            if(Number(item.orderStatus)=="4"){ //已完成
                param += Objects.format("<div class=\"tdiv_text\"  style=\"float: right\" ><input type='button' class='btn btn-info' onclick=\"_showReceiptModal('{0}')\" value='签收单上传'/> </div>",item.orderId);
            }
            param += Objects.format("<div class=\"clearfloat\"></div>");
            param += Objects.format("</div>");
            var ss = Number(data);
            var sss = datas.data.length-1;
            if(ss!=sss){
                param += Objects.format("<hr class=\"hr_style\"/>");
                param += Objects.format("<div class=\"clearfloat\"></div>");
            }else{
                param += Objects.format("<div class=\"clearfloat\"></div>");
                param += Objects.format("<div class=\"neir_bottom\"></div>");
            }

//            param += Objects.format("</div>");
//            param += Objects.format("</div>");
        }
        param += Objects.format("</div>");
        param += Objects.format("</div>");
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

    function detailsOrder(orderId) {
        changeDaoHangFrame('订单查询`<%=path%>/commonUserOrderQuery/getOrderList.do,订单详情`<%=path%>/commonUserOrderQuery/showDetailsOrder.do?orderId='+orderId);
        window.location.href='<%=path%>/commonUserOrderQuery/showDetailsOrder.do?orderId='+orderId;
    }
    function editOrder(orderId) {
        changeDaoHangFrame('订单查询`<%=path%>/commonUserOrderQuery/getOrderList.do,编辑`<%=path%>/commonUserOrder/backEditOrder.do?orderId='+orderId);
        window.location.href='<%=path%>/commonUserOrder/backEditOrder.do?orderId='+orderId;
    }

    function _showReceiptModal(orderId) {
        $("#receiptUploadForm .orderId").val('');
        $("#receiptUploadForm .orderId").val(orderId);
        $("#receiptModal").modal("show");
        $.ajax({
            url: "<%=path%>/commonUserOrderQuery/getReceiptUploadFiles.do",
            type: "POST",
            data:{"orderId":orderId},
            success: function(data){
                if(data.result=="SUCCESS"){
                    closeLoading();
                    //回传页面
                    $("#pic_list").empty();
                    $("#fileName").val('');
                    for(var i in data.message){
                        var src = data.message[i].id;
//						var src = data.errorMsg;
                        var ioReadImg = "/file/ioReadImageReceipt.do?id="+src;
                        var newElement = "<li id='img_"+src+"' ><img class='content' onclick='clickImg(this.src)'  src=" + ioReadImg + " style=\"width:100px;height:100px;\"><img class='button' onclick=\"_deleteReceiptUnloadFiles('"+src+"')\" src='/js/swf/images/fancy_close.png'></li>";
                        $("#pic_list").append(newElement);
                    }
                }else{
                    alert(data.errorMsg);
                    closeLoading();
                }
            }
        })
    }

    function _receiptUpload() {
        if($("#fileName").val()==""){
            alert("请传入上传文件。");
            return false;
        }
//		var theform = $("#uploadForm");
        <%--var theform = document.getElementById("uploadForm");--%>
        <%--theform.action = "<%=path%>/file/uploadOrderInfo.do";--%>
        <%--theform.submit();--%>
//		var formData = new FormData($("#uploadForm")[0]);

        openLoading();
        $("#receiptUploadForm").ajaxSubmit({
            type:'post',
            url: "<%=path%>/file/uploadReceiptInfo.do",
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
                        var ioReadImg = "/file/ioReadImageReceipt.do?id="+src;
                        var newElement = "<li id='img_"+src+"' ><img class='content' onclick='clickImg(this.src)'  src=" + ioReadImg + " style=\"width:100px;height:100px;\"><img class='button' onclick=\"_deleteReceiptUnloadFiles('"+src+"')\" src='/js/swf/images/fancy_close.png'></li>";
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
    }

    function _deleteReceiptUnloadFiles(unloadFilesId) {
        if(confirm("是否删除！")){
            openLoading();
            $.ajax({
                url: "<%=path%>/commonUserOrder/deleteReceiptUnloadFiles.do",
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


    function clickImg(src) {
        var newwin = window.open();
        newwin.document.write("<img src='"+src+"'/>");
    }


    function _putComplate(orderId){
        openLoading();
        $.ajax({
            url: "<%=path%>/commonUserOrderQuery/putComplate.do",
            type: "POST",
            data:{"orderId":orderId},
            success: function(data){
                if(data.result=="SUCCESS"){
                    alert("提交成功！");
                    closeLoading();
//                    $("#complateDiv_"+orderId).remove();
                    parserAsyncClassDataFormData();
                    <%--location.href= "<%=path%>/file/downloadTemp.do?fileName="+data.errorMsg;--%>
                }else{
                    alert(data.errorMsg);
                    closeLoading();
                }
            }
        })
    }

    //获取快递信息回调模态框
    function _getOrderExpress(data) {
        $("#orderExpressId").val(data.split(',')[0]);
        var arrivalAccount = data.split(',')[1];
        $("select[name=expressType]").val(arrivalAccount);
        $("input[name=expressName]").val(data.split(',')[2]);
        $("input[name=expressNo]").val(data.split(',')[3]);
        var expressMessage = data.split(',')[4];
        $("textarea[name=expressMessage]").val(expressMessage=="null"?"":expressMessage);
        $("#orderExpressModal").modal('show');
        $("#productionFlowId").val('');//变更无需绑定流水号

        // 用户禁用form
        $("#expressForm .form-control").attr( "disabled", "disabled" );
        //删除品名
        $(".expressType").remove();
    }

    </script>
</body>
</html>
