<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<spring:url value="/system/user/edit" var="editFormSubmitUrl" />

<form:form modelAttribute="formModel" id="editProfileForm" class="form-horizontal" formMode="create" action="${editFormSubmitUrl}" method="POST">
	<h4 class="widgettitle">Chỉnh sửa thông tin người dùng</h4>
	<div class="widgetcontent">
		<div>
			<c:if test="${listErrors !=null}">
				<div class="section">
					<h4>Lỗi:</h4>
					<c:forEach var="row" items="${listErrors}" varStatus="statusItem">
						<div class="error">
							<c:choose>
								<c:when test="${row.objectName == 'UserAlreadyExist' }">Người dùng hoặc email đã tồn tại. Mời chọn tên khác</c:when>
								<c:when test="${row.objectName == 'DataError' }">Lỗi dữ liệu ${row.defaultMessage}</c:when>
								<c:otherwise>Có lỗi xảy ra. Yêu cầu kiểm tra lại thông tin! <br>
									<p>${row.defaultMessage}</p>
								</c:otherwise>
							</c:choose>
						</div>
					</c:forEach>
				</div>
			</c:if>
			<div class="alert alert-danger" id="alertshowErrorMsg" style="display:none;"></div>
			<h4>Thông tin người dùng</h4>
			<div class="form-group">
				<label class="control-label col-sm-1">Tên tài khoản:</label>
				<div class="col-sm-9">
					<form:input path="userName" id="userName" type="text" style="width: 50%;" readonly="true"/>
				</div>
			</div>
			<div class="form-group required">
				<label class="control-label col-sm-1">Họ và tên:</label>
				<div class="col-sm-9">
					<form:input path="realName" id="realName" type="text" style="width: 50%;" required="true" />
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-1">Group Code:</label>
				<div class="col-sm-1-5">
					<form:select path="groupId" id="groupId" style="width: 100%;">
						<form:option value="" disabled="disabled"></form:option>
						<form:options items="${groupList}" itemLabel="groupCode" itemValue="id" />
					</form:select>
				</div>
				<label class="control-label col-sm-1">Team Code:</label>
				<div class="col-sm-1-5">
					<form:input path="teamCode" id="teamCode" style="width: 100%;" />
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-1">Cấp trên:</label>
				<div class="col-sm-9">
					<form:select path="parentUserName" id="parentUserName" style="width: 50%;">
						<form:option value="" disabled="disabled"></form:option>
						<form:options items="${userList}" itemLabel="userName" itemValue="userName" />
					</form:select>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-1">EXT:</label>
				<div class="col-sm-1-5">
					<form:input path="ext" id="ext" style="width: 100%;" class="form-control txtformatNumber" maxlength="4"/>
				</div>
				<label class="control-label col-sm-1">HR CODE:</label>
				<div class="col-sm-1-5">
					<form:input path="staffCode" id="staffCode" style="width: 100%;" class="form-control" maxlength="12"/>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-1">Ngày hiệu lực p/b:</label>
				<div class="col-sm-1-5">
					<form:input path="startAllocatedDate" id="startAllocatedDate" style="width: 100%;" class="form-control txtInputDate" maxlength="12"/>
				</div>
				<label class="control-label col-sm-1">Ngày kết thúc p/b:</label>
				<div class="col-sm-1-5">
					<form:input path="endAllocatedDate" id="endAllocatedDate" style="width: 100%;" class="form-control txtInputDate" maxlength="12"/>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-1">Đối tác:</label>
				<div class="col-sm-9">
					<form:checkbox path="hasPartner" id="hasPartner" data-toggle="toggle" data-size="small" data-on="Có" data-off="Không" />
				</div>
			</div>	
			<div class="form-group">
				<label class="control-label col-sm-1">Mật khẩu:</label>
				<div class="col-sm-9">
					<form:input path="password" id="password" type="password" style="width: 50%;" disabled="true" maxlength="100" data-rule-minlength="6"/>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-1">Nhập lại mật khẩu:</label>
				<div class="col-sm-9">
					<form:input path="passwordConfirm" id="passwordConfirm" type="password" style="width: 50%;" disabled="true" maxlength="100" data-rule-equalTo="#password"/>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-1">Mô tả đối tác:</label>
				<div class="col-sm-9">
					<form:input path="partnerName" id="partnerName" type="text" style="width: 50%;" disabled="true"/>
				</div>
			</div>
		</div>

		<div class="buttons" style="text-align : left; padding-left: 100px;">
			<button type="submit" class="btn btn-primary" id="btnSave">Cập nhật</button>
			<a href='<c:url value="/system/user/load" />' class="btn btn-default"><i class="glyphicon glyphicon-step-backward"></i>Quay lại</a>
		</div>
	</div>
</form:form>
<script src="<c:url value="/resources/js/pages/forms/register.js" />"></script>
<script>
var arrTeamCode = [];
var arrAllTeamCode = [];
var arrAllGroup = [];
var arrAllPartner = [];

/* Lấy danh sách Group và Team  */
<c:forEach var="row" items="${listDebtGroupTeam}" varStatus="statusItem">
	arrAllTeamCode.push({
		groupCode : "${row.groupCode}",
		teamCode : "${row.teamCode}"
	});
</c:forEach>

/* Lấy danh sách Group */
<c:forEach var="row" items="${groupList}" varStatus="statusItem">
	arrAllGroup.push({
		groupId : "${row.id}",
		groupCode : "${row.groupCode}"
	});
