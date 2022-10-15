// listDashboard
var dataDiaBanCollector = null;
var tableRelationByColl = null;
var tableDebtCustomerLdDetailMainDtoRow = null;
var tableRelationByLos = null;
var DCS = DCS || {};
DCS.Main = {};
DCS.Main.MoiQuanHeList = [];
DCS.Main.MoiQuanHeOldList = [];
DCS.Main.DiaBanTinh = [];
DCS.Main.DebtSysLimitNodeCode = [];

var ListInforUdtTable = null;
var DiaBanThuongTruTable = null;
var DiaBanTamTruTable = null;
var DiaBanCoQuanTable = null;
var listDistrict;
var listWard;
var listAllWard;

function loadFormMain(data){
	if(data != undefined){
		createtableRelationByLos();
		createtableRelationByColl();
		createtableDebtCustomerLdDetailMainDtoRow();
		// Thông tin hidden
		$("#debtCustomerMainDtolmsCifNo").val(data.debtCustomerMainDto.lmsCifNo);
		$("#debtCustomerMainDtoId").val(data.debtCustomerMainDto.id);
		
		/* 1. Thông tin Khách Hàng */
		$("#debtCustomerMainDtohoTenDayDu").val(data.debtCustomerMainDto.hoTenDayDu);
		$("#debtCustomerMainDtosoCmndTheCanCuoc").val(data.debtCustomerMainDto.soCmndTheCanCuoc);
		$("#debtCustomerMainDtongaySinhStr").val(data.debtCustomerMainDto.ngaySinhStr);
		if(data.debtCustomerJobMainDto != null){
			$("#debtCustomerJobMainDtongheNghiep").val(data.debtCustomerJobMainDto.ngheNghiep);
			$("#debtCustomerJobMainDtongheNghiep").attr('title', data.debtCustomerJobMainDto.ngheNghiep);
			$("#debtCustomerJobMainDtotenCongTy").val(data.debtCustomerJobMainDto.tenCongTy);
			$("#debtCustomerJobMainDtotenCongTy").attr('title', data.debtCustomerJobMainDto.tenCongTy);
			$("#debtCustomerJobMainDtoviTri").val(data.debtCustomerJobMainDto.viTri);
			$("#debtCustomerJobMainDtothoiGianCongTac").val(data.debtCustomerJobMainDto.thoiGianCongTac);
			$("#debtCustomerJobMainDtohinhThucTraLuong").val(data.debtCustomerJobMainDto.hinhThucTraLuong);
			$("#debtCustomerJobMainDtongayTraLuongHangThang").val(data.debtCustomerJobMainDto.ngayTraLuongHangThang);
			$("#debtCustomerJobMainDtodiaChiLamViec").val(data.debtCustomerJobMainDto.diaChiLamViec);
			$("#debtCustomerJobMainDtodiaChiLamViec").val(data.debtCustomerJobMainDto.diaChiLamViec);
			$("#debtCustomerJobMainDtodienThoaiCongTy").val(data.debtCustomerJobMainDto.dienThoaiCongTy);
			if(data.debtCustomerJobMainDto.dienThoaiCongTy != null){
				$('#calldebtCustomerJobMainDtodienThoaiCongTy').attr('href',+data.debtCustomerJobMainDto.dienThoaiCongTy);
				$('#calldebtCustomerJobMainDtodienThoaiCongTy').show();
			} else {
				$('#calldebtCustomerJobMainDtodienThoaiCongTy').hide();
			}
		} else {
			$("#debtCustomerJobMainDtongheNghiep").val('');
			$("#debtCustomerJobMainDtongheNghiep").attr('title', '');
			$("#debtCustomerJobMainDtotenCongTy").val('');
			$("#debtCustomerJobMainDtotenCongTy").attr('title', '');
			$("#debtCustomerJobMainDtoviTri").val('');
			$("#debtCustomerJobMainDtothoiGianCongTac").val('');
			$("#debtCustomerJobMainDtohinhThucTraLuong").val('');
			$("#debtCustomerJobMainDtongayTraLuongHangThang").val('');
			$("#debtCustomerJobMainDtodiaChiLamViec").val('');
			$("#debtCustomerJobMainDtodiaChiLamViec").val('');
			$("#debtCustomerJobMainDtodienThoaiCongTy").val('');
			$('#calldebtCustomerJobMainDtodienThoaiCongTy').hide();
		}
		if(data.debtCustomerAddressMainDto != null){
			$("#debtCustomerAddressMainDtotenLoaiNhaO").val(data.debtCustomerAddressMainDto.tenLoaiNhaO);
			$("#debtCustomerAddressMainDtothoiGianSongTaiDcTm").val(data.debtCustomerAddressMainDto.thoiGianSongTaiDcTm);
			$("#debtCustomerAddressMainDtodiaChiTamTru").val(data.debtCustomerAddressMainDto.diaChiTamTru);
			$("#debtCustomerAddressMainDtodiaChiThuongTru").val(data.debtCustomerAddressMainDto.diaChiThuongTru);
			$("#debtCustomerAddressMainDtodiaChiThuongTru").val(data.debtCustomerAddressMainDto.diaChiThuongTru);
			$("#debtCustomerAddressMainDtosoDtNhaTamTru").val(data.debtCustomerAddressMainDto.soDtNhaTamTru);
			if(data.debtCustomerAddressMainDto.soDtNhaTamTru != null){
				$('#calldebtCustomerAddressMainDtosoDtNhaTamTru').attr('href',+data.debtCustomerAddressMainDto.soDtNhaTamTru);
				$('#calldebtCustomerAddressMainDtosoDtNhaTamTru').show();
			} else {
				$('#calldebtCustomerAddressMainDtosoDtNhaTamTru').hide();
			}
		} else {
			$("#debtCustomerAddressMainDtotenLoaiNhaO").val('');
			$("#debtCustomerAddressMainDtothoiGianSongTaiDcTm").val('');
			$("#debtCustomerAddressMainDtodiaChiTamTru").val('');
			$("#debtCustomerAddressMainDtodiaChiThuongTru").val('');
			$("#debtCustomerAddressMainDtodiaChiThuongTru").val('');
			$("#debtCustomerAddressMainDtosoDtNhaTamTru").val('');
			$('#calldebtCustomerAddressMainDtosoDtNhaTamTru').hide();
		}
		$("#debtCustomerMainDtotrinhDoHocVan").val(data.debtCustomerMainDto.trinhDoHocVan);
		$("#debtCustomerMainDtotinhTrangHonNhan").val(data.debtCustomerMainDto.tinhTrangHonNhan);
		$("#debtCustomerMainDtonguonThuNhap").val(data.debtCustomerMainDto.nguonThuNhap);
		$("#debtCustomerMainDtosoSoHoKhau").val(data.debtCustomerMainDto.soSoHoKhau);
		$("#debtCustomerMainDtogioiTinh").val(data.debtCustomerMainDto.gioiTinh);
		$("#debtCustomerMainDtodpdKh").val(data.debtCustomerMainDto.dpdKh);
		$("#debtCustomerMainDtosoNguoiPhuThuoc").val(data.debtCustomerMainDto.soNguoiPhuThuoc);
		$("#debtCustomerMainDtosoLuongHd").val(data.debtCustomerMainDto.soLuongHd);
		$("#debtCustomerMainDtotongThuNhapThang").val(data.debtCustomerMainDto.tongThuNhapThang);
		$("#debtCustomerMainDtotongSoTienQuaHan").val(data.debtCustomerMainDto.tongSoTienQuaHan);
		$("#debtCustomerMainDtotongSotienPhaiThu").val(data.debtCustomerMainDto.tongSotienPhaiThu);
		/* 2. Thông tin hợp đồng  */
		tableDebtCustomerLdDetailMainDtoRow.clearTable();
		$.each( data.listDebtCustomerLdDetailMainDto, function( key, item ) {
			tableDebtCustomerLdDetailMainDtoRow.createRow(item);
		});
		/* 3. Thông tin liên hệ   */
		/* 3.1 Thông tin ban đầu từ LOS  */
		$("#debtCustomerMainDtosoDienThoai").val(data.debtCustomerMainDto.soDienThoai);
		if(data.debtCustomerMainDto.soDienThoai != null){
			$('#calldebtCustomerMainDtosoDienThoai').attr('href',+data.debtCustomerMainDto.soDienThoai);
			$('#calldebtCustomerMainDtosoDienThoai').show();
		} else {
			$('#calldebtCustomerMainDtosoDienThoai').hide();
		}

		
		$("#debtCustomerMainDtosoDienThoaiKhac").val(data.debtCustomerMainDto.soDienThoaiKhac);
		if(data.debtCustomerMainDto.soDienThoaiKhac != null){
			$('#calldebtCustomerMainDtosoDienThoaiKhac').attr('href',+data.debtCustomerMainDto.soDienThoaiKhac);
			$('#calldebtCustomerMainDtosoDienThoaiKhac').show();
		} else {
			$('#calldebtCustomerMainDtosoDienThoaiKhac').hide();
		}
		/* Thông tin liên hệ LOS */
		tableRelationByLos.clearTable();
		$.each( data.debtCustomerRelationMainDto, function( key, item ) {
			tableRelationByLos.createRow(item);
		});
		/* 3.1 Thông tin ban đầu từ DEBT  */
		getDebtCustomerAddressByColl();
		getTableDebtCustomerRelationByColl();
		
		$(".txtformatMoney").unmask();
		$(".txtformatMoney").mask("000.000.000.000.000.000", {reverse: true});
		// 5. Tác động mới nhất
		setTimeout(function(){
			loadTableImpact();
		},100);
		
	}
}

