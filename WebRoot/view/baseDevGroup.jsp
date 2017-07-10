<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>BaseDevGroupService</title>
    
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
	
  </head>
  
  <body>
     <table id="tg" height="100%" width="100%">
	<thead>
		<tr>

		</tr>
	</thead>
</table>

<div id="toolbar">
<% 
int pw=255;
if( (pw & (1<<0)) >0 ){ %>
	<a href="javascript:void(0)" class="easyui-linkbutton easyui-tooltip" iconCls="icon-add" plain="true"    onclick="btn_new_child()" title="新建选中记录的下级记录" position="right">新建子节点</a>
	<a href="javascript:void(0)" class="easyui-linkbutton easyui-tooltip" iconCls="icon-add" id="btn_addb" plain="true"   onclick="btn_new_nested()" title="编辑选中记录的平行记录" position="right">新建平行节点</a>
<% } %>

<% if( (pw & (1<<2)) >0 ){ %>
	<a href="javascript:void(0)" class="easyui-linkbutton easyui-tooltip" iconCls="icon-edit" plain="true"   onclick="btn_mod()" title="编辑现有记录" position="right">编辑</a>
<% } %>

<!--  if( pw == 0xFFFFFFFF ){ -->
<% if( pw == 255 ){ %>
	<a href="javascript:void(0)" class="easyui-linkbutton easyui-tooltip" iconCls="icon-cancel" plain="true" onclick="btn_del()" title="删除一条记录" position="right">删除</a>
<% } %> 
</div>

<div id="dlg" class="easyui-dialog" style="width:550px;height:350px;padding:10px 20px"
	closed="true" buttons="#dlg-buttons" resizable="true" modal="true">
	<div class="ftitle">设备群组信息</div>

	<form id="fm" method="post" novalidate>

		<!-- <input name="id" id="id" type="hidden"> -->
		<div class="fitem">
			<label>id:</label>
			<input name="id" id="id" >
		</div>

		<div class="fitem">
			<label>设备群组名称:</label>
			<input name="grpname" class="easyui-validatebox" required="true" style="width:160px">
		</div>

		<div class="fitem">
			<label>可用扩展应用:</label>
			<select name="ml1" id="ml1" class="easyui-validatebox" style="width:290px"></select>
		</div>

		<div class="fitem">
			<label>认证方式:</label>
			<select name="efc" id="efc" class="easyui-combobox" editable="false" style="width:160px">
				<option value="0">熟客认证</option>
				<option value="1">免认证</option>
				<option value="2">强制认证</option>
			</select>
		</div>

		<div class="fitem">
			<label>微信公共号:</label>
			<select name="wxid" id="wxid" class="easyui-combobox" editable="false" style="width:160px">
			</select>
		</div>

		<div class="fitem">
			<label>短信平台:</label>
			<select name="smsid" id="smsid" class="easyui-combobox" editable="false" style="width:160px">
				<option value="1">默认短信平台</option>
				<option value="2">上海集信通短信平台</option>
			</select>
			(修改时需超级用户权限)
		</div>

		<div class="fitem">
			<label>备注</label>
			<input name="grpmemo" class="easyui-validatebox" style="width:290px">
		</div>

	</form>

</div>



<script type="text/javascript">

var url,pw = <%=pw %>;
url='./devGrpSwitch';
var treeData;

function btn_new_child(){
	//alert("new child");
	var row = $('#tg').datagrid('getSelected');
	if (!row)return;

	$('#dlg').dialog('open').dialog('setTitle','新建子节点');
	$('#fm').form('clear');
	$('#id').val(row.id);
	url = url+'?funct=add_child&parentId='+row._parentId;
}

function btn_new_nested(){
	var row = $('#tg').datagrid('getSelected');
	if (!row)return;

	$('#dlg').dialog('open').dialog('setTitle','新建平行节点');
	$('#fm').form('clear');
	$('#id').val(row.id);
	
	url = url+'?funct=add_nested&parentId='+row._parentId;
}

function btn_mod(){
	var row = $('#tg').datagrid('getSelected');
	if (row){
		if(row.id ==1 && row._parentId==0){
			$.messager.show({
				title: '提示',
				height:150,
				width:250,
				msg:'系统初始化数据，不能编辑或删除。'
				});
			return;
		}

		$('#dlg').dialog('open').dialog('setTitle','编辑');
		$('#fm').form('load',row);
		url = url+'?funct=mod&parentId='+row._parentId;
	}
}

function btn_save(){
	alert(url);
	//alert("pid = "+$('#pid').val());
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
				$('#dlg').dialog('close');
				$('#tg').treegrid('reload');
			}
		}
	});
}

function btn_del(){
	var row = $('#tg').treegrid('getSelected');
	if(!row) return;
	if( (row.id ==1 && row._parentId==0) || row.id==2){
		$.messager.show({title: '提示',height:150,width:250,
			msg:'系统初始化数据，不能编辑或删除。'});
		return;
	}
	$.messager.confirm('确认', '您确认要删除此记录："'+row.grpname+'"?<p style="color:red">(属于此记录下的所有子节点都将删除)</p>',function(r){
		if (r){
		url='./devGrpSwitch?funct=del&parentId='+row._parentId;
			$.post(url,  {id: row.id}, function(result){
			if (result.success){
				$('#tg').treegrid('reload');
			} else {
				$.messager.show({
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
	var  ml={};
	var wxlst;
	$.ajax({ 
		type:'GET',
		url: './assitTest',
		success:function(result,s){
			wxlst = result;
		    $('#wxid').combobox({
			data: result,
			valueField:'fid',
			textField:'pname',
			separator:',',
			panelHeight:100,
			multiple:false
		});
	},dataType:'json', async:false});

	//treegrid
	//alert("test a");
    $('#tg').treegrid({
		iconCls: 'icon-ok',
		//maximized:true,
		striped:true,
		rownumbers: true,
		animate: true,
		collapsible: true,
		fitColumns: true,
		url: './getBaseDevGrpData',
		idField: 'id',
		treeField: 'grpname',
		toolbar: "#toolbar",
		nowrap:false,
		title: "设备群组列表",
		columns:[[
			{field:'grpname',title:'设备群组名称',width:250},
			{field:'ml',title:'可用扩展模块',width:200},
			{field:'efc',title:'认证方式',width:100,align:'center', formatter:function(v,r){
				return ['熟客认证','免认证','强制认证'][v]
			}},
			{field:'wxid',title:'微信',width:100,align:'center', formatter:function(v,r){
 				for(var i=0;i<wxlst.length;i++){
 					if(v == wxlst[i].fid)
 						return wxlst[i].pname;
 				}
			}},
			{field:'smsid',title:'短信平台',width:100,align:'center', formatter:function(v,r){
				return ['默认短信平台','集信通短信','强制认证'][v-1]
			}},
			{field:'id',title:'id',width:150},
			{field:'_parentId',title:'pid',width:150},
			{field:'grpmemo',title:'备注',width:150}
		]],
		onClickRow:function(row){
			$('#btn_addb').linkbutton( ($('#tg').treegrid('getLevel', row.id)==1)?'disable':'enable');
		}
    });

	$('#ml1').combotree({
		url:'./switchModules?funct=assist',
		valueField:'id',
		textField:'text',
		separator:',',
		multiple:true
    });
});

function get_tree_data(data)
{
	var rows = convert(data.rows);
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
		var node = toDo.shift();
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

</script><div id="dlg-buttons">
	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="btn_save()">保存</a>
	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')">放弃</a>
</div>
     
  </body>
</html>
