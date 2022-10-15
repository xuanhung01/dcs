<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<br/>
<form:form id="formLogin" class="form-signin" action="j_spring_security_check" method="post">
	<!-- <h4 class="widgettitle"><b>DEBT COLLECTION SYSTEM</b></h4> -->
	<c:if test="${param.error != null}">
		<div class="alert-danger fade in" id="alertshowErrorMsg1">
			<c:choose>
				<c:when test="${SPRING_SECURITY_LAST_EXCEPTION.message == 'disabled'}">
					<div class="alert alert-danger" >Tài khoản đang bị disabled!</div>
				</c:when>
				<c:when test="${SPRING_SECURITY_LAST_EXCEPTION.message == 'ldap' || SPRING_SECURITY_LAST_EXCEPTION.message == 'password' }">
            		Tài khoản và mật khẩu không chính xác! <br/>Hoặc Đăng nhập quá 5 lần phải đợi trong 30 phút cho lần đăng nhập tiếp theo
        		</c:when>
        		<c:when test="${SPRING_SECURITY_LAST_EXCEPTION.message == 'hasDcs'}">
            		Tài khoản không có quyền truy cập!
        		</c:when>
        		<c:when test="${SPRING_SECURITY_LAST_EXCEPTION.message == 'captcha'}">
            		Bạn chọn vào captcha.Xin mời chọn!
        		</c:when>
        		<c:when test="${SPRING_SECURITY_LAST_EXCEPTION.message == 'invalidJSession'}">
            		Tài khoản đang đăng nhập.Bạn hãy chờ lượt sau!
        		</c:when>
				<c:when test="${SPRING_SECURITY_LAST_EXCEPTION.message == 'not found'}">
            		Tài khoản chưa tồn tại trong hệ thống DCS!
        		</c:when>
        		<c:when test="${SPRING_SECURITY_LAST_EXCEPTION.message == 'lock'}">
            		Bạn login sai mật khẩu quá 5 lần. Tài khoản sẽ bị lock trong 10 phút!
        		</c:when>
				<c:otherwise>
					<div class="alert alert-danger" >Tài khoản chưa tồn tại trong hệ thống!</div>
				</c:otherwise>
			</c:choose>
		</div>
	</c:if>
	<div style="padding: 4px">
		<div class="input-group">
	   		<div class="input-group-addon">
			<span class="glyphicon glyphicon-user"></span> 
	   		</div>
	   		<label for="j_username" class="sr-only">Tài khoản</label> <input type="text" id="j_username" name="j_username" class="form-control" placeholder="Tên tài khoản" required autofocus>
  		</div>
		
	</div>
	<div style="padding: 4px">
		<div class="input-group">
	   		<div class="input-group-addon">
			<span class="glyphicon glyphicon-lock"></span> 
	   		</div>
	   		<label for="j_password" class="sr-only">Mật khẩu</label> <input type="password" id="j_password" name="j_password" class="form-control" placeholder="Mật khẩu" required>
  		</div>
	</div>
	
	<!-- 
	<div class="checkbox">
		<label> <input type="checkbox" value="remember-me"> Ghi nhớ
		</label>
	</div>
	 -->
	<div>
		<button class="btn btn-lg btn-primary btn-block" type="submit" 
			style="color: #0000009e; background-color: #ececf3;border-color: #ececf3">Đăng nhập</button>
	</div>

	<%-- <div>
		<a href="<c:url value="/user/register" />">Đăng ký</a> <br> <a href="<c:url value="/user/forgetPassword" />">Quên mật khẩu?</a>
	</div> --%>
	<script>
		$('#formLogin').validate({
			errorClass : "valError",
			errorElement : "span",
			ignore : 'input[type=hidden]'
		});
	</script>
</form:form>

