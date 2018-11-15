/**
 * Created by eclip_000 on 2017/6/14.
 */
(function ($) {
    $.fn.breadcrumb = function (options) {
        let defaults = {
            'contentContainer': '#wrapper-content',
            'speed': 500
        };
        let loading = null;
        let settings = $.extend({}, defaults, options);
        let container = $(settings.contentContainer);
        let _this = this;

        if (container.length === 0) {
            console.error('can not find ' + settings.contentContainer);
            return;
        }
        container.css({
            "position": "relative",
            "overflow-x": "hidden",
            height:parent.$('body').height() - 91 - 37
        }).children("div").addClass("breadcrumb-content-item");

        _this.on("click", "li", function () {
            let index = $(this).children('a').attr("data-index");
            let text = $(this).children('a').text();
            if (typeof(index) === "undefined") return;
            $(this).nextAll().remove();
            $(this).text(text).children("a").remove();
            let current = container.children(".breadcrumb-content-item:visible");
            let target = container.children('.breadcrumb-content-item[data-index="'+index+'"]');
            let range = target.width();


            current.css({"position": "absolute"});
            target.stop().show().animate({left: '0px'}, settings.speed, function () {
                $(this).nextAll().remove();
            });

            current.stop().animate({left: range + 'px'}, settings.speed, function () {

            });

        });


        _this.push = function (options) {
            let title = options.title,
                url = options.url,
                data = options.data,
                callback = options.callback,
                anchor = options.anchor;

            if (!url || url === '') {
                console.error('need parameter : url ');
                return;
            }

            let index = Math.floor((Math.random() * 1000) + 1);
            let current = container.children(".breadcrumb-content-item:visible");
            let range = current.width();

            let appendNode = function () {
                let lastLi = _this.children("li:last");
                let a = $('<a></a>');
                a.attr("data-index", index).text(lastLi.text());
                if (anchor) {
                    a.attr("href", '#' + anchor);
                } else {
                    a.removeAttr("href");
                }
                lastLi.html(a);
                current.attr("data-index", index);
                let li = $('<li>' + title + '</li>');
                _this.append(li);
            };

            let $target = $('<div class="breadcrumb-content-item"></div>');
            $target.css({"left": range + 'px', "position": "absolute"}).appendTo(container);

            //开始加载内容
            loading = parent.layer.load(1, {
                shade: [0.5, '#000']
            });
            $target.load(url, data, function (response, state, xhr) {
                parent.layer.close(loading);
                let j = null;
                try {
                    j = JSON.parse(response);
                    if (j.code === "NACK") {
                        parent.$.gritter.add({
                            title: '操作失败',
                            text: j.message,
                            class_name: 'gritter-error',
                            sticky: false
                        });
                        return;
                    }
                } catch (err) {

                }
                appendNode();
                current.stop().animate({left: -range + 'px'}, settings.speed, function () {
                    $(this).hide();
                });
                $target.stop().animate({left: '0px'}, settings.speed, function () {
                    $(this).css({"position": "relative", "left": ''});
                    $('html, body').animate({scrollTop: 0}, 'fast');
                    if (state === 'success') {
                        if (callback && typeof(callback) === 'function') {
                            callback(response, status, xhr);
                        }
                    } else {
                        let doc = $.parseXML(response.replace("<!doctype html>", ''));
                        let body = $(doc).find("body").html();
                        $target.html(body);
                    }
                });
            });

        };

        _this.pop = function (options) {
            let anchor = '';
            if (options) {
                anchor = options.anchor;
            }
            let li = _this.children("li:last").prev();
            let index = li.children("a").attr("data-index");
            let text = li.children("a").text();
            if (typeof(index) === "undefined") return;

            li.nextAll().remove();
            li.text(text).children("a").remove();

            let current = container.children(".breadcrumb-content-item:visible");
            let target = container.children('.breadcrumb-content-item[data-index="'+index+'"]');
            let range = target.width();


            current.css({"position": "absolute"});
            target.stop().show().animate({left: '0px'}, settings.speed, function () {
                if (anchor && anchor.length > 0) {
                    let targetTop = $("a[name='" + anchor + "']").offset().top;
                    $('html, body').animate({scrollTop: targetTop}, 'fast');
                } else if (anchor && anchor.length === 0) {
                    $('html, body').animate({scrollTop: 0}, 'fast');
                }
            });

            current.stop().animate({left: range + 'px'}, settings.speed, function () {
                $(this).css({"position": "relative"}).remove();
            });
        };

        return this;
    }
}(jQuery));