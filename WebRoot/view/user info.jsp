<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <link rel="stylesheet" type="text/css" href="public/css/edtstyle.css">
	<link rel="stylesheet" type="text/css" href="public/css/easyui.css">
	<link rel="stylesheet" type="text/css" href="public/css/icon.css">
	
	<script type="text/javascript" src="public/jquery/jquery.min.js"></script>
	<script type="text/javascript" src="public/jquery/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="public/jquery/easyui-lang-zh_CN.js"></script>
	    
    <title>user info</title>
    
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
    
<table id="tg" height="100%" width="100%"></table>

<div id="toolbar">

<% 
int pw=255;
if( (pw & (1<<0)) >0 ){ %>
	<a href="javascript:void(0)" class="easyui-linkbutton easyui-tooltip" iconCls="icon-add" plain="true"    onclick="btn_new()" title="新建一条记录" position="right">新建</a>
<% } %>

<% if( (pw & (1<<2)) >0 ){ %>
	<a href="javascript:void(0)" class="easyui-linkbutton easyui-tooltip" iconCls="icon-edit" plain="true"   onclick="btn_mod()" title="编辑现有记录" position="right">编辑</a>
<% } %>

<% if( (pw & (1<<3)) >0 ){ %>
	<a href="javascript:void(0)" class="easyui-linkbutton easyui-tooltip" iconCls="icon-usrpw" plain="true"   onclick="btn_power()" title="编辑人员权限" position="right">权限设置</a>
<% } %>

<% if( (pw & (1<<1)) >0 ){ %>
	<a href="javascript:void(0)" class="easyui-linkbutton easyui-tooltip" iconCls="icon-cancel" plain="true" onclick="btn_del()" title="删除一条记录" position="right">删除</a>
<% } %>
</div>

<div id="dlg" class="easyui-dialog" style="width:470px;height:380px;padding:10px 20px"
	closed="true" buttons="#dlg-buttons" resizable="true" modal="true">
	<div class="ftitle">用户信息</div>

	<form id="fm" method="post" novalidate>

		<input name="fid" id="fid" type="hidden">

		<div class="fitem">
			<label>设备群组:</label>
			<select name="devgrp_id" id="devgrp_id" class="easyui-combotree" required="true" style="width:260px"></select>
		</div>

		<div class="fitem">
			<label>用户名称:</label>
			<input name="fname" id="fname" class="easyui-validatebox" required="true" validType="length[5,50]" style="width:250px">
		</div>

		<div class="fitem">
			<label>用户密码:</label>
			<input name="fpassword" type="password" class="easyui-validatebox" required="true" validType="length[6,50]" style="width:250px">
		</div>
<%
if(isroot) { %>
		<div class="fitem">
			<label>超级用户:</label>
			<input type="checkbox" name="isroot" id="isroot" value="1">
		</div>
<% } %>

		<div class="fitem">
			<label>email地址:</label>
			<input name="email" class="easyui-validatebox" validType="email" style="width:250px">
		</div>

		<div class="fitem">
			<label>手机号码:</label>
			<input name="tel" class="easyui-validatebox" style="width:250px">
		</div>

		<div class="fitem">
			<label>备注:</label>
			<input name="fmemo" class="easyui-validatebox" style="width:250px">
		</div>

	</form>

</div>

<div id="dlg-buttons">
	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="btn_save()">保存</a>
	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')">放弃</a>
</div>

<script type="text/javascript">

var url;
function btn_new(){
	$('#dlg').dialog('open').dialog('setTitle','新建');
	$('#fm').form('clear');
	url = '?opt=add';
}

function btn_mod(){
	var row = $('#tg').datagrid('getSelected');
	if (row){
		if(row.fname =='admin'){
			$.messager.show({ // show error message
				title: '提示',
				height:150,
				width:250,
				msg:'系统初始化数据，不能编辑或删除。'
				});
			return;
		}

		$('#dlg').dialog('open').dialog('setTitle','编辑');
		$('#fm').form('load',row);
		url = '?opt=mod&fid='+row.fid;
	}
}

function btn_save(){
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
	var row = $('#tg').datagrid('getSelected');
	if(!row) return;

	if(row.fname =='admin'){
		$.messager.show({title: '提示',height:150,width:250,
			msg:'系统初始化数据，不能编辑或删除。'});
		return;
	}

	$.messager.confirm('确认', '您确认要删除此记录："'+row.fname+'"?',function(r){
		if (r){
			$.post('?opt=del',  {fid: row.fid}, function(result){
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

function btn_power()
{
	var row = $('#tg').datagrid('getSelected');
	if(!row) return;
	if(row.isroot){
		$.messager.show({ // show error message
			title: '提示',
			height:150,
			width:250,
			msg:'该用户为超级管理员，不能设置权限。'
		});
		return;
	}
	window.parent.document.getElementById('mainframe').src = './userpower?fid='+row.fid;
}

function get_tree_data()
{
	var rows = [];
	$.ajax({url:'./devgroup?opt=listtree', dataType:"json", async:false,
		success: function(data){
			rows = convert(data);
		},
		error: function(){
		}
	});
	return rows;
}

function convert(rows){
	function exists(rows, parentId){
		for(var i=0; i<rows.length; i++){
			if (rows[i].id == parentId) return true;
		}
		return false;
	}

	var nodes = [];
	// get the top level nodes
	for(var i=0; i<rows.length; i++){
		var row = rows[i];
		if (!exists(rows, row._parentId)){
			nodes.push({
				id: row.id,
				text: row.grpname
			});
		}
	}
	var toDo = [];
	for(var i=0; i<nodes.length; i++){
		toDo.push(nodes[i]);
	}
	while(toDo.length){
		var node = toDo.shift(); // the parent node
		// get the children nodes
		for(var i=0; i<rows.length; i++){
			var row = rows[i];
			if (row._parentId == node.id){
				var child = {
					id: row.id,
					text: row.grpname
					};
				if (node.children){
					node.children.push(child);
				} else {
					node.children = [child];
				}
				toDo.push(child);
			}
		}
	}
	return nodes;
}

$(function(){
	//datagrid
    $('#tg').datagrid({
		iconCls: 'icon-ok',
		maximized:true,
		striped:true,
		rownumbers: true,
		fitColumns: true,
		url: '?opt=listall',
		toolbar: "#toolbar",
		title: "用户列表",
		pagination:true,
		singleSelect:true,
		columns:[[
			{field:'fname',title:'用户名称',width:100},
			{field:'grpname',title:'管理群组',width:150},
			{field:'isroot',title:'超级用户',width:60,align:'center',formatter: function(val,r,i){return ['否','是'][val]; }},
			{field:'email',title:'email地址',width:150},
			{field:'tel',title:'手机号码',width:100},
			{field:'fmemo',title:'备注',width:150}
		]]
    });

    var treeData = get_tree_data();
    $("#devgrp_id").combotree('loadData', treeData);
});

</script>
  </body>
</html>
