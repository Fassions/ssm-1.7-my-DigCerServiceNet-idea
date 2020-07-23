<%--
  Created by IntelliJ IDEA.
  User: horace_zhang
  Date: 2019/10/24
  Time: 16:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%--<%@ include file="../inc/header.jsp" %>--%>
<%--<%@ include file="../init.jsp" %>--%>
<%--<link type="text/css" href="/css/home.css" rel="stylesheet" />--%>
<%--<link type="text/css" href="/css/jquery.bxslider.css" rel="stylesheet" />--%>
<%--<script type="text/javascript" src="/js/jquery.bxslider.js"></script>--%>
<%--<link type="text/css" href="<%=path%>/css/top.css" rel="stylesheet"/>--%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="utf-8">
    <script type="text/javascript" src="/js/jquery-1.9.1.min.js"></script>
    <script type="text/javascript" src="/js/jquery.bxslider.js"></script>
    <script type="text/javascript" src="/js/example.js"></script>
    <script type="text/javascript" src="/js/layer.js"></script>
    <link type="text/css" href="/css/jquery.bxslider.css" rel="stylesheet" />
    <link type="text/css" href="/css/home.css" rel="stylesheet" />
    <%--<link type="text/css" href="/css/main.css" rel="stylesheet" />--%>
    <%--<link type="text/css" href="/css/top.css" rel="stylesheet"/>--%>
    <%--<link type="text/css" href="/css/main.css" rel="stylesheet"/>--%>
    <%--<link type="text/css" href="/css/bootstrap.min.css" rel="stylesheet" />--%>


    <title>证书服务网首页</title>
    <style>

        html,body{
            height: 100%;
            width: 100%;
            margin: 0;
            padding: 0;
            position: relative;
            background-color: #242424;
        }
        .body{
            position:relative ;
            padding-bottom: 35px;
            height: 70%;
            min-width: 1080px;
        }
        .body_footer{
            position:relative ;
            /*padding-bottom: 35px;*/
            height: 30%;
            min-width: 1080px;
        }
        .img_footer{
            position: absolute;
            /*top: 0;*/
            /*left:0;*/
            width : 100%;
            height: 100%;
            background-image: url("/images/home/bg1.png");
            background-repeat: no-repeat;
            /*background-attachment: fixed;*/
            /*background-attachment: fixed;*/
            background-size: 100% 100%;
            /*background-position: center;*/
            /*-o-background-size: cover;*/
            /*zoom: 1;*/
            z-index: 0;
            filter: progid:DXImageTransform.Microsoft.AlphaImageLoader(src='/images/home/home_background.png',sizingMethod=scale);
        }
        .img{
            position: absolute;
            /*top: 0;*/
            /*left:0;*/
            width : 100%;
            height: 100%;
            background-image: url("/images/home/home_background.png");
            background-repeat: no-repeat;
            /*background-attachment: fixed;*/
            /*background-attachment: fixed;*/
            background-size: 100% 100%;
            /*background-position: center;*/
            /*-o-background-size: cover;*/
            /*zoom: 1;*/
            z-index: 0;
            filter: progid:DXImageTransform.Microsoft.AlphaImageLoader(src='/images/home/home_background.png',sizingMethod=scale);
        }



        .wrapper{
            margin-top: 7%;
            position:relative ;
            font-size: 11px;
        }
        .container{
            /*max-width:1200px;*/
            max-width:1700px;
            margin: 0 auto;
            position: relative;
        }
        .home_slide_title{
            float: right;
        }
        .home_slide_btn{
            margin-top: 17%;
            margin-left: 69%;
        }
        .trapezium{
            height: 160px;
            width: 100px;
            background: #8734f7;
            -webkit-transform: skew(30deg);
            -moz-transform: skew(30deg);
            -o-transform: skew(30deg);
            transform: skew(30deg);
            /*border-bottom: 120px solid #ec3504;*/
            /*border-left: 60px solid transparent;*/
            /*border-right: 60px solid transparent;*/
        }
        .footer{
            /*background: #242424;*/
            /*padding:20px 0;*/
            min-width: 1080px;
        }
        .footer .footer_bottom{
            /*max-width:1080px; !*集中*!*/
            margin: 0 auto;
            position: relative;
        }
        .footer_bottom p{
            text-align: center;
            color: #aaa;
            line-height: 30px;
            font-size: 12px;
        }
        div{
            margin: 0;
            padding: 0;
            outline: none;
        }

        .btn-info{
            color: #fff;
            -webkit-border-radius: 20px;
            -moz-border-radius: 20px;
            border-radius: 20px;
            background-color: #599efd;
        }
        .btn-info:hover{
            color: #fff;
            background-color: #31b0d5;
            border-color: #269abc;
        }
        .unit_content{
            width: 105px;
            overflow: hidden;
            white-space: nowrap;
            text-overflow: ellipsis;
        }
        .neir_1_kcgl { padding: 16px; margin-bottom: 20px !important;}

    </style>
    <script type="text/javascript">


        //        $(function () {
        //            layer.msg('加载中', {
        //                time: 0,
        //                icon: 0,
        //                shade: [1, '#000']
        //            });
        //        })
        if(top.location != self.location){
            top.location = self.location;
        }
        function mainPage(){
            top.location.attr("src","home.do");
            changeDaoHang();
        }

        function about(){
            openLoading();
            top.location.attr("src","/abt.do");
            changeDaoHang('关于系统');
        }

        function wclose() {
            if(confirm("确认退出系统？")){
//                window.open();
                openLoading();
                location.href = "/logOut.do";
                <%--window.document.("<%=path%>/logOut.do");--%>
                <%--location.href("<%=path%>/logOut.do");--%>
            }
        }
        //    parent.bot.location.href="/home";


        <%--function entry() {--%>
        <%--/* if(CaluNumCtrl.GetCerKeyStatus(0)!=0){--%>
        <%--alert("未插入数字证书");--%>
        <%--return;--%>
        <%--} */--%>
        <%--$("#loginP").css("background-image", "url(images/login/button_check.png)");--%>
        <%--location.href="https://<%=serverName%>:<%=httpsPort%><%=path%>/login.do";--%>
        <%--}--%>
    </script>
