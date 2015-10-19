/**
 * 创建表格
 * 
 * @param id		表格ID
 * @param height	表格高度
 * @param columns 	表格的列
 * @param url 		表格请求的url
 * @param setting 	其他的属性设置Object
 */
function createBootstrapTable(id, height, columns, url, setting) {
	var settings = {
		url : url,
		classes : 'table table-bordered table-hover',
		method : 'get',
//		striped : true,
		height : height,					//设置表格高度
//		cache : false,
		pagination : true,                  //是否要分页，true的时候，在表格底部添加分页栏，默认是false
//		pageSize : 10,                      //每页的分页数目，默认是10条记录
//		pageNumber : 1,                     //当前页页码,默认1
//		pageList: [10,20,30],               //选择每页的显示数量，默认是[10, 25, 50, 100, All]
		undefinedText : '暂无',              //列匹配不到数据时，显示替换的文本，默认是"-"
//	    showHeader : true ,                 //显示头部，默认是true
//	    showFooter : false,					//显示脚部
	    sidePagination : 'server', 			//在服务器端分页,'client'表示客户端分页
//	    showPaginationSwitch : true ,       //是否要显示分页按钮，该用来可以用来切换是否分页默认是false
	    formatLoadingMessage : function() {
			return '加载中, 请稍等…';
		},
//		formatRecordsPerPage : function(pageNumber) {
//			return '每页显示 ' + pageNumber + ' 条';
//		},
//		formatShowingRows : function(pageFrom, pageTo, totalRows) {
//			return '显示第 ' + pageFrom + '到' + pageTo + ' 条记录,总共' + totalRows
//					+ ' 条';
//		},
		formatShowingRows : function(){		//隐藏底部的行数信息
			return "";
		},
	    queryParams : function(params) {	//向服务器传参，即在URL中添加参数
	    	//params['a'] = 1;
			//params['searchText'] = initSearchData;
	    	return params;
		},
		columns : columns || []
	};

	/**
	 * 把setting对象的赋值给settings对象
	 */
	$.extend(settings,setting || {});

	return $('#' + id).bootstrapTable(settings);
}