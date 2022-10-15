var CMS = CMS || {};

CMS.Form1 = {};

var currentForm = $("#form_DVSDNS");
var currentFormDom = currentForm[0];
var token = $("meta[name='_csrf']").attr("content");
var header = $("meta[name='_csrf_header']").attr("content");

CMS.Form1.createChuongForDVCM = function() {
	CMS.DM.ChuongTrungUong = CMSCommonJs.Form.getDMChuongNganSach("tu");
	CMS.DM.ChuongDiaPhuong = CMSCommonJs.Form.getDMChuongNganSach("dp");

	$('#chuongNganSach')
			.select2(
					{
						language : "vi",
						placeholder : "Lựa chọn",
						query : function(query) {
							var results = [];
							if ($('#maTinh').select2("val") == CMS.DM.CQTC.IdBoTaiChinh) {
								results = CMSCommonJs.Select2.findInArray(
										CMS.DM.ChuongTrungUong, query.term);
							} else {
								results = CMSCommonJs.Select2.findInArray(
										CMS.DM.ChuongDiaPhuong, query.term);
							}
							query.callback({
								results : results
							});
						},

						initSelection : function(element, callback) {
							var maChuong = $(element).val();
							if (maChuong) {
								callback(CMSCommonJs.Form
										.getChuongNganSachStatic(maChuong));
							}
						}
					});
}

CMS.Form1.createToggleDuToan = function(isEditable) {
	currentFormDom.radioName1 = 'dVDTCTBool';
	currentFormDom.radioName2 = 'dVDTCDBool';

	currentFormDom.toggleRadio1 = function(state) {
		if (!state || state == "0" || state == "false") {
			CMSCommonJs.Form.inputDisable([ $("#DVDuocGiaoDTKP") ], function(
					index, input) {
				if (isEditable) {
					input.rules('remove');
				}
			});
		} else {
			CMSCommonJs.Form.inputEnable([ $("#DVDuocGiaoDTKP") ], function(
					index, input) {
				if (isEditable) {
					input.rules('add', {
						required : true,
					});
				}
			});
		}
	};

	currentFormDom.toggleRadio2 = function(state) {
		if (!state || state == "0" || state == "false") {
			CMSCommonJs.Form.inputDisable([ $("#DVGiaoDTKP1"),
					$("#DVGiaoDTKP2"), $("#DVGiaoDTKP3") ], function(index,
					input) {
				if (isEditable) {
					input.rules('remove');
				}
			});

		} else {
			CMSCommonJs.Form.inputEnable([ $("#DVGiaoDTKP1"),
					$("#DVGiaoDTKP2"), $("#DVGiaoDTKP3") ], function(index,
					input) {
				if (isEditable) {
					if (index == 0) {
						input.rules('add', {
							required : true,
						});
					}
				}
			});
		}
	};

	var getRadioVal = function(radioName) {
		return currentForm.find("input:radio[name=" + radioName + "]:checked")
				.val();
	}

	$("input:radio[name=dVDTCTBool]").change(function(evt) {
		currentFormDom.toggleRadio1(getRadioVal(currentFormDom.radioName1));
	});

	$("input:radio[name=dVDTCDBool]").change(function(evt) {
		currentFormDom.toggleRadio2(getRadioVal(currentFormDom.radioName2));
	});
}

CMS.Form1.getAjaxCQQL = function(editable) {
	var ma = $('#DonViQuanLyCapTrenTrucTiep').val();

	if (ma && ma.length == 7) {
		var ajaxUrl = CMSCommonJs.ROOT_URL + 'ajax/danhmuc/dvsdns';
		if (editable == false) {
			ajaxUrl = ajaxUrl + "/view";
		}
		$.ajax({
			url : ajaxUrl,
			dataType : "json",
			type : "GET",
			data : {
				ma : ma
			},
			success : function(result) {
				if (result != null) {
					$("#TenDonViQuanLyCapTrenTrucTiep").val(result.ten);

				} else {
					$("#TenDonViQuanLyCapTrenTrucTiep").val("");
				}
			}
		});
	}
};

