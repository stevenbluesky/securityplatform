/*******************************************************************************
 * 
 * @Name: 表单验证插件 v1.0.2 -- 基于jQuery
 * @TODO: 实时验证
 * @Author: 李必琪
 * @脚本举例 
 * 	<code>
 	var validate = require('validate');
	if (validate.checkFrom("#form_Id")) {
		//处理函数
	}
	if (validate.checkEle("#ele_Id")) {
		//处理函数
	}
 	</code>
 	tobe 函数 传参表示 对比
 *@ update 新增验证单个表单元素功能 
 * 
 ******************************************************************************/
"use strict";
var $ = require('jquery'),
	layer = require('../layer/layer');
var validate = {
	/**
	 * 验证正则条件
	 */
	rule: {
		"*": /^[\w\W]+$/, //任意不为空
		"null":/\S+$/, 	//不包含空格以及特殊自负床
		"n": /^\d+$/, //数字
		"s": /^[\u4E00-\u9FA5\uf900-\ufa2d\w\.\s]+$/, //特殊字符
		"p": /^[0-9]{6}$/, //邮政编码
		"m": /(^1[3,4,5,7,8]{1}[0-9]{9}$)/, //手机
		"gh": /^(\d{3,4}\-?)?\d{7,8}$/, //固话
		"tel": /^(\d{3,4}\-?)?\d{7,8}$|^1[3,4,5,7,8]{1}[0-9]{9}$/, //联系电话
		"f": /^((\+?[0-9]{2,4}\-[0-9]{3,4}\-)|([0-9]{3,4}\-))?([0-9]{7,8})(\-[0-9]+)?$/, //传真
		"e": /^\w+([-+.']\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/, //邮箱
		"url": /^http[s]{0,1}:\/\/.{1,200}$/ , //地址路径
		"zh": /^[\u4E00-\u9FA5\uf900-\ufa2d]+$/, //中文
		"money": /^(([1-9]+)|([0-9]+(\.[0-9]{1,2})?))$/, //金钱/^((\d{1,3}(,\d{3})+?|\d+)(\.\d{1,2})?|(\.\d{1,2}))$/
		"u": /^[a-zA-Z][a-zA-Z0-9_]$/, //用户名
		"up": /[A-za-z]+[0-9]+/, //用户密码
		"em": /(^\w+([-+.']\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$)|(^1[3,4,5,7,8]{1}[0-9]{9}$)/, //手机或者邮箱
		"sfz": /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/, //身份证
		"urlCode":/^(?!.+?(?:(html))$).+$/,		//urlCode 
		"yzm": /^\d{6}$/, //验证码
		"hz": /^(P\d{7}|G\d{8}|S\d{7,8}|D\d+|1[4,5]\d{7})$/ //护照
	},
	/**
	 * 验证提示信息
	 */
	msg: {
		"*": label.needchar,
		"null":label.valinull,
		"n": label.valin,
		"s": label.valis,
		"p": label.valip,
		"m": label.needphonenumber,
		"gh": label.valigh,
		"tel": "请输入正确的手机号或座机号，座机格式如010-62555255或010-6255255-分机号",
		"f": "请填写传真号码！",
		"e": label.valie,
		"url": "请填写网址！",
		"zh": "请填写个{limit}中文汉字!",
		"money": label.valimoney,
		"u": label.valiu,
		"em": label.valiem,
		"up": label.valiup,
		"sfz": label.valisfz,
		"hz": label.valihz,
		"urlCode":"个性网站不能包含.html",
		"tobe": label.valitobe,
		"yzm": label.valiyzm,
		"radio": label.valiradio,
		"select": label.valiselect
	},
	// 获取元素值
	getValue: function(_this) {
		var value;
		if (_this.hasClass("u-radio")) { //处理单选框
			value = _this.parent().find(".u-radio:checked").val() || "";
		} else {
			value = _this.val();
		}
		return value;
	},
	/**
	 * @Description: 检验
	 * @Author: xxb03
	 * @Version: V1.00
	 * @Create Date: 2014-12-10下午4:22:12
	 * @Parameters:checkElement 表单元素
	 * @Return true | false
	 */
	checkFun: function(checkElement, type) {
		var that = this,
			flag = true,
			funcall = function(index, element) {
				var _this = $(this),
					datatype = that.getDatatype(_this);
				var value = that.getValue(_this);
				for (var i in datatype) {
					var dt = datatype[i].dt,
						dtLimit = datatype[i].dtLimit || null,
						msg = that.msg[dt];
					//获取正则
					var reg = that.rule[dt];
					//判断元素是否匹配正则
					if (value.replace(/\s/g, "").length === 0 && _this.attr("nocheck") == "nocheck") {
						flag = true;
					} else if (/tobe/.test(dt)) {
						var $tobe = $("#" + dt.replace("tobe(", "").replace(")", ""));
						if (value != that.getValue($tobe)) {
							layer.tips(that.msg.tobe, _this, {
								tips: 1,
								time: 3000
							});
							flag = false;
							return false;
						}
					} else {
						//处理类型
						if (dtLimit != null) {
							//处理长度
							var start = parseInt(dtLimit[0], 10),
								end = parseInt(dtLimit[1], 10);
							reg = eval(("" + reg).replace("+", "{" + start + "," + end + "}"));
							//处理返回信息
							msg = msg.replace("{limit}", "" + start + "-" + end + "");
						} else {
							msg = msg.replace("{limit}", "");
						}
						if (!reg.test(value)) {
							if (_this.hasClass("u-radio")) { //处理单选框
								msg = that.msg.radio;
							} else if (_this.hasClass("u-select")) { //处理下拉框select
								msg = that.msg.select;
							}
							_this.focus();
							var errMsg = $(_this).attr("errMsg");
							if(errMsg && errMsg.length > 0)
								msg = errMsg;
							
							layer.tips(msg, _this, {
								tips: 1,
								time: 3000
							});
							flag = false;
							return false;
						}
					}
				}
			};
		$(checkElement + "[datatype]:visible").add($(checkElement).find("[datatype]:visible")).each(funcall);
		return flag;
	},
	/**
	  * @Description: 获取元素上检验格式
	  * @Author: xxb03
	  * @Version: V1.00 
	  * @Create Date: 2014-12-11上午11:22:37
	  * @Parameters:@param _this  
	  * 	<code>*|n{1,3} 代表 不为空 且 为数字</code>
	  * @Return 
	  *	[{
			dt: 检验格式,
			dtLimit : 限制长度
		}]
	 */
	getDatatype: function(_this) {
		var dts = _this.attr("datatype").replace(/\s/g, "").split("|"),
			dtArray = [];
		for (var i in dts) {
			var dt = dts[i],
				dtLimitNum = [],
				dtLimit;
			//如果datatype中包含数字
			if (/\d/.test(dt)) {
				dtLimit = dt.substr(0, dt.indexOf("{"));
				dtLimitNum = dt.substr(dt.indexOf("{") + 1, dt.indexOf("}") - 2).split(",");
				dtArray.push({
					dt: dtLimit,
					dtLimit: dtLimitNum
				});
			} else {
				dtArray.push({
					dt: dt
				});
			}
		}
		return dtArray;
	},
	/**
	 * @Description: 检验form 表单元素 入口
	 * @Author: xxb03
	 * @Version: V1.00
	 * @Create Date: 2014-12-10下午4:22:12
	 * @Parameters:FormId 表单id
	 * @Return void
	 */
	check: function(FormId) {
		var that = this;
		try {
			return that.checkFun(FormId);
		} catch (e) {
			console.log(e);
		}
	},
	/**
	 * @Description: 检验表单单个元素入口
	 * @Author: xxb03
	 * @Version: V1.00
	 * @Create Date: 2015/12/1
	 * @Parameters:@param element
	 * @Return void
	 */
	checkEle: function(element) {
		var that = this;
		try {
			return that.checkFun(element);
		} catch (e) {
			console.log(e);
		}
	}
};

module.exports = validate;