<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="../../init.jsp" %>
<%@ include file="../../inc/header.jsp" %>
<%@ include file="../../power.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>数字证书服务网</title>
    <%--<link type="text/css" href="<%=path%>/css/bootstrap.min.css" rel="stylesheet" />--%>
    <%--&lt;%&ndash;<link type="text/css" href="<%=path%>/css/css.css" rel="stylesheet" />&ndash;%&gt;--%>
    <%--<link type="text/css" href="<%=path%>/css/main.css" rel="stylesheet" />--%>
    <%--<link type="text/css" href="<%=path%>/js/themes/jquery-ui.min.css"--%>
    <%--rel="stylesheet" />--%>

    <%-- 		<script type="text/javascript" src="<%=path%>/js/validate/jquery.validate.js"></script>
            <script type="text/javascript" src="<%=path%>/js/validate/additional-methods.js"></script>
            <script type="text/javascript" src="<%=path%>/js/validate/messages_zh.js"></script> --%>
    <%--<script type="text/javascript" src="<%=path%>/js/validate.js"></script>--%>
    <style type="text/css">
        /*.neir_2_kcgl_bod2 {*/
            /*background-color: white;*/
        /*}*/
        /*.neir_2_kcgl_bod {*/
            /*background-color: white;*/
        /*}*/
        input[type=radio],input[type=checkbox]{
            vertical-align: middle;
            /*margin-top: 0px;*/
            float: left;
            width: 74px;
            /*vertical-align: text-bottom;*/
            /*margin-bottom: 2px;*/
            /**margin-bottom: -2px;*/
        }
        /*body{*/
            /*background-color: #e6e6e6;*/
        /*}*/
    </style>

