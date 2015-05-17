<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../../common/common.jsp"%>
<%@ include file="../../common/common_html_validator.jsp"%>
<script type="text/javascript" src="../../../../ueditor/editor_config.js"></script>
<script type="text/javascript" src="../../../../ueditor/editor_all.js"></script>
<title>后台添加视频</title>
<style>
	.td_right{text-align: right;}
</style>

</head>

<body>

	<form action="post" namespace="/" theme="simple" id="form" method="post">
		<table class="table table-bordered">
			<tr>
				<td colspan="2" style="background-color: #dff0d8;text-align: center;">
					<c:choose>
					  <c:when test="${id == null || id == '' }">
					    <strong>视频新增</strong>
					  </c:when>
					  <c:otherwise>
					    <strong>视频编辑</strong>
					  </c:otherwise>
					</c:choose>
					
				</td>
			</tr>
			<tr style="display:none;">
				<th>id</th>
				<td><input type="hidden" name="id" value="${id }" /></td>
			</tr>
			<tr>
				<th class="td_right">视频名称</th>
				<td style="text-align: left;">
					<input type="text" name="name" id="name" 
					  <c:if test="${id != null && id != '' }">
					    readonly="false" 
					  </c:if>		
					  value="${video.name }"		
					/>
				</td>
			</tr>
			
			<tr>
				<th class="td_right">视频分类</th>
				<td style="text-align: left;">
					<select id="videoTypeId" name="videoTypeId" data-rule="视频分类:required;videoTypeId;" >
					  <option value="">--请选择--</option>
					  <c:forEach items="${videoTypeList }" var="r">
					    <option value="${r.id }" 
					      <c:if test="${r.id == video.videoTypeId }">selected="true"</c:if>
					     >${r.name }
					     </option>
					  </c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<th class="td_right">年级</th>
				<td style="text-align: left;">
					<select id="gradeId" name="gradeId" data-rule-required="true" data-msg-required="请选择年级" >
					  <option value="">--请选择--</option>
					  <option value="">初一</option>
					  <option value="">初二</option>
					</select>
				</td>
			</tr>
			<tr>
				<th class="td_right">科目</th>
				<td style="text-align: left;">
					<select id="topCourseId" name="topCourseId" data-rule-required="true" data-msg-required="请选择科目" >
					  <option value="">--请选择--</option>
					  <option value="">语文</option>
					  <option value="">数学</option>
					  <option value="">英语</option>
					  <option value="">化学</option>
					  <option value="">物理</option>
					</select>
				</td>
			</tr>
			<tr>
				<th class="td_right">老师</th>
				<td style="text-align: left;">
					<select id="lecturerId" name="lecturerId" data-rule-required="true" data-msg-required="请选择老师" >
					  <option value="">--请选择--</option>
					  <option value="">谢老师</option>
					  <option value="">陈老师</option>
					</select>
				</td>
			</tr>
			
			<tr>
				<th class="td_right">选择视频</th>
				<td style="text-align: left;">
				<input type="text" name="videoYunId" id="videoYunId" value="${user.videoYunId }"/>
				<button onclick="chooseVideoYun()" class="btn btn-success">
					<i class="icon-ok icon-white"></i> 选择视频
				</button>
				</td>
			</tr>
			<%-- <tr>
				<th class="td_right">视频图片</th>
				<td style="text-align: left;">
				<input type="text" name="realName" id="realName" value="${user.realName }" data-rule="视频图片:required;realName;length[2~20];"/>
				<input type="file" name="file" id="file"  class="easyui-validatebox" />
				</td>
			</tr> --%>
			<tr>
				<th class="td_right">视频时长（分钟）</th>
				<td style="text-align: left;">
				<input type="text" name="duration" id="duration" value="${video.duration }" data-rule="视频时长:required;duration;"/>
				</td>
			</tr>
			<tr>
				<th class="td_right">视频介绍</th>
				<td style="text-align: left;">
				<script type="text/plain" id="introduction" name="introduction" style="width:400px" ></script>
				</td>
			</tr>
			<tr>
				<th class="td_right">视频排序</th>
				<td style="text-align: left;">
				<input type="text" name="sorting" id="sorting" value="${video.sorting }" data-rule="视频排序:required;duration;"/>
				</td>
			</tr>
			<tr>
				<th class="td_right">视频标签</th>
				<td style="text-align: left;">
				<input type="text" name="tag" id="tag" value="${video.tag }" />
				</td>
			</tr>
			
			<tr >
				<td colspan="2" style="text-align: center;">
				   <c:choose>
				     <c:when test="${id == null || id == '' }">
				        <button method="save" class="btn btn-success">
							<i class="icon-ok icon-white"></i> 新增
						</button>
				     </c:when>
				     <c:otherwise>
				        <button method="save" class="btn btn-success">
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
	
	
	<!-- 模态框（Modal） -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog" 
	   	aria-labelledby="myModalLabel" aria-hidden="true">
	   <div class="modal-dialog">
	      <div class="modal-content">
	         <div class="modal-header">
	            <button type="button" class="close" 
	               data-dismiss="modal" aria-hidden="true">
	                  &times;
	            </button>
	            <h4 class="modal-title" id="myModalLabel">
	              	视频云列表
	            </h4>
	         </div>
	         <div class="modal-body">
	           	<table class="table table-bordered table-hover">
					<thead>
						<tr style="background-color: #dff0d8">
							<th width="20"><input type="checkbox" id="firstCheckbox"/></th>
							<th>编号</th>
							<th>名称</th>
							<th>视频VID</th>
							<th>状态</th>
							<th>添加人</th>
							<th>添加时间</th>
						</tr>
					</thead>
					<c:forEach items="${page.list }" var="u">
						<tr >
						   <td><input type="checkbox" name="ids" value="${u.id }"/></td>
						   <td>&nbsp;${u.id }</td>
						   <td>&nbsp;${u.name }</td>
						   <td>&nbsp;${u.vid }</td>
						   <td>&nbsp;${u.deleteFlag }</td>
						   <td>&nbsp;${u.createId }</td>
						   <td>&nbsp;${u.createDate }</td>
						   <td>
						      <a href="localhost:8080/px_management_web/boss/product/video/edit?id=${u.id}">编辑</a>
						   </td>
					    </tr>
					</c:forEach>
					<tr><td colspan="16" style="text-align:center;"><%@ include file="../../common/pager.jsp"%></td>
					</tr>
				</table>
	         </div>
	         <div class="modal-footer">
	            <button type="button" class="btn btn-default" 
	               data-dismiss="modal">关闭
	            </button>
	            <button type="button" class="btn btn-primary">
	               	确认
	            </button>
	         </div>
	      </div><!-- /.modal-content -->
	</div><!-- /.modal -->
</body>
<script type="text/javascript">
function chooseVideoYun(){
	
}
</script>
</html>
