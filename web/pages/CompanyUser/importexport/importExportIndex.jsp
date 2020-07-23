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
    <%--<link type="text/css" href="<%=path%>/css/css.css" rel="stylesheet" />--%>
    <%--<link type="text/css" href="<%=path%>/css/main.css" rel="stylesheet" />--%>


    <style type="text/css">
        body{
             background-color: #e6e6e6;
        }
        input.file{ vertical-align:middle; position:relative; filter:alpha(opacity=0); opacity:0; z-index:1; width:382px; height: 36px; line-height: 36px; cursor:pointer; top: -36px; }

        form input.viewfile { z-index:99; border:1px solid #ccc; padding:2px; width:300px; vertical-align:middle; color:#999; }

        .tdiv_a_3{
            min-width: 160px!important;
        }

        /*form p span { float:left; }*/
    </style>

    <script type="text/javascript">

        $(function () {
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
<body class="page_content">
    <div class="content">
        <div class="down_area">
            <div class="tdiv">
                <div class="tdiv_title">
                    <i class="menu-i"></i>
                    <p class="unit">导出</p>
                </div>
                <div>
                    <div class="tdiv_a_z">
                        <div class="tdiv_a_6" style="margin-left: 15px">
                            <div class="upload_image1">
                                <input type="radio" name="exportRadio"  value="1">
                                <div class="upload_image2"></div>
                                <div class="upload_text">财务开票表</div>
                            </div>
                        </div>
                        <div class="tdiv_a_6" >
                            <div class="upload_image1">
                                <input type="radio" name="exportRadio" value="4">
                                <div class="upload_image2"></div>
                                <div class="upload_text">开票快递面单</div>
                            </div>
                        </div>
                        <div class="tdiv_a_6" >
                            <div class="upload_image1">
                                <input type="radio" name="exportRadio" value="2">
                                <div class="upload_image2"></div>
                                <div class="upload_text">产品快递面单</div>
                            </div>
                        </div>
                        <div class="tdiv_a_6" >
                            <div class="upload_image1">
                                <input type="radio" name="exportRadio" value="3">
                                <div class="upload_image2"></div>
                                <div class="upload_text">订购单管理表</div>
                            </div>
                        </div>
                        
                        <div class="clearfloat"></div>
                        <div class="tdiv_a_6" style="margin-left: 15px">
                            <div class="upload_image1">
                                <input type="radio" name="exportRadio" value="0">
                                <div class="upload_image2"></div>
                                <div class="upload_text">生产任务单</div>
                            </div>
                        </div>
                        

                        <input type="button" class="btn btn-info btn-width btn-right"  value="导出" onclick="_showExportModel()">
                        <div class="clearfloat"></div>
                        <%--<div class="tdiv_a_1">--%>
                            <%--<input type="button" class="btn btn-info" onclick="_exportExcel('1')"  value="导出财务开票表"/>--%>
                        <%--</div>--%>
                        <%--<div class="tdiv_a_1">--%>
                            <%--<input type="button" class="btn btn-info" onclick="_exportExcel('2')"  value="导出快递发运单"/>--%>
                        <%--</div>--%>
                        <%--<div class="tdiv_a_1">--%>
                            <%--<input type="button" class="btn btn-info" onclick="_exportExcel('0')"  value="导出生产任务单"/>--%>
                        <%--</div>--%>
                        <%--<div class="tdiv_a_1">--%>
                            <%--<input type="button" class="btn btn-info" data-toggle="modal" data-target="#templateExcelModal" value="导出订购单管理表"/>--%>
                        <%--</div>--%>
                    </div>
                    <div class="clearfloat"></div>
                    <div class="tdiv_bottom"></div>

                    </div>
                </div>

            <div class="tdiv" >
                <div class="tdiv_title">
                    <i class="menu-i"></i>
                    <p class="unit">导入</p>
                </div>
                <div>
                    <div class="tdiv_a_z">
                        <%--暂不需要隐藏--%>
                        <%--<div class="tdiv_a_6" style="margin-left: 15px">--%>
                            <%--<div class="upload_image1">--%>
                                <%--<input type="radio" name="importRadio"  value="myModal">--%>
                                <%--<div class="upload_image2"></div>--%>
                                <%--<div class="upload_text">到款信息</div>--%>
                            <%--</div>--%>
                        <%--</div>--%>
                        <div class="tdiv_a_6" style="margin-left: 15px">
                            <div class="upload_image1">
                                <input type="radio" name="importRadio" value="myModalInvoice">
                                <div class="upload_image2"></div>
                                <div class="upload_text">开票日期</div>
                            </div>
                        </div>
                        <div class="tdiv_a_6" >
                            <div class="upload_image1">
                                <input type="radio" name="importRadio"  value="myModalInvoiceExpress">
                                <div class="upload_image2"></div>
                                <div class="upload_text">开票快递信息</div>
                            </div>
                        </div>
                        <div class="tdiv_a_6" >
                            <div class="upload_image1">
                                <input type="radio" name="importRadio"  value="myModalExpress">
                                <div class="upload_image2"></div>
                                <div class="upload_text">产品快递信息</div>
                            </div>
                        </div>

                        <input type="button" class="btn btn-info btn-width btn-right"  value="导入" onclick="_showImportModel()">
                        <div class="clearfloat"></div>
                    </div>

                    <%--<div class="tdiv_a_1">--%>
                    <%--<input type="button" class="btn btn-info" data-toggle="modal" data-target="#myModal"  value="导入到款信息"/>--%>
                    <%--</div>--%>
                    <%--<div class="tdiv_a_1">--%>
                    <%--<input type="button" class="btn btn-info" data-toggle="modal" data-target="#myModalInvoice" value="导入开票日期"/>--%>
                    <%--</div>--%>
                    <%--<div class="tdiv_a_1">--%>
                    <%--<input type="button" class="btn btn-info" data-toggle="modal" data-target="#myModalExpress"  value="导入快递发运信息"/>--%>
                    <%--</div>--%>
                    <div class="clearfloat"></div>
                    <div class="tdiv_bottom"></div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <%--生产任务单模态框--%>
    <div class="modal fade" id="productionExcelModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog modal-lg" role="document">
            <div class="modal-content" style="width: 100%">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
                    <h4 class="modal-title">导出生产任务单</h4>
                </div>
                <div class="modal-body">
                    <form id="productionForm">
                        <input type="hidden" name="excelId" value="0"/>
                        <div class="tdiv_a_z">
                            <div class="tdiv_a_1">请选择日期：</div>
                            <div class="tdiv_a_3">
                                <input type="text" class="checkText form-control col-md-10 col-xs-4 classData" name="startDate" onclick="WdatePicker()">
                                <div class="col-md-1 col-xs-1" style="width: 45px;">
                                    到
                                </div>
                                <input type="text" class="checkText form-control col-md-10 col-xs-4 classData" name="endDate" onclick="WdatePicker()">
                            </div>
                        </div>
                        <div class="tdiv_a_z appendCheckBoxProductNames">

                        </div>
                    </form>
                    <div class="clearfloat"></div>
                </div>

                <div class="modal-footer">
                    <div class="tdiv_a_z">
                        <input type="button" class="btn btn-info btn-width btn-right" onclick="_exportExcel('0')" value="导出" />
                    </div>
                </div>
            </div>
        </div>
    </div>
    <%--产品快递发运单模态框--%>
    <div class="modal fade" id="expressExcelModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog modal-lg" role="document">
            <div class="modal-content" style="width: 100%">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
                    <h4 class="modal-title">导出产品快递单</h4>
                </div>
                <div class="modal-body">
                    <form id="expressForm">
                        <input type="hidden" name="excelId" value="2"/>
                        <div class="tdiv_a_z">
                            <div class="tdiv_a_1">请选择日期：</div>
                            <div class="tdiv_a_3">
                                <input type="text" class="checkText form-control col-md-10 col-xs-4 classData" name="startDate" onclick="WdatePicker()">
                                <div class="col-md-1 col-xs-1" style="width: 45px;">
                                    到
                                </div>
                                <input type="text" class="checkText form-control col-md-10 col-xs-4 classData" name="endDate" onclick="WdatePicker()">
                            </div>
                        </div>
                    </form>
                    <div class="clearfloat"></div>
                </div>

                <div class="modal-footer">
                    <div class="tdiv_a_z">
                        <input type="button" class="btn btn-info btn-width btn-right" onclick="_exportExcel('2')" value="导出" />
                    </div>
                </div>
            </div>
        </div>
    </div>
    <%--开票快递发运单模态框--%>
    <div class="modal fade" id="invoiceExpressExcelModal" tabindex="-1" role="dialog" 
    	aria-labelledby="myModalLabel">
        <div class="modal-dialog modal-lg" role="document">
            <div class="modal-content" style="width: 100%">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" 
                    	aria-label="Close"><span aria-hidden="true">×</span>
                   	</button>
                    <h4 class="modal-title">导出开票快递单</h4>
                </div>
                <div class="modal-body">
                    <form id="invoiceExpressForm">
                        <input type="hidden" name="excelId" value="4"/>
                        <div class="tdiv_a_z">
                            <div class="tdiv_a_1">请选择日期：</div>
                            <div class="tdiv_a_3">
                                <input type="text" class="checkText form-control 
	                                col-md-10 col-xs-4 classData" name="startDate" 
	                                onclick="WdatePicker()">
                                <div class="col-md-1 col-xs-1" style="width: 45px;">到</div>
                                <input type="text" class="checkText form-control 
	                                col-md-10 col-xs-4 classData" name="endDate" 
	                                onclick="WdatePicker()">
                            </div>
                        </div>
                    </form>
                    <div class="clearfloat"></div>
                </div>
                <div class="modal-footer">
                    <div class="tdiv_a_z">
                        <input type="button" class="btn btn-info btn-width btn-right" 
                        	onclick="_exportExcel('4')" value="导出" />
                    </div>
                </div>
            </div>
        </div>
    </div>
    
    <%--订购单管理表模态框--%>
<div class="modal fade" id="templateExcelModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog modal-lg" role="document">
        <div class="modal-content" style="width: 100%">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
                <h4 class="modal-title" id="myModalLabel" >导出订购单管理表</h4>
            </div>
            <div class="modal-body">
                <form id="orderForm">
                    <input type="hidden" name="orderId" value="${orderId}"/>

                    <div class="tdiv_a_z">
                        <div class="tdiv_a_1">选择年份：</div>
                        <div class="tdiv_a_3">
                            <select name="authYear" id="authYear" style="width:300px;padding: 0px 0px;height: 24px;float: left;margin-right: 40px;" class="form-control">
                                <option value="" selected>请选择</option>

                            </select>
                        </div>
                        <%--<div class="tdiv_a_3"><input type="button" class="btn btn-info" onclick="_exportExcel('3')" value="导出" style="margin: -8px 0 0 0;"/></div>--%>
                    </div>
                    <div class="clearfloat"></div>
                </form>
            </div>

            <div class="modal-footer">
                <div class="tdiv_a_z">
                    <input type="button" class="btn btn-info btn-width btn-right" onclick="_exportExcel('3')" value="导出" />
                </div>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">导入到款信息上传附件</h4>
            </div>
            <div class="modal-body">
                <form id="formId" name="uploadForm" method="post" enctype="multipart/form-data">

                    <p class="form-group">
                        <span>
                            <input type="text" name="viewfile" id="viewfile" class="form-control viewfile" style="height: 36px;"/>
                            <label for="upload" class="btn btn-info bottom" style="margin-left: 10px">选择文件</label>
                            <input type="file" name="uploadFile" onchange="document.getElementById('viewfile').value=this.value;" class="file"/>

                        </span>
                    </p>
                    <strong><a href="<%=path%>/modal/PaidImport.xls">模板文件下载</a> </strong>
                    <div class="modal-footer">
                        <p class="clear"><input class="btn btn-primary submit" type="button" onclick="_importPaid()" value="确定上传"/></p>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="myModalInvoice" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">导入开票日期上传附件</h4>
            </div>
            <div class="modal-body">
                <form  id="myModalInvoiceForm" name="uploadForm" method="post" enctype="multipart/form-data">

                    <p class="form-group">
                        <span>
                            <input type="text" name="invoiceViewfile" id="invoiceViewfile" class="form-control viewfile" style="height: 36px;"/>
                            <label for="upload" class="btn btn-info bottom" style="margin-left: 10px">选择文件</label>
                            <input type="file" name="uploadFile" onchange="document.getElementById('invoiceViewfile').value=this.value;" class="file"/>

                        </span>
                    </p>
                    <strong><a href="<%=path%>/modal/InvoiceImport.xls">模板文件下载</a> </strong>
                    <div class="modal-footer">
                        <p class="clear"><input class="btn btn-primary submit" type="button" onclick="_importInvoice()" value="确定上传"/></p>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<%--产品快递信息模板--%>
<div class="modal fade" id="myModalExpress" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">导入快递发运信息上传附件</h4>
            </div>
            <div class="modal-body">
                <form  id="myModalExpressForm" name="uploadForm" method="post" enctype="multipart/form-data">

                    <p class="form-group">
                        <span>
                            <input type="text" name="expressViewfile" id="expressViewfile" class="form-control viewfile" style="height: 36px;"/>
                            <label for="upload" class="btn btn-info bottom" style="margin-left: 10px">选择文件</label>
                            <input type="file" name="uploadFile" onchange="document.getElementById('expressViewfile').value=this.value;" class="file"/>

                        </span>
                    </p>
                    <strong><a href="<%=path%>/modal/ExpressImport.xls">模板文件下载</a> </strong>
                    <div class="modal-footer">
                        <p class="clear"><input class="btn btn-primary submit" type="button" onclick="_importExpress()" value="确定上传"/></p>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
    <%--开票快递信息模板--%>
<div class="modal fade" id="myModalInvoiceExpress" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">导入开票快递发运信息上传附件</h4>
            </div>
            <div class="modal-body">
                <form  id="myModalInvoiceExpressForm" name="uploadForm" method="post" enctype="multipart/form-data">

                    <p class="form-group">
                        <span>
                            <input type="text" name="expressInvoiceViewfile" id="expressInvoiceViewfile" class="form-control viewfile" style="height: 36px;"/>
                            <label for="upload" class="btn btn-info bottom" style="margin-left: 10px">选择文件</label>
                            <input type="file" name="uploadFile" onchange="document.getElementById('expressInvoiceViewfile').value=this.value;" class="file"/>
                        </span>
                    </p>
                    <strong><a href="<%=path%>/modal/InvoiceExpressImport.xls">模板文件下载</a></strong>
                    <div class="modal-footer">
                        <p class="clear"><input class="btn btn-primary submit" type="button" onclick="_importInvoiceExpress()" value="确定上传"/></p>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    //设置年份选择
    var myDate = new Date();
    var startYear = 2018; //起始年份
//    if(startYear < 2018){
//        startYear = 2018;
//    }
    var endYear = myDate.getFullYear(); //结束年份
    var obj = document.getElementById('authYear');
    obj.options.remove(1);
    for(var i = startYear;i <=endYear;i++){
        obj.options.add(new Option(i,i));
    }
    obj.options[obj.options.length-1].selected=1;
</script>
<script type="text/javascript">
    $(".tdiv_a_6").click(function () {
        $(this).find("input[type=radio]").prop("checked","checked");
    })
    //显示导入模态框
    function _showImportModel() {
        var modelType = $("input[name=importRadio]:checked").val();
        if(modelType=="" || modelType == undefined){
            alert("请选择一个导入模板。");
            return false;
        }
        $("#"+modelType).modal("show");

    }
    //导出按钮跳转
    function _showExportModel(){
        var modelType = $("input[name=exportRadio]:checked").val();
        if(modelType=="" || modelType == undefined){
            alert("请选择一个导出模板。");
            return false;
        }
		// 订购单管理表模态框
        if(modelType=="3"){
            $("#templateExcelModal").modal("show");
            return false;
        }else if(modelType=="0"){ //生产任务单
            $(".appendCheckBoxProductNames").append(appendCheckBoxProductIdNames($(".appendCheckBoxProductNames")));
            $("#productionExcelModal").modal("show");
            return false;
        } else if(modelType=="2"){//产品快递面单
           $("#expressExcelModal").modal('show');
            return false;
        } else if(modelType=="4"){//开票快递面单
           $("#invoiceExpressExcelModal").modal('show');
            return false;
        } else {
            //导出数据
            _exportExcel(modelType);
        }

    }
    function _exportExcel(excelId){
        var href = "";
        if(excelId==""){
            alert("请重新选择。");
            return false;
        }
        //生产任务单
        if(excelId=="0"){
            if($("#productionForm input[name='startDate']").val()==""){
                alert("请填写开始日期。");
                return false;
            }
            if($("#productionForm input[name='endDate']").val()==""){
                alert("请填写结束日期。");
                return false;
            }
            href = "<%=path%>/commonCompanyImportExport/modelExportExcel.do?"+$("#productionForm").serialize();
        }else if(excelId=="2"){ //产品快递发运单
            if($("#expressForm input[name='startDate']").val()==""){
                alert("请填写开始日期。");
                return false;
            }
            if($("#expressForm input[name='endDate']").val()==""){
                alert("请填写结束日期。");
                return false;
            }
            href = "<%=path%>/commonCompanyImportExport/modelExportExcel.do?"+$("#expressForm").serialize();
        }else if(excelId=="3"){  //订购单管理表
            var year = $("#authYear").val();
            href = "<%=path%>/commonCompanyImportExport/exportExcel.do?excelId="+excelId+"&year="+year;
        } else if(excelId=="4") { //开票快递发运单
            if($("#invoiceExpressForm input[name='startDate']").val()==""){
                alert("请填写开始日期。");
                return false;
            }
            if($("#invoiceExpressForm input[name='endDate']").val()==""){
                alert("请填写结束日期。");
                return false;
            }
            href = "<%=path%>/commonCompanyImportExport/modelExportExcel.do?"+$("#invoiceExpressForm").serialize();
        } else {
            href = "<%=path%>/commonCompanyImportExport/exportExcel.do?excelId="+excelId;
        }
        location.href= href;

    }
    function _importPaid() {
        openLoading();
        var formData = new FormData($("#formId")[0]);
        var url = "<%=path%>/commonCompanyImportExport/importPaid.do";
        imports(url,formData);
    }

    function _importInvoice() {
        openLoading();
        var formData = new FormData($("#myModalInvoiceForm")[0]);
        var url = "<%=path%>/commonCompanyImportExport/importInvoice.do";
        imports(url,formData);
    }

    function _importExpress() {
        var formData = new FormData($("#myModalExpressForm")[0]);
        var url =  "<%=path%>/commonCompanyImportExport/importExpress.do";
        imports(url,formData);
    }

    function _importInvoiceExpress(){
        var formData = new FormData($("#myModalInvoiceExpressForm")[0]);
        var url =  "<%=path%>/commonCompanyImportExport/importInvoiceExpress.do";
        imports(url,formData);
    }

    function imports(url,formData) {
        openLoading();
        $.ajax({
            url: url,
            type: "POST",
            data:formData,
            cache:false,
            contentType:false,
            processData:false,
            success:function(data){
                closeLoading();
                //对于表单提交成功后处理，message为返回内容
//                var result = JSON.parse(data);
                var result = data;
                if(result.result == "OUTSUCCESS"){
                    if(window.confirm('存在导入失败内容，是否下载查看详细原因')){
                        location.href= "<%=path%>/file/downloadTemp.do?fileName="+result.errorMsg;
                    }
                }else{
                    alert(result.errorMsg);
                    $('#myModal').modal('hide');
                    $('#myModalInvoice').modal('hide');
                    $('#myModalExpress').modal('hide');
                    $('#myModalInvoiceExpress').modal('hide');
                    $(".viewfile").val('');
                }
            },
            error:function (request) {
                closeLoading();
                alert("Connection error:"+request.error);
            }
        })
    }



</script>
</body>
</html>
