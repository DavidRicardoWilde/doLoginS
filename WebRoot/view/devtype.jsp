<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>

   	<link rel="stylesheet" type="text/css" href="public/css/edtstyle.css">
	<link rel="stylesheet" type="text/css" href="public/css/easyui.css">
	<link rel="stylesheet" type="text/css" href="public/css/icon.css">

	<script type="text/javascript" src="public/jquery/jquery.min.js"></script>
	<script type="text/javascript" src="public/jquery/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="public/jquery/easyui-lang-zh_CN.js"></script>

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
	<div class="ftitle">设备型号信息</div>

	<form id="fm" method="post" novalidate>

		<input name="devtype_id" type="hidden">

		<div class="fitem">
			<label>型号名称:</label>
			<input name="devtypename" class="easyui-validatebox" required="true">
		</div>

		<div class="fitem">
			<label>CPU类型:</label>
			    <select name="dev_arch" class="easyui-combobox" editable="false" required="true" style="width:173px;">
					<option value="ARM">ARM</option>
					<option value="MIPS">MIPS</option>
					<option value="X86">X86</option>
					<option value="X86-64">X86-64</option>
				</select>
		</div>

		<div class="fitem">
			<label>CPU名称:</label>
			<input name="dev_archname" class="easyui-validatebox">
		</div>

	</form>

</div>

<div id="dlg-buttons">
	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="btn_save()">保存</a>
	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')">放弃</a>
</div>

<script type="text/javascript">

var url='./switchDevtype';
function btn_new(){
	$('#dlg').dialog('open').dialog('setTitle','新建');
	$('#fm').form('clear');
	url = url+'?funct=add';
}

function btn_mod(){
	var row = $('#tg').datagrid('getSelected');
	if (row){
		$('#dlg').dialog('open').dialog('setTitle','编辑');
		$('#fm').form('load',row);
		url = url+'?funct=mod&fid='+row.fid;
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
				title: '错误',
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

	if(row.fid ==1 || row.modulename=='system'){
		$.messager.show({title: '提示',height:150,width:250,
			msg:'系统初始化数据，不能编辑或删除。'});
		return;
	}

	$.messager.confirm('确认', '您确认要删除此记录："'+row.modulememo+'"?',function(r){
		if (r){
			$.post(url+'?funct=del',  {fid: row.fid}, function(result){
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

$(function(){
	//datagrid
	alert("test fucntion if it run already");
    $('#tg').datagrid({
		iconCls: 'icon-ok',
		maximized:true,
		striped:true,
		rownumbers: true,
		fitColumns: false,
		idField:'fid',
		singleSelect:true,
		url: "./getDevTypeData",
		toolbar: "#toolbar",
		title: "设备型号列表",
		columns:[[
			{field:'devtypename',title:'型号名称',width:80,halign:'center'},
			{field:'dev_arch',title:'CPU类型',width:80,halign:'center'},
			{field:'dev_archname',title:'CPU名称',width:300,halign:'center'},
		]]
    });
});

</script>

</body>

</html>