function getDebtCustomerAddressByColl(){
	// load table ajax
	var jsonString;
	$.ajax({
		url : CMSCommonJs.ROOT_URL + 'common/ajax/getDebtCustomerAddressByColl',
		headers: {'X-CSRF-TOKEN': csrfToken},
		timeout : 30000,
		dataType : "json",
		type : "POST",
		async : true,
		data : {
			customerId : $("#debtCustomerMainDtoId").val()
        },
		success : function(data) {
			if(data != undefined){
				$("#debtCustomerAddressByCollMainDtodiaChiTamTru").val(data.diaChiTamTru);
				$("#debtCustomerAddressByCollMainDtodiaChiThuongTru").val(data.diaChiThuongTru);
				$("#debtCustomerAddressByCollMainDtodiaChiLamViec").val(data.diaChiLamViec);
				if(data.soDienThoaiCall != null){
					$( "#debtCustomerAddressByCollMainDtoSoDienThoaiCall").show();
					$( "#debtCustomerAddressByCollMainDtoSoDienThoaiCall").attr("href",data.soDienThoaiCall);
					
				} else{
					$( "#debtCustomerAddressByCollMainDtoSoDienThoaiCall").hide();
				}
				if(data.soDienThoaiKhacCall != null){
					$( "#debtCustomerAddressByCollMainDtoSoDienThoaiKhacCall").show();
					$( "#debtCustomerAddressByCollMainDtoSoDienThoaiKhacCall").attr("href",data.soDienThoaiKhacCall);
					
				} else{
					$( "#debtCustomerAddressByCollMainDtoSoDienThoaiKhacCall").hide();
				}
				if(data.soDtNhaTamTruCall != null){
					$( "#debtCustomerAddressByCollMainDtoSoDtNhaTamTruCall").show();
					$( "#debtCustomerAddressByCollMainDtoSoDtNhaTamTruCall").attr("href",data.soDtNhaTamTruCall);
					
				} else{
					$( "#debtCustomerAddressByCollMainDtoSoDtNhaTamTruCall").hide();
				}
				if(data.dienThoaiCongTyCall != null){
					$( "#debtCustomerAddressByCollMainDtoDienThoaiCongTyCall").show();
					$( "#debtCustomerAddressByCollMainDtoDienThoaiCongTyCall").attr("href",data.dienThoaiCongTyCall);
					
				} else{
					$( "#debtCustomerAddressByCollMainDtoDienThoaiCongTyCall").hide();
				}
				$("#debtCustomerAddressByCollMainDtoSoDienThoai").val(data.soDienThoai);
				$("#debtCustomerAddressByCollMainDtoSoDienThoaiKhac").val(data.soDienThoaiKhac);
				$("#debtCustomerAddressByCollMainDtoSoDtNhaTamTru").val(data.soDtNhaTamTru);
				$("#debtCustomerAddressByCollMainDtoDienThoaiCongTy").val(data.dienThoaiCongTy);
				// load Modal
				dataDiaBanCollector = data;
			} else {
				$("#debtCustomerAddressByCollMainDtodiaChiTamTru").val('');
				$("#debtCustomerAddressByCollMainDtodiaChiThuongTru").val('');
				$("#debtCustomerAddressByCollMainDtodiaChiLamViec").val('');
				$("#debtCustomerAddressByCollMainDtoSoDienThoai").val('');
				$("#debtCustomerAddressByCollMainDtoSoDienThoaiKhac").val('');
				$("#debtCustomerAddressByCollMainDtoSoDtNhaTamTru").val('');
				$("#debtCustomerAddressByCollMainDtoDienThoaiCongTy").val('');
				$("#debtCustomerAddressByCollMainDtoSoDienThoaiCall").hide();
				$("#debtCustomerAddressByCollMainDtoSoDienThoaiKhacCall").hide();
				$("#debtCustomerAddressByCollMainDtoSoDtNhaTamTruCall").hide();
				$("#debtCustomerAddressByCollMainDtoDienThoaiCongTyCall").hide();
			}
		}
	});
}
function getTableDebtCustomerRelationByColl(){
	// load table ajax
	var jsonString;
	$.ajax({
		url : CMSCommonJs.ROOT_URL + 'common/ajax/getTableDebtCustomerRelationByColl',
		headers: {'X-CSRF-TOKEN': csrfToken},
		timeout : 30000,
		dataType : "json",
		type : "POST",
		async : true,
		data : {
			customerId : $("#debtCustomerMainDtoId").val()
        },
		success : function(data) {
			tableRelationByColl.clearTable();
			DCS.Main.MoiQuanHeOldList = []; 
			$.each( data, function( key, item ) {
				tableRelationByColl.createRow(item);
				DCS.Main.MoiQuanHeOldList.push({
					tenNguoiLienQuan : item.tenNguoiLienQuan,
					soDienThoaiNguoiLienQuan : item.soDienThoaiNguoiLienQuan,
					tenQuanHe : item.tenQuanHe,
					maQuanHe : item.maQuanHe
				});
   			});
		}
	});
}

