<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<%@ include file="../../common/common.jsp"%>
<%@ include file="../../common/common_html_validator.jsp"%>
<title>添加视频类型</title>
<style>
	.td_right{text-align: right;}
</style>

</head>

<body>


	<form action="" namespace="/" theme="simple" id="form">
		<table class="table table-bordered">
			<tr>
				<td colspan="2" style="background-color: #dff0d8;text-align: center;">
					<c:choose>
					  <c:when test="${id == null || id == '' }">
					    <strong>新增</strong>
					  </c:when>
					  <c:otherwise>
					    <strong>编辑</strong>
					  </c:otherwise>
					</c:choose>
					
				</td>
			</tr>
			<tr style="display:none;">
				<th>id</th>
				<td><input type="hidden" name="id" value="${id }" /></td>
			</tr>
			
			<c:choose>
			   <c:when test="${id == null || id == '' }">
			      <tr>
					<th class="td_right">类型名称</th>
					<td style="text-align: left;">
					<input type="text" name="name" data-rule="类型名称:required;password;length[1~64];" id="name" />
					</td>
				  </tr>
			   </c:when>
			   <c:otherwise>
			      <tr>
					<th class="td_right">类型名称</th>
					<td style="text-align: left;">
					  <input type="text" name="name" data-rule="类型名称:password;length[1~64];"  id="name" />
					   <br>(不输入表示不修改)
					</td>
				  </tr>
			   </c:otherwise>
			</c:choose>
			
			
			<tr >
				<td colspan="2" style="text-align: center;">
				   <c:choose>
				     <c:when test="${id == null || id == '' }">
				        <button method="saveorupdate" class="btn btn-success">
							<i class="icon-ok icon-white"></i> 新增
						</button>
				     </c:when>
				     <c:otherwise>
				        <button method="saveorupdate" class="btn btn-success">
							<i class="icon-ok icon-white"></i> 保存
						</button>
				     </c:otherwise>
				   </c:choose>
					
					<button class="btn btn-inverse" onclick="history.go(-1);">
			   			 <i class="icon-arrow-left icon-white"></i> 返回
					</button>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>
