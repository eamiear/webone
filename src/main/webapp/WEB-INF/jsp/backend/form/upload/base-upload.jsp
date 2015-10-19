<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>使用说明</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath }/resources/libs/bootstrap/dist/css/bootstrap.min.css"/>
    <script type="text/javascript">
	var contextPath = "${pageContext.request.contextPath}";
</script>
</head>
<body>
<div>
    <!-- 面包屑导航 -->
    <!-- <ol class="breadcrumb">
        <li class="">首页</li>
        <li class="active">使用说明</li>
    </ol> -->
    <div class="page_content">
        <!-- <blockquote class="alert alert-info">
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
        </blockquote> -->
			<div class="well">
				<form class="form-horizontal" id="upload">
					<div class="form-group">
						<div class="row">
							<div class="col-xs-6 col-md-3 col-sm-10">
								<a href="#" class="thumbnail"> 
									<img src="" alt="" id="cover" style="">
								</a>
							</div>
						</div>
						<div class="">
							<input type="file" class="form-control" name="upload" id="upload" capture="camera" accept="image/*" onchange="previewImage(this,'cover')">
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							<button type="submit" id="save" class="btn btn-default" >上传</button>
						</div>
					</div>

					
				</form>
				<hr>
				<form id="upload1">
					<div class="well">
						<section id="splash">
							<p id="errorMessage">Loading...</p>
						</section>
						<section id="app" hidden>
							<p>
								<video id="monitor" autoplay></video>
								<canvas id="photo"></canvas>
							<p>
							<input type=button value="拍照" onclick="snapshot()">
						</section>
						<button class="btn btn-default" id="save1">上传</button>
					</div>
				</form>
				<hr/>
				<div class="well">
					<form id="form2" method="post" enctype="multipart/form-data">
						<canvas id="c2"></canvas>
						<input type="file" class="form-control" name="upload" id="upload2" capture="camera" accept="image/*">
						<!-- <input type="hidden" name="upload" value="" id="data"> -->
						<button class="btn btn-default" id="save2">上传</button>
					</form>
				</div>
				
				<hr/>
				<div class="well">
					<form id="form3" method="post" enctype="multipart/form-data">
						<canvas id="c3"></canvas>
						<input type="file" class="form-control" name="upload" id="upload3" capture="camera" accept="image/*">
						<input type="hidden" name="upload" value="" id="data">
						<button class="btn btn-default" id="save3">上传</button>
					</form>
				</div>
			</div>
		</div>
</div>
<script src="${pageContext.request.contextPath }/resources/libs/jquery/dist/jquery.min.js"></script>
<script src="${pageContext.request.contextPath }/resources/libs/bootstrap/dist/js/bootstrap.min.js"></script>
<script>
$('#save').click(function() {
	var publicMessage = new FormData($('#upload')[0]);
	console.log(publicMessage);
	$.ajax({
		url : contextPath+'/backhandler/upload',
		data : publicMessage,
		type : 'POST',
		dataType : 'json',
		processData : false,
		contentType : false,
		success : function(res) {
			if (res['ok'] == 1) {
				alert("success");
			}else{
				alert("error");
			}
		},
		error : function(res) {
			alert("error");
		}
	});
});

/**
 * 预览图片
 * @param elem	//当前点击事件对象
 * @param target	//显示图片的ID
 */
function previewImage(elem,target){
	var $img = $('#'+target);
	if (elem.files && elem.files[0]) {
		var reader = new FileReader();
		reader.onload = function(evt) {
			$img.attr('src', evt.target.result);
		}
		reader.readAsDataURL(elem.files[0]);
	}
}
</script>

<script>
navigator.getUserMedia = navigator.getUserMedia || navigator.webkitGetUserMedia || navigator.mozGetUserMedia || navigator.msGetUserMedia;  
window.URL = window.URL || window.webkitURL || window.mozURL || window.msURL;  

navigator.getUserMedia({video:true}, gotStream, noStream);
var video = document.getElementById('monitor');
var canvas = document.getElementById('photo');

function gotStream(stream) {
    video.src = window.URL.createObjectURL(stream);
    video.onerror = function () {
        stream.stop();
    };
    stream.onended = noStream;
    video.onloadedmetadata = function () {
        canvas.width = video.videoWidth;
        canvas.height = video.videoHeight;
        document.getElementById('splash').hidden = true;
        document.getElementById('app').hidden = false;
    };
}
function noStream() {
    document.getElementById('errorMessage').textContent = 'No camera available.';
}
function snapshot() {
    canvas.getContext('2d').drawImage(video, 0, 0);
}

