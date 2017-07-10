<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>



<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'welcome.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
 
    <p>Hello <%= request.getAttribute("username") %>, this is welcome page.</p>
    <!-- <p><%= request.getAttribute("isRegister") %></p> -->
    <%= (String)request.getSession().getAttribute("isRegister") %>
     <%= (String)request.getSession().getAttribute("username") %>
    <a href="logout.action">Log out</a>
    <a href="WxPaccountService.action">wx_account</a>
    <a href="baseModulesPage.action">baseModules Page</a>
    <a href="baseDevGroupPage.action">baseDevGroup page</a>
    <a href="baseDevTypePage.action">devType page</a>
    <a href="">user info</a>
    <a href="">user power</a>
    <a href="baseFeaturePage.action">feature page</a>
  </body>
</html>
