var CMS = CMS || {};
CMS.Form6 = {};

var currentForm = $("#form_ThayDoi");
var token = $("meta[name='_csrf']").attr("content");
var header = $("meta[name='_csrf_header']").attr("content");

var ThongTinThayDoiTable = null;

CMS.Form6.changeFormType = function(data) {
	if (data.type == 1) {
		$("#FormType").val("1");
		currentForm.attr("formType", "donVi");
		currentForm[0].LIST_TT = CMS.Form6.getListTT();
		CMS.Form6.hideAllForm();
		CMS.Form6.displayFormDonVi(data.donVi);
	} else if (data.type == 2) {
		$("#FormType").val("2");
		currentForm.attr("formType", "duAn");
		currentForm[0].LIST_TT = CMS.Form6.getListTT();
		CMS.Form6.hideAllForm();
		CMS.Form6.displayFormDuAn(data.duAn);
	} else {
		$("#FormType").val("");
		currentForm.attr("formType", "");
		currentForm[0].LIST_TT = [];
		CMS.Form6.hideAllForm();
	}

	ThongTinThayDoiTable.clearTable();

}

CMS.Form6.getListTT = function() {
	var results;
	$.ajax({
		url : currentForm[0].infoCheckListUrl,
		dataType : "json",
		type : "GET",
		async : false,
		data : {
			type : currentForm.attr("formType")
		},
		timeout : 5000,
		success : function(data) {
			results = $.map(data, function(item) {
				return {
					text : item.colId + " - " + item.ten,
					id : item.id,
					colName : item.colName
				}
			});
		}
	});
	return results;
}

CMS.Form6.hideAllForm = function() {
	$("#TenDonVi").val("");
	$("#TinhThanhChuDauTu").val("");
	$("#HuyenChuDauTu").val("");
	$("#XaChuDauTu").val("");
	$("#DiaChiCDT").val("");
	$("#DienThoaiCDT").val("");
	$("#EmailCDT").val("");
};

CMS.Form6.displayFormDonVi = function(result) {
	$("#TenDonVi").val(result.ten);
	$("#TinhThanhChuDauTu").val(result.tinh);
	$("#HuyenChuDauTu").val(result.huyen);
	$("#XaChuDauTu").val(result.xa);
	$("#DiaChiCDT").val(result.diaChi);
	$("#DienThoaiCDT").val("");
	$("#EmailCDT").val("");

};

CMS.Form6.displayFormDuAn = function(result) {
	$("#TenDonVi").val(result.tenDuAn);
	if (result.chuDauTu) {
		$("#TinhThanhChuDauTu").val(result.chuDauTu.tinh);
		$("#HuyenChuDauTu").val(result.chuDauTu.huyen);
		$("#XaChuDauTu").val(result.chuDauTu.xa);
		$("#DiaChiCDT").val(result.chuDauTu.diaChi);
	}
	$("#DienThoaiCDT").val("");
	$("#EmailCDT").val("");
};

CMS.Form6.findThongTinCu = function(maDv, truongHtId, el) {
	$.ajax({
		url : currentForm[0].infoCheckFieldUrl,
		dataType : "text",
		type : "GET",
		data : {
			type : currentForm.attr("formType"),
			maDv : maDv,
			truongHTId : truongHtId,
		},
		timeout : 5000,
		success : function(data) {
			el.val(data);
		},
		error : function() {
			el.val("Không tìm thấy dữ liệu!");
		}
	});
};