var imgData =canvas.toDataURL("image/png");

$('#save1').click(function() {
	$.ajax({
		url : contextPath+'/backhandler/upload',
		data : {"upload":imgData},
		type : 'POST',
		dataType : 'json',
		processData : false,
		contentType : false,
		success : function(res) {
			if (res['ok'] == 1) {
				alert("success");
			}else{
				alert("error");
			}
		},
		error : function(res) {
			alert("error");
		}
	});
});
</script>

<script>
$(function(){
	function drawOnCanvas(file) {
		var reader = new FileReader();
		reader.onload = function(e) {
			var dataURL = e.target.result, 
				//canvas = document.querySelector('canvas'), 
				canvas = document.getElementById('c2'),
				ctx = canvas.getContext('2d'), 
				img = new Image();
			
			img.onload = function() {
				var square = 320;
				canvas.width = square;
				canvas.height = square;
				var context = canvas.getContext('2d');
				context.clearRect(0, 0, square, square);
				var imageWidth;
				var imageHeight;
				var offsetX = 0;
				var offsetY = 0;
				if (this.width > this.height) {
					imageWidth = Math.round(square * this.width / this.height);
					imageHeight = square;
					offsetX = -Math.round((imageWidth - square) / 2);
				} else {
					imageHeight = Math.round(square * this.height / this.width);
					imageWidth = square;
					offsetY = -Math.round((imageHeight - square) / 2);
				}
				context.drawImage(this, offsetX, offsetY, imageWidth,
						imageHeight);
				
				var base64 = canvas.toDataURL('image/jpeg', 0.5);
				var data = base64.substr(22);
				//console.log(base64);
				//console.log(data);
				//$('#upload2').val(base64.substr(22));
			};
			img.src = dataURL;
		};
		reader.readAsDataURL(file);
	}
	
	
	document.querySelector('input[id=upload2]').onchange = function() {
		var file = this.files[0];
		drawOnCanvas(file);
	};
	
	$('#save2').click(function() {
		$("#form2").prop("action",contextPath+'/backhandler/upload');
		$("#form2").submit();
		 var imgData = new FormData($('#form2')[0]);
		//var imgData = $("#upload2").val();
		 /* $.ajax({
			url : contextPath+'/backhandler/upload',
			data : imgData,
			type : 'POST',
			dataType : 'json',
			processData : false,
			contentType : false,
			success : function(res) {
				if (res['ok'] == 1) {
					alert("success");
				}else{
					alert("error");
				}
			},
			error : function(res) {
				alert("error");
			} 
		}); */
	}); 
})
	
</script>



<script>
$(function(){
	function drawOnCanvas(file) {
		var reader = new FileReader();
		reader.onload = function(e) {
			var dataURL = e.target.result, 
				//canvas = document.querySelector('canvas'), 
				canvas = document.getElementById('c3'),
				ctx = canvas.getContext('2d'), 
				img = new Image();
			
			img.onload = function() {
				var square = 320;
				canvas.width = square;
				canvas.height = square;
				var context = canvas.getContext('2d');
				context.clearRect(0, 0, square, square);
				var imageWidth;
				var imageHeight;
				var offsetX = 0;
				var offsetY = 0;
				if (this.width > this.height) {
					imageWidth = Math.round(square * this.width / this.height);
					imageHeight = square;
					offsetX = -Math.round((imageWidth - square) / 2);
				} else {
					imageHeight = Math.round(square * this.height / this.width);
					imageWidth = square;
					offsetY = -Math.round((imageHeight - square) / 2);
				}
				context.drawImage(this, offsetX, offsetY, imageWidth,
						imageHeight);
				
				var base64 = canvas.toDataURL('image/jpeg', 0.5);
				var data = base64.substr(22);
				//console.log(base64);
				//console.log(data);
				$('#data').val(base64.substr(22));
			};
			img.src = dataURL;
		};
		reader.readAsDataURL(file);
	}
	
	
	document.querySelector('input[id=upload3]').onchange = function() {
		var file = this.files[0];
		drawOnCanvas(file);
	};
	
	$('#save3').click(function() {
		 var imgData = $("#data").val();
		//var imgData = $("#upload2").val();
		 $.ajax({
			url : contextPath+'/backhandler/upload2',
			data : {'upload':imgData},
			type : 'POST',
			dataType : 'json',
			processData : false,
			contentType : false,
			success : function(res) {
				if (res['ok'] == 1) {
					alert("success");
				}else{
					alert("error");
				}
			},
			error : function(res) {
				alert("error");
			} 
		}); 
	}); 
})
	
</script>
</body>
</html>