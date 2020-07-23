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
    <%--<link type="text/css" href="<%=path%>/js/themes/jquery-ui.min.css"--%>
          <%--rel="stylesheet" />--%>

    <%--<script type="text/javascript" src="<%=path%>/js/jquery-1.7.2.min.js"></script>--%>
    <%--<script type="text/javascript" src="<%=path%>/js/bootstrap.min.js"></script>--%>
    <%-- 		<script type="text/javascript" src="<%=path%>/js/validate/jquery.validate.js"></script>
            <script type="text/javascript" src="<%=path%>/js/validate/additional-methods.js"></script>
            <script type="text/javascript" src="<%=path%>/js/validate/messages_zh.js"></script> --%>
    <%--<script type="text/javascript" src="<%=path%>/js/validate.js"></script>--%>
    <style type="text/css">
        .neir_2_kcgl_bod2 {
            background-color: white;
        }
        .red_label{
            color: red;
        }
        dt{
            text-align: right;
        }
    </style>
    <script type="text/javascript">



        function postAddress(){
            if(isNULL("#purchaseUser")||isNULL("#unitName")||isNULL("#address")||isNULL("#facsimile")||isNULL("#telphone")||isNULL("#zipCode")||isNULL("#gamail")||isNULL("#province")||isNULL("#city")){
                alert("请填写必填项");
                return false;
            }

            $.ajax({
                url: "<%=path%>/commonAddressInfo/postAddress.do",
                type: "POST",
                cache: false,
                data: $("#addForm").serialize(),
                success: function(data){
                    choose(data);
                }
            });

        }

    </script>
