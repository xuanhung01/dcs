<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<c:if test="${message != null}">
	<div class="alert-success fade in">
		<c:choose>
			<c:when test="${param.message == 'AddUserSuccess'}">
					Tạo mới người dùng thành công!
				</c:when>
			<c:when test="${param.message == 'EditUserSuccess'}">
					Chỉnh sửa thông tin người dùng thành công!
				</c:when>
		</c:choose>
	</div>
</c:if>
<c:choose>
	<c:when test="${act==null || act=='list'}">
		<jsp:include page="user/list.jsp"></jsp:include>
	</c:when>

	<c:when test="${ act=='create'}">
		<div>
			<jsp:include page="user/create.jsp"></jsp:include>
		</div>
	</c:when>
	<c:when test="${ act=='view'}">
		<div>
			<jsp:include page="user/view.jsp"></jsp:include>
		</div>
	</c:when>
	<c:when test="${ act=='edit'}">
		<div>
			<jsp:include page="user/edit.jsp"></jsp:include>
		</div>
	</c:when>
	<c:when test="${ act=='reset'}">
		<div>
			<jsp:include page="user/reset.jsp"></jsp:include>
		</div>
	</c:when>
</c:choose>