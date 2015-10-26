<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>使用说明</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/backend/form/upload/jquery-upload.css?x=1.1"/>
    <script type="text/javascript">
		var contextPath = "${pageContext.request.contextPath}";
	</script>
</head>
<body>
<div>
    <!-- 面包屑导航 -->
    <ol class="breadcrumb">
        <li class="">首页</li>
        <li class="active">使用说明</li>
    </ol>
    <div class="page_content">
        <blockquote class="alert alert-info">
            <header style="border-bottom: 1px solid #bbb;">
                <h3 class="title page-header">关于此前端模板的使用介绍</h3>
            </header>
            <section class="descripiton">
                 <p>
                     <span class="label label-default">@description: </span>
                     <span class="label wet-asphalt">头部导航添加菜单项方式,右侧菜单添加列表项方式</span>
                 </p>
                 <p>
                    <span class="label label-default">@author: </span>
                    <a class="label" style="color: #777;" href="javascript:;">skz</a>
                 </p>
            </section>
        </blockquote>
        <div class="well">
        	<h6 class="alert alert-danger">相关文档</h6>
	        <p class="unexplain">
	        	<a class="white-bold" href="https://github.com/blueimp/jQuery-File-Upload/wiki">https://github.com/blueimp/jQuery-File-Upload/wiki</a><br>
	        	<a class="white-bold" href="https://github.com/blueimp/jQuery-File-Upload/wiki/Options">https://github.com/blueimp/jQuery-File-Upload/wiki/Options</a><br>
	        	<a class="white-bold" href="https://github.com/blueimp/jQuery-File-Upload/wiki/Basic-plugin">https://github.com/blueimp/jQuery-File-Upload/wiki/Basic-plugin</a>
	        </p>
        </div>
        
		<div class="well">
			<div style="width: 500px; padding: 20px">
				<input id="fileupload" type="file" name="files[]"
					data-url="../backhandler/upload" multiple>

				<div id="dropzone" class="dropzone fade well">Drop files here</div>

				<div id="progress" class="progressbar  fileupload-progressbar">
					<div class="bar" style="width: 0%;"></div>
				</div>

				<table id="uploaded-files" class="table table-bordered table-striped">
					<tr>
						<th>File Name</th>
						<th>File Size</th>
						<th>File Type</th>
						<th>Download</th>
					</tr>
				</table>
			</div>
		</div>
		
		<div class="well">
				<form action="../" method="post" enctype="multipart/form-data">
					<input type="file" name="file"> <br> 
					<input type="file" name="file"> <br> 
					<input type="file" name="file" />
					<input type="submit" value="提交">
				</form>
			</div>
	</div>
</div>
</body>
<script src="${pageContext.request.contextPath }/resources/libs/jquery/dist/jquery.min.js"></script>
<script src="${pageContext.request.contextPath }/resources/libs/bootstrap/dist/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath }/resources/libs/jquery-ui-1.10.3/ui/jquery.ui.widget.js"></script>
<script src="${pageContext.request.contextPath }/resources/libs/jQuery-File-Upload-master/js/jquery.iframe-transport.js"></script>
<script src="${pageContext.request.contextPath }/resources/libs/jQuery-File-Upload-master/js/jquery.fileupload.js"></script>
<script src="${pageContext.request.contextPath }/resources/js/backend/form/upload/jquery-upload.js"></script>
</html>