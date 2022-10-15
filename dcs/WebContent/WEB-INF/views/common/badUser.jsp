<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<div class="form-contact">
	<c:if test="${message != null}">
		<div class="alert-success fade in">
			<c:choose>
				<c:when test="${param.message == 'expired' || message == 'expired'}">
					<h4>Mã kích hoạt đã hết hạn. Hãy gửi yêu cầu đến ban quản trị để được được giải đáp.</h4>
				</c:when>
				<c:when test="${param.message== 'invalidToken' || message == 'invalidToken'}">
					<h4>Mã kích hoạt không hợp lệ. Hãy gửi yêu cầu đến ban quản trị để được được giải đáp.</h4>
				</c:when>
			</c:choose>
		</div>
	</c:if>


</div>

