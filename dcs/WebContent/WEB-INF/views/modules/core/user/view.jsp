<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<spring:url value="/system/user/add" var="addFormSubmitUrl" />

<form:form modelAttribute="formModel" id="editProfileForm" class="form-horizontal" formMode="view" action="${addFormSubmitUrl}" method="post">
	<h4 class="widgettitle">Xem thông tin người dùng</h4>
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
			<h4>Thông tin người dùng</h4>
			<div class="form-group">
				<label class="control-label col-sm-3">Tên tài khoản:</label>
				<div class="col-sm-9">
					<form:input path="userName" id="userName" type="text" style="width: 50%;" required="true"/>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-3">Họ và tên người dùng:</label>
				<div class="col-sm-9">
					<form:input path="realName" id="realName" type="text" style="width: 50%;" required="true" />
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-3">Group Code:</label>
				<div class="col-sm-9">
					<form:input path="groupCode" id="groupCode" type="text" style="width: 50%;" required="true" />	
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-3">Cấp trên:</label>
				<div class="col-sm-9">
					<form:input path="parentUserName" id="parentUserName" type="text" style="width: 50%;" required="true" />	
				</div>
			</div>
		</div>

		<div class="buttons"></div>
	</div>
</form:form>
<script >

//select userName
$(function() {
	$('#groupId').select2({
		placeholder : "Lựa chọn",
		language : "vi"
	}).on("select2-close", function(e) {
		$(this).valid();
	});
})
</script>
