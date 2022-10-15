<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<h3>
	<i class="icon-bar-chart"></i> Báo cáo/ Thống kê
</h3>
<hr>
<h4>Thống kê toàn hệ thống</h4>
<div class="section">
	<div>
		Số lượng người dùng: <b>${numUsers} <i class="icon icon-user"></i></b>
	</div>

	<div>
		Số lượng tờ khai: <b>${numToKhai} <i class="icon icon-file"></i></b>
	</div>
</div>
<hr>
<c:if test='${tenDvcm!=null}'>
	<h4>
		Thống kê của đơn vị <b>${tenDvcm}</b>
	</h4>
	<div class="section">
		<div>
			Số lượng người dùng: <b>${numUsersDvcm} <i class="icon icon-user"></i></b>
		</div>

		<div>
			Số lượng tờ khai: <b>${numToKhaiDvcm} <i class="icon icon-file"></i></b>
		</div>
	</div>
	<hr>
</c:if>
