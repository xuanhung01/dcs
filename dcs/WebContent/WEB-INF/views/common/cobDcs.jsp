<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="false"%>


<jsp:include page="front-header.jsp" />
<div style="height: 200px">
	<h2 style="text-align: center">
		<span class="glyphicon glyphicon glyphicon-fire"></span> Xóa phiên làm việc của tài khoản
	</h2>
	<h5 style="text-align: center">
		Hệ thống DCS đang khóa ngày. Xin vui trở lại sau 7h sáng, hoặc có thông báo từ IT <a href="<c:url value="/" />">Trang chủ</a> <a href="<c:url value="/" />">Trang chủ</a>
	</h5>
</div>

<br style="clear: both" />
<jsp:include page="front-footer.jsp" />