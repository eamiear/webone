/**
 * JavaScript和标签共同驱动BootstrapTable
 */
$(function(){
	initTable();
	initEvents();
});

/**
 * 初始化table
 */
function initTable(){
	var setting = {
		toolbar : "#custom-toolbar",
		search:true
	};
	createBootstrapTable('bootstrapTable', 300, [], contextPath + '/wbookmsg/get_book_list', setting);
}

/**
 * 格式化内容信息
 * @param value
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

/**
 * 不用js启动BootstrapTable的“操作”部分，包含事件。事件可以通过事件句柄触发
 * @param value
 * @param row
 * @param index
 * @returns
 */
function optFormater(value,row,index){
	return [
            '<a class="deal" href="javascript:void(0)" title="删除记录">',
            	'<i class="glyphicon glyphicon-trash"></i>',
            '</a>  ',
            '<a class="star" href="javascript:void(0)" title="anything"> ',
	        	'<i class="glyphicon glyphicon-star"></i>',
	        '</a>'
        ].join('');
}

/**
 * BoostrapTable事件
 */
window.optEvents = {
		'click .deal': function (e, value, row, index) {
            alert('You click me ');
        },
        'click .star': function (e, value, row, index) {
            alert('You click me ');
        }
   };

/**
 * 格式化日期
 * @param value
 * @param row
 * @param index
 * @returns
 */
function dateFormatter(value,row,index){
		// 格式化时间
		value = (value == null ? "" : formatDate(new Date(value.time),"yyyy-MM-dd hh:mm:ss"));
		return value;
}