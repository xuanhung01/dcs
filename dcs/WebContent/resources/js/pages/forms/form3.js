var CMS = CMS || {};
CMS.Form3 = {};

var currentForm = $("#Form_DATHDT");
var token = $("meta[name='_csrf']").attr("content");
var header = $("meta[name='_csrf_header']").attr("content");

CMS.Form3.getAjaxCDT = function(editable) {
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

CMS.Form3.getAjaxBQL = function(editable) {
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

CMS.Form3.getAjaxCQQL = function(editable) {
	var ma = $('#CoQuanChuQuanCapTren').val();
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
					$("#TenCoQuanChuQuanCapTren").val(result.ten);

				} else {
					$("#TenCoQuanChuQuanCapTren").val("");

				}
			}
		});
	}
};

CMS.Form3.getAjaxDuAnCapTren = function(editable) {
	var ma = $('#duAnCapTren').val();
	if (editable == undefined) {
		editable = true;
	} else {
		editable = false;
	}
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

CMS.Form3.checkSumFor = function(className, target) {
	var sumOfVals = 0;
	$(className).each(function(i, e) {
		var v = 0;
		try {
			var val = $(this).val();
			if (val) {
				v = parseFloat(val);
			}
		} catch (e) {

		}
		sumOfVals = sumOfVals + v;
	});
	$(target).text(sumOfVals);
	$(target).priceFormat({
		prefix : '',
		suffix : '',
		centsSeparator : ',',
		thousandsSeparator : '.',
		centsLimit : 0
	});
	
}

CMS.Form3.createNguonVonTable = function(data) {
	currentForm[0].NguonVonTable = new SimpleFormTable({
		$el : $("#nguonVonList"),
		$template : $.templates("#nguonVonRow"),
		data : data,
		createRowData : function(row, rowData) {
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
				jQuery("#sum").html('100%');
			}
		},
		createEmptyRow : function() {
			return {

			}
		},
		onCreateRowsFinish : function() {
			CMS.Form3.checkSumFor(".tyLeInput", "#sum");
		}
	});
	$("btnAdd").blur();
}

CMS.Form3.createTongMucDauTuTable = function(data) {
	currentForm[0].TongMucDauTuTable = new SimpleFormTable({
		$el : $("#tongMucDauTuList"),
		$template : $.templates("#tongMucDauTuRow"),
		data : data,
		createRowData : function(row, rowData) {
			var index = row.attr("rowid");
			var kieuSelect = row.find("#maCP" + index);
			kieuSelect.select2({
				placeholder : "Lựa chọn",
				language : "vi",
				data : CMS.DM.LOAICP
			}).on("select2-close", function(e) {
				$(this).valid();
			}).select2("val", rowData.loaiCPID);

			row.find("#soTien" + index).val(rowData.soTien);
			if (this.editable) {
				kieuSelect.rules('add', {
					checkDuplicate : {
						el : ".loaiCPInput"
					},
					messages : {
						digits : jQuery.validator.format("Trùng kiểu chi phí")
					}
				});

				row.find("#soTien" + index).rules('add', {
					digits : true,
					messages : {
						digits : jQuery.validator.format("Chỉ được nhập số")
					}
				});

				row.find("#soTien" + index).change(function() {
					CMS.Form3.checkSumFor(".soTienInput", "#sumSoTien");
				});
			} else {
				kieuSelect.prop("disabled", true);
				row.find("#soTien" + index).prop("disabled", true);
			}

		},
		createEmptyRow : function() {
			return {

			}
		},
		onCreateRowsFinish : function() {
			CMS.Form3.checkSumFor(".soTienInput", "#sumSoTien");
		}
	});
}

CMS.Form3.createDiaDiemTable = function(editable, data) {
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
		createEmptyRow : function() {
			return {

			}
		}
	});
}

