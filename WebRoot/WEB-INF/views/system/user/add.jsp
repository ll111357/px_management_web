<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<%@ include file="../../common/common.jsp"%>
<%@ include file="../../common/common_html_validator.jsp"%>
<title>后台添加用户</title>
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
					    <strong>帐号新增</strong>
					  </c:when>
					  <c:otherwise>
					    <strong>帐号编辑</strong>
					  </c:otherwise>
					</c:choose>
					
				</td>
			</tr>
			<tr style="display:none;">
				<th>id</th>
				<td><input type="hidden" name="id" value="${id }" /></td>
			</tr>
			<tr>
				<th class="td_right">帐号</th>
				<td style="text-align: left;">
					<input type="text" name="username" id="username" 
					  <c:if test="${id != null && id != '' }">
					    readonly="false" 
					  </c:if>		
					  value="${user.username }"		
				     <c:choose>
				       <c:when test="${id != null && id != '' }">
				          data-rule="帐号:required;username;length[4~20];"
				       </c:when>
				       <c:otherwise>
				          data-rule="帐号:required;username;length[4~20];remote[unique]"
				       </c:otherwise>
				     </c:choose>
					/>
				</td>
			</tr>
			<tr>
				<th class="td_right">真实姓名</th>
				<td style="text-align: left;">
				<input type="text" name="realName" id="realName" value="${user.realName }" data-rule="真实姓名:required;realName;length[2~20];"/>
				</td>
			</tr>
			
			<c:choose>
			   <c:when test="${id == null || id == '' }">
			      <tr>
					<th class="td_right">密码</th>
					<td style="text-align: left;">
					<input type="password" name="password" data-rule="密码:required;password;length[6~20];" id="password" />
					</td>
				  </tr>
				  <tr>
					<th class="td_right">确认密码</th>
					<td style="text-align: left;">
					<input type="password" name="newpassword2" data-rule="确认密码:required;match(password)" id="newpassword2" />
					</td>
				  </tr>
			   </c:when>
			   <c:otherwise>
			      <tr>
					<th class="td_right">密码</th>
					<td style="text-align: left;">
					  <input type="password" name="password" data-rule="密码:password;length[6~20];"  id="password" />
					   <br>(不输入表示不修改密码)
					</td>
				  </tr>
				  <tr>
					<th class="td_right">确认密码</th>
					<td style="text-align: left;">
						<input type="password" name="newpassword" data-rule="确认密码:match(password)" id="newpassword2" />
					</td>
				  </tr>
			   </c:otherwise>
			</c:choose>
			
			
			<tr>
				<th class="td_right">选择角色</th>
				<td style="text-align: left;">
					<select id="roleId" name="roleId" data-rule="角色:required;roleId;" >
					  <option value="">--请选择--</option>
					  <c:forEach items="${roleList }" var="r">
					    <option value="${r.id }" 
					      <c:if test="${r.id == userRole.id }">
					         selected="true"
					      </c:if>
					     >
					      ${r.name }
					    </option>
					  </c:forEach>
					</select>
				</td>
			</tr>
			
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
