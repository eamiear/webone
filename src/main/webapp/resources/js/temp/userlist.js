/**
 * 示例代码JS
 * 
 * @author liuxg
 * @date 2015年6月10日 上午9:50:46
 */
$(function() {
	initLayout();
	initData();
	initTable();
});	

function initData(){
	$.ajax({
		url : contextPath
				+ '/public/getAllUser.action',
		type : 'POST',
		dataType : 'json',
		success : function(result) {
			if(result==null){
				return;
			}
			 $('#bootstrapTable').bootstrapTable('load',result.data);
		}
	});
}



// 初始化表格
function initTable() {
	$('#bootstrapTable').bootstrapTable({
		url : Constants.CONTEXT_PATH
				+ '/public/getAllUser.action',
		cache : false,
		method : 'get',
		height : 400,
		striped : true,
		dataType : "json",
		pagination : true,
		/*showPaginationSwitch : true,*/
		singleSelect : false,
		pageSize : 10,
		pageNumber : 1,
		pageList : [10, 25, 50],
		search : true, // 不显示 搜索框
		showColumns : false, // 不显示下拉框（选择显示的列）
		responseHandler : responseHandler
	});
}

function responseHandler(res) {
	if (res) {
		return {
			"rows" : res.data,
			"total" : res.total
		};
	} else {
		return {
			"rows" : [],
			"total" : 0
		};
	}
}


/**
 * 初始化用户布局
 */
function initLayout() {
	$(".main-container").height($(window).outerHeight() - 75);
}



