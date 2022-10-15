<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<nav class="navbar navbar-inverse navbar-fixed-top" id="menubar" style="border-bottom: 2px solid #F58220;">
	<div class="container-fluid">
		<jsp:include page="sidebar.jsp" />
		<div class="navbar-header">
			<a class="navbar-brand" href="#"><img src="<c:url value="/resources/images/icon1.png" />" alt="SHB Finance"> </a>
		</div>
		<div class="nav navbar-nav text-primary" style="left: 40%;position: absolute;margin-top: 0px">
			<h2 style="color:#4c4a99"><b>Debt Collection System</b></h2>
		</div>
		<div class="nav navbar-nav pull-right text-primary">
			<c:choose>
				<c:when test="${operatorCode != null}">
					<div class="user">
						<table class="table table-condensed" style="color:#4c4a99;font-size: 12px; margin-bottom: 0px;">
							<tr>
								<td>Thời gian đăng nhập</td>
								<td>&nbsp;</td>
								<td><b>${dateTimeLogin}</b></td>
							</tr>
							<tr>
								<td>Mã nhân viên</td>
								<td>&nbsp;</td>
								<td id="operatorCode"><b>${operatorCode}</b></td>
							</tr>
							<tr>
								<td>Tên nhân viên</td>
								<td>&nbsp;</td>
								<td><b>${operatorName}</b></td>
							</tr>
						</table>
						
					</div>
				</c:when>
			</c:choose>
		</div>
	</div>
</nav>