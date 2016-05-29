
<%
	response.setCharacterEncoding("UTF-8");
	response.setHeader("Cache-Control", "no-cache");
	response.setHeader("Pragma", "no-cache");
	response.setDateHeader("Expires", -1);
%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="/WEB-INF/shopizer-tags.tld" prefix="sm"%>

<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<link href="<c:url value="/resources/css/jquery-ui.css" />" rel="stylesheet"></link>
<!-- requires functions.jsp -->
<script src="<c:url value="/resources/js/jquery-1.10.2.min.js" />"></script>
<script src="<c:url value="/resources/js/jquery-ui.js" />"></script>
<script src="<c:url value="/resources/js/jquery.maskedinput.min.js" />"></script>
<script src="<c:url value="/resources/js/shop-customer.js" />"></script>
<script src="<c:url value="/resources/js/address.js" />"></script>

<script type="text/javascript">
	var RecaptchaOptions = {
		theme : 'clean'
	};

	$(document).ready(
			function() {

				getZones($('#registration_country').val(),
						'<c:out value="${customer.country}" />', isFormValid);
				
				$("#hidden_zones").hide();
				$("#registration_country").change(
						function() {
							getZones($(this).val(),
									'<c:out value="${customer.country}" />',
									isFormValid);
						})

				isFormValid();
				$("input[type='text']").on("change keyup paste", function() {
					isFormValid();
				});

				$("input[type='password']").on("change keyup paste",
						function() {
							isFormValid();
						});

				$("#registration_country").change(function() {
					isFormValid();
				});
				
 				
			});

	function isFormValid() {
		$('#customer_zones option[value="${zoneSelected}"]').attr('selected','selected');
		
		if ($('.alert-error').is(":visible")) {
			return true;
		}

		if ($('.alert-success').is(":visible")) {
			return true;
		}

		$('#registrationError').hide();//reset error message
		var msg = isCustomerFormValid($('#registrationForm'));

		if (msg != null) {//disable submit button
			$('#submitRegistration').addClass('btn-disabled');
			$('#submitRegistration').prop('disabled', true);
			$('#registrationError').html(msg);
			$('#registrationError').show();
			return false;
		} else {
			$('#submitRegistration').removeClass('btn-disabled');
			$('#submitRegistration').prop('disabled', false);
			$('#registrationError').hide();
			return true;
		}
	}
</script>


<c:set var="register_url"
	value="${pageContext.request.contextPath}/shop/customer/updateProfile.html" />


