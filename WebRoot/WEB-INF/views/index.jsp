<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="common/common.jsp"%>
<title>品学网后台系统</title>
</head>
    <c:if test="${sessionScope.user_session_info == null || sessionScope.user_session_info == '' }">
     	<c:redirect url="${request.getContextPath }/boss/login"/>
    </c:if>

<frameset rows="15%,85%">
	<frame src="${request.getContextPath }/boss/top" name="topFrame" noresize="noresize">
	<frameset cols="15%,85%">
		<frame src="${request.getContextPath }/boss/menu" name="leftFrame" noresize="noresize">
		<frame src="${request.getContextPath }/boss/resource/list" name="rightFrame">
	</frameset>
</frameset>
</html>
