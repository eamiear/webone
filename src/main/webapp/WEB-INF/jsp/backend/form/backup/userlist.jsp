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
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/libs/bootstrap-table-master/dist/bootstrap-table.min.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/temp/form.css"/>
<script src="${pageContext.request.contextPath }/resources/libs/jquery/dist/jquery.min.js"></script>
<script src="${pageContext.request.contextPath }/resources/libs/bootstrap-table-master/dist/bootstrap-table.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/libs/bootstrap-table-master/dist/locale/bootstrap-table-zh-CN.min.js"></script>
<%-- <script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/temp/userlist.js"></script> --%>
<script >
	contextPath =  "${pageContext.request.contextPath}";
</script>
</head>
<body>
	<div class="ui-layout-center" id="centerDiv">
		<div class="panel panel-default">
			<div class="panel-heading">选择用户</div>
			<!-- 表格 -->
			<table id="bootstrapTable" class="table table-bordered"
				data-pagination="true" data-toolbar="#custom-toolbar"
				data-side-pagination="client" data-height="400" data-search="true">
				<thead>
					<tr>
						<th data-field="accountName">用户名称</th>
						<th data-field="orgName">所属部门</th>
					</tr>
				</thead>
			</table>
		</div>
	</div>
</body>
<script src="${pageContext.request.contextPath}/resources/js/common/bootstrapTable.js"></script>
<script>
createBootstrapTable('bootstrapTable', 300, [], contextPath + '/resources/js/temp/userList.json', {sidePagination : 'client'});
</script>
</html>