<div id="main-content" class="container" style="padding: 15px 0;">
	<div class="bg">
		<div class="row">
			<div class="col-sm-12">
				<h2 class="title text-center">
					<s:message code="label.updateProfile" text="Manage Account" />
				</h2>
				<form:form method="post" action="${register_url}"
					id="registrationForm" commandName="customer">
					<form:errors path="*"
						cssClass="alert alert-error alert-danger form-group" element="div" />

					<c:if test="${not empty successMsgCode}">
						<div class="alert alert alert-success">
							<s:message code="${successMsgCode}" text="success" />
						</div>
					</c:if>

					<c:if test="${not empty msgCode}">
						<div class="alert alert-error alert-danger">
								<s:message code="${msgCode}" text="error" />
							</div>
					</c:if>
					
					<div class="form-group col-md-6">
						<s:message code="NotEmpty.customer.firstName"
							text="First name is required" var="msgFirstNameReq" />
						<label class="required"><s:message
								code="label.updateProfile.firstName" text="Code" /></label>
						<div class="controls">
							<form:input path="firstName"
								cssClass="required span8 required input form-control form-control-md"
								id="firstName" placeholder="${msgFirstName}"
								title="${msgFirstNameReq}" />
							<font color="red"> <form:errors path="firstName"
									cssClass="error" />
							</font>
						</div>
					</div>
					<div class="form-group col-md-6">
						<label class="required"><s:message
								code="label.updateProfile.lastName" text="Code" /></label>
						<s:message code="NotEmpty.customer.lastName"
							text="Last name is required" var="msgLastNameReq" />
						<div class="controls">
							<form:input path="lastName"
								cssClass="span8 required form-control form-control-md"
								id="lastName" title="${msgLastNameReq}"
								placeholder="${msgLastName}" />
							<font color="red"> <form:errors path="lastName"
									cssClass="error" /></font>
						</div>
					</div>
					<div class="form-group col-md-6">
						<label class="required"><s:message
								code="label.updateProfile.gender" text="Code" /></label>
						<div class="controls">
							<form:select path="gender" class="form-control form-control-lg">
								<form:option value="M">
									<s:message code="label.generic.male" text="Male" />
								</form:option>
								<form:option value="F">
									<s:message code="label.generic.female" text="Female" />
								</form:option>
							</form:select>
						</div>
						<font color="red"><form:errors path="gender"
								cssClass="error" /></font>
					</div>

					<div class="form-group col-md-6">
						<label class="required"><s:message
								code="label.updateProfile.country" text="Code" /></label>
						<div class="controls">
							<form:select path="country" class="form-control form-control-lg"
								id="registration_country">
								<form:options items="${countryList}" itemValue="isoCode"
									itemLabel="name" />
							</form:select>
						</div>
					</div>

					<div class="form-group col-md-6">
							<label class="control-label required"><s:message code="label.generic.stateprovince" text="State / Province"/></label>
						<div class="controls">
							<s:message code="NotEmpty.customer.billing.stateProvince" text="State / Province is required" var="msgStateProvince"/>
							<form:select path="zone" id="customer_zones" class="form-control form-control-lg">
							</form:select>
							<form:input path="stateProvince" cssClass="span8 required form-control form-control-md" id="hidden_zones" title="${msgStateProvince}"/>
						</div>
					</div>	
					
					<div class="form-group col-md-6">
		            	<label><s:message code="label.customer.billing.streetaddress" text="Street Address"/></label>
						<div class="controls">
				 			<form:input  cssClass="span8 required form-control form-control-md"  maxlength="256"  path="address"/>		 				
			            </div>
		            </div>

					<div class="form-group col-md-6">
						<label class="required"><s:message
								code="label.updateProfile.email" text="Code" /></label>
						<div class="controls">
							<s:message code="NotEmpty.customer.emailAddress"
								text="Email address is required" var="msgEmail" />
							<form:input path="emailAddress"
								cssClass="span8 required email form-control form-control-md"
								id="email" title="${msgEmail}" placeholder="${emailMsg}" />
							<font color="red"> <form:errors path="emailAddress"
									cssClass="error" /></font>
						</div>
					</div>

					<div class="form-group col-md-6">
						<label class="required"><s:message
								code="label.updateProfile.password" text="Code" /></label>
						<div class="controls">
							<s:message code="message.password.required"
								text="Password is required" var="msgPassword" />
							<form:password path="password"
								class="span8 required password form-control form-control-md"
								id="password" title="${msgPassword}"
								placeholder="${passwordMsg}" />
							<font color="red"><form:errors path="password"
									cssClass="error" /></font>
						</div>
					</div>
					
					<div class="form-group col-md-6">
	                        <label>Birthday</label>
	                        <div class="controls">
	                        ${customer.birthdate}
	                        <fmt:formatDate value="${customer.birthdate}" pattern="dd/MM/yyyy" var="birthdate" />
	                       
	                        		 <input id="birthdate" name="birthdate" value="${birthdate}" cssClass="span8 required form-control form-control-md" type="text" data-date-format="<%=com.salesmanager.core.constants.Constants.DEFAULT_DATE_FORMAT%>" data-datepicker="datepicker"> 
	                                 <script type="text/javascript">
    	                                 $('#birthdate').datepicker();
	                                 </script>
<%-- 	                                 <span class="help-inline"><form:errors path="dateOfBirth" cssClass="error" /></span> --%>
	                        </div>
	                  	</div>
					
					<div class="form-actions">
						<button type="submit" class="btn btn-primary pull-right">Save</button>
					</div>
				</form:form>
			</div>
			<div class="col-sm-6">
			 	<jsp:include page="/pages/shop/common/customer/customerProfileMenu.jsp" />
			 	<jsp:include page="/pages/shop/common/customer/customerOrdersMenu.jsp" />
			 </div>
		</div>
	</div>
</div>
