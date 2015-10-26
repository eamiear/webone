<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/libs/bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/libs/btun/css/base.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/libs/btun/css/Btun.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/temp/style.css" />
<script type="text/javascript">
	var contextPath = "${pageContext.request.contextPath}";
</script>
</head>

<body>
<div id="section_container">
    <section class="active">
    
        <article id="pie" class="active article" >
        	<div class="chart-c">
	        	<span class="chart" data-percent="86">
				 <span class="percent"></span>
				 <span class="total" id="total">10000</span>
			   </span>
        	</div>
           

	       <div class="items rate">
	       		<div class="items-title">设备诊断状态统计</div>
	       		<ul>
	       			<li>
	       				<div>
	       					<i class="glyphicon glyphicon-heart"></i><span id="normal">3233</span>
	       				</div>
	       				<div class="desc">正常</div>
	       			</li>
	       			<li>
	       				<div>
	       					<i class="glyphicon glyphicon-heart"></i><span id="warn">3234</span>
	       				</div>
	       				<div class="desc">警告</div>
	       			</li>
	       			<li>
	       				<div>
	       					<i class="glyphicon glyphicon-heart"></i><span id="abnormal">3234</span>
	       				</div>
	       				<div class="desc">异常</div>
	       			</li>
	       			<li>
	       				<div>
	       					<i class="glyphicon glyphicon-heart"></i><span id="detectionFailure">3234</span>
	       				</div>
	       				<div class="desc">检测失败</div>
	       			</li>
	       		</ul>
	       </div>
	      <div class="items items-sm">
	      		<div class="items-title">视频异常统计</div>
	       		<ul>
	       			<li>
	       				<div><span id="dark">33</span></div>
	       				<div class="desc ">偏暗</div>
	       			</li>
	       			<li>
	       				<div><span id="partialLight">3</span></div>
	       				<div class="desc ">偏亮</div>
	       			</li>
	       			<li>
	       				<div><span id="partialColor">34</span></div>
	       				<div class="desc">偏色</div>
	       			</li>
	       			<li>
	       				<div><span id="snow">34</span></div>
	       				<div class="desc">雪花</div>
	       			</li>
	       			<!-- <li><i class="glyphicon glyphicon-heart"></i><span class="desc"></span></li> -->
	       		</ul>
	       		<ul>
	       			<li>
	       				<div><span id="fuzzy">4</span></div>
	       				<div class="desc ">模糊</div>
	       			</li>
	       			<li>
	       				<div><span id="definition">3</span></div>
	       				<div class="desc">清晰度</div>
	       			</li>
	       			<li>
	       				<div><span id="fringe">34</span></div>
	       				<div class="desc">条纹</div>
	       			</li>
	       			<li>
	       				<div><span id="forzen">34</span></div>
	       				<div class="desc">冻结</div>
	       			</li>
	       		</ul>
	       		<ul>
	       			<li>
	       				<div><span id="shake">34</span></div>
	       				<div class="desc">抖动</div>
	       			</li>
	       			<li>
	       				<div><span id="occlusion">34</span></div>
	       				<div class="desc">遮挡</div>
	       			</li>
	       			<li class="">
	       				<div><span id="signalLoss">34</span></div>
	       				<div class="desc">信号丢失</div>
	       			</li>
	       			<li class="">
	       				
	       			</li>
	       		</ul>
	       </div>
        </article>
    </section>
</div>
<script src="${pageContext.request.contextPath}/resources/libs/jquery/jquery-1.11.2.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/libs/easy-pie-chart/dist/jquery.easypiechart.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/libs/jquery-easing/jquery.easing.1.3.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/webapp/chart/easypie/app.js"></script>

</body>
</html>