// Tao danh muc
CMS.DM.createDMList_LoaiHDV();
CMS.DM.createDMList_LoaiCP();
CMS.DM.createDMList_ChuongNganSach();
CMS.DM.createDMList_CapDuToan();
CMS.DM.createDMList_NhomDA();
CMS.DM.createDMList_HinhThucDuAn();
CMS.DM.createDMList_HinhThucQuanLy();
CMS.DM.createDMList_NganhKT();
var funcMap = {
	'LHDV_ID' : function(row, input) {
		input.select2(
				{
					placeholder : "Lựa chọn",
					language : "vi",
					query : function(query) {
						CMSCommonJs.Select2.queryArray(CMS.DM.List.LoaiHDV,
								query);
					},
					initSelection : function(element, callback) {
						CMSCommonJs.Select2.initSelectionArray(
								CMS.DM.List.LoaiHDV, element, callback);
					}
				}).on("select2-close", function(e) {
			$(this).valid();
		}).on("select2-selected", function(evt) {
			CMS.Form6.onchangeSelect2(row);
		});
	},
	'CCH_ID' : function(row, input) {
		input.select2(
				{
					placeholder : "Lựa chọn",
					language : "vi",
					query : function(query) {
						CMSCommonJs.Select2.queryArray(
								CMS.DM.List.ChuongNganSach, query);
					},
					initSelection : function(element, callback) {
						CMSCommonJs.Select2.initSelectionArray(
								CMS.DM.List.ChuongNganSach, element, callback);
					}
				}).on("select2-close", function(e) {
			$(this).valid();
		}).on("select2-selected", function(evt) {
			CMS.Form6.onchangeSelect2(row);
		});
	},
	'CAPDT' : function(row, input) {
		input.select2(
				{
					placeholder : "Lựa chọn",
					language : "vi",
					query : function(query) {
						CMSCommonJs.Select2.queryArray(CMS.DM.List.CapDuToan,
								query);
					},
					initSelection : function(element, callback) {
						CMSCommonJs.Select2.initSelectionArray(
								CMS.DM.List.CapDuToan, element, callback);
					}
				}).on("select2-close", function(e) {
			$(this).valid();
		}).on("select2-selected", function(evt) {
			CMS.Form6.onchangeSelect2(row);
		});
	},
	'NHOMDA_ID' : function(row, input) {
		input.select2(
				{
					placeholder : "Lựa chọn",
					language : "vi",
					query : function(query) {
						CMSCommonJs.Select2.queryArray(CMS.DM.List.NhomDA,
								query);
					},
					initSelection : function(element, callback) {
						CMSCommonJs.Select2.initSelectionArray(
								CMS.DM.List.NhomDA, element, callback);
					}
				}).on("select2-close", function(e) {
			$(this).valid();
		}).on("select2-selected", function(evt) {
			CMS.Form6.onchangeSelect2(row);
		});
	},
	'HTDA_ID' : function(row, input) {
		input.select2(
				{
					placeholder : "Lựa chọn",
					language : "vi",
					query : function(query) {
						CMSCommonJs.Select2.queryArray(
								CMS.DM.List.HinhThucDuAn, query);
					},
					initSelection : function(element, callback) {
						CMSCommonJs.Select2.initSelectionArray(
								CMS.DM.List.HinhThucDuAn, element, callback);
					}
				}).on("select2-close", function(e) {
			$(this).valid();
		}).on("select2-selected", function(evt) {
			CMS.Form6.onchangeSelect2(row);
		});
	},
	'HTQL_ID' : function(row, input) {
		input.select2(
				{
					placeholder : "Lựa chọn",
					language : "vi",
					query : function(query) {
						CMSCommonJs.Select2.queryArray(
								CMS.DM.List.HinhThucQuanLy, query);
					},
					initSelection : function(element, callback) {
						CMSCommonJs.Select2.initSelectionArray(
								CMS.DM.List.HinhThucDuAn, element, callback);
					}
				}).on("select2-close", function(e) {
			$(this).valid();
		}).on("select2-selected", function(evt) {
			CMS.Form6.onchangeSelect2(row);
		});
	}
// ,
// 'NGANH_KINH_TE': function (row, input){
// input.select2({
// placeholder : "Lựa chọn",
// language : "vi",
// query : function(query) {
// CMSCommonJs.Select2.queryArray(CMS.DM.List.NganhKT, query);
// },
// initSelection : function(element, callback) {
// CMSCommonJs.Select2.initSelectionArray(
// CMS.DM.List.NganhKT, element, callback);
// }
// }).on("select2-close", function(e) {
// $(this).valid();
// }).on("select2-selected",function(evt) {
// CMS.Form6.onchangeSelect2(row);
// });
// },
// 'THOI_GIAN_BAT_DAU': function (row, input){
// input.datepicker().mask("00/00/0000");
// },
// 'THOI_GIAN_KET_THUC': function (row, input){
// input.datepicker().mask("00/00/0000");
// },
// 'NGAY_QD': function (row, input){
// input.datepicker().mask("00/00/0000");
// },
// 'NGUOI_KY_QD': function (row, input){
// input.datepicker().mask("00/00/0000");
// },
// 'DIA_DIEM': function (row, input){
// }
}
CMS.Form6.changeInput = function(row, colName) {
	var rowIndex = row.attr("rowid");
	row.find("#tdNewValue").empty();
	row
			.find("#tdNewValue")
			.append(
					"<input name='listThayDoi["
							+ rowIndex
							+ "].thongTinMoi' id='newValue' type='text' style='width:200px' required = 'true'/>");
	if (funcMap[colName]) {
		var input = row.find("#newValue");
		funcMap[colName](row, input);
	}
};

