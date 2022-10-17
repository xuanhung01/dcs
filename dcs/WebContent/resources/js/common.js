/**
 * CONSTANTS
 */

/**
 * CMS Common functions
 */
var CMS = {};
CMS.DM = {};
CMS.DM.Map = {};
CMS.DM.List = {};

CMSCommonJs = {};
CMSCommonJs.Form = CMSCommonJs.Form || {};


/**
 * Lay danh sach huyen cua 1 tinh qua ajax
 * @param id
 * @param type
 */
CMSCommonJs.Form.getDiaDiemByTinh = function(rootUrl, id) {
	var output;
	$.ajax({
		url : CMSCommonJs.ROOT_URL + 'ajax/danhmuc/diaban/onetinh',
		dataType : "json",
		type : "GET",
		data : {
			maTinh : id
		},
		async : false,
		timeout : 5000,
		success : function(data) {
			output = {
				text : data.maTinh + " - " + data.ten,
				id : data.maTinh
			}

		}
	});
	return output;
};

/**
 * Lay danh sach xa cua 1 huyen qua ajax
 * @param rootUrl
 * @param id
 * @returns {}
 */
CMSCommonJs.Form.getDiaDiemByHuyen = function(rootUrl, id) {
	var output;
	$.ajax({
		url : CMSCommonJs.ROOT_URL + 'ajax/danhmuc/diaban/onehuyen',
		dataType : "json",
		type : "GET",
		data : {
			maHuyen : id
		},
		async : false,
		timeout : 5000,
		success : function(data) {
			output = {
				text : data.maHuyen + " - " + data.ten,
				id : data.maHuyen
			}

		}
	});
	return output;
};

CMSCommonJs.Form.getDiaDiemByXa = function(rootUrl, id) {
	var output;
	$.ajax({
		url : CMSCommonJs.ROOT_URL + 'ajax/danhmuc/diaban/onexa',
		dataType : "json",
		type : "GET",
		data : {
			maXa : id
		},
		async : false,
		timeout : 5000,
		success : function(data) {
			output = {
				text : data.maXa + " - " + data.ten,
				id : data.id
			}

		}
	});
	return output;
};

CMSCommonJs.Form.getDiaDiemByQuocGia = function(rootUrl, id) {
	var output;
	$.ajax({
		url : CMSCommonJs.ROOT_URL + 'ajax/danhmuc/diaban/onequocgia',
		dataType : "json",
		type : "GET",
		data : {
			maQuocGia : id
		},
		async : false,
		timeout : 5000,
		success : function(data) {
			output = {
				text : data.maQuocGia + " - " + data.ten,
				id : data.maQuocGia
			}

		}
	});
	return output;
};

/**
 * Lay danh muc quoc gia
 * @returns
 */
CMSCommonJs.Form.getDMQuocGia = function() {
	var listDM;
	$.ajax({
		url : CMSCommonJs.ROOT_URL + 'ajax/danhmuc/diaban/quocgia',
		dataType : "json",
		type : "GET",
		data : function(params) {
		},
		async : false,
		timeout : 5000,
		success : function(data) {
			listDM = $.map(data, function(item) {
				return {
					text : item.ma + " - " + item.ten,
					id : item.id,
					ma : item.ma
				}
			});
		}
	});
	return listDM;
}
CMSCommonJs.Form.getDMTinh = function() {
	var listDM = [];
	$.ajax({
		url : CMSCommonJs.ROOT_URL + 'ajax/danhmuc/diaban/tinh',
		dataType : "json",
		type : "GET",
		data : function(params) {
		},
		async : false,
		timeout : 5000,
		success : function(data) {
			listDM = $.map(data, function(item) {
				return {
					text : item.ma + " - " + item.ten,
					id : item.ma
				}
			});
		}
	});
	return listDM;
}
CMSCommonJs.Form.getDMHuyen = function(val) {
	var listDM = [];
	if (val) {
		$.ajax({
			url : CMSCommonJs.ROOT_URL + 'ajax/danhmuc/diaban/huyen/by',
			dataType : "json",
			type : "GET",
			data : {
				ma : val
			},
			async : false,
			timeout : 5000,
			success : function(data) {
				listDM = $.map(data, function(item) {
					return {
						text : item.ma + " - " + item.ten,
						id : item.ma
					}
				});
			}
		});
	}
	return listDM;
}
CMSCommonJs.Form.getDMXa = function(val) {
	var listDM = [];
	if (val) {
		$.ajax({
			url : CMSCommonJs.ROOT_URL + 'ajax/danhmuc/diaban/xa/by',
			dataType : "json",
			type : "GET",
			data : {
				ma : val
			},
			async : false,
			timeout : 5000,
			success : function(data) {
				listDM = $.map(data, function(item) {
					return {
						text : item.ma + " - " + item.ten,
						id : item.id
					}
				});
			}
		});
	}
	return listDM;
}
CMSCommonJs.Form.getDMChuongNganSach = function(cap) {
	var output;
	$.ajax({
		url : CMSCommonJs.ROOT_URL + 'ajax/danhmuc/chuong/' + cap,
		dataType : "json",
		type : "GET",
		async : false,
		timeout : 5000,
		success : function(data) {
			output = $.map(data, function(item) {
				return {
					text : item.chuong + " - " + item.ten,
					id : item.chuong
				};
			});
		}
	});
	return output;
};

