/**
 * 处理时间格式
 * @param str
 * @return
 */
function formatDate(str) {
	if (str != null) {
		return (new Date(parseInt(str))).Format("yyyy-MM-dd"); //
	}
}

function formatTime(str) {
	if (str != null) {
		return (new Date(parseInt(str))).Format("yyyy-MM-dd hh:mm:ss"); //
	}
}

Date.prototype.Format = function(fmt) {
	var o = {
		"M+" :this.getMonth() + 1, // 月份
		"d+" :this.getDate(), // 日
		"h+" :this.getHours(), // 小时
		"m+" :this.getMinutes(), // 分
		"s+" :this.getSeconds(), // 秒
		"q+" :Math.floor((this.getMonth() + 3) / 3), // 季度
		"S" :this.getMilliseconds()
		// 毫秒
	};
	if (/(y+)/.test(fmt))
        fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
	for ( var k in o)
		if (new RegExp("(" + k + ")").test(fmt))
			fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]): (("00" + o[k]).substr(("" + o[k]).length)));
			return fmt;
}

/**
 *验证身份证号码
 */
function isCardNo(card){  
   // 身份证号码为15位或者18位，15位时全为数字，18位前17位为数字，最后一位是校验位，可能为数字或字符X  
   var reg = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;  
   if(reg.test(card) === false){  
       return  false;  
   }
   return true;  
}

/**
 * 验证中文姓名
 * @param str
 * @returns {Boolean}
 */
function checkChineseName(str){ 
	// [\u4E00-\uFA29]|[\uE7C7-\uE7F3]汉字编码范围 
	var re1 = new RegExp("^([\u4E00-\uFA29]|[\uE7C7-\uE7F3])*$"); 
	if (!re1.test(str)){ 
		return false; 
	} 
	return true; 
}

/**
 * 验证手机号码
 */
function checkPhone(phone){
  	var re= /(^1[3|5|7|8][0-9]{9}$)/;
    if(!re.test(phone)){
        return false;
    }
    return true;
}

/**
 * 验证邮箱
 */
function validateEmail(field){
	var apos = field.indexOf("@")
	var dotpos = field.lastIndexOf(".")
	if (apos<1||dotpos-apos<2) {
		return false
	}
	return true;
}

/**
 * 通用提示框
 */
function custom_alert(output_msg, title_msg){
    if (!title_msg)
        title_msg = 'Alert';

    if (!output_msg)
        output_msg = 'No Message to Display.';

    $("<div></div>").html(output_msg).dialog({
        title: title_msg,
        resizable: false,
        modal: true,
        buttons: {
            "Ok": function(){
                $( this ).dialog( "close" );
            }
        }
    });
}

/**
 * 身份证号码验证
 * @param code
 * @returns {Boolean}
 */
function IdentityCodeValid(code) { 
    var city={11:"北京",12:"天津",13:"河北",14:"山西",15:"内蒙古",21:"辽宁",22:"吉林",23:"黑龙江 ",31:"上海",32:"江苏",33:"浙江",34:"安徽",35:"福建",36:"江西",37:"山东",41:"河南",42:"湖北 ",43:"湖南",44:"广东",45:"广西",46:"海南",50:"重庆",51:"四川",52:"贵州",53:"云南",54:"西藏 ",61:"陕西",62:"甘肃",63:"青海",64:"宁夏",65:"新疆",71:"台湾",81:"香港",82:"澳门",91:"国外 "};
    var tip = "";
    var pass= true;
    
    if(!code || !/^\d{6}(18|19|20)?\d{2}(0[1-9]|1[12])(0[1-9]|[12]\d|3[01])\d{3}(\d|X)$/i.test(code)){
        tip = "身份证号格式错误";
        pass = false;
    }else if(!city[code.substr(0,2)]){
        tip = "地址编码错误";
        pass = false;
    }else{
        //18位身份证需要验证最后一位校验位
        if(code.length == 18){
            code = code.split('');
            //∑(ai×Wi)(mod 11)
            //加权因子
            var factor = [ 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2 ];
            //校验位
            var parity = [ 1, 0, 'X', 9, 8, 7, 6, 5, 4, 3, 2 ];
            var sum = 0;
            var ai = 0;
            var wi = 0;
            for (var i = 0; i < 17; i++)
            {
                ai = code[i];
                wi = factor[i];
                sum += ai * wi;
            }
            var last = parity[sum % 11];
            if(parity[sum % 11] != code[17]){
                tip = "校验位错误";
                pass =false;
            }
        }
    }
    if(!pass) alert(tip);
    return pass;
}

