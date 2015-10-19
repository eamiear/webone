/**
* 处理a标签的ajax内容跳转
*/
window.alink = {
  loading : false,
  defaults: {
    target: 'body',//
    scope: 'body' //内容展示范围
  },

  init: function(option){
    if(!this.loading){
      this.loading = true;
      var options = $.extend(this.defaults, option);   //读取和初始化 公共参数
      $(document).on("mouseup", options.target+" a[data-ajax='true']", function(e) {
        var obj = $(this);
        var href = obj.attr("href");
        var reg = /javascript|[^\w]/
        if (href && reg.test(href)) { //判断是否合法
          var index = layer.load(null, 0);
          $(options.scope).hide();
          $(options.scope).load(href, function (response, status, xhr) {      //ajax页面加载
            layer.close(index);
            $(options.scope).show();
          });
        }
      });
      $(document).on("click", options.scope+" a[data-ajax='true']", function(){   //取消a标签的href跳转
        return false;
      });
    }
  }
}