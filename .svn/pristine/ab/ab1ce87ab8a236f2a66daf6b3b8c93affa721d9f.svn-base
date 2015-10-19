<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>bootstrapTable</title>
<meta charset="utf-8">
<meta name="keywords" content="zend,springmvc,前端,后台"/>
<meta name="description" content="内容总结"/>
<meta http-equiv="X-UA-Compatible" content="IE=9;IE=8;IE=EDGE;chrome=1" />
<meta http-equiv="X-UA-Compatible" content="IE=8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />

<!-- support responsive for IE before 8  -->
<!--[if lte IE 8]>
	<script src="${pageContext.request.contextPath }/resources/assets/responsive/html5shiv.min.js"></script>
	<script src="${pageContext.request.contextPath }/resources/assets/responsive/respond.min.js"></script>
<![endif]-->


<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/common/reset.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/assets/bootstrap-3.3.2-dist/css/bootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/assets/font-awesome-4.3.0/css/font-awesome.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/assets/bootstrap-table-master/dist/bootstrap-table.min.css"/>
<%-- <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/assets/bootstrapTable/bootstrap-table.min.css"/> --%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap-table/bootstrap-table.css?x=1"/>

<script type="text/javascript">
	var contextPath = "${pageContext.request.contextPath}";
</script>
</head>

<body>
	<div class="container">
		<div class="row">
			<div class="desc">
				<header class="title">通过JavaScript启用BootstrapTable</header>
			</div>
			<table id="bootstrapTable"></table>
		</div>
		
		<div class="row">
			<div class="desc">
				<header class="title">
					<p>在标签中驱动BootstrapTable</p>
				</header>
			</div>
			<table id="bootstrapTable2"
				data-url="../pagination/get_book_list"
				data-toggle="table" data-side-pagination="server"
				data-pagination="true" data-page-list="[10,20,30,40,50]"
				data-toolbar="#custom-toolbar" style="padding: 5px;"
				data-height="300">
				<thead>
					<tr>
						<th data-field="" data-checkbox="true"></th>
						<th data-formatter="NumberFormater">编号</th>
						<th data-field="id" data-visible="false">ID</th>
						<th data-field="name">书名</th>
						<th data-field="publisher" >出版社</th>
						<th data-field="publishTime" data-formatter="">出版时间</th>
						<th data-field="introduce" data-formatter="contentFormater">图书简介</th>
						<th data-field="bookType">图书类型</th>
						<th data-field="count">页码数</th>
						<th data-formatter="optFormater" data-events="optEvents">操作</th>
					</tr>
				</thead>
			</table>
		</div>
		
		<div class="row">
			<div class="desc">
				<header class="title">
					<p>标签和js结合使用驱动BootstrapTable</p>
				</header>
			</div>
			<table id="bootstrapTable3">
				<thead>
					<tr>
						<th data-field="" data-checkbox="true"></th>
						<th data-formatter="NumberFormater">编号</th>
						<th data-field="id" data-visible="false">ID</th>
						<th data-field="name">书名</th>
						<th data-field="publisher" >出版社</th>
						<th data-field="publishTime" data-formatter="">出版时间</th>
						<th data-field="introduce" data-formatter="contentFormater">图书简介</th>
						<th data-field="bookType">图书类型</th>
						<th data-field="count">页码数</th>
						<th data-formatter="optFormater" data-events="optEvents">操作</th>
					</tr>
				</thead>
			</table>
		</div>
	</div>
</body>
<script src="${pageContext.request.contextPath}/resources/assets/jquery/jquery-1.11.2.min.js"></script>
<script src="${pageContext.request.contextPath }/resources/assets/bootstrap-3.3.2-dist/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath }/resources/assets/bootstrap-table-master/dist/bootstrap-table.min.js"></script>
<%-- <script src="${pageContext.request.contextPath }/resources/assets/bootstrapTable/bootstrap-table.min.js"></script> --%>
<script src="${pageContext.request.contextPath}/resources/assets/bootstrap-table-master/dist/locale/bootstrap-table-zh-CN.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/common/bootstrapTable.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/bootstrap-table/bootstrap-table.js?x=1"></script>
<script src="${pageContext.request.contextPath}/resources/js/bootstrap-table/bootstrap-table-tag.js?x=1"></script>
<script src="${pageContext.request.contextPath}/resources/js/bootstrap-table/bootstrap-table-js-tag.js?x=1"></script>
</html>