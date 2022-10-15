/**
 * SimpleFormTable is a simple table that populate rows by templates. Should use Backbone or Marrionette!
 */
var SimpleFormTable = function(options) {
	this.$el = options.$el;

	this.createRowData = options.createRowData;
	this.createEmptyRow = options.createEmptyRow;
	this.onCreateRowsFinish = options.onCreateRowsFinish;

	this.$template = options.$template;
	this.rowIndexClass = options.rowIndexClass || "rowIndex";
	this.editable = true;
	this.form = options.form || this.$el.parents("form")[0];
	if (this.form) {
		this.editable = ($(this.form).attr("formMode") == "create")
				|| ($(this.form).attr("formMode") == "edit");
		// console.log("Found a form " + this.editable);
	}

	jQuery.extend(this, {
		createRow : function(data) {
			var cid = this.getNumRow();
			/*
			 * if (data == undefined) { if (this.createEmptyRow == undefined) {
			 * data = this.createEmptyRow(); data.index = cid; } else { data = {
			 * index : cid }; } } else { data.index = cid; }
			 */
			if (data == undefined) {
				data = {
					index : cid
				};
			} else {
				data.index = cid;
			}

			var html = this.$template.render(data);
			var table = this;
			var newRow = $(html);
			this.$el.find("tbody").append(newRow);
			// var newRow = this.$el.find("tr[rowId=" + cid + "]");
			this.createRowAction(newRow);
			this.createRowData(newRow, data);
			this.reindexRows();
		},
		createTitleAction : function() {
			var table = this;
			//if (this.editable) {
				this.$el.find("#btnAdd").click(function(evt) {
					evt.preventDefault();
					table.createRow();
				});
				this.$el.find("#btnAdd").keypress(function(e) {
					e.preventDefault();
					return false;
				});
				this.$el.find("#btnAdd").blur();
			//} else {
				//this.$el.find("#btnAdd").prop("disabled", true);
			//}
		},

		createRowAction : function(row) {
			var table = this;
			//if (this.editable) {
				row.find("#btnRemove").click(function(evt) {
					evt.preventDefault();
					table.removeRow($(this).data("rowid"));

				});
				row.find("#btnRemove").keypress(function(e) {
					e.preventDefault();
					return false;
				});
				row.find("#btnRemove").blur();
			/*} else {
				row.find("#btnRemove").prop("disabled", true);
				row.find("#btnRemove").hide();
			}*/
		},

		removeRow : function(id) {
			//console.log(id);
			if (this.getNumRow() > 1) {
				this.$el.find("tr[rowid=" + id + "]").remove();
				this.reindexRows();
			}

		},

		refreshTable : function() {
			this.$el.find("tbody").empty();
			// this.createRow();
		},

		clearTable : function() {
			this.$el.find("tbody").empty();
			// this.createRow();
		},
		getNumRow : function() {
			return this.$el.find("tbody tr").length;
		},

		reindexRows : function() {
			this.$el.find("." + this.rowIndexClass).each(
					function(order, element) {
						$(element).text(order + 1);
					});
			$("tbody tr").each(function(i, tr) {
				$(tr).attr("rowid", i);
				$(tr).find("#btnRemove").attr("data-rowid", i);
			});

		},

		validateTable : function() {

		}
	});

	// this.createRow();
	this.createTitleAction();

	// If there is initial data
	if (options.data) {
		var table = this;
		$(options.data).each(function(i, d) {
			table.createRow(d);
		});
		if (table.onCreateRowsFinish) {
			table.onCreateRowsFinish();
		}
	} else {
		this.createRow();
	}

	
	$(document).keypress(function(e) {
		if (e.keyCode == 13) {
			e.preventDefault();
			return false;
		}
	});
	
};
