<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'baseModules.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	
    <link rel="stylesheet" type="text/css" href="public/css/edtstyle.css">
	<link rel="stylesheet" type="text/css" href="public/css/easyui.css">
	<link rel="stylesheet" type="text/css" href="public/css/icon.css">

	<script type="text/javascript" src="public/jquery/jquery.min.js"></script>
	<script type="text/javascript" src="public/jquery/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="public/jquery/easyui-lang-zh_CN.js"></script>
	
	<!-- 上面需要添加jquery函数库 才能起作用 -->
</head>
  
  <body>
    <table id="tg" height="100%" width="100%">
	<thead>
		<tr>

		</tr>
	</thead>
</table>

<div id="toolbar">
	<a href="javascript:void(0)" class="easyui-linkbutton easyui-tooltip" iconCls="icon-add" plain="true"    onclick="btn_new()" title="新建一条记录" position="right">新建</a>
	<a href="javascript:void(0)" class="easyui-linkbutton easyui-tooltip" iconCls="icon-edit" plain="true"   onclick="btn_mod()" title="编辑现有记录" position="right">编辑</a>
	<a href="javascript:void(0)" class="easyui-linkbutton easyui-tooltip" iconCls="icon-cancel" plain="true" onclick="btn_del()" title="删除一条记录" position="right">删除</a>
</div>

<div id="dlg" class="easyui-dialog" style="width:400px;height:300px;padding:10px 20px"
	closed="true" buttons="#dlg-buttons" resizable="true" modal="true">
	<div class="ftitle">设备扩展功能信息</div>

	<form id="fm" method="post" novalidate>
		<input type="hidden" id="fid"  name="fid">
		
		<div class="fitem">
			<label>扩展功能名称：</label>
			<input name="modulename" class="easyui-validatebox" required="true">
		</div>

		<div class="fitem">
			<label>扩展功能描述</label>
			<input id="modulememo" name="modulememo" class="easyui-validatebox" required="true">
		</div>
	</form>

</div>

<div id="dlg-buttons">
	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="btn_save()">保存</a>
	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')">放弃</a>
</div>

<script type="text/javascript">

var url='./switchModules';
function btn_new(){
	//alert($('#fm'));
	$('#dlg').dialog('open').dialog('setTitle','新建');
	$('#fm').form('clear');
	url='./switchModules?funct=add';
}

function btn_mod(){
	var row = $('#tg').datagrid('getSelected');
	if (row){
		$('#dlg').dialog('open').dialog('setTitle','编辑');
		$('#fm').form('load',row);
		url='./switchModules?funct=mod';
	}
}

function btn_save(){
	//alert($('#fid').val())
	//alert("appid = "+$("#appid").val());
	$('#fm').form('submit',{
		url: url,
		onSubmit: function(){
			return $(this).form('validate');
		},
		success: function(result){
			var result = eval('('+result+')');
			if (result.errorMsg){
				$.messager.show({
				title: 'Error',
				msg: result.errorMsg
				});
			} else {
				$('#dlg').dialog('close'); // close the dialog
				$('#tg').datagrid('reload'); // reload the user data
			}
		}
	});
}

function btn_del(){
	//alert(url);
	//url='./switchModules?funct=del';
	var row = $('#tg').datagrid('getSelected');
	if(!row) return;

	if(row.fid ==1 || row.modulename=='system'){
		$.messager.show({title: '提示',height:150,width:250,
			msg:'系统初始化数据，不能编辑或删除。'});
		return;
	}

	$.messager.confirm('确认', '您确认要删除此记录："'+row.modulememo+'"?',function(r){
		if (r){
			$.post('./switchModules?funct=del',  {fid: row.fid}, function(result){
			if (result.success){
				$('#tg').datagrid('reload'); // reload data
			} else {
				$.messager.show({ // show error message
					title: '错误',
					height:150,
					width:250,
					msg: result.errorMsg
					});
				}
			},'json');
		}
	});
}

//$(document).ready(function() {
$(function(){
	//datagrid
	url=url+'?funct=showPage';
	//alert(url);
    $('#tg').datagrid({
		iconCls: 'icon-ok',
		maximized:true,
		striped:true,
		rownumbers: true,
		fitColumns: false,
		idField:'fid',
		singleSelect:true,
		url: url,
		toolbar: "#toolbar",
		title: "设备扩展应用功能列表",
		columns:[[
			{field:"fid",title:'操作',width:60,align:'center',formatter:function(v,r,i){
				var rst='';
				pw=255;
				//alert(pw);
				//alert(pw & (1<<2));
				
				//if( (pw & (1<<2)) >0 )
					rst += '<span class="opt_spn" onclick="javascript:btn_mod('+i+');"><img src="./public/image/pencil.png" /></span>&nbsp;';
				//if( (pw & (1<<1)) >0 )
					rst +='<span class="opt_spn" onclick="javascript:btn_del('+i+');"><img src="public/image/cancel.png" /></span>&nbsp;';
				return rst;
			}},
			{field:'modulename',title:'扩展功能名称',width:150,halign:'center'},
			{field:'modulememo',title:'扩展功能描述',width:250,halign:'center'}
		]]
    });
});

</script>
  </body>
</html>