function loadDiaBanModal(data){
	// tạm trú
	if(data.maTinhTamTru != null){
		listDistrict = getDistrictByProvinceCode(data.maTinhTamTru);
		DiaBanTamTruTable.$el.find("#tinh0").select2("val", data.maTinhTamTru);
	}
	if(data.maQuanHuyenTamTru != null){
		listWard = getWardByDistrictCode(data.maQuanHuyenTamTru);
		DiaBanTamTruTable.$el.find("#quan0").select2("data", { id: data.maQuanHuyenTamTru, text: data.tenQuanHuyenTamTru });
	}
	if(data.maPhuongXaTamTru != null){
		DiaBanTamTruTable.$el.find("#phuong0").select2("data", { id: data.maPhuongXaTamTru, text: data.tenPhuongXaTamTru });
	}
	DiaBanTamTruTable.$el.find("#diaChiChiTiet0").val(data.diaChiChiTietTamTru);
	// thuong trú
	if(data.maTinhThuongTru != null){
		listDistrict = getDistrictByProvinceCode(data.maTinhThuongTru);
		DiaBanThuongTruTable.$el.find("#tinh0").select2("val", data.maTinhThuongTru);
	}
	if(data.maQuanHuyenThuongTru != null){
		listWard = getWardByDistrictCode(data.maQuanHuyenThuongTru);
		DiaBanThuongTruTable.$el.find("#quan0").select2("data", { id: data.maQuanHuyenThuongTru, text: data.tenQuanHuyenThuongTru });
	}
	if(data.maPhuongXaThuongTru != null){
		DiaBanThuongTruTable.$el.find("#phuong0").select2("data", { id: data.maPhuongXaThuongTru, text: data.tenPhuongXaThuongTru });
	}
	DiaBanThuongTruTable.$el.find("#diaChiChiTiet0").val(data.diaChiChiTietThuongTru);
	// cơ quan
	if(data.maTinhNoiLamViec != null){
		listDistrict = getDistrictByProvinceCode(data.maTinhNoiLamViec);	
		DiaBanCoQuanTable.$el.find("#tinh0").select2("val", data.maTinhNoiLamViec);
	}
	if(data.maQuanHuyenNoiLamViec != null){
		listWard = getWardByDistrictCode(data.maQuanHuyenNoiLamViec);
		DiaBanCoQuanTable.$el.find("#quan0").select2("data", { id: data.maQuanHuyenNoiLamViec, text: data.tenQuanHuyenNoiLamViec });
	}
	if(data.maPhuongXaNoiLamViec != null){
		DiaBanCoQuanTable.$el.find("#phuong0").select2("data", { id: data.maPhuongXaNoiLamViec, text: data.tenPhuongXaNoiLamViec });
	}
	DiaBanCoQuanTable.$el.find("#diaChiChiTiet0").val(data.diaChiChiTietNoiLamViec);
}

