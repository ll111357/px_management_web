<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page session="false" %>
<%@ taglib uri="http://jsptags.com/tags/navigation/pager" prefix="pg" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<title>课程分类列表</title>
<%@ include file="../../common/common.jsp"%>
</head>

<body>
<form action="" name="form" id="form" method="post" theme="simple">
	<table class="table table-bordered table-condensed">
		<tr>
			<td colspan="11">
				分类名称：</i><input type="text" name="name" >&nbsp;&nbsp;
				<button method="list" class="btn btn-primary" onclick="selectList(this)">
					<i class="icon-search icon-white"></i> 查询
				</button>
				<button class="btn btn-success" onclick="return toAdd();">
					<i class="icon-plus-sign icon-white"></i> 添加
				</button>	
			</td>
		</tr>
	</table>
	
	<table class="table table-bordered table-hover">
		<thead>
			<tr style="background-color: #dff0d8">
				<th width="20"><input type="checkbox" id="firstCheckbox"/></th>
				<th>类型名称</th>
				<th>排序</th>
				<th nowrap="nowrap">操作</th>
			</tr>
		</thead>
		<c:forEach items="${page.list }" var="u">
			<tr >
			   <td><input type="checkbox" name="ids" value="${u.id }"/></td>
			   <td>&nbsp;${u.name }</td>
			   <td>&nbsp;${u.orderNo }</td>
			   <td>
			      <a href="http://localhost:8080/px_management_web/boss/product/courseType/edit?id=${u.id}">编辑</a>
			      &nbsp;<a href="http://localhost:8080/px_management_web/boss/product/courseType/delete?id=${u.id}">删除</a>
			   </td>
		    </tr>
		</c:forEach>
		<tr><td colspan="16" style="text-align:center;"><%@ include file="../../common/pager.jsp"%></td>
		</tr>
	</table>
</form>
 <script type="text/javascript">
	function toAdd(){
		var _url = "http://localhost:8080/px_management_web/boss/product/courseType/edit";
        var _form = $("#form");
		_form.attr("action",_url);
		_form.submit();
	}
 </script>
</body>
</html>