CMS.Form3.createNganhKinhTeTable = function(data) {
	currentForm[0].NganhKinhTeTable = new SimpleFormTable({
		$el : $("#nganhKinhTeList"),
		$template : $.templates("#nganhKinhTeRow"),
		data : data,
		createRowData : function(row, rowData) {
			var index = row.attr("rowid");
			var kieuSelect = row.find("#kieu" + index);
			kieuSelect.select2(
					{
						placeholder : "Lựa chọn",
						language : "vi",
						query : function(query) {
							CMSCommonJs.Select2.queryArray(CMS.DM.List.NganhKT,
									query);
						},
						initSelection : function(element, callback) {
							CMSCommonJs.Select2.initSelectionArray(
									CMS.DM.List.NganhKT, element, callback);
						}
					}).on("select2-close", function(e) {
				$(this).valid();
			})

			if (this.editable) {
				kieuSelect.rules('add', {
					checkDuplicate : {
						el : ".kieuNKTInput"
					},
					messages : {
						digits : jQuery.validator
								.format("Trùng kiểu ngành kinh tế")
					}
				});
			}
			// kieuSelect.select2("val", rowData.nganhKTId);
			// console.log(rowData.nganhKTId);
			// console.log(rowData.nganhKTId);
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
	$('#TinhThanhDangKy').select2({
		placeholder : "Lựa chọn",
		language : "vi"
	}).on("select2-close", function(e) {
		$(this).valid();
	});

	$("#danhmuc_NganhKT").select2({
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
		currentForm.validate({
			ignore : 'input[type=hidden]',
			ignoreTitle : true,
			rules : {
				ngayQD : {
					vnDate : true
				},
				tgBatDau : {
					vnDate : true,
					dateLessThan : "#ThoiGianHT"
				},
				tgHoanThanh : {
					vnDate : true,
					dateGreaterThan : "#ThoiGianBD"
				}
			},
			showErrors : function(errorMap, errorList) {
				this.defaultShowErrors();
				if (errorList.length > 0) {
					CMSCommonJs.Form.showNotifier(currentForm);
				}
			}
		});

		$("#errorAlertDiv").hide();

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
	CMS.DM.createDMList_NganhKT();

	// Initial data
	if (!isHasData) {
		CMS.Form3.createNguonVonTable();
		CMS.Form3.createDiaDiemTable(isEditable);
		CMS.Form3.createTongMucDauTuTable();
		CMS.Form3.createNganhKinhTeTable();
	} else {
		CMS.Form3.createNguonVonTable(currentForm.NguonVonList);
		CMS.Form3.createDiaDiemTable(isEditable, currentForm.DiaDiemList);
		CMS.Form3.createTongMucDauTuTable(currentForm.TongMucDauTu);
		CMS.Form3.createNganhKinhTeTable(currentForm.NganhKinhTeList);
		CMS.Form3.checkSumFor(".soTienInput", "#sumSoTien");

		CMS.Form3.getAjaxDuAnCapTren(isEditable);
		CMS.Form3.getAjaxCQQL(isEditable);
		CMS.Form3.getAjaxBQL(isEditable);
		CMS.Form3.getAjaxCDT(isEditable);
	}

	// Event handler ----------------------------------
	if (isEditable) {
		$('#CoQuanChuQuanCapTren').change(function() {
			CMS.Form3.getAjaxCQQL();
		}).rules('add', {
			checkMaDVSDNS : true
		});
		$('#duAnCapTren').change(function() {
			CMS.Form3.getAjaxDuAnCapTren(false);
		}).rules('add', {
			checkMaDuAn : true
		});

		$('#MaBQL').change(function() {
			CMS.Form3.getAjaxBQL();
		}).rules('add', {
			checkMaDVSDNS : true
		});

		$('#maChuDauTu').change(function() {
			CMS.Form3.getAjaxCDT(false);
		}).rules('add', {
			checkMaDVSDNS : true
		});

	} else {
		currentForm.find("input").prop("disabled", true);
		currentForm.find("select").prop("disabled", true);
	}

//	CMSCommonJs.Form.createSoTienInput("#sumSoTien");
	
	$("#printGCN").click(function(e) {
		e.preventDefault();
		var id = currentForm.find("[name='hsid']").val();
		var type = 3;
		var url = CMSCommonJs.ROOT_URL + "user/forms/gcn/" + type + "/" + id;
		window.location.href = url;
	});

	$("#printForm").click(function(e) {
		e.preventDefault();
		currentForm.print();
	});
});
