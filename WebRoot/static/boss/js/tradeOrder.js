$(function() {
	$('#mydatagrid').datagrid({
		title : '订单列表信息',
		iconCls : 'icon-ok',
		pageSize : 10,//默认选择的分页是每页5行数据
		pageList : [ 5, 10, 15, 20 ],//可以选择的分页集合
		nowrap : true,//设置为true，当数据长度超出列宽时将会自动截取
		striped : true,//设置为true将交替显示行背景。
		collapsible : true,//显示可折叠按钮
		toolbar:"#tb",//在添加 增添、删除、修改操作的按钮要用到这个
		url:'../../boss/order/ajax_list',//url调用Action方法
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
	              {field:'name',title:'订单名称',width:90,align:'center'},  
		            {field:'order_id',title:'订单ID',width:90,align:'center'},
		            {field:'total_money',title:'订单金额',width:90,align:'center'},
		            {field:'order_type',title:'订单类型',width:90,align:'center',
		            	formatter:function (value){
		            		  if(value == 1){
		            		    return '充值订单';
		            		  }else{
		            		    return '代购订单';
		            		  }
		            	}
		            },
					{field:'order_status',title:'订单状态',width:90,align:'center',
		            	formatter:function (value){
		            		  if(value == 1){
		            		    return '下单成功';
		            		  }else{
		            		    return '下单失败';
		            		  }
		            	}
		            },
					{field:'pay_status',title:'支付状态',width:90,align:'center',
		            	formatter:function (value){
		            		  if(value == 0){
		            		    return '未支付';
		            		  }else if(value == 1){
		            		    return '支付成功';
		            		  }else if(value == 2){
		            		    return '支付失败';
		            		  }else{
		            		    return '支付超时';
		            		  }
		            	}
		            },
					{field:'user_name',title:'下单人账号',width:90,align:'center'},
					{field:'real_name',title:'下单人名称',width:90,align:'center'},
					{field:'address',title:'地址',width:90,align:'center'},
					{field:'telephone',title:'电话',width:90,align:'center'},
					{field:'order_time',title:'订单时间',width:100,align:'center',
						formatter: function(value,row,index){
							var time = row.order_time;
							if(time !=""){
								return formatTime(time);
							}
					}},
		      ]]
	});	
});


function searchOrder(){
	var tradeOrder = {};
	tradeOrder["userName"]=$("#search_userName").val().trim();
	tradeOrder["realName"]=$("#search_realName").val().trim();
	tradeOrder["telephone"]=$("#search_telephone").val().trim();
	tradeOrder["orderType"]=$("#search_orderType").val().trim();
	tradeOrder["orderStatus"]=$("#search_orderStatus").val().trim();
	tradeOrder["payStatus"]=$("#search_payStatus").val().trim();
	//=======================================
	tradeOrder["startDate"]=$("#checkStartTime").datebox('getValue');
	tradeOrder["endDate"]=$("#checkEndTime").datebox('getValue');
	
	//把查询条件封装到实体对象中进行条件查询
	$('#mydatagrid').datagrid('load',tradeOrder);	
}

function clearSearchForm(){
	$("#search_userName").val("");
	$("#search_realName").val('');
	$("#search_telephone").val('');
	$("#search_orderType").val('');
	$("#search_orderStatus").val('');
	$("#search_payStatus").val('');
	$("#checkStartTime").val("");
	$("#checkEndTime").val("");
	searchOrder();
}