</head>
<body>
<div class="body">
    <%--蒙版--%>
    <div id="make_keep" style="width: 100%;height: 200%;background-color: white;z-index: 99999;position: absolute;float: left;" ></div>

    <div class="img" ></div>
    <%--<img class="back_img" src="/images/home/home_background.png">--%>
    <div class="nav">
        <div class="div_1"><img src="/images/top/topLogo.png"/></div>
        <div class="div_2"><p style="font-size: 28px">数字证书服务网</p></div>
        <div class="div_3">
            欢迎回来：${userName}
        </div>
        <div class="div_4">
            <a onclick="javascript:mainPage()" title="主页" ><img src="/images/top/icon_home.png"/></a>
            <%--<a onclick="javascript:about()" title="技术支持"><img src="<%=path%>/images/top/icon_about.png"/></a>--%>
            <a title="退出" onclick="wclose()"><img src="/images/top/icon_exit.png"/></a>
        </div>
    </div>
    <div class="clearfloat"></div>
    <div class="wrapper ">
        <div class="container">
            <div class="slider2">
                <c:forEach begin="1" end="4">
                <div class="slide" >
                    <div style="">
                        <div class="" style="margin-left: 5%">
                            <div class="home_slide _neir">
                                <div class="home_slide_image_picture">
                                    <img src="/images/home/DC.png"/>
                                </div>
                                <div class="home_slide_title">公安数字证书</div>
                                <%--<div class="home_slide_context">产品介绍xx</div>--%>
                                <%--<p></p>--%>
                                <div class="home_slide_btn">
                                    <input type="button" class="btn btn-info" onclick="_hrefOrderProcess(1)" value="前往选购"/>
                                </div>
                            </div>
                            <div class="home_slide _neir">
                                <div class="home_slide_image_picture">
                                    <img src="/images/home/FJ.png"/>
                                </div>
                                <div class="home_slide_title">警辅数字证书</div>
                                <%--<div class="home_slide_context">产品介绍xx</div>--%>
                                <%--<p></p>--%>
                                <div class="home_slide_btn">
                                    <input type="button" class="btn btn-info" onclick="_hrefOrderProcess(2)" value="前往选购"/>
                                </div>
                            </div>
                            <div class="home_slide _neir">
                                <div class="home_slide_image"></div>
                                <div class="home_slide_title">公安、警辅</div>
                                <div class="home_slide_title">数字证书解锁卡</div>
                                <%--<div class="home_slide_context">产品介绍xx</div>--%>
                                <%--<p></p>--%>
                                <div class="home_slide_btn">
                                    <input type="button" class="btn btn-info" onclick="_hrefOrderProcess(3)" value="前往选购"/>
                                </div>
                            </div>
                            <div class="home_slide _neir">
                                <div class="home_slide_image_picture">
                                    <img src="/images/home/GAZW.png"/>
                                </div>
                                <div class="home_slide_title">公安指纹数字证书</div>
                                <%--<div class="home_slide_context">产品介绍xx</div>--%>
                                <%--<p></p>--%>
                                <div class="home_slide_btn">
                                    <input type="button" class="btn btn-info" onclick="_hrefOrderProcess(4)" value="前往选购"/>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="" style="margin-left: 5%">
                        <div class="home_slide _neir">
                            <div class="home_slide_image"></div>
                            <div class="home_slide_title">警辅指纹数字证书</div>
                            <%--<div class="home_slide_context">产品介绍xx</div>--%>
                            <%--<p></p>--%>
                            <div class="home_slide_btn">
                                <input type="button" class="btn btn-info" onclick="_hrefOrderProcess(5)" value="前往选购"/>
                            </div>
                        </div>
                        <div class="home_slide _neir">
                            <div class="home_slide_image_picture">
                                <img src="/images/home/ZW.png"/>
                            </div>
                            <div class="home_slide_title">专网数字证书</div>
                            <%--<div class="home_slide_context">产品介绍xx</div>--%>
                            <%--<p></p>--%>
                            <div class="home_slide_btn">
                                <input type="button" class="btn btn-info" onclick="_hrefOrderProcess(6)" value="前往选购"/>
                            </div>
                        </div>
                        <div class="home_slide _neir">
                            <div class="home_slide_image"></div>
                            <div class="home_slide_title">延长线</div>
                            <%--<div class="home_slide_context">产品介绍xx</div>--%>
                            <%--<p></p>--%>
                            <div class="home_slide_btn">
                                <input type="button" class="btn btn-info" onclick="_hrefOrderProcess(0.1)" value="前往选购"/>
                            </div>
                        </div>
                        <div class="home_slide _neir">
                            <div class="home_slide_image"></div>
                            <div class="home_slide_title">吊带</div>
                                <%--<div class="home_slide_context">产品介绍xx</div>--%>
                                <%--<p></p>--%>
                            <div class="home_slide_btn">
                                <input type="button" class="btn btn-info" onclick="_hrefOrderProcess(0.2)" value="前往选购"/>
                            </div>
                        </div>
                    </div>
                </div>
                </c:forEach>
            </div>
        </div>
    </div>
