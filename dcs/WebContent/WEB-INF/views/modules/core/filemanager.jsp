<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<h1>Danh sách attachment</h1>

<table id="DataTables_Attachments"
	class="table table-striped table-bordered bootstrap-datatable datatable">
	<thead>
		<tr role="row">
			<th>No</th>
			<th>Filename</th>
			<th>Path</th>
			<th>UserId</th>
		</tr>
	</thead>

	<tbody aria-relevant="all" aria-live="polite">
		<c:forEach var="attachment" items="${attachments}" varStatus="status">
			<tr>
				<td>${status.index + 1}</td>
				<td>${attachment.filename}</td>
				<td>${attachment.path}</td>
				<td>${attachment.userId}</td>
			</tr>
		</c:forEach>
	</tbody>
</table>

