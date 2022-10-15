var CMS = CMS || {};
CMS.Form2 = {};

var currentForm = $("#Form_DACBDT");
var token = $("meta[name='_csrf']").attr("content");
var header = $("meta[name='_csrf_header']").attr("content");

CMS.Form2.getAjaxCDT = function(editable) {
	var ma = $('#maChuDauTu').val();
	if (editable == undefined) {
		editable = true;
	} else {
		editable = false;
	}
	if (ma) {
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
			timeout : 5000,
			success : function(result) {
				if (result != null) {
					$("#ChuDauTu").val(result.ten);
					$("#TinhThanhChuDauTu").val(result.tinh);
					$("#HuyenChuDauTu").val(result.huyen);
					$("#XaChuDauTu").val(result.xa);
					$("#DiaChiCDT").val(result.diaChi);
					$("#DienThoaiCDT").val(result.dienThoai);
					$("#EmailCDT").val("");
				} else {
					$("#ChuDauTu").val("");
					$("#TinhThanhChuDauTu").val("");
					$("#HuyenChuDauTu").val("");
					$("#XaChuDauTu").val("");
					$("#DiaChiCDT").val("");
					$("#DienThoaiCDT").val("");
					$("#EmailCDT").val("");
				}
			}
		});
	}
};

CMS.Form2.getAjaxDuAnCapTren = function(editable) {
	if (editable == undefined) {
		editable = true;
	} else {
		editable = false;
	}
	var ma = $('#duAnCapTren').val();
	if (ma) {
		var ajaxUrl = CMSCommonJs.ROOT_URL + 'ajax/danhmuc/duan';
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
			timeout : 5000,
			success : function(result) {
				if (result != null) {
					$("#tenDuAnCapTren").val(result.tenDuAn);
				} else {
					$("#tenDuAnCapTren").val("");
				}
			}
		});
	}
};

CMS.Form2.getAjaxBQL = function(editable) {
	if (editable == undefined) {
		editable = true;
	} else {
		editable = false;
	}
	var ma = $('#MaBQL').val();
	if (ma) {
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
			timeout : 5000,
			success : function(result) {
				if (result != null) {
					$("#BanQuanLy").val(result.ten);
					$("#TinhThanhBanQuanLy").val(result.tinh);
					$("#HuyenBanQuanLy").val(result.huyen);
					$("#XaBanQuanLy").val(result.xa);
					$("#DiaChiBQL").val(result.diaChi);
					$("#DienThoaiBQL").val(result.dienThoai);
					$("#EmailBQL").val("");
				} else {
					$("#BanQuanLy").val("");
					$("#TinhThanhBanQuanLy").val("");
					$("#HuyenBanQuanLy").val("");
					$("#XaBanQuanLy").val("");
					$("#DiaChiBQL").val("");
					$("#DienThoaiBQL").val("");
					$("#EmailBQL").val("");
				}
			}
		});
	}
};

CMS.Form2.getAjaxCQQL = function(editable) {
	if (editable == undefined) {
		editable = true;
	} else {
		editable = false;
	}
	var ma = $('#CoQuanChuQuanCapTren').val();
	if (ma) {
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
			timeout : 5000,
			success : function(result) {
				if (result != null) {
					$("#TenCoQuanChuQuanCapTren").val(result.ten);

				} else {
					$("#TenCoQuanChuQuanCapTren").val("");

				}
			}
		});
	}
};

CMS.Form2.getAjaxDuAnCapTren = function(editable) {
	if (editable == undefined) {
		editable = true;
	} else {
		editable = false;
	}
	var ma = $('#duAnCapTren').val();
	if (ma) {
		var ajaxUrl = CMSCommonJs.ROOT_URL + 'ajax/danhmuc/duan';
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
			timeout : 5000,
			success : function(result) {
				if (result != null) {
					$("#tenDuAnCapTren").val(result.tenDuAn);
				} else {
					$("#tenDuAnCapTren").val("");
				}
			}
		});
	}
};

