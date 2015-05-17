//The validate rule is defined by using required and validType property, here are the rules already implemented:
//email: Match email regex rule.
//url: Match URL regex rule.
//length[0,100]: Between x and x characters allowed.
//remote['http://.../action.do','paramName']: Send ajax request to do validate value, return 'true' when successfully.

$.extend($.fn.validatebox.defaults.rules, {
	minLength : {
		validator : function(value, param) {
			return value.length >= param[0];
		},
		message : '最少长度为{0}字符.'
	},
	maxLenght : {
		validator : function(value, param) {
			return value.length <= param[0];
		},
		message : "最大长度为{0}字符."
	},
	int : {
		validator : function(value, param) {
			return /^-?\d+$/.test(value);
		},
		message : "只能为数字！"
	},
	mobile : {
		validator : function(value, param) {
			return /^1[3|4|5|8]\d{9}$/.test(value);
		},
		message : "输入正确手机号码！"
	}
});