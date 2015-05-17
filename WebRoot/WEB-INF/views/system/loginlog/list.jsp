<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page session="false"%>
<%@ taglib uri="http://jsptags.com/tags/navigation/pager" prefix="pg"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<title>用户登录日志列表</title>
<%@ include file="../../common/common.jsp"%>
<style type="text/css">
.titleCss {
	background-color: #e6e6e6;
	border: solid 1px #e6e6e6;
	position: relative;
	margin: -1px 0 0 0;
	line-height: 32px;
	text-align: left;
}

.aCss {
	overflow: hidden;
	word-break: keep-all;
	white-space: nowrap;
	text-overflow: ellipsis;
	text-align: left;
	font-size: 12px;
}

.liCss {
	white-space: nowrap;
	text-overflow: ellipsis;
	overflow: hidden;
	height: 30px;
	text-align: left;
	margin-left: 10px;
	margin-right: 10px;
}
</style>
</head>

<body style="font-size: 12px;">
	<form action="" id="form1" name="form1" method="post" theme="simple">
				<table class="table table-bordered">
					<tr>
						<td>登陆账号</td>
						<td>
							<input type="text" cssClass="input-medium search-query" name="account" value="${account }" />
						</td>
					</tr>
				</table>
				
				<table class="table table-bordered">
					<tr>
						<td colspan="16">
							<button method="list" class="btn btn-primary" onclick="selectList(this)">
								<i class="icon-search icon-white"></i> 查询
							</button>
							
							<div style="float: right;vertical-align: middle;bottom: 0px;top: 10px;">
								<%@ include file="../../common/pager.jsp"%>
							</div>
						</td>
					</tr>
				</table>
				<table class="table table-bordered">
					<tr style="background-color: #dff0d8">
						<th width="20"><input type="checkbox" id="firstCheckbox" /></th>
						<th style="display: none;">编号</th>
						<th >类型</th>
						<th >账号</th>
						<th >登陆时间</th>
						<th >登陆IP</th>
					</tr>
					<c:forEach items="${page.list }" var="l">
						<tr>
							<td><input type="checkbox" name="ids" value="${l.id }" /></td>
							<td style="display: none;">&nbsp;${l.id }</td>
							<td>&nbsp;${l.eventType }</td>
							<td>&nbsp;${l.user.username }</td>
							<td>&nbsp;<fmt:formatDate value='${l.createDate}' pattern='yyyy-MM-dd HH:mm:ss'/></td>
							<td>&nbsp;${l.loginIp }</td>
						</tr>
					</c:forEach>
					<tr>
						<td colspan="17" style="text-align: center;"><%@ include file="../../common/pager.jsp"%></td>
					</tr>
				</table>
	</form>
</body>
</html>
