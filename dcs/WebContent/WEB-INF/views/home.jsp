<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="false"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- <title>Công ty tài chính SHB Finance </title> -->
<%-- <meta name="_csrf" content="${_csrf.token}" /> --%>
<!-- default header name is X-CSRF-TOKEN -->
<%-- <meta name="_csrf_header" content="${_csrf.headerName}" /> --%>

<jsp:include page="common/commoninclude.jsp" />
<link href="<c:url value="/resources/css/main.css" />" rel="stylesheet">
<script src="<c:url value="/resources/js/main.js" />"></script>
</head>
<body>
	<div class="container">
		<jsp:include page="common/header.jsp" />
		<jsp:include page="common/login.jsp" />
		<br style="clear: both" />	
	</div>
</body>
<jsp:include page="common/commoninclude-end.jsp" />
</html>
