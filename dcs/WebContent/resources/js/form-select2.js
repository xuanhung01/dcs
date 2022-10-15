/**
 * Functions for select2
 */

CMSCommonJs.Select2 = CMSCommonJs.Select2 || {};

CMSCommonJs.Select2.createSelect2Ajax = function(el, options) {
	var getOne = function(rootUrl, id) {
		var output;
		$.ajax({
			url : rootUrl + options.urlOne,
			dataType : "json",
			type : "GET",
			data : function(params) {
				var dataParam = {};
				dataParam[options.idName] = id;
				return dataParam;
			},
			async : false,
			success : function(data) {
				output = options.processResults(data)
			}
		});
		return output;
	};
	el.select2({
		language : "vi",
		placeholder : "Lựa chọn",
		allowClear : true,
		ajax : {
			url : options.urlAll,
			dataType : "json",
			type : "GET",
			data : function(params) {
			},
			processResults : options.processResults
		},
		initSelection : function(element, callback) {
			var id = $(element).val();
			var result = options.getOne(options.rootUrl, id);
			callback(result);
		}
	});
}

CMSCommonJs.Select2.findInArray = function(array, term) {
	var results = [];
	if (term) {
		results = $.grep(array, function(entry, index) {
			return entry.text.indexOf(term) != -1;
		});
	} else {
		results = array;
	}
	return results;
}

CMSCommonJs.Select2.queryArray = function(array, query) {
	var results = [];
	if (query.term) {
		results = $.grep(array, function(entry, index) {
			return entry.text.indexOf(query.term) != -1;
		});
	} else {
		results = array;
	}
	query.callback({
		results : results
	});
}

CMSCommonJs.Select2.initSelectionArray = function(array, element, callback) {
	var id = $(element).val();
	// console.log(id);
	var result = [];

	result = $.grep(array, function(entry, index) {
		return entry.id == id;
	});
	callback(result[0]);
}

CMSCommonJs.Select2.createSelect2ArrayStatic = function(options) {
	el.select2({
		language : "vi",
		placeholder : "Lựa chọn",
		allowClear : true,
		query : function(query) {
			CMSCommonJs.Select2.queryArray(options.staticArray, query);
		},
		initSelection : function(element, callback) {
			CMSCommonJs.Select2.initSelectionArray(options.staticArray,
					element, callback);
		}
	});
}