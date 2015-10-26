<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<title>Insert title here</title>
<!-- bootstrap 样式 -->
<%
	String taskId = request.getParameter("taskId");
	String processInstanceId = request
			.getParameter("processInstanceId");
	String processDefinitionId = request
			.getParameter("processDefinitionId");
	String path = request.getContextPath();
%>
<script type="text/javascript">
	globalTaskId = '<%=taskId%>';
	globalProcessInstanceId = '<%=processInstanceId%>';
	globalProcessDefinitionId = '<%=processDefinitionId%>';
	contextPath = '<%=path%>';
</script>
 <link rel="stylesheet" href="${pageContext.request.contextPath }/resources/libs/bootstrap/dist/css/bootstrap.min.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/content/themes/blue/bootstrap/form.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/temp/form.css"/>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/content/js/workflow/bootstrap/dealtask.js"></script>
</head>
<body>
<div id="section_container">
<section class="active">
	<article class="active">
		<div class="page_content">
			<div class="ui-layout-center">
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
							<div class="form-control txt" id="reason">坏了坏了</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-4 control-label">备注</label>
						<div class="col-sm-6">
							<div class="form-control txt nb-line" id="mark">说点什么...</div>
						</div>
					</div>
				</form>
				<form class="form-horizontal taskdealForm" id='taskdealForm'>
					<div class="form-group">
						<label class="col-sm-4 control-label">设备维修详情</label>
						<div class="col-sm-6">
							<div class="form-control txt" id="maintainDetial">修不好了</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-4 control-label">图片信息</label>
						<div class="col-sm-6">
							<img id="imgId" width="100" height="60"/>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-4 control-label">审批结果</label>
						<div class="col-sm-6 select-c">
							<select id="taskResultId" name="taskResult"
								class="form-control cateType">
								<option value="">-----</option>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-4 control-label">审核意见</label>
						<div class="col-sm-6">
							<textarea class="form-control txt" rows="" id="detailRemark">说点什么...</textarea>
						</div>
					</div>
				</form>
				<!-- <div class="form-group">
						<div class="">
							<button type="submit" class="btn btn-default full-btn" id="finishTaskBtn">完成任务</button>
						</div>
					</div> -->
			</div>
		</div>
		<button type="submit" class="btn btn-primary full-btn" id="finishTaskBtn">完成任务</button>
	</article>
</section>
</div>
</body>
</html>