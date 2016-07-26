/*
 * by txb 55342775@qq.com
 * h5与native之间的交互测试
 * */

$(function() {
    $('.demo').each(function() {
        var _this = this;
        $('.native', _this).click(function() {
            //$('#debug').append('start....');
            var data = $(".data", _this).val() || "{}";
            var callback = $(".callback", _this).val() || "";
            if ($(this).hasClass('link')) {
                location.href = $(this).attr('href') + encodeURIComponent(data)
            } else {
                //if (callback) {
                var method = $(this).attr('href').split('//')[1].split('?')[0];
                window.JSBridge.requestHybrid({
                    method: method,
                    data: JSON.parse(data),
                    callbackName: callback,
                    callback: function(data) {
                        $('.result', _this).html(JSON.stringify(data));
                        conosle.log(JSON.stringify(data));
                    }
                });
                //}
            }
            return false;
        })
    })
});