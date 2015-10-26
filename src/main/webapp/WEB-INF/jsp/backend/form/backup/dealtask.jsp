<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<!-- bootstrap 样式 -->
    <link rel="stylesheet" href="${pageContext.request.contextPath }/resources/libs/bootstrap/dist/css/bootstrap.min.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/content/resources/plugin/jquery/jquery.form.js"></script>
<%
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
</script>
<script type="text/javascript" src="${pageContext.request.contextPath}/content/js/workflow/bootstrap/dealtask.js"></script>
<style>
html,
body {
  padding: 0;
  margin: 0;
}
body {
  -webkit-font-smoothing: antialiased;
  font: 18px / 1.4 'Microsoft YaHei','Helvetica', 'Arial', sans-serif;
  overflow-x:hidden;
}

.ui-layout-center{
    width: 98%;
    margin: 5px auto;
 }
 .form-horizontal .form-group,
 .row {
    margin-right: 0;
    margin-left: 0;
}
 label,.form-group {
    margin-bottom: 0;
}
.control-label{
	font-size: 11px;
    color: #555;
}
input.form-control,
textarea.form-control,
.txt{
	font-size: 10px;
    color: #999;
    letter-spacing: .5px;
    padding:0;
    text-indent:13px;
    border:1px solid transparent;
}
textarea.txt{
	width:100%;
	border:1px solid #eee;
	min-height: 60px;
}
textarea:focus,
.form-control:focus{
	border:1px solid #eee;
	outline: 0;
	-webkit-box-shadow: none;
	box-shadow:none;
	box-image:none;
}

div.txt{
	/* padding-top: 7px; */
}

.form-control[disabled], .form-control[readonly], fieldset[disabled] .form-control {
	background:#FFF;
}

@media (min-width:768px){
	div.txt{
	 padding-top: 7px; 
	}
	input.form-control,
	textarea.form-control,
	.txt{
	    padding:0 12px;
	    text-indent:0;
	}
}

</style>
</head>
<body>
	<div class="ui-layout-center">
		<form class="form-horizontal" id='taskInfoForm'>
			<div class="form-group">
				<label class="col-sm-4 control-label">报修单编码:</label>
				<div class="col-sm-6">
					<div class="txt" id="orderId"></div>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-4 control-label">报修日期:</label>
				<div class="col-sm-6">
					<div class="txt" id="orderDate"></div>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-4 control-label">报修设备:</label>
				<div class="col-sm-6">
					<div class="txt" id="deviceName" ></div>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-4 control-label">报修原因:</label>
				<div class="col-sm-6">
					<div class="txt"></div>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-4 control-label">备注:</label>
				<div class="col-sm-6">
					<div class="txt" id="mark"></div>
				</div>
			</div>
		</form>
		<form class="form-horizontal" id='taskdealForm'>
			<div class="form-group">
				<label class="col-sm-4 control-label">设备维修详情:</label>
				<div class="col-sm-6">
					<textarea class="txt" rows="" cols="" id="detailRemark"></textarea>
					<!-- <input type="text" class="form-control" id="detailRemark"> -->
				</div>
			</div>
		</form>
		<form id="formupload" class="form-horizontal" method="post" enctype="multipart/form-data" onsubmit="return false">
			<!-- accept="image/*;capture=camera" -->
			<div class="form-group">
				<div class="col-sm-offset-4 col-sm-5">
					<input type="file" class="form-control" id="uploadFile"
						name="uploadFile" /> <input type="hidden" class="form-control"
						id="uploadFileName" name="uploadFileName" />
					<input type="hidden" class="form-control"
						id="uploadFilePath" name="uploadFilePath" />
				</div>
				<div class="col-sm-1">
					<button type="submit" class="btn btn-default" id="saveupload">上传</button>
				</div>
			</div>
		</form>
		<div class="row">
				<label class="col-sm-4 control-label"></label>
				<div class="col-sm-6">
					<button type="button" class="btn btn-default" id="finishTaskBtn">完成任务</button>
				</div>
			</div>
	</div>
</body>
</html>