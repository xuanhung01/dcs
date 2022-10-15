<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ page session="false"%>


<jsp:include page="front-header.jsp" />
<div style="height: 200px">
	<h2 style="text-align: center">
		<span class="glyphicon glyphicon-warning-sign"></span> Dịch vụ bị gián đoạn
	</h2>
	<h5 style="text-align: center">
		Dịch vụ bị gián đoạn. Xin vui lòng thử lại sau hoặc trở lại <a href="<c:url value="/" />">Trang chủ</a>
	</h5>
	<div class="error">${exceptionMessage }</div>
</div>

<br style="clear: both" />
<jsp:include page="front-footer.jsp" />
