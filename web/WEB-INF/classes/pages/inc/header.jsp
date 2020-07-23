<%@ page pageEncoding="UTF-8" import="cn.com.common.utils.DateUtils"%>
<%
	String path = request.getContextPath();
	String serverName = request.getServerName();
	String httpsPort = "8443";
	String nowDate = DateUtils.getCurrentDate();
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>


<script type="text/javascript" src="<c:url value="/js/jquery-1.10.2.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/bootstrap.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/common.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/example.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/layer.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/validate.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/site.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/datepicker/WdatePicker.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/jquery-form.js"/>"></script>


<style type="text/css">
	/* service-menu +- 图标 */
	.menu-i{background:url("../../images/menubg.png") no-repeat;}
	.menu-c{background:url("../../images/menubg.png") no-repeat;}
	.menu-i{float:left;display:inline;width:11px;height:11px;overflow:hidden;background-position:-20px -103px;margin:9.0px 0px 0px 7px;}
	.menu-c{float:left;display:inline;width:11px;height:11px;overflow:hidden;background-position:0px -103px;margin:9.0px 0px 0px 7px;}
	.tdiv_a_z{
		/*固定长度不随浏览器放大缩小拖动*/
		/*width: 1440px!important;*/
	}
	/*合并单元格垂直居中*/
	.merge{
		vertical-align: middle!important;
	}
	a:hover{text-decoration: underline;}
	/*p.unit_select,p.unit:hover{text-decoration: underline;}*/
	div.left_l>p:hover{text-decoration: underline;}
	input[type='button']{text-decoration: none;}
	input[type='button']:hover{text-decoration: underline;}
	.selectText{
		width: 85px;
		padding: 0px 0px;
		height: 24px;
		float: left;
		margin-right: 12px;
	}
</style>