function loadTableImpact(){
	// load table ajax
	var jsonString;
	$.ajax({
		url : CMSCommonJs.ROOT_URL + 'common/ajax/searchTableImpact',
		headers: {'X-CSRF-TOKEN': csrfToken},
		timeout : 30000,
		dataType : "json",
		type : "POST",
		async : true,
		data : {
			lmsCifNo : $("#debtCustomerMainDtolmsCifNo").val()
        },
		success : function(data) {
			jsonString = data;
			var table = $("#tableActionList").dataTable({
				"data" : jsonString,
				"language" : DATA_TABLE_LANG_VI,
				"iDisplayLength": 10,
		        "scrollX"  : true,
		        "paging"   : false,
		        "ordering" : false,
		        "searching": false,
		        "destroy": true,
				"oSearch": {"sSearch": ""},			
				"columns": [
				        { data: 'createDateStr'} ,
				        { data: 'createTimeStr'} ,
				        { data: 'maNhanVien'},
				        { data: 'collMethodCode'},
				        { data: 'noiLienHe'},
				        { data: 'nguoiLienHe'},
				        { data: 'actionCode'},
				        { data: 'callBackTimeStr'},
				        { data: 'ngayHuaThanhToanStr'},
				        { data: 'soTienHuaThanhToan'},
				        { data: 'ghiChuKhac'} 
		   		  ],
		   		"columnDefs": [
		   			{
		        		'targets': 9,
			         	'type': 'numeric-dots',
			         	'className': 'dt-body-right',
			         	'render': function (data, type, full, meta){
			         		var result = 0;
			         		if(data != null){
			         			var result = data.toString().replace(/(\d)(?=(\d{3})+(?!\d))/g, '$1.');
			         		} else {
			         			return '';
			         		}
			         		return result;
			          	}
			        },
			        {
		        		'targets': 10,
		        		 'render': $.fn.dataTable.render.ellipsis( 200 )
			        }
		   		]
		    });
		}
	});
}

