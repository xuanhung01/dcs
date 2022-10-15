var CMS = CMS || {};
CMS.Form4 = {};

var currentForm = $("#Form_ChuyenGiaiDoan");
var currentFormDom = $("#Form_ChuyenGiaiDoan")[0];
var token = $("meta[name='_csrf']").attr("content");
var header = $("meta[name='_csrf_header']").attr("content");

CMS.Form4.getAjaxCDT = function(editable) {
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

CMS.Form4.getAjaxBQL = function(editable) {
	var ma = $('#MaBQL').val();
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

CMS.Form4.getAjaxTenDuAnVaDiaChi = function() {
	var ma = $('#MaCBDT').val();

	if (ma) {
		$.ajax({
			url : CMSCommonJs.ROOT_URL + 'ajax/danhmuc/duan/view',
			dataType : "json",
			type : "GET",
			data : {
				ma : ma
			},
			timeout : 5000,
			success : function(result) {
				if (result != null) {
					$("#tenDuAn").val(result.tenDuAn);
					$("#diaChiChuDauTu").val(result.chuDauTu.diaChiFull);
				} else {
					$("#tenDuAn").val("");
					$("#diaChiChuDauTu").val("");
				}
			}
		});
	}
};

CMS.Form4.createNguonVonTable = function(data) {
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
						alert : "Tổng tỷ lệ phải bằng 100"
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
	$("btnAdd").blur();
}

CMS.Form4.createTongMucDauTuTable = function(isEditable, data) {
	currentForm[0].TongMucDauTuTable = new SimpleFormTable({
		$el : $("#tongMucDauTuList"),
		$template : $.templates("#tongMucDauTuRow"),
		data : data,
		createRowData : function(row, rowData) {
			var index = row.attr("rowid");

			var kieuSelect = row.find("#loaiCPID");

			kieuSelect.select2({
				placeholder : "Lựa chọn",
				language : "vi",
				data : CMS.DM.LOAICP
			});

			if (isEditable) {
				kieuSelect.select2("val", rowData.loaiCPID);
				row.find("#soTien" + index).val(rowData.soTien);
				row.find("#soTien" + index).rules('add', {
					digits : true,
					messages : {
						digits : jQuery.validator.format("Chỉ được nhập số")
					}
				});

				kieuSelect.rules('add', {
					checkDuplicate : {
						el : ".loaiCPInput"
					},
					messages : {
						digits : jQuery.validator
								.format("Trùng kiểu loại chi phí")
					}
				});
			} else {
				kieuSelect.select2("val", rowData.loaiCPID);
				kieuSelect.prop("disabled", true);

				row.find("#soTien" + index).val(rowData.soTien);
				row.find("#soTien" + index).prop("disabled", true);
			}

		},
		createEmptyRow : function() {
			return {

			}
		}
	});
}

CMS.Form4.createDiaDiemTable = function(editable, data) {
	currentForm[0].DiaDiemTable = new SimpleFormTable({
		$el : $("#diaDiemList"),
		$template : editable ? $.templates("#diaDiemRow") : $
				.templates("#diaDiemRowView"),
		data : data,
		createRowData : function(row, rowData) {
			var index = row.attr("rowid");
			if (editable) {
				CMSCommonJs.Form.createDiaDiemList(editable, row
						.find('#TinhThanhDiaDiem' + index), row
						.find('#HuyenDiaDiem' + index), row.find('#XaDiaDiem'
						+ index), row.find('#quocGiaID' + index), row
						.find('#diaChi' + index));
			}
		},
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

	$('#NhomDA').select2({
		placeholder : "Lựa chọn",
		language : "vi"
	}).on("select2-close", function(e) {
		$(this).valid();
	});
	$('#HinhThucDuAn').select2({
		placeholder : "Lựa chọn",
		language : "vi"
	}).on("select2-close", function(e) {
		$(this).valid();
	});
	$('#HinhThucQuanLy').select2({
		placeholder : "Lựa chọn",
		language : "vi"
	}).on("select2-close", function(e) {
		$(this).valid();
	});

	$("#NgayRaQD").datepicker({
		maxDate : "+0D"
	}).mask("00/00/0000");

	$("#ThoiGianBD").datepicker({
		onSelect : function(selected) {
			$("#ThoiGianHT").datepicker("option", "minDate", selected)
		}
	});
	$("#ThoiGianHT").datepicker({
		onSelect : function(selected) {
			$("#ThoiGianBD").datepicker("option", "maxDate", selected)
		}
	});
	$("#ThoiGianBD").mask("00/00/0000");
	$("#ThoiGianHT").mask("00/00/0000");

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
		editable = true;
		currentForm.validate({
			ignore : 'input[type=hidden]',
			ignoreTitle : true,
			rules : {
				ngayQD : {
					vnDate : true
				},
				ngayBD : {
					vnDate : true,
					dateLessThan : "#ThoiGianHT"
				},
				ngayHT : {
					vnDate : true,
					dateGreaterThan : "#ThoiGianBD"
				}
			},
			submitHandler : function(form) {
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

	}

	// Initial data
	if (!isHasData) {
		CMS.Form4.createNguonVonTable();
		CMS.Form4.createDiaDiemTable(isEditable);
		CMS.Form4.createTongMucDauTuTable(isEditable);

	} else {
		CMS.Form4.createNguonVonTable(currentForm.NguonVonList);
		CMS.Form4.createDiaDiemTable(isEditable, currentForm.DiaDiemList);
		CMS.Form4.createTongMucDauTuTable(isEditable, currentForm.TongMucDauTu);
		CMS.Form4.getAjaxBQL(isEditable);
		CMS.Form4.getAjaxCDT(isEditable);
		CMS.Form4.getAjaxTenDuAnVaDiaChi();
	}

	$('#MaCBDT').change(function() {
		CMS.Form4.getAjaxTenDuAnVaDiaChi(isEditable);
	}).rules('add', {
		checkMaDuAnCBDT : true
	});

	if (!isEditable) {
		currentForm.find("input").prop("disabled", true);
		currentForm.find("select").prop("disabled", true);
	}

	$("#printForm").click(function(e) {
		e.preventDefault();
		currentForm.print();
	});
});