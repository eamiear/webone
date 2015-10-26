$.cbj = function (imgUrlWX, imgUrlWB) {
    function is_weixin() {
        var ua = navigator.userAgent.toLowerCase();
        if (ua.match(/MicroMessenger/i) == "micromessenger") {
            return true;
        } else {
            return false;
        }
    }
    function is_weibo() {
        var ua = navigator.userAgent.toLowerCase();
        if (ua.match(/Weibo/i) == "weibo") {
            return true;
        } else {
            return false;
        }
    }
    var isWeixin = is_weixin();
    var isWeibo = is_weibo();
    if (isWeixin || isWeibo) {
        var winHeight = typeof window.innerHeight != 'undefined' ? window.innerHeight : document.documentElement.clientHeight;
        var weixinTip = $('<div class="weixin-tip"><p><img src="' + imgUrlWX + '" alt="微信打开"/></p></div>');
        if (isWeibo)
            weixinTip.find("img").attr("src", imgUrlWB);
        weixinTip.css("height", winHeight);
        weixinTip.show();
        $("body").prepend(weixinTip);
        $("div.weixin-tip").on("click", function () { $(this).hide(); });
    }
};