CMS.Form6.onchangeSelect2 = function(row) {
	var newValueText = row.find("#newValue").select2('data').text;
	row.find("#newValueText").val(newValueText);
};

$(function() {
	// Create send dialog
	$("#ValidateDialog").dialog({
		resizable : false,
		autoOpen : false,
		width : 400,
		height : 200,
		modal : true,
		buttons : {
			"Huỷ" : function() {
				$(this).dialog("close");
			}
		}
	});
	
	$("#SendDialog").dialog({
		resizable : false,
		autoOpen : false,
		width : 400,
		height : 200,
		modal : true,
		buttons : {
			"Gửi" : function() {
				$(this).dialog("close");
				currentForm.submit();
			},
			"Huỷ" : function() {
				$(this).dialog("close");
			}
		}
	});
	
	var isEditable = currentForm.attr("formMode") == "create"
			|| currentForm.attr("formMode") == "edit";
	var isHasData = currentForm.attr("formMode") == "view"
			|| currentForm.attr("formMode") == "edit";

	ThongTinThayDoiTable = new SimpleFormTable({
		$el : $("#ThongTinThayDoiList"),
		$template : $.templates("#ThongTinThayDoiRow"),
		createRowData : function(row) {
			var input = row.find("#TruongThongTin");
			input.select2(
					{
						placeholder : "Lựa chọn",
						language : "vi",
						query : function(query) {
							CMSCommonJs.Select2.queryArray(
									currentForm[0].LIST_TT, query);
						},
						initSelection : function(element, callback) {
							CMSCommonJs.Select2.initSelectionArray(
									currentForm[0].LIST_TT, element, callback);
						}
					}).on(
					"select2-selected",
					function(evt) {
						row.find("#oldValue").val("Đang tìm thông tin cũ");

						CMS.Form6.findThongTinCu($("#maDv").val(), row.find(
								"#TruongThongTin").select2('val'), row
								.find("#oldValue"));

						var truongId = row.find("#TruongThongTin").select2(
								'val');
						var colName = $.grep(currentForm[0].LIST_TT, function(
								entry, index) {
							return entry.id == truongId;
						})[0];

						CMS.Form6.changeInput(row, colName.colName);
					});
		},
	});
	
	ThongTinThayDoiTable.clearTable();
	currentForm[0].LIST_TT = CMS.Form6.getListTT();
	
	$("#btnGui").click(function(evt) {
		evt.preventDefault();
		if (ThongTinThayDoiTable.getNumRow() == 0) {
			$("#ValidateDialog").dialog("open");
		} else {
			$("#SendDialog").dialog("open");
		}
	});

	$("#resetEditForm").click(function(evt) {
		evt.preventDefault();
		ThongTinThayDoiTable.clearTable();
	});
	$("#resetCreateForm").click(function(evt) {
		ThongTinThayDoiTable.clearTable();
	});

});