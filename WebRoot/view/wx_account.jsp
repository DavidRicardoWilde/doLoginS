<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>wx_paccount</title>
    
    <link rel="stylesheet" type="text/css" href="public/css/edtstyle.css">
	<link rel="stylesheet" type="text/css" href="public/css/easyui.css">
	<link rel="stylesheet" type="text/css" href="public/css/icon.css">
	
	<script type="text/javascript" src="public/jquery/jquery.min.js"></script>
	<script type="text/javascript" src="public/jquery/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="public/jquery/easyui-lang-zh_CN.js"></script>
	
	<style>
	.easyui-validatebox{
		width:250px;
	}
	.fitem{
		line-height:30px;
		font-size:14px;
	}
	
	.datagrid-cell,
	.datagrid-cell-c1-info
	{
		padding:5px 10px 5px 10px;
		font-size:13px;
	}
	</style>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  	
<table id="dg" height="100%" width="100%"></table>

<div id="toolbar" style="padding:3px">


<% 
int pw=255;
if( (pw & (1<<0)) >0 ){ %>
	<span>
		<a href="#" class="easyui-linkbutton" plain="true" iconCls="icon-add" onclick="on_add()">添加</a>
	</span>
<% } %>
</div>

<div id="dlg" class="easyui-dialog" style="width:500px;height:400px;padding:10px 20px"
	closed="true" buttons="#dlg-buttons" resizable="true" modal="true">
	<div class="ftitle">微信公共号信息</div>

	
</div>

<div id="dlg-buttons">
	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="btn_save()">保存</a>
	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')">放弃</a>
</div>


<script type="text/javascript">

var url,pw = 0;
function on_add()
{
	$('#dlg').dialog('open').dialog('setTitle','新建');
	$('#fm').form('clear');
	$('#enabled').combobox('select', '1');
	url = './switchData?funct=add';  //'?opt=add';   //this is a good way
}

function btn_mod(idx)
{
	$('#dg').datagrid('selectRow', idx);
	var row = $('#dg').datagrid('getSelected');
	if(row){
		$('#dlg').dialog('open').dialog('setTitle','编辑');
		$('#fm').form('load',row);
		url = './switchData?funct=mod';
	}
}

function btn_save()
{
	//alert("url:"+url);
	$('#fm').form('submit',{
		url: url,
		//url:"./saveData",    //not a good method
		onSubmit: function(){
			//$appid=$("#appid").val;  //only element
			//alert("here2");  //
			//alert("appid = "+$("#appid").val()); //获取不到appid？？？
			return $(this).form('validate');
		},
		success: function(result){
			//alert("result");
			var rst = $.parseJSON(result);
			if (rst.success==0){
				$.messager.show({title:'错误', msg:rst.errorMsg});
			} else {
				$('#dlg').dialog('close');
				$('#dg').datagrid('reload');
			}
		}
	});
}

function btn_del(idx)
{
	$('#dg').datagrid('selectRow', idx);
	var row = $('#dg').datagrid('getSelected');
	if(!row) return;

	$.messager.confirm('确认', '您确认要删除此记录:"'+row.fid+'"?',function(r){
		if (r){
			$.post('./switchData?funct=del',  {fid: row.fid}, function(result){
				if (result.success==1) $('#dg').datagrid('reload');
				else $.messager.show({ title: '错误',msg:"数据删除错误！"});
			},'json');
		}
	});
}

$(document).ready(function() {
	//datagrid
	//url: '?opt=list',
	url='./wxJson';
    $('#dg').datagrid({
		iconCls: 'icon-ok',
		striped:true,
		rownumbers: true,
		url: url,
		toolbar: "#toolbar",
		title: "微信公共号管理",
		pagination:true,
		maximized:true,
		pageSize:20,
		singleSelect:true,
		nowrap:false,
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
			{field:"paccount",title:'原始ID',align:'center',width:150},
            {field:"pnick",title:'微信号',width:100},
            {field:"pname",title:'名称',width:160},
            {field:"enabled",title:'启用',width:40,formatter:function(v,r,i){
				return ['<span class="outline">禁用</span>','<span class="online">启用</span>','<span class="online">第三方</span>'][v];}},
            {field:"appid",title:'AppId',width:200},
            {field:"appsecret",title:'AppSecret',width:300},
            {field:"last",title:'最后更新',width:160}
		]]
    });
});

</script>
  <body>
    
  <form id="fm" method="post" style="padding:10px" novalidate="">
		<input type="hidden" name="fid">

		<div class="fitem">
			<label>原始ID:</label>
			<input name="paccount" class="easyui-validatebox" required="true">
		</div>

		<div class="fitem">
			<label>微信号:</label>
			<input name="pnick" class="easyui-validatebox" required="true">
		</div>

		<div class="fitem">
			<label>名称:</label>
			<input name="pname" class="easyui-validatebox" required="true">
		</div>

		<div class="fitem">
			<label>AppId:</label>
			<input name="appid" id="appid" class="easyui-validatebox" required="true">
		</div>

		<div class="fitem">
			<label>AppSecret:</label>
			<input name="appsecret" class="easyui-validatebox" required="true">
		</div>

		<div class="fitem">
			<label>启用:</label>
			    <select name="enabled" id="enabled" class="easyui-combobox" style="width:173px;" panelHeight="70" editable="false">
					<option value="1">启用</option>
					<option value="0">禁用</option>
					<option value="2">第三方</option>
				</select>
		</div>

		<div class="fitem">
			<label>最后更新:</label>
			<input name="last" class="easyui-validatebox">
		</div>



	</form></body>
</html>
