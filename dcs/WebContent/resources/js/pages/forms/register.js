var CMS = CMS || {};

CMS.FormRegister = {};

var currentForm;
if ($("#registerForm")[0]) {
	currentForm = $("#registerForm");
} else {
	currentForm = $("#editProfileForm");

}
var currentFormDom = currentForm[0];

CMS.FormRegister.getTenDonViCapTren = function(editable) {
	var ma = $('#maDonViCapTren').val();
	if (editable == undefined) {
		editable = true;
	} else {
		editable = false;
	}
	if (ma) {
		var ajaxUrl = CMSCommonJs.ROOT_URL + '/ajax/danhmuc/dvsdns';
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
					$("#tenDonViCapTren").val(result.ten);

				} else {
					$("#tenDonViCapTren").val("");

				}
			}
		});
	}
}

// MAIN
$(function() {
	// Create form
	// ------------------------------------------------------------------------------------
	var isEditable = currentForm.attr("formMode") == "create"
			|| currentForm.attr("formMode") == "edit";
	var isHasData = currentForm.attr("formMode") == "view"
			|| currentForm.attr("formMode") == "edit";

	$("[title='CAPTCHA']").text("");
	$('#maTinh').select2({
		placeholder : "Lựa chọn",
		language : "vi"
	});
	$('#role').select2({
		placeholder : "Lựa chọn",
		language : "vi"
	});

	if (isEditable) {
		// Form validate
		currentForm.validate({
			errorClass : "valError",
			errorElement : "span",
			ignore : 'input[type=hidden]',
			ignoreTitle : true,
			rules : {
				userName : {
					noSpace : true,
					CheckUTF8 : true,
				},
				email : {
					checkEmail : currentForm.find("[name=userName]")
				}
			},
			showErrors : function(errorMap, errorList) {
				this.defaultShowErrors();
				if (errorList.length > 0) {
					CMSCommonJs.Form.showNotifier(currentForm);
				}
			}
		});

		CMSCommonJs.Form.addFormSubmitDialogCheck(currentForm, "#Nhap", "#Gui",
				"#SendDialog");
	} else {
	}

	// init Data
	// ------------------------------------------------------------------------------------
	if (isHasData) {
		CMS.FormRegister.getTenDonViCapTren(isEditable);
	}

	// init event handler
	// ------------------------------------------------------------------------------------

	if (isEditable) {
		$('#maDonViCapTren').change(function() {
			CMS.FormRegister.getTenDonViCapTren(isEditable);
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