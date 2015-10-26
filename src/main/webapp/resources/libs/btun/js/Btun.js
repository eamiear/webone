var B = {
    $ : window.Zepto,
    //参数设置
    settings : {
        //自定义动画时的默认动画时间
        transitionTime : 250,
        //自定义动画时的默认动画函数
        transitionTimingFunc : 'ease-in'
    },
    //是否有打开的弹出框
    hasPopupOpen : false,
    launch : function(){
        B.Element.init();
    }
};

/**
 * 初始化页面组件元素
 * @type {Function}
 */
B.Element = (function($){
    var SELECTOR = {
        'icon' : '[data-icon]',
        'scroll' : '[data-scroll]="true"',
        'toggle' : '.toggle',
        'range' : '[data-rangeinput]',
        'progress' : '[data-progress]',
        'count' : '[data-count]',
        'checkbox' : '[data-checkbox'
    };

    /**
     * 初始化容器内组件
     * @param selector
     */
    var init = function(selector){
        if(!selector){

        }
        var $el = $(selector || 'body');
        if($el.length == 0)return;

        /**
         * $.map([],function(){}),表示对数组里元素都执行function函数
         */
        $.map(_getMatchElements($el,SELECTOR.icon),_init_icon);
    }
    /**
     *自身与子集相结合
     * @param $el
     * @param selector
     * @private
     * @return []
     */
    var _getMatchElements = function($el,selector){
        return $el.find(selector).add($el.filter(selector));
    }

    /**
     * 构建icon组件
     * @param el
     * @private
     */
    var _init_icon = function(el){
        var $el = $(el),$icon=$el.children("i.icon"),icon = $el.data('icon');
        if($icon.length > 0){//已经初始化，就更新icon
            $icon.attr('class','icon '+icon);
        }else{
            $el.prepend('<i class="icon '+icon+'"></i>');
        }
    };

    /**
     * 构建toggle组件
     * @param el
     * @private
     */
    var _init_toggle = function(el){
        var $el = $(el),$input;
        if($el.find('div.toggle-handle').length>0){//已经初始化
            return ;
        }
        var name = $el.attr('name');
        //添加隐藏域，方便获取值
        if(name){
            $el.append('<input style="display: none;" name="'+name+'" value="'+$el.hasClass('active')+'"/>');
        }
        $el.append('<div class="toggle-handle"></div>');
        $input = $el.find('input');
        $el.tap(function(){
            var value;
            if($el.hasClass('active')){
                $el.removeClass('active');
                value = false;
            }else{
                $el.addClass('active');
                value = true;
            }
            $input.val(value);
            //自定义事件：toggle
            $el.trigger('toggle');
        })
    };

    /**
     * 构建range滑块组件
     * @param el
     * @private
     */
    var _init_range = function(el){
        var $el = $(el),$input;
        var $range = $('input[type="range"]',el);
        var align = $el.data('rangeinput');
        var input = $('<input type="text" name="test" value="'+$range.val()+'"/>');
        if(align == 'left'){
            $input = input.prependTo($el);
        }else{
            $input = input.appendTo($el);
        }
        var max = parseInt($range.attr('max'),10);
        var min = parseInt($range.attr('min'),10);
        $range.change(function(){
            $input.val($range.val());
        });
        $input.on('input',function(){
            var value = parseInt($input.val(),10);
            value = value>max?max:(value<min?min:value);
            $range.val(value);
            $input.val(value);
        })
    };

    return {
        init : init,
        initIcon : _init_icon,
        initToggle : _init_toggle,
        initRange : _init_range
    }
})(B.$);

/**
 * 模板
 */
