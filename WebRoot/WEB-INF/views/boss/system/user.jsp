<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>用户管理</title>
<!-- 引入Jquery -->
<script type="text/javascript"	src="<%=basePath%>static/js/jquery-1.8.3.min.js" charset="utf-8"></script>
<script src="<%=basePath%>/static/js/commonUtil.js" type="text/javascript"></script>
<%@ include file="../../common/easyUiInclude.jsp"%>

<script type="text/javascript">
	$(function() {
		$('#mydatagrid').datagrid({
			title : '用户列表信息',
			iconCls : 'icon-ok',
			pageSize : 10,//默认选择的分页是每页5行数据
			pageList : [ 5, 10, 15, 20 ],//可以选择的分页集合
			nowrap : true,//设置为true，当数据长度超出列宽时将会自动截取
			striped : true,//设置为true将交替显示行背景。
			collapsible : true,//显示可折叠按钮
			toolbar:"#tb",//在添加 增添、删除、修改操作的按钮要用到这个
			url:'<%=basePath%>boss/sysuser/ajax_list',//url调用Action方法
			loadMsg : '数据装载中......',
			//singleSelect:true,//为true时只能选择单行
			fitColumns:true,//允许表格自动缩放，以适应父容器
			//sortName : 'xh',//当数据表格初始化时以哪一列来排序
			//sortOrder : 'desc',//定义排序顺序，可以是'asc'或者'desc'（正序或者倒序）。
			remoteSort : false,
 			 frozenColumns : [ [ {
				field : 'ck',
				checkbox : true
			} ] ], 
			pagination : true,//分页
			rownumbers : true,//行数
			columns:[[
		              	{field:'username',title:'用户名',width:90,align:'center'},  
			            {field:'real_name',title:'真实名',width:90,align:'center'},
						{field:'create_date',title:'创建时间',width:100,align:'center',
							formatter: function(value,row,index){
											var time = row.create_date;
											if(time !=''){
												return formatTime(time);
											}
										}
						}
			      ]]

		});	
		
	});
	
</script>	

</head>
<body>

<!-- 工具栏 -->
<div id="tb" style="height:auto">
    <form id="queryForm">
	<table>
		<tr>
			<td>用户名：</td>
			<td><input id="search_userName" /></td>
			<td>真实姓名：</td>
			<td><input id="search_realName" /></td>
		</tr>
		<tr>
			 <td>创建时间：</td>
			 <td>
              	<input name="checkStartTime" id="checkStartTime" class="easyui-datebox" style="width:150px">
             </td>
             <td align="right">到:</td>
             <td colspan="3">
               	<input name="checkEndTime" id="checkEndTime" class="easyui-datebox" style="width:150px">
             </td>
		</tr>
	</table>  
	</form>
	<div>
		<a href="javascript:searchSysUser()" class="easyui-linkbutton" iconCls="icon-search" plain="true">查询</a>
		<a href="javascript:newUser()" class="easyui-linkbutton" iconCls="icon-add" plain="true">新建</a>
		<a href="javascript:destroyUser()" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
	</div>
</div>
	<table id="mydatagrid" ></table>
   
   <div id="dlg" class="easyui-dialog" style="width:400px;height:280px;padding:10px 20px"
		closed="true" buttons="#dlg-buttons">
		<form id="fm" method="post" novalidate accept-charset="utf-8" onsubmit="document.charset='utf-8'">
			<div class="fitem">
			<label>用户名:</label>
			<input id="userName" name="userName" class="easyui-textbox" >
			</div>
			<div class="fitem">
			<label>密&nbsp;&nbsp;码:</label>
			<input id="password" name="password" type="password" class="easyui-textbox" >
			</div>
			<div class="fitem">
			<label>姓&nbsp;&nbsp;名:</label>
			<input id="realName" name="realName" class="easyui-textbox" >
			</div>
			
		</form>
	</div>
	<div id="dlg-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="saveUser()" style="width:90px">确定</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')" style="width:90px">取消</a>
	</div>
	
	<script type="text/javascript">
		var url;
		function newUser(){
			$('#dlg').dialog('open').dialog('setTitle','创建用户');
			$('#fm').form('clear');
			url = '<%=basePath%>boss/sysuser/ajax_save';
		}

		function saveUser(){
		    var userName = $("#userName").val();
		    var password = $("#password").val();
		    var realName = $("#realName").val();
		    
		    if(userName == ""){
		      alert("请输入用户名！");
		      return false;
		    }
		    
		    if(password == ""){
		      alert("请输入密码！");
		      return false;
		    }
		    
		    if(userName != "" && password != ""){
		      $('#fm').form('submit',{
				url: url,
				onSubmit: function(){
				return $(this).form('validate');
				},
				success: function(result){
					var result = eval('('+result+')');
					if (result.sucess){
						$('#dlg').dialog('close'); // close the dialog
						$('#mydatagrid').datagrid('reload'); // reload the user data
					} else {
						$.messager.show({
							title: '提醒',
							msg: "新增失败"
						});
				}
			  }
			 });
		    }
			
		}
		
		
		function destroyUser(){
				var row = $('#mydatagrid').datagrid('getSelections');
				if (row.length == 0){
				  alert("请至少选择一行数据！");
				  return false;
				}else{
				    var ids='';
				    for(var i=0;i<row.length;i++){
				      var id=row[i].id;
				      ids+=id+';';
				    }
				    if(ids.length >0){
				      ids=ids.substring(0,ids.length-1);
				    }
				    
					$.messager.confirm('Confirm','你是否要删除选中记录?',function(r){
					if (r){
						$.post('<%=basePath%>boss/sysuser/ajax_delete',
						{ids:ids},
						function(result){
							if (result.sucess){
								$('#mydatagrid').datagrid('reload'); // reload the user data
							}else {
								$.messager.show({ // show error message
									title: '提醒',
									msg: "删除失败！"
							});
						}
					},'json');
				  }
				});
				}
		}
		
		function clearSearchForm(){
			$("#queryForm").form('clear');
			searchSysUser();
		}
		
		function searchSysUser(){
			var sysUser = {};
			sysUser["realName"]=$("#search_realName").val().trim();
			sysUser["userName"]=$("#search_userName").val().trim();
			sysUser["startDate"]=$("#checkStartTime").datebox('getValue');
			sysUser["endDate"]=$("#checkEndTime").datebox('getValue');
			
			//把查询条件封装到实体对象中进行条件查询
			$('#mydatagrid').datagrid('load',sysUser);	
		}
</script>
   
</body>
</html>