<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'feature.jsp' starting page</title>
    
     <link rel="stylesheet" type="text/css" href="public/css/edtstyle.css">
	<link rel="stylesheet" type="text/css" href="public/css/easyui.css">
	<link rel="stylesheet" type="text/css" href="public/css/icon.css">
	
	<script type="text/javascript" src="public/jquery/jquery.min.js"></script>
	<script type="text/javascript" src="public/jquery/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="public/jquery/easyui-lang-zh_CN.js"></script>
	
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
int pw =255;
if( (pw & (1<<0)) >0 ){ %>
	<a href="javascript:void(0)" class="easyui-linkbutton easyui-tooltip" iconCls="icon-add" plain="true"    onclick="btn_new()" title="新建一条记录" position="right">新建</a>
<% } %>

<% if( (pw & (1<<2)) >0 ){ %>
	<a href="javascript:void(0)" class="easyui-linkbutton easyui-tooltip" iconCls="icon-edit" plain="true"   onclick="btn_mod()" title="编辑现有记录" position="right">编辑</a>
<% } %>

<% if( (pw & (1<<1)) >0 ){ %>
	<a href="javascript:void(0)" class="easyui-linkbutton easyui-tooltip" iconCls="icon-cancel" plain="true" onclick="btn_del()" title="删除一条记录" position="right">删除</a>
<% } %>
</div>

<div id="dlg" class="easyui-dialog" style="width:520px;height:480px;padding:10px 20px"
	closed="true" buttons="#dlg-buttons" resizable="true" modal="true">
	<div class="ftitle">模块或模块功能信息</div>

	<form id="fm" method="post" novalidate>
		<input name="fid" type="hidden">

		<div class="fitem">
			<label>父节点ID:</label>
			<input name="pid" class="easyui-numberbox" required="true" style="width:290px">
		</div>

		<div class="fitem">
			<label>节点ID:</label>
			<input name="id" class="easyui-numberbox" required="true" style="width:290px">
		</div>

		<div class="fitem">
			<label>名称:</label>
			<input name="fname" class="easyui-validatebox" required="true" style="width:290px">
		</div>
		<div class="fitem">
			<label>URL:</label>
			<input name="furl" class="easyui-validatebox" style="width:290px">
		</div>
		<div class="fitem">
			<label>图标</label>
			<input name="ficon" class="easyui-validatebox" style="width:290px">
		</div>
		<div class="fitem">
			<label>超级权限</label>
			<input name="isroot" class="easyui-validatebox" type="checkbox" value="1">
		</div>
		<div class="fitem">
			<label></label>
			<input type="checkbox" name="b_add" id="b_add" value="1">增加
			<input type="checkbox" name="b_del" id="b_del" value="1">删除
			<input type="checkbox" name="b_mod" id="b_mod" value="1">修改
			<input type="checkbox" name="b_qry" id="b_qry" value="1">查询
		</div>
		<div class="fitem">
			<label>权限属性</label>
			<input type="checkbox" name="b_ipt" id="b_ipt" value="1">导入
			<input type="checkbox" name="b_opt" id="b_opt" value="1">导出
		</div>
		<div class="fitem">
			<label></label>
			<input type="checkbox" name="b_sdg" id="b_sdg" value="1">选择设备群组
			<input type="checkbox" name="b_ad1" id="b_ad1" value="1">广告位选择
		</div>

		<div class="fitem">
			<label>备注</label>
			<input name="fmemo" class="easyui-validatebox" style="width:290px">
		</div>

		<div class="fitem">
			<label>显示顺序</label>
			<input name="ordid" class="easyui-numberspinner" data-options="min:0,max:99999">
		</div>

	</form>
</div>

<div id="dlg-buttons">
	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="btn_save()">保存</a>
	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')">放弃</a>
</div>

<script type="text/javascript">

var url='./featureSwitch';
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
		show_chkbox_of_pw(row.pwinfo);
		show_info_of_pw(row.pwinfo);
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
				title: 'Error',
				msg: result.errorMsg
				});
			} else {
				$('#dlg').dialog('close'); // close the dialog
				$('#tg').treegrid('reload'); // reload the user data
			}
		}
	});
}

function btn_del(){
	var row = $('#tg').treegrid('getSelected');
		if (row){
		$.messager.confirm('确认', '您确认要删除此记录："'+row.fname+'"?',function(r){
			if (r){
				$.post(url+'?funct=del',  {fid: row.fid}, function(result){
				if (result.success){
					$('#tg').treegrid('reload'); // reload data
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
}

function show_chkbox_of_pw(pwinfo)
{
	var b_add = 1, b_del = 1<<1, b_mod = 1<<2, b_qry = 1<<3
		, b_ipt = 1<<4, b_opt = 1<<5, b_sdg = 1<<6, b_ad1 = 1<<7;
	document.getElementById('b_add').checked = pwinfo & b_add;
	document.getElementById('b_del').checked = pwinfo & b_del;
	document.getElementById('b_mod').checked = pwinfo & b_mod;
	document.getElementById('b_qry').checked = pwinfo & b_qry;
	document.getElementById('b_ipt').checked = pwinfo & b_ipt;
	document.getElementById('b_opt').checked = pwinfo & b_opt;
	document.getElementById('b_sdg').checked = pwinfo & b_sdg;
	document.getElementById('b_ad1').checked = pwinfo & b_ad1;
}

function show_info_of_pw(pwinfo)
{
	var b_add = 1, b_del = 1<<1, b_mod = 1<<2, b_qry = 1<<3
		, b_ipt = 1<<4, b_opt = 1<<5, b_sdg = 1<<6, b_ad1 = 1<<7;

	var str = (pwinfo & b_add)>0? ',增' : '';
	str += (pwinfo & b_del)>0? ',删' : '';
	str += (pwinfo & b_mod)>0? ',改' : '';
	str += (pwinfo & b_qry)>0? ',查' : '';
	str += (pwinfo & b_ipt)>0? ',导入' : '';
	str += (pwinfo & b_opt)>0? ',导出' : '';
	str += (pwinfo & b_sdg)>0? ',设备组' : '';
	str += (pwinfo & b_ad1)>0? ',广告位' : '';
	if(str.length > 0)
		str = str.substring(1);
		
	return str;
}


$(function(){
    $('#tg').treegrid({
		iconCls: 'icon-ok',
		//maximized:true,
		striped:true,
		rownumbers: true,
		animate: true,
		collapsible: true,
		//fitColumns: true,
		//nowrap:false,
		url: './featurePageData',
		idField: 'id',
		treeField: 'fname',
		toolbar: "#toolbar",
		title: "模块及功能列表",
		columns:[[
			{field:'fname',title:'模块或模块功能名称',width:160},
			{field:'furl',title:'功能url链接',width:150},
			{field:'id',title:'节点ID',width:40,align:'right'},
			{field:'pid',title:'父节点',width:40,align:'right'},
			{field:'ficon',title:'图标url链接',width:170},
			{field:'isroot',title:'root',width:30, align:'center', formatter:function(val,row){return val>0?'<span class="online">是</span>':'否'}},
			{field:'pwinfo',title:'权限属性',width:170, formatter:function(val,row){return show_info_of_pw(val);}},
			{field:'ordid',title:'显示顺序',width:100,align:'center'},
		]]
    });
});

</script>

  </body>
</html>
