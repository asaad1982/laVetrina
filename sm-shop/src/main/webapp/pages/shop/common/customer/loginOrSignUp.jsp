
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
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<link href="<c:url value="/resources/css/jquery-ui.css" />" rel="stylesheet"></link>
<!-- requires functions.jsp -->
<script src="<c:url value="/resources/js/jquery.maskedinput.min.js" />"></script>
<script src="<c:url value="/resources/js/shop-customer.js" />"></script>
<script src="<c:url value="/resources/js/address.js" />"></script>
<%-- <link href="<c:url value="/resources/css/bootstrap/css/datepicker.css" />" rel="stylesheet"></link> --%>
<%--  <script src="<c:url value="/resources/js/bootstrap/bootstrap-datepicker.js" />"></script> --%>
<%-- <script src="<c:url value="/resources/js/ckeditor/ckeditor.js" />"></script> --%>
<%-- <script src="<c:url value="/resources/js/jquery.alphanumeric.pack.js" />"></script> --%>

<script src="<c:url value="/resources/js/jquery-1.10.2.min.js" />"></script>
<script src="<c:url value="/resources/js/jquery-ui.js" />"></script>

<script type="text/javascript">
	var RecaptchaOptions = {
		theme : 'clean'
	};

	$(document)
			.ready(
					function() {

						getZones($('#registration_country').val(),
								'<c:out value="${customer.zone}" />',
								isFormValid);
						$("#hidden_zones").hide();
						$("#registration_country")
								.change(
										function() {
											getZones(
													$(this).val(),
													'<c:out value="${customer.zone}" />',
													isFormValid);
										})

						isFormValid();
						$("input[type='text']").on("change keyup paste",
								function() {
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
	value="${pageContext.request.contextPath}/shop/customer/registration.html" />


<section id="form">
	<!--form-->
	<div class="container">
		<div class="row">
			<h2 class="title text-center">Log in & sign out</h2>
			<div class="col-sm-4 col-sm-offset-1">
				
					<!--login form-->
					<h2>Login to your account</h2>
					<c:if test="${not empty successMsgCode}">
						<div class="alert alert alert-success">
							<s:message code="${successMsgCode}" text="success" />
						</div>
					</c:if>

					<c:if test="${not empty msgCode}">
						<c:if test="${msgCode == '1'}">
							<div class="alert alert-error alert-danger form-group">
								<s:message code="message.email.not.registered" text="code"></s:message>
							</div>
						</c:if>
						<c:if test="${msgCode != '1'}">
							<div class="alert alert-error alert-danger">
								<s:message code="${msgCode}" text="error" />
							</div>
						</c:if>
					</c:if>
						<div class="social-box">
							<div class="row mg-btm">
							<div class="col-md-12">
								<form name='facebookSocialloginForm'
									action="<c:url value='../../auth/facebook?scope=email,user_about_me,user_birthday' />"
									method='POST'>
									<button type="submit" class="btn btn-primary btn-block"> <i
										class="icon-facebook"></i> Login with Facebook
									</button>
								</form>
							</div>
						</div>
							<div class="row">
								<div class="col-md-12">
									<form name='facebookSocialloginForm'
										action="<c:url value='../../auth/twitter?include_email=true' />"
										method='POST'>
										<input type="hidden" name="scope" value="email,user_about_me,user_birthday" />
										<button type="submit" class="btn btn-info btn-block"> <i
											class="icon-twitter"></i>  Login with Twitter
										</button>
									</form>
								</div>
							</div>
						</div>
						<div class="login-form">
					<form class="form-signin mg-btm" action="<c:url value='/shop/customer/j_spring_security_check' />" method='POST'>
						<input type="text" class="form-control" placeholder="Email" name="j_username">
						<input type="password" class="form-control"
							placeholder="Password" name="j_password">
<%-- 							<span> <input --%>
<!-- 							type="checkbox" class="checkbox"> Keep me signed in -->
<%-- 						</span> --%>
						<button type="submit" class="btn btn-default">Login</button>
					</form>
				</div>
				<!--/login form-->
			</div>
			<div class="col-sm-1">
				<h2 class="or">OR</h2>
			</div>
			<div class="col-sm-4">
				<div class="signup-form">
					<!--sign up form-->
					<h2>New User Signup!</h2>
					<form:form method="post" action="${register_url}"
						id="registrationForm" class="form-horizontal"
						commandName="customer">
						<form:errors path="*"
							cssClass="alert alert-error alert-danger form-group"
							element="div" />
						<s:message code="NotEmpty.customer.firstName"
							text="First name is required" var="msgFirstNameReq" />
						<s:message code="label.generic.firstname" text="First name"
							var="msgFirstName" />
						<form:input path="firstName"
							cssClass="required span8 required input form-control form-control-md"
							id="firstName" placeholder="${msgFirstName}"
							title="${msgFirstNameReq}" />
						<font color="red"> <form:errors path="firstName"
								cssClass="error" />
						</font>

						<s:message code="label.generic.lastname" text="Last name"
							var="msgLastName" />
						<s:message code="NotEmpty.customer.lastName"
							text="Last name is required" var="msgLastNameReq" />
						<form:input path="lastName"
							cssClass="span8 required form-control form-control-md"
							id="lastName" title="${msgLastNameReq}"
							placeholder="${msgLastName}" />
						<font color="red"> <form:errors path="lastName"
								cssClass="error" /></font>

						<div class="form-group col-md-12">
							<form:select path="gender" class="form-control form-control-lg">
								<form:option value="M">
									<s:message code="label.generic.male" text="Male" />
								</form:option>
								<form:option value="F">
									<s:message code="label.generic.female" text="Female" />
								</form:option>
							</form:select>
							<font color="red"><form:errors path="gender"
									cssClass="error" /></font>
						</div>

						<div class="form-group col-md-12">
							<div class="controls">
								<form:select path="country"
									class="form-control form-control-lg" id="registration_country">
									<form:options items="${countryList}" itemValue="isoCode"
										itemLabel="name" />
								</form:select>
							</div>
						</div>


						<div class="form-group col-md-12">
							<label class="control-label required"><s:message code="label.generic.stateprovince" text="State / Province"/></label>
							<div class="controls">
							<s:message code="NotEmpty.customer.billing.stateProvince" text="State / Province is required" var="msgStateProvince"/>
							<form:select path="zone" id="customer_zones" class="form-control form-control-lg">
							</form:select>
							<form:input path="stateProvince" cssClass="span8 required form-control form-control-md" id="hidden_zones" title="${msgStateProvince}"/>
							</div>
						</div>	

						<div class="form-group col-md-12">
			            	<label><s:message code="label.customer.billing.streetaddress" text="Street Address"/></label>
							<div class="controls">
					 			<form:input  cssClass="input-large highlight"  maxlength="256"  path="address"/>		 				
				            </div>
		            	</div>

						<s:message code="label.generic.email" text="Email address"
							var="emailMsg" />
						<div class="controls">
							<s:message code="NotEmpty.customer.emailAddress"
								text="Email address is required" var="msgEmail" />
							<form:input path="emailAddress"
								cssClass="span8 required email form-control form-control-md"
								id="email" title="${msgEmail}" placeholder="${emailMsg}" />
							<font color="red"> <form:errors path="emailAddress"
									cssClass="error" /></font>

						</div>


						<s:message code="label.generic.password" text="Password"
							var="passwordMsg" />
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


						<s:message code="label.generic.repeatpassword"
							text="Repeat password" var="repeatpasswordMsg" />
						<div class="controls">
							<s:message code="message.password.repeat.required"
								text="Repeated password is required" var="msgRepeatPassword" />
							<form:password path="checkPassword"
								class="span8 required checkPassword form-control form-control-md"
								id="passwordAgain" title="${msgRepeatPassword}"
								placeholder="${repeatpasswordMsg}" />
							<font color="red"><form:errors path="checkPassword"
									cssClass="error" /></font>
						</div>
						
						<div class="control-group">
	                        <label>Birthday</label>
	                        <div class="controls">
<%-- 	                        <fmt:formatDate value="${customer.birthdate}" pattern="yyyy-MM-dd" var="birthdate" /> --%>
	                       
	                        		 <input id="birthdate" name="birthdate" value="${birthdate}" class="small" type="text" data-date-format="<%=com.salesmanager.core.constants.Constants.DEFAULT_DATE_FORMAT%>" data-datepicker="datepicker"> 
	                                 <script type="text/javascript">
    	                                 $('#birthdate').datepicker();
	                                 </script>
<%-- 	                                 <span class="help-inline"><form:errors path="dateOfBirth" cssClass="error" /></span> --%>
	                        </div>
	                  	</div>


						<div class="form-group col-md-12">
							<div class="controls">
								<!--watch the white space in IOS!-->
								<script type="text/javascript"
									src="http://www.google.com/recaptcha/api/challenge?k=<c:out value="${recapatcha_public_key}"/>&hl=${requestScope.LANGUAGE.code}">
									
								</script>
								<noscript>
									<iframe
										src="http://www.google.com/recaptcha/api/noscript?k=<c:out value="${recapatcha_public_key}"/>&hl=${requestScope.LANGUAGE.code}"
										height="300" width="500" frameborder="0"> </iframe>
									<br />
									<form:textarea path="recaptcha_challenge_field"
										class="form-control" readonly="3" cols="40" />
									<font color="red"><form:errors
											path="recaptcha_challenge_field" cssClass="error" /></font> <input
										type="hidden" name="recaptcha_response_field"
										value="manual_challenge">
								</noscript>
							</div>
						</div>


						<button type="submit" class="btn btn-default">Signup</button>
					</form:form>
				</div>
				<!--/sign up form-->
			</div>
		</div>
	</div>
</section>
<!--/form-->
