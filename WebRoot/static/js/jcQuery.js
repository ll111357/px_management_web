(function (window) {
    window.jc = window.jc || function (id) {
        return document.getElementById(id);
    }

    jc.isIE6 = !window.XMLHttpRequest;


    jc.parseHTML = function (str) {
        var oDiv = document.createElement("div");
        var aResult = [];
        oDiv.style.display = "none";
        oDiv.innerHTML = str;
        document.body.appendChild(oDiv);

        for (var i = 0, l = oDiv.children.length; i < l; i++) {
            aResult.push(oDiv.children[i]);
        }
        document.body.removeChild(oDiv);
        return aResult;
    }




    jc.getDialog = function (url, fnSuccess, token) {

        if (token) {//如果有标识 清空之前窗口信息
            if (window[token] && window[token].length == 2) {//有加载过这个窗口
                if (document.createStyleSheet) {
                    window[token][0].cssText = "";
                }
                else {
                    document.getElementsByTagName("head")[0].removeChild(window[token][0]);
                }
                if (window[token][1].parentNode) {
                    document.body.removeChild(window[token][1]);
                }

            }
        }


        var oAjax = window.XMLHttpRequest ? new XMLHttpRequest() : new ActiveXObject("Microsoft.XMLHTTP");

        oAjax.open('GET', url /*+ "?time=" + parseInt(Math.random() * 1000)*/, true);
        oAjax.send();

        oAjax.onreadystatechange = function () {
            if (oAjax.readyState == 4) {
                if (oAjax.status == 200) {
                    var str = oAjax.responseText;

                    //样式表
                    sStyleInner = str.match(/<style type=\"text\/css\">([\s\S]*?)<\/style>/)[1];
                    //脚本
                    aScriptInner = str.match(/<script>([\s\S]*?)<\/script>/);
                    //HTML
                    sBodyInner = str.match(/<body>([\s\S]*?)<\/body>/)[1];

                    oDialog = document.body.appendChild(jc.parseHTML(sBodyInner)[0]);
                    var oStyle = null;




                    if (aScriptInner) {//有JS
                        var sScript = aScriptInner[1];
                        if (!!(window.attachEvent && !window.opera)) {
                            //ie 
                            execScript(sScript);
                        } else {
                            //not ie 
                            window.eval(sScript);
                        }

                        //window.eval(aScriptInner[1]);

                    }
                    else {
                        //木有js的时候 do someThink
                    }


                    if (document.createStyleSheet) {//IE
                        oStyle = document.createStyleSheet();
                        oStyle.cssText = sStyleInner;
                    }
                    else {//W3C
                        oStyle = document.createElement("style");
                        with (oStyle) {
                            type = "text/css";
                            innerHTML = sStyleInner;
                        }
                        document.getElementsByTagName("head")[0].appendChild(oStyle);
                    }



                    var oResult = new jc.Dialog(oDialog, oStyle);


                    if (token) {
                        window[token] = new Array();
                        window[token].push(oStyle);
                        window[token].push(oDialog);
                    }

                    if (fnSuccess) fnSuccess(oResult, oDialog);

                }
                else {
                    //opt.fnFaild && opt.fnFaild(oAjax.status);
                }
            }
        };

    }

    jc.Dialog = function (oDialog, oStyle) {
        this.dialog = typeof oDialog === "string" ? document.getElementById(oDialog) : oDialog;
        this.styleTable = oStyle;
        this.mask = null;
        this.init();
    }
    jc.Dialog.prototype.init = function () {
        this.dialog.style.cssText = 'position:fixed;_position:absolute;filter:alpha(opacity=0);opacity:0;display:none;z-index:10000;'
    }

    jc.Dialog.prototype.show = function (fnEnd) {

        if (!this.dialog) return;
        if (!this.mask) {
            this.mask = document.createElement("div");
            document.body.appendChild(this.mask);
            this.mask.style.cssText = 'position:fixed;_position:absolute;top:0;left: 0;width:100%;height:100%;background :#545454;zIndex = 9999;filter:alpha(opacity=0);opacity:0;' + (jc.isIE6 ? "height:" + document.body.clientHeight + "px;" : "");
        }
        this.dialog.style.display = "block";
        jc.animate(this.mask, { opacity: 0.5 }, 16);
        jc.animate(this.dialog, { opacity: 1 }, 16);
        if (fnEnd) fnEnd();

    }
    jc.Dialog.prototype.hide = function (fnEnd) {
        if (!this.dialog) return;
        var _this = this;
        jc.animate(this.dialog, { opacity: 0 }, 16, function () {
            this.style.display = "none";
        });
        jc.animate(this.mask, { opacity: 0 }, 16, function () {
            _this.mask = null;
            document.body.removeChild(this);
            if (fnEnd) fnEnd();
        });
    }
    jc.Dialog.prototype.remove = function () {
        document.body.removeChild(this.dialog);
        if (this.styleTable) {
            if (document.createElement) {
                this.styleTable.cssText = "";
            } else {
                document.getElementsByTagName("head")[0].removeChild(this.styleTable);
            }
        };
        this.dialog = undefined;
    }





    jc.each = function (arr, fn) {
        for (var i = 0, l = arr.length; i < l; i++) {
            if (fn) fn(arr[i], i);
        }
    }

    jc.setStyle = function (obj, attr, value) {
        if (attr != "opacity") {
            obj.style[attr] = value + "px";
        }
        else {
            obj.style.opacity = value;
            obj.style.filter = "alpha(opacity=" + (value * 100) + ")";
        }
    }

    jc.range = function (iNow, iMax, iMin) {
        if (iNow > iMax) {
            return iMax;
        }
        else if (iNow < iMin) {
            return iMin;
        }
        else {
            return iNow;
        }
    }

    jc.drag = function (obj, fnEnd, oParent) {
        obj.onmousedown = function (e) {
            var e = e || event;
            var iX = e.clientX - obj.offsetLeft;
            var iY = e.clientY - obj.offsetTop;

            document.onmousemove = function (e) {
                var e = e || event;
                var iL = e.clientX - iX;
                var iT = e.clientY - iY;

                if (oParent) {
                    iL = jc.range(iL, oParent.offsetWidth - obj.offsetWidth, 0);
                    iT = jc.range(iT, oParent.offsetHeight - obj.offsetHeight, 0);
                }
                obj.style.left = iL + "px";
                obj.style.top = iT + "px";
                return false;
            }
            document.onmouseup = function () {
                document.onmousemove = null;
                document.onmouseup = null;
            }
            return false;
        }

    }


    jc.animate = function (obj, json, step, fnEnd) {
        clearInterval(obj.timer);
        if (step && typeof step === "function") {
            fnEnd = step;
            step = 20;
        }
        var iCount = 0;
        var oOld = {};
        var oPos = {};
        for (var attr in json) {
            oOld[attr] = parseFloat(obj.currentStyle ? obj.currentStyle[attr] : getComputedStyle(obj, false)[attr])
            oOld[attr] = isNaN(oOld[attr]) ? 0 : oOld[attr];

            if (json[attr] != oOld[attr]) {
                oPos[attr] = (json[attr] - oOld[attr]) / step;
            }
        }
        obj.timer = setInterval(function () {
            if (iCount < step) {
                iCount++;
                for (var attr in oPos) {
                    oOld[attr] += oPos[attr];
                    jc.setStyle(obj, attr, oOld[attr]);
                }
            }
            else {
                clearInterval(obj.timer);
                for (var attr in oPos) { jc.setStyle(obj, attr, json[attr]) }
                if (fnEnd) fnEnd.call(obj);
            }

        }, 30);

    }


    jc.addListener = function (obj, attr, fn) {//事件绑定
        if (!obj) return;
        if (obj.addEventListener) {// W3C
            obj.addEventListener(attr, fn, false);
        } else {
            obj.attachEvent("on" + attr, fn);

        }
    }

    jc.removeListener = function (obj, attr, handler) {//移除事件绑定
        if (obj.removeEventListener) {// W3C
            obj.removeEventListener(attr, handler, false);
        }
        else {
            obj.detachEvent("on" + attr, handler);
        }
    }

    jc.configJson = function (json1, json2) {
        if (typeof json1 != "object" || typeof json2 != "object") {
            return false;
        }
        var bResult = true;



        for (var attr in json1) {
            if (!json2[attr]) {
                bResult = false;
                break;
            }
            else {
                if (json2[attr] !== json1[attr]) {
                    bResult = false;
                    break;
                }
            }

        }

        return bResult;
    }

    jc.inputConfig = function () {
        jc.addListener(window, "load", function () {
            var aInput = document.getElementsByTagName("input");


            for (var i = 0, l = aInput.length; i < l; i++) {
                if (aInput[i].getAttribute("default")) {
                    aInput[i].attr = aInput[i].getAttribute("default");
                    aInput[i].value = aInput[i].attr;
                    aInput[i].style.color = "#999";
                    aInput[i].onfocus = function () {
                        if (this.value == this.attr) {
                            this.value = "";
                        }
                        this.style.color = "#333";
                    }
                    aInput[i].onblur = function () {
                        if (this.value == this.attr) {
                            this.style.color = "#999";
                        }
                        else if (this.value == "") {
                            this.value = this.attr;
                            this.style.color = "#999";
                        }
                    }

                }
            }


        });

    }

    jc.append = function (oParent, args) {
        for (var i = 1, l = arguments.length; i < l; i++) {
            oParent.appendChild(arguments[i]);
        }


    }

    jc.Search = function (obj, fn_strChanges, iMaxLength) {
        this.oDiv = obj;
        this.oLeft = obj.children[0];
        this.oRight = obj.children[1];
        this.oInput = this.oRight.getElementsByTagName("input")[0];
        this.oPanel = this.oRight.getElementsByTagName("div")[0];
        this.fn_changes = fn_strChanges;
        this.iIndex = -1;
        this.init();
        this.iMax = iMaxLength;
    }


    jc.Search.prototype = {
        init: function () {
            var _this = this;
            var iDelElement = 0;
            this.oDiv.onkeyup = function (e) {
                var e = e || window.event;
                var iKeyCode = e.keyCode;


                _this.showPanel();



                //if (iKeyCode == 38 || iKeyCode == 40 || iKeyCode == 13 || iKeyCode == 32 || iKeyCode == 8) {
                //如果 按了 下  上 回车  空格 退格
                var aA = _this.oPanel.children;



                switch (iKeyCode) {
                    case 8: (function () {//退格
                        /*******************************************************************/
                        _this.oPanel.innerHTML = "";
                        _this.iIndex = 0;
                        if (_this.fn_changes) _this.fn_changes(_this.oInput.value, _this);

                        /*******************************************************************/
                        if (_this.oInput.value === "") {
                            _this.hidePanel();
                            if (iDelElement == 1) {
                                //这里删除最后一个元素
                                var aDelA = _this.oLeft.getElementsByTagName("a");
                                if (aDelA.length) {
                                    _this.oLeft.removeChild(aDelA[aDelA.length - 1]);
                                    _this.toWidth();
                                }
                                iDelElement = 0;
                                return;
                            }
                            iDelElement++;
                            return;
                        }
                        iDelElement = 0;
                    })();
                        break;
                    case 38: (function () {//上
                        _this.iIndex--;
                        _this.iIndex = _this.range(_this.iIndex, 0, aA.length - 1);
                    })();
                        break;
                    case 40: (function () {//下
                        _this.iIndex++;
                        _this.iIndex = _this.range(_this.iIndex, 0, aA.length - 1);
                    })();
                        break;
                    case 13: (function () {//回车
                        if (aA[_this.iIndex] === undefined) {
                            _this.hidePanel();
                            return;
                        }
                        _this.add(aA[_this.iIndex].innerHTML);

                        _this.oPanel.innerHTML = "";
                        _this.oInput.value = "";
                        _this.hidePanel();
                    })();
                        break;

                    case 32: (function () {//空格
                        /*
                        var str = _this.oInput.value;
                        str = str.replace(/\s+/g, "");
                        if (!str) return; //如果是空字符就返回
                        _this.add(_this.oInput.value.replace(/\s+/g, ""));
                        _this.oInput.value = "";
                        _this.hidePanel();
                        */
                        var str = _this.oInput.value;
                        if (str.charAt(str.length - 1) === " ") {
                            _this.add(str.replace(/\s+/g, ""));
                            _this.oInput.value = "";
                            _this.hidePanel();
                        }

                        /*******************************************************************/
                        _this.oPanel.innerHTML = "";
                        _this.iIndex = 0;
                        if (_this.fn_changes) _this.fn_changes(_this.oInput.value, _this);

                        /*******************************************************************/
                    })();
                        break;
                }
                _this.fixActive();
                if (aA[_this.iIndex]) {
                    aA[_this.iIndex].className = "active";
                }

                // return;
                // }

                else {
                    //console.log("更新")

                    //each panel里面的内容

                    /*
                    _this.oPanel.innerHTML = "";
                    _this.iIndex = 0;
                    //注册事件
                    if (_this.fn_changes) _this.fn_changes(_this.oInput.value, _this);
                    */
                    jc.addListener(document, "click", function () {
                        _this.hidePanel();
                    });

                }




            }



            this.oPanel.onclick = function (e) {
                var e = e || window.event;
                var oTarget = e.srcElement || e.target;
                if (oTarget.tagName == "A") {
                    _this.oInput.value = "";
                    _this.hidePanel();
                    _this.add(oTarget.innerHTML);
                }

            }


        },
        add: function (str) {
            if (!str) return;


            var _this = this;

            var oA = document.createElement("a");
            var oSpan = document.createElement("span");
            var oI = document.createElement("i");

            oA.className = "l_item";
            oA.href = "javascript:;";


            oSpan.innerHTML = str;


            oI.innerHTML = "×";


            oA.appendChild(oSpan);
            oA.appendChild(oI);

            oA.onclick = function () {
                _this.oLeft.removeChild(this);
                _this.toWidth();
            }

            if (this.iMax > -1) {
                var aA = this.oLeft.getElementsByTagName("a");
                if (aA.length == this.iMax) {
                    alert("已选项目不能超过" + this.iMax + "个");
                    return;
                }
            }

            this.oLeft.appendChild(oA);
            this.toWidth();
        },
        toWidth: function () {
            /*
            var aA = this.oLeft.getElementsByTagName("a");
            var iWidth = 0;
            for (var i = 0, l = aA.length; i < l; i++) {

            iWidth += aA[i].offsetWidth;

            }
            this.oDiv.style.paddingLeft = iWidth + "px";
            */
            this.oDiv.style.paddingLeft = this.oLeft.offsetWidth + "px";
        },
        push: function (str, value) {
            var _this = this;

            var aA = this.oPanel.children;

            var oA = document.createElement("A");
            oA.href = "javascript:;";
            oA.innerHTML = str;
            oA.onmouseover = function () {
                _this.fixActive();
                this.className = "active";
                _this.iIndex = this.index;
            }

            oA.index = aA.length;


            if (value) {
                oA.token = value;
            }
            this.oPanel.appendChild(oA);
        },
        range: function (iNow, iMin, iMax) {
            if (iNow < iMin) {
                return iMin;
            }
            else if (iNow > iMax) {
                return iMax;
            }
            else {
                return iNow;
            }

        },
        fixActive: function () {
            var aA = this.oPanel.children;

            for (var i = 0, l = aA.length; i < l; i++) {
                aA[i].className = "";
            }

        },
        showPanel: function () {
            this.oPanel.style.display = "block";
        },
        hidePanel: function () {
            this.oPanel.style.display = "none";
        },
        toString: function () {
            var aResult = [];
            var aA = this.oLeft.getElementsByTagName("a");
            for (var i = 0, l = aA.length; i < l; i++) {
                aResult.push(aA[i].getElementsByTagName("span")[0].innerHTML);

            }

            return aResult.join(";");

        },
        toElement: function (str) {
            str = str.split(";");
            for (var i = 0, l = str.length; i < l; i++) {
                this.add(str[i]);
            }
        }

    }


    jc.page = function (obj, iNow, iMax) {
        //如果等于0页;
        if (iMax === 0) return;

        //首页
        var oFirst = document.createElement("a");
        oFirst.innerHTML = "首页"
        //上一页
        var oPrev = document.createElement("a");
        oPrev.innerHTML = "上一页"
        //子项目
        var oFrag = document.createDocumentFragment();



        var iStart = 1;
        var iTarget = 0;



        if (iNow - 5 < 1) {//如果传入的小于5 开始也是1
            // console.log("走IF")
            iStart = 1;
            iTarget = iMax < 9 ? iMax : 9;
        }

        else if (iNow > iMax - 5) {//如果大于Max
            // console.log("走else if")
            iStart = iMax - 8;
            iTarget = iMax;
        }

        else {
            //console.log("走else")
            iStart = iNow - 4;
            iTarget = iNow + 4;

        }


        //    console.log(iStart, iTarget)



        for (var i = iStart; i <= iTarget; i++) {
            if (i == iNow) {
                var oElement = document.createElement("span");
            }
            else {
                var oElement = document.createElement("a");
                oElement.href = "#";
            }
            oElement.innerHTML = i;
            oFrag.appendChild(oElement);
        }



        //下一页
        var oNext = document.createElement("a");
        oNext.innerHTML = "下一页";
        //尾页
        var oLast = document.createElement("a");
        oLast.innerHTML = "尾页";



        jc.append(obj, oFirst, oPrev, oFrag, oNext, oLast);





    }


    jc.inputConfig();

})(window);
    