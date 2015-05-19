<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page session="false" %>
<%@ taglib uri="http://jsptags.com/tags/navigation/pager" prefix="pg" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<title>视频列表</title>
<%@ include file="../../common/common.jsp"%>
</head>

<body>
<form action="" name="form" id="form" method="post" theme="simple">
	<table class="table table-bordered table-condensed">
		<tr>
			<td colspan="11">
					<button method="list" class="btn btn-primary" onclick="selectList(this)">
						<i class="icon-search icon-white"></i> 查询
					</button>
					<button class="btn btn-success" onclick="return toAdd();">
						<i class="icon-plus-sign icon-white"></i> 添加
					</button>	
					<div style="float: right;vertical-align: middle;bottom: 0px;top: 10px;">
						<%@ include file="../../common/pager.jsp"%>
				    </div>			
			</td>
		</tr>
	</table>
	
	<table class="table table-bordered table-hover">
		<thead>
			<tr style="background-color: #dff0d8">
				<th width="20"><input type="checkbox" id="firstCheckbox"/></th>
				<th>编号</th>
				<th>分类</th>
				<th>视频名称</th>
				<th>视频VID</th>
				<th>年级</th>
				<th>科目</th>
				<th>讲师</th>
				<th>时长</th>
				<th>状态</th>
				<th>添加人</th>
				<th>添加时间</th>
				<th nowrap="nowrap">操作</th>
			</tr>
		</thead>
		<c:forEach items="${page.list }" var="u">
			<tr >
			   <td><input type="checkbox" name="ids" value="${u.id }"/></td>
			   <td>&nbsp;${u.id }</td>
			   <td>&nbsp;${u.videoType.name }</td>
			   <td>&nbsp;${u.name }</td>
			   <td>&nbsp;${u.videoYunId }</td>
			   <td>&nbsp;${u.gradeId }</td>
			   <td>&nbsp;${u.topCourseId }</td>
			   <td>&nbsp;${u.lecturerId }</td>
			   <td>&nbsp;${u.duration }</td>
			   <td>&nbsp;${u.deleteFlag }</td>
			   <td>&nbsp;${u.createId }</td>
			   <td>&nbsp;${u.createDate }</td>
			   <td>
			      <a href="http://localhost:8080/px_management_web/boss/product/video/edit?id=${u.id}">编辑</a>
			   </td>
		    </tr>
		</c:forEach>
		<tr><td colspan="16" style="text-align:center;"><%@ include file="../../common/pager.jsp"%></td>
		</tr>
	</table>
</form>
 <script type="text/javascript">
	function toAdd(){
		var _url = "http://localhost:8080/px_management_web/boss/product/video/edit";
        var _form = $("#form");
		_form.attr("action",_url);
		_form.submit();
	}
 </script>
</body>
</html>
