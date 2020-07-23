<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ include file="../../init.jsp" %>
<%@ include file="../../inc/header.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>数字证书服务网</title>
    <script type="text/javascript" src="<%=path%>/js/datepicker/WdatePicker.js"></script>
    <%@ include file="../../power.jsp" %>
    <style type="text/css">
        body{
            background-color: #e6e6e6;
        }
        .tdiv_a_3{
            min-width: 160px!important;
        }
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
                <p class="unit">报表下载</p>
            </div>
            <div>
                <div class="tdiv_a_z">
                    <div class="tdiv_a_6" style="margin-left: 15px">
                        <div class="upload_image1">
                            <input type="radio" name="exportRadio"  value="0">
                            <div class="upload_image2"></div>
                            <div class="upload_text">生产任务单</div>
                        </div>
                    </div>
                    <div class="tdiv_a_6" >
                        <div class="upload_image1">
                            <input type="radio" name="exportRadio" value="2">
                            <div class="upload_image2"></div>
                            <div class="upload_text">产品快递发运单</div>
                        </div>
                    </div>
                    <div class="tdiv_a_6" >
                        <div class="upload_image1">
                            <input type="radio" name="exportRadio" value="1">
                            <div class="upload_image2"></div>
                            <div class="upload_text">签收单</div>
                        </div>
                    </div>
                    <input type="button" class="btn btn-info btn-width btn-right"  value="下载" onclick="_showExportModel()">
                    <div class="clearfloat"></div>
                </div>
                <div class="clearfloat"></div>
                <div class="tdiv_bottom"></div>

            </div>
        </div>
        <%--生产任务单模态框--%>
        <div class="modal fade" id="productionExcelModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
            <div class="modal-dialog modal-lg" role="document">
                <div class="modal-content" style="width: 100%">
                    <div class="modal-header">
                        <button type="button" class="close invoiceDetailsClose" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
                        <h4 class="modal-title">下载生产任务单</h4>
                    </div>
                    <div class="modal-body">
                        <form id="productionForm">
                            <input type="hidden" name="fileType" value="0"/>
                            <div class="tdiv_a_z">
                                <div class="tdiv_a_1">请选择日期：</div>
                                <div class="tdiv_a_3">
                                    <input type="text"  class="weekDate checkText form-control col-md-10 col-xs-4 classData" name="startDate" onclick="WdatePicker({disabledDays:[0,2,3,4,5,6,7]})">
                                </div>
                                <div class="tdiv_a_3"><div class="tdiv_a_left left_tubiao warn"></div><strong>提醒：数据生成条件为订单状态是已到款待生产。</strong></div>
                            </div>
                            <div class="tdiv_a_z appendCheckBoxProductNames">

                            </div>
                        </form>
                        <div class="clearfloat"></div>
                    </div>

                    <div class="modal-footer">
                        <div class="tdiv_a_z">
                            <input type="button" class="btn btn-info btn-width btn-right" onclick="_exportExcel('0')" value="下载" />
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <%--快递发运单模态框--%>
        <div class="modal fade" id="expressModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
            <div class="modal-dialog modal-lg" role="document">
                <div class="modal-content" style="width: 100%">
                    <div class="modal-header">
                        <button type="button" class="close invoiceDetailsClose" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
                        <h4 class="modal-title">下载快递发运单</h4>
                    </div>
                    <div class="modal-body">
                        <form id="expressForm">
                            <input type="hidden" name="fileType" value="2"/>
                            <div class="tdiv_a_z">
                                <div class="tdiv_a_1">请选择日期：</div>
                                <div class="tdiv_a_3">
                                    <input type="text" class="weekDate checkText form-control col-md-10 col-xs-4 classData" name="startDate" onclick="WdatePicker({disabledDays:[0,2,3,4,5,6,7]})">
                                </div>
                                <div class="tdiv_a_3"><div class="tdiv_a_left left_tubiao warn"></div><strong>提醒：数据生成条件为订单状态是已到款待生产。</strong></div>
                            </div>
                        </form>
                        <div class="clearfloat"></div>
                    </div>

                    <div class="modal-footer">
                        <div class="tdiv_a_z">
                            <input type="button" class="btn btn-info btn-width btn-right" onclick="_exportExcel('2')" value="下载" />
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <%--签收单模态框--%>
        <div class="modal fade" id="receiptModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
            <div class="modal-dialog modal-lg" role="document">
                <div class="modal-content" style="width: 100%">
                    <div class="modal-header">
                        <button type="button" class="close invoiceDetailsClose" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
                        <h4 class="modal-title">下载签收单</h4>
                    </div>
                    <div class="modal-body">
                        <form id="receiptForm">
                            <input type="hidden" name="fileType" value="1"/>
                            <div class="tdiv_a_z">
                                <div class="tdiv_a_1">请选择日期：</div>
                                <div class="tdiv_a_3">
                                    <input type="text" class="weekDate checkText form-control col-md-10 col-xs-4 classData" name="startDate" onclick="WdatePicker({disabledDays:[0,2,3,4,5,6,7]})">
                                </div>
                                <div class="tdiv_a_3"><div class="tdiv_a_left left_tubiao warn"></div><strong>提醒：数据生成条件为订单状态是已到款待生产。</strong></div>
                            </div>
                            <div class="tdiv_a_z appendCheckBoxProductNames">

                            </div>
                        </form>
                        <div class="clearfloat"></div>
                    </div>

                    <div class="modal-footer">
                        <div class="tdiv_a_z">
                            <input type="button" class="btn btn-info btn-width btn-right" onclick="_exportExcel('1')" value="下载" />
                        </div>
                    </div>
                </div>
            </div>
        </div>
        </div>
    </div>
