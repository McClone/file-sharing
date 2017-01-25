$(function () {

    initNavbar();
    initPopover();

    //开启WebSocket连接
    if ($('#user_name').text()) {
        connect('/websocket', '/topic/pushed', handlerMessage);
    }

});

//处理推送的信息
function handlerMessage(data) {
    var $badge = $('#msg_badge');
    $('#content_val').append('<div>' + data.body + '</div>');
    var count = $badge.text();
    $badge.text(parseInt(count) + 1);
}

function initNavbar() {
    //主页导航栏菜单按钮时间
    $(".content").on('click', function (e) {
        $('.menu-list li.active').removeClass("active");
        $(this).parent().addClass("active");
        $('#content').panel({
            href: '/content/' + $(this).attr('id') + '.html'
        });
    });
    //默认选中项
    $('.menu-list li:last a.content').trigger('click');
}

function initPopover() {
    var $popover = $('#msg_popover');
    var $badge = $('#msg_badge');
    $popover.popover({
        html: true,
        title: '消息',
        content: '<div id="msg_content"></div>'
    });

    //当调用 show 实例方法时立即触发该事件。
    $popover.on('shown.bs.popover', function () {
        var $contentVal = $('#content_val');
        $('#msg_content').html($contentVal.html());
        $contentVal.html('');
        $badge.text(0);
    });
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