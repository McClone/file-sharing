/**
 * Created by McClone on 2017/1/22.
 */
$(function () {
    var $popover = $('#msg_popover');
    var $badge = $('#msg_badge');
    $popover.popover({
        html: true,
        title: '消息',
        content: showContent()
    });
    connect('/hello-websocket', '/topic/greetings', function (data) {
        $('#content_val').append(data.body);
        var count = $badge.text();
        $badge.text(parseInt(count) + 1);
    });
    //当调用 show 实例方法时立即触发该事件。
    $popover.on('shown.bs.popover', function () {
        var $contentVal = $('#content_val');
        $('#msg_content').text($contentVal.html());
        $contentVal.html('');
        $badge.text(0);
    });
});

function showContent() {
    return '<div id="msg_content"></div>';
}

function connect(endpoint, topic, handler) {
    var socket = new SockJS(endpoint);
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        stompClient.subscribe(topic, function (data) {
            handler(data);
        });
    });
}