</div>
<div class = "footer body_footer">
    <div class="footer_bottom img_footer">
        <%--<p>--%>
        <div class="home_bottom">
            <div  class="bottom_div">
                <div class="home_bottom_title" >
                    <strong style="color: #5a9ffe;font-size: 18px">证书相关</strong>
                </div>
                <div class="home_bottom_content">
                    <div class="click_style" title="证书订购">
                        <div class="home_left_tubiao dinggou"></div>
                        <div class="unit" id="dinggou">证书订购</div>
                    </div>
                    <div class="home_bottom_hr"></div>
                    <div class="click_style" title="订单查询">
                        <div class="home_left_tubiao chaxun"></div>
                        <div class="unit" id="chaxun">订单查询</div>
                    </div>
                    <div class="home_bottom_hr"></div>
                    <div class="click_style" title="收货地址管理">
                        <div class="home_left_tubiao dizhi" ></div>
                        <div class="unit" id="dizhi">收货地址管理</div>
                    </div>
                </div>
            </div >
            <div class="bottom_div" >
                <div class="home_bottom_title" >
                    <strong style="color: #5a9ffe;font-size: 18px">相关下载</strong>
                </div>
                <div class="home_bottom_content">
                    <c:forEach items="${tblHomes}" var="tblHomes">
                        <%--<div class="tdiv_bottom_2_home">--%>
                        <div class="tdiv_a_4 click_style" title="${tblHomes.name}"  onclick="_downLoadHomeFile('${tblHomes.fileName}')">
                            <div class="home_left_tubiao ${tblHomes.fileLabel}"></div>
                            <div class="unit unit_content">${tblHomes.name}</div>
                        </div>
                        <div class="home_bottom_hr"></div>
                        <%--</div>--%>
                    </c:forEach>
                </div>
            </div>
        </div>
        <%--</p>--%>
        <%--<p></p>--%>


    </div>
</div>
</div>



<script type="text/javascript">


    $(document).ready(function(){

        $('.slider2').bxSlider({


//            mode:'vertical',
//            slideWidth: 1700,
            slideWidth: 1300,
//            adaptiveHeight: true,
//            adaptiveHeightSpeed: 5000,
            auto: true,
            adaptiveHeight: true,
            startSlides: 0,
            slideMargin: 10
////            拉伸最小宽度
//            slideWidth: 1440,
//            auto: true,
//            autoControls: true,
//            minSlides: 1,
//            maxSlides: 1,
//            slideMargin: 10,
//            adaptiveHeight: true
        });
        $("#make_keep").hide();
//        closeLoading();
    });
    function _downLoadHomeFile(fileName) {
        openLoading();
        location.href= "/file/homeDownload.do?fileName="+fileName;
        closeLoading();
    }
    //选购产品跳转订购页面
    function _hrefOrderProcess(type) {
        openLoading();
        location.href="/commonUser/commonMain.do?url=/commonUserOrder/showOrderProcess.do?product="+type;
    }

    //证书订购
    $("#dinggou").bind("click",function() {
        openLoading();
        location.href= "/commonUser/commonMain.do?url=/commonUserOrder/showOrderProcess.do";
    });
    //证书查询
    $("#chaxun").bind("click",function() {
        openLoading();
        location.href= "/commonUser/commonMain.do?url=/commonUserOrderQuery/getOrderList.do";
    });
    //收货地址管理
    $("#dizhi").bind("click",function() {
        openLoading();
        location.href= "/commonUser/commonMain.do?url=/commonUserAddress/showAddressInfo.do";
    });
</script>


</body>

</html>
