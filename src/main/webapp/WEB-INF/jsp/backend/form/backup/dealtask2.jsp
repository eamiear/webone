<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<title>Insert title here</title>
<!-- bootstrap 样式 -->
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/libs/bootstrap/dist/css/bootstrap.min.css"/>
<%-- <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/content/themes/blue/bootstrap/form.css" /> --%>
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/temp/form.css"/>
<%-- <script type="text/javascript" src="${pageContext.request.contextPath}/content/resources/plugin/jquery/jquery.form.js"></script> --%>
<%-- <%
	String taskId = request.getParameter("taskId");
	String processInstanceId = request
			.getParameter("processInstanceId");
	String processDefinitionId = request
			.getParameter("processDefinitionId");
%>
<script type="text/javascript">
	globalTaskId = '<%=taskId%>';
	globalProcessInstanceId = '<%=processInstanceId%>';
	globalProcessDefinitionId = '<%=processDefinitionId%>';
</script> --%>
<%-- <script type="text/javascript" src="${pageContext.request.contextPath}/content/js/workflow/bootstrap/dealtask.js"></script> --%>
</head>
<body>
<div id="section_container">
<section class="active">
	<article class="active">
		<div class="page_content">
			<form class="form-horizontal" id='taskInfoForm'>
				<div class="form-group">
					<label class="col-sm-4 control-label">报修单编码</label>
					<div class="col-sm-6">
						<div class="form-control txt" id="orderId">L20151020MGAED</div>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-4 control-label">报修日期</label>
					<div class="col-sm-6">
						<div class="form-control txt" id="orderDate">2015-10-25 12:12:44</div>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-4 control-label">报修设备</label>
					<div class="col-sm-6">
						<div class="form-control txt" id="deviceName" >VD3.2</div>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-4 control-label">报修原因</label>
					<div class="col-sm-6">
						<div class="form-control txt" id="reason" >E--Error</div>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-4 control-label">备注</label>
					<div class="col-sm-6">
						<div class="form-control txt nb-line" id="mark">说点什么...</div>
					</div>
				</div>
			</form>
			<form class="form-horizontal" id='taskdealForm'>
				<div class="form-group">
					<label class="col-sm-4 control-label">设备维修详情</label>
					<div class="col-sm-6">
						<textarea class="form-control txt" rows="" cols="" id="detailRemark"></textarea>
						<!-- <input type="text" class="form-control" id="detailRemark"> -->
					</div>
				</div>
			</form>
			<form id="formupload" class="form-horizontal" method="post" enctype="multipart/form-data" onsubmit="return false">
				<!-- accept="image/*;capture=camera" -->
				<div class="form-group">
					<label class="col-sm-4 control-label">照片</label>
					<div class="col-sm-6">
						
						<a href="javascript:;" class="file">
							选择文件
							<img id="imgId"/>
							<input type="file"  id="uploadFile" name="uploadFile" accept="image/*;capture=camera" onchange="previewImage(this,'imgId')" /> 
						</a>
						<input type="hidden" class="form-control" id="uploadFileName" name="uploadFileName" />
						<input type="hidden" class="form-control" id="uploadFilePath" name="uploadFilePath" />
					</div>
					<div class="col-sm-10 upload-btn">
						<button type="submit" class="btn btn-default" id="saveupload">上传</button>
					</div>
				</div>
			</form>
		</div>
		<button type="button" class="btn btn-primary full-btn" id="finishTaskBtn">完成任务</button>
	</article>
</section>
</div>
<script src="${pageContext.request.contextPath }/resources/libs/jquery/dist/jquery.min.js"></script>
<script>

/**
 * 预览图片
 * @param elem	//当前点击事件对象
 * @param target	//显示图片的ID
 */
function previewImage(elem,target){
	target = target.indexOf("#") == -1 ? '#'+target : target;
	var $img = $(target);
	if (elem.files && elem.files[0]) {
		var reader = new FileReader();
		reader.onload = function(evt) {
			$img.attr('src', evt.target.result);
		}
		reader.readAsDataURL(elem.files[0]);
	}
}
</script>
</body>
</html>