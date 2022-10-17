<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<spring:url value="/system/user/add" var="addFormSubmitUrl" />

<form:form modelAttribute="formModel" id="editProfileForm" class="form-horizontal" formMode="create" action="${addFormSubmitUrl}" method="POST">
	<h4 class="widgettitle">Tạo mới người dùng</h4>
	<div class="widgetcontent" style="align-content: center;">
		<c:if test="${formMode == 'create' || formMode == 'edit' }">
			<div id="SendDialog" title="Xác nhận tạo mới tài khoản">
				<p>
					<span class="ui-icon ui-icon-alert" style="float: left; margin: 0 7px 20px 0;"></span>Bạn có thực sự muốn tạo mới tài khoản?
				</p>
			</div>
		</c:if>
		<div>
			<c:if test="${listErrors !=null}">
				<div class="section">
					<h4>Lỗi:</h4>
					<c:forEach var="row" items="${listErrors}" varStatus="statusItem">
						<div class="error">
							<c:choose>
								<c:when test="${row.objectName == 'UserAlreadyExist' }">Người dùng đã tồn tại. Mời chọn tên khác</c:when>
								<c:when test="${row.objectName == 'DataError' }">Lỗi dữ liệu ${row.defaultMessage}</c:when>
								<c:otherwise>Có lỗi xảy ra. Yêu cầu kiểm tra lại thông tin! <br>
									<p>${row.defaultMessage}</p>
								</c:otherwise>
							</c:choose>
						</div>
					</c:forEach>
				</div>
			</c:if>
			<c:if test="${message !=null}">
				<div class="success">
					<p>Bạn đã tạo mới người dùng thành công!</p>
				</div>
			</c:if>
			<div class="alert alert-danger" id="alertshowErrorMsg" style="display:none;"></div>
			<h4>Thông tin người dùng</h4>
			<div class="form-group required">
				<label class="control-label col-sm-2">Tên tài khoản:</label>
				<div class="col-sm-5">
					<form:input path="username" id="username" type="text" cssClass="txtuppercase form-control" required="true"/>
					<form:errors path="username" element="div"></form:errors>
				</div>
			</div>
			<div class="form-group required">
				<label class="control-label col-sm-2">Họ và tên:</label>
				<div class="col-sm-5">
					<form:input path="realname" id="realname" type="text" cssClass="form-control" required="true" />
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2">Cấp trên:</label>
				<div class="col-sm-5">
					<form:select path="parentUsername" id="parentUsername" multiple="true" style="width: 50%;">
						<form:option value="" disabled="disabled"></form:option>
						<form:options items="${userList}" itemLabel="username" itemValue="username" />
					</form:select>
				</div>
			</div>
			<div class="form-group required">
				<label class="control-label col-sm-2">Mật khẩu:</label>
				<div class="col-sm-5">
					<form:input path="password" id="password" type="password" data-toggle="password" cssClass="form-control pwd" maxlength="100" data-rule-minlength="6"/>
				</div>
				<div class="col-sm-1">
					<span class="input-group-btn">
			        	<button class="btn btn-default reveal" type="button"><i class="glyphicon glyphicon-eye-open"></i></button>
			        </span>
				</div>
			</div>
			<div class="form-group required">
				<label class="control-label col-sm-2">Nhập lại mật khẩu:</label>
				<div class="col-sm-5">
					<form:input path="passwordConfirm" id="passwordConfirm" data-toggle="password" cssClass="form-control pwd" type="password" maxlength="100" data-rule-equalTo="#password"/>
				</div>
			</div>
		</div>

		<div class="buttons" style="text-align : left; padding-left: 100px;">
			<button type="submit" class="btn btn-primary" id="btnSave">Thêm</button>
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
	
	$('#parentUsername').select2({
		allowClear: true,
		placeholder : "Lựa chọn",
		language : "vi"
	}).on("select2-close", function(e) {
		$(this).valid();
	});
	
	$('#btnSave').on('click', function(){
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
		
	});
})

$('.txtuppercase').keyup(function() {
	$(this).val($(this).val().toUpperCase());
});
</script>
