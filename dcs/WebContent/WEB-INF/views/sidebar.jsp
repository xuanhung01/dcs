<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<div class="gw-sidebar">
  <div id="gw-sidebar" class="gw-sidebar">
    <div class="nano-content">
      <ul class="gw-nav gw-nav-list">
          <c:forEach items="${listMenuLeftParent}" var="itemP">
	          <c:if test="${itemP == 1010}">
				<li class="arrow-up active"> <a href="javascript:void(0)"> <span class="gw-menu-text">Quản lý hệ thống</span> <b class="gw-arrow"></b> </a>
					<ul class="gw-submenu" style="display: block;">				          
			          <c:forEach items="${listMenuLeftChild}" var="itemC">
						<c:if test="${itemC == 1011}">
							<li><a tabindex="-1" id='submenu_1011' class="submenu" href="<c:url value="/system/user/load"/>">Q/L người dùng</a></li>
						</c:if>
						<c:if test="${itemC == 1012}">
							<li><a tabindex="-1" id='submenu_1012' class="submenu" href="<c:url value="/system/role/load"/>">Q/L nhóm</a></li>
						</c:if>
						<c:if test="${itemC == 1014}">
							<li><a tabindex="-1" id='submenu_1014' class="submenu" href="<c:url value="/system/userRole/load"/>">Q/L phân User - Role</a></li>
						</c:if>
						<c:if test="${itemC == 1015}">
							<li><a tabindex="-1" id='submenu_1015' class="submenu" href="<c:url value="/system/rolePrivilege/load"/>">Q/L phân Role - Privilege</a></li>
						</c:if>
						<c:if test="${itemC == 1017}">
							<li><a tabindex="-1" id='submenu_1017' class="submenu" href="<c:url value="/system/password/load"/>">Cài đặt mật khẩu</a></li>
						</c:if>
					  </c:forEach>
			        </ul>
			  	</li>
			  </c:if>
			  <c:if test="${itemP == 1030}">
				<li class="arrow-up active"> <a href="javascript:void(0)"> <span class="gw-menu-text">Administrator</span> <b class="gw-arrow"></b> </a>
					<ul class="gw-submenu" style="display: block;">				          
			          <c:forEach items="${listMenuLeftChild}" var="itemC">
						<c:if test="${itemC == 1031}">
							<li><a tabindex="-1" id='submenu_1031' class="submenu" href="<c:url value="/admin/uploadCustomerLd/load"/>">Upload Customer - LD</a></li>
						</c:if>
					  </c:forEach>
			        </ul>
			  	</li>
			  </c:if>
          	</c:forEach>
          	<li class="menuLogout" >
				 <a onclick="logout();" href="#">Đăng xuât &nbsp; (${operatorCode}) <span style="color: black;"></span></a>
			</li>
       </ul>
	</div>
  </div>
</div>

<form name="logoutForm" method="post"
			action="<c:url value="/j_spring_security_logout" />"
			style="display: none">
			
</form>

<script>
function logout() {
	sessionStorage.submenu = null;
	document.logoutForm.submit();
}

$(document).ready(function(){
  $('.dropdown-submenu a.test').on("click", function(e){
    $(this).next('ul').toggle();
    e.stopPropagation();
    e.preventDefault();
  });
});
</script>