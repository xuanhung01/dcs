<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="false"%>


<div class="row-fluid">

	<div class="col-sm-7 task-list">
		<h1>Danh sách hồ sơ</h1>

		<div class="priority high">
			<span>Chưa duyệt</span>
		</div>

		<c:forEach var="row" items="${listChuaTiepNhan}" varStatus="statusItem">
			<div class="task high">
				<div class="desc">
					<div class="title">${row.ten}</div>
					<div>${row.nguoiDangKy}</div>
				</div>
				<div class="time">
					<div class="date">${row.ma}</div>
				</div>
			</div>
		</c:forEach>
		<div class="priority medium">
			<span>Chờ duyệt</span>
		</div>

		<c:forEach var="row" items="${listChoDuyet}" varStatus="statusItem">
			<div class="task medium">
				<div class="desc">
					<div class="title">${row.ten}</div>
					<div>${row.nguoiDangKy}</div>
				</div>
				<div class="time">
					<div class="date">${row.ma}</div>
				</div>
			</div>
		</c:forEach>

		<div class="priority low">
			<span>Đã duyệt</span>
		</div>

		<c:forEach var="row" items="${listDaDuyet}" varStatus="statusItem">
			<div class="task low">
				<div class="desc">
					<div class="title">${row.ten}</div>
					<div>${row.nguoiDangKy}</div>
				</div>
				<div class="time">
					<div class="date">${row.ma}</div>
				</div>
			</div>
		</c:forEach>

		<div class="clearfix"></div>

	</div>

	<div class="col-sm-5 graph">

		<h1>Thời gian chi tiết</h1>

		<div class="timeline">

			<div class="timeslot">

				<div class="task">
					<span> <span class="type">Hồ sơ</span> <span class="details"> Hồ sơ 1 </span> <span> thời gian còn lại <span class="remaining"> 3 tiếng 10 ngày </span>
					</span>
					</span>
					<div class="arrow"></div>
				</div>
				<div class="icon">
					<i class="icon-map-marker"></i>
				</div>
				<div class="time">5:30 PM</div>

			</div>

			<div class="clearfix"></div>
		</div>

	</div>

</div>


