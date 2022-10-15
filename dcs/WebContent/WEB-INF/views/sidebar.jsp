<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<nav class="navbar" style="font-weight:bold; font-size:14px;min-height: 40px">
	<ul class="nav nav-pills" >
		<!-- Menu trái  -->
		<c:forEach items="${listMenuLeftParent}" var="itemP">
			<c:if test="${itemP == 1040}">
				<li class="dropdown ${menuActive == 'softCall' || menuActive == 'hardSoft' ? 'active' : ''}">
			        <c:forEach items="${listMenuLeftChild}" var="itemC">
			        	<c:if test="${itemC == 1041}">
							<a href="<c:url value="/dashboard/leaderHardField/load" />"><i class="glyphicon glyphicon-home"></i><span>Trang chủ </span></a>
						</c:if>
						<c:if test="${itemC == 1042}">
							<a href="<c:url value="/dashboard/leaderSoftCall/load" />"><i class="glyphicon glyphicon-home"></i><span>Trang chủ </span></a>
						</c:if>
						<c:if test="${itemC == 1043}">
							<a href="<c:url value="/dashboard/callerSoftCall/load" />"><i class="glyphicon glyphicon-home"></i><span>Trang chủ </span></a>
						</c:if>
						<c:if test="${itemC == 1044}">
							<a href="<c:url value="/dashboard/callerSoftCall/load" />"><i class="glyphicon glyphicon-home"></i><span>Trang chủ </span></a>
						</c:if>
						<c:if test="${itemC == 1045}">
							<a href="<c:url value="/dashboard/callerSoftCall/load" />"><i class="glyphicon glyphicon-home"></i><span>Trang chủ </span></a>
						</c:if>
						<c:if test="${itemC == 1046}">
							<a href="<c:url value="/dashboard/callerSoftCall/load" />"><i class="glyphicon glyphicon-home"></i><span>Trang chủ </span></a>
						</c:if>
						<c:if test="${itemC == 1047}">
							<a href="<c:url value="/dashboard/callerSoftCall/load" />"><i class="glyphicon glyphicon-home"></i><span>Trang chủ </span></a>
						</c:if>
						<c:if test="${itemC == 1048}">
							<a href="<c:url value="/dashboard/callerSoftCall/load" />"><i class="glyphicon glyphicon-home"></i><span>Trang chủ </span></a>
						</c:if>
						<c:if test="${itemC == 1049}">
							<a href="<c:url value="/dashboard/callerSoftCall/load" />"><i class="glyphicon glyphicon-home"></i><span>Trang chủ </span></a>
						</c:if>
						<c:if test="${itemC == 1050}">
							<a href="<c:url value="/dashboard/callerSoftCall/load" />"><i class="glyphicon glyphicon-home"></i><span>Trang chủ </span></a>
						</c:if>
						<c:if test="${itemC == 1051}">
							<a href="<c:url value="/dashboard/callerSoftCall/load" />"><i class="glyphicon glyphicon-home"></i><span>Trang chủ </span></a>
						</c:if>						
					</c:forEach>
				</li>
		    </c:if>
		    <c:if test="${itemP == 1150}">
				<li class="dropdown ${menuActive == 'adhoc' ? 'active' : ''}">
			        <c:forEach items="${listMenuLeftChild}" var="itemC">
						<c:if test="${itemC == 1151}">
							<a href="<c:url value="/dashboard/adhocSearch/load" />"><i class="glyphicon glyphicon-zoom-in"></i><span>Adhoc Search</span></a>
						</c:if>
					</c:forEach>
				</li>
		    </c:if>
		   	<c:if test="${itemP == 1200}">
				<li class="dropdown ${menuActive == 'tool' ? 'active' : ''}">
					<a data-toggle="dropdown" href="#"><i class="glyphicon glyphicon-calendar"></i><span>Công cụ <b class="caret"></b> </span></a>
					<ul class="dropdown-menu">
						<c:forEach items="${listMenuLeftChild}" var="itemC">
							<c:if test="${itemC == 1201}">
								<li><a id='submenu_1201' class="submenu" href="<c:url value="/tool/payoffLoan/load" />"><span>C/c tính thanh lý</span></a></li>
							</c:if>
							<c:if test="${itemC == 1202}">
								<li><a id='submenu_1202' class="submenu" href="<c:url value="/tool/searchCustomer/load" />"><span>Truy vấn KH (SKIP TRACE)</span></a></li>
							</c:if>
							<c:if test="${itemC == 1203}">
								<li><a id='submenu_1203' class="submenu" href="<c:url value="/tool/searchReceipt/load" />"><span>Kiểm soát Biên nhận thanh toán</span></a></li>
							</c:if>
						</c:forEach>
					</ul>
				</li>
		    </c:if>
		    <c:if test="${itemP == 1100}">
				<li class="dropdown ${menuActive == 'admin' ? 'active' : ''}">
		            <a data-toggle="dropdown" href="#"><i class="glyphicon glyphicon-text-color"></i><span>Administration <b class="caret"></b> </span></a>
		            <ul class="dropdown-menu">
		            	<c:forEach items="${listMenuLeftChild}" var="itemC">
							<c:if test="${itemC == 1101}">
								<li><a id='submenu_1101' class="submenu" href="<c:url value="/admin/noteCode/load" />">Q/l Group - note code</a></li>
							</c:if>
							<c:if test="${itemC == 1102}">
								<li><a id='submenu_1102' class="submenu" href="<c:url value="/admin/categoryNoteCode/load" />">D/m Note Code</a></li>
							</c:if>
							<c:if test="${itemC == 1103}">
								<li><a id='submenu_1103' class="submenu" href="<c:url value="/admin/reallocation/load" />">Tái phân bổ KH</a></li>
							</c:if>
							<c:if test="${itemC == 1104}">
								<li><a id='submenu_1104' class="submenu" href="<c:url value="/admin/uploadUpTrail/load" />">Upload File Up-Trail</a></li>
							</c:if>
							<c:if test="${itemC == 1105}">
								<li><a id='submenu_1105' class="submenu" href="<c:url value="/admin/allocationField/load" />">Phân bổ địa bàn cho FIELD</a></li>
							</c:if>
							<c:if test="${itemC == 1106}">
								<li><a id='submenu_1106' class="submenu" href="<c:url value="/admin/uploadReCase/load" />">Upload File Re-Case</a></li>
							</c:if>
							<c:if test="${itemC == 1107}">
								<li><a id='submenu_1107' class="submenu" href="<c:url value="/admin/groupCodeUser/load" />">Gán Group User</a></li>
							</c:if>
							<c:if test="${itemC == 1108}">
								<li><a id='submenu_1108' class="submenu" href="<c:url value="/admin/hideMobileCus/load" />">Q/l Số điện thoại Main</a></li>
							</c:if>
							<c:if test="${itemC == 1109}">
								<li><a id='submenu_1109' class="submenu" href="<c:url value="/admin/caseMarking/load" />">Q/l Case Marking</a></li>
							</c:if>
							<c:if test="${itemC == 1110}">
								<li><a id='submenu_1110' class="submenu" href="<c:url value="/admin/document/load" />">Q/l Hồ sơ chứng từ của hợp đồng</a></li>
							</c:if>							
						</c:forEach>
		               
		            </ul>
		        </li>
		    </c:if>
		    <c:if test="${itemP == 1250}">
				<li class="dropdown ${menuActive == 'report' ? 'active' : ''}">
		            <a data-toggle="dropdown" href="#"><i class="glyphicon glyphicon glyphicon-btc"></i><span>áo cáo <b class="caret"></b> </span></a>
		            <ul class="dropdown-menu">
		            	<c:forEach items="${listMenuLeftChild}" var="itemC">
							<c:if test="${itemC == 1251}">
								<li><a id='submenu_1251' class="submenu" href="<c:url value="/report/paymentToday/load" />">Tiền về trong ngày - HF</a></li>
							</c:if>
							<c:if test="${itemC == 1252}">
								<li><a id='submenu_1252' class="submenu" href="<c:url value="/report/trailToday/load" />">Kết quả thu hồi nợ - F</a></li>
							</c:if>
							<c:if test="${itemC == 1253}">
								<li><a id='submenu_1253' class="submenu" href="<c:url value="/report/paymentDaily/load" />">Payment Daily - HF</a></li>
							</c:if>
						</c:forEach>
		               
		            </ul>
		        </li>
		    </c:if>
		    <c:if test="${itemP == 1300}">
				<li class="dropdown ${menuActive == 'autoDialer' ? 'active' : ''}">
		            <a data-toggle="dropdown" href="#"><i class="glyphicon glyphicon-phone-alt"></i><span>Auto Dialer <b class="caret"></b> </span></a>
		            <ul class="dropdown-menu">
		            	<c:forEach items="${listMenuLeftChild}" var="itemC">
							<c:if test="${itemC == 1301}">
								<li><a id='submenu_1301' class="submenu" href="<c:url value="/autoDialer/dashboard/load" />">Tổng hợp ngắn</a></li>
							</c:if>							
							<c:if test="${itemC == 1302}">
								<li><a id='submenu_1302' class="submenu" href="<c:url value="/autoDialer/config/load" />">Upload & Setup</a></li>
							</c:if>
							<c:if test="${itemC == 1303}">
								<li><a id='submenu_1301' class="submenu" href="<c:url value="/autoDialer/dashboardSoftNew/load" />">Tổng hợp ngắn Group New</a></li>
							</c:if>
							<c:if test="${itemC == 1304}">
								<li><a id='submenu_1304' class="submenu" href="<c:url value="/autoDialer/configSoftNew/load" />">Config DPD Group New</a></li>
							</c:if>
							<c:if test="${itemC == 1305}">
								<li><a id='submenu_1301' class="submenu" href="<c:url value="/autoDialer/dashboardSoftNew10/load" />">Tổng hợp ngắn Group New 10</a></li>
							</c:if>
							<c:if test="${itemC == 1306}">
								<li><a id='submenu_1304' class="submenu" href="<c:url value="/autoDialer/configSoftNew10/load" />">Config DPD Group New 10</a></li>
							</c:if>
							<c:if test="${itemC == 1307}">
								<li><a id='submenu_1301' class="submenu" href="<c:url value="/autoDialer/dashboardSoftBom/load" />">Tổng hợp ngắn Group Bom</a></li>
							</c:if>
							<c:if test="${itemC == 1308}">
								<li><a id='submenu_1304' class="submenu" href="<c:url value="/autoDialer/configSoftBom/load" />">Config DPD Group Bom</a></li>
							</c:if>
						</c:forEach>
		            </ul>
		        </li>
		    </c:if>
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
							<c:if test="${itemC == 1013}">
								<li><a id='submenu_1013' class="submenu" href="<c:url value="/system/partner/load" />">Q/L đối tác</a></li>
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