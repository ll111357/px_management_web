<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>后台管理系统头部分</title>

  </head>
  
  <body>
    <h3>品学网管理系统</h3>
    <div style="float:right">
      		<font color="red">${sessionScope.user_session_info.username }</font>
      		欢迎你! | <a href="#" onclick="loginOut()">注销</a> | <a target="_blank" href="#">门户</a>
    </div>
     <script type="text/javascript">
    	//注销
    	function loginOut(){
    		window.parent.location.href = "${request.getContextPath }/boss/loginOut";
    	}
     </script>  
  </body>
</html>
