$(function () {

    var Chat = {};

    Chat.sendMessage = function () {
        var message = $('#chat').val();
        if (message != '') {
            $('#console_table').append('<tr><td><span class="self_message">' + message + '</span></td></tr>');
            WS.send(message);
            $('#chat').val('');
        }
    };

    WS.init({
        host: '/char',
        onOpen: function () {
            console.log('Info: WebSocket connection opened.');
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
            console.log('Info: WebSocket closed.');
        }, onMessage: function (message) {
            $('#console_table').append('<tr><td><span>' + message.data + '</span></td></tr>');
        }
    });

});