</head>
<body class=" ">
<div align="center">
    <div class="content" style="margin: 0 auto;width: 950px;">
        <input type="hidden" id="addressType" value="${addressType}" />
        <form class="form-inline" id="addressForm" action="" method="post" accept-charset="UTF-8">
            <input type="hidden" id="id" name="id" value="${address.id}" />
            <div class="tdiv_top"></div>

            <div class="tdiv_a_z ">
                <div class="tdiv_a_1 tdiv_a_left" >收货单位名称<span class="red_label">★</span>：</div>
                <div class="tdiv_a_3">
                    <input name="receiveUnitName" id="receiveUnitName" value="${address.receiveUnitName}" class="form-control"  onblur="javascript:ok_or_error(this)" type="text" >
                    <i class="glyphicon glyphicon-ok" style="color: #5cb85c;display: none;"></i>
                    <i class="glyphicon glyphicon-remove" style="color: red;display: none;"></i>
                </div>
                <div class="tdiv_a_1" >收货地址<span class="red_label">★</span>：</div>
                <div class="tdiv_a_3">
                    <input name="receiveUnitAddress" id="receiveUnitAddress" value="${address.receiveUnitAddress}" class="form-control"  onblur="javascript:ok_or_error(this)" type="text" >
                    <i class="glyphicon glyphicon-ok" style="color: #5cb85c;display: none;"></i>
                    <i class="glyphicon glyphicon-remove" style="color: red;display: none;"></i>
                </div>
            </div>
            <div class="tdiv_a_z ">
                <div class="tdiv_a_1 tdiv_a_left" >收货联系人<span class="red_label">★</span>：</div>
                <div class="tdiv_a_3">
                    <input name="receiveName" id="receiveName" value="${address.receiveName}" class="form-control"  onblur="javascript:ok_or_error(this)" type="text" >
                    <i class="glyphicon glyphicon-ok" style="color: #5cb85c;display: none;"></i>
                    <i class="glyphicon glyphicon-remove" style="color: red;display: none;"></i>
                </div>
                <div class="tdiv_a_1" >手机号码<span class="red_label">★</span>：</div>
                <div class="tdiv_a_3">
                    <input name="receiveMobile" id="receiveMobile" value="${address.receiveMobile}" class="form-control"  onblur="javascript:ok_or_error(this)" type="text" >
                    <i class="glyphicon glyphicon-ok" style="color: #5cb85c;display: none;"></i>
                    <i class="glyphicon glyphicon-remove" style="color: red;display: none;"></i>
                </div>
            </div>
            <div class="tdiv_a_z ">
                <div class="tdiv_a_1 tdiv_a_left" >固定电话：</div>
                <div class="tdiv_a_3">
                    <input name="receivePhone" id="receivePhone" value="${address.receivePhone}" class="form-control"  onblur="javascript:ok_or_error(this)" type="text" >
                </div>
                <div class="tdiv_a_1" >备选联系人：</div>
                <div class="tdiv_a_3">
                    <input name="standbyName" id="standbyName" value="${address.standbyName}" class="form-control"  onblur="javascript:ok_or_error(this)" type="text" >
                </div>
            </div>
            <div class="tdiv_a_z ">
                <div class="tdiv_a_1 tdiv_a_left" >手机号码：</div>
                <div class="tdiv_a_3">
                    <input name="standbyMobile" id="standbyMobile" value="${address.standbyMobile}" class="form-control"  onblur="javascript:ok_or_error(this)" type="text" >
                </div>
                <div class="tdiv_a_1" >固定电话：</div>
                <div class="tdiv_a_3">
                    <input name="standbyPhone" id="standbyPhone" value="${address.standbyPhone}" class="form-control"  onblur="javascript:ok_or_error(this)" type="text" >
                </div>
            </div>
        </form>
        <div class="clearfloat"></div>
        <hr class="hr_style"/>

        <div class="seqx_gly">
        <%--<a class="btn btn-default" role="button" href="/360workshop/admin/newCourse/news">重新选择课程类型</a>--%>
        <c:choose>
        <c:when test="${address.id!=null}">
            <button type="button" class="btn btn-info btn-lg" onclick="_edit()" >确定</button>
        <%--<a class="btn btn-danger" id="next" type="button" onclick="_edit()">确定</a>--%>
        </c:when>
        <c:otherwise>
            <button type="button" class="btn btn-info btn-lg" onclick="_chlick()" >保存</button>
        </c:otherwise>
        </c:choose>
        </div>

        <%--<div class="down_area neir_2_kcgl_bod2">--%>
            <%--<input type="hidden" id="addressType" value="${addressType}" />--%>

            <%--<div class="neir_2">--%>
                <%--<form class="form-inline" id="addressForm" action="" method="post" accept-charset="UTF-8">--%>
                    <%--<input type="hidden" id="id" name="id" value="${address.id}" />--%>
                    <%--<div id="first">--%>
                        <%--<div class="neir_1">--%>

                            <%--<dl class="neir_xjpc_dl">--%>
                                <%--<dt>收货单位名称<span class="red_label">★</span></dt>--%>
                                <%--<dd>--%>
                                    <%--<input name="receiveUnitName" id="receiveUnitName" value="${address.receiveUnitName}" class="form-control"  onblur="javascript:ok_or_error(this)" type="text" >--%>
                                    <%--<i class="glyphicon glyphicon-ok" style="color: #5cb85c;display: none;"></i>--%>
                                    <%--<i class="glyphicon glyphicon-remove" style="color: red;display: none;"></i>--%>
                                <%--</dd>--%>
                                <%--<dt>收货地址<span class="red_label">★</span></dt>--%>
                                <%--<dd>--%>
                                    <%--<input name="receiveUnitAddress" id="receiveUnitAddress" value="${address.receiveUnitAddress}" class="form-control"  onblur="javascript:ok_or_error(this)" type="text" >--%>
                                    <%--<i class="glyphicon glyphicon-ok" style="color: #5cb85c;display: none;"></i>--%>
                                    <%--<i class="glyphicon glyphicon-remove" style="color: red;display: none;"></i>--%>
                                <%--</dd>--%>
                                <%--<dt>收货联系人<span class="red_label">★</span></dt>--%>
                                <%--<dd>--%>
                                    <%--<input name="receiveName" id="receiveName" value="${address.receiveName}" class="form-control"  onblur="javascript:ok_or_error(this)" type="text" >--%>
                                    <%--<i class="glyphicon glyphicon-ok" style="color: #5cb85c;display: none;"></i>--%>
                                    <%--<i class="glyphicon glyphicon-remove" style="color: red;display: none;"></i>--%>
                                <%--</dd>--%>
                                <%--<dt>手机号码<span class="red_label">★</span></dt>--%>
                                <%--<dd>--%>
                                    <%--<input name="receiveMobile" id="receiveMobile" value="${address.receiveMobile}" class="form-control"  onblur="javascript:ok_or_error(this)" type="text" >--%>
                                    <%--<i class="glyphicon glyphicon-ok" style="color: #5cb85c;display: none;"></i>--%>
                                    <%--<i class="glyphicon glyphicon-remove" style="color: red;display: none;"></i>--%>
                                <%--</dd>--%>
                                <%--<dt>固定电话</dt>--%>
                                <%--<dd>--%>
                                    <%--<input name="receivePhone" id="receivePhone" value="${address.receivePhone}" class="form-control"  onblur="javascript:ok_or_error(this)" type="text" >--%>
                                <%--</dd>--%>
                                <%--<dt>备选联系人</dt>--%>
                                <%--<dd>--%>
                                    <%--<input name="standbyName" id="standbyName" value="${address.standbyName}" class="form-control"  onblur="javascript:ok_or_error(this)" type="text" >--%>
                                <%--</dd>--%>
                                <%--<dt>手机号码</dt>--%>
                                <%--<dd>--%>
                                    <%--<input name="standbyMobile" id="standbyMobile" value="${address.standbyMobile}" class="form-control"  onblur="javascript:ok_or_error(this)" type="text" >--%>
                                <%--</dd>--%>
                                <%--<dt>固定电话</dt>--%>
                                <%--<dd>--%>
                                    <%--<input name="standbyPhone" id="standbyPhone" value="${address.standbyPhone}" class="form-control"  onblur="javascript:ok_or_error(this)" type="text" >--%>
                                <%--</dd>--%>
                                <%--&lt;%&ndash;<dt>是否设为默认地址<span class="red_label">★</span></dt>&ndash;%&gt;--%>
                                <%--&lt;%&ndash;<dd>&ndash;%&gt;--%>
                                    <%--&lt;%&ndash;<c:choose>&ndash;%&gt;--%>
                                        <%--&lt;%&ndash;<c:when test="${address.isDefault==0}">&ndash;%&gt;--%>
                                            <%--&lt;%&ndash;<input name="isDefault" class="isDefault"  value="1" style="width: 70px;" type="radio" value="1" />是&ndash;%&gt;--%>
                                            <%--&lt;%&ndash;<input name="isDefault" class="isDefault"  value="0" style="width: 70px;" type="radio" value="0" checked/>否&ndash;%&gt;--%>
                                        <%--&lt;%&ndash;</c:when>&ndash;%&gt;--%>
                                        <%--&lt;%&ndash;<c:otherwise>&ndash;%&gt;--%>
                                            <%--&lt;%&ndash;<input name="isDefault" class="isDefault"  value="1" style="width: 70px;" type="radio" value="1" checked/>是&ndash;%&gt;--%>
                                            <%--&lt;%&ndash;<input name="isDefault" class="isDefault"  value="0" style="width: 70px;" type="radio" value="0" />否&ndash;%&gt;--%>
                                        <%--&lt;%&ndash;</c:otherwise>&ndash;%&gt;--%>
                                    <%--&lt;%&ndash;</c:choose>&ndash;%&gt;--%>
                                <%--&lt;%&ndash;</dd>&ndash;%&gt;--%>


                            <%--<div class="seqx_gly">--%>
                                <%--&lt;%&ndash;<a class="btn btn-default" role="button" href="/360workshop/admin/newCourse/news">重新选择课程类型</a>&ndash;%&gt;--%>
                                <%--<c:choose>--%>
                                    <%--<c:when test="${address.id!=null}">--%>
                                        <%--<a class="btn btn-danger" id="next" type="button" onclick="_edit()">确定</a>--%>
                                    <%--</c:when>--%>
                                    <%--<c:otherwise>--%>
                                        <%--<a class="btn btn-danger" id="next" type="button" onclick="_chlick()">保存</a>--%>
                                    <%--</c:otherwise>--%>
                                <%--</c:choose>--%>
                            <%--</div>--%>
                            <%--</dl>--%>
                        <%--</div>--%>
                    <%--</div>--%>
                <%--</form>--%>
            <%--</div>--%>
        <%--</div>--%>
    </div>