B.Template = (function($){

    /**
     * 背景模板
     * @param el    选择器
     * @param title 文本标题
     * @param icon  图标
     */
    var background = function(el,title,icon){
        var markup = '<div class="back-mask"><div class="icon '+icon+'"></div><div>'+title+'</div></div>';
        $(el).html(markup);
    }

    /**
     * 没有记录 的背景模板
     * @param el
     */
    var no_result = function(el){
        background(el,'没有找到相关数据','drawer');
    }

    /**
     * 加载等待背景模板
     * @param el
     */
    var loading = function(el){
        background(el,'加载中...','cloud-download');
    }

    /**
     * 借助artTemplate模板来渲染页面
     * @param containerSelector    目标容器
     * @param templateId           artTemplate模板ID
     * @param data                 模板数据
     * @param type                 replace|add 渲染好的文档片段是替换还是添加到目标容器中
     */
    var render = function(containerSelector,templateId,data,type){
        var el =  $(containerSelector),
            type = type || 'replace';//replace  add
        if($.type(data) == 'array' && data.length == 0 ){
            no_result(el);
        }else{
            var html = $(template(templateId,data));
            if(type == 'replace'){
                el.html(html);
            }else{
                el.append(html);
            }
            B.Element.init(html);
        }
    }
    return {
        render : render,
        background : background,
        loading : loading,
        no_result : no_result
    }
})(B.$);

/**
 * 消息组件
 */
B.Toast = (function($){
    var TOAST_DURATION = 5000;

    //定义模板
    var TEMPLATE = {
        toast : '<a href="#">{value}</a>',
        success : '<a href="#"><i class="icon checkmark-circle"></i>{value}</a>',
        error : '<a href="#"><i class="icon cancel-circle"></i>{value}</a>',
        info : '<a href="#"><i class="icon info-2"></i>{value}</a>'
    }
    var toast_type = 'toast',_toast,timer;

    /**
     * 初始化
     * @private
     */
    var _init = function(){
        //全局只有一个实例
        $('body').append('<div id="b_toast"></div>');
        _toast = $('#b_toast');
        _subscribeCloseTag();
    }

    /**
     * 关闭消息提示
     */
    var hide = function(){
        B.anim(_toast,'scaleOut',function(){
            _toast.hide();
            _toast.empty();
        });
    }

    /**
     * 显示消息提示
     * @param type      类型  toast|success|error|info
     * @param text      文字内容
     * @param duration  持续时间 为0则不自动关闭,默认为5000ms
     */
    var show = function(type,text,duration){
        if(timer) clearTimeout(timer);
        toast_type = type;
        _toast.attr('class',type).html(TEMPLATE[type].replace('{value}',text)).show();
        B.anim(_toast,'scaleIn');
        if(duration !== 0){//为0 不自动关闭
            timer = setTimeout(hide,duration || TOAST_DURATION);
        }
    }
    /**
     * 关闭消息组件按钮
     * @private
     */
    var _subscribeCloseTag = function(){
        _toast.on('tap','[data-target="close"]',function(){
            hide();
        })
    }
    _init();

    return {
        show : show,
        hide : hide
    }

})(B.$);


/**
 * 弹出框组件
 */