</c:forEach>
/* Lấy danh sách Parter */
<c:forEach var="row" items="${listDebtPartner}" varStatus="statusItem">
	arrAllPartner.push({
		'id' : "${row.partnerCode}",
		'text' : "${row.partnerCode}"
	});
</c:forEach>

$(function() {
	if($('#groupId').val() != ''){
		var groupCode = null;
		$.each(arrAllGroup, function( index, value ) {
			if($('#groupId').val() == value.groupId){
				groupCode = value.groupCode;
			}
		});
		$.each(arrAllTeamCode, function( index, value ) {
			if(groupCode == value.groupCode){
				arrTeamCode.push({
					'id' : value.teamCode,
					'text' : value.teamCode
				});
			}
		});
		var teamCode = $('#teamCode').val();
		$("#teamCode").select2(
		{
			placeholder : "Lựa chọn",
			language : "vi",
			data : arrTeamCode,
			allowClear: true
		}).on("select2-close", function(e) {
			$(this).valid();
		});
		$("#teamCode").val({'id' : teamCode,'text' : teamCode}).trigger("change");
		
	} else {
		$("#teamCode").select2(
		{
			placeholder : "Lựa chọn",
			language : "vi",
			data : arrTeamCode,
			allowClear: true,
			query : function(query) {
				CMSCommonJs.Select2.queryArray(arrTeamCode, query);
			}
		});
	}
	
	$('#groupId').select2({
		allowClear: true,
		placeholder : "Lựa chọn",
		language : "vi"
	}).on("select2-selected",function(evt) {
	    var theID = $('#groupId').select2('data').id;
	    var theSelection = $('#groupId').select2('data').text;
		if (evt.val) {
			arrTeamCode = [];
			$.each(arrAllTeamCode, function( index, value ) {
				if(theSelection == value.groupCode){
					arrTeamCode.push({
						'id' : value.teamCode,
						'text' : value.teamCode
					});
				}
			});
			$('#teamCode').select2('data', null);
			$('#teamCode').select2('destroy');
			$("#teamCode").select2(
			{
				placeholder : "Lựa chọn",
				language : "vi",
				data : arrTeamCode,
				allowClear: true,
				query : function(query) {
					CMSCommonJs.Select2.queryArray(arrTeamCode, query);
				}
			});
		}
	}).on("select2-removed",function(evt) {
		arrTeamCode = [];
        $('#teamCode').select2('data', null);
        $('#teamCode').select2('destroy');
		$("#teamCode").select2(
		{
			placeholder : "Lựa chọn",
			language : "vi",
			data : arrTeamCode,
			allowClear: true,
			query : function(query) {
				CMSCommonJs.Select2.queryArray(arrTeamCode, query);
			}
		});
	}).on("select2-close", function(e) {
		$(this).valid();
	});
	
	$('#parentUserName').select2({
		allowClear: true,
		placeholder : "Lựa chọn",
		language : "vi"
	}).on("select2-close", function(e) {
		$(this).valid();
	});
	
	if($('#hasPartner').prop('checked')){
		$("#password").prop('disabled', false);
		$("#passwordConfirm").prop('disabled', false);
		var partnerName = $('#partnerName').val();
		$("#partnerName").prop('disabled', false);
		$("#partnerName").select2(
		{
			placeholder : "Lựa chọn",
			language : "vi",
			data : arrAllPartner,
			allowClear: true
		}).on("select2-close", function(e) {
			$(this).valid();
		});
		$("#partnerName").val({'id' : partnerName,'text' : partnerName}).trigger("change");
	} else {
		$("#password").prop('disabled', true);
		$("#password").val('');
		$("#passwordConfirm").prop('disabled', true);
		$("#passwordConfirm").val('');
		$("#partnerName").prop('disabled', true);
		$("#partnerName").val('');
	}
	
	$('#hasPartner').change(function() {
   		var checked = $(this).prop('checked');
   		if(checked){
   			$("#password").prop('disabled', false);
   			$("#passwordConfirm").prop('disabled', false);
   			$("#partnerName").prop('disabled', false);	
   			$('#partnerName').select2('data', null);
			$('#partnerName').select2('destroy');
   			$("#partnerName").select2(
			{
				placeholder : "Lựa chọn",
				language : "vi",
				data : arrTeamCode,
				allowClear: true,
				query : function(query) {
					CMSCommonJs.Select2.queryArray(arrAllPartner, query);
				}
			}).on("select2-close", function(e) {
				$(this).valid();
			});
   		} else {
   			$("#password").prop('disabled', true);
   			$("#password").val('');
   			$("#passwordConfirm").prop('disabled', true);
   			$("#passwordConfirm").val('');
			$('#partnerName').select2('data', null);
			$('#partnerName').select2('destroy');
   			$("#partnerName").prop('disabled', true);
   		}
	});
	
	$('#btnSave').on('click', function(){
   		var checked = $('#hasPartner').prop('checked');
   		if(checked){
   			if($.trim($("#password").val()) == ''){
		   		alertError('Mật khẩu bạn chưa nhập');
		   		return false;
   			}
   			if($("#password").val() != $("#passwordConfirm").val()){
   				alertError('Mật khẩu và nhập lại chưa trùng khớp');
		   		return false;
   			}
   			if($.trim($("#partnerName").select2("val")) == ''){
   				alertError('Mô tả đối tác bạn chưa nhập');
		   		return false;
   			}
		}
	});
})
</script>
