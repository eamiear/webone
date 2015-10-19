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
                <h3 class="title page-header">通过标签启用BootstrapTable</h3>
            </header>
            <section class="descripiton">
                 <p>
                     <span class="label label-default">@description: </span>
                     <span class="label wet-asphalt">通过标签启用BootstrapTable</span>
                 </p>
                 <p>
                    <span class="label label-default">@author: </span>
                     <a class="label" style="color: #777;" href="javascript:;">skz</a>
                 </p>
            </section>
        </blockquote>
        <div class="well">
        	<table id="bootstrapTable"
				data-url="../wbookmsg/get_book_list"
				data-toggle="table" data-side-pagination="server"
				data-pagination="true" data-page-list="[10,20,30,40,50]"
				style="padding: 5px;" data-toolbar="#custom-toolbar"
				data-height="300">
				<thead>
					<tr>
						<th data-field="" data-checkbox="true"></th>
						<th data-formatter="NumberFormater">编号</th>
						<th data-field="id" data-visible="false">ID</th>
						<th data-field="name">书名</th>
						<th data-field="publisher" >出版社</th>
						<th data-field="publishTime" data-formatter="dateFormatter">出版时间</th>
						<th data-field="introduce" data-formatter="contentFormater">图书简介</th>
						<th data-field="bookType">图书类型</th>
						<th data-field="count">页码数</th>
						<th data-formatter="optFormater" data-events="optEvents">操作</th>
					</tr>
				</thead>
			</table>
			
			<blockquote class="alert alert-danger">
        	 	前端HTML代码
        	</blockquote>
        	<button class="btn btn-primary" data-toggle="modal" data-target="#viewCode">查看HTML代码</button><br/><br/>
        	
        	<blockquote class="alert alert-danger">
        	 	前端JS代码
        	</blockquote>
        	<button class="btn btn-primary" data-toggle="modal" data-target="#viewCode1">查看JS代码</button><br/><br/>
        </div>
    </div>
</div>



<!-- modal  集合 -->

<!--标签驱动bsTable -->
<div class="modal fade" id="viewCode" style="z-index:1111111111111;">
	  <div class="modal-dialog modal-lg">
	    <div class="modal-content">
	      <div class="modal-body" >
	       	<textarea class="unexplain" readonly id="load-bs-js1"></textarea>
	      	<script>
	      		$("#load-bs-js1").load(contextPath+"/resources/html/bstable/bytag/tag.html");
	      	</script>
	      </div>
	    </div><!-- /.modal-content -->
	  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<div class="modal fade" id="viewCode1" style="z-index:1111111111111;">
	  <div class="modal-dialog modal-lg">
	    <div class="modal-content">
	      <div class="modal-body" >
	       	<textarea class="unexplain" readonly id="load-bs-js2"></textarea>
	      	<script>
	      		$("#load-bs-js2").load(contextPath+"/resources/js/bstable/bootstrap-table-tag.js");
	      	</script>
	      </div>
	    </div><!-- /.modal-content -->
	  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<script src="${pageContext.request.contextPath }/resources/libs/bootstrap-table-master/dist/bootstrap-table.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/libs/bootstrap-table-master/dist/locale/bootstrap-table-zh-CN.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/bstable/bootstrap-table-tag.js?x=1"></script>
</body>
</html>