B.Popup = (function($){
    var _popup,_mask,transition,clickMask2close,
        POSITION = {
            'top':{
                top:0,
                left:0,
                right:0
            },
            'top-second':{
                top:'44px',
                left:0,
                right:0
            },
            'center':{
                top:'50%',
                left:'5%',
                right:'5%',
                'border-radius' : '3px'
            },
            'bottom' : {
                bottom:0,
                left:0,
                right:0
            },
            'bottom-second':{
                bottom : '51px',
                left:0,
                right:0
            }
        },
        ANIM = {
            top : ['slideDownIn','slideUpOut'],
            bottom : ['slideUpIn','slideDownOut'],
            defaultAnim : ['bounceIn','bounceOut']
        },
        TEMPLATE = {
            alert : '<div class="popup-title">{title}</div><div class="popup-content">{content}</div><div id="popup_btn_container"><a data-target="closePopup" data-icon="checkmark">{ok}</a></div>',
            confirm : '<div class="popup-title">{title}</div><div class="popup-content">{content}</div><div id="popup_btn_container"><a class="cancel" data-icon="close">{cancel}</a><a data-icon="checkmark">{ok}</a></div>',
            loading : '<i class="icon spinner"></i><p>{title}</p>'
        };

    /**
     * 全局只有一个popup实例
     * @private
     */
    var _init = function(){
        $('body').append('<div id="b_popup"></div><div id="b_popup_mask"></div>');
        _mask = $('#b_popup_mask');
        _popup = $('#b_popup');
        _subscribeEvents();
    }

    var show = function(options){
        var settings = {
            height : undefined,//高度
            width : undefined,//宽度
            opacity : 0.3,//透明度
            url : null,//远程加载url
            tplId : null,//加载模板ID
            tplData : null,//模板数据，配合tplId使用
            html : '',//popup内容
            pos : 'center',//位置 {@String top|top-second|center|bottom|bottom-second}   {@object  css样式}
            clickMask2Close : true,// 是否点击外层遮罩关闭popup
            showCloseBtn : true,// 是否显示关闭按钮
            arrowDirection : undefined,//popover的箭头指向
            animation : true,//是否显示动画
            timingFunc : 'linear',
            duration : 200,//动画执行时间
            onShow : undefined //@event 在popup内容加载完毕，动画开始前触发
        }
        $.extend(settings,options);
        clickMask2close = settings.clickMask2Close;
        _mask.css('opacity',settings.opacity);
        //rest position and class
        _popup.attr({'style':'','class':''});
        settings.width && _popup.width(settings.width);
        settings.height && _popup.height(settings.height);
        var pos_type = $.type(settings.pos);
        if(pos_type == 'object'){// style
            _popup.css(settings.pos);
            transition = ANIM['defaultAnim'];
        }else if(pos_type == 'string'){
            if(POSITION[settings.pos]){ //已经默认的样式
                _popup.css(POSITION[settings.pos])
                var trans_key = settings.pos.indexOf('top')>-1?'top':(settings.pos.indexOf('bottom')>-1?'bottom':'defaultAnim');
                transition = ANIM[trans_key];
            }else{// pos 为 class
                _popup.addClass(settings.pos);
                transition = ANIM['defaultAnim'];
            }
        }else{
            console.error('错误的参数！');
            return;
        }
        _mask.show();
        var html;
        if(settings.html){
            html = settings.html;
        }else if(settings.url){//远程加载
            //html = J.Page.loadContent(settings.url);
        }else if(settings.tplId){//加载模板
            html = template(settings.tplId,settings.tplData)
        }

        //是否显示关闭按钮
        if(settings.showCloseBtn){
            html += '<div id="tag_close_popup" data-target="closePopup" class="icon cancel-circle"></div>';
        }
        //popover 箭头方向
        if(settings.arrowDirection){
            _popup.addClass('arrow '+settings.arrowDirection);
            _popup.css('padding','8px');
            if(settings.arrowDirection=='top'||settings.arrowDirection=='bottom'){
                transition = ANIM[settings.arrowDirection];
            }
        }

        _popup.html(html).show();
        B.Element.init(_popup);
        //执行onShow事件，可以动态添加内容
        settings.onShow && settings.onShow.call(_popup);

        //显示获取容器高度，调整至垂直居中
        if(settings.pos == 'center'){
            var height = _popup.height();
            _popup.css('margin-top','-'+height/2+'px')
        }
        B.Element.init(_popup);
        if(settings.animation){
            B.anim(_popup,transition[0],settings.duration,settings.timingFunc);
        }
        B.hasPopupOpen = true;
    }

    /**
     * 关闭弹出框
     * @param noTransition 立即关闭，无动画
     */
    var hide = function(noTransition){
        _mask.hide();
        if(transition && !noTransition){
            B.anim(_popup,transition[1],200,function(){
                _popup.hide().empty();
                B.hasPopupOpen = false;
            });
        }else{
            _popup.hide().empty();
            B.hasPopupOpen = false;
        }

    }

    var _subscribeEvents = function(){
        _mask.on('tap',function(){
            if(clickMask2close){
                hide();
            }
        });
        _popup.on('tap','[data-target="closePopup"]',function(){hide();});
    }

    /**
     * alert组件
     * @param title 标题
     * @param content 内容
     */
    var alert = function(title,content){
        var markup = TEMPLATE.alert.replace('{title}',title).replace('{content}',content).replace('{ok}','确定');
        show({
            html : markup,
            pos : 'center',
            clickMask2Close : false,
            showCloseBtn : false
        });
    }

    /**
     * confirm 组件
     * @param title 标题
     * @param content 内容
     * @param okCall 确定按钮handler
     * @param cancelCall 取消按钮handler
     */
    var confirm = function(title,content,okCall,cancelCall){
        var markup = TEMPLATE.confirm.replace('{title}',title).replace('{content}',content).replace('{cancel}','取消').replace('{ok}','确定');
        show({
            html : markup,
            pos : 'center',
            clickMask2Close : false,
            showCloseBtn : false
        });
        $('#popup_btn_container [data-icon="checkmark"]').tap(function(){
            hide();
            okCall.call(this);
        });
        $('#popup_btn_container [data-icon="close"]').tap(function(){
            hide();
            cancelCall.call(this);
        });
    }

    /**
     * 带箭头的弹出框
     * @param html 弹出框内容
     * @param pos 位置
     * @param arrow_direction 箭头方向
     * @param onShow onShow事件
     */
    var popover = function(html,pos,arrow_direction,onShow){
        show({
            html : html,
            pos : pos,
            showCloseBtn : false,
            arrowDirection : arrow_direction,
            onShow : onShow
        });
    }

    /**
     * loading组件
     * @param text 文本，默认为“加载中...”
     */
    var loading = function(text){
        var markup = TEMPLATE.loading.replace('{title}',text||'加载中...');
        show({
            html : markup,
            pos : 'loading',
            opacity : 0,
            animation : false,
            clickMask2Close : false
        });
    }

    /**
     * actionsheet组件
     * @param buttons 按钮集合
     * [{color:'red',text:'btn',handler:function(){}},{color:'red',text:'btn',handler:function(){}}]
     */
    var actionsheet = function(buttons){
        var markup = '<div class="actionsheet">';
        $.each(buttons,function(i,n){
            markup += '<button style="background-color: '+ n.backgroudColor +' !important;">'+ n.text +'</button>';
        });
        markup += '<button class="alizarin">取消</button>';
        markup += '</div>';
        show({
            html : markup,
            pos : 'bottom',
            showCloseBtn : false,
            onShow : function(){
                $(this).find('button').each(function(i,button){
                    $(button).on('tap',function(){
                        if(buttons[i] && buttons[i].handler){
                            buttons[i].handler.call(button);
                        }
                        hide();
                    });
                });
            }
        });
    }

    _init();

    return {
        show : show,
        close : hide,
        alert : alert,
        confirm : confirm,
        popover : popover,
        loading : loading,
        actionsheet : actionsheet
    }
})(B.$);


