<link rel="stylesheet" href="<%=request.getContextPath()%>/static/bootstrap/css/bootstrap.min.css"  type="text/css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/base.css"  type="text/css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/ueditor/themes/default/css/ueditor.css"  type="text/css" />

<script type="text/javascript" src="<%=request.getContextPath() %>/static/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/static/js/jquery.blockUI.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/static/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/static/js/manage.js"></script>

<script type="text/javascript">
	window.UEDITOR_HOME_URL="http://localhost:8080/px_management_web/ueditor/";
</script>
<script type="text/javascript" src="<%=request.getContextPath() %>/ueditor/ueditor.all.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/ueditor/ueditor.config.js"></script>


<c:set var="context" value="${pageContext.request.contextPath}" />
<c:set var="ctx" value='<%=request.getRealPath("/")%>' />