</div>
<script type="text/javascript">
    $(function () {
        var orderUnitName = "${orderUnitName}";
        if(orderUnitName!=""&&orderUnitName!=undefined){       //默认单位名称
            $("#receiveUnitName").val(orderUnitName);
        }
    })

    function _chlick() {
        if($("#purchaser").val()==""||$("#receiveUnitName").val()==""
                ||$("#receiveUnitAddress").val()==""||$("#receiveName").val()==""||$("#receiveMobile").val()==""){
            alert("必填字段不能为空");
            return;
        }else if(!validatorIsTelephone($("#receiveMobile").val())){
            alert("收货联系人手机号码格式错误");
            return;
        }
        openLoading();
        $.ajax({
            url: "<%=path%>/commonUserAddress/postAddress.do",
            type: "POST",
            data: $("#addressForm").serialize(),
            success: function(data){
                closeLoading();
                alert(data);
//                changeDaoHang("收货地址管理")
                window.location.href='<%=path%>/commonUserAddress/showAddressInfo.do';
            },error : function(XMLHttpRequest, textStatus, errorThrown) {
                alert("加入失败" + errorThrown);
                closeLoading();
            }
        });

    }

    function _edit() {
        if($("#purchaser").val()==""||$("#receiveUnitName").val()==""
                ||$("#receiveUnitAddress").val()==""||$("#receiveName").val()==""||$("#receiveMobile").val()==""){
            alert("必填字段不能为空");
            return;
        }else if(!validatorIsTelephone($("#receiveMobile").val())){
            alert("收货联系人手机号码格式错误");
            return;
        }
        openLoading();
        $.ajax({
            url: "<%=path%>/commonUserAddress/putAddress.do",
            type: "POST",
            data: $("#addressForm").serialize(),
            success: function(data){
                closeLoading();
                alert(data);
//                changeDaoHang("收货地址管理")
                window.location.href='<%=path%>/commonUserAddress/showAddressInfo.do';
            },error : function(XMLHttpRequest, textStatus, errorThrown) {
                alert("加入失败" + errorThrown);
                closeLoading();
            }
        });

    }
</script>
</body>
</html>