/**
 *常用方法封装
 */

/**
 * 动画
 * @param el            选择器
 * @param animName      动画名称
 * @param duration      持续时间
 * @param ease          动画方式
 * @param callback      回调函数
 */
B.anim = function(el,animName,duration,ease,callback){
    var d, e,c;
    var len = arguments.length;
    for(var i = 2;i<len;i++){
        var a = arguments[i];
        var t = $.type(a);
        t == 'number'?(d=a):(t=='string'?(e=a):(t=='function')?(c=a):null);
    }
    $(el).animate(animName,d|| B.settings.transitionTime,e||B.settings.transitionTimingFunc,c);
}


/**
 * 显示消息
 * @param text
 * @param type
 * @param duration
 */
B.showToast = function(text,type,duration){
    type = type || 'toast';
    B.Toast.show(type,text,duration);
}

/**
 * 关闭消息提示
 */
B.hideToast = function(){
    B.Toast.hide();
}


/**
 * 显示loading框
 * @param text
 */
B.showMask = function(text){
    B.Popup.loading(text);
}

/**
 * 关闭loading框
 */
B.hideMask = function(){
    B.Popup.close(true);
}


/**
 * 警示框
 * @param title
 * @param content
 */
B.alert = function(title,content){
    B.Popup.alert(title,content);
}

/**
 * 确认框
 * @param title
 * @param content
 * @param okCall
 * @param cancelCall
 */
B.confirm = function(title,content,okCall,cancelCall){
    B.Popup.confirm(title,content,okCall,cancelCall);
}

/**
 * 弹出窗口
 * @param options
 */
B.popup = function(options){
    B.Popup.show(options);
}

/**
 * 关闭窗口
 */
B.closePopup = function(){
    B.Popup.close();
}

/**
 * 带箭头的弹出框
 * @param html [可选]
 * @param pos [可选]  位置
 * @param arrowDirection [可选] 箭头方向
 * @param onShow [可选] 显示之前执行
 */