</head>
<body class="page_content page_color">
    <div class="content">
        <div class="tdiv" >
            <div class="tdiv_title">
                <c:if test="${status=='save'}">
                    <p class="unit" style="width: 150px;">新增自定义模板</p>
                </c:if>
                <c:if test="${status=='edit'}">
                    <p class="unit" style="width: 150px;">编辑自定义模板</p>
                </c:if>
            </div>
            <div class="neir_1_kcgl neir_2_kcgl_bod2">
                <div style="text-align: center">
                        <h4 style="float: left;">标题：</h4>
                        <input class="form-control templateName" style="float: left;margin-top: 0.3%;width: 250px;" />
                </div>
                <div class="clearfloat"></div>

                <input type="hidden" id="id"/>
                <hr/>
                <table class="table table-bordered" border="0" cellpadding="0" cellspacing="0">
                    <tbody>
                    <tr>
                        <td style="width: 20px">
                            <div class="checker">
                                <span><input type="checkbox" class="selectAllTr" /></span>
                            </div>
                        </td>

                        <td>
                            <div class='checker'><span> <input type='checkbox' class='orderCheckbox orderDate' value='orderDate'/></span></div>
                            订购日期
                        </td>
                        <td>
                            <div class='checker'><span> <input type='checkbox' class='orderCheckbox provinceName' value='provinceName'/></span></div>
                            省份
                        </td>
                        <td>
                            <div class='checker'><span> <input type='checkbox' class='orderCheckbox cityName' value='cityName'/></span></div>
                            地市
                        </td>
                        <td>
                            <div class='checker'><span> <input type='checkbox' class='orderCheckbox orderUnitName' value='orderUnitName'/></span></div>
                            订购单位
                        </td>
                        <td>
                            <div class='checker'><span> <input type='checkbox' class='orderCheckbox flowId' value='flowId'/></span></div>
                            流水号
                        </td>
                        <td>
                            <div class='checker'><span> <input type='checkbox' class='orderCheckbox productType' value='productType'/></span></div>
                            证书型号
                        </td>
                    </tr>
                    <tr>
                        <td style="width: 20px">
                            <div class="checker">
                                <span><input type="checkbox" class="selectAllTr" /></span>
                            </div>
                        </td>
                        <td>
                            <div class='checker'><span> <input type='checkbox' class='orderCheckbox productTotalCount' value='productTotalCount'/></span></div>
                            数量
                        </td>
                        <td>
                            <div class='checker'><span> <input type='checkbox' class='orderCheckbox productPrice' value='productPrice'/></span></div>
                            单价
                        </td>
                        <td>
                            <div class='checker'><span> <input type='checkbox' class='orderCheckbox productTotalAmount' value='productTotalAmount'/></span></div>
                            金额
                        </td>
                        <td>
                            <div class='checker'><span> <input type='checkbox' class='orderCheckbox prefixName' value='prefixName'/></span></div>
                            省份字母
                        </td>
                        <td>
                            <div class='checker'><span> <input type='checkbox' class='orderCheckbox supplier' value='supplier'/></span></div>
                            供货方
                        </td>
                        <td>
                            <div class='checker'><span> <input type='checkbox' class='orderCheckbox labelStart' value='labelStart'/></span></div>
                            起始号
                        </td>
                    </tr>
                    <tr>
                        <td style="width: 20px">
                            <div class="checker">
                                <span><input type="checkbox" class="selectAllTr" /></span>
                            </div>
                        </td>
                        <td>
                            <div class='checker'><span> <input type='checkbox' class='orderCheckbox labelEnd' value='labelEnd'/></span></div>
                            末尾号
                        </td>
                        <td>
                            <div class='checker'><span> <input type='checkbox' class='orderCheckbox labelSuffix' value='labelSuffix'/></span></div>
                            产品字母
                        </td>


                        <td>
                            <div class='checker'><span> <input type='checkbox' class='orderCheckbox expressTotalCount' value='expressTotalCount'/></span></div>
                            发货数
                        </td>
                        <td>
                            <div class='checker'><span> <input type='checkbox' class='orderCheckbox expressTotalAmount' value='expressTotalAmount'/></span></div>
                            发货金额
                        </td>
                        <td>
                            <div class='checker'><span> <input type='checkbox' class='orderCheckbox socialCreditCode' value='socialCreditCode;address;phone;depositBank;account'/></span></div>
                            开票信息
                        </td>
                        <td>
                            <div class='checker'><span> <input type='checkbox' class='orderCheckbox invoiceExpressNo' value='invoiceExpressNo'/></span></div>
                            发票快递单号
                        </td>
                    </tr>
                    <tr>
                        <td style="width: 20px">
                            <div class="checker">
                                <span><input type="checkbox" class="selectAllTr" /></span>
                            </div>
                        </td>
                        <td>
                            <div class='checker'><span> <input type='checkbox' class='orderCheckbox orderType' value='orderType'/></span></div>
                            类型
                        </td>
                        <td>
                            <div class='checker'><span> <input type='checkbox' class='orderCheckbox arrivalAccount' value='arrivalAccount'/></span></div>
                            到款账户
                        </td>
                        <%--<td>--%>
                            <%--<div class='checker'><span> <input type='checkbox' class='orderCheckbox invoiceNum' value='invoiceNum'/></span></div>--%>
                            <%--发票数量--%>
                        <%--</td>--%>
                        <td>
                            <div class='checker'><span> <input type='checkbox' class='orderCheckbox billingDate' value='billingDate'/></span></div>
                            开票日期
                        </td>
                        <td>
                            <div class='checker'><span> <input type='checkbox' class='orderCheckbox arrivalDate' value='arrivalDate'/></span></div>
                            到款日期
                        </td>
                        <td>
                            <div class='checker'><span> <input type='checkbox' class='orderCheckbox productName' value='productName'/></span></div>
                            证书类别
                        </td>
                        <td>
                            <div class='checker'><span> <input type='checkbox' class='orderCheckbox' value=''/></span></div>
                            部门
                        </td>
                    </tr>
                    <tr>
                        <td style="width: 20px">
                            <div class="checker">
                                <span><input type="checkbox" class="selectAllTr" /></span>
                            </div>
                        </td>



                        <td>
                            <div class='checker'><span> <input type='checkbox' class='orderCheckbox regionName' value='regionName'/></span></div>
                            大区
                        </td>
                        <td>
                            <div class='checker'><span> <input type='checkbox' class='orderCheckbox saleManager' value='saleManager'/></span></div>
                            销售经理
                        </td>
                        <td>
                            <div class='checker'><span> <input type='checkbox' class='orderCheckbox receiveName' value='receiveName'/></span></div>
                            收件人
                        </td>
                        <td>
                            <div class='checker'><span> <input type='checkbox' class='orderCheckbox receivePhone' value='receivePhone'/></span></div>
                            收件固定电话
                        </td>
                        <td>
                            <div class='checker'><span> <input type='checkbox' class='orderCheckbox receiveMobile' value='receiveMobile'/></span></div>
                            收件手机号
                        </td>
                        <td>
                            <div class='checker'><span> <input type='checkbox' class='orderCheckbox receiveUnitAddress' value='receiveUnitAddress'/></span></div>
                            收件地址
                        </td>
                    </tr>
                    <tr>
                        <td style="width: 20px">
                            <div class="checker">
                                <span><input type="checkbox" class="selectAllTr" /></span>
                            </div>
                        </td>


                        <td>
                            <div class='checker'><span> <input type='checkbox' class='orderCheckbox memo' value='memo'/></span></div>
                            备忘录
                        </td>
                        <td>
                            <div class='checker'><span> <input type='checkbox' class='orderCheckbox' value=''/></span></div>
                            证书规格
                        </td>
                        <td>
                            <div class='checker'><span> <input type='checkbox' class='orderCheckbox' value=''/></span></div>
                            合同
                        </td>
                        <td>
                            <div class='checker'><span> <input type='checkbox' class='orderCheckbox' value=''/></span></div>
                            警种
                        </td>
                        <td>
                            <div class='checker'><span> <input type='checkbox' class='orderCheckbox' value=''/></span></div>
                            签订状态
                        </td>
                        <td>

                        </td>
                    </tr>
                    <%--<tr>--%>
                        <%--<td style="width: 20px">--%>
                            <%--<div class="checker">--%>
                                <%--<span><input type="checkbox" class="selectAllTr" /></span>--%>
                            <%--</div>--%>
                        <%--</td>--%>
                        <%--<td>--%>
                            <%--<div class='checker'><span> <input type='checkbox' class='orderCheckbox productExpressNo' value='productExpressNo'/></span></div>--%>
                            <%--证书快递单号--%>
                        <%--</td>--%>
                        <%--<td>--%>
                            <%--<div class='checker'><span> <input type='checkbox' class='orderCheckbox productInvoiceExpressNo' value='productInvoiceExpressNo'/></span></div>--%>
                            <%--证书发票快递单号--%>
                        <%--</td>--%>
                    <%--</tr>--%>
                    </tbody>
                </table>
                    <c:if test="${status=='save'}">
                       <input type="button" class="btn btn-info btn-width" style="float: right" onclick="_postTemplate()" value="新建">
                    </c:if>
                    <c:if test="${status=='edit'}">
                        <input type="button" class="btn btn-info btn-width" style="float: right" onclick="_putTemplate()" value="保存">
                    </c:if>
                <div class="tdiv_bottom"></div>

            </div>
            <div class="clear_float"></div>
        </div>
