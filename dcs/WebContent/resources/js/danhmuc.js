/**
 * Create static map for danh muc
 */
/**
 * Danh muc CoQuanTaiChinh
 */
CMS.DM.CQTC= {};
CMS.DM.CQTC.IdBoTaiChinh = '00';
/**
 * Danh muc loai chi phi
 */
CMS.DM.createDMList_LoaiCP = function() {

	$.ajax({
		url : CMSCommonJs.ROOT_URL + 'ajax/danhmuc/list/loaiCP',
		dataType : "json",
		type : "GET",
		async : false,
		data : {
		},
		success : function(data) {
			CMS.DM.List.LoaiCP = $.map(data, function(item) {
				return {
					text : item.ma + " - " + item.ten,
					id : item.id,
					ma : item.ma
				}
			});
		}
	});
}

/**
 * Danh muc loai hinh don vi
 */
CMS.DM.createDMList_LoaiHDV = function() {

	$.ajax({
		url : CMSCommonJs.ROOT_URL + 'ajax/danhmuc/list/loaiHinhDV',
		dataType : "json",
		type : "GET",
		async : false,
		data : {
		},
		success : function(data) {
			CMS.DM.List.LoaiHDV = $.map(data, function(item) {
				return {
					text : item.ma + " - " + item.ten,
					id : item.id,
					ma : item.ma
				}
			});
		}
	});
}

CMS.DM.createDMList_NhomDA = function() {
	
	$.ajax({
		url : CMSCommonJs.ROOT_URL + 'ajax/danhmuc/list/nhomDuAn',
		dataType : "json",
		type : "GET",
		async : false,
		data : {
		},
		success : function(data) {
			CMS.DM.List.NhomDA = $.map(data, function(item) {
				return {
					text : item.ma + " - " + item.ten,
					id : item.id,
					ma : item.ma
				}
			});
		}
	});
}

CMS.DM.createDMList_HinhThucDuAn = function() {
	
	$.ajax({
		url : CMSCommonJs.ROOT_URL + 'ajax/danhmuc/list/hinhThucDuAn',
		dataType : "json",
		type : "GET",
		async : false,
		data : {
		},
		success : function(data) {
			CMS.DM.List.HinhThucDuAn = $.map(data, function(item) {
				return {
					text : item.ma + " - " + item.ten,
					id : item.id,
					ma : item.ma
				}
			});
		}
	});
}

CMS.DM.createDMList_HinhThucQuanLy = function() {
	
	$.ajax({
		url : CMSCommonJs.ROOT_URL + 'ajax/danhmuc/list/hinhThucQuanLy',
		dataType : "json",
		type : "GET",
		async : false,
		data : {
		},
		success : function(data) {
			CMS.DM.List.HinhThucQuanLy = $.map(data, function(item) {
				return {
					text : item.ma + " - " + item.ten,
					id : item.id,
					ma : item.ma
				}
			});
		}
	});
}

CMS.DM.createDMList_NganhKT = function() {
	
	$.ajax({
		url : CMSCommonJs.ROOT_URL + 'ajax/danhmuc/list/nganhKT',
		dataType : "json",
		type : "GET",
		async : false,
		data : {
		},
		success : function(data) {
			CMS.DM.List.NganhKT = $.map(data, function(item) {
				return {
					text : item.ma + " - " + item.ten,
					id : item.id,
					ma : item.ma
				}
			});
		}
	});
}

CMS.DM.createDMList_ChuongNganSach = function() {
	
	$.ajax({
		url : CMSCommonJs.ROOT_URL + 'ajax/danhmuc/list/chuongNganSach',
		dataType : "json",
		type : "GET",
		async : false,
		data : {
		},
		success : function(data) {
			CMS.DM.List.ChuongNganSach = $.map(data, function(item) {
				return {
					text : item.chuong + " - " + item.ten,
					id : item.id,
					ma : item.cap
				}
			});
		}
	});
}

CMS.DM.createDMList_CapDuToan = function() {
	CMS.DM.List.CapDuToan = [];
	CMS.DM.List.CapDuToan.push(
			{
				text : "1",
				id : 1,
				ma : "1"
			}
	);
	CMS.DM.List.CapDuToan.push(
			{
				text : "2",
				id : 2,
				ma : 2
			}
	);
	CMS.DM.List.CapDuToan.push(
			{
				text : "3",
				id : 3,
				ma : "3"
			}
	);
	
}