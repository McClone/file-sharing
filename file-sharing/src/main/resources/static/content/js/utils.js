(function ($) {
    $.extend({
        ajaxUtils: function (options) {
            options.error = function (data) {
                $.messager.alert("提示", JSON.parse(data.responseText).error, "warning");
            };
            $.ajax(options);
        },
        jsonRequest: function (options) {
            options.contentType = 'application/json';
            $.ajaxUtils(options);
        }
    });


})(jQuery);

var WS = {};
WS.socket = null;
WS.init = function (options) {
    var host;
    if (window.location.protocol == 'http:') {
        host = 'ws://' + window.location.host + options.host;
    } else {
        host = 'wss://' + window.location.host + options.host;
    }
    if ('WebSocket' in window) {
        WS.socket = new WebSocket(host);
    } else if ('MozWebSocket' in window) {
        WS.socket = new MozWebSocket(host);
    } else {
        console.warn('此浏览器不支持WebSocket.');
    }

    WS.socket.onopen = function () {
        options.onOpen();
    };

    WS.socket.onclose = function (e) {
        options.onClose();
    };

    WS.socket.onmessage = function (e) {
        options.onMessage(e);
    };
    WS.socket.onerror = function (e) {

    }
};
WS.send = function (message) {
    WS.socket.send(message);
};