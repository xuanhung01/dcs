<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<h4 class="widgettitle">
	Danh sách role
</h4>
<div class="widgetcontent">
	<hr>
	<div>
		<c:if test="${message != null}">
			<div class="alert-success fade in">
				<c:choose>
					<c:when test="${param.message == 'RemoveSuccess'}">
						Nhóm đã xoá thành công!
					</c:when>
				</c:choose>
			</div>
		</c:if>
	</div>

	<div>
		<a href='<c:url value="/system/role/add" />' class="btn btn-default"><i class="glyphicon glyphicon-plus"></i>Tạo mới</a>
	</div>
	<hr>
	<table id="DataTables_Roles" class="table table-striped table-bordered">
		<thead>
			<tr role="row">
				<th>Mã role</th>
				<th>Tên role</th>
				<th style="width: 150px">Thao tác</th>
			</tr>
		</thead>
	
		<tbody aria-relevant="all" aria-live="polite">
			<c:forEach var="role" items="${listRole}" varStatus="status">
				<tr>
					<td><a class="btnView" style="cursor: pointer;" title="Xem chi tiết" id="${role.id}" >${role.id}</a></td>
					<td>${role.name}</td>
					<td style="width: 150px"><a class="btn btn-default btnSua" title="Sửa" id="${role.id}"><i class=" glyphicon glyphicon-pencil"></i></a> <a class="btn btn-default btnXoa"
						title="Xoá" id="${role.id}"><i class=" glyphicon glyphicon-remove"></i></a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>
<hr>
<script>
	$(function() {
		$("#DataTables_Roles").dataTable({
			"language" : DATA_TABLE_LANG_VI,
			"iDisplayLength": 100, 
			fixedHeader: {
	            header: true,
	            headerOffset: 0
	        }
		});
		$(document).on("click", ".btnSua", function(e) {
			e.preventDefault();
			var id = $(this).attr("id");
			var url = "<c:url value="/system/role/edit/"/>" + id;
			window.location.href = url;
		});
		$(document).on("click", ".btnView", function(e) {
			e.preventDefault();
			var id = $(this).attr("id");
			var url = "<c:url value="/system/role/view/"/>" + id;
			window.location.href = url;
		});
		$(document).on("click", ".btnXoa", function(e) {
			e.preventDefault();
			var id = $(this).attr("id");
			var url = "<c:url value="/system/role/remove/"/>" + id;
			window.location.href = url;
		});
	});
</script>
