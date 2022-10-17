<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<nav class="navbar" style="font-weight:bold; font-size:14px;min-height: 40px">
	<ul class="nav nav-pills" >
		<!-- Menu trái  -->
		<c:forEach items="${listMenuLeftParent}" var="itemP">
			<c:if test="${itemP == 1020}">
				<li class="dropdown ${menuActive == 'outbound' ? 'active' : ''}">
		            <a data-toggle="dropdown" href="#"><span style="color: black;">OutBound <b class="caret"></b> </span></a>
		            <ul class="dropdown-menu">
		            	<c:forEach items="${listMenuLeftChild}" var="itemC">
							<c:if test="${itemC == 1021}">
								<li><a id='submenu_1021' class="submenu" href="<c:url value="/outbound/welcomeCall/load" />">Welcome Call</a></li>
							</c:if>
						</c:forEach>
						<c:forEach items="${listMenuLeftChild}" var="itemC">
							<c:if test="${itemC == 1022}">
								<li><a id='submenu_1022' class="submenu" href="<c:url value="/outbound/pending/load"/>">Hồ sơ pending</a></li>
							</c:if>
						</c:forEach>
						<c:forEach items="${listMenuLeftChild}" var="itemC">
							<c:if test="${itemC == 1023}">
								<li><a id='submenu_1023' class="submenu" href="<c:url value="/outbound/managerCall/load"/>">Quản lý cuộc gọi</a></li>
							</c:if>
						</c:forEach>
						<c:forEach items="${listMenuLeftChild}" var="itemC">
							<c:if test="${itemC == 1024}">
								<li><a id='submenu_1024' class="submenu" href="<c:url value="/outbound/managerCampaign/load"/>">Quản lý chiến dịch cuộc gọi</a></li>
							</c:if>
						</c:forEach>
		            </ul>
		        </li>
		    </c:if>
		    <c:if test="${itemP == 1030}">
				<li class="dropdown ${menuActive == 'customer360' ? 'active' : ''}">
		            <a href="<c:url value="/customer360/inbound/load" />"><span style="color: black;">Customer360</span></a>
		            <%-- <ul class="dropdown-menu">
		            	<c:forEach items="${listMenuLeftChild}" var="itemC">
							<c:if test="${itemC == 1031}">
								<li><a id='submenu_1031' class="submenu" href="">Tra cứu thông tin</a></li>
							</c:if>
						</c:forEach>
		            </ul> --%>
		        </li>
		    </c:if>
		    <c:if test="${itemP == 1040}">
				<li class="dropdown ${menuActive == 'search' ? 'active' : ''}">
		            <a data-toggle="dropdown" href="#"><span style="color: black;">Tra cứu <b class="caret"></b> </i> </span></a>
		            <ul class="dropdown-menu">
		            	<c:forEach items="${listMenuLeftChild}" var="itemC">
							<c:if test="${itemC == 1041}">
								<li><a id='submenu_1041' class="submenu" href="<c:url value="/search/detailCall/load" />">Chi tiết cuộc gọi</a></li>
							</c:if>
						</c:forEach>
		            </ul>
		        </li>
		    </c:if>
		    <c:if test="${itemP == 1060}">
				<li class="dropdown ${menuActive == 'report' ? 'active' : ''}">
		            <a data-toggle="dropdown" href="#"><span style="color: black;">Báo Cáo <b class="caret"></b> </span></a>
		            <ul class="dropdown-menu">
		            	<c:forEach items="${listMenuLeftChild}" var="itemC">
							<c:if test="${itemC == 1061}">
								<li><a id='submenu_1041' class="submenu" href="<c:url value="/report/realTime/load" />">Realtime</a></li>
							</c:if>
							<c:if test="${itemC == 1062}">
								<li><a id='submenu_1042' class="submenu" href="<c:url value="/report/surveyPurposeLoan/load" />">K/s khoản vay giải ngân</a></li>
							</c:if>
						</c:forEach>
		            </ul>
		        </li>
		    </c:if>
		    <c:if test="${itemP == 1070}">
				<li class="dropdown ${menuActive == 'ticket' ? 'active' : ''}">
		            <a data-toggle="dropdown" href="#"><span style="color: black;">Quản lý Ticket <b class="caret"></b></span></a>
		            <ul class="dropdown-menu">
		            	<c:forEach items="${listMenuLeftChild}" var="itemC">
							<c:if test="${itemC == 1071}">
								<li><a id='submenu_1071' class="submenu" href="<c:url value="/ticket/overview/load" />">Tổng quan</a></li>
							</c:if>
						</c:forEach>
		            </ul>
		        </li>
		    </c:if>
			<c:if test="${itemP == 1010}">
				<li class="dropdown ${menuActive == 'system' ? 'active' : ''}">
		            <a data-toggle="dropdown" href="#"><span style="color: black;">Quản lý hệ thống <b class="caret"></b> </span></a>
		            <ul class="dropdown-menu">
		            	<c:forEach items="${listMenuLeftChild}" var="itemC">
							<c:if test="${itemC == 1011}">
								<li><a id='submenu_1011' class="submenu" href="<c:url value="/system/user/load" />">Q/L người dùng</a></li>
							</c:if>
							<c:if test="${itemC == 1012}">
								<li><a id='submenu_1012' class="submenu" href="<c:url value="/system/role/load" />">Q/L nhóm</a></li>
							</c:if>
							<c:if test="${itemC == 1013}">
								<li><a id='submenu_1013' class="submenu" href="<c:url value="/system/userRole/load" />">Q/L phân nhóm người dùng</a></li>
							</c:if>
							<c:if test="${itemC == 1014}">
								<li><a id='submenu_1014' class="submenu" href="<c:url value="/system/rolePrivilege/load" />">Q/L phân nhóm chức năng</a></li>
							</c:if>
						</c:forEach>
		               
		            </ul>
		        </li>
		    </c:if>
	    </c:forEach>
		<li class="menuLogout" >
			<a onclick="logout();" href="#" id="username"><span style="color: black;">LOGOUT</span></a>
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