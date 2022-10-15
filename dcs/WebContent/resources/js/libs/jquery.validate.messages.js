jQuery.extend(jQuery.validator.messages, {
	required : "Trường bắt buộc.",
	remote : "Please fix this field.",
	email : "Nhập đúng kiểu email.",
	phoneUS : "Nhập đúng kiểu số điện thoại.",
	url : "Nhập đúng kiểu URL.",
	date : "Nhập ngày đúng kiểu.",
	dateISO : "Chỉ nhập ngày.",
	number : "Chỉ nhập số.",
	digits : "Chỉ nhập số.",
	equalTo: "Nhập không khớp",
	maxlength : jQuery.validator.format("Nhập ít hơn {0} kí tự."),
	minlength : jQuery.validator.format("Nhập ít nhất {0} kí tự."),
	rangelength : jQuery.validator
			.format("Nhập trong khoảng {0} và {1} kí tự."),
	range : jQuery.validator.format("Nhập số trong khoảng {0} và {1}."),
	max : jQuery.validator.format("Nhập số nhỏ hơn {0}."),
	min : jQuery.validator.format("Nhập số lớn hơn {0}.")
});