// MAIN
$(function() {
	// Create form
	// ------------------------------------------------------------------------------------
	var isEditable = currentForm.attr("formMode") == "create"
			|| currentForm.attr("formMode") == "edit";
	var isHasData = currentForm.attr("formMode") == "view"
			|| currentForm.attr("formMode") == "edit";

	$('#LoaiHinhDonVi').select2({
		placeholder : "Lựa chọn",
		language : "vi",
	}).on("select2-close", function(e) {
		$(this).valid();
	});

	$('#maTinh').select2({
		placeholder : "Lựa chọn",
		language : "vi",
	}).on("select2-selecting", function(evt) {
		$("#chuongNganSach").select2('val', '');
	}).on("select2-close", function(e) {
		$(this).valid();
	});

	CMSCommonJs.Form.addGaleryDisplay2([ {
		fileInput : "#FileDinhKem1",
		hiddenInput : "#url1",
	}, {
		fileInput : "#FileDinhKem2",
		hiddenInput : "#url2",
	}, {
		fileInput : "#FileDinhKem3",
		hiddenInput : "#url3",
	} ], isEditable);

	if (isEditable) {
		// Form validate
		currentForm.validate({
			ignore : 'input[type=hidden]',
			ignoreTitle : true,
			rules : {
				ngayThanhLap : {
					vnDate : true
				}
			},
			showErrors : function(errorMap, errorList) {
				this.defaultShowErrors();
				if (errorList.length > 0) {
					CMSCommonJs.Form.showNotifier(currentForm);
				}
			}
		});
		CMSCommonJs.Form.addFormFileValidation(currentForm,
				" input[name='file1']", isEditable);
		CMSCommonJs.Form.addFormFileValidation(currentForm,
				" input[name='file2']", isEditable);
		CMSCommonJs.Form.addFormFileValidation(currentForm,
				"input[name='file3']");

		CMSCommonJs.Form.addFormSubmitDialogCheck(currentForm, "#Nhap", "#Gui",
				"#SendDialog");
	} else {
	}

	CMSCommonJs.Form.createDiaDiemList(isEditable, '#TinhThanhDiaDiem',
			"#HuyenDiaDiem", "#XaDiaDiem");
	CMS.Form1.createToggleDuToan(isEditable);
	CMS.Form1.createChuongForDVCM();

	// init Data
	// ------------------------------------------------------------------------------------
	if (isHasData) {
		CMS.Form1.getAjaxCQQL(isEditable);
	}
	currentForm[0].toggleRadio1($(
			"input:radio[name=" + currentFormDom.radioName1 + "]:checked")
			.val());
	currentForm[0].toggleRadio2($(
			"input:radio[name=" + currentFormDom.radioName2 + "]:checked")
			.val());

	// init event handler
	// ------------------------------------------------------------------------------------

	$("#printGCN").click(function(e) {
		e.preventDefault();
		var id = currentForm.find("[name='hsid']").val();
		var type = 1;
		var url = CMSCommonJs.ROOT_URL + "user/forms/gcn/" + type + "/" + id;
		window.location.href = url;
	});
	$("#printForm").click(function(e) {
		e.preventDefault();
		currentForm.print();
	});
	if (isEditable) {
		$('#DonViQuanLyCapTrenTrucTiep').change(function() {
			CMS.Form1.getAjaxCQQL(isEditable);
		}).rules('add', {
			checkMaDVSDNS : true
		});
	} else {
		// READONLY
		currentForm.find("input").prop("disabled", true);
		currentForm.find("select").prop("disabled", true);
	}

	$(document).keypress(function(e) {
		if (e.keyCode == 13) {
			e.preventDefault();
			return false;
		}
	});
});