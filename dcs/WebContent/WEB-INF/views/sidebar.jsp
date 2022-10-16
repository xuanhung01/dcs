<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<nav class="navbar" style="font-weight:bold; font-size:14px;min-height: 40px">
	<ul class="nav nav-pills" >
		<!-- Menu trái  -->
		<c:forEach items="${listMenuLeftParent}" var="itemP">
			<c:if test="${itemP == 1010}">
				<li class="dropdown ${menuActive == 'system' ? 'active' : ''}">
		            <a data-toggle="dropdown" href="#"><span>Quản lý hệ thống <b class="caret"></b> </span></a>
		            <ul class="dropdown-menu">
		            	<c:forEach items="${listMenuLeftChild}" var="itemC">
							<c:if test="${itemC == 1011}">
								<li><a id='submenu_1011' class="submenu" href="<c:url value="/system/user/load" />">Q/L người dùng</a></li>
							</c:if>
							<c:if test="${itemC == 1012}">
								<li><a id='submenu_1012' class="submenu" href="<c:url value="/system/role/load" />">Q/L nhóm</a></li>
							</c:if>
							<c:if test="${itemC == 1014}">
								<li><a id='submenu_1014' class="submenu" href="<c:url value="/system/userRole/load" />">Q/L phân User - Role</a></li>
							</c:if>
							<c:if test="${itemC == 1015}">
								<li><a id='submenu_1015' class="submenu" href="<c:url value="/system/rolePrivilege/load" />">Q/L phân Role - Privilege</a></li>
							</c:if>
							<c:if test="${itemC == 1016}">
								<li><a id='submenu_1016' class="submenu" href="<c:url value="/system/userUpload/load" />">Upload lô User</a></li>
							</c:if>
							<c:if test="${itemC == 1017}">
								<li><a id='submenu_1017' class="submenu" href="<c:url value="/system/password/load" />">Cài đặt mật khẩu</a></li>
							</c:if>
						</c:forEach>
		               
		            </ul>
		        </li>
		    </c:if>
	    </c:forEach>
		<li class="menuLogout" >
			 <a onclick="logout();" href="#">(<i>${operatorCode}</i>) &nbsp; <span style="color: black;">LOGOUT</span></a>
		</li>			
	</ul>
</nav>

<form name="logoutForm" method="post"
			action="<c:url value="/j_spring_security_logout" />"
			style="display: none">
			
</form>
<style>
.menuLogout {
	float : right !important;
	margin-right: 0px;
}
.dropdown-menu{
	background-color: #ededed;
}
</style>
<script>
function logout() {
	sessionStorage.submenu = null;
	document.logoutForm.submit();
}
</script>