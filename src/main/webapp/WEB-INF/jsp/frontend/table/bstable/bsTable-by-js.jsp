<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>bsTable</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/libs/bootstrap-table-master/dist/bootstrap-table.min.css"/>
<script type="text/javascript">
	var contextPath = "${pageContext.request.contextPath}";
</script>
</head>
<body>
<div>
    <!-- 面包屑导航 -->
    <ol class="breadcrumb">
        <li class="">首页</li>
        <li class="active">BootstrapTable</li>
    </ol>
    <div class="page_content">
        <blockquote class="alert alert-info">
            <header style="border-bottom: 1px solid #bbb;">
                <h3 class="title page-header">通过JavaScript启用BootstrapTable</h3>
            </header>
            <section class="descripiton">
                 <p>
                     <span class="label label-default">@description: </span>
                     <span class="label wet-asphalt">通过JavaScript启用BootstrapTable</span>
                 </p>
                 <p>
                    <span class="label label-default">@author: </span>
                     <a class="label" style="color: #777;" href="javascript:;">skz</a>
                 </p>
            </section>
        </blockquote>
        <div class="">
        	<table id="bootstrapTable"></table>
        </div>
        <div class="well">
        	 <blockquote class="alert alert-info">
        	 	前端HTML代码
        	 </blockquote>
        	 <button class="btn btn-primary" data-toggle="modal" data-target="#viewCode0">查看引入文件代碼</button><br/><br/>
        	 
			 <blockquote class="alert alert-info">
        	 	前端JS代码
        	 </blockquote>
        	 <button class="btn btn-primary" data-toggle="modal" data-target="#viewCode">查看BoostrapTable 公共默认js代码</button><br/><br/>
        	 <button class="btn btn-primary" data-toggle="modal" data-target="#viewCode2">查看BoostrapTable js代码</button><br/><br/>
        	 
        	 <blockquote class="alert alert-info">
        	 	后台代码
        	 </blockquote>
        	 <button class="btn btn-primary" data-toggle="modal" data-target="#viewCode3">查看后台代码</button>
        </div>
    </div>
</div><!-- /page_content -->




<!-- modal  集合 -->

<div class="modal fade" id="viewCode0" style="z-index:1111111111111;">
	  <div class="modal-dialog modal-lg">
	    <div class="modal-content">
	      <div class="modal-body" >
	       	<textarea class="unexplain" readonly id="load-bs-js0"></textarea>
	      	<script>
	      		$("#load-bs-js0").load(contextPath+"/resources/html/bstable/byjs/import.html");
	      	</script>
	      </div>
	    </div><!-- /.modal-content -->
	  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<!-- boostrapTable 公共默认js代码 -->
<div class="modal fade" id="viewCode" style="z-index:1111111111111;">
	  <div class="modal-dialog modal-lg">
	    <div class="modal-content">
	      <div class="modal-body" >
	       	<pre id="load-bs-js1"></pre>
	      	<script>
	      		$("#load-bs-js1").load(contextPath+"/resources/js/common/bootstrapTable.js");
	      	</script>
	      </div>
	    </div><!-- /.modal-content -->
	  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<!-- boostrapTable 初始化js代码 -->
<div class="modal fade" id="viewCode2" style="z-index:1111111111111;">
	  <div class="modal-dialog modal-lg">
	    <div class="modal-content">
	      <div class="modal-body" >
	       	<textarea class="unexplain" readonly id="load-bs-js2"></textarea>
	      	<script>
	      		$("#load-bs-js2").load(contextPath+"/resources/js/bstable/bootstrap-table.js");
	      	</script>
	      </div>
	    </div><!-- /.modal-content -->
	  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<script src="${pageContext.request.contextPath }/resources/libs/bootstrap-table-master/dist/bootstrap-table.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/libs/bootstrap-table-master/dist/locale/bootstrap-table-zh-CN.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/common/bootstrapTable.js?x=1.0"></script>
<script src="${pageContext.request.contextPath}/resources/js/bstable/bootstrap-table.js?x=1.0"></script>
<script>
	$(".code").laycode({
		 height: 'auto', //高度
		 skin: 1   //皮肤
	});
</script>
</body>
</html>