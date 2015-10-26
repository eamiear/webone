$(function () {
	initFileUPload();
	dropZoneEffect();
	//dropZonesEffect();
});

/***
 * 初始化上传
 */
function initFileUPload(){
 $('#fileupload').fileupload({
        dataType: 'json',
 
        done: function (e, data) {
           /* $("tr:has(td)").remove();*/
            $.each(data.result, function (index, file) {
                $("#uploaded-files").append(
                      $('<tr/>')
                        .append($('<td/>').text(file.fileName))
                        .append($('<td/>').text(file.fileSize))
                        .append($('<td/>').text(file.fileType))
                        .append($('<td/>').html("<a href='"+contextPath+"/backhandler/get/"+index+"'>Click</a>"))
                        )
            }); 
        },
        //进度条效果
        progressall: function (e, data) {
            var progress = parseInt(data.loaded / data.total * 100, 10);
            $('#progress .bar').css(
                'width',
                progress + '%'
            );
        },
 
        dropZone: $('#dropzone')
    });
}

/**
 * 拖拽区域效果
 */
function dropZoneEffect(){
	$(document).bind('dragover', function (e) {
	    var dropZone = $('#dropzone'),
	        timeout = window.dropZoneTimeout;
	    if (!timeout) {
	        dropZone.addClass('in');
	    } else {
	        clearTimeout(timeout);
	    }
	    var found = false,
	        node = e.target;
	    do {
	        if (node === dropZone[0]) {
	            found = true;
	            break;
	        }
	        node = node.parentNode;
	    } while (node != null);
	    if (found) {
	        dropZone.addClass('hover');
	    } else {
	        dropZone.removeClass('hover');
	    }
	    window.dropZoneTimeout = setTimeout(function () {
	        window.dropZoneTimeout = null;
	        dropZone.removeClass('in hover');
	    }, 100);
	});
}

/**
 * 多文件拖拽效果
 */
function dropZonesEffect(){
	$(document).bind('dragover', function (e){
	    var dropZone = $('.dropzone'),
	        foundDropzone,
	        timeout = window.dropZoneTimeout;
		if (!timeout) {
			dropZone.addClass('in');
		} else {
			clearTimeout(timeout);
		}
		var found = false, node = e.target;
		do {
			if ($(node).hasClass('dropzone')) {
				found = true;
				foundDropzone = $(node);
				break;
			}
			node = node.parentNode;
		} while (node != null);
		dropZone.removeClass('in hover');
		if (found) {
			foundDropzone.addClass('hover');
		}
		window.dropZoneTimeout = setTimeout(function() {
			window.dropZoneTimeout = null;
			dropZone.removeClass('in hover');
		}, 100);
   });
}