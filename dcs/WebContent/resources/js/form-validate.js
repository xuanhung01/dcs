CMSCommonJs.Form.checkDonViByMa = function(rootUrl, maDV) {
	var output = false;
	if (maDV && maDV.length == 7) {
		$.ajax({
			url : CMSCommonJs.ROOT_URL + 'ajax/danhmuc/dvsdns/check',
			timeout : 10000,
			dataType : "json",
			contentType : "text/plain",
			type : "GET",
			data : {
				maDV : maDV
			},
			async : false,
			success : function(data) {
				output = (data || data == "true");
			}
		});
	}
	return output;
};

CMSCommonJs.Form.checkDuAnByMa = function(rootUrl, ma) {
	var output = false;
	if (ma && ma.length == 7) {
		$.ajax({
			url : CMSCommonJs.ROOT_URL + 'ajax/danhmuc/duan/check',
			timeout : 10000,
			dataType : "json",
			contentType : "text/plain",
			type : "GET",
			data : {
				ma : ma
			},
			async : false,
			success : function(data) {
				output = (data || data == "true");
			}
		});
	}
	return output;
};

CMSCommonJs.Form.checkDuAnCBDTByMa = function(rootUrl, ma) {
	var output = false;
	if (ma && ma.length == 7) {
		$.ajax({
			url : CMSCommonJs.ROOT_URL + 'ajax/danhmuc/duan/cbdt/check',
			timeout : 10000,
			dataType : "json",
			contentType : "text/plain",
			type : "GET",
			data : {
				ma : ma
			},
			async : false,
			success : function(data) {
				output = (data || data == "true");
			}
		});
	}
	return output;
};

CMSCommonJs.Form.checkDuAnVaDonVi = function(rootUrl, ma) {
	var output = false;
	if (ma && ma.length == 7) {
		$.ajax({
			url : CMSCommonJs.ROOT_URL + 'ajax/danhmuc/duanvadonvi/check',
			timeout : 10000,
			dataType : "json",
			contentType : "text/plain",
			type : "GET",
			data : {
				ma : ma
			},
			async : false,
			success : function(data) {
				output = (data || data == "true");
			}
		});
	}
	return output;
};

CMSCommonJs.Form.checkUsername = function(username) {
	var output = false;
	if (username) {
		$.ajax({
			url : CMSCommonJs.ROOT_URL + 'system/user/check/user',
			timeout : 10000,
			dataType : "json",
			contentType : "text/plain",
			type : "GET",
			data : {
				username : username
			},
			async : false,
			success : function(data) {
				output = (data || data == "true");
			}
		});
	}
	return output;
};

CMSCommonJs.Form.checkEmail = function(email, userName) {
	var output = false;
	if (userName) {
		if (email) {
			$.ajax({
				url : CMSCommonJs.ROOT_URL
						+ 'user/register/check/emailUserName',
				timeout : 10000,
				dataType : "json",
				contentType : "text/plain",
				type : "GET",
				data : {
					email : email,
					userName : userName
				},
				async : false,
				success : function(data) {
					output = (data || data == "true");
				}
			});
		}
	} else {
		if (email) {
			$.ajax({
				url : CMSCommonJs.ROOT_URL + 'user/register/check/email',
				timeout : 10000,
				dataType : "json",
				contentType : "text/plain",
				type : "GET",
				data : {
					email : email
				},
				async : false,
				success : function(data) {
					output = (data || data == "true");
				}
			});
		}
	}
	return output;
};

// Custom validations ---------------------------------------------------------
$.validator.addMethod('checkSum', function(value, element, params) {
	var sumOfVals = 0;
	var lastEl = null;
	$(params.el).each(function(i, e) {
		sumOfVals = sumOfVals + parseFloat($(this).val());
		lastEl = e;
	});

	// Only check in last element
	if (element == lastEl) {
		if (sumOfVals != params.value) {
			$(params.sumEl).html(
					"<span class='alert-danger'>" + params.alert + "</span>");
			return false;
		} else {
			$(params.sumEl).html(sumOfVals);
			return true;
		}
	} else {
		return true;
	}
}, "Không hợp lệ");

$.validator.addMethod('checkDuplicate', function(value, element, params) {
	var vals = [];
	var lastEl = null;

	function hasInVals(val) {
		for (var i = 0; i < vals.length; i++) {
			if (vals[i] == val) {
				return true;
			}
		}
		return false;
	}

	$(params.el).each(function(i, e) {
		var val = $(this).val();
		if (hasInVals(val)) {
		} else {
			if (e != element) {
				vals.push(val);
			}
		}
	});

	return !hasInVals(value);

}, "Không được trùng kiểu đã có");

$.validator.addMethod("checkMaDVSDNS", function(value, element) {
	var ma = $(element).val();
	if (ma) {
		return CMSCommonJs.Form.checkDonViByMa(CMSCommonJs.ROOT_URL, ma);
	} else {
		return true;
	}
}, "Mã không hợp lệ");

$.validator.addMethod("checkMaDuAn", function(value, element) {
	var ma = $(element).val();
	if (ma) {
		return CMSCommonJs.Form.checkDuAnByMa(CMSCommonJs.ROOT_URL, ma);
	} else {
		return true;
	}
}, "Mã không hợp lệ");

$.validator.addMethod("checkMaDuAnCBDT", function(value, element) {
	var ma = $(element).val();
	if (ma) {
		return CMSCommonJs.Form.checkDuAnCBDTByMa(CMSCommonJs.ROOT_URL, ma);
	} else {
		return true;
	}
}, "Mã không hợp lệ");

$.validator.addMethod("checkDuAnVaDonVi", function(value, element) {
	var ma = $(element).val();
	if (ma) {
		return CMSCommonJs.Form.checkDuAnVaDonVi(CMSCommonJs.ROOT_URL, ma);
	} else {
		return true;
	}
}, "Mã không hợp lệ");

$.validator.addMethod("checkUsername", function(value, element) {
	var regex = /^[a-zA-Z0-9][a-zA-Z0-9\.]{5,80}$/;
	var username = $(element).val();
	
	if (!regex.test(username))
		return false;
	if (username) {
		return CMSCommonJs.Form.checkUsername(username);
	} else {
		return true;
	}
}, "Tài khoản đã tồn tại hoặc không hợp lệ!");

$.validator.addMethod("checkEmail", function(value, element, params) {
	var email = $(element).val();
	if (email) {
//		console.log(params);
		if (params) {
			var userName = params.val();
//			console.log(userName);
			return CMSCommonJs.Form.checkEmail(email, userName);
		} else {
			return CMSCommonJs.Form.checkEmail(email);
		}
	} else {
		return true;
	}
}, "Email đã tồn tại! Vui lòng chọn email khác!");

$.validator.addMethod("soTien", function(value, element) {
	/*
	 * $(element).blur(function(e) { var txt = $(e.target).val(); txt =
	 * txt.replace(",", ""); var l = txt.length; var result = ''; for (var i =
	 * l; i > 0; i = i - 3) { var block = txt.substr(i - 3, 3); if (i > l - 3) {
	 * result = block; } else { result = block + ',' + result; } }
	 * $(e.target).val(result); });
	 */
	// var pattern = /^(\d+|\d+,\d{1,2})$/;
	var pattern = /^(\d|.|,)*$/;
	return this.optional(element) || pattern.test(value);

	// return true;
}, "Số tiền không hợp lệ");

$.validator.addMethod("checkgroupCodeId", function(value, element) {
	alert(value + ':' + element);
	return false;
}, "User Group đang để trống!");