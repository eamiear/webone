<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Simditor</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/libs/simditor/styles/simditor.css" />
</head>
<body>
<div>
    <!-- 面包屑导航 -->
    <ol class="breadcrumb">
        <li class="">首页</li>
        <li class="active">Simditor</li>
    </ol>
    <div class="page_content">
        <blockquote class="alert alert-info">
            <header style="border-bottom: 1px solid #bbb;">
                <h3 class="title page-header">Simditor使用介绍</h3>
            </header>
            <section class="descripiton">
                 <p>
                     <span class="">@description: </span>
                     <span class="">Simditor</span>
                 </p>
                 <p>
                    <span class=" ">@author: </span>
                     <a class="" style="color: #777;" href="javascript:;">skz</a>
                 </p>
            </section>
        </blockquote>
        <textarea id="editor"  autofocus></textarea>
        <div class="well">
        	<a href="http://simditor.tower.im/docs/doc-usage.html" target="_blank">simditor官网</a>
        </div>
    </div>
</div>
<script src="${pageContext.request.contextPath }/resources/libs/simditor/scripts/module.js"></script>
<script src="${pageContext.request.contextPath }/resources/libs/simditor/scripts/hotkeys.js"></script>
<script src="${pageContext.request.contextPath }/resources/libs/simditor/scripts/uploader.js"></script>
<script src="${pageContext.request.contextPath }/resources/libs/simditor/scripts/simditor.js"></script>
<script src="${pageContext.request.contextPath }/resources/js/form/editor/simditor.js"></script>
</body>
</html>