<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<spring:url value="/system/rolePrivilege/search?${_csrf.parameterName}=${_csrf.token}" var="searchSubmitUrl" />

<h4 class="widgettitle">
	Quản lý phân role chức năng<span id="help1"></span>
</h4>
<div class="widgetcontent">
	<form:form class="form-horizontal" id="form_BaoCaoHoSo" method="post"
		modelAttribute="formModel" action="${searchSubmitUrl}"
		acceptCharset="utf-8" enctype="multipart/form-data">
		<div class="section">
			<div class="form-group">
				<label class="control-label col-sm-2" for="TinhThanh">Danh sách role:</label>
				<div class="col-sm-8">
					<form:select path="tempRole" id="tempRole" style="width: 50%;">
						<form:options items="${listRole}" itemLabel="name" itemValue="id" />
					</form:select>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2" for="TinhThanh">Danh sách chức năng:</label>
				<div class="col-sm-8">
					<form:select path="tempPrivilege" id="tempPrivilege" style="width: 50%;">
						<form:options items="${listPrivilege}" itemLabel="name" itemValue="id" />
					</form:select>
				</div>
			</div>
			<div class="buttons">
				<button type="button" id="save" class="btn btn-primary">
					<i class="icon-save"></i> Phân chức năng
				</button>
			</div>
		</div>
	</form:form>
	<br />
	
	<div class="row">
		<div class="dual-list list-left col-md-5">
			<h4>
				Danh sách chức năng chưa phân role
			</h4>
			<div class="well">
				<div class="row">
					<div class="col-md-10">
						<div class="input-group">
							<span class="input-group-addon glyphicon glyphicon-search"></span>
							<input type="text" name="SearchDualList" class="form-control"
								placeholder="search" />
						</div>
					</div>
					<div class="col-md-2">
						<div class="btn-group">
							<a class="btn btn-default selector" title="select all"><i
								class="glyphicon glyphicon-unchecked"></i></a>
						</div>
					</div>
				</div>
				<ul class="list-group">
					<c:if test="${listPrivilegeLeft !=null}">
						<c:forEach var="row" items="${listPrivilegeLeft}" varStatus="statusItem">
							<li class="list-group-item" id="${row.id}">
								<div class="row">
									<div class="col-sm-2">${row.id}</div>
									<div class="col-sm-6">${row.name}</div>
								</div>
							</li>
						</c:forEach>				
					</c:if>
				</ul>
			</div>
		</div>

		<div class="list-arrows col-md-1 text-center">
			<button class="btn btn-default btn-lg move-right">
				<span class="glyphicon glyphicon-chevron-right"></span>
			</button>

			<button class="btn btn-default btn-lg move-left">
				<span class="glyphicon glyphicon-chevron-left"></span>
			</button>
		</div>

		<div class="dual-list list-right col-md-5">
			<h4>
				Danh sách chức năng đã phân role
			</h4>
			<div class="well">
				<div class="row">
					<div class="col-md-2">
						<div class="btn-group">
							<a class="btn btn-default selector" title="select all"><i
								class="glyphicon glyphicon-unchecked"></i></a>
						</div>
					</div>
					<div class="col-md-10">
						<div class="input-group">
							<input type="text" name="SearchDualList" class="form-control"
								placeholder="search" /> <span
								class="input-group-addon glyphicon glyphicon-search"></span>
						</div>
					</div>
				</div>
				<ul class="list-group">
					<c:if test="${listPrivilegeRight !=null}">
						<c:forEach var="row" items="${listPrivilegeRight}" varStatus="statusItem">
							<li class="list-group-item" id="${row.id}">
							<div class="row">
									<div class="col-sm-2">${row.id}</div>
									<div class="col-sm-6">${row.name}</div>
								</div>
							</li>
						</c:forEach>		
					</c:if>					
				</ul>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	var csrfToken = $("meta[name='_csrf']").attr("content");
	
	$(function() {
		//
		var table = $("#DataTables_UserRole").DataTable({
			"language" : DATA_TABLE_LANG_VI,
			"bSort" : true
		});

		$('#tempRole').select2({
			placeholder : "",
			language : "vi",
		}).on("change", function(e) {
			$(this).valid();
			$("#form_BaoCaoHoSo" ).submit();
		});
		
		$('#tempPrivilege').select2({
			placeholder : "",
			language : "vi",
		}).on("change", function(e) {
			$(this).valid();
			$("#form_BaoCaoHoSo" ).submit();
		});
		
		$("#save").click(function(e) {
			e.preventDefault();
			var strId = "@@";
			$('.list-right ul li').each(function(i)
			{
				strId += $(this).attr('id'); 
				strId += '@@';
			});
			var tempRole = $('select[id=tempRole]').val()
			var tempPrivilege = $('select[id=tempPrivilege]').val()
			var url = "<c:url value="/" />system/rolePrivilege/save/" + strId +"/"+ tempRole + "/" +tempPrivilege;
			window.location.href = url + '?token=' + csrfToken;
		});
		
		$('body').on('click', '.list-group .list-group-item', function() {
			
			$(this).toggleClass('active');
		});
		$('.list-arrows button').click(function() {
			var $button = $(this), actives = '';
			if ($button.hasClass('move-left')) {
				actives = $('.list-right ul li.active');
				actives.clone().appendTo('.list-left ul');
				actives.remove();
			} else if ($button.hasClass('move-right')) {
				actives = $('.list-left ul li.active');
				actives.clone().appendTo('.list-right ul');
				actives.remove();
			}
		});
		
		$('.dual-list .selector').click(function() {
			var $checkBox = $(this);
			if (!$checkBox.hasClass('selected')) {
				$checkBox.addClass('selected').closest('.well').find(
						'ul li:not(.active)').addClass('active');
				$checkBox.children('i').removeClass(
						'glyphicon-unchecked').addClass(
						'glyphicon-check');
			} else {
				$checkBox.removeClass('selected').closest('.well')
						.find('ul li.active').removeClass('active');
				$checkBox.children('i').removeClass('glyphicon-check')
						.addClass('glyphicon-unchecked');
			}
		});
		
		$('[name="SearchDualList"]').keyup(function(e) {
			var code = e.keyCode || e.which;
			if (code == '9')
				return;
			if (code == '27')
				$(this).val(null);
			var $rows = $(this).closest('.dual-list').find('.list-group li');
			var val = $.trim($(this).val()).replace(/ +/g, ' ').toLowerCase();
			$rows.show().filter(function() {
				var text = $(this).text().replace(/\s+/g, ' ').toLowerCase();
				return !~text.indexOf(val);
			}).hide();
		});
	});
</script>

<style>
.dual-list .list-group {
	margin-top: 8px;
}

.list-left li, .list-right li {
	cursor: pointer;
}

.list-arrows {
	padding-top: 100px;
}

.list-arrows button {
	margin-bottom: 20px;
}
</style>
