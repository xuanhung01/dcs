<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<meta name="_csrf" content="${_csrf.token}" />
<!-- default header name is X-CSRF-TOKEN -->
<meta name="_csrf_header" content="${_csrf.headerName}" />

<!-- <title>Công ty tài chính SHB Finance</title> -->

<jsp:include page="common/commoninclude.jsp" />
<jsp:include page="admininclude.jsp" />

<link href="<c:url value="/resources/css/dashboard.css" />" rel="stylesheet">
<script src="<c:url value="/resources/js/pages/dashboard.js" />"></script>
</head>
<body>
	<c:choose>
		<c:when test="${formList != 'formMain'}">
				<%-- <jsp:include page="menu.jsp" /> --%>
			<div class="container-fluid">				
				<jsp:include page="sidebar.jsp" />
				<div>
					<c:choose>
						<c:when test="${subpage == 'system_users'}">
							<jsp:include page="modules/core/users.jsp" />
						</c:when>
						<c:when test="${subpage == 'system_roles'}">
							<jsp:include page="modules/core/roles.jsp" />
						</c:when>
						<c:when test="${subpage == 'system_users_roles'}">
							<jsp:include page="modules/core/userRole/list.jsp" />
						</c:when>
						<c:when test="${subpage == 'system_users_privilege'}">
							<jsp:include page="modules/core/rolePrivilege/list.jsp" />
						</c:when>
						<c:when test="${subpage == 'admin_upload_customerLd'}">
							<jsp:include page="modules/admin/uploadCustomerLd.jsp" />
						</c:when>
						<c:when test="${subpage == 'blank'}">
						</c:when>
						<c:otherwise>
							<%-- <jsp:include page="modules/listDashboard/listDashboard.jsp" /> --%>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
		</c:when>
		<c:otherwise>
			<!-- Form Main -->
			<div class="container-fluid">
				<br/>
				<div class="col-md-offset-0">
						<%-- <jsp:include page="modules/listDashboard/formMain.jsp" /> --%>
				</div>
			</div>
		</c:otherwise>
	</c:choose>
</body>
<jsp:include page="common/commoninclude-end.jsp" />
<jsp:include page="admininclude-end.jsp" />


<script>
var username = '${operatorCode}';
$(function() {
	/*  */
	 $(".txtformatDt").datepicker({
		 minDate: 0
	}).mask("00/00/0000");
	
	 $(".txtInputDate").datepicker({
			changeMonth: true,
		    changeYear: true
	 }).mask("00/00/0000");
	
	 $(".txtformatMoney").mask("N###.###.###.###.###", {
	        reverse: true,
	        translation: {
	            '#': {
	                pattern: /-|\d/,
	                recursive: true
	            }
	        },
	        onChange: function (value, e) {
	            e.target.value = value.replace(/(?!^)-/g, 	'').replace(/^,/, '').replace(/^-,/, '-');
	        }
	 });
	 
	 /*  */
	 $(".txtformatNumber").mask("0000000000000000", {reverse: true});
})

function is_empty(x)
{
    return (                                                           //don't put newline after return
        (typeof x == 'undefined')
              ||
        (x == null)
              ||
        (x == false)        //same as: !x
              ||
        (x.length == 0)
              ||
        (x == 0)            // note this line, you might not need this. 
              ||
        (x == "")
              ||
        (x.replace(/\s/g,"") == "")
              ||
        (!/[^\s]/.test(x))
              ||
        (/^\s*$/.test(x))
    );
}

</script>
<style>

.progress-bar.animate {
   	width: 100%;
}
  
#sidebar-left {
    display: inline;
    white-space: nowrap;
    width: 65px;
    bottom: 0px;
    opacity : 1;
    /* HOVER OFF */
   -webkit-transition: width, bottom ,opacity 1s;
   border-radius: 6px;
   padding-right: 10px;
   padding-top: 0px;
   transition: all .75s ease-in-out 1s;
}   

#sidebar-left:hover {
	width: 250px;
	bottom: auto;
	opacity : 1;
	background-color: #fd7e14;
	color: #ffffff; important;
	opacity : 0.9;
	transition: all .25s ease-in-out 0s; 
}    

#sidebar-left:hover {
	
}  

#sidebar-left {  
    pointer-events: none;
}

#sidebar-left li {
    pointer-events: auto;
}

.glyphicon.glyphicon-earphone {
   	font-size: 17px;
}

.glyphicon.glyphicon-ok {
   	color: green;
}

.glyphicon.glyphicon-education {
   	color: #800000;
   	font-size: 20px;
}
</style>
</html>