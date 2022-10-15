<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<spring:url value="/system/role/add" var="addFormSubmitUrl" />

<form:form modelAttribute="formModel" id="editProfileForm" class="form-horizontal" formMode="create" action="${addFormSubmitUrl}" method="POST">
	<h4 class="widgettitle">Tạo mới role</h4>
	<div class="widgetcontent">
		<c:if test="${formMode == 'create' || formMode == 'edit' }">
			<div id="SendDialog" title="Xác nhận tạo mới nhóm">
				<p>
					<span class="ui-icon ui-icon-alert" style="float: left; margin: 0 7px 20px 0;"></span>Bạn có thực sự muốn tạo mới nhóm?
				</p>
			</div>
		</c:if>
		<div>
			<c:if test="${listErrors !=null}">
				<div class="section">
					<h4>Lỗi:</h4>
					<c:forEach var="row" items="${listErrors}" varStatus="statusItem">
						<div class="error">
							<c:choose>
								<c:when test="${row.objectName == 'UserAlreadyExist' }">Id đã tồn tại. Mời chọn id khác</c:when>
								<c:when test="${row.objectName == 'DataError' }">Lỗi dữ liệu ${row.defaultMessage}</c:when>
								<c:otherwise>Có lỗi xảy ra. Yêu cầu kiểm tra lại thông tin! <br>
									<p>${row.defaultMessage}</p>
								</c:otherwise>
							</c:choose>
						</div>
					</c:forEach>
				</div>
			</c:if>
			<h4>Thông tin role</h4>
			<div class="form-group required">
				<label class="control-label col-sm-3">Tên role:</label>
				<div class="col-sm-9">
					<form:input path="name" id="name" type="text" style="width: 50%;" required="true"/>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-3">Được dùng Mobile:</label>
				<div class="col-sm-9">
					<form:select path="hasMobile" id="hasMobile" style="width: 100%;" cssClass="selectbox">
						<form:option value="0" label="Không dùng"></form:option>
						<form:option value="1" label="Có dùng" ></form:option>
					</form:select>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-3">Được dùng WEB DCS:</label>
				<div class="col-sm-9">
					<form:select path="hasDcs" id="hasDcs" style="width: 100%;" cssClass="selectbox">
						<form:option value="0" label="Không dùng"></form:option>
						<form:option value="1" label="Có dùng" ></form:option>
					</form:select>
				</div>
			</div>
		</div>

		<div class="buttons">
			<button type="submit" class="btn btn-primary">Thêm</button>
			<a href='<c:url value="/system/role/load" />' class="btn btn-default"><i class="glyphicon glyphicon-step-backward"></i>Quay lại</a>
		</div>
	</div>
</form:form>
<script src="<c:url value="/resources/js/pages/forms/register.js" />"></script>
<script>
$(function() {
	$('.selectbox').select2({
		language : "vi"
	}).on("select2-close", function(e) {
		$(this).valid();
	});
})
</script>
