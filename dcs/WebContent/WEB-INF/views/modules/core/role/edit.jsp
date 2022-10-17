<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<spring:url value="/system/role/edit" var="editFormSubmitUrl" />

<form:form modelAttribute="formModel" id="editProfileForm" class="form-horizontal" formMode="edit" action="${editFormSubmitUrl}" method="POST">
	<h4 class="widgettitle">Chỉnh sửa thông tin role</h4>
	<div class="widgetcontent">
		<div>
			<c:if test="${formMode == 'create' || formMode == 'edit' }">
				<div id="SendDialog" title="Xác nhận chỉnh sửa nhóm">
					<p>
						<span class="ui-icon ui-icon-alert" style="float: left; margin: 0 7px 20px 0;"></span>Bạn có thực sự muốn chỉnh sửa nhóm?
					</p>
				</div>
			</c:if>
			<c:if test="${listErrors !=null}">
				<div class="section">
					<h4>Lỗi:</h4>
					<c:forEach var="row" items="${listErrors}" varStatus="statusItem">
						<div class="error">
							<c:choose>
								<c:when test="${row.objectName == 'UserAlreadyExist' }">Role đã tồn tại. Mời chọn tên khác</c:when>
								<c:otherwise>Có lỗi xảy ra. Yêu cầu kiểm tra lại thông tin! <br>
									<p>${row.objectName}${row.defaultMessage}</p>
									<p>${row.toString()}</p>
								</c:otherwise>
							</c:choose>
						</div>
					</c:forEach>
				</div>
			</c:if>
			<h4>Thông tin role</h4>
			<div class="form-group">
				<label class="control-label col-sm-3">Mã role:</label>
				<div class="col-sm-6">
					<form:input path="id" id="id" type="text" style="width: 30%;" readonly="true" disabled="true"/>
				</div>
			</div>
			<div class="form-group required">
				<label class="control-label col-sm-3">Tên role:</label>
				<div class="col-sm-9">
					<form:input path="name" id="name" type="text" style="width: 50%;" required="true" />
				</div>
			</div>
		</div>

		<div class="buttons">
			<button type="submit" class="btn btn-primary">Cập nhật</button>
			<a href='<c:url value="/system/role/load" />' class="btn btn-default"><i class="glyphicon glyphicon-step-backward"></i>Quay lại</a>
		</div>
	</div>
	
	<!-- Hidden -->
	<form:hidden path="idHidden" />
</form:form>
<script src="<c:url value="/resources/js/pages/forms/register.js" />">

</script>
<script>
$(function() {
	$('.selectbox').select2({
		language : "vi"
	}).on("select2-close", function(e) {
		$(this).valid();
	});
})
</script>
