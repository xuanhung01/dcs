<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<h4 class="widgettitle">
	Danh sách người dùng
</h4>
<div class="widgetcontent">
	<hr>
	<div>
		<c:if test="${message != null}">
			<div class="alert-success fade in">
				<c:choose>
					<c:when test="${param.message == 'RemoveSuccess'}">
						Tài khoản đã xoá thành công!
					</c:when>
					<c:when test="${param.message == 'ResetPasswordSuccess'}">
						Mật khẩu tài khoản đã được thiết lập lại và gửi email đến cho người dùng!
					</c:when>
				</c:choose>
			</div>
		</c:if>
		<c:if test="${listErrors !=null}">
			<div class="section">
				<h4>Lỗi:</h4>
				<c:forEach var="row" items="${listErrors}" varStatus="statusItem">
					<div class="error">
						<c:choose>
							<c:when test="${row.objectName == 'UserAlreadyExist' }">Người dùng hoặc email đã tồn tại. Mời chọn tên khác</c:when>
							<c:when test="${row.objectName == 'DataError' }">Lỗi dữ liệu ${row.defaultMessage}</c:when>
							<c:otherwise>Có lỗi xảy ra. Yêu cầu kiểm tra lại thông tin! <br>
								<p>${row.defaultMessage}</p>
							</c:otherwise>
						</c:choose>
					</div>
				</c:forEach>
			</div>
		</c:if>
	</div>
	<div>
		<a href='<c:url value="/system/user/add" />' class="btn btn-default"><i class="glyphicon glyphicon-plus"></i>Tạo mới</a>
	</div>
	<hr>
	<table id="DataTables_Users" class="table table-striped table-bordered">
		<thead>
			<tr role="row">
				<th>Thứ tự</th>
				<th>USERNAME</th>
				<th >Tên</th>
				<th>Role</th>
				<th>Group Code</th>
				<th>Cấp trên</th>
				<th>EXT</th>
				<th>Trạng thái</th>
				<th>Thao tác</th>
			</tr>
		</thead>
	
		<tbody aria-relevant="all" aria-live="polite">
			<c:forEach var="user" items="${userList}" varStatus="status">
				<tr>
					<td></td>
					<td><a class="btnView" style="cursor: pointer; <c:if test="${user.username == username}">color:red</c:if>" title="Xem chi tiết" username="${user.username}" >${user.username}</a></td>
					<td>${user.realname}</td>
					<td>
						<c:forEach var="role" items="${user.roles}" varStatus="status">
							${role.name}
						</c:forEach>
					</td>
					<td>
						<c:forEach var="group" items="${groupList}" varStatus="status">
							<c:if test="${user.groupId == group.id}">
								${group.groupCode}
							</c:if>
						</c:forEach>
					</td>
					<td>${user.parentUsername}</td>
					<td>${user.ext}</td>
					<td>
						<c:if test="${user.enabled}">
							<i class=" glyphicon glyphicon-ok" title="Enable"></i>
						</c:if>
					</td>
					<td width="17%">
						<a class="btn btn-default btnSua" title="Sửa" username="${user.username}"><i class=" glyphicon glyphicon-pencil"></i></a> 
						<a class="btn btn-default btnXoa" title="Xoá" username="${user.username}"><i class=" glyphicon glyphicon-remove"></i></a>
						<a class="btn btn-default btnStatus" title="Enable/Disable" username="${user.username}"><i class=" glyphicon glyphicon-ban-circle"></i></a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>
<hr>
<script>
	$(function() {
		
		$('.btnXoa').bootstrap_confirm_delete({
			  heading: 'Remove User',
			  message: 'Bạn có thực sự muốn xóa bản ghi?',
			  data_type: $(this).attr("username"),
			  callback: function( event ){
				  var id = event.data.originalObject.attr("username");
				  var url = "<c:url value="/system/user/remove/"/>" + id;
				  window.location.href = url;
			  }
		});
		
		$('.btnStatus').bootstrap_confirm_delete({
			  heading: 'Active/Inactive User',
			  message: 'Bạn có thực sự muốn Active/Inactive người dùng?',
			  data_type: $(this).attr("username"),
			  callback: function( event ){
				  var id = event.data.originalObject.attr("username");
				  var url = "<c:url value="/system/user/active/"/>" + id;
				  window.location.href = url;
			  }
		});
		
		$("#DataTables_Users").dataTable({
			"language" : DATA_TABLE_LANG_VI,
			"iDisplayLength": 100, 
			fixedHeader: {
	            header: true,
	            headerOffset: 0
	        },
			"columnDefs": [ {
	            "searchable": false,
	            "orderable": false,
	            "targets": 0,
	            'render': function (data, type, full, meta){
	            	return  meta.row + 1;
		         }
	        }]
		});
		
		var table = $('#DataTables_Users').DataTable();
		table.on( 'order.dt search.dt', function () {
			table.column(0, {search:'applied', order:'applied'}).nodes().each( function (cell, i) {
		            cell.innerHTML = i+1;
	        } );
	    }).draw();
		
		$(document).on("click", ".btnSua", function(e) {
			e.preventDefault();
			var id = $(this).attr("username");
			var url = "<c:url value="/system/user/edit/"/>" + id;
			window.location.href = url;
		});
		$(document).on("click", ".btnView", function(e) {
			e.preventDefault();
			var id = $(this).attr("username");
			var url = "<c:url value="/system/user/view/"/>" + id;
			window.location.href = url;
		});
		
	});
</script>
