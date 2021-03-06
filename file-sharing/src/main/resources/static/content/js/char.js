$(function () {

    var Chat = {};

    Chat.sendMessage = function () {
        var $char = $('#chat');
        var message = $char.val();
        if (message != '') {
            $('#console_table').append('<tr><td><span class="self_message">' + message + '</span></td></tr>');
            WS.send(message);
            $char.val('');
        }
    };

    WS.init({
        host: '/char',
        onOpen: function () {
            console.log('在线状态');
            document.getElementById('chat').onkeydown = function (event) {
                if (event.keyCode == 13) {
                    Chat.sendMessage();
                }
            };
            $('#subBtn').bind('click', function () {
                Chat.sendMessage();
            });
        }, onClose: function () {
            document.getElementById('chat').onkeydown = null;
            console.log('离线状态');
        }, onMessage: function (message) {
            $('#console_table').append('<tr><td><span>' + message.data + '</span></td></tr>');
        }
    });

});



