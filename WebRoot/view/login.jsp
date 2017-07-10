<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//ENww">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>Login page</title>
    
   <!--  <script src="http://code.jquery.com/jquery-latest.js"></script> -->
   <!-- <script type="text/javascript" src="jquery/jquery.min.js"></script> -->
    <script type="text/javascript" src="public/jquery/jquery.min.js"></script>
    <link rel="stylesheet" type="text/css" href="public/css/admin_login.css" />
	<link rel="Stylesheet" type="text/css" href="public/css/type.css" />
	
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="
	styles.css">
	-->
	
	
	
	<!-- which 是键的值 空格为13 tab为9 -->
	
	<script type="text/javascript">
		function onClickAjaxTest(){
			$("#username").attr('placeholder','用户名');
			$("#password").attr('placeholder','密码');
			
			//var Test=$("#username").attr("value");
			
			
			if($("#username").val()==''){
				$("#username").attr('placeholder','请输入正确的用户名');
				$("#username").focus();
				return false;
			}
			if($("#password").val()==''){
				$("#password").attr('placeholder','请输入正确的密码');
				alert("please enter your password");
				$('#password').focus();
				return false;
			}
		
			$.ajax({
				type:'GET',
				url:"./ajaxLogin",
				dataType:'json',
				data: $("#mainForm").serialize() + "&_=" + Math.random().toString(),
				success:function(data){
 					if(data.success != 'underfined'&& data.success == "1"){
							//var Test=$("#mianForm").attr("value");
 							//alert(data.success);
						var actionStr='login.action?username='+$("#username").val()+'&password='+$("#password").val();
 						document.location=actionStr;
 					}else{
 						alert(data.errMsg);
 					}
				},
				error:function(){
					alert("something is wrong in ajax =.=");
				},
			});
			return false;
		}
	</script>
	
	<!-- test -->
	
  </head>
  
  <body>
  	<div style="width:1000px;height:750px;margin:auto">

<div id="header">
	<div style="position:relative;top:0px;left:0px;width:1000;height:80px;background:url(public/image/head_rp1.jpg) repeat">
	</div>
	<div style="position:relative;top:-55px;left:10px;width:264px;height:30px;border:0px;background:url(public/image/logo1.png) no-repeat"></div>
	<div style="position:relative;top:-110px;left:275px;width:530px;height:80px;border:0px;background:url(public/image/top_bg1.jpg) no-repeat"></div>
</div>

<div id="bodygrp" style="width:1000px;height:600px;border:0px solid #f00;">

	<div id="bd_bg"></div>

	<form id="mainForm">
     <div id="bd_dlg">
    	<div style="position: relative;width:300px;height:35px;top:42px;left:75px; border: 0px solid #00f;">
			<input type="text" name="username" id="username" class="input-block input_elm" />
    	</div>

        <div style="position: relative;width:300px;height:35px;top:67px;left:75px; border: 0px solid #00f;">
			<input type="password" name="password" id="password" class="input-block input_elm"/>
        </div>

        <div style="position:relative;width:300px;height:35px;top:117px;left:75px;border:0px solid #00f;">
			<input type="button" value="立即登录" name="commit" class="button" onclick="onClickAjaxTest()"/>
        </div>
	</div>

	</form>
</div>

</div>
  </body>
</html>