CMSCommonJs.Form.getChuongNganSachStatic = function(maChuong) {
	var result = null;
	$.each(CMS.DM.ChuongTrungUong, function(index, entry) {
		if (entry.id == maChuong) {
			result = entry;
		}
	});

	$.each(CMS.DM.ChuongDiaPhuong, function(index, entry) {
		if (entry.id == maChuong) {
			result = entry;
		}
	});
	return result;
}
CMSCommonJs.Form.getChuongNganSach = function(rootUrl, id) {
	var output;
	$.ajax({
		url : CMSCommonJs.ROOT_URL + 'ajax/danhmuc/chuong/machuong',
		timeout : 10000,
		dataType : "json",
		type : "GET",
		data : {
			maChuong : id
		},
		async : false,
		timeout : 5000,
		success : function(data) {
			return data;
		}
	});
	return output;
};
CMSCommonJs.Form.getChuongNganSachSelect2 = function(rootUrl, id) {
	var output;
	$.ajax({
		url : CMSCommonJs.ROOT_URL + 'ajax/danhmuc/chuong/machuong',
		timeout : 10000,
		dataType : "json",
		type : "GET",
		data : {
			maChuong : id
		},
		async : false,
		timeout : 5000,
		success : function(data) {
			output = {
				text : data.chuong + " - " + data.ten,
				id : data.chuong
			}
		}
	});
	return output;
};

/**
 * Create dia diem list ajax
 * 
 * @param rootUrl
 * @param tinhSelect
 * @param huyenSelect
 * @param xaSelect
 */
