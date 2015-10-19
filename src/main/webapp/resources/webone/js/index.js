$(function() {
  if (ToolUtil.isIE()) { //如果是ie
    location.href = "bsie.html";
  }

  //加载处理a标签ajax跳转
  //alink.init({ scope: '.main_content' });
  //alink.init({ scope: 'nav' });
  //alink.init({ target:'nav',scope: '.main_container'});

  menu.navbarHightLight({selector:'#menu',hasNavHeader:'true'});
  //菜单加载，并处理单击事件
  menu.init({
    selector: "#menu", //菜单选择器 默认  #menu;
    beforeClick: function(obj) { //执行跳转前回调，返回false 停止跳转。默认返回true
      // console.info(obj);
    },
    itemClick: function(obj) { //跳转后执行，无返回值
      // console.info(obj);
    }
  });

  index();
});
//首页js模块
function index(){


  //初始化 回到顶部按钮
  $(window).scroll(function(e) {
    $(this).scrollTop() > 0 ? ($(".btn_scroll_up").is(":hidden") ? $(".btn_scroll_up").click(function() {
      $("body,html").stop().animate({
        scrollTop: 0
      }, 500);
    }).show() : 0) : $(".btn_scroll_up").hide().unload();
  });

};
