<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="false"%>


<jsp:include page="front-header.jsp" />
<c:choose>
	<c:when test="${subpage == 'login'}">
		<jsp:include page="login.jsp" />
	</c:when>
</c:choose>
<c:choose>
	<c:when test="${subpage == 'badUser'}">
		<jsp:include page="badUser.jsp" />
	</c:when>
</c:choose>
<c:choose>
	<c:when test="${subpage == 'expiredAccount'}">
		<jsp:include page="expiredAccount.jsp" />
	</c:when>
</c:choose>
<c:choose>
	<c:when test="${subpage == 'expiredPassword'}">
		<jsp:include page="expiredPassword.jsp" />
	</c:when>
</c:choose>
<c:choose>
	<c:when test="${subpage == 'forgetPassword'}">
		<jsp:include page="forgetPassword.jsp" />
	</c:when>
</c:choose>
<c:choose>
	<c:when test="${subpage == 'resetPasswordSent'}">
		<jsp:include page="forgetPassword.jsp" />
	</c:when>
</c:choose>
<c:choose>
	<c:when test="${subpage == 'activate'}">
		<jsp:include page="activate.jsp" />
	</c:when>
</c:choose>
<!-- /container -->
<br style="clear: both" />
<jsp:include page="front-footer.jsp" />