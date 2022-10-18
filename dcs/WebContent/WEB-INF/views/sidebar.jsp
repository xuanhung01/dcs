<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<nav class="navbar" style="font-weight:bold; font-size:16px;min-height: 40px; position: absolute; ">
      <ul class="nav navbar-nav">
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown">${operatorCode} <span class="glyphicon glyphicon-menu-hamburger pull-right"></span></a>
          <ul class="dropdown-menu">
	          <c:forEach items="${listMenuLeftParent}" var="itemP">
		          <c:if test="${itemP == 1010}">
					<li class="dropdown-submenu">
						<a class="test" tabindex="-1" href="#"><span>Quản lý hệ thống <b class="caret"></b> </span></a>
						<ul class="dropdown-menu">				          
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
					<li class="dropdown-submenu">
						<a class="test" tabindex="-1" href="#"><span>Administrator <b class="caret"></b> </span></a>
						<ul class="dropdown-menu">				          
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
					 <a onclick="logout();" href="#">Đăng xuât &nbsp; <span style="color: black;"></span></a>
				</li>
	       </ul>
        </li>
      </ul>
</nav>

<form name="logoutForm" method="post"
			action="<c:url value="/j_spring_security_logout" />"
			style="display: none">
			
</form>
<style>
.dropdown-submenu {
  position: relative;
}

.dropdown-submenu .dropdown-menu {
  top: 0;
  left: 100%;
  margin-top: -1px;
}
/* style menu  */
.nav {
    /* left:50%; */
    /* margin-left:-150px; */
    /* top:50px; */
    position:absolute;
}
.nav>li>a:hover, .nav>li>a:focus, .nav .open>a, .nav .open>a:hover, .nav .open>a:focus {
    background:#fff;
}
.dropdown {
    background:#fff;
    border:1px solid #ccc;
    border-radius:4px;
    width:150px;    
}
.dropdown-menu>li>a {
    color:#428bca;
}
.dropdown ul.dropdown-menu {
    border-radius:4px;
    box-shadow:none;
    margin-top:20px;
    width:150px;
}
.dropdown ul.dropdown-menu:before {
    content: "";
    border-bottom: 10px solid #fff;
    border-right: 10px solid transparent;
    border-left: 10px solid transparent;
    position: absolute;
    top: -10px;
    right: 16px;
    z-index: 10;
}
.dropdown ul.dropdown-menu:after {
    content: "";
    border-bottom: 12px solid #ccc;
    border-right: 12px solid transparent;
    border-left: 12px solid transparent;
    position: absolute;
    top: -12px;
    right: 14px;
    z-index: 9;
}
</style>
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