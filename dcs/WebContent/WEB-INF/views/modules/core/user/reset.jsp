<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<spring:url value="/system/password/save" var="addFormSubmitUrl" />

<form:form modelAttribute="formModel" id="registerForm" class="form-horizontal" formMode="create"  action="${addFormSubmitUrl}" method="post">
	<h4 class="widgettitle">Cập nhật Mật khẩu</h4>
	<div class="widgetcontent">
		<div>
			<div class="alert alert-danger" id="alertshowErrorMsg" style="display:none;"></div>
			<h4>Thông tin người dùng</h4>
			<div class="form-group">
				<label class="control-label col-sm-2">Tên tài khoản:</label>
				<div class="col-sm-5">
					<form:input path="username" id="username" type="text" cssClass="form-control" readonly="true"/>
				</div>
			</div>
			<div class="form-group required">
				<label class="control-label col-sm-2">Mật khẩu:</label>
				<div class="col-sm-5">
					<form:input path="password" id="password" type="password" data-toggle="password" cssClass="form-control pwd" maxlength="100" data-rule-minlength="6"/>
				</div>
				<div class="col-sm-1">
					<span class="input-group-btn">
			        	<button class="btn btn-default reveal" type="button"><i class="glyphicon glyphicon-eye-open"></i></button>
			        </span>
				</div>
			</div>
			<div class="form-group required">
				<label class="control-label col-sm-2">Nhập lại mật khẩu:</label>
				<div class="col-sm-5">
					<form:input path="passwordConfirm" id="passwordConfirm" data-toggle="password" cssClass="form-control pwd" type="password" maxlength="100" data-rule-equalTo="#password"/>
				</div>
			</div>
		</div>

		<div class="buttons">
			<button type="submit" class="btn btn-primary">Cập nhật</button>
		</div>
	</div>
</form:form>
<script src="<c:url value="/resources/js/pages/forms/register.js" />"></script>
<script>
	$(function() {
		// check error
		var listErrors = "${listErrors}";
		if(!is_empty(listErrors)){
			<c:forEach var="error" items="${listErrors}" varStatus="status">
				alertError("${error.defaultMessage}"); 
			</c:forEach>
		}
		// check infor
		var message = "${message}";
		if(!is_empty(message)){
			alertSuccess("Bạn đã tạo mới người dùng thành công!");
		}
	})
</script>