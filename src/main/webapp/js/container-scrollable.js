$(function () {
    if ($(window).width() > 1200) {
        var windowHeight = $(window).height();
        $('.container-scrollable').each(function () {
            var elementHeight = windowHeight - $(this).offset().top - 15;
            if (elementHeight > 0 && parseInt($(this).css("height")) > elementHeight) {
                $(this).css("overflow-y", "scroll").css('height', elementHeight);
            }
        });
    }
});