</div>
<script type="text/javascript">
    $(function () {
        tableAddClass();


        $("body").on('click','td',function () {
            var checkbox = $(this).not(".selectAllTr").find("input[type=checkbox]");
            if(checkbox.is(":checked")){
                checkbox.prop("checked",false);
            }else if(checkbox.not(":checked")){
                checkbox.prop("checked",true);
            }
        })
//        阻止多选事件冒泡
        $('body').on('click','input[type=checkbox]',function (e) {
            e.stopPropagation();
        })

        //编辑 checked
        var tblTemplateExcel = '${templateExcel}';
        if(tblTemplateExcel != undefined && tblTemplateExcel != ""){
            tblTemplateExcel = JSON.parse(tblTemplateExcel);
            $(".templateName").val(tblTemplateExcel.templateName);
            $("#id").val(tblTemplateExcel.id);
            var template = tblTemplateExcel.excelField.split(";");
            for(var i in template){
                if(template[i]!=""){
                    $("."+template[i]).attr("checked",true);
                }
            }
        }

        $(".selectAllTr").click(function () {
            var eachCheckbox = $(this).parents('tr').find(".orderCheckbox");
            if($(this).prop("checked")==true){
                eachCheckbox.each(function () {
                    if($(this).val()!=""){
                    }
                    $(this).prop("checked",true);
                })
            }else{
                eachCheckbox.each(function () {
                    $(this).prop("checked",false);
                })
            }
        })
    })

    function _postTemplate() {
        if($(".templateName").val()==""){
            alert("请填写标题");
            return false;
        }
        var excelField = "";
        $.each($(".orderCheckbox"),function (index,item) {
            var checkStatus = $(item).prop('checked');
            var _var = $(item).val();
            if(_var!=""){
                if(checkStatus){
                    excelField +=_var+";";
                }
            }
        });
        if(excelField==""){
            alert("请选择一个字段");
            return false;
        }

        openLoading();
        $.ajax({
            url: "<%=path%>/excelTemplate/postTblTemplateExcel.do",
            type: "POST",
            data:{"templateName":$(".templateName").val(),"excelField":excelField},
            success: function(data){
                if(data.result=="SUCCESS"){
                    alert("提交成功！");
                    closeLoading();
                    location.href="<%=path%>/excelTemplate/getTemplateIndex.do";
                }else{
                    alert(data.errorMsg);
                    closeLoading();
                }
            }
        });
    }

    function _putTemplate() {
        if($(".templateName").val()==""){
            alert("请填写标题");
            return false;
        }
        var excelField = "";
        $.each($(".orderCheckbox"),function (index,item) {
            var checkStatus = $(item).prop('checked');
            var _var = $(item).val();
            if(_var!=""){
                if(checkStatus){
                    excelField +=_var+";";
                }
            }
        });
        if(excelField==""){
            alert("请选择一个字段");
            return false;
        }
        if($("#id").val()==""){
            alert("请重新选择模板");
            return false;
        }

        openLoading();
        $.ajax({
            url: "<%=path%>/excelTemplate/putTblTemplateExcel.do",
            type: "POST",
            data:{"templateName":$(".templateName").val(),"excelField":excelField,"id":$("#id").val()},
            success: function(data){
                if(data.result=="SUCCESS"){
                    alert("提交成功！");
                    closeLoading();
                    location.href="<%=path%>/excelTemplate/getTemplateIndex.do";
                }else{
                    alert(data.errorMsg);
                    closeLoading();
                }
            }
        });
    }

</script>
</body>
</html>
