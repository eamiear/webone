$(function(){
	var toolbar = ['title', 'bold', 'italic', 'underline', 'strikethrough', 'color', '|', 'ol', 'ul', 'blockquote', 'code', 'table', '|', 'link', 'image', 'hr', '|', 'indent', 'outdent', 'alignment'];
	var editor = new Simditor({
		  textarea: $('#editor'),
		  placeholder: '这里输入文字...',
		  toolbar: toolbar,
		  toolbarFloat: true,
		  toolbarFloatOffset: 0
		 
		});
})