<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="false"%>


<jsp:include page="front-header.jsp" />
<div style="height: 300px">
	<h2 style="text-align: center">
		<span class="glyphicon glyphicon-warning-sign"></span> Lỗi ứng dụng DCS!
	</h2>
	<h5 style="text-align: center">
		Bạn gặp lỗi <b>${errorMsg}</b>.
		<br/> 
		Xin vui lòng liên hệ IT và trở lại <a href="<c:url value="/" />">Đăng nhập</a>
	</h5>
</div>
<br style="clear: both" />
<jsp:include page="front-footer.jsp" />
