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
    <form id="fm" method="post" novalidate>

<input name="fid" id="fid" type="hidden" value="<%= fid %>">

<table id="tg" height="100%" width="100%"></table>

<div id="toolbar">
	<a href="javascript:void(0)" class="easyui-linkbutton easyui-tooltip" iconCls="icon-save" plain="true" onclick="btn_save()" title="保存用户的权限设置" position="right">保存</a>
	<a href="javascript:void(0)" class="easyui-linkbutton easyui-tooltip" iconCls="icon-back" plain="true" onclick="btn_back()" title="返回到用户信息页面" position="right">返回</a>
</div>

</form>

<script type="text/javascript">

var url;
var fid=<%= fid %>;
function btn_save(){
	$('#fm').form('submit',{
		url: '?opt=mod',
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

function btn_back()
{
window.parent.document.getElementById('mainframe').src = './userinfo';
}

function show_info_of_pw(val, row)
{
	var _e = '<input type="checkbox" name="ffid' +row.ffid+ '" value="';
	var b_add = 1, b_del = 1<<1, b_mod = 1<<2, b_qry = 1<<3
		, b_ipt = 1<<4, b_opt = 1<<5, b_sdg = 1<<6, b_ad1 = 1<<7;

	var str = (row.pwinfo & b_add)>0? _e+b_add+'"' +( (val && (val&b_add)>0 ) ? ' checked="true"' : '' ) +'>增&nbsp;' : '';
	str += (row.pwinfo & b_del)>0? _e+b_del+'"' +( (val && (val&b_del)>0 ) ? ' checked="true"' : '' ) +'>删&nbsp;' : '';
	str += (row.pwinfo & b_mod)>0? _e+b_mod+'"' +( (val && (val&b_mod)>0 ) ? ' checked="true"' : '' ) +'>改&nbsp;' : '';
	str += (row.pwinfo & b_qry)>0? _e+b_qry+'"' +( (val && (val&b_qry)>0 ) ? ' checked="true"' : '' ) +'>查&nbsp;' : '';
	str += (row.pwinfo & b_ipt)>0? _e+b_ipt+'"' +( (val && (val&b_ipt)>0 ) ? ' checked="true"' : '' ) +'>导入&nbsp;' : '';
	str += (row.pwinfo & b_opt)>0? _e+b_opt+'"' +( (val && (val&b_opt)>0 ) ? ' checked="true"' : '' ) +'>导出&nbsp;' : '';
	str += (row.pwinfo & b_sdg)>0? _e+b_sdg+'"' +( (val && (val&b_sdg)>0 ) ? ' checked="true"' : '' ) +'>设备组&nbsp;' : '';
	str += (row.pwinfo & b_ad1)>0? _e+b_ad1+'"' +( (val && (val&b_ad1)>0 ) ? ' checked="true"' : '' ) +'>广告位&nbsp;' : '';
	//if(str.length > 0)
	//	str = str.substring(1);
	return str;
}

/**
 * 级联判断父节点 的子节点是否有被选择的
 * @param {Object} target
 * @param {Object} id 节点ID
 * @return {TypeName}
 */
function IsParentHasSelectedChildren(target,id,idField,status)
{
	var count=0;
	var children = $(target).treegrid('getChildren',id);
	var selectNodes = $(target).treegrid('getSelections');
	var p=$(target).treegrid('find',id);
	//注意,这里的children是指后代所有子节点,不是指儿子节点,所以要加上children[i]['_parentId']==p[idField]过滤出儿子节点
	for(var i=0;i<children.length ;i++  )
	{
		var childId = children[i][idField];
		for(var j=0;j<selectNodes.length;j++){
			if(selectNodes[j][idField]==childId && children[i]['_parentId']==p[idField])
				count++;
		}
	}
	//注意,click 函数在unselect事件之前运行,这里需要减去自己
	return count>0;
}

function chk_ffid(ffid, id)
{
	var node;
	var isChecked = $('#'+ffid).is(":checked");
	if(isChecked){
		node = $('#tg').treegrid('getParent', id);
		if(node) document.getElementById(node.ffid).checked = true;

		node = $('#tg').treegrid('getChildren', id);
		for(var i=0; i<node.length; i++){
			document.getElementById(node[i].ffid).checked = true;
		}
	}
	else{
		node = $('#tg').treegrid('getChildren', id);
		for(var i=0; i<node.length; i++){
			document.getElementById(node[i].ffid).checked = false;
		}
	}
}


$(function(){
    $('#tg').treegrid({
		iconCls: 'icon-ok',
		//maximized:true,
		striped:true,
		rownumbers: true,
		animate: true,
		collapsible: true,
		fitColumns: true,
		url: '?opt=listall&fid=<%= fid %>',
		idField: 'id',
		treeField: 'fname',
		toolbar: "#toolbar",
		title: "用户权限设置：<%= fname %>",
		columns:[[
			{field:'fname',title:'模块或模块功能名称',width:150,formatter:function(val,row,idx){
				var _str = '<input type="checkbox" name="ffid" id="'+row.ffid+'" onclick="chk_ffid('+row.ffid+','+row.id+')" value="'+row.ffid+'" ';
				_str += (row.cfid>0) ? 'checked="true"' : '';
				_str += '>&nbsp;&nbsp;&nbsp;' + val;
				return _str;
			}},
			{field:'cpw',title:'权限属性',width:200, formatter:function(val,row, idx){
				return show_info_of_pw(val, row);
			}}
		]]
    });
});

</script>

  </body>
</html>
