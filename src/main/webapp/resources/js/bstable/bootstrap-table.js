$(function(){
	initBookInfo();
	initEvents();
});


/**
 * 初始化图书信息
 */
function initBookInfo() {
	//表格列信息
	var columns = [
	    {
	    	field:'',
	    	title:'编号',
	    	align:'center',
	    	valign:"middle",
	    	formatter:NumberFormater
	    },
	    {
	    	field:'id',
	    	title:'id',
	    	visible:false
	    },
		{
			field : 'name',
			title : '书名',
			align : 'center',
			valign : 'middle',
			sortable : false,
			searchable : true
		},
		{
			field : 'author',
			title : '作者',
			align : 'center',
			valign : 'middle',
			sortable : false
		},
		{
			field : 'publisher',
			title : '出版社',
			align : 'center',
			valign : 'middle',
			sortable : false
		},
		{
			field : 'publishTime',
			title : '出版时间',
			align : 'center',
			valign : 'middle',
			sortable : false,
			formatter : function(value, row, index) {
				// 格式化时间
				value = (value == null ? "" : formatDate(new Date(value.time),"yyyy-MM-dd hh:mm:ss"));
				return value;
			}
		},
		{
			field : 'introduce',
			title : '图书简介',
			align : 'center',
			valign : 'middle',
			sortable : false,
			formatter : contentFormater
		},
		{
			field : 'bookType',
			title : '图书类型',
			align : 'center',
			valign : 'middle',
			sortable : false
		},
		{
			field : 'count',
			title : '页码数',
			align : 'left',
			valign : 'middle',
			sortable : false
		},
		{
			field : '',
			title : '操作',
			align : 'center',
			valign : 'middle',
			clickToSelect : false,
			formatter : function(value, row, index) {
				return [
						'<a class="edit ml10" href="javascript:void(0)" title="编辑">',
						'编辑  ',
						'<i class="glyphicon glyphicon-edit">&nbsp;</i>',
						'</a>' ].join('');
				},
			events : {
				'click .edit' : function(e, value, row, index) {
					window.location.href = contextPath
							+ "/feedbackEdit?fb=" + row.id;
				}
			}
		} ];

	//覆盖默认设置
	var setting = {
		sortName : 'id',
		sortOrder : 'desc',
		classes : 'table table-hover'
	};

	//创建表格
	createBootstrapTable('bootstrapTable', 300, columns, contextPath + '/wbookmsg/get_book_list', setting);
}

/**
 * 格式化内容信息
 * @param value	当前值
 * @returns
 */
function contentFormater(value) {
	var svalue = value.length <= 7 ? value : value.substr(0, 6) + "...";
	return [
			'<a class="ml10 pop" id="pop" data-toggle="popover" href="javascript:void(0)" ',
			'tabindex="0" data-title="内容" data-trigger="hover" ',
			'data-placement="right"  data-content="', value, '">', svalue,
			'</a>' ].join("");
}

/**
 * 格式化编号
 * @param value
 * @param row
 * @param index
 * @returns
 */
function NumberFormater(value,row,index){
	return index+1;
}
/**
 * BootstrapTable加载成功后执行
 */
function initEvents(){
	$('#bootstrapTable').bootstrapTable().on('load-success.bs.table', function (e, data) {
		$('.pop').popover();
	});
}

