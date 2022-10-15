<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%-- <%@ taglib uri="botDetect" prefix="botDetect"%> --%>

<div style="padding: 30px">
	<h4>Yêu cầu cấp lại mật khẩu</h4>
	<c:choose>
		<c:when test="${subpage == 'forgetPassword'}">
			<div>
				<p>Yêu cầu cấp lại mật khẩu sẽ được gửi về email đã đăng ký của tài khoản được cấp, làm theo hướng dẫn trong email để cấp lại mật khẩu.</p>
			</div>
			<c:if test="${listErrors !=null}">
				<div class="section">
					<h4>Lỗi:</h4>
					<c:forEach var="row" items="${listErrors}" varStatus="statusItem">
						<div class="error">
							<c:choose>
								<c:when test="${row.objectName == 'UserAlreadyExist' }">Người dùng hoặc email không chính xác</c:when>
								<c:when test="${row.objectName == 'CaptchaCode' }">Mã bảo vệ không chính xác!</c:when>
								<c:otherwise>Có lỗi xảy ra. Yêu cầu kiểm tra lại thông tin! <p>${row.defaultMessage}</p>
								</c:otherwise>
							</c:choose>
						</div>
					</c:forEach>
				</div>
			</c:if>
			<div>
				<form:form class="form-horizontal" id="formResetPassword" modelAttribute="formModel" method="post">
					<div class="form-group required">
						<label class="control-label col-sm-2" for="userName">Tên tài khoản:</label>
						<div class="col-sm-10">
							<form:input path="userName" id="userName" style="width: 50%;" type="text" required="true" data-rule-minlength="6" data-rule-maxlength="40" />
						</div>
					</div>
					<div class="form-group required">
						<label class="control-label col-sm-2" for="email">Email:</label>
						<div class="col-sm-10">
							<form:input path="email" id="email" style="width: 50%;" type="email" required="true" data-rule-email="true" />
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-3">Mã bảo vệ:</label>
						<div class="col-sm-9">
							<!-- <botDetect:captcha id="springFormCaptcha" codeLength="5" imageWidth="150" imageStyles="graffiti, graffiti2" /> -->
							<form:input id="userCaptchaCode" type="text" path="userCaptchaCode" data-rule-required="true" placeholder="Mã bảo vệ" />
						</div>
					</div>
					<div class="buttons">
						<div class="control-label col-sm-2"></div>
						<div class="col-sm-10">
							<button type="submit" id="Gui" class="btn btn-primary">
								<i class="icon-envelope"></i> Gửi
							</button>
						</div>
					</div>
				</form:form>
			</div>
			<script>
				$(document).ready(function() {
					$("#formResetPassword").validate({
						errorClass : "valError",
						errorElement : "span",
						ignore : 'input[type=hidden]'
					});
				});
			</script>
		</c:when>
	</c:choose>
	<c:choose>
		<c:when test="${subpage == 'resetPasswordSent'}">
			<div>
				<p>
					Email đã được gửi về địa chỉ <a>${formModel.email}</a> .
				</p>
				<p>Xin mời làm theo hướng dẫn trong email để cấp lại mật khẩu.</p>
			</div>
		</c:when>
	</c:choose>
</div>

