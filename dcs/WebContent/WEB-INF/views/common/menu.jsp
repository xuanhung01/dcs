<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- Static navbar -->
<nav class="navbar navbar-default">
	<div class="container-fluid">
		<div id="navbar" class="navbar-collapse collapse">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#navbar" aria-expanded="false"
					aria-controls="navbar">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="<c:url value="/" />">Trang chủ</a>
			</div>
			<ul class="nav navbar-nav">
				<li class="dropdown"><a href="<c:url value="/" />user/forms/1">Nộp hồ sơ</a>
				<li><a href="<c:url value="/" />user/register">Tạo tài khoản</a></li>
				<li><a href="<c:url value="/" />login">Đăng nhập</a></li>
				<li><a href="<c:url value="/" />contact">Liên hệ</a></li>
				<li><a href="<c:url value="/" />help">Hướng dẫn</a></li>
			</ul>
		</div>
		<!--/.nav-collapse -->
	</div>
	<!--/.container-fluid -->
</nav>
