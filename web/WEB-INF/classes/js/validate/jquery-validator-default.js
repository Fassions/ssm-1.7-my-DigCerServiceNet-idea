$(function() {
       $.validator.setDefaults({
			submitHandler : function(form) {
				form.submit();
			}
		});

		//字符验证		 
		jQuery.validator.addMethod("stringCheck",
				function(value, element) {
					return this.optional(element)
							|| /^[\u0391-\uFFE5\w]+$/.test(value);
				}, "只能包括中文字、英文字母、数字和下划线");

		// 身份证号码验证 
		jQuery.validator.addMethod("isIdCardNo", function(value, element) {
			return this.optional(element) || /(^\d{15}$)|(^\d{17}(\d|X)$)/.test(value);
		}, "请输入正确的身份证号码");

		// 机构代码验证 
		jQuery.validator.addMethod("isUnitCode", function(value, element) {
			return this.optional(element) || /^[0-9]+$/.test(value);
		}, "请输入正确的单位编码");
		
});