function updateDebtCustomerRelationByColl(dataDcTamTru,dataDcThuongTru,dataDcCoQuan,dataDienThoai,dataMoiQuanHe){
	$.ajax({
		url : CMSCommonJs.ROOT_URL + 'dashboard/callerSoftCall/ajax/updateDebtCustomerRelationByColl',
		dataType : "json",
		headers: {'X-CSRF-TOKEN': csrfToken},
		type : "POST",
		async : true,
		timeout : 30000,
		data : {
			customerId : $("#debtCustomerMainDtoId").val(), 
			dataDcTamTru : JSON.stringify(dataDcTamTru),
			dataDcThuongTru : JSON.stringify(dataDcThuongTru),
			dataDcCoQuan : JSON.stringify(dataDcCoQuan),
			dataDienThoai : JSON.stringify(dataDienThoai),
			dataMoiQuanHe : JSON.stringify(dataMoiQuanHe)
        },
        beforeSend: function() {
			HoldOn.open({ theme:"sk-cube-grid" })
		},
		complete: function(data) {
			HoldOn.close();
			if(data.responseText != ''){
				alertErrorModal(data.responseText);
			} else {
				alertSuccessModal('Cập nhật thông tin liên hệ thành công');
				// load data form
				getDebtCustomerAddressByColl();
				getTableDebtCustomerRelationByColl();
			}
		}
	})
}
	
function getDistrictByProvinceCode(proviceCode){
 	var output = [];
 	$.ajax({
 		url : CMSCommonJs.ROOT_URL + 'common/ajax/getDistrictByProvinceCode',
 		headers: {'X-CSRF-TOKEN': csrfToken},
 		dataType : "json",
 		type : "POST",
 		data : {
 			proviceCode : proviceCode
 		},
 		async : false,
 		timeout : 5000,
 		success : function(data) {
 			output = $.map(data, function(item) {
 				return {
 					text : item.districtName,
 					id : item.districtCode
 				}
 			});
 		}
 	});
 	return output;
 };
 
 function getWardByDistrictCode(districtCode){
	 var output = [];
	 $.each(listAllWard, function( index, value ) {
		  if(value.districtCode == districtCode){
			  output.push({'id' : value.wardCode, 'text' : value.wardName});
		  }
	 });
	 return output;
 }
 