B.popover = function(html,pos,arrowDirection,onShow){
    B.Popup.popover(html,pos,arrowDirection,onShow);
}

/**
 * 自动渲染模板并填充到页面
 * @param containerSelector 欲填充的容器
 * @param templateId 模板ID
 * @param data 数据源
 * @param type [可选] add|replace
 */
B.tmpl = function(containerSelector,templateId,data,type){
    B.Template.render(containerSelector,templateId,data,type);
}


/**
 *滚动条
 */
;(function($){
    var scrollCache = {},index = 1;
    B.Scroll = function(selector,opts){
        var scroll,scrollId,$el = $(selector),
            options = {
                hScroll : false,
                bounce : false,
                lockDirection : true,
                useTransform: true,
                useTransition: false,
                checkDOMChanges: false,
                onBeforeScrollStart: function (e) {
                    var target = e.target;
                    while (target.nodeType != 1) target = target.parentNode;
                    if (target.tagName != 'SELECT' && target.tagName != 'INPUT' && target.tagName != 'TEXTAREA')
                        e.preventDefault();
                }
            };
        scrollId = $el.data('_jscroll_');
        //滚动组件使用频繁，缓存起来节省开销
        if(scrollId && scrollCache[scrollId]){
            scroll = scrollCache[scrollId];
            $.extend(scroll.scroller.options,opts);
            scroll.scroller.refresh();
            return scroll;
        }else{
            scrollId = '_jscroll_'+index++;
            $el.data('_jscroll_',scrollId);
            $.extend(options,opts);
            scroller = new iScroll($el[0],options);
            return scrollCache[scrollId] = {
                scroller : scroller,
                destroy : function(){
                    scroller.destroy();
                    delete scrollCache[scrollId];
                }
            };
        };
    }
})(B.$);

/**
 * 上拉/下拉组件
 */