CMSCommonJs.Form.createDiaDiemList = function(editable, tinhSelect,
		huyenSelect, xaSelect, quocGiaSelect, diaChiInput) {
	var idTinh = $(tinhSelect).val();
	var idHuyen = $(huyenSelect).val();
	var idXa = $(xaSelect).val();

	if (CMS.DM.QUOCGIA == undefined) {
		CMS.DM.QUOCGIA = CMSCommonJs.Form.getDMQuocGia();
		CMS.DM.QUOCGIA_VN = $.grep(CMS.DM.QUOCGIA, function(entry, index) {
			return entry.ma == 'VN';
		})[0].id;
	}

	if (CMS.DM.TINH == undefined) {
		CMS.DM.TINH = CMSCommonJs.Form.getDMTinh();
	}

	var isQuocGiaVN = function() {
		return (quocGiaSelect && ($(quocGiaSelect).select2('val') == CMS.DM.QUOCGIA_VN || $(
				quocGiaSelect).val() == CMS.DM.QUOCGIA_VN))
				|| quocGiaSelect == undefined;
	}

	var refreshHuyen = function(val) {
		if (val) {
			$(xaSelect).select2('enable');
			$(xaSelect).select2('val', '');
		} else {
			$(xaSelect).select2('disable');
			$(xaSelect).select2('val', '');
		}
	}

	var refreshTinh = function(val) {
		if (val && val != '00') {
			$(huyenSelect).select2('enable');
			$(huyenSelect).select2('val', '');
		} else {
			$(huyenSelect).select2('disable');
			$(huyenSelect).select2('val', '');
		}
		refreshHuyen();
	}

	var refreshQuocGia = function(val) {
		if (val == undefined || val != CMS.DM.QUOCGIA_VN) {
			$(tinhSelect).select2("disable");
			$(huyenSelect).select2("disable");
			$(xaSelect).select2("disable");
			$(tinhSelect).select2("val", "");
			$(huyenSelect).select2("val", "");
			$(xaSelect).select2("val", "");
			if (diaChiInput) {
				$(diaChiInput).prop("disabled", false);
			}
		} else {
			$(tinhSelect).select2("enable");
			$(diaChiInput).val("");
			if (diaChiInput) {
				$(diaChiInput).prop("disabled", true);
			}
			var tinhVal = CMS.DM.TINH[0].id;
			// $(tinhSelect).select2("val", tinhVal);
			refreshTinh();
		}
	}

	$(tinhSelect).select2(
			{
				placeholder : "Lựa chọn",
				language : "vi",
				// allowClear : true,
				query : function(query) {
					if (isQuocGiaVN()) {
						CMSCommonJs.Select2.queryArray(CMS.DM.TINH, query);
					}
				},
				initSelection : function(element, callback) {
					if (isQuocGiaVN()) {
						CMSCommonJs.Select2.initSelectionArray(CMS.DM.TINH,
								element, callback);
					}
				}
			}).on(
			"select2-selected",
			function(evt) {
				if (evt.val && evt.val != '00') {
					if (editable) {
						$(tinhSelect)[0].listHuyen = CMSCommonJs.Form
								.getDMHuyen(evt.val);
					}
				}
				refreshTinh(evt.val);
			}).on("select2-close", function(e) {
		$(this).valid();
	});

	$(huyenSelect).select2(
			{
				placeholder : "Lựa chọn",
				language : "vi",
				// allowClear : true,
				query : function(query) {
					if (isQuocGiaVN()) {
						CMSCommonJs.Select2.queryArray(
								$(tinhSelect)[0].listHuyen, query);
					}
				},
				initSelection : function(element, callback) {
					if (isQuocGiaVN()) {
						$(tinhSelect)[0].listHuyen = CMSCommonJs.Form
								.getDMHuyen(idTinh);
						CMSCommonJs.Select2.initSelectionArray(
								$(tinhSelect)[0].listHuyen, element, callback);
					}
				}
			}).on("select2-selected", function(evt) {
		if (evt.val) {
			if (editable) {
				$(huyenSelect)[0].listXa = CMSCommonJs.Form.getDMXa(evt.val);
			}
		}
		refreshHuyen(evt.val);
	}).on("select2-close", function(e) {
		$(this).valid();
	});

	$(xaSelect).select2(
			{
				placeholder : "Lựa chọn",
				language : "vi",
				// allowClear : true,
				query : function(query) {
					if (isQuocGiaVN()) {
						CMSCommonJs.Select2.queryArray(
								$(huyenSelect)[0].listXa, query);
					}
				},
				initSelection : function(element, callback) {
					if (isQuocGiaVN()) {
						$(huyenSelect)[0].listXa = CMSCommonJs.Form
								.getDMXa(idHuyen);
						CMSCommonJs.Select2.initSelectionArray(
								$(huyenSelect)[0].listXa, element, callback);
					}
				}
			}).on("select2-close", function(e) {
		$(this).valid();
	});

	if (quocGiaSelect) {
		if (editable && !$(quocGiaSelect).val()) {
			$(quocGiaSelect).val(CMS.DM.QUOCGIA_VN);
			// $(quocGiaSelect).select2('val', 'VN');
			refreshQuocGia(CMS.DM.QUOCGIA_VN);
			// console.log($(quocGiaSelect).val());
		} else {
			if ($(quocGiaSelect).val() == CMS.DM.QUOCGIA_VN) {
				$(diaChiInput).prop("disabled", true);
			} else {
				$(tinhSelect).select2("disable");
				$(huyenSelect).select2("disable");
				$(xaSelect).select2("disable");
			}
		}
		$(quocGiaSelect).select2(
				{
					placeholder : "Lựa chọn",
					language : "vi",
					// allowClear : true,
					query : function(query) {
						CMSCommonJs.Select2.queryArray(CMS.DM.QUOCGIA, query);
					},
					initSelection : function(element, callback) {
						CMSCommonJs.Select2.initSelectionArray(CMS.DM.QUOCGIA,
								element, callback);
					}
				}).on("select2-selected", function(evt) {
			refreshQuocGia(evt.val);
		}).on("select2-close", function(e) {
			$(this).valid();
		});
	}

	if (editable) {
		if (!idHuyen) {
			$(huyenSelect).select2('disable');
		} else {
			$(huyenSelect).select2('enable');
		}

		if (!idXa) {
			$(xaSelect).select2('disable');
		} else {
			$(xaSelect).select2('enable');
		}
	} else {
		$(xaSelect).select2('disable');
		$(huyenSelect).select2('disable');
		$(tinhSelect).select2('disable');
		if (quocGiaSelect) {
			$(quocGiaSelect).select2('disable');
		}
		if (diaChiInput) {
			$(diaChiInput).prop("disabled", true);
		}
	}
}