function getAllWard(){
 	//var output = [];
 	$.ajax({
 		url : CMSCommonJs.ROOT_URL + 'common/ajax/getAllWard',
 		headers: {'X-CSRF-TOKEN': csrfToken},
 		dataType : "json",
 		type : "POST",
 		async : true,
 		timeout : 15000,
 		success : function(data) {
 			listAllWard = data;
 		}
 	});
};
/*Thông tin liên hệ LOS*/
var createtableRelationByLos = function(data) {
	// category
	tableRelationByLos = new SimpleFormTable({
	$el : $("#listDebtCustomerRelationByLosMainDto"),
	$template : $.templates("#DebtCustomerRelationByCollMainDtoRow"),
	data : data,
	createRowData : function(row, rowData) {
		var index = row.attr("rowid");
		if(rowData.tenNguoiLienQuan != undefined ){
			row.find("#labelNguoiLienQuan" + index).text('Người LH ' + rowData.stt);
			row.find("#debtCustomerRelationByCollMainDtotenNguoiLienQuan" + index).val(rowData.tenNguoiLienQuan);
			row.find("#debtCustomerRelationByCollMainDtoSoDienThoaiNguoiLienQuan" + index).val(rowData.soDienThoaiNguoiLienQuan);
			row.find("#debtCustomerRelationByCollMainDtoTenQuanHe" + index).val(rowData.tenQuanHe);
			if(rowData.soDienThoaiNguoiLienQuanCall != null){
				row.find('a:first').attr('href',+rowData.soDienThoaiNguoiLienQuanCall);
				row.find('a:first').show();
			} else {
				row.find('a:first').hide();
			}
		}
	}});
}
/*Thông tin liên hệ DEBT COLLECTION*/
var createtableRelationByColl = function(data) {
	// category
	tableRelationByColl = new SimpleFormTable({
	$el : $("#listDebtCustomerRelationByCollMainDto"),
	$template : $.templates("#DebtCustomerRelationByCollMainDtoRow"),
	data : data,
	createRowData : function(row, rowData) {
		var index = row.attr("rowid");
		if(rowData.tenNguoiLienQuan != undefined || rowData.soDienThoaiNguoiLienQuan != undefined){
			row.find("#labelNguoiLienQuan" + index).text('Người LH ' + rowData.stt);
			row.find("#debtCustomerRelationByCollMainDtotenNguoiLienQuan" + index).val(rowData.tenNguoiLienQuan);
			row.find("#debtCustomerRelationByCollMainDtoSoDienThoaiNguoiLienQuan" + index).val(rowData.soDienThoaiNguoiLienQuan);
			row.find("#debtCustomerRelationByCollMainDtoTenQuanHe" + index).val(rowData.tenQuanHe);
			if(rowData.soDienThoaiNguoiLienQuanCall != null){
				row.find('a:first').attr('href',+rowData.soDienThoaiNguoiLienQuanCall);
				row.find('a:first').show();
			} else {
				row.find('a:first').hide();
			}
		}
	}});
}

/*Thông tin liên hệ DEBT COLLECTION AUTO*/
var createtableRelationByCollAuto = function(data) {
	// category
	tableRelationByColl = new SimpleFormTable({
	$el : $("#listDebtCustomerRelationByCollMainDto"),
	$template : $.templates("#DebtCustomerRelationByCollMainAutoDtoRow"),
	data : data,
	createRowData : function(row, rowData) {
		var index = row.attr("rowid");
		if(rowData.tenNguoiLienQuan != undefined || rowData.soDienThoaiNguoiLienQuan != undefined){
			row.find("#labelNguoiLienQuan" + index).text('Người LH ' + rowData.stt);
			row.find("#debtCustomerRelationByCollMainDtotenNguoiLienQuan" + index).val(rowData.tenNguoiLienQuan);
			row.find("#debtCustomerRelationByCollMainDtoSoDienThoaiNguoiLienQuan" + index).val(rowData.soDienThoaiNguoiLienQuan);
			row.find("#debtCustomerRelationByCollMainDtoTenQuanHe" + index).val(rowData.tenQuanHe);
		}
	}});
}

