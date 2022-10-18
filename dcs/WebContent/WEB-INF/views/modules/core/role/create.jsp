<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<spring:url value="/system/role/add" var="addFormSubmitUrl" />

<form:form modelAttribute="formModel" id="editProfileForm" class="form-horizontal" formMode="create" action="${addFormSubmitUrl}" method="POST">
	<h4 class="widgettitle">Tạo mới role</h4>
	<div class="widgetcontent">
		<div>
			<h4>Thông tin role</h4>
			<div class="form-group required">
				<label class="control-label col-sm-2">Tên role:</label>
				<div class="col-sm-5">
					<form:input path="name" id="name" type="text" cssClass="form-control" style="width: 50%;" required="true"/>
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