CMSCommonJs.Form.addFileValidation = function(file, required) {
	// console.log("addFileValidation" + required);
	if (required) {
		file.attr("required-file", true);
	}
	file
			.rules(
					'add',
					{
						extension : "pdf|doc|docx|jpg|png",
						filesize : 10485760,
						required : required && (!file.attr("oldUrl")),
						messages : {
							extension : jQuery.validator
									.format("Tập tin không đúng định dạng"),
							filesize : jQuery.validator
									.format("Tập tin không hợp lệ. Kích thước hơn 10 Mb."),
							required : jQuery.validator
									.format("Trường bắt buộc")
						}
					});
}

CMSCommonJs.Form.addFormFileValidation = function(form, fileElName, required) {
	CMSCommonJs.Form.addFileValidation(form.find(fileElName), required);
}

CMSCommonJs.Form.addFileDisplay = function(fileInput, hiddenInput, link, url,
		viewBtn, title) {
	var file_name = url.substring(url.lastIndexOf('/') + 1);
	var file_ext = url.substring(url.lastIndexOf('.') + 1);
	$(link).attr("href", CMSCommonJs.ROOT_URL + "file?path=" + url);
	$(link).text(file_name);
	$(link).attr("target", "_blank");
	if (viewBtn) {
		var viewBtnId = viewBtn.replace(/#/g, '');
		if (url) {
			var viewDiv = $('<div id="'
					+ viewBtnId
					+ '" style="cursor: pointer" class="btn btn-default"><i class="icon-search"></i>Xem</div>');
			$(link).parent().prepend(viewDiv);
		}
	}
	$(fileInput).change(function() {
		if ($(fileInput).val()) {
			$(hiddenInput).val("");
			$(link).attr("href", "").text("");
		}
	});
}

CMSCommonJs.Form.addGaleryDisplay = function(list) {
	$.each(list, function(index, entry) {
		var viewBtn = entry.link + 'View';
		var url = entry.url;
		console.log(viewBtn);
		CMSCommonJs.Form.addFileDisplay(entry.fileInput, entry.hiddenInput,
				entry.link, entry.url, viewBtn, entry.title);

		var file_name = url.substring(url.lastIndexOf('/') + 1);
		var file_ext = url.substring(url.lastIndexOf('.') + 1);

		if (file_ext == 'jpg' || file_ext == 'png' || file_ext == 'jpeg') {
			$(viewBtn).click(function() {
				$.fancybox.open([ {
					href : CMSCommonJs.ROOT_URL + "file?path=" + url,
					title : entry.title
				} ], {
					helpers : {
						thumbs : {
							width : 75,
							height : 50
						}
					}
				});
			});
		}
	});
}

CMSCommonJs.Form.addFileDisplayLink = function(fileInput, hiddenInput,
		isEditable) {
	var url;
	var title;

	// console.log("addFileDisplayLink");
	if ($(fileInput)) {
		title = $(fileInput).closest(".form-group").prev().text();
	} else {
		title = $(hiddenInput).closest(".form-group").prev().text();
	}

	// console.log("isEditable: " + isEditable);
	// console.log("title: " + title);

	url = $(hiddenInput).val();

	if (!isEditable) {
		$(fileInput).prop("disabled", true);
		$(fileInput).hide();
		$(fileInput).closest(".col-sm-6").removeClass("col-sm-6");
	} else {
		$(fileInput).change(function() {
			if ($(fileInput).val()) {
				$(hiddenInput).val("");
				$(link).attr("href", "").text("");
			}
		});
	}

	if (isEditable) {
		if (url != null) {
			$(fileInput).attr("oldUrl", url);
			if ($(fileInput).attr("required-file")) {
				$(fileInput).rules('add', {
					required : true,
					messages : {
						required : jQuery.validator.format("Trường bắt buộc")
					}
				});
			}
		}
	}
	if (url) {
		// Create link
		var urlId = hiddenInput.replace(/#/g, '');
		var urlLinkId = urlId + "Link";
		var linkDiv = $('<a id="' + urlLinkId + '" href="' + url + '" title="'
				+ title + '">' + url + '</a>');
		$(hiddenInput).parent().prepend(linkDiv);

		var file_name = url.substring(url.lastIndexOf('/') + 1);
		var file_ext = url.substring(url.lastIndexOf('.') + 1);
		linkDiv.attr("href", CMSCommonJs.ROOT_URL + "file?path=" + url);
		linkDiv.text(file_name);
		linkDiv.attr("target", "_blank");

		if (file_ext == 'jpg' || file_ext == 'png' || file_ext == 'jpeg') {
			// Create view button
			var viewBtn = urlId + "View";
			var viewBtnId = viewBtn.replace(/#/g, '');
			var viewDiv = $('<span id="'
					+ viewBtnId
					+ '" style="cursor: pointer" class="btn btn-default"><i class="icon-search"></i>Xem</span> ');

			$(hiddenInput).parent().prepend(viewDiv);

			$("#" + viewBtnId).click(function() {
				// console.log("click View");
				$.fancybox.open([ {
					href : CMSCommonJs.ROOT_URL + "file?path=" + url,
					title : title
				} ], {
					helpers : {
						thumbs : {
							width : 75,
							height : 50
						}
					}
				});
			});
		}
	}
}

CMSCommonJs.Form.addGaleryDisplay2 = function(list, isEditable) {
	// console.log(list);
	$.each(list,
			function(index, entry) {
				var hiddenInput = entry.hiddenInput;
				var fileInput = entry.fileInput;
				CMSCommonJs.Form.addFileDisplayLink(fileInput, hiddenInput,
						isEditable);
			});
}

CMSCommonJs.Form.addFormSubmitStatusCheck = function(form, btnNhap, btnGui,
		submitCb) {
	$(btnNhap).click(function(evt) {
		// $(elStatus).val("0");
	});

	$(btnGui).click(function(evt) {
		evt.preventDefault();
		// $(elStatus).val("1");
		submitCb();
	});
}

CMSCommonJs.Form.addFormSubmitDialogCheck = function(form, btnNhap, btnGui,
		dialogName) {
	$(dialogName).dialog({
		resizable : false,
		autoOpen : false,
		width : 400,
		height : 200,
		modal : true,
		buttons : {
			"Gửi" : function() {
				$(this).dialog("close");
				// Prevent duplicate post?
				$(".buttons button").prop("disabled", true);
				form.submit();
			},
			"Huỷ" : function() {
				$(this).dialog("close");
			}
		}
	});

	$(btnGui).click(function(evt) {
		evt.preventDefault();
		// $(elStatus).val("1");
		if (form.valid()) {
			$(dialogName).dialog("open");
		}
	});

}

CMSCommonJs.Form.addFormSubmitDialog = function(form, dialogName) {
	$(dialogName).dialog({
		resizable : false,
		autoOpen : false,
		width : 400,
		height : 200,
		modal : true,
		// title: "Xác nhận gửi đăng ký?",
		buttons : {
			"Gửi" : function() {
				$(this).dialog("close");
				form.submit();
			},
			"Huỷ" : function() {
				$(this).dialog("close");
			}
		}
	});
}

CMSCommonJs.Form.inputEnable = function(inputs, more) {
	$.each(inputs, function(index, input) {
		input.prop("disabled", false);
		if (more != null) {
			more(index, input);
		}
	});
}

CMSCommonJs.Form.inputDisable = function(inputs, more) {
	$.each(inputs, function(index, input) {
		input.prop("disabled", true);
		input.val("");
		if (more != null) {
			more(index, input);
		}
	});
}

CMSCommonJs.Form.inputClear = function(inputs, more) {
	$.each(inputs, function(index, input) {
		input.prop("disabled", true);
		input.val("");
		if (more) {
			more(index, input);
		}
	});
}

CMSCommonJs.Form.createQRCode = function(id, codeSource) {
	codeSource = removeDiacritics(codeSource.substring(0, 100));
	new QRCode(id, codeSource, {
		text : codeSource,
		width : 128,
		height : 128,
		colorDark : "#000000",
		colorLight : "#ffffff",
		correctLevel : QRCode.CorrectLevel.Q
	});
}

CMSCommonJs.Form.createSoTienInput = function(element) {
	var txt = $(element).val();
	$(element).priceFormat({
		prefix : '',
		suffix : '',
		centsSeparator : ',',
		thousandsSeparator : '.',
		centsLimit : 0
	});
	// console.log(txt);
	// $(element).val(CMSCommonJs.Form.createSoTienFormat(txt));
	// $(element).mask('000.000.000.000.000.000.000.000.000.000', {
	// reverse : true
	// });

}

CMSCommonJs.Form.createSoTienFormat = function(val) {
	var txt = val;
	txt = txt.replace(/\./g, "");
	var l = txt.length;
	var result = '';
	for (var i = l; i >= 0; i = i - 3) {
		var block = txt.substr(i - 3, 3);
		if (i > l - 3) {
			result = block;
		} else {
			result = block + '.' + result;
		}
	}
	console.log(result);
	return result;
}

CMSCommonJs.Form.showNotifier = function(currentForm) {
	/*
	 * if (currentForm.attr("lastNotifierShow")) { var currentTime = new
	 * Date().getTime(); var lastNotifierShow = currentForm
	 * .attr("lastNotifierShow"); //console.log(parseInt(currentTime) -
	 * parseInt(lastNotifierShow)); if (parseInt(currentTime) -
	 * parseInt(lastNotifierShow) > 3000) { currentForm.attr("lastNotifierShow",
	 * currentTime); Notifier.warning("Vui lòng kiểm tra lại!", "Thông tin không
	 * hợp lệ"); } } else { var currentTime = new Date().getTime();
	 * //console.log(currentTime); currentForm.attr("lastNotifierShow",
	 * currentTime); Notifier.warning("Vui lòng kiểm tra lại!", "Thông tin không
	 * hợp lệ"); }
	 */
}

/* show message lỗi */
function alertError(errorMsg) {
	$.showNotification({
  		body:errorMsg,
  		type:"danger",
  		duration: 3000,
  		direction:"append"
	})
}

/* show message thành công */
function alertSuccess(inforMsg) {
   $('#alertshowInforMsg').empty();
   $('#alertshowInforMsg').append(inforMsg);
   $('#alertshowInforMsg').fadeIn(1000);
   setTimeout(function() { 
       $('#alertshowInforMsg').fadeOut(1000); 
   }, 3000);
}

/* show message lỗi */
function alertErrorModal(errorMsg) {
   $('#alertshowErrorMsgModal').empty();
   $('#alertshowErrorMsgModal').append(errorMsg);
   $('#alertshowErrorMsgModal').fadeIn(1000);
   setTimeout(function() { 
       $('#alertshowErrorMsgModal').fadeOut(1000); 
   }, 3000);
}

/* show message thành công */
function alertSuccessModal(inforMsg) {
   $('#alertshowInforMsgModal').empty();
   $('#alertshowInforMsgModal').append(inforMsg);
   $('#alertshowInforMsgModal').fadeIn(1000);
   setTimeout(function() { 
       $('#alertshowInforMsgModal').fadeOut(1000); 
   }, 3000);
}

// remove after character dot
function subStrAfterDot(s){
	//Using substring
	var i = s.indexOf('.');
	s = s.substring(0, i);
	return s;
}