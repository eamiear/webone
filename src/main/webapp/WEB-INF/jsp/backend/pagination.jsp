<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>bootstrapTable</title>
<meta charset="utf-8"/>
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
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap-table/bootstrap-table.css?x=1.0"/>

<script type="text/javascript">
	var contextPath = "${pageContext.request.contextPath}";
</script>
</head>

<body>
	<div class="container">
		<div class="row">
			<div class="desc">
				<header class="title">
					<p>使用spring提供PageRequest，Page，Pageable进行分页</p>
					<p>通过Jpa提供的查询方法进行分页查询如findAll(Pageable pageable)</p>
				</header>
			</div>
			<table id="bootstrapTable"></table>
		</div>
		
		<div class="row">
			<div class="desc">
				<header class="title">
					<p>使用spring提供PageRequest，Page，Pageable进行分页</p>
					<p>通过Jpa提供的查询方法进行分页查询如findAll(Pageable pageable)</p>
				</header>
			</div>
			<div class="">
				
			</div>
		</div>
		
	</div>
</body>
<script src="${pageContext.request.contextPath}/resources/assets/jquery/jquery-1.11.2.min.js"></script>
<script src="${pageContext.request.contextPath }/resources/assets/bootstrap-3.3.2-dist/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath }/resources/assets/bootstrap-table-master/dist/bootstrap-table.min.js"></script>
<%-- <script src="${pageContext.request.contextPath }/resources/assets/bootstrapTable/bootstrap-table.min.js"></script> --%>
<script src="${pageContext.request.contextPath}/resources/assets/bootstrap-table-master/dist/locale/bootstrap-table-zh-CN.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/common/bootstrapTable.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/pagination/bootstrap-table.js?x=1"></script>
</html>