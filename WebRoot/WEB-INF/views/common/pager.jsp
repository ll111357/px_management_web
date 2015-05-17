<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page session="false"%>
<%@ taglib uri="http://jsptags.com/tags/navigation/pager" prefix="pg"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<style type="text/css">
.pageLink {
	border: 1px solid #dddddd;
	padding: 4px 12px;
	text-decoration: none;
}

.selectPageLink {
	border: 1px solid #0088cc;
	padding: 4px 12px;
	color: #0088cc;
	background-color: #dddddd;
	text-decoration: none;
}
</style>
</head>

<body>
	<!-- 分页标签 -->
	<div style="text-align: right; border: 0;padding: 4px 12px;" class="pageDiv">
		<pg:pager url="" items="${page.totalCount}"
			export="currentPageNumber=pageNumber"
			maxPageItems="${page.pageSize}" maxIndexPages="10" isOffset="true">
					总共：${page.totalCount}条,共:${page.pageSize}页
					<pg:param name="cc" />
			<pg:first>
				<a href="${request.getContextPath }?currentPage=${pageNumber}" class="pageLink">首页</a>
			</pg:first>
			<pg:prev>
				<a href="${request.getContextPath }?currentPage=${pageNumber}" class="pageLink">上一页</a>
			</pg:prev>
			<pg:pages>
				<c:choose>
					<c:when test="${page.currentPage==pageNumber}">
						<span class="selectPageLink">${pageNumber}</span>
					</c:when>
					<c:otherwise>
						<a href="${request.getContextPath }?currentPage=${pageNumber}" class="pageLink">${pageNumber}</a>
					</c:otherwise>
				</c:choose>
			</pg:pages>
			<pg:next>
			     <a href="${request.getContextPath }?currentPage=${pageNumber}" class="pageLink">下一页</a>
			</pg:next>
			<pg:last>
				<a href="${request.getContextPath }?currentPage=${pageNumber}" class="pageLink">尾页</a>
			</pg:last>
		</pg:pager>
	</div>
</body>
</html>
