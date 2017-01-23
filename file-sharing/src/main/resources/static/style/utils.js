/**
 * Created by mcclone on 17-1-21.
 */
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