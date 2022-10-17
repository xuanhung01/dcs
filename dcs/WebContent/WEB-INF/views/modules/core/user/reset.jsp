<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<spring:url value="/system/password/save" var="addFormSubmitUrl" />

<form:form modelAttribute="formModel" id="registerForm" class="form-horizontal" formMode="create"  action="${addFormSubmitUrl}" method="post">
	<h4 class="widgettitle">Cập nhật Mật khẩu</h4>
	<div class="widgetcontent">
		<div>
			<c:if test="${listErrors !=null}">
				<div class="section">
					<h4>Lỗi:</h4>
					<c:forEach var="row" items="${listErrors}" varStatus="statusItem">
						<div class="error">
							<c:choose>
								<c:when test="${row.defaultMessage == 'UserAlreadyExist' }">Người dùng hoặc email đã tồn tại. Mời chọn tên khác</c:when>
								<c:otherwise>Có lỗi xảy ra. Yêu cầu kiểm tra lại thông tin!</c:otherwise>
							</c:choose>
						</div>
					</c:forEach>
				</div>
			</c:if>
			<c:if test="${message != null}">
				<div class="alert-success fade in">
					<h4>Mật khẩu đã được cập nhật thành công.</h4>
				</div>
			</c:if>
			<div class="alert alert-danger" id="alertshowErrorMsg" style="display:none;"></div>
			<h4>Thông tin người dùng</h4>
			<div class="form-group">
				<label class="control-label col-sm-3">Tên tài khoản:</label>
				<div class="col-sm-9">
					<form:input path="username" id="username" type="text" style="width: 50%;" readonly="true"/>
				</div>
			</div>
			<div class="form-group required">
				<label class="control-label col-sm-3">Mật khẩu:</label>
				<div class="col-sm-9">
					<form:input path="password" name="password" id="password" type="password" style="width: 50%;" required="true" data-rule-minlength="6"/>
				</div>
			</div>
			<div class="form-group required">
				<label class="control-label col-sm-3">Nhập lại mật khẩu:</label>
				<div class="col-sm-9">
					<form:input path="passwordConfirm" name="passwordConfirm" id="passwordConfirm" type="password" style="width: 50%;" required="true" data-rule-equalTo="#password"/>
				</div>
			</div>
		</div>

		<div class="buttons">
			<button type="submit" class="btn btn-primary">Cập nhật</button>
		</div>
	</div>
</form:form>
<script src="<c:url value="/resources/js/pages/forms/register.js" />"></script>