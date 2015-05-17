	$(function() {
		$('#memberUserDataGrid').datagrid({
			title : '会员列表信息',
			iconCls : 'icon-ok',
			pageSize : 10,//默认选择的分页是每页5行数据
			pageList : [ 5, 10, 15, 20 ],//可以选择的分页集合
			nowrap : true,//设置为true，当数据长度超出列宽时将会自动截取
			striped : true,//设置为true将交替显示行背景。
			collapsible : true,//显示可折叠按钮
			toolbar:"#tb",//在添加 增添、删除、修改操作的按钮要用到这个
			url:'../../boss/member/ajax_list_page',//url调用Action方法
			loadMsg : '数据装载中......',
			singleSelect:true,//为true时只能选择单行
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
			              {field:'user_name',title:'用户名',width:90,align:'center'},  
				            {field:'real_name',title:'真实名',width:90,align:'center'},
				            {field:'email',title:'邮箱',width:90,align:'center'},
				            {field:'address',title:'地址',width:90,align:'center'},
							{field:'city',title:'城市',width:90,align:'center'},
							{field:'telephone',title:'手机号码',width:90,align:'center'},
							{field:'identity_card',title:'身份证',width:90,align:'center'},
							{field:'bank_code',title:'支付宝账号',width:90,align:'center'},
							{field:'total_score',title:'总积分',width:90,align:'center'},
							{field:'available_score',title:'可用积分',width:90,align:'center'},
							{field:'create_date',title:'创建时间',width:100,align:'center',
								formatter: function(value,row,index){
									var time = row.create_date;
									if(time !=""){
										return formatTime(time);
									}
							}},
				      ]]
		});	
		
	});
	
	
	
	function searchMemberUser(){
		
		var memberUser = {};
		memberUser["userName"]=$("#search_userName").val();
		memberUser["realName"]=$("#search_realName").val();
		memberUser["telephone"]=$("#search_telephone").val();
		//=======================================
		memberUser["startDate"]=$("#checkStartTime").datebox('getValue');
		memberUser["endDate"]=$("#checkEndTime").datebox('getValue');
		
		//把查询条件封装到实体对象中进行条件查询
		$('#memberUserDataGrid').datagrid('load',memberUser);	
	}

	function clearSearchForm(){
		$('#queryForm').form('clear');
		searchMemberUser();
	}
	
	function closeMemberUserDialog(){
		$('#dlg').window('close');
		$('#memberUserForm').form('clear');
	}
	
	/**
	 * 弹出修改页面
	 * @param str
	 * @returns
	 */
	function updateMemberUserDialog(){
		var selectedRows=$("#memberUserDataGrid").datagrid('getSelections');
		if(selectedRows.length!=1){
			$.messager.alert('系统提示','请选择一条要编辑的数据！');
			return;
		}
		var row=selectedRows[0];
		$("#dlg").dialog("open").dialog("setTitle","修改会员信息");
		$("#memberUserForm").form("load",row);
		$("#update").show();
		$("#add").hide();
	}
	
	/**
	 * 修改信息提交
	 */
	function updateMemberUser(){
		if($("#userName").val().trim()==''){
			$.messager.show({ title : '提示', msg :'请输入用户名！'});  
			return;
		}
		if($("#realName").val().trim()==''){
			 $.messager.show({ title : '提示', msg :'真实姓名不能为空！'});
			 return;
		}
		if(!checkChineseName($("#realName").val().trim())){
			 $.messager.show({ title : '提示', msg :'中文姓名格式不正确！'});
			 return;
		}
		if($("#identityCard").val().trim()==''){
	    	 $.messager.show({ title : '提示', msg :'身份证号码不能为空！'});
			 return;
		}
		if(!isCardNo($("#identityCard").val().trim())){
			 $.messager.show({ title : '提示', msg :'身份证输入不合法！'}); 
			 return;  
		}
		if($("#telephone").val().trim()==''){
			 $.messager.show({ title : '提示', msg :'手机号不能为空！'}); 
			 return;
		}
		if(!checkPhone($("#telephone").val().trim())){
			 $.messager.show({ title : '提示', msg :'手机号格式不正确！'}); 
			 return;
		}
		if($("#bankCode").val().trim()==''){
			 $.messager.show({ title : '提示', msg :'支付宝账号不能为空！'}); 
			 return;
		}
		if(!validateEmail($("#bankCode").val().trim())
			&&!checkPhone($("#bankCode").val().trim())){
			 $.messager.show({ title : '提示', msg :'请输入支付宝账号（手机号/邮箱地址）！'}); 
			 return;
		}
		
		var memberUser = {};
		memberUser["userName"]=$("#userName").val();
		memberUser["realName"]=$("#realName").val();
		memberUser["telephone"]=$("#telephone").val();
		memberUser["identityCard"]=$("#identityCard").val();
		memberUser["bankCode"]=$("#bankCode").val();
		//保存
		$.ajax({
			url : '../../boss/member/updateMemberUser',
			data:memberUser,
			type:'post',
			dataType:'text',
			success : function(result) {
				$.messager.show({ title : '提示', msg : result });
				closeMemberUserDialog();
				clearSearchForm();
				$('#memberUserDataGrid').datagrid("reload");
			}    
		});
	}
	