<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>


<spring:url value="/admin/config" var="systemConfigSubmitUrl" />

<h3>Tham số hệ thống Mạng</h3>
<hr>
<form:form id="Form_Config" class="form-horizontal" modelAttribute="formModel" action="${systemConfigSubmitUrl}" method="POST" acceptCharset="utf-8" enctype="multipart/form-data">
	<h4>Danh sách IP bị cấm</h4>
	<table id="DataTables_Table" class="table table-striped table-bordered bootstrap-datatable datatable">
		<thead>
			<tr role="row">
				<th style="width: 300px;" role="columnheader" class="sorting_asc">Tên</th>
				<th style="width: 223px;" tabindex="0" role="columnheader" class="sorting">Giá trị</th>
				<th style="width: 125px;" tabindex="0" role="columnheader" class="sorting">Ghi chú</th>
			</tr>
		</thead>

		<tbody>
			<c:forEach var="row" items="${formModel.listThamSo}" varStatus="statusItem">
				<tr class="odd">
					<td class="sorting_1">${row.ten}</td>
					<td class="center "><input type="text" value="${row.giaTri}" name="listThamSo[${statusItem}].giaTri" required="true" /></td>
					<td class="center "><input type="text" value="${row.ghiChu}" name="listThamSo[${statusItem}].ghiChu" /></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</form:form>

<script>
	$(function() {
		$("#DataTables_Table").dataTable({
			"language" : DATA_TABLE_LANG_VI,
			"bSort" : false
		});
	});
</script>