<script type="text/javascript">
    $(function(){
     $(".weekDate").val(week());
    })
    $(".tdiv_a_6").click(function () {
        $(this).find("input[type=radio]").prop("checked","checked");
    })

    //弹出模态框
    function _showExportModel(){
        $(".appendCheckBoxProductNames").append(appendCheckBoxProductNames($(".appendCheckBoxProductNames")));
        var modelType = $("input[name=exportRadio]:checked").val();
        if(modelType=="" || modelType == undefined){
            alert("请选择一个导出模板。");
            return false;
        }
        if(modelType=="0"){ //生产任务单
            $("#productionExcelModal").modal("show");
            return false;
        }else if(modelType=="1"){ //签收单
            $("#receiptModal").modal("show");
            return false;
        }else if(modelType=="2"){  //快递发运单
            $("#expressModal").modal("show");
            return false;
        }
    }
    //下载
    function _exportExcel(fileType){
        var href = ""; //数据下载
        var data = "";
        if(fileType==""){
            alert("请重新选择。");
            return false;
        }
        //生产任务单
        if(fileType=="0"){
            href = "<%=path%>/report/downLoadReportExcel.do?"+$("#productionForm").serialize();
            data = $("#productionForm").serialize();
        }else if(fileType=="1"){ //签收单
            href = "<%=path%>/report/downLoadReportExcel.do?"+$("#receiptForm").serialize();
            data = $("#receiptForm").serialize();
        }else if(fileType=="2"){   //快递发运单
            href = "<%=path%>/report/downLoadReportExcel.do?"+$("#expressForm").serialize();
            data = $("#expressForm").serialize();
        }
        openLoading();
        $.ajax({
            url: "<%=path%>/report/getOrderReport.do", //数据查询
            type: "POST",
            data: data,
            success: function(data){
                if(data.result=="SUCCESS"){
                    closeLoading();
                    //数据无误开始下载
                    location.href= href;
                }else{
                    closeLoading();
                    alert(data.errorMsg);
                }
            },error : function(XMLHttpRequest, textStatus, errorThrown) {
                alert("加入失败" + errorThrown);
                closeLoading();
            }
        })
    }




</script>
</body>
</html>