/*Hợp đồng*/
var createtableDebtCustomerLdDetailMainDtoRow = function(data) {
	// category
	tableDebtCustomerLdDetailMainDtoRow = new SimpleFormTable({
	$el : $("#listDebtCustomerLdDetailMainDto"),
	$template : $.templates("#debtCustomerLdDetailMainDtoRow"),
	data : data,
	createRowData : function(row, rowData) {
		var index = row.attr("rowid");
		if(rowData.soHopDong != undefined){
			row.find("#debtCustomerLdDetailMainDtosoHopDong" + index).text('Hợp đồng : ' + rowData.soHopDong);
			row.find("#debtCustomerLdDetailMainDtoldStatus" + index).val(rowData.ldStatus);
			row.find("#debtCustomerLdDetailMainDtomucDichVay" + index).val(rowData.mucDichVay);
			row.find("#debtCustomerLdDetailMainDtomaSanPham" + index).val(rowData.maSanPham);
			row.find("#debtCustomerLdDetailMainDtotenSanPham" + index).val(rowData.tenSanPham);
			row.find("#debtCustomerLdDetailMainDtosaleCode" + index).val(rowData.saleCode);
			row.find("#debtCustomerLdDetailMainDtongayThanhToanGanNhatStr" + index).val(rowData.ngayThanhToanGanNhatStr);
			row.find("#debtCustomerLdDetailMainDtosoTienTtGanNhat" + index).val(rowData.soTienTtGanNhat);
			row.find("#debtCustomerLdDetailMainDtotongSoTienDaThu" + index).val(rowData.tongSoTienDaThu);
			row.find("#debtCustomerLdDetailMainDtosoTienGiaiNgan" + index).val(rowData.soTienGiaiNgan);
			row.find("#debtCustomerLdDetailMainDtosoTienDuocBh" + index).val(rowData.soTienDuocBh);
			row.find("#debtCustomerLdDetailMainDtophiBaoHiem" + index).val(rowData.phiBaoHiem);
			row.find("#debtCustomerLdDetailMainDtoemiAmount" + index).val(rowData.emiAmount);
			row.find("#debtCustomerLdDetailMainDtoduNo" + index).val(rowData.duNo);
			row.find("#debtCustomerLdDetailMainDtotongSotienQuaHan" + index).val(rowData.tongSotienQuaHan);
			row.find("#debtCustomerLdDetailMainDtotongSotienPhaiThu" + index).val(rowData.tongSotienPhaiThu);
			row.find("#debtCustLdCaseMaskingMainDtoflagCode" + index).val(rowData.flagCode);
			row.find("#debtCustLdCaseMaskingMainDtoflagDesc" + index).val(rowData.flagDesc);
			row.find("#debtCustomerLdDetailMainDtonhomNoTheoHdDpd" + index).val(rowData.nhomNoTheoHdDpd);
			row.find("#debtCustomerLdDetailMainDtodpdHd" + index).val(rowData.dpdHd);
			row.find("#debtCustomerLdDetailMainDtosoDuTkKhachHang" + index).val(rowData.soDuTkKhachHang);
			row.find("#debtCustomerLdDetailMainDtolaiSuat" + index).val(rowData.laiSuat +'%');
			row.find("#debtCustomerLdDetailMainDtongayGiaiNganStr" + index).val(rowData.ngayGiaiNganStr);
			row.find("#debtCustomerLdDetailMainDtokyHanVay" + index).val(rowData.kyHanVay);
			row.find("#debtCustomerLdDetailMainDtongayTraNoDauTienStr" + index).val(rowData.ngayTraNoDauTienStr);
			row.find("#debtCustomerLdDetailMainDtokyTraNoHienTai" + index).val(rowData.kyTraNoHienTai);
			row.find("#debtCustomerLdDetailMainDtoghiChuTuThamDinh" + index).val(rowData.ghiChuTuThamDinh);
			row.find("#debtCustomerLdDetailMainDtoghiChuTuThamDinh" + index).attr('title', rowData.ghiChuTuThamDinh);
		}
	}});
}