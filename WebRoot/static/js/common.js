/// <reference path="jquery-1.8.3.min.js" />
(function () {

    /* 命名空间 */
    window.jc = window.jc || {};

    /* 媒体查询 */
    jc.isMobile = (/AppleWebKit.*Mobile/i.test(navigator.userAgent) || (/MIDP|SymbianOS|NOKIA|SAMSUNG|LG|NEC|TCL|Alcatel|BIRD|DBTEL|Dopod|PHILIPS|HAIER|LENOVO|MOT-|Nokia|SonyEricsson|SIE-|Amoi|ZTE/.test(navigator.userAgent)));

    /* 我的信息 */
    jc.my = {
        mobile: null
    };

    /* 如果不是手机 */
    if (!jc.isMobile) {
        //window.location.href = "code.htm";
    }

    /* 初始化 */
    jc.init = [];

    /* UI Ready 相关 */
    jc.aReady = [];
    jc.isReady = false;

    jc.uiReady = function (fn) {

        if (jc.isReady) {
            if (fn) fn(jc.ui);
        }
        else {
            jc.aReady.push(fn);
        }
    }


    jc.ui = {
        imgBox: {

            jump: function (index) {
                var sSrc = this.aLi.eq(index).find("img").attr("data-img");
                this.oImg.attr("src", sSrc);
                this.aLi.eq(index).find("a").addClass("active").end().siblings().find("a").removeClass("active");
            },


            init: function () {
                var _this = this;
                this.oImg = $(this.element).find(".i_showBox img");
                this.oUl = $(this.element).find(".i_ctrlBox ul");
                this.aLi = this.oUl.find("li");
                this.aA = this.aLi.find("a");
                this.oLeft = $(this.element).find(".c_left");
                this.oRight = $(this.element).find(".c_right");

                //this.aA.first().addClass("active");

                this.oUl.width(this.aLi.length * this.aLi.width());

                this.aA.click(function () {
                    _this.jump($(this).parent().index());
                });

                this.iNow = 0;
                this.iMax = this.aLi.length - 5;

                //console.log("iMax" + this.iMax)

                this.oLeft.mousedown(function () {
                    if (_this.iNow == 0) return;
                    _this.iNow--;
                    _this.oUl.stop().animate({ "left": -_this.iNow * 170 }, "fast");
                });


                this.oRight.mousedown(function () {
                    if (_this.iNow > _this.iMax) return;
                    _this.iNow++;
                    _this.oUl.stop().animate({ "left": -_this.iNow * 170 }, "fast");

                });

                this.jump(0);

            }



        },


        banner: {
            timer: null,
            now: 0,
            max: 0,
            page: null,
            jump: function (num) {

                this.now = num;
                this.page.eq(num).css("zIndex", "1").stop().animate({ opacity: 1 }, "slow").siblings().css("zIndex", "0").animate({ opacity: 0 }, "slow");
            },
            start: function () {
                var _this = this;
                this.timer = setInterval(function () {
                    _this.now++;
                    if (_this.now >= _this.max) {
                        _this.now = 0;
                    }
                    _this.jump(_this.now);
                }, 4000);
            },
            stop: function () {
                clearInterval(this.timer);
            },
            btnsShow: function () {
                $(this.oLeft).stop().animate({ left: 20, opacity: 1 }, "fast");
                $(this.oRight).stop().animate({ right: 20, opacity: 1 }, "fast");
            },
            btnHide: function () {
                $(this.oLeft).stop().animate({ left: -80, opacity: 1 }, "fast");
                $(this.oRight).stop().animate({ right: -80, opacity: 1 }, "fast");
            },
            btnRemove: function () {
                $(this.oLeft).remove();
                $(this.oRight).remove();
            },
            init: function () {
                var _this = this;

                var oElement = this.element;


                this.oLeft = $(oElement).find(".b_left");
                this.oRight = $(oElement).find(".b_right");

                var oMain = $(oElement).find(".b_main");
                this.page = oMain.find("a");

                this.max = this.page.length;

                if (this.max <= 1) {
                    this.btnRemove();
                    this.page.eq(0).css("opacity", 1);
                    return;
                }

                this.oLeft.click(function () {
                    _this.now--;
                    if (_this.now == -1) {
                        _this.now = _this.max - 1;
                    }
                    _this.jump(_this.now);
                });

                this.oRight.click(function () {
                    _this.now++;
                    if (_this.now >= _this.max) {
                        _this.now = 0;
                    }
                    _this.jump(_this.now);
                });


                $(oElement).hover(function () {
                    _this.stop();
                    _this.btnsShow();
                }, function () {
                    _this.start();
                    _this.btnHide();
                });

                this.start();

                this.jump(0);


            }

        },
        nav: {
            init: function () {
                var aItem = $(this.element).children().children();

                aItem.hover(function () {
                    $(this).addClass("active");
                    $(this).find("ul").stop().slideDown("fast");
                }, function () {
                    $(this).removeClass("active");
                    $(this).find("ul").stop().slideUp("fast");
                });

            },
            setCurrent: function (str) {
                var aItem = $(this.element).children().children();
                aItem.each(function (i, obj) {
                    var oName = $(obj).find(".t_name");

                    if (oName.text() == str) {
                        $(obj).addClass("current");
                    }

                });

            }
        },
        kcjs: {

            init: function () {
                var aChild = $(this.element).children();


                aChild.each(function (i, obj) {
                    if (i % 2) {
                        obj.style.cssText = "float:right;"
                    }
                    else {
                        obj.style.cssText = "float:left;"
                    }

                    var aA = $(obj).find(".r_title a");
                    var aPage = $(obj).find(".r_main").children();


                    aA.first().addClass("active");
                    aPage.first().addClass("active");

                    aA.mouseover(function () {

                        $(this).addClass("active").siblings().removeClass("active");

                        aPage.eq($(this).index()).addClass("active").siblings().removeClass("active");

                    });

                });




                /*
                var aA = $(this.element).find(".r_title a");

                var aPage = $(this.element).find(".r_main").children();
                aPage.first().addClass("active");

                aA.click(function () {
                $(this).addClass("active").siblings().removeClass("active");
                aPage.eq($(this).index()).addClass("active").siblings().removeClass("active");
                });
                */

            }

        }

    }


    jc.init.push(function () {
        var aDiv = document.querySelectorAll ? document.querySelectorAll('[data-ui]') : document.getElementsByTagName('div');
        for (var i = 0, l = aDiv.length; i < l; i++) {
            var cur = aDiv[i];
            var sPlugName = cur.getAttribute("data-ui");

            if (!jc.ui[sPlugName]) jc.ui[sPlugName] = {};

            jc.ui[sPlugName].element = cur;

            if (jc.ui[sPlugName].init) {
                jc.ui[sPlugName].init();
            }
        }
        jc.isReady = true;


        for (var i = 0, l = jc.aReady.length; i < l; i++) {
            jc.aReady[i](jc.ui);
        }
    });


    $(function () {
        for (var i = 0, l = jc.init.length; i < l; i++) {
            jc.init[i]();
        }
    });




})();