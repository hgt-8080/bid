(function ($) {
    //1.得到$.ajax的对象
    var _ajax = $.ajaxSetup;
    //
    _ajax=$.ajaxSetup({
        complete:function(XMLHttpRequest,textStatus){
            if(textStatus=="parsererror"){
                window.location.href = 'login';
            } else if(textStatus=="error"){
                $.messager.alert('提示信息', "请求超时！请稍后再试！", 'info');
            }
        }
    });
    //
    $.ajax = function (options) {
        //2.每次调用发送ajax请求的时候定义默认的error处理方法
        var fn = {
            error: function (XMLHttpRequest, textStatus, errorThrown){
                alert('请求出错!!!');
            }
        };
        //3.如果在调用的时候写了error的处理方法，就不用默认的
        if (options.error){
            fn.error = options.error;
        }
        //4.扩展原生的$.ajax方法，返回最新的参数
        var _options = $.extend(options, {
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                fn.error(XMLHttpRequest, textStatus, errorThrown);
            }
        });
        //5.将最新的参数传回ajax对象
        _ajax(_options);
    };
})(jQuery);