CMS.Form2.createNguonVonTable = function(data) {
	currentForm[0].NguonVonTable = new SimpleFormTable({
		$el : $("#nguonVonList"),
		$template : $.templates("#nguonVonRow"),
		data : data,
		createRowData : function(row, rowData) {
			// console.log(rowData.id);
			var index = row.attr("rowid");
			var kieuSelect = row.find("#kieu" + index);
			kieuSelect.select2({
				placeholder : "Lựa chọn",
				language : "vi"
			}).on("select2-close", function(e) {
				$(this).valid();
			}).select2("val", rowData.nvnID, true);

			if (this.editable) {
				kieuSelect.rules('add', {
					checkDuplicate : {
						el : ".kieuNvnInput"
					},
					messages : {
						digits : jQuery.validator
								.format("Trùng kiểu nguồn vốn")
					}
				});

				row.find("#tyLe" + index).rules('add', {
					digits : true,
					checkSum : {
						value : 100,
						el : ".tyLeInput",
						sumEl : "#sum",
						alert : "Tổng tỷ lệ phải bằng 100%"
					},
					messages : {
						digits : jQuery.validator.format("Chỉ được nhập số")
					}
				});
			} else {
				kieuSelect.prop("disabled", true);
				row.find("#tyLe" + index).prop("disabled", true);
			}
		},
		createEmptyRow : function() {
			return {

			}
		},
		onCreateRowsFinish : function() {
			var sumOfVals = 0;
			$(".tyLeInput").each(function(i, e) {
				sumOfVals = sumOfVals + parseFloat($(this).val());
			});
			$("#sum").text(sumOfVals);
		}
	});
}

// MAIN
$(function() {
	// Create form
	// ------------------------------------------------------------------------------------
	var isEditable = currentForm.attr("formMode") == "create"
			|| currentForm.attr("formMode") == "edit";
	var isHasData = currentForm.attr("formMode") == "view"
			|| currentForm.attr("formMode") == "edit";

	$('#TinhThanhDangKy').select2({
		placeholder : "Lựa chọn",
		language : "vi"
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
		currentForm.validate({
			ignore : 'input[type=hidden]',
			ignoreTitle : true,
			rules : {
				ngayRaVanBan : {
					vnDate : true
				}
			},
			submitHandler : function(form) {
				var txt = $("#tongKinhPhi").val();
				txt = txt.replace(/\./g, '');
				txt = txt.replace(/,/g, '');
				$("#tongKinhPhi").val(txt);

				form.submit();
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
		currentForm.find("input").prop("disabled", true);
		currentForm.find("select").prop("disabled", true);
	}

	// init Data
	// ------------------------------------------------------------------------------------
	if (currentForm.attr("formMode") == "create") {
		CMS.Form2.createNguonVonTable();
	} else {
		CMS.Form2.createNguonVonTable(currentForm.NguonVonList);
	}

	if (isHasData) {
		CMS.Form2.getAjaxCQQL(isEditable);
		CMS.Form2.getAjaxBQL(isEditable);
		CMS.Form2.getAjaxCDT(isEditable);
		CMS.Form2.getAjaxDuAnCapTren(isEditable);
	}

	// init event handler
	// ------------------------------------------------------------------------------------
	if (isEditable) {
		$('#CoQuanChuQuanCapTren').change(function() {
			CMS.Form2.getAjaxCQQL(isEditable);
		}).rules('add', {
			checkMaDVSDNS : true
		});
		$('#MaBQL').change(function() {
			CMS.Form2.getAjaxBQL(isEditable);
		}).rules('add', {
			checkMaDVSDNS : true
		});
		$('#maChuDauTu').change(function() {
			CMS.Form2.getAjaxCDT(isEditable);
		}).rules('add', {
			checkMaDVSDNS : true
		});
		$('#duAnCapTren').change(function() {
			CMS.Form2.getAjaxDuAnCapTren(isEditable);
		}).rules('add', {
			checkMaDuAn : true
		});

	}

	CMSCommonJs.Form.createSoTienInput("#tongKinhPhi");

	$("#printGCN").click(function(e) {
		e.preventDefault();
		var id = currentForm.find("[name='hsid']").val();
		var type = 2;
		var url = CMSCommonJs.ROOT_URL + "user/forms/gcn/" + type + "/" + id;
		window.location.href = url;
	});

	$("#printForm").click(function(e) {
		e.preventDefault();
		currentForm.print();
	});
});