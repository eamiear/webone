window.menu = {
	animating:false,
	speed : 200,
	defaults : {
		selector: ".bsMenu", //选择器
		hasNavHeader: false,
		beforeClick: function() {
			return true;
		}, //单击之前回调
		itemClick: function() {} //点击每项回调
	},
	init : function(options){
		var that = this;
		var options = $.extend(that.defaults, options);
		var obj = $(options.selector);
		obj.addClass("bsMenu");
		obj.find("li ul").addClass("submenu well");
		obj.find(".active").parents("ul").addClass("open");
		obj.find("li a").mouseup(function() {
			that.toggleItem($(this));
		});
		obj.find("li a[data-ajax='true']").click(function() {
			return false;
		});

		//初始化mini
		that.mini();
	},

	toggleItem : function(item) {
		var that = this;
		if (!that.animating) {
			var parent = item.parent("li"); //获得父li
			var submenu = parent.find(">ul.submenu"); //获得子菜单

			if (submenu[0]) {
				that.animating = true;
				if (parent.hasClass("open")) {
					parent.removeClass("open");
					submenu.slideUp(that.speed, function () {
						that.animating = false;
					});
				} else {
					parent.addClass("open").siblings("li.open").removeClass("open").find("ul.submenu").slideUp(that.speed);
					submenu.slideDown(that.speed, function () {
						that.animating = false;
					});
				}
			} else {
				$(".bsMenu a.active").removeClass("active");
				item.addClass("active").parents("li.open").find("a:first").addClass("active"); //选中点击的a标签  同时选中 根标签
				$(".sbMenu li.open").removeClass("open").find("ul.submenu").slideUp(that.speed);
			}
			var bool = that.defaults.beforeClick(item);
			bool = (bool == null) ? true : bool; //如果没有返回值，默认true
			if (bool) { //返回 false 不继续执行
				var href = item.attr("href");
				var reg = /javascript|[^\w]/
				if (href && reg.test(href)) { //判断是否合法
					var index = layer.load(null, 0); //打开loading
					var loadType = item.attr("data-load-type");
					$(".main_content").hide();
					if(!loadType){	//默认加载方式
						$(".main_content").load(href, function (response, status, xhr) { //ajax页面加载
							layer.close(index); //关闭loading
							$(".main_content").show();
						});
					}else{//TODO 加载静态页面的方式
						var $include = '<jsp:include page="../' + href + '"></jsp:include>';
						$(".main_content").append($include);
						layer.close(index); //关闭loading
						$(".main_content").show();
					}
					
				}
			}
			that.defaults.itemClick(item); //点击回调
		}
	},
	selectItem : function(url) {
		this.toggleItem($(".bsMenu a[href='" + url + "']"));
	},
	mini : function () {
		$(".bsMenu_collapse a").click(function() {
			if(!$(".sidebar").hasClass("bsMenu_mini")){
				$(".sidebar").addClass("bsMenu_mini");
				//绑定鼠标移开事件
				$(".bsMenu > li").mouseenter(function() {
					$(this).addClass("open").find(">.submenu").css("display", "block");
				}).mouseleave(function() {
					$("li.open").removeClass("open").find(">.submenu").css("display", "none");
				});
			}else{
				$(".sidebar").removeClass("bsMenu_mini");
				$(".bsMenu > li").unbind(); //移除事件
			}
		});
	},
	//顶部导航菜单高亮
	navbarHightLight: function(option){
		var options = $.extend(this.defaults, option);
		if(options.hasNavHeader){
			var href = window.location.href;
			var parent = $(".navbar-menu");
			var item = parent.find('li');
			var obj = $(options.selector);
			
			item.each(function(index,elem,obj){
				var _item = $(this).find("a").attr("data-item");
				href.lastIndexOf(_item) != -1  ? $(this).addClass('active').siblings('li').removeClass('active') : $(this).removeClass('active');
			});
		}
	}
}
