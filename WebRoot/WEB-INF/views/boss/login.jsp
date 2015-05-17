<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>品学网后台管理</title>
<%@ include file="../common/bootsrapInclude.jsp"%>
</head>
<body>
	<div class="container" style="margin-top: 100px">
		<form action="${pageContext.request.contextPath}/boss/dologin" method="post" class="well"
			style="width: 220px; margin: 0px auto;">
			<h3>品学网后台管理</h3>
			<c:if test="${requestScope.error != null }">
			  <div class="control-group ">
				<div class="alert alert-error span2">
					<button type="button" class="close" data-dismiss="alert">×</button>
					${requestScope.error }
				</div>
			 </div>
			</c:if>
			
			<label>用户名:</label> 
			<input type="text" name="username" value="${username}"	style="height: 30px" class="span3" /> 
			<label>密码：</label> <input type="password" name="password" style="height: 30px" class="span3" />
			<button type="submit" class="btn btn-primary">登陆系统</button>
		</form>
	</div>
	
	<script type="text/javascript">
	//保证顶层窗口打开
	function open(win){
			win.document.location.href= document.location.href;
	}
	
	$(function (){
		if(window.top&&window.top!=window){
			open(window.top);
		}
	});
	
	</script>

</body>
</html>
