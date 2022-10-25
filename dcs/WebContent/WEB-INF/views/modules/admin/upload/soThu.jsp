<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<spring:url value="/admin/uploadSoThu/add?${_csrf.parameterName}=${_csrf.token}" var="addFormSubmitUrl" />

<form:form modelAttribute="formModel" id="editProfileForm" class="form-horizontal" formMode="create" action="${addFormSubmitUrl}" method="POST" enctype="multipart/form-data" acceptCharset="utf-8">
	<h4 class="widgettitle">Upload File số thu</h4>
	<div class="widgetcontent">
		<div class="section">
		
			<h6 class="require">File có định dạng <b>XLSX</b>, Số lượng tối đa <b>10.000</b> dòng cho 1 lượt upload. Download template mẫu theo link dưới 
			</h6>
			<div class="form-group">
				<div class="col-sm-3">
					<b>File Mẫu</b>
				</div>
				<div class="col-sm-3">
					<a href="<c:url value="/resources/download/Template-TaiPhanBo.xlsx" />">Download This File</a>
				</div>
			</div>
		
			<hr/>
			<div class="form-group required">
				<label class="control-label col-sm-2">File upload:</label>
				<div class="col-sm-3">
					<form:input path="file1" id="FileDinhKem1" style="width: 100%" type="file" value="Chọn tệp..."/>
				</div>
			</div>

			<div class="buttons">
				<button type="submit" class="btn btn-primary btnUpload" value="upload"><i class="glyphicon glyphicon-cloud-upload"></i> Upload File</button>
			</div>
			
			<br/><br/>
			<div class="section">
				<table id="DataTables_Reallocation" class="table table-striped table-bordered">
					<thead>
						<tr role="row">
							<th>Ngày upload (yyyy-MM-dd)</th>
							<th>Người tạo</th>
							<th>Tên file</th>
							<th>Tổng số dòng</th>
							<th>Số dòng thành công</th>
							<th>Số dòng thất bại</th>
							<th>Mô tả</th>
							<th>Xuất D/s thất bại</th>
						</tr>
					</thead>
					<tbody aria-relevant="all" aria-live="polite">
						<c:forEach var="var" items="${listDebtUploadHdr}" varStatus="status">
							<tr>
								<td>${var.createDate}</td>
								<td>${var.createBy}</td>
								<td>${var.fileName}</td>
								<td>${var.fileRowTotal}</td>
								<td>${var.fileRowSuccess}</td>
								<td>${var.fileRowFail}</td>
								<td>${var.errorMsg}</td>
								<td>
									<c:if test="${(var.fileRowFail != null) && (var.fileRowFail > 0)}">
										<a class="btn btn-default btnExportFail" title="Export" id="${var.id}"><i class="glyphicon glyphicon-download-alt"></i></a>
									</c:if> 
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
	<form:hidden path="hidAction" id="hidAction"/>
</form:form>
<script>
$(function() {

	$('.btnUpload').on('click', function(){
		$('#hidAction').val(this.value);
		if($('#FileDinhKem1').val().length == '0'){
			alertError("Bạn chưa chọn file để upload");
			return false;
		}
	});
	
	$("#DataTables_Reallocation").dataTable({
		"language" : DATA_TABLE_LANG_VI,
		"iDisplayLength": 100,
		"oSearch": {"sSearch": ""}
	});
	
	$(document).on("click", ".btnExportFail", function(e) {
		var id = $(this).attr("id");
		var url = "<c:url value="/admin/uploadSoThu/resultFail/"/>" + id;
		
		//window.location.href = url;
		HoldOn.open({ theme:"sk-cube-grid",message: 'Do dung lượng file quá lớn, Bạn vui lòng đợi trong giây lát' })
		// Use XMLHttpRequest instead of Jquery $ajax
		xhttp = new XMLHttpRequest();
		xhttp.onreadystatechange = function() {
		    var a;
		    if (xhttp.readyState === 4 && xhttp.status === 200) {
		        // Trick for making downloadable link
		        a = document.createElement('a');
		        a.href = window.URL.createObjectURL(xhttp.response);
		        // Give filename you wish to download
		        a.download = "Template-Fail.xlsx";
		        a.style.display = 'none';
		        document.body.appendChild(a);
		        a.click();
		        HoldOn.close();
		    }
		};
		// Post data to URL which handles post request
		xhttp.open("GET", url);
		xhttp.setRequestHeader("Content-Type", "application/json");
		xhttp.responseType = 'blob';
		xhttp.send();
	});
})
</script>
