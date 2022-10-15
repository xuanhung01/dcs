<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>


<div class="form-contact" style="height: 350px">
	<form class="form-horizontal">
		<h2>Liên hệ</h2>
		<table>
			<tr>
				<td>Tiêu đề</td>
				<td><input value="" /></td>
			</tr>
			<tr>
				<td>Người gửi</td>
				<td><input value="" /></td>
			</tr>
			<tr>
				<td>Số điện thoại</td>
				<td><input value="" /></td>
			</tr>
			<tr>
				<td>Email</td>
				<td><input value="" /></td>
			</tr>
			<tr>
				<td>Nội dung</td>
				<td><textarea value=""></textarea></td>
			</tr>
			<tr>
				<td colspan=2><input class="btn btn-default" type="submit" value="Gửi" /></td>
			</tr>
		</table>
	</form>
</div>