;(function($){
    var refreshCache = {},index = 1;
    function Refresh(selector,type,callback){
        var iscroll, scroller,refreshEl,iconEl,labelEl,topOffset,isPullDown,
            options = {
                selector : undefined,
                type : 'pullDown',//pullDown|pullUp 默认为pullDown
                minPullHeight : 10,//拉动的像素相对值，超过才会翻转
                pullText: "下拉刷新...",
                releaseText: "松开立即刷新...",
                refreshText: "刷新中...",
                refreshTip : false,//下拉时显示的文本，比如：最后更新时间:2013-....
                onPullIcon : 'arrow-down-2',
                onReleaseIcon  : 'icon-reverse',
                onRefreshIcon : 'spinner',
                callback : undefined
            };
        //装载配置
        if(typeof selector === 'object'){
            $.extend(options,selector);
        }else{
            options.selector = selector;
            options.type = type;
            options.callback = callback;
            if(type === 'pullUp'){
                $.extend(options,{
                    pullText: "上拉加载更多...",
                    releaseText: "松开开立即加载...",
                    refreshText: "加载中...",
                    onPullIcon : 'arrow-up-3'
                })
            }
        }
        isPullDown = options.type === 'pullDown' ? true : false;

        /**
         * 初始化dom节点
         * @param opts
         * @private
         */
        var _init = function(opts){
            scroller = $(opts.selector).children()[0];
            var refreshTpl = '<div class="refresh-container"><span class="refresh-icon icon '+opts.onPullIcon
                +'"></span><span class="refresh-label">'
                +opts.pullText+'</span>'
                +(opts.refreshTip?'<div class="refresh-tip">'+opts.refreshTip+'</div>':'')+'</div>';
            if(isPullDown){
                refreshEl = $(refreshTpl).prependTo(scroller);
            }else{
                refreshEl = $(refreshTpl).appendTo(scroller);
            }
            topOffset = refreshEl.height();
            iconEl = refreshEl.find('.refresh-icon');
            labelEl = refreshEl.find('.refresh-label');
        }

        /**
         * 构造iscroll组件，并绑定滑动事件
         * @param opts
         * @private
         */
        var _excuteScroll = function(opts){
            return B.Scroll(opts.selector,{
                topOffset:isPullDown?topOffset:0,
                bounce : true,
                onScrollMove : function(){
                    if (this.y > opts.minPullHeight && isPullDown && !iconEl.hasClass(opts.onReleaseIcon)) {
                        iconEl.addClass(opts.onReleaseIcon);
                        labelEl.html(opts.releaseText);
                        this.minScrollY = 0;
                    } else if (this.y < opts.minPullHeight && isPullDown && iconEl.hasClass(opts.onReleaseIcon)) {
                        iconEl.removeClass(opts.onReleaseIcon);
                        labelEl.html(opts.pullText);
                        this.minScrollY = -topOffset;
                    }else if (this.y < (this.maxScrollY - opts.minPullHeight) && !isPullDown && !iconEl.hasClass(opts.onReleaseIcon)) {
                        iconEl.addClass(opts.onReleaseIcon);
                        labelEl.html(opts.releaseText);
                        this.maxScrollY = this.maxScrollY;
                    } else if (this.y > (this.maxScrollY + opts.minPullHeight) && !isPullDown && iconEl.hasClass(opts.onReleaseIcon)) {
                        iconEl.removeClass(opts.onReleaseIcon);
                        labelEl.html(opts.pullText);
                        this.maxScrollY = topOffset;
                    }
                },
                onScrollEnd : function(){
                    if(iconEl.hasClass(opts.onReleaseIcon)){
                        iconEl.removeClass(opts.onReleaseIcon).removeClass(opts.onPullIcon).addClass(opts.onRefreshIcon);
                        labelEl.html(opts.refreshText);
                        var _this = this;
                        setTimeout(function(){//解决在chrome下onRefresh的时候文本无法更改的问题。奇怪的问题！
                            opts.callback.call(_this);
                        },1);

                    }
                },
                onRefresh: function () {
                    iconEl.removeClass(opts.onRefreshIcon).addClass(opts.onPullIcon);
                    labelEl.html(opts.pullText);
                }
            });
        }

        //run
        _init(options);
        iscroll = _excuteScroll(options);
        return iscroll;

    }

    /**
     * 刷新组件
     * @param selector selector
     * @param type 类型 pullDown(下拉) pullUp(上拉)
     * @param callback 回调函数
     */
    B.Refresh = function(selector,type,callback){
        var el,jRefreshId;
        if(selector.selector){
            el = $(selector.selector)
        }else{
            el = $(selector);
        }
        jRefreshId = el.data('_jrefresh_');
        //因上拉下拉可能会使用的比较频繁，故缓存起来节省开销,亦可防止重复绑定
        if(jRefreshId && refreshCache[jRefreshId]){
            return refreshCache[jRefreshId];
        }else{
            jRefreshId = '_jrefresh_'+index++;
            el.data('_jrefresh_',jRefreshId);
            var refresh = new Refresh(selector,type,callback);
            return refreshCache[jRefreshId] = {
                scroller : refresh.scroller,
                destroy : function(){
                    delete refreshCache[jRefreshId];
                    refresh.scroller.destroy();
                    $('.refresh-container',selector).remove();
                }
            };
        }
    }
})(B.$);


/**
 * 幻灯片组件
 */
