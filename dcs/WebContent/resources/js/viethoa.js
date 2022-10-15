/**
 * CONSTANTS
 */
var DATA_TABLE_LANG_VI = {
	"emptyTable" : "Chưa có thông tin",
	"search" : "<b style='color: #F58220'>Tìm kiếm &nbsp; <i class='icon-search'></i></b>",
	"lengthMenu" : "Hiển thị _MENU_ bản ghi một trang",
	"zeroRecords" : "Không tìm thấy kết quả nào",
	"info" : "Hiển thị _PAGE_ / _PAGES_",
	"infoEmpty" : "Không tìm thấy kết quả nào",
	"infoFiltered" : "(lọc từ _MAX_ tổng số bản ghi)",
	"paginate" : {
		"first" : "Đầu",
		"last" : "Cuối",
		"next" : "Tiếp",
		"previous" : "Trước"
	},
	"aria" : {
		"sortAscending" : ": sắp xếp giảm dần",
		"sortDescending" : ": sắp xếp tăng dần"
	}
}

/**
 * Doc so tieng Viet cho mot so bat ky
 */
CMSCommonJs.Form.docSoTiengViet = function(so) {
	var mangso = [ 'không', 'một', 'hai', 'ba', 'bốn', 'năm', 'sáu', 'bảy',
			'tám', 'chín' ];

	var dochangchuc = function(so, daydu) {
		var chuoi = "";
		chuc = Math.floor(so / 10);
		donvi = so % 10;
		if (chuc > 1) {
			chuoi = " " + mangso[chuc] + " mươi";
			if (donvi == 1) {
				chuoi += " mốt";
			}
		} else if (chuc == 1) {
			chuoi = " mười";
			if (donvi == 1) {
				chuoi += " một";
			}
		} else if (daydu && donvi > 0) {
			chuoi = " lẻ";
		}
		if (donvi == 5 && chuc > 1) {
			chuoi += " lăm";
		} else if (donvi > 1 || (donvi == 1 && chuc == 0)) {
			chuoi += " " + mangso[donvi];
		}
		return chuoi;
	}
	var docblock = function(so, daydu) {
		var chuoi = "";
		tram = Math.floor(so / 100);
		so = so % 100;
		if (daydu || tram > 0) {
			chuoi = " " + mangso[tram] + " trăm";
			chuoi += dochangchuc(so, true);
		} else {
			chuoi = dochangchuc(so, false);
		}
		return chuoi;
	}
	var dochangtrieu = function(so, daydu) {
		var chuoi = "";
		trieu = Math.floor(so / 1000000);
		so = so % 1000000;
		if (trieu > 0) {
			chuoi = docblock(trieu, daydu) + " triệu";
			daydu = true;
		}
		nghin = Math.floor(so / 1000);
		so = so % 1000;
		if (nghin > 0) {
			chuoi += docblock(nghin, daydu) + " nghìn";
			daydu = true;
		}
		if (so > 0) {
			chuoi += docblock(so, daydu);
		}
		return chuoi;
	}
	var docso = function(so) {
		if (so == 0)
			return mangso[0];
		var chuoi = "", hauto = "";
		do {
			ty = so % 1000000000;
			so = Math.floor(so / 1000000000);
			if (so > 0) {
				chuoi = dochangtrieu(ty, true) + hauto + chuoi;
			} else {
				chuoi = dochangtrieu(ty, false) + hauto + chuoi;
			}
			hauto = " tỷ";
		} while (so > 0);
		return chuoi;
	}

	return docso(so);
}