;(function($){
    function slider(selector,showDots){
        var afterSlide = function(){},
            beforeSlide = function(){return true},
            gestureStarted = false,
            index = 0,
            speed = 200,
            wrapper,
            dots,
            container,
            slides,
            slideNum,
            slideWidth,
            deltaX,
            autoPlay
            interval = 0;
        var _this = this;

        if($.isPlainObject(selector)){
            wrapper = $(selector.selector);
            showDots = selector.showDots;
            beforeSlide = selector.onBeforeSlide || beforeSlide;
            afterSlide = selector.onAfterSlide || afterSlide;
            autoPlay = selector.autoPlay;
            interval = selector.interval || 3000;
        }else{
            wrapper = $(selector);
        }
        /**
         * 初始化容器大小
         */
        var _init = function() {
            container = wrapper.children().first();
            slides = container.children();
            slideNum = slides.length;
            slideWidth = wrapper.offset().width;
            container.css('width',slideNum * slideWidth);
            slides.css({
                    'width':slideWidth,
                    'float':'left'
            }).show();
            if(showDots == undefined)showDots = true;
            showDots && _initDots();
            _slide(0, 0);
            afterSlide(0);
            autoPlay && _autoPlay();
        };

        var _autoPlay = function(){
            setTimeout(function(){
                if(index == slideNum - 1){
                    _slide(0);
                }else{
                    _this.next();
                }
                _autoPlay();
            },interval);
        }

        var _initDots = function(){
            dots = wrapper.find('.dots');
            if(dots.length>0){
                dots.show();
            }else{
                var dotsWidth = slideNum*30+20+2;
                var html = '<div class="dots"><ul>';
                for(var i=0;i<slideNum;i++){
                    html +='<li index="'+i+'"';
                    if(i == 0){
                        html += 'class="active"';
                    }
                    html += '><a href="#"></a></li>';
                }
                html += '</ul></div>';
                wrapper.append(html);
                dots = wrapper.find('.dots');
                dots.children().css('width',dotsWidth+'px');
                dots.find('li').on('tap',function(){
                    var index = $(this).attr('index');
                    _slide(parseInt(index), speed);
                })
            }
        }

        /**
         * 滑动到指定卡片
         * @param i
         * @param duration
         * @private
         */
        var _slide = function(i, duration) {
            duration = duration || speed;
            container.css({
                '-webkit-transition-duration':duration + 'ms',
                '-webkit-transform':'translate3D(' + -(i * slideWidth) + 'px,0,0)'
            });
            if(index != i){
                index = i;
                if(dots) $(dots.find('li').get(index)).addClass('active').siblings().removeClass('active');
                afterSlide(index);
            }
        };

        /**
         * 绑定滑动事件
         */
        var _bindEvents = function() {
            container.on('touchstart',_touchStart,false);
            container.on('touchmove',_touchMove,false);
            container.on('touchend',_touchEnd,false);
        };

        var  _touchStart = function(event) {
            var e = event.touches[0];
            start = {
                pageX: e.pageX,
                pageY: e.pageY,
                time: Number(new Date())
            };
            isScrolling = undefined;
            deltaX = 0;
            container[0].style.webkitTransitionDuration = 0;
            gestureStarted = true;
        };

        var _touchMove = function(event) {
            if(!gestureStarted)return;
            var e = event.touches[0];
            deltaX = e.pageX - start.pageX;
            if ( typeof isScrolling == 'undefined') {
                //根据X、Y轴的偏移量判断用户的意图是左右滑动还是上下滑动
                isScrolling = Math.abs(deltaX) < Math.abs(e.pageY - start.pageY)
            }
            if (!isScrolling) {
                event.preventDefault();
                //判定是否达到了边界即第一个右滑、最后一个左滑
                var isPastBounds = !index && deltaX > 0 || index == slideNum - 1 && deltaX < 0;
                if(isPastBounds)return;
                var pos = (deltaX - index * slideWidth);
                container[0].style.webkitTransform = 'translate3D('+pos+'px,0,0)';
            }
        };

        var _touchEnd = function(e) {
            //判定是否跳转到下一个卡片
            //滑动时间小于250ms或者滑动X轴的距离大于屏幕宽度的1/3，则直接跳转到下一个卡片
            var isValidSlide = (Number(new Date()) - start.time < 250 && Math.abs(deltaX) > 20) || Math.abs(deltaX) > slideWidth/3;
                //判定是否达到了边界即第一个右滑、最后一个左滑
            var isPastBounds = !index && deltaX > 0 || index == slideNum - 1 && deltaX < 0;
            if (!isScrolling) {
                if(beforeSlide(index,deltaX)){
                    _slide( index + ( isValidSlide && !isPastBounds ? (deltaX < 0 ? 1 : -1) : 0 ), speed );
                }else{
                    _slide(index);
                }
            }
            gestureStarted = false;
        };


        _init();
        _bindEvents();

        this.refresh = function(){
            container.attr('style','');
            _init();
        };

        this.prev = function() {
            if (index) _slide(index-1, speed);
        };

        this.next = function() {
            if(index < slideNum-1){
                _slide(index+1, speed);
            }
        };
        this.index = function(i) {
            _slide(i);
        };
    }
    B.Slider = slider;
})(B.$);